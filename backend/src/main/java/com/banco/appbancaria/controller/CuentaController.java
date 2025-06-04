package com.banco.appbancaria.controller;

import com.banco.appbancaria.dto.CuentaRequest;
import com.banco.appbancaria.model.CuentaBancaria;
import com.banco.appbancaria.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<CuentaBancaria> crearCuenta(@PathVariable Long usuarioId, @RequestBody CuentaRequest request) {
        return ResponseEntity.ok(cuentaService.crearCuentaParaUsuario(usuarioId, request));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CuentaBancaria>> obtenerCuentasPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(cuentaService.obtenerCuentasPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancaria> obtenerCuentaPorId(@PathVariable Long id) {
        return cuentaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaBancaria> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaBancaria cuentaActualizada) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, cuentaActualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
