package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.Medico;
import com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity.BloqueMedico;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private double precio;

    // Relación ManyToOne con User
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relación ManyToOne con Medico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Medico medico;

    // Relación ManyToOne con BloqueMedico
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "bloque_medico_id", nullable = false)
    private BloqueMedico bloqueMedico;

    // Constructor vacío
    public Cita() {
    }

    // Constructor con todos los atributos
    public Cita(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado, double precio, User user, Medico medico, BloqueMedico bloqueMedico) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.precio = precio;
        this.user = user;
        this.medico = medico;
        this.bloqueMedico = bloqueMedico;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public BloqueMedico getBloqueMedico() {
        return bloqueMedico;
    }

    public void setBloqueMedico(BloqueMedico bloqueMedico) {
        this.bloqueMedico = bloqueMedico;
    }
}
