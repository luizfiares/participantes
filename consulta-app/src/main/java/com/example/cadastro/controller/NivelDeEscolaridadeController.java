package com.example.cadastro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.model.NivelDeEscolaridade;
import com.example.cadastro.service.NivelDeEscolaridadeService;

@RestController
@RequestMapping("/api/escolaridade")
public class NivelDeEscolaridadeController {

    private final NivelDeEscolaridadeService nivelDeEscolaridadeService;


    public NivelDeEscolaridadeController(NivelDeEscolaridadeService nivelDeEscolaridadeService) {
        this.nivelDeEscolaridadeService = nivelDeEscolaridadeService;
    }

    @GetMapping
    public List<NivelDeEscolaridade> getNivelEscolaridade() {
        return nivelDeEscolaridadeService.getAllEscolaridades();
    }
}