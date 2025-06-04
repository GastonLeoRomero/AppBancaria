package com.banco.appbancaria.controller;

import com.banco.appbancaria.model.Usuario;
import com.banco.appbancaria.repository.UsuarioRepository;
import com.banco.appbancaria.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.banco.appbancaria.dto.LoginRequest;
import com.banco.appbancaria.dto.LoginResponse;


import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioRepository.findByNombre(loginRequest.getNombre());
        if (usuario.isPresent()) {
            String token = jwtUtil.generarToken(loginRequest.getNombre());
            return ResponseEntity.ok(new LoginResponse("Bearer " + token));
        } else {
            return ResponseEntity.status(401).body("Usuario no encontrado");
        }
    }
}
