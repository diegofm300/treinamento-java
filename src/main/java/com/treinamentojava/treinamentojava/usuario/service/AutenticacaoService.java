package com.treinamentojava.treinamentojava.usuario.service;

import com.treinamentojava.treinamentojava.usuario.api.dto.RespostaAutenticacaoDto;
import com.treinamentojava.treinamentojava.usuario.api.dto.AutenticacaoRequisicaoDto;
import com.treinamentojava.treinamentojava.usuario.api.dto.RegistroRequisicaoDto;
import com.treinamentojava.treinamentojava.usuario.model.Role;
import com.treinamentojava.treinamentojava.usuario.model.Usuario;
import com.treinamentojava.treinamentojava.usuario.repository.UsuarioRepository;
import com.treinamentojava.treinamentojava.usuario.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RespostaAutenticacaoDto registrar(RegistroRequisicaoDto requisicao) {
        var usuario = Usuario.builder()
                .id(UUID.randomUUID().toString())
                .firstname(requisicao.getFirstname())
                .lastname(requisicao.getLastname())
                .password(passwordEncoder.encode(requisicao.getPassword()))
                .email(requisicao.getEmail())
                .role(Role.ADMIN)
                .build();

        repository.save(usuario);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", usuario.getRole());

        var tokenJwt = jwtService.generateToken(extraClaims, usuario);
        return RespostaAutenticacaoDto.builder()
                .token(tokenJwt)
                .build();
    }

    public RespostaAutenticacaoDto autenticar(AutenticacaoRequisicaoDto requisicao) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requisicao.getEmail(), requisicao.getPassword())
        );
        var usuario = repository.findByEmail(requisicao.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));

        var tokenJwt = jwtService.generateToken(usuario);
        return RespostaAutenticacaoDto.builder()
                .token(tokenJwt)
                .build();
    }
}
