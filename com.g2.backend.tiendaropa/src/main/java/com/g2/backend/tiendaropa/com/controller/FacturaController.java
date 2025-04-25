package com.g2.backend.tiendaropa.com.controller;

import com.g2.backend.tiendaropa.com.model.entity.Factura;
import com.g2.backend.tiendaropa.com.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "http://localhost:4200")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaService.getFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        Factura factura = facturaService.getFacturaById(id);
        return ResponseEntity.ok(factura);
    }

    @PostMapping
    public Factura createFactura(@RequestBody Factura factura) {
        return facturaService.createFactura(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura facturaDetails) {
        Factura updatedFactura = facturaService.updateFactura(id, facturaDetails);
        return ResponseEntity.ok(updatedFactura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        facturaService.deleteFactura(id);
        return ResponseEntity.noContent().build();
    }
}