package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Especialidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadesRepository extends JpaRepository<Especialidades, Integer>{
    
}
