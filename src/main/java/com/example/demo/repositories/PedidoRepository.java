package com.example.demo.repositories;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT * FROM Pedido WHERE ID = :id", nativeQuery = true)
    public Pedido getById(Long id);
}
