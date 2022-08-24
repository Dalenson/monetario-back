package com.dale.autenticacao.service;

import com.dale.autenticacao.model.Usuario;
import com.dale.autenticacao.model.usuario.LoginResponse;
import com.dale.autenticacao.repository.UsuarioRepository;
import com.dale.autenticacao.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private static final String hederPrefix = "Bearer ";
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Usuario> obterTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterId(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obterEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario adicionar(Usuario usuario){
        usuario.setId(null);
        if(obterEmail(usuario.getEmail()).isPresent()){
            throw new InputMismatchException("Email de usuario já existe!");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public LoginResponse logar(String email, String senha){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = hederPrefix + jwtService.gerarToken(authentication);
        Usuario usuario = usuarioRepository.findByEmail(email).get();
        return new LoginResponse(token, usuario);
    }
}
