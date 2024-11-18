package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Servicios;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.ServiciosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosServices {
     private final ServiciosRepository serviciosRepository;

    @Autowired
    public ServiciosServices(ServiciosRepository serviciosRepository) {
        this.serviciosRepository = serviciosRepository;
    }

    public List<Servicios> getAllServicios() {
        return serviciosRepository.findAll();
    }

    public Optional<Servicios> getServicioById(int id) {
        return serviciosRepository.findById(id);
    }

    public Servicios createServicio(Servicios servicio) {
        return serviciosRepository.save(servicio);
    }

    public Servicios updateServicio(int id, Servicios servicioDetails) {
        return serviciosRepository.findById(id)
            .map(servicio -> {
                servicio.setNombre(servicioDetails.getNombre());
                servicio.setPrecio(servicioDetails.getPrecio());
                servicio.setDescripcion(servicioDetails.getDescripcion());
                servicio.setDepartamento(servicioDetails.getDepartamento());
                servicio.setEspecialidad(servicioDetails.getEspecialidad());
                return serviciosRepository.save(servicio);
            })
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id " + id));
    }

    public void deleteServicio(int id) {
        if (serviciosRepository.existsById(id)) {
            serviciosRepository.deleteById(id);
        } else {
            throw new RuntimeException("Servicio no encontrado con id " + id);
        }
    }
}
