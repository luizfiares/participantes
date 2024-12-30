package com.example.cadastro.controller;

import com.example.cadastro.model.AreaDeAtuacao;
import com.example.cadastro.service.AreaDeAtuacaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaDeAtuacaoController {

    private final AreaDeAtuacaoService areaDeAtuacaoService;

    public AreaDeAtuacaoController(AreaDeAtuacaoService areaDeAtuacaoService) {
        this.areaDeAtuacaoService = areaDeAtuacaoService;
    }

    @GetMapping
    public List<AreaDeAtuacao> getAreasDeAtuacao() {
        return areaDeAtuacaoService.getAllAreas();
    }
}
