package com.example.cadastro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro.dto.UsuarioFiltroDTO;
import com.example.cadastro.model.AreaDeAtuacao;
import com.example.cadastro.model.EnderecoParticipante;
import com.example.cadastro.model.Estados;
import com.example.cadastro.model.NivelDeEscolaridade;
import com.example.cadastro.model.Usuario;
import com.example.cadastro.repository.AreaDeAtuacaoRepository;
import com.example.cadastro.repository.EstadosRepository;
import com.example.cadastro.repository.NivelDeEscolarRepository;
import com.example.cadastro.repository.UsuarioRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AreaDeAtuacaoRepository areaDeAtuacaoRepository;
    
    @Autowired
    private NivelDeEscolarRepository nivelDeEscolarRepository;
    
    @Autowired
    private EstadosRepository estadosRepository;
    

    public Usuario cadastrarUsuario(Usuario usuario) {
    	System.out.println("Usuario inclusao :" +usuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setGenero(usuario.getGenero());
        usuarioExistente.setDataNascimento(usuario.getDataNascimento());
        usuarioExistente.setCelular(usuario.getCelular());
        usuarioExistente.setAtivo(usuario.getAtivo());


        if (usuario.getAreaDeAtuacao() != null) {
            AreaDeAtuacao areaDeAtuacao = areaDeAtuacaoRepository.findById(usuario.getAreaDeAtuacao().getId())
                    .orElseThrow(() -> new RuntimeException("Área de atuação não encontrada com id: " + usuario.getAreaDeAtuacao().getId()));

            usuarioExistente.setAreaDeAtuacao(areaDeAtuacao); 
        }
        
        if (usuario.getNivelDeEscolaridade() != null) {
            NivelDeEscolaridade nivelEscolaridade = nivelDeEscolarRepository.findById(usuario.getNivelDeEscolaridade().getId())
                    .orElseThrow(() -> new RuntimeException("Escolaridade não encontrada com id: " + usuario.getNivelDeEscolaridade().getId()));

            usuarioExistente.setNivelDeEscolaridade(nivelEscolaridade); 
        }

        if (usuario.getEstado() != null) {
        	Estados estado = estadosRepository.findById(usuario.getEstado().getId())
                    .orElseThrow(() -> new RuntimeException("Estado não encontrada com id: " + usuario.getEstado().getId()));

            usuarioExistente.setEstado(estado); 
        }

        
        
    	System.out.println("usuario ver se tem endereco : "+usuario);
        // Atualiza o endereço, se fornecido
        if (usuario.getEnderecoParticipante() != null) {
        
        	EnderecoParticipante enderecoExistente = usuarioExistente.getEnderecoParticipante();

            if (enderecoExistente == null) {
                enderecoExistente = new EnderecoParticipante();
            }

            enderecoExistente.setCep(usuario.getEnderecoParticipante().getCep());
            enderecoExistente.setEndereco(usuario.getEnderecoParticipante().getEndereco());
            enderecoExistente.setNumero(usuario.getEnderecoParticipante().getNumero());
            enderecoExistente.setComplemento(usuario.getEnderecoParticipante().getComplemento());
            enderecoExistente.setBairro(usuario.getEnderecoParticipante().getBairro());
            enderecoExistente.setUf(usuario.getEnderecoParticipante().getUf());
            enderecoExistente.setMunicipio(usuario.getEnderecoParticipante().getMunicipio());
            usuarioExistente.setEnderecoParticipante(enderecoExistente);
        }
        
        
        return usuarioRepository.save(usuarioExistente);
    }


    public List<Usuario> getUsuarios(UsuarioFiltroDTO filtro) {
        return usuarioRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (filtro.getNome() != null && !filtro.getNome().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }


            if (filtro.getEmail() != null && !filtro.getEmail().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filtro.getEmail().toLowerCase() + "%"));
            }


            if (filtro.getDataNascimento() != null && !filtro.getDataNascimento().isEmpty()) {
                predicates.add(cb.equal(root.get("dataNascimento"), filtro.getDataNascimento()));
            }


            if (filtro.getGenero() != null && !filtro.getGenero().isEmpty()) {
                predicates.add(cb.equal(root.get("genero"), filtro.getGenero()));
            }
            
            if (filtro.getCpf() != null && !filtro.getCpf().isEmpty()) {
                predicates.add(cb.equal(root.get("cpf"), filtro.getCpf()));
            }


            if (filtro.getAtivo() != null) {
                predicates.add(cb.equal(root.get("ativo"), filtro.getAtivo()));
            }

            if (filtro.getAreaDeAtuacao() != null && filtro.getAreaDeAtuacao() != null) {
                predicates.add(cb.equal(root.join("areaDeAtuacao").get("id"), filtro.getAreaDeAtuacao()));
            }

            if (filtro.getNivelDeEscolaridade() != null && filtro.getNivelDeEscolaridade() != null) {
                predicates.add(cb.equal(root.join("nivelDeEscolaridade").get("id"), filtro.getNivelDeEscolaridade()));
            }
            
            if (filtro.getEstado() != null && filtro.getEstado() != null) {
                predicates.add(cb.equal(root.join("estado").get("id"), filtro.getEstado()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }


    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id); 
    }
}