package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Bitacora;

public interface BitacoraRepository extends JpaRepository<Bitacora, Integer> {
    // Puedes agregar métodos personalizados si necesitas filtros específicos
}
