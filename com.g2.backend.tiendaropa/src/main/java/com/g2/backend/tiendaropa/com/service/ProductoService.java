package com.g2.backend.tiendaropa.com.service;

import com.g2.backend.tiendaropa.com.model.dto.ProductoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {

    // Crear un producto
    ProductoDTO crearProducto(ProductoDTO productoDTO);

    // Obtener un producto por su ID
    ProductoDTO obtenerProductoPorId(Long Id);

    // Obtener todos los productos
    List<ProductoDTO> obtenerTodosLosProductos();

    // Actualizar un producto
    ProductoDTO actualizarProducto(Long Id, ProductoDTO productoDTO);

    // Eliminar un producto
    void eliminarProducto(Long Id);

    // Obtener productos filtrados con paginación
    Page<ProductoDTO> obtenerProductosFiltrados(String nombre, Double precioMin, Double precioMax, int pageNo, int pageSize, String sortBy);
}
