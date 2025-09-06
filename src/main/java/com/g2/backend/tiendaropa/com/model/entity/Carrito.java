package com.g2.backend.tiendaropa.com.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore // Para evitar la referencia circular
    private Usuario usuario;


    // Relación con Producto
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "carrito_producto",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    @JsonManagedReference
    private List<Producto> productos = new ArrayList<>();

    private Double total = 0.0;


    public Double calcularTotal() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    public void actualizarTotal(){
        this.total = calcularTotal();
    } //agregue esto
}