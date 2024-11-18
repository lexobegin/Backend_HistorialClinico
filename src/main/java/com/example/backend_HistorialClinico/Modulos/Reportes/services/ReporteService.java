// ReporteService.java
package com.example.backend_HistorialClinico.Modulos.Reportes.services;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.MedicoRepository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.specifications.MedicoSpecifications;
import com.example.backend_HistorialClinico.Modulos.Reportes.dto.ReporteRequest;
import com.example.backend_HistorialClinico.Modulos.Reportes.utils.ExcelGenerator;
import com.example.backend_HistorialClinico.Modulos.Reportes.utils.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private MedicoRepository medicoRepository;

    public byte[] generarReporteMedicos(ReporteRequest reporteRequest) throws Exception {
        // Obtener los datos aplicando filtros, orden y columnas
        List<Medico> medicos = obtenerDatosMedicos(reporteRequest);

        // Generar el reporte en el formato solicitado
        String formato = reporteRequest.getFormato().toLowerCase();

        if ("excel".equals(formato)) {
            return ExcelGenerator.generarReporteExcelMedicos(medicos, reporteRequest);
        } else if ("pdf".equals(formato)) {
            return PDFGenerator.generarReportePDFMedicos(medicos, reporteRequest);
        } else {
            throw new IllegalArgumentException("Formato no soportado");
        }
    }

    private List<Medico> obtenerDatosMedicos(ReporteRequest reporteRequest) {
    Specification<Medico> specs = MedicoSpecifications.crearEspecificaciones(reporteRequest.getFiltros());

    // Aplicar orden
    Sort sort = Sort.unsorted();
    if (reporteRequest.getOrden() != null) {
        String columna = reporteRequest.getOrden().getColumna();
        String direccion = reporteRequest.getOrden().getDireccion();

        Sort.Direction dir = "asc".equalsIgnoreCase(direccion) ? Sort.Direction.ASC : Sort.Direction.DESC;
        sort = Sort.by(dir, columna);
    }

    return medicoRepository.findAll(specs, sort);
}
}
