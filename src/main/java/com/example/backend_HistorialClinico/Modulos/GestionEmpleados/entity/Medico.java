package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity;
import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "medico")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate fechaContratacion;

    @Column(nullable = false)
    private String estado;

    // Relación Uno a Uno con User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Llave foránea hacia la tabla User
    private User user;

    // Relación Muchos a Muchos con Especialidades
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "medico_especialidades", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "medico_id"), // Llave foránea hacia Medico
            inverseJoinColumns = @JoinColumn(name = "especialidad_id") // Llave foránea hacia Especialidades
    )
    private Set<Especialidades> especialidades = new HashSet<>();

    // Constructor vacío
    public Medico() {
    }

    // Constructor con todos los atributos
    public Medico(LocalDate fechaContratacion, String estado, User user, Set<Especialidades> especialidades) {
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
        this.user = user;
        this.especialidades = especialidades;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Especialidades> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }
}
