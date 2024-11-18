package com.example.backend_HistorialClinico.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://historia-clinica-front-cyjf.vercel.app/","http://localhost:4200","http://localhost:5173","http://localhost:5174", "http://192.168.0.3",})  
public class AuthController {

    private final AuthService authService;

    @PostMapping(value="login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {   
        System.out.println("Is Web Access: " + request);
        return ResponseEntity.ok(authService.login(request));
    }


    @PostMapping(value="register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/getUserByUsername")
public ResponseEntity<UserResponse> getUserByUsername(@RequestBody UsernameRequest request) {
    return ResponseEntity.ok(authService.getUserByUsername(request.getUsername()));
}


}