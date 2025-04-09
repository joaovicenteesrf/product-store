package com.example.demo.repositories;

import com.example.demo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    /*
    Query customizada:

    @Query(value = "SELECT * FROM Cliente WHERE email = :email", nativeQuery=true)
    Optional<Cliente> findByEmail(String email);
     */
}
