package com.treinamentojava.treinamentojava.clientes.api.dto;

import com.treinamentojava.treinamentojava.clientes.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarClienteDto {

    @NotBlank(message = "O campo nome deve ser informado")
    private String nome;
    @NotBlank(message = "O campo endereço deve ser informado")
    private String endereco;
    @NotNull(message = "O campo data de nascimento deve ser informado")
    private LocalDate dataNascimento;

    public static AtualizarClienteDto from(Cliente cliente) {
        return AtualizarClienteDto.builder()
                .nome(cliente.getNome())
                .endereco(cliente.getEndereco())
                .dataNascimento(cliente.getDataNascimento())
                .build();
    }
}
