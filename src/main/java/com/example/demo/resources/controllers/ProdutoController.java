package com.example.demo.resources.controllers;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ProdutoDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Produto;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> getProductById (@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.getProductById(id));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<CollectionModel<Produto>> listAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        var response = CollectionModel.of(produtoService.getAllProducts(pageable));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Produto> createProduct(@RequestBody ProdutoDTO produtoDTO) {
        Produto saved = produtoService.createProduct(produtoDTO);
        URI location = URI.create("/product" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        produtoService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
