package com.example.backend_HistorialClinico.Modulos.GestionUsuarios.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements UserDetails {
    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)


    String username;
    String ci;
    String nombre;
    String apellido_paterno;
    String apellido_materno;
    String fecha_nacimiento;
    String password;
    String telefono;
    String genero;

    // Relación Muchos a Uno: muchos usuarios pueden tener un mismo rol
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id", nullable = false) // Llave foránea hacia Roles
    private Roles role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // Verifica si el role no es null antes de acceder a sus métodos
        if (role != null) {
            // Añadir el rol con prefijo "ROLE_"
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNombre()));

            // Añadir los permisos asociados al rol
            for (Permisos permiso : role.getPermisos()) {
                authorities.add(new SimpleGrantedAuthority(permiso.getNombre()));
            }
        }

        return authorities;
    }

    public Roles getRol() {
        return role;
    }

    public void setRol(Roles rol) {
        this.role = rol;
    }
    
    public String getFechaNacimiento() {
        return fecha_nacimiento;
    }
    public String getApellidoMaterno() {
        return apellido_materno;
    }
    public String getApellidoPaterno() {
        return apellido_paterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellido_paterno = apellidoPaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellido_materno = apellidoMaterno;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fecha_nacimiento = fechaNacimiento;
    }

   
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
