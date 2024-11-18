package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Medicamento;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
    
}
