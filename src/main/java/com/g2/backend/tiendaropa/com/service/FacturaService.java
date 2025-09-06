package com.g2.backend.tiendaropa.com.service;

import com.g2.backend.tiendaropa.com.model.dto.FacturaDTO;
import com.g2.backend.tiendaropa.com.model.entity.Factura;

import java.util.List;

public interface FacturaService {
    List<Factura> getFacturas();
    Factura getFacturaById(Long id);
    Factura createFactura(Factura factura);
    Factura updateFactura(Long id, Factura facturaDetails);
    void deleteFactura(Long id);


    FacturaDTO crearFacturaDesdeCarrito(Long usuarioId);

    Factura createFacturaDesdeDTO(FacturaDTO facturaDTO);

}