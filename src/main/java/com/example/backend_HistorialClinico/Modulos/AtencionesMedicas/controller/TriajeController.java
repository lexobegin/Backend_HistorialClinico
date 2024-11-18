package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Triaje;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.TriajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/triaje")
public class TriajeController {

    private final TriajeService triajeService;

    @Autowired
    public TriajeController(TriajeService triajeService) {
        this.triajeService = triajeService;
    }

    @GetMapping
    public List<Triaje> getAllTriajes() {
        return triajeService.getAllTriajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Triaje> getTriajeById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(triajeService.getTriajeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Triaje> createTriaje(@RequestBody Triaje triaje) {
        return ResponseEntity.ok(triajeService.createTriaje(triaje));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Triaje> updateTriaje(@PathVariable int id, @RequestBody Triaje triajeDetails) {
        try {
            return ResponseEntity.ok(triajeService.updateTriaje(id, triajeDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteTriaje(@PathVariable int id) {
        try {
            triajeService.deleteTriaje(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
