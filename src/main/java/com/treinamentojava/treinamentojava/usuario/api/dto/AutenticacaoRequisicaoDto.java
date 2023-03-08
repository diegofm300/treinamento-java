package com.treinamentojava.treinamentojava.usuario.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoRequisicaoDto {
    private String email;
    private String password;
}
