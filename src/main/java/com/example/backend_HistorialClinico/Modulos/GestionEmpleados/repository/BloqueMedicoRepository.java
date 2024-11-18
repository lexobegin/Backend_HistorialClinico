package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.BloqueMedico;

@Repository
public interface BloqueMedicoRepository extends JpaRepository<BloqueMedico, Integer>{

}
