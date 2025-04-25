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
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Double precio;
    private Integer stock;
    private String image;

    // Relación con Carrito
    @ManyToMany(mappedBy = "productos") // Cambiado a "productos" para coincidir con la entidad Carrito
    private List<Carrito> carritos = new ArrayList<>();
}