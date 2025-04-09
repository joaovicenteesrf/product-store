package com.example.demo.entities;

import com.example.demo.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany
    private List<Produto> produtos;

    private BigDecimal total;
}
