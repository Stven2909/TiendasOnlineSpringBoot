package com.g2.backend.tiendaropa.com.controller;

import com.g2.backend.tiendaropa.com.enums.MetodoPago;
import com.g2.backend.tiendaropa.com.model.dto.UsuarioDTO;
import com.g2.backend.tiendaropa.com.model.entity.Usuario;
import com.g2.backend.tiendaropa.com.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestParam String email, @RequestParam String password)
    {
        //filtro
       //busca al usuario por email y contra
       UsuarioDTO usuarioDTO = usuarioService.buscarPorEmailyPassword(email, password);
       if (usuarioDTO != null){
           return ResponseEntity.ok(usuarioDTO); //login exitoso
       }else {
           return ResponseEntity.status(401).body(null); //no es valido
       }

       }

    // --- MÉTODO REGISTER---
    @PostMapping("/register") // Mapea las peticiones POST a /api/auth/register
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioDTO usuarioDTO) { // <--- ¡AQUÍ EL CAMBIO CLAVE!
        try {
            // Llama a crearUsuario pasando directamente el UsuarioDTO
            UsuarioDTO nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
            // Si el registro fue exitoso, devuelve 201
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            // Loguea el error para depuración en consola del backend
            System.err.println("Error al registrar usuario: " + e.getMessage());
            // Devuelve un error 400 Bad Request si algo salió mal (ej. email ya existe)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    }


