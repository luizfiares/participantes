package com.example.cadastro.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.model.Estados;
import com.example.cadastro.service.EstadosService;

@RestController
@RequestMapping("/api/estados")
public class EstadosController {

    private final EstadosService estadosService;

    public EstadosController(EstadosService estadosService) {
        this.estadosService = estadosService;
    }
    
    @GetMapping
    public List<Estados> getEstados() {
        return estadosService.getAllEstados();
    }
}
