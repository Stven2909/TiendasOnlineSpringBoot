package com.g2.backend.tiendaropa.com.service.impl;

import com.g2.backend.tiendaropa.com.model.dto.CarritoDTO;
import com.g2.backend.tiendaropa.com.model.dto.ProductoDTO;
import com.g2.backend.tiendaropa.com.model.entity.Carrito;
import com.g2.backend.tiendaropa.com.model.entity.Producto;
import com.g2.backend.tiendaropa.com.model.entity.Usuario;
import com.g2.backend.tiendaropa.com.repository.CarritoRepository;
import com.g2.backend.tiendaropa.com.repository.ProductoRepository; // Asegúrate de tener este repositorio
import com.g2.backend.tiendaropa.com.repository.UsuarioRepository; // Asegúrate de tener este repositorio
import com.g2.backend.tiendaropa.com.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositorio para buscar usuarios

    @Autowired
    private ProductoRepository productoRepository; // Repositorio para buscar productos

    @Override
    public List<CarritoDTO> getAllCarritos() {
        return carritoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarritoDTO getCarritoById(Long Id) {
        Carrito carrito = carritoRepository.findById(Id).orElse(null);
        return carrito != null ? convertToDTO(carrito) : null;
    }

    @Override
    public CarritoDTO saveCarrito(CarritoDTO carritoDTO) {
        Carrito carrito = convertToEntity(carritoDTO);
        Carrito savedCarrito = carritoRepository.save(carrito);
        return convertToDTO(savedCarrito);
    }

    @Override
    public void deleteCarrito(Long Id) {
        carritoRepository.deleteById(Id);
    }

    @Override
    public void agregarProducto(Long carritoId, Long productoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        carrito.getProductos().add(producto);
        carritoRepository.save(carrito);
    }

    @Override
    public void eliminarProducto(Long carritoId, Long productoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        carrito.getProductos().removeIf(producto -> producto.getId().equals(productoId));
        carritoRepository.save(carrito);
    }

    // Método para convertir CarritoDTO a Carrito
    private Carrito convertToEntity(CarritoDTO carritoDTO) {
        Carrito carrito = new Carrito();
        carrito.setId(carritoDTO.getId());

        // Asignar el usuario al carrito
        if (carritoDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(carritoDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            carrito.setUsuario(usuario);
        }

        // Convertir la lista de productos DTO a entidades
        List<Producto> productos = carritoDTO.getProductos().stream()
                .map(this::convertToProductoEntity)
                .collect(Collectors.toList());
        carrito.setProductos(productos);

        return carrito;
    }

    // Método para convertir ProductoDTO a Producto
    private Producto convertToProductoEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        return producto;
    }

    // Método para convertir Carrito a CarritoDTO
    private CarritoDTO convertToDTO(Carrito carrito) {
        CarritoDTO dto = new CarritoDTO();
        dto.setId(carrito.getId());
        dto.setUsuarioId(carrito.getUsuario() != null ? carrito.getUsuario().getId() : null);
        dto.setProductos(carrito.getProductos().stream()
                .map(this::convertToProductoDTO)
                .collect(Collectors.toList()));
        dto.setTotal(carrito.calcularTotal());
        return dto;
    }

    //Metodo para convertir producto a productoDTO
    private ProductoDTO convertToProductoDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }
}