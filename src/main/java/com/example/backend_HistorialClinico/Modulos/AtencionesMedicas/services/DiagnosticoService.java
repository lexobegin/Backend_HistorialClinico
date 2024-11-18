package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;


import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Diagnostico;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.DiagnositicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnositicoRepository diagnosticoRepository;

    public Diagnostico guardarDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    public Optional<Diagnostico> obtenerDiagnosticoPorConsulta(int consultaId) {
        return Optional.ofNullable(diagnosticoRepository.findByConsultaId(consultaId));
    }

    public Diagnostico actualizarDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    public Optional<Diagnostico> obtenerDiagnosticoPorId(int id) {
        return diagnosticoRepository.findById(id);
    }
    
}
