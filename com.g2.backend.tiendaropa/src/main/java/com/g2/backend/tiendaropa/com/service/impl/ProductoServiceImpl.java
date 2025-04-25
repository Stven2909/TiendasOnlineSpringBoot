package com.g2.backend.tiendaropa.com.service.impl;

import com.g2.backend.tiendaropa.com.model.dto.ProductoDTO;
import com.g2.backend.tiendaropa.com.model.entity.Producto;
import com.g2.backend.tiendaropa.com.repository.ProductoRepository;
import com.g2.backend.tiendaropa.com.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;


    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO)
    {
       // Convertir el DTO a entidad Producto
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setImage(productoDTO.getImage());

        // Guardar el producto en la base de datos
        Producto savedProducto = productoRepository.save(producto);

        // Convertir la entidad guardada de vuelta a DTO y devolverlo
        return convertToDTO(savedProducto);
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Long Id) {
        Producto producto = productoRepository.findById(Id).orElse(null);
        return producto != null ? convertToDTO(producto) : null;
    }

    @Override
    public List<ProductoDTO> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::convertToDTO)  // Convertir cada Producto a ProductoDTO
                .collect(Collectors.toList());  // Recoger todos los DTOs en una lista
    }


    @Override
    public ProductoDTO actualizarProducto(Long Id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(Id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setNombre(productoDTO.getNombre());
            productoExistente.setDescripcion(productoDTO.getDescripcion());
            productoExistente.setCategoria(productoDTO.getCategoria());
            productoExistente.setPrecio(productoDTO.getPrecio());
            productoExistente.setStock(productoDTO.getStock());
            productoExistente.setImage(productoDTO.getImage());

            // Guardar el producto actualizado en la base de datos
            productoRepository.save(productoExistente);

            // Convertir la entidad actualizada a DTO
            return convertToDTO(productoExistente);
        }
        return null;
    }

    @Override
    public void eliminarProducto(Long Id) {
        productoRepository.deleteById(Id);

    }

    @Override
    public Page<ProductoDTO> obtenerProductosFiltrados(String nombre, Double precioMin, Double precioMax, int pageNo, int pageSize, String sortBy) {
        //Creando la ordenacion
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));

        //Realizando la busqueda en la db
        Page<Producto> productosPage = productoRepository.findByNombreContainingAndPrecioBetween(
                nombre != null ? nombre : "",  // Si nombre es null, se busca todo
                precioMin != null ? precioMin : 0.0,  // Si no se proporciona precio mínimo, se usa 0
                precioMax != null ? precioMax : Double.MAX_VALUE,  // Si no se proporciona precio máximo, se usa el valor más alto posible
                pageable
        );

        //Convertir los productos a DTO
        return productosPage.map(this::convertToDTO);
    }

    //Metodo para convertir a dto
    private ProductoDTO convertToDTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getCategoria(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getImage()
        );
    }
}