package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación ManyToOne con Cita (Cada consulta se refiere a una cita específica)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_id", nullable = false, unique = true)
    // Cada consulta está ligada a una cita única
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Cita cita;

    // Relación ManyToOne con User (Cada consulta tiene un paciente asociado)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private User user;

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Receta receta;

    @Column(nullable = false)
    private LocalDateTime fechaConsulta;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = true)
    private LocalTime horaFin;

    @Column(nullable = true)
    private String motivoConsulta;

    // Constructor vacío
    public Consulta() {
    }

    // Constructor con todos los atributos
    public Consulta(Cita cita, User user, LocalDateTime fechaConsulta, LocalTime horaInicio, LocalTime horaFin,
            String motivoConsulta) {
        this.cita = cita;
        this.user = user;
        this.fechaConsulta = fechaConsulta;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.motivoConsulta = motivoConsulta;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
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

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    
}
