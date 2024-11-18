package com.example.backend_HistorialClinico.Modulos.AtencionesMedicas.entity;

import com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "triaje")
public class Triaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private double temperatura;

    @Column(nullable = false)
    private String presionArterial;

    @Column(nullable = false)
    private int frecuenciaCardiaca;

    @Column(nullable = false)
    private int frecuenciaRespiratoria;

    @Column(nullable = false)
    private int saturacionOxigeno;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private double altura;

    @Column(nullable = true)
    private double imc;  // Se puede calcular en el servicio si es necesario

    @Column(length = 500)
    private String sintomas;

    @Column
    private String estadoMental;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructor vacío
    public Triaje() {}

    // Constructor completo
    public Triaje(LocalDate fecha, double temperatura, String presionArterial, int frecuenciaCardiaca, int frecuenciaRespiratoria, int saturacionOxigeno, double peso, double altura, String sintomas, String estadoMental, User user) {
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.presionArterial = presionArterial;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.saturacionOxigeno = saturacionOxigeno;
        this.peso = peso;
        this.altura = altura;
        this.sintomas = sintomas;
        this.estadoMental = estadoMental;
        this.user = user;
        this.imc = calcularIMC();
    }

    // Método para calcular el IMC
    public double calcularIMC() {
        return peso / (altura * altura);
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

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public int getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(int saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
        this.imc = calcularIMC(); // Recalcula el IMC cuando se cambia el peso
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
        this.imc = calcularIMC(); // Recalcula el IMC cuando se cambia la altura
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getEstadoMental() {
        return estadoMental;
    }

    public void setEstadoMental(String estadoMental) {
        this.estadoMental = estadoMental;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters y Setters para cada campo
    // (agrega los getters y setters para cada uno de los campos)
}
