package com.g2.backend.tiendaropa.com.repository;

import com.g2.backend.tiendaropa.com.model.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
