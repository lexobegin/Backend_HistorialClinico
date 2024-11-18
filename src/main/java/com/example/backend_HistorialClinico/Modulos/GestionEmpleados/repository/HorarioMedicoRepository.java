package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Horario_medico;

@Repository
public interface HorarioMedicoRepository extends JpaRepository<Horario_medico, Integer>{
    // Método para obtener horarios de un médico específico ordenados por fecha
    List<Horario_medico> findByMedicoIdOrderByFechaAsc(int medicoId);
    
    List<Horario_medico> findByServicioIdAndFecha(int servicioId, LocalDate fecha);
}
