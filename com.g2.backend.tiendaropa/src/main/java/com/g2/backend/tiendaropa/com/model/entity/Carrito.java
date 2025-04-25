package com.g2.backend.tiendaropa.com.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación con Producto
    @ManyToMany
    @JoinTable(
            name = "carrito_producto", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "carrito_id"), // Columna que referencia a Carrito
            inverseJoinColumns = @JoinColumn(name = "producto_id") // Columna que referencia a Producto
    )
    private List<Producto> productos = new ArrayList<>();

    public Double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}