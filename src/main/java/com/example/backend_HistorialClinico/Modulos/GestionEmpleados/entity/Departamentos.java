package com.example.backend_HistorialClinico.Modulos.GestionEmpleados.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "departamentos")
public class Departamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;
    
    @Column(nullable = false)
    private String direccion;
    // Constructor vacío
    public Departamentos() {
    }

    // Constructor con todos los atributos
    

    // Getters y Setters
    public int getId() {
        return id;
    }

    public Departamentos(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
