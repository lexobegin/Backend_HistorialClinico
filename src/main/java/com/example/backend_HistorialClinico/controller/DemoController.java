package com.example.backend_HistorialClinico.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
public class DemoController {
    
    @PostMapping(value = "demo")
    public String welcome(){
        return "Welcome form secure endpoint";
    }
    
    @GetMapping(value = "demo")
    public String uptime(){
        return "Welcome form secure endpoint";
    }
}
