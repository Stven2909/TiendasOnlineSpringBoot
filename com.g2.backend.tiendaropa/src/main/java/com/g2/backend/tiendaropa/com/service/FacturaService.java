package com.g2.backend.tiendaropa.com.service;

import com.g2.backend.tiendaropa.com.model.entity.Factura;

import java.util.List;

public interface FacturaService {
    List<Factura> getFacturas();
    Factura getFacturaById(Long id);
    Factura createFactura(Factura factura);
    Factura updateFactura(Long id, Factura facturaDetails);
    void deleteFactura(Long id);
}