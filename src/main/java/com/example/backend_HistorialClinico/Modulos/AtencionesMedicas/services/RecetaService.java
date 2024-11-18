package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;


import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Medicamento;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Receta;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.MedicamentoRepository;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {
    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository; 

    public Receta guardarReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public Optional<Receta> obtenerRecetaPorConsulta(Integer consultaId) {
        return recetaRepository.findByConsultaId(consultaId);
    }


    public Optional<Receta> getRecetaById(Integer id) {
        return recetaRepository.findById(id);
    }

    public void eliminarMedicamento(Medicamento medicamento) {
        medicamentoRepository.delete(medicamento);
    }
}

