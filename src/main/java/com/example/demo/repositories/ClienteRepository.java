package com.example.demo.repositories;

import com.example.demo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM Cliente WHERE email = :email ", nativeQuery = true)
    Optional<Cliente> getClientByEmail(String email);

    @Query(value = "SELECT * FROM Cliente WHERE id = :id", nativeQuery = true)
    Optional<Cliente> getClientById(Long id);
}
