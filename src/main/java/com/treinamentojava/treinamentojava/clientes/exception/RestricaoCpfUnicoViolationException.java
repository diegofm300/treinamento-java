package com.treinamentojava.treinamentojava.clientes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestricaoCpfUnicoViolationException extends RuntimeException{

    public RestricaoCpfUnicoViolationException(String message) {
        super(message);
    }
}
