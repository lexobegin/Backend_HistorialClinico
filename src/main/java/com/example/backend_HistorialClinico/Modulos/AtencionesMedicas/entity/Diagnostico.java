package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "diagnosticos")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tipoDiagnostico;

    @Column(nullable = false)
    private String sintomas;

    @Column
    private String observaciones;

    // Relación ManyToOne con Consulta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Consulta consulta;

    // Constructor vacío
    public Diagnostico() {}

    // Constructor con atributos
    public Diagnostico(String tipoDiagnostico, String sintomas, String observaciones, Consulta consulta) {
        this.tipoDiagnostico = tipoDiagnostico;
        this.sintomas = sintomas;
        this.observaciones = observaciones;
        this.consulta = consulta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(String tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    
}
