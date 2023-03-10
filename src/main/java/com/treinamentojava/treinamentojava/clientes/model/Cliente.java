package com.treinamentojava.treinamentojava.clientes.model;

import com.treinamentojava.treinamentojava.clientes.api.dto.AtualizarClienteDto;
import com.treinamentojava.treinamentojava.clientes.api.dto.CadastroClienteDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "clientes")
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Cliente {

    @Id
    private String id;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String cpf;

    public static Cliente from(CadastroClienteDto dto) {
        return Cliente.builder()
                .id(UUID.randomUUID().toString())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .endereco(dto.getEndereco())
                .dataNascimento(dto.getDataNascimento())
                .build();
    }

    public static Cliente from(String cpf, String id, AtualizarClienteDto dto) {
        return Cliente.builder()
                .id(id)
                .nome(dto.getNome())
                .cpf(cpf)
                .endereco(dto.getEndereco())
                .dataNascimento(dto.getDataNascimento())
                .build();
    }
}
