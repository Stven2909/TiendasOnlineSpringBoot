package com.g2.backend.tiendaropa.com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {
    private Long id;
    private Long usuarioId; // ID del usuario
    private List<ProductoDTO> productos; // Lista de productos
    private Double total; // Total del carrito
}
