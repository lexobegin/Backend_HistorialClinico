package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Bitacora;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.BitacoraRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BitacoraService {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    public List<Bitacora> obtenerTodosLosRegistros() {
        return bitacoraRepository.findAll();
    }

    public Bitacora registrarAccion(Bitacora bitacora) {
        bitacora.setFecha(LocalDate.now());
        bitacora.setHora(LocalTime.now());
        return bitacoraRepository.save(bitacora);
    }
}