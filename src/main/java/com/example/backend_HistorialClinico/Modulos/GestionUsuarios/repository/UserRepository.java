package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByCi(String ci);
}
