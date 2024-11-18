package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Roles;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.RolesServices;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/roles")
@RequiredArgsConstructor
// @CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173"})
public class RolesController {

    private final RolesServices rolesServices;

    // Endpoint para crear un nuevo rol
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Roles> crearRol(@RequestBody Roles roles) {
        Roles nuevoRol = rolesServices.crearRoles(roles);
        return ResponseEntity.ok(nuevoRol);
    }

    // Endpoint para obtener todos los roles
    
    @GetMapping
    public ResponseEntity<List<Roles>> obtenerTodosLosRoles() {
        List<Roles> roles = rolesServices.obtenerTodosLosRoles();
        return ResponseEntity.ok(roles);
    }

    // Endpoint para obtener un rol por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Roles> obtenerRolPorId(@PathVariable Integer id) {
        Optional<Roles> rol = rolesServices.obtenerRolPorId(id);
        if (rol.isPresent()) {
            return ResponseEntity.ok(rol.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para actualizar un rol
    //@PreAuthorize("hasAuthority('UPDATE_ROLE')")
    @PutMapping("editar/{id}")
    public ResponseEntity<Roles> actualizarRol(@PathVariable Integer id, @RequestBody Roles rolesDetalles) {
        Optional<Roles> rolActualizado = rolesServices.actualizarRol(id, rolesDetalles);
        if (rolActualizado.isPresent()) {
            return ResponseEntity.ok(rolActualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Roles> actualizarRolNombre(@PathVariable Integer id, @RequestBody Roles rolesDetalles) {
        Optional<Roles> rolActualizado = rolesServices.actualizarRolNombre(id, rolesDetalles);
        if (rolActualizado.isPresent()) {
            return ResponseEntity.ok(rolActualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un rol por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Integer id) {
        boolean eliminado = rolesServices.eliminarRol(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
