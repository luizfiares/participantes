package com.example.cadastro.service;

import com.example.cadastro.model.AreaDeAtuacao;
import com.example.cadastro.repository.AreaDeAtuacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaDeAtuacaoService {

    private final AreaDeAtuacaoRepository areaDeAtuacaoRepository;

    public AreaDeAtuacaoService(AreaDeAtuacaoRepository areaDeAtuacaoRepository) {
        this.areaDeAtuacaoRepository = areaDeAtuacaoRepository;
    }

    public List<AreaDeAtuacao> getAllAreas() {
        return areaDeAtuacaoRepository.findAll();
    }
}
