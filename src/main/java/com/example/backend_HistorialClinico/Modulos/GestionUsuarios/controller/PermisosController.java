package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.controller;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Permisos;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.PermisosServices;

@RestController
@RequestMapping("/auth/permisos")
@RequiredArgsConstructor
// @CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173"})
public class PermisosController {
    
    private final PermisosServices permisosServices;


    @PostMapping
    public ResponseEntity<Permisos> crearPermisos(@RequestBody Permisos permisos){

        Permisos nuevPermisos= permisosServices.crearPermisos(permisos);
        return ResponseEntity.ok(nuevPermisos);
    }



     // Endpoint para obtener todos los roles
    @GetMapping
    public ResponseEntity<List<Permisos>> obtenerTodosLosPermisos() {
        List<Permisos> permisos = permisosServices.obtenerTodosLosPermisos();
        return ResponseEntity.ok(permisos);
    }


    // Endpoint para obtener un rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Permisos> obtenerPermisosPorId(@PathVariable Integer id) {
        Optional<Permisos> permisos = permisosServices.obtenerPermisoPorId(id);
        if (permisos.isPresent()) {
            return ResponseEntity.ok(permisos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
