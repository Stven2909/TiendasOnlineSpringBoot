package com.g2.backend.tiendaropa.com.controller;

import com.g2.backend.tiendaropa.com.model.dto.CarritoDTO;
import com.g2.backend.tiendaropa.com.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> getCarritoById(@PathVariable Long id) {
        CarritoDTO carritoDTO = carritoService.getCarritoById(id);
        return carritoDTO != null ? ResponseEntity.ok(carritoDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public CarritoDTO createCarrito(@RequestBody CarritoDTO carritoDTO) {
        return carritoService.saveCarrito(carritoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Long id) {
        carritoService.deleteCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{carritoId}/productos/{productoId}")
    public ResponseEntity<Void> agregarProducto(@PathVariable Long carritoId, @PathVariable Long productoId) {
        carritoService.agregarProducto(carritoId, productoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{carritoId}/productos/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long carritoId, @PathVariable Long productoId) {
        carritoService.eliminarProducto(carritoId, productoId);
        return ResponseEntity.ok().build();
    }
}