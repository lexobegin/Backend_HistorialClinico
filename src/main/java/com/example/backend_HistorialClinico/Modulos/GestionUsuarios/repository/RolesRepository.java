package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.Roles;


@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByNombre(String nombre);
}
