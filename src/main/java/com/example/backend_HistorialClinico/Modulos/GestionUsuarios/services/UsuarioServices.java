package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServices {
    private final UserRepository userRepository;

    public List<User> obtenerTodosLosUsuario() {
        return userRepository.findAll();
    }

    // Obtener un rol por ID
    public Optional<User> obtenerUsuarioPorId(Integer id) {
        return userRepository.findById(id);
    }

    // Guardar usuario actualizado
    public User guardarUsuario(User user) {
        return userRepository.save(user);
    }

    public User actualizarUsuario(Integer id, User userActualizado) {
        Optional<User> userOpt = userRepository.findById(id);
    
        if (userOpt.isPresent()) {
            User user = userOpt.get();
    
            // Actualizar solo los campos permitidos
            user.setNombre(userActualizado.getNombre());
            user.setApellidoPaterno(userActualizado.getApellidoPaterno());
            user.setApellidoMaterno(userActualizado.getApellidoMaterno());
            user.setFechaNacimiento(userActualizado.getFechaNacimiento());
            user.setTelefono(userActualizado.getTelefono());
            user.setGenero(userActualizado.getGenero());
            user.setCi(userActualizado.getCi());
            user.setUsername(userActualizado.getUsername());
    
            return userRepository.save(user); // Guardar el usuario actualizado
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
    
}
