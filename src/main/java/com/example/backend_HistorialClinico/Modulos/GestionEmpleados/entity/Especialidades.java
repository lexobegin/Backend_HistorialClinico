package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "especialidades")
public class Especialidades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Relación Muchos a Muchos con Medico
    @ManyToMany(mappedBy = "especialidades")
    @JsonIgnore// Ignora la serialización de los roles para evitar la recursión infinita
    private Set<Medico> medicos = new HashSet<>();

    // Constructor vacío
    public Especialidades() {
    }

    // Constructor con todos los atributos
    public Especialidades(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
    }
}
