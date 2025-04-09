package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PedidoResponseDTO {
    public Long id;
    public String cliente;
    public LocalDate data;
    public BigDecimal total;
    public List<String> itens;
}
