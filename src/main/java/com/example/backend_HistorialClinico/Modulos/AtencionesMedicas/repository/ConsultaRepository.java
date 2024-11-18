package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    List<Consulta> findByUser(User user);  // Consultas de un paciente
    List<Consulta> findByCita_Id(int citaId);  // Consulta específica de una cita
}
