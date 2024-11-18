package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services;

import org.springframework.stereotype.Service;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Permisos;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.PermisosRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermisosServices {
    private final PermisosRepository permisosRepository;


    public Permisos crearPermisos(Permisos permisos){
        return permisosRepository.save(permisos);
    }

    // Obtener todos los roles
    public List<Permisos> obtenerTodosLosPermisos() {
        return permisosRepository.findAll();
    }

     // Obtener un rol por ID
     public Optional<Permisos> obtenerPermisoPorId(Integer id) {
        return permisosRepository.findById(id);
    }

}
