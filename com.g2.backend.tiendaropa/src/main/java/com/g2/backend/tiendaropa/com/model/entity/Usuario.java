package com.g2.backend.tiendaropa.com.model.entity;

import com.g2.backend.tiendaropa.com.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private List<MetodoPago> metodosPago;
    private String direccion;
    private String telefono;

    //Creando las relaciones

    //Para el carrito, un usuario puede tener un carrito, y un carrito pertenece a un solo usuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Carrito> carrito = new ArrayList<>();

    //Para la factura, un usuario puede tener varias facturas, cada factura pertenece a unb solo usuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Factura> facturas = new ArrayList<>();


}
