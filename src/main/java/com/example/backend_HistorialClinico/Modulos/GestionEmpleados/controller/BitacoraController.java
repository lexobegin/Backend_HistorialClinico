package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Bitacora;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services.BitacoraService;

import java.util.List;

@RestController
@RequestMapping("/auth/bitacora")
public class BitacoraController {

    @Autowired
    private BitacoraService bitacoraService;

    @GetMapping
    public List<Bitacora> obtenerBitacora() {
        return bitacoraService.obtenerTodosLosRegistros();
    }

    @PostMapping("/registrar")
    public Bitacora registrarAccion(@RequestBody Bitacora bitacora) {
        return bitacoraService.registrarAccion(bitacora);
    }
}
