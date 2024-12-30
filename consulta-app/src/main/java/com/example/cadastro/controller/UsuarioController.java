package com.example.cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.dto.UsuarioFiltroDTO;
import com.example.cadastro.model.Usuario;
import com.example.cadastro.service.UsuarioService;


@CrossOrigin(origins = "http://localhost:42026")
@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;  

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario); 
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);  // Atualiza o usuário no serviço
        return ResponseEntity.ok(usuarioAtualizado);
    }

     
    
    @PostMapping("/buscar")
    public ResponseEntity<?> getUsuarios(@RequestBody @Validated UsuarioFiltroDTO usuarioFiltroDTO) {
        List<Usuario> usuarios = usuarioService.getUsuarios(usuarioFiltroDTO);
        return ResponseEntity.ok(usuarios);
    }
    
    
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);  // Usando o serviço
        return ResponseEntity.noContent().build();
    }
}