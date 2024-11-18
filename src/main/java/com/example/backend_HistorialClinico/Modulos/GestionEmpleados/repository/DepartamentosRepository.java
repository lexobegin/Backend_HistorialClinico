package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Departamentos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentosRepository extends JpaRepository<Departamentos, Integer>{
    
}
