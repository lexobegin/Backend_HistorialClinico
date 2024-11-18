package com.example.backend_HistorialClinico.Auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    Integer userId;
    private String username;
    private String role;
    private List<String> permissions; // Lista de permisos del usuario

}
