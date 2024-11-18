package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Antecedente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedenteRepository extends JpaRepository<Antecedente, Long> {
    List<Antecedente> findByUserId(int userId);
}