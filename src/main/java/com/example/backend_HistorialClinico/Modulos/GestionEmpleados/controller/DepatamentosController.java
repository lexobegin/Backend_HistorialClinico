package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Departamentos;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services.DepartamentosServices;



@RestController
@RequestMapping("/auth/departamentos")
public class DepatamentosController {
    
    @Autowired
    private DepartamentosServices departamentosService;

    // Crear un nuevo departamento
    @PostMapping("/crear")
    public ResponseEntity<Departamentos> crearDepartamento(@RequestBody Departamentos departamento) {
        Departamentos nuevoDepartamento = departamentosService.crearDepartamento(departamento);
        return ResponseEntity.ok(nuevoDepartamento);
    }

    // Obtener todos los departamentos
    @GetMapping
    public List<Departamentos> obtenerTodosLosDepartamentos() {
        return departamentosService.listarDepartamentos();
    }

     // Obtener un departamento por ID
     @GetMapping("/{id}")
     public ResponseEntity<Departamentos> obtenerDepartamentoPorId(@PathVariable int id) {
         Optional<Departamentos> departamento = departamentosService.obtenerDepartamentoPorId(id);
         return departamento.map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());
     }
     
    // Actualizar un departamento por ID
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Departamentos> actualizarDepartamento(@PathVariable int id, @RequestBody Departamentos departamentoActualizado) {
        Departamentos departamento = departamentosService.actualizarDepartamento(id, departamentoActualizado);
        return (departamento != null) ? ResponseEntity.ok(departamento) : ResponseEntity.notFound().build();

    }

    // Eliminar un departamento por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarDepartamento(@PathVariable int id) {
        boolean eliminado = departamentosService.eliminarDepartamento(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
