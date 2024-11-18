package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services;

import org.springframework.stereotype.Service;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Roles;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.RolesRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesServices {

    private final RolesRepository rolesRepository;

    // Crear un nuevo rol
    public Roles crearRoles(Roles roles) {
        return rolesRepository.save(roles);
    }

    // Obtener todos los roles
    public List<Roles> obtenerTodosLosRoles() {
        return rolesRepository.findAll();
    }

    // Obtener un rol por ID
    public Optional<Roles> obtenerRolPorId(Integer id) {
        return rolesRepository.findById(id);
    }

    // Actualizar permisos de un rol por ID 
    public Optional<Roles> actualizarRol(Integer id, Roles rolesDetalles) {
        return rolesRepository.findById(id).map(rolExistente -> {
            rolExistente.setNombre(rolesDetalles.getNombre());
            rolExistente.setPermisos(rolesDetalles.getPermisos());
            return rolesRepository.save(rolExistente);
        });
    }


    // Actualizar el nombre de un rol por ID 
    public Optional<Roles> actualizarRolNombre(Integer id, Roles rolesDetalles) {
        return rolesRepository.findById(id).map(rolExistente -> {
            rolExistente.setNombre(rolesDetalles.getNombre());
            return rolesRepository.save(rolExistente);
        });
    }

    // Eliminar un rol por ID
    public boolean eliminarRol(Integer id) {
        if (rolesRepository.existsById(id)) {
            rolesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

