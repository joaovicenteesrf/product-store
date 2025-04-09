package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Produto {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Min(0)
    private BigDecimal valor;

    @Min(0)
    private Integer quantidade = 1;
}
