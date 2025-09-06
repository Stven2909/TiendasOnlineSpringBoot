package com.g2.backend.tiendaropa.com.controller;

import com.g2.backend.tiendaropa.com.model.dto.ProductoDTO;
import com.g2.backend.tiendaropa.com.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/crearProducto")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.crearProducto(productoDTO));
    }

    @GetMapping("/{id}/obtenerProducto")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Long id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/obtenerTodosLosProductos")
    public ResponseEntity<List<ProductoDTO>> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }

    @PutMapping("/{id}/actualizarProducto")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return productoActualizado != null ? ResponseEntity.ok(productoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/eliminarProducto")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtrados")
    public ResponseEntity<Page<ProductoDTO>> obtenerProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Double precioMin,
            @RequestParam(required = false) Double precioMax,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "nombre") String sortBy
    ) {
        Page<ProductoDTO> productos = productoService.obtenerProductosFiltrados(
                nombre, precioMin, precioMax, pageNo, pageSize, sortBy
        );
        return ResponseEntity.ok(productos);
    }
    //Para probar el envio de correos de errores
    @GetMapping("/test-error")
      public ResponseEntity<String> testError() {throw new RuntimeException("Este es un error de prueba para verificar la notificación por correo.");
    }
}
