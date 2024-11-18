package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Cita;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.CitaService;

import java.util.List;

@RestController
@RequestMapping("/auth/citas")
public class CitaController {
    private final CitaService citaService;

    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaService.getAllCitas();
    }

    @GetMapping("/obtener/{id}")  //obtener cita por id de cita
    public ResponseEntity<Cita> getCitaById(@PathVariable int id) {
        return citaService.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/crear")
    public Cita createCita(@RequestBody Cita cita) {
        return citaService.createCita(cita);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable int id, @RequestBody Cita citaDetails) {
        try {
            Cita updatedCita = citaService.updateCita(id, citaDetails);
            return ResponseEntity.ok(updatedCita);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable int id) {
        try {
            citaService.deleteCita(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

  
    // Nuevo endpoint para cancelar una cita
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Cita> cancelarCita(@PathVariable int id) {
        try {
            Cita citaCancelada = citaService.cancelarCita(id);
            return ResponseEntity.ok(citaCancelada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paciente/{userId}")  //obtener todas la citas de un paciente
    public ResponseEntity<List<Cita>> getCitasByUserId(@PathVariable int userId) {
        List<Cita> citas = citaService.getCitasByUserId(userId);
        return ResponseEntity.ok(citas);
    }

    // obtiene las citas por el id de usuario del medico
    @GetMapping("/medico/{userId}")
    public ResponseEntity<List<Cita>> getCitasByMedicoUserId(@PathVariable int userId) {
        List<Cita> citas = citaService.getCitasByMedicoUserId(userId);
        return ResponseEntity.ok(citas);
    }


}
