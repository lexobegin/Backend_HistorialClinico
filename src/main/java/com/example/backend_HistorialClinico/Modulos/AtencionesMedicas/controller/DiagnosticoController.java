package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;


import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Diagnostico;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.ConsultaService;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.DiagnosticoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/diagnosticos")
public class DiagnosticoController {
    
    @Autowired
    private DiagnosticoService diagnosticoService;
    
    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/guardar")
    public ResponseEntity<Diagnostico> guardarDiagnostico(@RequestBody Map<String, Object> requestBody) {
        // Extraer datos del cuerpo de la solicitud
        String tipoDiagnostico = (String) requestBody.get("tipoDiagnostico");
        String sintomas = (String) requestBody.get("sintomas");
        String observaciones = (String) requestBody.get("observaciones");
        Integer consultaId = (Integer) requestBody.get("consultaId");

        // Verificar que consultaId no sea nulo
        if (consultaId == null) {
            return ResponseEntity.badRequest().build();
        }

        // Obtener la consulta asociada
        Optional<Consulta> consultaOptional = consultaService.getConsultaById(consultaId);
        if (!consultaOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Consulta consulta = consultaOptional.get();

        // Crear instancia de Diagnostico y establecer sus campos
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setTipoDiagnostico(tipoDiagnostico);
        diagnostico.setSintomas(sintomas);
        diagnostico.setObservaciones(observaciones);
        diagnostico.setConsulta(consulta); // Establecer la relación con Consulta

        // Guardar el diagnóstico
        Diagnostico savedDiagnostico = diagnosticoService.guardarDiagnostico(diagnostico);
        return ResponseEntity.ok(savedDiagnostico);
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Diagnostico> obtenerDiagnosticoPorConsulta(@PathVariable int consultaId) {
        Optional<Diagnostico> diagnostico = diagnosticoService.obtenerDiagnosticoPorConsulta(consultaId);
        return diagnostico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Diagnostico> actualizarDiagnostico(@PathVariable int id, @RequestBody Diagnostico diagnostico) {
        Optional<Diagnostico> diagnosticoExistente = diagnosticoService.obtenerDiagnosticoPorId(id);
        if (diagnosticoExistente.isPresent()) {
            Diagnostico updatedDiagnostico = diagnosticoExistente.get();
            updatedDiagnostico.setTipoDiagnostico(diagnostico.getTipoDiagnostico());
            updatedDiagnostico.setSintomas(diagnostico.getSintomas());
            updatedDiagnostico.setObservaciones(diagnostico.getObservaciones());
            return ResponseEntity.ok(diagnosticoService.actualizarDiagnostico(updatedDiagnostico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
