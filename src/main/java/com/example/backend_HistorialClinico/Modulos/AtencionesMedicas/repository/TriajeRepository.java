package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Triaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TriajeRepository extends JpaRepository<Triaje, Integer> {
}
