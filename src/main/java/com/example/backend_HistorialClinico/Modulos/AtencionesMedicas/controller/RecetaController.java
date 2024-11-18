package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.controller;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Consulta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Medicamento;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Receta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.ConsultaService;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/recetas")
public class RecetaController {
    @Autowired
    private RecetaService recetaService;

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/guardar")
    public ResponseEntity<Receta> guardarReceta(@RequestBody Map<String, Object> requestBody) {
        try {
            Integer consultaId = Integer.valueOf(requestBody.get("consultaId").toString());
            Optional<Consulta> consultaOptional = consultaService.getConsultaById(consultaId);

            if (!consultaOptional.isPresent()) {
                return ResponseEntity.badRequest().build();
            }

            Consulta consulta = consultaOptional.get();
            Receta receta = new Receta();
            receta.setConsulta(consulta);
            receta.setFecha(LocalDateTime.now());

            // Procesar lista de medicamentos
            List<Map<String, String>> medicamentosData = (List<Map<String, String>>) requestBody.get("medicamentos");
            List<Medicamento> listaMedicamentos = new ArrayList<>();

            for (Map<String, String> m : medicamentosData) {
                Medicamento medicamento = new Medicamento();
                medicamento.setNombre(m.get("nombre"));
                medicamento.setDosis(m.get("dosis"));
                medicamento.setInstrucciones(m.get("instrucciones"));
                medicamento.setReceta(receta);
                listaMedicamentos.add(medicamento);
            }

            receta.setMedicamentos(listaMedicamentos);

            return ResponseEntity.ok(recetaService.guardarReceta(receta));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<Receta> obtenerRecetaPorConsulta(@PathVariable Integer consultaId) {
        Optional<Receta> recetaOptional = recetaService.obtenerRecetaPorConsulta(consultaId);

        if (recetaOptional.isPresent()) {
            return ResponseEntity.ok(recetaOptional.get()); // Si existe la receta, la devuelve.
        } else {
            return ResponseEntity.ok(null); // Si no existe, devuelve un cuerpo vacío con 200 OK.
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Receta> actualizarReceta(@PathVariable Integer id,
            @RequestBody Map<String, Object> requestBody) {
        try {
            Optional<Receta> recetaOptional = recetaService.getRecetaById(id);
            if (!recetaOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Receta receta = recetaOptional.get();

            // Actualizar la consulta
            Integer consultaId = Integer.valueOf(requestBody.get("consultaId").toString());
            Optional<Consulta> consultaOptional = consultaService.getConsultaById(consultaId);
            if (!consultaOptional.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            receta.setConsulta(consultaOptional.get());
            receta.setFecha(LocalDateTime.now());

            // Procesar la lista de medicamentos
            List<Map<String, Object>> medicamentosData = (List<Map<String, Object>>) requestBody.get("medicamentos");
            List<Medicamento> nuevosMedicamentos = new ArrayList<>();

            for (Map<String, Object> m : medicamentosData) {
                Medicamento medicamento;

                if (m.containsKey("id")) {
                    Integer medicamentoId = Integer.valueOf(m.get("id").toString());
                    Optional<Medicamento> medicamentoExistente = receta.getMedicamentos()
                            .stream()
                            .filter(med -> med.getId().equals(medicamentoId))
                            .findFirst();
                    if (medicamentoExistente.isPresent()) {
                        medicamento = medicamentoExistente.get();
                    } else {
                        medicamento = new Medicamento();
                        medicamento.setReceta(receta); // Asocia el medicamento a la receta
                    }
                } else {
                    medicamento = new Medicamento();
                    medicamento.setReceta(receta); // Asocia el medicamento a la receta
                }

                medicamento.setNombre(m.get("nombre").toString());
                medicamento.setDosis(m.get("dosis").toString());
                medicamento.setInstrucciones(m.get("instrucciones").toString());

                nuevosMedicamentos.add(medicamento);
            }

            // Actualizar la lista de medicamentos en la receta
            receta.getMedicamentos().clear(); // Esto marcará los medicamentos antiguos como "huérfanos"
            receta.getMedicamentos().addAll(nuevosMedicamentos);

            // Guardar la receta actualizada
            Receta recetaActualizada = recetaService.guardarReceta(receta);

            return ResponseEntity.ok(recetaActualizada);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}