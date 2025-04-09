package com.example.demo.resources.controllers;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ProdutoDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Produto;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/byId")
    public ResponseEntity<Produto> getProductById (@RequestParam Long id) {
        return ResponseEntity.ok(produtoService.getProductById(id));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CollectionModel<Produto>> listAllProducts() {
        var response = CollectionModel.of(produtoService.getAllProducts());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> createProduct(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(EntityModel.of(produtoService.createProduct(produtoDTO)));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam Long id) {
        produtoService.deleteProduct(id);
        return ResponseEntity.ok(String.format("Produto de ID %s deletado.", id));
    }

}
