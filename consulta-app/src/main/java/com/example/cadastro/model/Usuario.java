package com.example.cadastro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String genero;

    private LocalDate dataNascimento;

    private String celular;

    private Boolean ativo;

    @Lob
    private byte[] curriculo; 
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_de_atuacao_id, nullable = true")
    private AreaDeAtuacao areaDeAtuacao;
    
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "nivel_escolaridade_id, nullable = true")
    private NivelDeEscolaridade nivelDeEscolaridade;
    
    
    @ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "estados_id, nullable = true")
    private Estados estado;
    
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_participante_id" , nullable = true)
    private EnderecoParticipante enderecoParticipante;
   
}
