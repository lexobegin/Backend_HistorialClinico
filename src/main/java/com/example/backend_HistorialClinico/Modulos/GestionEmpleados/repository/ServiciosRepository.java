package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Servicios;

public interface ServiciosRepository  extends JpaRepository<Servicios, Integer>{
    
}
