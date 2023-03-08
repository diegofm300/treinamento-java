package com.treinamentojava.treinamentojava.usuario.repository;

import com.treinamentojava.treinamentojava.usuario.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);
}
