package com.banco.appbancaria.service;

import com.banco.appbancaria.dto.CuentaRequest;
import com.banco.appbancaria.model.CuentaBancaria;
import com.banco.appbancaria.model.Usuario;
import com.banco.appbancaria.repository.CuentaRepository;
import com.banco.appbancaria.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final UsuarioRepository usuarioRepository;

    public CuentaService(CuentaRepository cuentaRepository, UsuarioRepository usuarioRepository) {
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CuentaBancaria crearCuentaParaUsuario(Long usuarioId, CuentaRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con id: " + usuarioId);
        }

        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setTitular(request.getTitular());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setUsuario(usuarioOpt.get());

        return cuentaRepository.save(cuenta);
    }

    public List<CuentaBancaria> obtenerCuentasPorUsuario(Long usuarioId) {
        return cuentaRepository.findByUsuarioId(usuarioId);
    }

    public Optional<CuentaBancaria> obtenerPorId(Long id) {
        return cuentaRepository.findById(id);
    }

    public void eliminarCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    public CuentaBancaria actualizarCuenta(Long id, CuentaBancaria cuentaActualizada) {
        return cuentaRepository.findById(id).map(cuenta -> {
            cuenta.setTitular(cuentaActualizada.getTitular());
            cuenta.setSaldo(cuentaActualizada.getSaldo());
            return cuentaRepository.save(cuenta);
        }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada con id: " + id));
    }
}
