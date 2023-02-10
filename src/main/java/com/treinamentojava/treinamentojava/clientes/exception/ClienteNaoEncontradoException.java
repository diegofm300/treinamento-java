package com.treinamentojava.treinamentojava.clientes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 8831009113969481858L;

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
