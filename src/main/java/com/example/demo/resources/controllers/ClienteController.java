package com.example.demo.resources.controllers;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.service.ClienteService;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(path = "/test")
    public ResponseEntity<String> testeConexao() {
        return ResponseEntity.ok("Conexão com componente está ok!");
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> getClientById (@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getClienteById(id));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Cliente>> createClient(@RequestBody ClienteDTO cliente) {
        return ResponseEntity.ok(EntityModel.of(clienteService.createCliente(cliente)));
    }

}
