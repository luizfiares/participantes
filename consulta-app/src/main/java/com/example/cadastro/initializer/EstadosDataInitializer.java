package com.example.cadastro.initializer;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.cadastro.model.Estados;
import com.example.cadastro.repository.EstadosRepository;

@Component
public class EstadosDataInitializer implements CommandLineRunner {

    private final EstadosRepository repository;

    public EstadosDataInitializer(EstadosRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
        	 repository.saveAll(Arrays.asList(
                new Estados("Acre"),
                new Estados("Alagoas"),
                new Estados("Amapá"),
                new Estados("Amazonas"),
                new Estados("Bahia"),
                new Estados("Ceará"),
                new Estados("Distrito Federal"),
                new Estados("Espírito Santo"),
                new Estados("Goiás"),
                new Estados("Maranhão"),
                new Estados("Mato Grosso"),
                new Estados("Mato Grosso do Sul"),
                new Estados("Minas Gerais"),
                new Estados("Pará"),
                new Estados("Paraíba"),
                new Estados("Paraná"),
                new Estados("Pernambuco"),
                new Estados("Piauí"),
                new Estados("Rio de Janeiro"),
                new Estados("Rio Grande do Norte"),
                new Estados("Rio Grande do Sul"),
                new Estados("Rondônia"),
                new Estados("Roraima"),
                new Estados("Santa Catarina"),
                new Estados("São Paulo"),
                new Estados("Sergipe"),
                new Estados("Tocantins")
            ));
        }
    }
}