package com.treinamentojava.treinamentojava.clientes.api.dto;

import com.treinamentojava.treinamentojava.clientes.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroClienteDto {

    @NotBlank(message = "O campo nome deve ser informado")
    private String nome;
    @CPF(message = "Informe um CPF válido")
    @NotBlank(message = "O campo CPF deve ser informado")
    private String cpf;
    @NotBlank(message = "O campo endereço deve ser informado")
    private String endereco;
    @NotNull(message = "O campo data de nascimento deve ser informado")
    private LocalDate dataNascimento;

    public static CadastroClienteDto from(Cliente cliente) {
        return CadastroClienteDto.builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .endereco(cliente.getEndereco())
                .dataNascimento(cliente.getDataNascimento())
                .build();
    }
}
