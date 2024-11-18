package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "consulta_id", nullable = false, unique = true)
    @JsonIgnoreProperties({"receta", "user", "cita"})
    private Consulta consulta;

    @Column(nullable = false)
    private LocalDateTime fecha;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JoinColumn(name = "receta_id")
    @JsonIgnoreProperties({"receta", "user", "cita"})
    private List<Medicamento> medicamentos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

}
