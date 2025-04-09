package com.example.demo.repositories;

import com.example.demo.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Optional<Produto> getProductById(Long id);

    public Optional<Produto> findByNome(String name);

    public List<Produto> findAll();
}
