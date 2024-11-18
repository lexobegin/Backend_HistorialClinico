// ReporteController.java
package com.example.backend_HistorialClinico.Modulos.Reportes.controller;

import com.example.backend_HistorialClinico.Modulos.Reportes.dto.ReporteRequest;
import com.example.backend_HistorialClinico.Modulos.Reportes.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping("/medicos")
    public ResponseEntity<byte[]> generarReporteMedicos(@RequestBody ReporteRequest reporteRequest) {
        try {
            byte[] reporte = reporteService.generarReporteMedicos(reporteRequest);

            String formato = reporteRequest.getFormato().toLowerCase();
            String contentType;
            String extension;

            if ("excel".equals(formato)) {
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                extension = "xlsx";
            } else if ("pdf".equals(formato)) {
                contentType = "application/pdf";
                extension = "pdf";
            } else {
                return ResponseEntity.badRequest().build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_medicos." + extension);

            return ResponseEntity.ok().headers(headers).body(reporte);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
