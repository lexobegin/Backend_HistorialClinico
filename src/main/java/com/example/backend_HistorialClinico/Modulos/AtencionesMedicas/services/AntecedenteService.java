package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.services;

import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity.Antecedente;
import com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.repository.AntecedenteRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntecedenteService {

    private final AntecedenteRepository antecedenteRepository;
    private final UserRepository userRepository;

    @Autowired
    public AntecedenteService(AntecedenteRepository antecedenteRepository, UserRepository userRepository) {
        this.antecedenteRepository = antecedenteRepository;
        this.userRepository = userRepository;
    }

    public List<Antecedente> getAllAntecedentes() {
        return antecedenteRepository.findAll();
    }

    public Antecedente getAntecedenteById(Long id) {
        return antecedenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Antecedente no encontrado con id " + id));
    }

    public Antecedente createAntecedente(Antecedente antecedente) {
        Integer userId = antecedente.getUser().getId();
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));
        return antecedenteRepository.save(antecedente);
    }

    public Antecedente updateAntecedente(Long id, Antecedente antecedenteDetails) {
        return antecedenteRepository.findById(id)
                .map(antecedente -> {
                    antecedente.setFecha(antecedenteDetails.getFecha());
                    antecedente.setEnfermedadesCronicas(antecedenteDetails.getEnfermedadesCronicas());
                    antecedente.setEnfermedadesAgudas(antecedenteDetails.getEnfermedadesAgudas());
                    antecedente.setIntervencionesQuirurgicas(antecedenteDetails.getIntervencionesQuirurgicas());
                    antecedente.setAlergias(antecedenteDetails.getAlergias());
                    antecedente.setHospitalizacionesPrevias(antecedenteDetails.getHospitalizacionesPrevias());
                    antecedente.setMedicamentosUsoLargoPlazo(antecedenteDetails.getMedicamentosUsoLargoPlazo());
                    antecedente.setEnfermedadesInfecciosas(antecedenteDetails.getEnfermedadesInfecciosas());
                    antecedente.setVacunas(antecedenteDetails.getVacunas());
                    antecedente.setEnfermedadesHereditarias(antecedenteDetails.getEnfermedadesHereditarias());
                    antecedente.setSuplementos(antecedenteDetails.getSuplementos());
                    antecedente.setRestriccionesAlimenticias(antecedenteDetails.getRestriccionesAlimenticias());
                    return antecedenteRepository.save(antecedente);
                })
                .orElseThrow(() -> new RuntimeException("Antecedente no encontrado con id " + id));
    }

    public void deleteAntecedente(Long id) {
        if (antecedenteRepository.existsById(id)) {
            antecedenteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Antecedente no encontrado con id " + id);
        }
    }

    public List<Antecedente> getAntecedentesByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + userId));
        return antecedenteRepository.findByUserId(userId);
    }
}