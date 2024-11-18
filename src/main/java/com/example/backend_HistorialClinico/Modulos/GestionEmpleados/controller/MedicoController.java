package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.controller;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services.EspecialidadServices;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services.MedicoServices;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Roles;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.RolesServices;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services.UsuarioServices;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Especialidades;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth/medicos")
public class MedicoController {
    @Autowired
    private MedicoServices medicoService;
    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private RolesServices rolesServices;
    @Autowired
    private EspecialidadServices especialidadService;

    // Crear un nuevo médico asociado a un usuario
    @PostMapping("/crear/{userId}")
    public ResponseEntity<Medico> crearMedico(@PathVariable int userId, @RequestBody Medico medicoDetalles,
            @RequestParam int rolId) {
        // Crear el médico utilizando el servicio correspondiente
        Medico nuevoMedico = medicoService.crearMedico(userId, medicoDetalles);

        // Obtener el usuario creado
        User usuarioExistente = nuevoMedico.getUser();

        // Cambiar el rol del usuario utilizando el rolId proporcionado
        Optional<Roles> nuevoRolOpt = rolesServices.obtenerRolPorId(rolId);
        if (nuevoRolOpt.isPresent()) {
            usuarioExistente.setRol(nuevoRolOpt.get());
            usuarioServices.guardarUsuario(usuarioExistente); // Guardar el cambio de rol
        } else {
            return ResponseEntity.badRequest().body(null); // Devolver error si no se encuentra el rol
        }

        // Devolver la respuesta con el médico creado y actualizado
        return ResponseEntity.ok(nuevoMedico);
    }

    // Obtener todos los médicos
    @GetMapping
    public List<Medico> obtenerTodosLosMedicos() {
        return medicoService.obtenerTodosLosMedicos();
    }

    // Obtener médico por ID de usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<Medico> obtenerMedicoPorUserId(@PathVariable int userId) {
        Optional<Medico> medico = medicoService.obtenerMedicoPorUserId(userId);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener médico por ID del médico
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedicoPorId(@PathVariable int id) {
        Optional<Medico> medico = medicoService.obtenerMedicoPorId(id);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Editar un médico y también los datos del usuario (teléfono, etc.)

    // edita pero sin las especialidades
    // @PutMapping("/editar/{id}")
    // public ResponseEntity<Medico> editarMedico(@PathVariable int id, @RequestBody
    // Medico medicoDetalles,
    // @RequestParam Optional<Integer> rolId) {
    // Optional<Medico> medicoOpt = medicoService.obtenerMedicoPorId(id);

    // if (medicoOpt.isPresent()) {
    // Medico medicoExistente = medicoOpt.get();

    // // Actualizar los datos del médico
    // medicoExistente.setEstado(medicoDetalles.getEstado());
    // medicoExistente.setFechaContratacion(medicoDetalles.getFechaContratacion());

    // // Obtener el usuario asociado y actualizar los datos del usuario (como el
    // // teléfono)
    // User usuarioExistente = medicoExistente.getUser();
    // usuarioExistente.setTelefono(medicoDetalles.getUser().getTelefono());
    // usuarioExistente.setNombre(medicoDetalles.getUser().getNombre());
    // usuarioExistente.setApellidoPaterno(medicoDetalles.getUser().getApellidoPaterno());
    // usuarioExistente.setApellidoMaterno(medicoDetalles.getUser().getApellidoMaterno());
    // usuarioExistente.setFechaNacimiento(medicoDetalles.getUser().getFechaNacimiento());
    // usuarioExistente.setGenero(medicoDetalles.getUser().getGenero());
    // usuarioServices.guardarUsuario(usuarioExistente); // Guardar los cambios en
    // el usuario

    // System.out.println("este es el rol" + rolId);
    // // Si se proporciona rolId, cambiar el rol del usuario
    // if (rolId.isPresent()) {
    // Optional<Roles> nuevoRolOpt = rolesServices.obtenerRolPorId(rolId.get());
    // if (nuevoRolOpt.isPresent()) {
    // usuarioExistente.setRol(nuevoRolOpt.get());
    // usuarioServices.guardarUsuario(usuarioExistente); // Guardar el cambio de rol
    // } else {
    // return ResponseEntity.badRequest().body(null);
    // }
    // }

    // // Guardar el médico actualizado
    // Medico medicoActualizado = medicoService.guardarMedico(medicoExistente);
    // return ResponseEntity.ok(medicoActualizado);
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Medico> editarMedico(@PathVariable int id, @RequestBody Medico medicoDetalles,
            @RequestParam Optional<Integer> rolId) {
        Optional<Medico> medicoOpt = medicoService.obtenerMedicoPorId(id);

        if (medicoOpt.isPresent()) {
            Medico medicoExistente = medicoOpt.get();

            // Actualizar los datos del médico
            medicoExistente.setEstado(medicoDetalles.getEstado());
            medicoExistente.setFechaContratacion(medicoDetalles.getFechaContratacion());

            // Actualizar los datos del usuario
            User usuarioExistente = medicoExistente.getUser();
            usuarioExistente.setTelefono(medicoDetalles.getUser().getTelefono());
            usuarioExistente.setNombre(medicoDetalles.getUser().getNombre());
            usuarioExistente.setApellidoPaterno(medicoDetalles.getUser().getApellidoPaterno());
            usuarioExistente.setApellidoMaterno(medicoDetalles.getUser().getApellidoMaterno());
            usuarioExistente.setFechaNacimiento(medicoDetalles.getUser().getFechaNacimiento());
            usuarioExistente.setGenero(medicoDetalles.getUser().getGenero());
            usuarioExistente.setCi(medicoDetalles.getUser().getCi());
            usuarioServices.guardarUsuario(usuarioExistente); // Guardar los cambios en el usuario

            // Si se proporciona rolId, cambiar el rol del usuario
            if (rolId.isPresent()) {
                Optional<Roles> nuevoRolOpt = rolesServices.obtenerRolPorId(rolId.get());
                if (nuevoRolOpt.isPresent()) {
                    usuarioExistente.setRol(nuevoRolOpt.get());
                    usuarioServices.guardarUsuario(usuarioExistente); // Guardar el cambio de rol
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }

            // Actualizar las especialidades del médico
            if (medicoDetalles.getEspecialidades() != null && !medicoDetalles.getEspecialidades().isEmpty()) {
                Set<Especialidades> especialidadesActualizadas = new HashSet<>();
                for (Especialidades especialidad : medicoDetalles.getEspecialidades()) {
                    Optional<Especialidades> especialidadOpt = especialidadService
                            .obtenerEspecialidadPorId(especialidad.getId());
                    if (especialidadOpt.isPresent()) {
                        especialidadesActualizadas.add(especialidadOpt.get());
                    } else {
                        return ResponseEntity.badRequest().body(null); // Si una especialidad no se encuentra, devolver
                                                                       // error
                    }
                }
                medicoExistente.setEspecialidades(especialidadesActualizadas); // Asignar las especialidades
                                                                               // actualizadas
            }

            // Guardar el médico actualizado
            Medico medicoActualizado = medicoService.guardarMedico(medicoExistente);
            return ResponseEntity.ok(medicoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar médico por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMedico(@PathVariable int id) {
        medicoService.eliminarMedico(id);
        return ResponseEntity.noContent().build();
    }
}
