// BloqueMedico.java
package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "bloque_medico")
public class BloqueMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private boolean disponibilidad;

    // Relación ManyToOne con Horario_medico
    @ManyToOne
    @JoinColumn(name = "horario_medico_id", nullable = false)
    @JsonBackReference
    private Horario_medico horarioMedico;

    // Constructor vacío
    public BloqueMedico() {
    }

    // Constructor con todos los atributos (excepto id)
    public BloqueMedico(LocalTime horaInicio, LocalTime horaFin, boolean disponibilidad, Horario_medico horarioMedico) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponibilidad = disponibilidad;
        this.horarioMedico = horarioMedico;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
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

    public boolean isDisponibilidad() {
       return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
       this.disponibilidad = disponibilidad;
    }

    public Horario_medico getHorarioMedico() {
       return horarioMedico;
    }

    public void setHorarioMedico(Horario_medico horarioMedico) {
       this.horarioMedico = horarioMedico;
    }
}
