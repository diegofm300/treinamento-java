package com.treinamentojava.treinamentojava.clientes.repository;

import com.treinamentojava.treinamentojava.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    Optional<Cliente> findByCpf(String cpf);
}
