package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Horario_medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.MedicoRepository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services.HorarioMedicoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/auth/horarios")
public class HorarioMedicoController {
    @Autowired
    private HorarioMedicoService horarioMedicoService;

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Endpoint para crear un Horario Médico junto con sus Bloques.
     * Método: POST
     * URL: /api/horarios
     *
     * @param horario El objeto Horario_medico con sus bloques.
     * @return El Horario_medico creado o una respuesta de error.
     */
    @PostMapping
    public ResponseEntity<Horario_medico> crearHorarioMedico(@RequestBody Horario_medico horario) {
        try {
            // Validar que el médico exista
            Medico medico = medicoRepository.findById(horario.getMedico().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Médico no encontrado con ID: " + horario.getMedico().getId()));
            horario.setMedico(medico);

            Horario_medico nuevoHorario = horarioMedicoService.crearHorarioMedico(horario);
            return ResponseEntity.ok(nuevoHorario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // Manejo básico de errores
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Endpoint para listar todos los Horarios Médicos.
     * Método: GET
     * URL: /api/horarios
     *
     * @return Lista de Horario_medico o una respuesta de error.
     */
    @GetMapping
    public ResponseEntity<List<Horario_medico>> listarHorarios() {
        try {
            List<Horario_medico> horarios = horarioMedicoService.listarHorarios();
            return ResponseEntity.ok(horarios);
        } catch (Exception e) {
            // Manejo básico de errores
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Endpoint para obtener un Horario Médico por su ID.
     * Método: GET
     * URL: /api/horarios/{id}
     *
     * @param id El ID del Horario_medico.
     * @return El Horario_medico encontrado o una respuesta de no encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Horario_medico> obtenerHorarioPorId(@PathVariable int id) {
        Horario_medico horario = horarioMedicoService.obtenerHorarioPorId(id);
        if (horario != null) {
            return ResponseEntity.ok(horario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para actualizar un Horario Médico existente.
     * Método: PUT
     * URL: /api/horarios/{id}
     *
     * @param id      El ID del Horario_medico a actualizar.
     * @param horario Los nuevos datos del Horario_medico.
     * @return El Horario_medico actualizado o una respuesta de error.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Horario_medico> actualizarHorarioMedico(@PathVariable int id,
            @RequestBody Horario_medico horario) {
        try {
            // Validar que el médico exista
            Medico medico = medicoRepository.findById(horario.getMedico().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Médico no encontrado con ID: " + horario.getMedico().getId()));
            horario.setMedico(medico);

            Horario_medico actualizado = horarioMedicoService.actualizarHorarioMedico(id, horario);
            if (actualizado != null) {
                return ResponseEntity.ok(actualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            // Manejo básico de errores
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Endpoint para eliminar un Horario Médico por su ID.
     * Método: DELETE
     * URL: /api/horarios/{id}
     *
     * @param id El ID del Horario_medico a eliminar.
     * @return Una respuesta indicando el resultado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHorarioMedico(@PathVariable int id) {
        boolean eliminado = horarioMedicoService.eliminarHorarioMedico(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para listar los horarios médicos de un médico específico por día.
     * Método: GET
     * URL: /auth/horarios/medico/{medicoId}
     *
     * @param medicoId El ID del médico.
     * @return Lista de Horario_medico de ese médico organizada por día.
     */

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Horario_medico>> listarHorariosPorMedico(@PathVariable int medicoId) {
        try {
            List<Horario_medico> horarios = horarioMedicoService.listarHorariosPorMedico(medicoId);
            return ResponseEntity.ok(horarios);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Horario_medico>> listarHorariosPorServicioYFecha(
            @RequestParam int servicioId,
            @RequestParam String fecha) {
        try {
            LocalDate localDate = LocalDate.parse(fecha); // Convierte la fecha de String a LocalDate
            List<Horario_medico> horarios = horarioMedicoService.listarHorariosPorServicioYFecha(servicioId, localDate);
            return ResponseEntity.ok(horarios);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }



}
