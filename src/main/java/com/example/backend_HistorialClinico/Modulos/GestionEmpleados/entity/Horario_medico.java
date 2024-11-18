// Horario_medico.java
package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "horario_medico")
public class Horario_medico {

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
    private int cupoTotal;

    @Column(nullable = false)
    private int cupoDisponible;

    // Relación ManyToOne con Medico
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    // Relación ManyToOne con Servicios
    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicios servicio;

    // Relación OneToMany con BloqueMedico
    @OneToMany(mappedBy = "horarioMedico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BloqueMedico> bloques = new ArrayList<>();

    // Constructor vacío
    public Horario_medico() {
    }

    // Constructor con todos los atributos (excepto id y bloques)
    public Horario_medico(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int cupoTotal, int cupoDisponible, Medico medico, Servicios servicio) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.medico = medico;
        this.servicio = servicio;
    }

    // Constructor con todos los atributos incluyendo bloques
    public Horario_medico(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int cupoTotal, int cupoDisponible, Medico medico, Servicios servicio, List<BloqueMedico> bloques) {
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cupoTotal = cupoTotal;
        this.cupoDisponible = cupoDisponible;
        this.medico = medico;
        this.servicio = servicio;
        this.bloques = bloques;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCupoTotal() {
        return cupoTotal;
    }

    public void setCupoTotal(int cupoTotal) {
        this.cupoTotal = cupoTotal;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public void setCupoDisponible(int cupoDisponible) {
        this.cupoDisponible = cupoDisponible;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public List<BloqueMedico> getBloques() {
        return bloques;
    }

    public void setBloques(List<BloqueMedico> bloques) {
        this.bloques = bloques;
    }

    // Métodos auxiliares para gestionar la relación bi-direccional

    public void addBloque(BloqueMedico bloque) {
        bloques.add(bloque);
        bloque.setHorarioMedico(this);
    }

    public void removeBloque(BloqueMedico bloque) {
        bloques.remove(bloque);
        bloque.setHorarioMedico(null);
    }
}
