package com.example.cadastro.dto;
import lombok.Data;

@Data
public class UsuarioFiltroDTO {
    private String nome;
    private String email;
    private String cpf;
    private String genero;
    private String dataNascimento;
    private Boolean ativo;
    private String celular;

    private Long  areaDeAtuacao;
    private Long  nivelDeEscolaridade;
    private Long  estado;
}