package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Cita;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.CitaRepository;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.ConsultaRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> getConsultaById(int id) {
        return consultaRepository.findById(id);
    }

    @Transactional
    public Consulta createConsulta(int citaId, int userId, LocalTime horaInicio) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id " + citaId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));

        if (!"Listo para Consulta".equals(cita.getEstado())) {
            throw new RuntimeException("La cita no está lista para consulta");
        }

        Consulta consulta = new Consulta();
        consulta.setCita(cita);
        consulta.setUser(user);
        consulta.setFechaConsulta(LocalDateTime.now());
        consulta.setHoraInicio(horaInicio);
        consulta.setMotivoConsulta(""); // Vacío hasta que se finalice

        cita.setEstado("En Consulta");
        citaRepository.save(cita); // Actualizamos el estado de la cita
        return consultaRepository.save(consulta); // Guardamos la nueva consulta
    }

    // @Transactional
    // public Consulta finalizarConsulta(int consultaId, String motivoConsulta,
    // LocalTime horaFin) {

    // return consultaRepository.findById(consultaId)
    // .map(consulta -> {
    // consulta.setMotivoConsulta(motivoConsulta);
    // consulta.setHoraFin(horaFin);
    // return consultaRepository.save(consulta);
    // })
    // .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id " +
    // consultaId));
    // }

    @Transactional
    public Consulta finalizarConsulta(int consultaId, String motivoConsulta, LocalTime horaFin) {
        return consultaRepository.findById(consultaId)
                .map(consulta -> {
                    consulta.setMotivoConsulta(motivoConsulta);
                    consulta.setHoraFin(horaFin);

                    // Cambiar el estado de la cita a "finalizado"
                    Cita cita = consulta.getCita();
                    if (cita != null) {
                        cita.setEstado("finalizado");
                        citaRepository.save(cita); // Asegúrate de que el repositorio de citas esté inyectado
                    }

                    return consultaRepository.save(consulta);
                })
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id " + consultaId));
    }

    @Transactional
    public Consulta updateConsulta(int id, Consulta consultaDetails) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consulta.setMotivoConsulta(consultaDetails.getMotivoConsulta());
                    consulta.setFechaConsulta(consultaDetails.getFechaConsulta());
                    consulta.setHoraInicio(consultaDetails.getHoraInicio());
                    consulta.setHoraFin(consultaDetails.getHoraFin());
                    return consultaRepository.save(consulta);
                })
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id " + id));
    }

    public void deleteConsulta(int id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consulta no encontrada con id " + id);
        }
    }

    public List<Consulta> getConsultasByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));
        return consultaRepository.findByUser(user);
    }
}
