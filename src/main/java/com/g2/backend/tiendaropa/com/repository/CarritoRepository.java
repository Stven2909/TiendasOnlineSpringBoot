package com.g2.backend.tiendaropa.com.repository;

import com.g2.backend.tiendaropa.com.model.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    @Query("SELECT c FROM Carrito c WHERE c.usuario.id = :usuarioId")
    List<Carrito> findAllByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT c FROM Carrito c LEFT JOIN FETCH c.productos WHERE c.usuario.id = :usuarioId")
    Carrito findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
