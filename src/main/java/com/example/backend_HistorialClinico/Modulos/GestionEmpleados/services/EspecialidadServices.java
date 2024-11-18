package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services;


import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Especialidades;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.EspecialidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServices {
    @Autowired
    private EspecialidadesRepository especialidadesRepository;

    // Guardar una especialidad
    public Especialidades guardarEspecialidad(Especialidades especialidad) {
        return especialidadesRepository.save(especialidad);
    }

    // Obtener todas las especialidades
    public List<Especialidades> obtenerTodasLasEspecialidades() {
        return especialidadesRepository.findAll();
    }

    // Eliminar una especialidad por ID
    public void eliminarEspecialidad(int id) {
        especialidadesRepository.deleteById(id);
    }
    
    public Optional<Especialidades> obtenerEspecialidadPorId(int id) {
        return especialidadesRepository.findById(id);
    }
}
