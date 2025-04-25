package com.g2.backend.tiendaropa.com.service.impl;

import com.g2.backend.tiendaropa.com.model.entity.Factura;
import com.g2.backend.tiendaropa.com.repository.FacturaRepository;
import com.g2.backend.tiendaropa.com.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura getFacturaById(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id));
    }

    @Override
    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura updateFactura(Long id, Factura facturaDetails) {
        Factura factura = getFacturaById(id);
        // Aquí puedes actualizar los campos de la factura según sea necesario
        factura.setTotal(facturaDetails.getTotal());
        factura.setFecha(facturaDetails.getFecha());
        // Actualiza otros campos según sea necesario
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}