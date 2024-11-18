package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.controller;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Especialidades;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services.EspecialidadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/especialidades")
public class EspecialidadController {
    @Autowired
    private EspecialidadServices especialidadesService;

    // Crear una nueva especialidad
    @PostMapping("/crear")
    public ResponseEntity<Especialidades> crearEspecialidad(@RequestBody Especialidades especialidad) {
        Especialidades nuevaEspecialidad = especialidadesService.guardarEspecialidad(especialidad);
        return ResponseEntity.ok(nuevaEspecialidad);
    }

    // Obtener todas las especialidades
    @GetMapping
    public List<Especialidades> obtenerTodasLasEspecialidades() {
        return especialidadesService.obtenerTodasLasEspecialidades();
    }

    // Eliminar una especialidad por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEspecialidad(@PathVariable int id) {
        especialidadesService.eliminarEspecialidad(id);
        return ResponseEntity.noContent().build();
    }
}
