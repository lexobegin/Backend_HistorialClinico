package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Diagnostico;


@Repository
public interface DiagnositicoRepository extends JpaRepository<Diagnostico, Integer>{
    Diagnostico findByConsultaId(int consultaId);
    
}
