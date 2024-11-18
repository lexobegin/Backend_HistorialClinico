package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "antecedente")
public class Antecedente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;

    @ElementCollection
    @CollectionTable(name = "antecedente_enfermedades_cronicas", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "enfermedad_cronica")
    private List<String> enfermedadesCronicas;

    @ElementCollection
    @CollectionTable(name = "antecedente_enfermedades_agudas", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "enfermedad_aguda")
    private List<String> enfermedadesAgudas;

    @ElementCollection
    @CollectionTable(name = "antecedente_intervenciones_quirurgicas", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "intervencion_quirurgica")
    private List<String> intervencionesQuirurgicas;

    @ElementCollection
    @CollectionTable(name = "antecedente_alergias", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "alergia")
    private List<String> alergias;

    private String hospitalizacionesPrevias;

    @ElementCollection
    @CollectionTable(name = "antecedente_medicamentos", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "medicamento")
    private List<String> medicamentosUsoLargoPlazo;

    @ElementCollection
    @CollectionTable(name = "antecedente_enfermedades_infecciosas", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "enfermedad_infecciosa")
    private List<String> enfermedadesInfecciosas;

    @ElementCollection
    @CollectionTable(name = "antecedente_vacunas", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "vacuna")
    private List<String> vacunas;

    @ElementCollection
    @CollectionTable(name = "antecedente_enfermedades_hereditarias", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "enfermedad_hereditaria")
    private List<String> enfermedadesHereditarias;

    @ElementCollection
    @CollectionTable(name = "antecedente_suplementos", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "suplemento")
    private List<String> suplementos;

    @ElementCollection
    @CollectionTable(name = "antecedente_restricciones", joinColumns = @JoinColumn(name = "antecedente_id"))
    @Column(name = "restriccion")
    private List<String> restriccionesAlimenticias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructor vacío
    public Antecedente() {}

    // Constructor completo
    public Antecedente(LocalDate fecha, List<String> enfermedadesCronicas, List<String> enfermedadesAgudas, List<String> intervencionesQuirurgicas, List<String> alergias, String hospitalizacionesPrevias, List<String> medicamentosUsoLargoPlazo, List<String> enfermedadesInfecciosas, List<String> vacunas, List<String> enfermedadesHereditarias, List<String> suplementos, List<String> restriccionesAlimenticias, User user) {
        this.fecha = fecha;
        this.enfermedadesCronicas = enfermedadesCronicas;
        this.enfermedadesAgudas = enfermedadesAgudas;
        this.intervencionesQuirurgicas = intervencionesQuirurgicas;
        this.alergias = alergias;
        this.hospitalizacionesPrevias = hospitalizacionesPrevias;
        this.medicamentosUsoLargoPlazo = medicamentosUsoLargoPlazo;
        this.enfermedadesInfecciosas = enfermedadesInfecciosas;
        this.vacunas = vacunas;
        this.enfermedadesHereditarias = enfermedadesHereditarias;
        this.suplementos = suplementos;
        this.restriccionesAlimenticias = restriccionesAlimenticias;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<String> getEnfermedadesCronicas() {
        return enfermedadesCronicas;
    }

    public void setEnfermedadesCronicas(List<String> enfermedadesCronicas) {
        this.enfermedadesCronicas = enfermedadesCronicas;
    }

    public List<String> getEnfermedadesAgudas() {
        return enfermedadesAgudas;
    }

    public void setEnfermedadesAgudas(List<String> enfermedadesAgudas) {
        this.enfermedadesAgudas = enfermedadesAgudas;
    }

    public List<String> getIntervencionesQuirurgicas() {
        return intervencionesQuirurgicas;
    }

    public void setIntervencionesQuirurgicas(List<String> intervencionesQuirurgicas) {
        this.intervencionesQuirurgicas = intervencionesQuirurgicas;
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public String getHospitalizacionesPrevias() {
        return hospitalizacionesPrevias;
    }

    public void setHospitalizacionesPrevias(String hospitalizacionesPrevias) {
        this.hospitalizacionesPrevias = hospitalizacionesPrevias;
    }

    public List<String> getMedicamentosUsoLargoPlazo() {
        return medicamentosUsoLargoPlazo;
    }

    public void setMedicamentosUsoLargoPlazo(List<String> medicamentosUsoLargoPlazo) {
        this.medicamentosUsoLargoPlazo = medicamentosUsoLargoPlazo;
    }

    public List<String> getEnfermedadesInfecciosas() {
        return enfermedadesInfecciosas;
    }

    public void setEnfermedadesInfecciosas(List<String> enfermedadesInfecciosas) {
        this.enfermedadesInfecciosas = enfermedadesInfecciosas;
    }

    public List<String> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<String> vacunas) {
        this.vacunas = vacunas;
    }

    public List<String> getEnfermedadesHereditarias() {
        return enfermedadesHereditarias;
    }

    public void setEnfermedadesHereditarias(List<String> enfermedadesHereditarias) {
        this.enfermedadesHereditarias = enfermedadesHereditarias;
    }

    public List<String> getSuplementos() {
        return suplementos;
    }

    public void setSuplementos(List<String> suplementos) {
        this.suplementos = suplementos;
    }

    public List<String> getRestriccionesAlimenticias() {
        return restriccionesAlimenticias;
    }

    public void setRestriccionesAlimenticias(List<String> restriccionesAlimenticias) {
        this.restriccionesAlimenticias = restriccionesAlimenticias;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}