package com.g2.backend.tiendaropa.com.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<Producto> productos;

    private Double total;
    private LocalDate fecha;

    public Double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}