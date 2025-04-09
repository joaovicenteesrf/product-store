package com.example.demo.repositories;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT * FROM Produto where id = :id", nativeQuery = true)
    public Optional<Produto> getProductById(Long id);

    @Query(value = "SELECT * FROM Produto where nome = :name", nativeQuery = true)
    public Optional<Produto> getProductByName(String name);

    @Query(value = "SELECT * FROM Produto", nativeQuery = true)
    public List<Produto> getAllProducts();
}
