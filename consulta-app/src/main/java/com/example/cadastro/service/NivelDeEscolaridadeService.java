package com.example.cadastro.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cadastro.model.NivelDeEscolaridade;
import com.example.cadastro.repository.NivelDeEscolarRepository;



@Service
public class NivelDeEscolaridadeService {

    private final NivelDeEscolarRepository nivelDeEscolarRepository;
    
    public NivelDeEscolaridadeService(NivelDeEscolarRepository nivelDeEscolarRepository) {
        this.nivelDeEscolarRepository = nivelDeEscolarRepository;
    }
    public List<NivelDeEscolaridade> getAllEscolaridades() {
        return nivelDeEscolarRepository.findAll();
    }
}
