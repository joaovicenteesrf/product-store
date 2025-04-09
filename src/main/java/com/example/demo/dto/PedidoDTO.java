package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PedidoDTO {

    @Email(message = "Email é obrigatório.")
    private String clienteEmail;

    private List<CompraDTO> produtos;

}
