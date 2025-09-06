package com.g2.backend.tiendaropa.com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    private Long id;
    private Long usuarioId;
    private List<ProductoDTO> productos; //Lista de productos
    private Double total;
    private LocalDate fecha;
}
