package com.example.demo.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    @DecimalMin("0.00")
    private BigDecimal valor;

    @Min(0)
    private Integer quantidade = 1;
}
