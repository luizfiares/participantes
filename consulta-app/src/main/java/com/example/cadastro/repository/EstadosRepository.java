package com.example.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastro.model.Estados;

public interface EstadosRepository extends JpaRepository<Estados, Long> {
}
