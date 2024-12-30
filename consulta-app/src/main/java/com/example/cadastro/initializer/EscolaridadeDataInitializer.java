package com.example.cadastro.initializer;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.cadastro.model.NivelDeEscolaridade;
import com.example.cadastro.repository.NivelDeEscolarRepository;

@Component
public class EscolaridadeDataInitializer implements CommandLineRunner {

    private final NivelDeEscolarRepository repository;

    public EscolaridadeDataInitializer(NivelDeEscolarRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.saveAll(Arrays.asList(
                new NivelDeEscolaridade("Ensino Médio"),
                new NivelDeEscolaridade("Ensino Superior - Incompleto"),
                new NivelDeEscolaridade("Ensino Superior - Completo"),
                new NivelDeEscolaridade("Pós-graduação - Incompleto"),
                new NivelDeEscolaridade("Pós-graduação - Completo"),
                new NivelDeEscolaridade("Mestrado - Completo"),
                new NivelDeEscolaridade("Mestrado - InCompleto"),
                new NivelDeEscolaridade("Doutorado - InCompleto"),
                new NivelDeEscolaridade("Doutorado - Completo")

            ));
        }
    }
}