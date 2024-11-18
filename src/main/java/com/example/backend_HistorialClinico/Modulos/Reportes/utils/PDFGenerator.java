// PDFGenerator.java
package com.example.backend_HistorialClinico.Modulos.Reportes.utils;


import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Roles;
import com.example.backend_HistorialClinico.Modulos.Reportes.dto.ReporteRequest;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PDFGenerator {

    public static byte[] generarReportePDFMedicos(List<Medico> medicos, ReporteRequest reporteRequest) throws Exception {
        Document document = new Document(PageSize.A4.rotate()); // Página en orientación horizontal
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();

        // Cargar el logo
        try {
            String logoPath = "src\\main\\java\\com\\example\\backend_HistorialClinico\\Modulos\\Reportes\\utils\\eye.png"; // Reemplaza con la ruta de tu logo
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 50); // Ajustar el tamaño del logo
            logo.setAlignment(Image.ALIGN_LEFT);
            document.add(logo);
        } catch (IOException e) {
            System.out.println("Error al cargar el logo: " + e.getMessage());
        }

        // Título del reporte
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.BLACK);
        Paragraph titulo = new Paragraph("Reporte de Empleados - Clínica SSVS", titleFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        // Espacio entre el título y el resumen
        document.add(new Paragraph(" "));

        // Información de resumen
        Font summaryFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);
        Paragraph resumen = new Paragraph("Total de empleados: " + medicos.size() + "\nEmpleados filtrados: " + medicos.size(), summaryFont);
        document.add(resumen);

        // Filtros aplicados
        if (reporteRequest.getFiltros() != null && !reporteRequest.getFiltros().isEmpty()) {
            Paragraph filtrosTitulo = new Paragraph("Filtros aplicados:", summaryFont);
            filtrosTitulo.setSpacingBefore(10);
            document.add(filtrosTitulo);

            for (Map.Entry<String, Object> filtro : reporteRequest.getFiltros().entrySet()) {
                Paragraph filtroTexto = new Paragraph(filtro.getKey() + ": " + filtro.getValue(), summaryFont);
                document.add(filtroTexto);
            }
        }

        // Agregar línea de separación entre los filtros y la tabla
        LineSeparator separator = new LineSeparator(2, 100, new Color(51, 102, 153), Element.ALIGN_CENTER, -2);
        document.add(new Chunk(separator));
        

        // Espacio entre el separador y la tabla
        document.add(new Paragraph(" "));

        // Crear tabla con ancho ajustable automáticamente
        int numColumnas = reporteRequest.getColumnas().size();
        PdfPTable table = new PdfPTable(numColumnas);
        table.setWidthPercentage(100); // Ajusta al 100% del ancho disponible
        table.setSpacingBefore(2f);
        table.setSpacingAfter(10f);

        // Definir anchos proporcionales según el contenido esperado
        float[] columnWidths = new float[numColumnas];
        for (int i = 0; i < numColumnas; i++) {
            String columna = reporteRequest.getColumnas().get(i);
            // Anchos específicos según el tipo de datos
            columnWidths[i] = (columna.equalsIgnoreCase("ID") || columna.equalsIgnoreCase("CI")) ? 1.0f :
                              (columna.equalsIgnoreCase("ESPECIALIDADES") || columna.equalsIgnoreCase("FECHA CONTRATACION")) ? 3.0f :
                              2.1f; // Valor por defecto para el resto de las columnas
        }
        table.setWidths(columnWidths);

        // Encabezado de la tabla
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Color.BLACK); // Fuente reducida para el encabezado
        for (String columna : reporteRequest.getColumnas()) {
            PdfPCell headerCell = new PdfPCell(new Phrase(columna.toUpperCase(), headerFont));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setBorder(Rectangle.BOTTOM); // Solo borde inferior
            headerCell.setBorderWidthBottom(1.5f); // Grosor de la línea inferior
            headerCell.setPadding(4); // Reducir padding para que ocupe menos espacio vertical
            table.addCell(headerCell);
        }

        // Formateador de fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Datos de la tabla sin bordes internos
        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Color.BLACK); // Fuente reducida para los datos
        boolean isEvenRow = true;
        for (Medico medico : medicos) {
            for (String columna : reporteRequest.getColumnas()) {
                String valorCelda;
                switch (columna) {
                    case "id":
                        valorCelda = String.valueOf(medico.getId());
                        break;
                    case "fecha contratacion":
                        valorCelda = medico.getFechaContratacion() != null
                                ? medico.getFechaContratacion().format(formatter)
                                : "N/A";
                        break;
                    case "estado":
                        valorCelda = medico.getEstado();
                        break;
                    case "correo":
                        valorCelda = medico.getUser().getUsername();
                        break;
                    case "ci":
                        valorCelda = medico.getUser().getCi();
                        break;
                    case "nombre":
                        valorCelda = medico.getUser().getNombre();
                        break;
                    case "apellido paterno":
                        valorCelda = medico.getUser().getApellidoPaterno();
                        break;
                    case "apellido materno":
                        valorCelda = medico.getUser().getApellidoMaterno();
                        break;
                    case "fecha nacimiento":
                        valorCelda = medico.getUser().getFechaNacimiento() != null
                                ? medico.getUser().getFechaNacimiento().formatted(formatter)
                                : "N/A";
                        break;
                    case "telefono":
                        valorCelda = medico.getUser().getTelefono();
                        break;
                    case "genero":
                        valorCelda = medico.getUser().getGenero();
                        break;
                    case "rol":
                        Roles role = medico.getUser().getRol();
                        valorCelda = role != null ? role.getNombre() : "N/A";
                        break;
                    case "especialidades":
                        String especialidades = medico.getEspecialidades().stream()
                                .map(especialidad -> especialidad.getNombre())
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("N/A");
                        valorCelda = especialidades;
                        break;
                    default:
                        valorCelda = "N/A";
                        break;
                }
                PdfPCell cell = new PdfPCell(new Phrase(valorCelda, bodyFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(2); // Reducir padding para que ocupe menos espacio vertical
                cell.setBorder(Rectangle.NO_BORDER); // Sin bordes para las celdas de datos

                // Color de fondo para filas alternadas
                if (isEvenRow) {
                    cell.setBackgroundColor(new Color(245, 245, 255)); // Azul muy claro para filas pares
                }

                table.addCell(cell);
            }
            isEvenRow = !isEvenRow; // Alternar color de fila
        }

        // Borde inferior de la tabla
        PdfPCell emptyCell = new PdfPCell();
        emptyCell.setColspan(numColumnas);
        emptyCell.setBorder(Rectangle.TOP); // Solo borde superior
        emptyCell.setBorderWidthTop(1.5f); // Grosor de la línea superior
        table.addCell(emptyCell);

        document.add(table);
        document.close();

        return out.toByteArray();
    }
}
