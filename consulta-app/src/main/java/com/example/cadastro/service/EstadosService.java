package com.example.cadastro.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cadastro.model.Estados;
import com.example.cadastro.repository.EstadosRepository;

@Service
public class EstadosService {

    private final EstadosRepository estadosRepository;

    public EstadosService(EstadosRepository estadosRepository) {
        this.estadosRepository = estadosRepository;
    }

    public List<Estados> getAllEstados() {
        return estadosRepository.findAll();
    }
}
