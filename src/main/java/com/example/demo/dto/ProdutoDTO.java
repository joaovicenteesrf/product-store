package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDTO {

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotNull
    private BigDecimal valor;
}
