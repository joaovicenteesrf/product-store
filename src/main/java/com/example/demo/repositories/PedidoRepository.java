package com.example.demo.repositories;

import com.example.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public Optional<Pedido> findById(Long id);
}
