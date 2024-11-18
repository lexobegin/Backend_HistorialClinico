package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.services;


import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository.MedicoRepository;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoServices {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UserRepository userRepository;

    // Guardar un médico
    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // Encontrar todos los médicos
    public List<Medico> obtenerTodosLosMedicos() {
        return medicoRepository.findAll();
    }

    // Encontrar un médico por ID de usuario
    public Optional<Medico> obtenerMedicoPorUserId(int userId) {
        return medicoRepository.findByUserId(userId);
    }

    // Obtener médico por ID del médico
    public Optional<Medico> obtenerMedicoPorId(int id) {
        return medicoRepository.findById(id);
    }

    // Eliminar un médico por su ID
    public void eliminarMedico(int id) {
        medicoRepository.deleteById(id);
    }

    

    // Crear un médico asociado a un usuario existente
    public Medico crearMedico(int userId, Medico medico) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            medico.setUser(user.get());
            return medicoRepository.save(medico);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
}
