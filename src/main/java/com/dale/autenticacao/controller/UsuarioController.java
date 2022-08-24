package com.dale.autenticacao.controller;

import com.dale.autenticacao.model.Usuario;
import com.dale.autenticacao.model.usuario.LoginRequest;
import com.dale.autenticacao.model.usuario.LoginResponse;
import com.dale.autenticacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obterTodos(){
        return usuarioService.obterTodos();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obter(@PathVariable("id") Long id){
         return usuarioService.obterId(id);
    }

    @PostMapping
    public Usuario adicionar(@RequestBody Usuario usuario){
        return usuarioService.adicionar(usuario);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return usuarioService.logar(request.getEmail(), request.getSenha());
    }
}
