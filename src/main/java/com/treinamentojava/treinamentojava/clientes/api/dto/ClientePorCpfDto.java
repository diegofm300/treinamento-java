package com.treinamentojava.treinamentojava.clientes.api.dto;

import com.treinamentojava.treinamentojava.clientes.model.Cliente;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class ClientePorCpfDto {
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;

    public static ClientePorCpfDto from(Cliente cliente) {
        return ClientePorCpfDto.builder()
                .nome(cliente.getNome())
                .dataNascimento(cliente.getDataNascimento())
                .endereco(cliente.getEndereco())
                .build();
    }
}
