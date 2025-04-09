package com.example.demo.resources.controllers;

import com.example.demo.dto.CompraDTO;
import com.example.demo.dto.PedidoDTO;
import com.example.demo.entities.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<String> testeConexao() {
        return ResponseEntity.ok("Conexão com componente está ok!");
    }

    @GetMapping(value = "/byId")
    public ResponseEntity<EntityModel<Pedido>> getPedidoById(@RequestParam Long id) {
        var response = pedidoService.getPedidoById(id);
        return ResponseEntity.ok(EntityModel.of(response));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Pedido>> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
       return ResponseEntity.ok(EntityModel.of(pedidoService.criarPedido(pedidoDTO)));
    }


    // TODO: adicionar GET /pedidos/{id}
    // TODO: adicionar GET /pedidos?status=...
}
