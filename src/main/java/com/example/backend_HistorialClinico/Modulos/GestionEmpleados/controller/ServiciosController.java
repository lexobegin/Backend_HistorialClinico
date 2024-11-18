package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Servicios;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services.ServiciosServices;
import java.util.List;

@RestController
@RequestMapping("/auth/servicios")
public class ServiciosController {
    private final ServiciosServices serviciosService;

    @Autowired
    public ServiciosController(ServiciosServices serviciosService) {
        this.serviciosService = serviciosService;
    }

    @GetMapping
    public List<Servicios> getAllServicios() {
        return serviciosService.getAllServicios();
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Servicios> getServicioById(@PathVariable int id) {
        return serviciosService.getServicioById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public Servicios createServicio(@RequestBody Servicios servicio) {
        return serviciosService.createServicio(servicio);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Servicios> updateServicio(@PathVariable int id, @RequestBody Servicios servicioDetails) {
        try {
            Servicios updatedServicio = serviciosService.updateServicio(id, servicioDetails);
            return ResponseEntity.ok(updatedServicio);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable int id) {
        try {
            serviciosService.deleteServicio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
