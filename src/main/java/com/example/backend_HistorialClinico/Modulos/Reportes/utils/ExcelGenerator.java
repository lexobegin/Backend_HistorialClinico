// ExcelGenerator.java
package com.example.backend_HistorialClinico.Modulos.Reportes.utils;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import com.example.backend_HistorialClinico.Modulos.Reportes.dto.ReporteRequest;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelGenerator {

    public static byte[] generarReporteExcelMedicos(List<Medico> medicos, ReporteRequest reporteRequest) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Reporte de Empleados");

        int rowNum = 0;

        // Título del reporte
        Row titleRow = sheet.createRow(rowNum++);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Reporte de Empleados - Clínica SSVS");
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeight(18);
        titleFont.setBold(true);
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, reporteRequest.getColumnas().size() - 1));

        // Filtros aplicados
        if (reporteRequest.getFiltros() != null && !reporteRequest.getFiltros().isEmpty()) {
            Row filtroTitleRow = sheet.createRow(rowNum++);
            Cell filtroTitleCell = filtroTitleRow.createCell(0);
            filtroTitleCell.setCellValue("Filtros aplicados:");
            filtroTitleCell.setCellStyle(titleStyle);

            for (Map.Entry<String, Object> filtro : reporteRequest.getFiltros().entrySet()) {
                Row filtroRow = sheet.createRow(rowNum++);
                Cell filtroCell = filtroRow.createCell(0);
                filtroCell.setCellValue(filtro.getKey() + ": " + filtro.getValue());
            }
        }

        // Agregar una fila de separación simulada
        Row separatorRow = sheet.createRow(rowNum++);
        Cell separatorCell = separatorRow.createCell(0);
        CellStyle separatorStyle = workbook.createCellStyle();
        separatorStyle.setBorderBottom(BorderStyle.THICK);
        separatorStyle.setBottomBorderColor(IndexedColors.BLUE.getIndex());
        separatorCell.setCellStyle(separatorStyle);
        sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, reporteRequest.getColumnas().size() - 1));

        // Encabezados de columnas
        Row headerRow = sheet.createRow(rowNum++);
        XSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeight(12);
        headerFont.setBold(true);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);

        for (int i = 0; i < reporteRequest.getColumnas().size(); i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(reporteRequest.getColumnas().get(i).toUpperCase());
            headerCell.setCellStyle(headerStyle);
        }

        // Estilo para los datos de la tabla
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Llenado de datos
        for (Medico medico : medicos) {
            Row dataRow = sheet.createRow(rowNum++);
            for (int i = 0; i < reporteRequest.getColumnas().size(); i++) {
                String columna = reporteRequest.getColumnas().get(i);
                Cell cell = dataRow.createCell(i);

                // Llenado de los datos según la columna
                switch (columna) {
                    case "id":
                        cell.setCellValue(medico.getId());
                        break;
                    case "fecha contratacion":
                        cell.setCellValue(medico.getFechaContratacion().toString());
                        break;
                    case "estado":
                        cell.setCellValue(medico.getEstado());
                        break;
                    case "correo":
                        cell.setCellValue(medico.getUser().getUsername());
                        break;
                    case "ci":
                        cell.setCellValue(medico.getUser().getCi());
                        break;
                    case "nombre":
                        cell.setCellValue(medico.getUser().getNombre());
                        break;
                    case "apellido paterno":
                        cell.setCellValue(medico.getUser().getApellidoPaterno());
                        break;
                    case "apellido materno":
                        cell.setCellValue(medico.getUser().getApellidoMaterno());
                        break;
                    case "fecha nacimiento":
                        cell.setCellValue(medico.getUser().getFechaNacimiento().toString());
                        break;
                    case "telefono":
                        cell.setCellValue(medico.getUser().getTelefono());
                        break;
                    case "genero":
                        cell.setCellValue(medico.getUser().getGenero());
                        break;
                    case "rol":
                        cell.setCellValue(medico.getUser().getRol().getNombre());
                        break;
                    case "especialidades":
                        String especialidades = medico.getEspecialidades().stream()
                                .map(especialidad -> especialidad.getNombre())
                                .reduce((a, b) -> a + ", " + b)
                                .orElse("N/A");
                        cell.setCellValue(especialidades);
                        break;
                    default:
                        cell.setCellValue("N/A");
                        break;
                }
                cell.setCellStyle(dataStyle);
            }
        }

        // Ajustar ancho de columnas automáticamente
        for (int i = 0; i < reporteRequest.getColumnas().size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Convertir el archivo a byte array y cerrar el workbook
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
