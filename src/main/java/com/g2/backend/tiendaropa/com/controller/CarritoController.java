package com.g2.backend.tiendaropa.com.controller;

import com.g2.backend.tiendaropa.com.model.dto.CarritoDTO;
import com.g2.backend.tiendaropa.com.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public List<CarritoDTO> getAllCarritos() {
        return carritoService.getAllCarritos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> getCarritoByUsuarioId(@PathVariable Long usuarioId) {
        CarritoDTO carritoDTO = carritoService.getCarritoByUsuarioId(usuarioId);

        if (carritoDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carritoDTO);
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> deleteCarritosByUsuarioId(@PathVariable Long usuarioId) {
        carritoService.deleteCarritosByUsuarioId(usuarioId);
        return ResponseEntity.ok().build();
    }

    // Método para crear un nuevo carrito solo si no existe
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> crearCarritoSiNoExiste(@PathVariable Long usuarioId) {
        CarritoDTO carritoDTO = carritoService.getCarritoByUsuarioId(usuarioId);

        if (carritoDTO == null) {
            carritoDTO = new CarritoDTO();
            carritoDTO.setUsuarioId(usuarioId);
            carritoDTO.setProductos(new ArrayList<>());
            carritoDTO = carritoService.saveCarrito(carritoDTO);
        }

        return ResponseEntity.ok(carritoDTO);
    }

    // Método para agregar producto al carrito existente
    @PostMapping("/{carritoId}/productos/{productoId}")
    public ResponseEntity<Void> agregarProducto(@PathVariable Long carritoId, @PathVariable Long productoId) {
        CarritoDTO carritoDTO = carritoService.getCarritoById(carritoId);

        if (carritoDTO == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si el carrito no existe
        }

        // Agregar el producto al carrito existente
        carritoService.agregarProducto(carritoDTO.getId(), productoId);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/{carritoId}/productos/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long carritoId, @PathVariable Long productoId) {
        carritoService.eliminarProducto(carritoId, productoId);
        return ResponseEntity.ok().build();
    }
}