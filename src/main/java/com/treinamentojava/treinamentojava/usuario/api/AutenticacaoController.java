package com.treinamentojava.treinamentojava.usuario.api;

import com.treinamentojava.treinamentojava.usuario.api.dto.RegistroRequisicaoDto;
import com.treinamentojava.treinamentojava.usuario.api.dto.RespostaAutenticacaoDto;
import com.treinamentojava.treinamentojava.usuario.api.dto.AutenticacaoRequisicaoDto;
import com.treinamentojava.treinamentojava.usuario.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService service;

    @PostMapping("/registrar")
    public ResponseEntity<RespostaAutenticacaoDto> registrar(@RequestBody RegistroRequisicaoDto requisicao) {
        return ResponseEntity.ok(service.registrar(requisicao));
    }

    @PostMapping("/autenticar")
    public ResponseEntity<RespostaAutenticacaoDto> autenticar(@RequestBody AutenticacaoRequisicaoDto requisicao) {
        return ResponseEntity.ok(service.autenticar(requisicao));
    }
}
