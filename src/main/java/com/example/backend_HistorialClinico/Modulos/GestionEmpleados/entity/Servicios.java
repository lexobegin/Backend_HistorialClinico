package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicios {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = true)
    private String descripcion;

    // Relación con Departamentos (Muchos a Uno)
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamentos departamento;

    // Relación con Especialidades (Muchos a Uno)
    @ManyToOne
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidades especialidad;

    // Constructor vacío
    public Servicios() {
    }

    // Constructor con todos los atributos
    public Servicios(String nombre, double precio, String descripcion, Departamentos departamento, Especialidades especialidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }
}
