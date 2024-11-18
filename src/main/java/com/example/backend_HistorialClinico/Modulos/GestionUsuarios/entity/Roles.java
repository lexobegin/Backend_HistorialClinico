package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity;

import java.util.HashSet;

import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Roles {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // Relación Muchos a Muchos con Permisos
    @ManyToMany(fetch = FetchType.EAGER) // Eager para cargar los permisos junto con el rol
    @JoinTable(
        name = "roles_permisos", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "rol_id"), // Llave foránea hacia Roles
        inverseJoinColumns = @JoinColumn(name = "permiso_id") // Llave foránea hacia Permisos
    )
    private Set<Permisos> permisos = new HashSet<>();

    

    public Roles() {
    }

    

    public Roles(int id, String nombre, Set<Permisos> permisos) {
        this.id = id;
        this.nombre = nombre;
        this.permisos = permisos;
    }



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

    public Set<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<Permisos> permisos) {
        this.permisos = permisos;
    }

    
   
}
