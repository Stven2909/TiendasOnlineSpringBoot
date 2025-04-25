package com.g2.backend.tiendaropa.com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Double precio;
    private Integer stock;
    private String image;

   // public ProductoDTO(Long id, String nombre, String descripcion, Double precio) {
   // }
}
