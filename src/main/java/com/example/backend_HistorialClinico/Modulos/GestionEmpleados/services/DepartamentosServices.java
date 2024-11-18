package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Departamentos;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.DepartamentosRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentosServices {

    private final DepartamentosRepository departamentosRepository;

     @Autowired
    public DepartamentosServices(DepartamentosRepository departamentosRepository) {
        this.departamentosRepository = departamentosRepository;
    }

    public List<Departamentos> listarDepartamentos() {
        return departamentosRepository.findAll();
    }

    public Optional<Departamentos> obtenerDepartamentoPorId(int id) {
        return departamentosRepository.findById(id);
    }

    public Departamentos crearDepartamento(Departamentos departamento) {
        return departamentosRepository.save(departamento);
    }

    public Departamentos actualizarDepartamento(int id, Departamentos departamentoActualizado) {
        return departamentosRepository.findById(id).map(departamento -> {
            departamento.setNombre(departamentoActualizado.getNombre());
            departamento.setDireccion(departamentoActualizado.getDireccion());
            return departamentosRepository.save(departamento);
        }).orElse(null);
    }

    public boolean eliminarDepartamento(int id) {
        if (departamentosRepository.existsById(id)) {
            departamentosRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
