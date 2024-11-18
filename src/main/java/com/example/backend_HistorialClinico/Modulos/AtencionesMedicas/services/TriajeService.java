package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;


import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Triaje;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.TriajeRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriajeService {

    @Autowired
    private TriajeRepository triajeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Triaje> getAllTriajes() {
        return triajeRepository.findAll();
    }

    public Triaje getTriajeById(int id) {
        return triajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Triaje no encontrado con id " + id));
    }

    public Triaje createTriaje(Triaje triaje) {
        // Verifica que el usuario exista en la base de datos
        int userId = triaje.getUser().getId();
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));

        // Calcula el IMC antes de guardar
        triaje.setImc(triaje.calcularIMC());
        return triajeRepository.save(triaje);
    }

    public Triaje updateTriaje(int id, Triaje triajeDetails) {
        return triajeRepository.findById(id)
                .map(triaje -> {
                    triaje.setFecha(triajeDetails.getFecha());
                    triaje.setTemperatura(triajeDetails.getTemperatura());
                    triaje.setPresionArterial(triajeDetails.getPresionArterial());
                    triaje.setFrecuenciaCardiaca(triajeDetails.getFrecuenciaCardiaca());
                    triaje.setFrecuenciaRespiratoria(triajeDetails.getFrecuenciaRespiratoria());
                    triaje.setSaturacionOxigeno(triajeDetails.getSaturacionOxigeno());
                    triaje.setPeso(triajeDetails.getPeso());
                    triaje.setAltura(triajeDetails.getAltura());
                    triaje.setSintomas(triajeDetails.getSintomas());
                    triaje.setEstadoMental(triajeDetails.getEstadoMental());
                    triaje.setImc(triaje.calcularIMC());
                    return triajeRepository.save(triaje);
                })
                .orElseThrow(() -> new RuntimeException("Triaje no encontrado con id " + id));
    }

    public void deleteTriaje(int id) {
        if (triajeRepository.existsById(id)) {
            triajeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Triaje no encontrado con id " + id);
        }
    }
}