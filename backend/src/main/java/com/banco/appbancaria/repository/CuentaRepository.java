package com.banco.appbancaria.repository;

import com.banco.appbancaria.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface CuentaRepository extends JpaRepository<CuentaBancaria, Long>{
    List<CuentaBancaria> findByUsuarioId(Long usuarioId);
}
