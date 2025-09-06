package com.g2.backend.tiendaropa.com.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.g2.backend.tiendaropa.com.enums.MetodoPago;
import com.g2.backend.tiendaropa.com.model.entity.Carrito;
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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    //esto le agregue
    @Column(nullable = false)
    private String password;


    //para serializarlo
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_metodos_pago", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "metodo_pago")
    private List<MetodoPago> metodosPago = new ArrayList<>();
    private String direccion;
    private String telefono;

    // Relación con Carrito
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference // Para evitar la referencia cíclica
    private List<Carrito> carrito = new ArrayList<>();
}
