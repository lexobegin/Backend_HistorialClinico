package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    /**
     * Obtener todas las consultas.
     */
    @GetMapping
    public ResponseEntity<List<Consulta>> getAllConsultas() {
        return ResponseEntity.ok(consultaService.getAllConsultas());
    }

    /**
     * Obtener una consulta por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable int id) {
        return consultaService.getConsultaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear una nueva consulta con solo citaId, userId y horaInicio.
     */
    // @PostMapping("/create")
    // public ResponseEntity<Consulta> createConsulta(
    // @RequestParam int citaId,
    // @RequestParam int userId,
    // @RequestParam String horaInicio) {
    // LocalTime horaInicioParsed = LocalTime.parse(horaInicio); // Parseo de hora
    // de inicio
    // Consulta consulta = consultaService.createConsulta(citaId, userId,
    // horaInicioParsed);
    // return ResponseEntity.ok(consulta);
    // }

    @PostMapping("/create")
    public ResponseEntity<Consulta> createConsulta(@RequestBody Map<String, String> requestData) {
        int citaId = Integer.parseInt(requestData.get("citaId"));
        int userId = Integer.parseInt(requestData.get("userId"));
        LocalTime horaInicio = LocalTime.parse(requestData.get("horaInicio"));

        Consulta consulta = consultaService.createConsulta(citaId, userId, horaInicio);
        return ResponseEntity.ok(consulta);
    }

    /**
     * Finalizar una consulta agregando los datos faltantes.
     */
    @PutMapping("/finalizar/{consultaId}")
    public ResponseEntity<Consulta> finalizarConsulta(@PathVariable int consultaId,@RequestBody Map<String, String> requestData) {

        // Extraer los datos desde el JSON del cuerpo de la solicitud
        String motivoConsulta = requestData.get("motivoConsulta");
        LocalTime horaFin = LocalTime.parse(requestData.get("horaFin"));

        // Llamar al servicio con los parámetros necesarios
        Consulta consulta = consultaService.finalizarConsulta(consultaId, motivoConsulta, horaFin);
        return ResponseEntity.ok(consulta);
    }

    /**
     * Eliminar una consulta por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable int id) {
        consultaService.deleteConsulta(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtener todas las consultas de un usuario específico por su userId.
     */
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Consulta>> getConsultasByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(consultaService.getConsultasByUserId(userId));
    }
}
