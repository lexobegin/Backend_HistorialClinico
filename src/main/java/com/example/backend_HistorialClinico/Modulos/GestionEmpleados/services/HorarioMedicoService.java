package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.BloqueMedico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Horario_medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.HorarioMedicoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HorarioMedicoService {
    @Autowired
    private HorarioMedicoRepository horarioMedicoRepository;

    /**
     * Crea un Horario_medico junto con sus BloqueMedico asociados.
     * La relación se maneja automáticamente gracias a CascadeType.ALL en la entidad
     * Horario_medico.
     *
     * @param horario El objeto Horario_medico con sus bloques.
     * @return El Horario_medico guardado con sus BloqueMedico.
     */
    @Transactional
    public Horario_medico crearHorarioMedico(Horario_medico horario) {
        // Se asegura que cupoDisponible sea igual a cupoTotal inicialmente
        // horario.setCupoDisponible(horario.getCupoTotal());

        // Asociar cada bloque al horario médico
        List<BloqueMedico> bloques = horario.getBloques();
        if (bloques != null) {
            bloques.forEach(bloque -> bloque.setHorarioMedico(horario));
        }

        // Guardar el horario junto con los bloques en una transacción
        return horarioMedicoRepository.save(horario);
    }

    /**
     * Lista todos los Horario_medico existentes.
     *
     * @return Lista de Horario_medico.
     */
    public List<Horario_medico> listarHorarios() {
        return horarioMedicoRepository.findAll();
    }

    /**
     * Obtiene un Horario_medico por su ID.
     *
     * @param id El ID del Horario_medico.
     * @return El Horario_medico encontrado o null si no existe.
     */
    public Horario_medico obtenerHorarioPorId(int id) {
        return horarioMedicoRepository.findById(id).orElse(null);
    }

    /**
     * Actualiza un Horario_medico existente.
     *
     * @param id      El ID del Horario_medico a actualizar.
     * @param horario Los nuevos datos del Horario_medico.
     * @return El Horario_medico actualizado o null si no existe.
     */
    @Transactional
    public Horario_medico actualizarHorarioMedico(int id, Horario_medico horario) {
        Horario_medico existente = horarioMedicoRepository.findById(id).orElse(null);
        if (existente != null) {
            // Actualizar los campos principales del Horario_medico
            existente.setFecha(horario.getFecha());
            existente.setHoraInicio(horario.getHoraInicio());
            existente.setHoraFin(horario.getHoraFin());
            existente.setCupoTotal(horario.getCupoTotal());
            existente.setCupoDisponible(horario.getCupoDisponible());
            existente.setMedico(horario.getMedico());
            existente.setServicio(horario.getServicio());

            // Actualizar bloques sin borrarlos
            List<BloqueMedico> bloquesExistentes = existente.getBloques();

            // Crear mapa de los bloques existentes para buscarlos por ID
            Map<Integer, BloqueMedico> bloquesMap = bloquesExistentes.stream()
                    .collect(Collectors.toMap(BloqueMedico::getId, bloque -> bloque));

            // Iterar sobre los bloques enviados en el nuevo horario
            for (BloqueMedico bloque : horario.getBloques()) {
                if (bloquesMap.containsKey(bloque.getId())) {
                    // Actualizar bloque existente
                    BloqueMedico bloqueExistente = bloquesMap.get(bloque.getId());
                    bloqueExistente.setHoraInicio(bloque.getHoraInicio());
                    bloqueExistente.setHoraFin(bloque.getHoraFin());
                    bloqueExistente.setDisponibilidad(bloque.isDisponibilidad());
                } else {
                    // Si el bloque no existe en el horario actual, agregarlo como nuevo
                    bloque.setHorarioMedico(existente);
                    bloquesExistentes.add(bloque);
                }
            }

            // Opcional: Si deseas eliminar bloques que no están en el horario actual,
            // descomenta el siguiente bloque de código
            /*
             * List<Integer> bloqueIdsEnviados = horario.getBloques().stream()
             * .map(BloqueMedico::getId)
             * .collect(Collectors.toList());
             * bloquesExistentes.removeIf(bloque ->
             * !bloqueIdsEnviados.contains(bloque.getId()));
             */

            // Guardar y retornar el horario médico actualizado
            return horarioMedicoRepository.save(existente);
        }
        return null;
    }

    // Aqui se actualiza pero los bloque de horarios se creaban como nuevo
    // public Horario_medico actualizarHorarioMedico(int id, Horario_medico horario)
    // {
    // Horario_medico existente = horarioMedicoRepository.findById(id).orElse(null);
    // if (existente != null) {
    // existente.setFecha(horario.getFecha());
    // existente.setHoraInicio(horario.getHoraInicio());
    // existente.setHoraFin(horario.getHoraFin());
    // existente.setCupoTotal(horario.getCupoTotal());
    // existente.setCupoDisponible(horario.getCupoDisponible());
    // existente.setMedico(horario.getMedico());

    // // Manejar bloques
    // existente.getBloques().clear();
    // if (horario.getBloques() != null) {
    // horario.getBloques().forEach(bloque -> {
    // bloque.setHorarioMedico(existente);
    // existente.getBloques().add(bloque);
    // });
    // }

    // return horarioMedicoRepository.save(existente);
    // }
    // return null;
    // }

    /**
     * Elimina un Horario_medico por su ID.
     *
     * @param id El ID del Horario_medico a eliminar.
     * @return true si se eliminó exitosamente, false si no existe.
     */
    @Transactional
    public boolean eliminarHorarioMedico(int id) {
        if (horarioMedicoRepository.existsById(id)) {
            horarioMedicoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Lista los horarios de un médico específico, agrupados por día.
     *
     * @param medicoId El ID del médico.
     * @return Lista de Horario_medico de ese médico.
     */
    public List<Horario_medico> listarHorariosPorMedico(int medicoId) {
        return horarioMedicoRepository.findByMedicoIdOrderByFechaAsc(medicoId);
    }

    public List<Horario_medico> listarHorariosPorServicioYFecha(int servicioId, LocalDate fecha) {
        return horarioMedicoRepository.findByServicioIdAndFecha(servicioId, fecha);
    }
}
