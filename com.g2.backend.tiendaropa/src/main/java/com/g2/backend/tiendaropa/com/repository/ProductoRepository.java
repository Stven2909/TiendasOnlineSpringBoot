package com.g2.backend.tiendaropa.com.repository;

import com.g2.backend.tiendaropa.com.model.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Método para filtrar por nombre y rango de precios con paginación
    Page<Producto> findByNombreContainingAndPrecioBetween(String nombre, Double precioMin, Double precioMax, Pageable pageable);
}
