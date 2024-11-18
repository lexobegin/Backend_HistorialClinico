package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;


import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Antecedente;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.AntecedenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/antecedente")
public class AntecedenteController {

    private final AntecedenteService antecedenteService;

    @Autowired
    public AntecedenteController(AntecedenteService antecedenteService) {
        this.antecedenteService = antecedenteService;
    }

    @GetMapping
    public List<Antecedente> getAllAntecedentes() {
        return antecedenteService.getAllAntecedentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Antecedente> getAntecedenteById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(antecedenteService.getAntecedenteById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Antecedente> createAntecedente(@RequestBody Antecedente antecedente) {
        return ResponseEntity.ok(antecedenteService.createAntecedente(antecedente));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Antecedente> updateAntecedente(@PathVariable Long id, @RequestBody Antecedente antecedenteDetails) {
        try {
            return ResponseEntity.ok(antecedenteService.updateAntecedente(id, antecedenteDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteAntecedente(@PathVariable Long id) {
        try {
            antecedenteService.deleteAntecedente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{userId}")
    public List<Antecedente> getAntecedentesByUserId(@PathVariable int userId) {
        return antecedenteService.getAntecedentesByUserId(userId);
    }


}