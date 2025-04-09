package com.example.demo.dto;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PedidoDTO {

    private String clienteEmail;

    private List<CompraDTO> produtos;

}
