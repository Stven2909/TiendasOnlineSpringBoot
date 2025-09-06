package com.g2.backend.tiendaropa.com.service;

import com.g2.backend.tiendaropa.com.model.dto.CarritoDTO;

import java.util.List;

public interface CarritoService {
    List<CarritoDTO> getAllCarritos();
    CarritoDTO getCarritoById(Long Id);
    CarritoDTO getCarritoByUsuarioId(Long Id);
    CarritoDTO saveCarrito(CarritoDTO carrito);
    void deleteCarritosByUsuarioId(Long usuarioId);
    //nuevos metodos para poder agregar y eliminar productos desde el carrito
    void agregarProducto(Long carritoId, Long productoId);
    void eliminarProducto(Long carritoId, Long productoId);

}
