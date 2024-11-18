package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Cita;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.CitaRepository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.BloqueMedico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Horario_medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.BloqueMedicoRepository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.HorarioMedicoRepository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.MedicoRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private BloqueMedicoRepository bloqueMedicoRepository;

    @Autowired
    private HorarioMedicoRepository horarioMedicoRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository, UserRepository userRepository,
            MedicoRepository medicoRepository, BloqueMedicoRepository bloqueMedicoRepository) {
        this.citaRepository = citaRepository;
        this.userRepository = userRepository;
        this.medicoRepository = medicoRepository;
        this.bloqueMedicoRepository = bloqueMedicoRepository;
    }

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> getCitaById(int id) {
        return citaRepository.findById(id);
    }

    @Transactional
    public Cita createCita(Cita cita) {
        // Establecer el estado automáticamente como "pendiente"
        cita.setEstado("pendiente");

        // Cargar las entidades completas desde la base de datos
        User user = userRepository.findById(cita.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + cita.getUser().getId()));

        Medico medico = medicoRepository.findById(cita.getMedico().getId())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado con id " + cita.getMedico().getId()));

        BloqueMedico bloqueMedico = bloqueMedicoRepository.findById(cita.getBloqueMedico().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Bloque Médico no encontrado con id " + cita.getBloqueMedico().getId()));

        // Obtener el horario asociado al bloque y verificar disponibilidad
        Horario_medico horarioMedico = bloqueMedico.getHorarioMedico();
        if (horarioMedico.getCupoDisponible() <= 0) {
            throw new RuntimeException("No hay cupos disponibles para este horario.");
        }

        // Reducir el cupo disponible
        horarioMedico.setCupoDisponible(horarioMedico.getCupoDisponible() - 1);
        horarioMedicoRepository.save(horarioMedico); // Guardar el cambio en la base de datos

        // Cambiar la disponibilidad del bloque
        bloqueMedico.setDisponibilidad(false);
        bloqueMedicoRepository.save(bloqueMedico); // Guardar el cambio en la base de datos

        // Asignar las entidades cargadas a la cita
        cita.setUser(user);
        cita.setMedico(medico);
        cita.setBloqueMedico(bloqueMedico);

        // Guardar la cita
        return citaRepository.save(cita);
    }

    public Cita updateCita(int id, Cita citaDetails) {
        return citaRepository.findById(id)
                .map(cita -> {
                    cita.setFecha(citaDetails.getFecha());
                    cita.setHoraInicio(citaDetails.getHoraInicio());
                    cita.setHoraFin(citaDetails.getHoraFin());
                    cita.setEstado(citaDetails.getEstado());
                    cita.setPrecio(citaDetails.getPrecio());
                    cita.setUser(citaDetails.getUser());
                    cita.setMedico(citaDetails.getMedico());
                    cita.setBloqueMedico(citaDetails.getBloqueMedico());
                    return citaRepository.save(cita);
                })
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id " + id));
    }

    public void deleteCita(int id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cita no encontrada con id " + id);
        }
    }

    @Transactional
    public Cita cancelarCita(int id) {
        return citaRepository.findById(id)
                .map(cita -> {
                    // Verificar si la cita ya está cancelada
                    if (!"cancelada".equals(cita.getEstado())) {
                        // Cambiar el estado de la cita a "cancelada"
                        cita.setEstado("cancelada");
    
                        // Obtener el bloque médico y horario asociado
                        BloqueMedico bloqueMedico = cita.getBloqueMedico();
                        Horario_medico horarioMedico = bloqueMedico.getHorarioMedico();
    
                        // Liberar el cupo en el horario
                        horarioMedico.setCupoDisponible(horarioMedico.getCupoDisponible() + 1);
                        horarioMedicoRepository.save(horarioMedico); // Guardar los cambios en la base de datos
    
                        // Cambiar la disponibilidad del bloque si es necesario
                        bloqueMedico.setDisponibilidad(true);
                        bloqueMedicoRepository.save(bloqueMedico); // Guardar el cambio en la base de datos
                    }
                    return citaRepository.save(cita);
                })
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id " + id));
    }
    
    public List<Cita> getCitasByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));
        return citaRepository.findByUser(user);
    }

    public List<Cita> getCitasByMedicoUserId(int userId) {
        return citaRepository.findByMedico_User_Id(userId);
    }
}
