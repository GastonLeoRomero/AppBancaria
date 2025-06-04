package com.banco.appbancaria.repository;

import java.util.Optional;

import com.banco.appbancaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);

}
