package com.treinamentojava.treinamentojava;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste-seguranca")
public class TesteSeguranca {

    @GetMapping
    public ResponseEntity<String> retornarString() {
        return ResponseEntity.ok("Olá para endpoint seguro");
    }
}
