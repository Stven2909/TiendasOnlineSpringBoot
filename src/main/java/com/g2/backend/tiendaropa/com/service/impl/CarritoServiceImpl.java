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

import java.util.ArrayList;
import java.util.Collections;
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
    public CarritoDTO getCarritoByUsuarioId(Long usuarioId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId);
        if (carrito == null) {
            // Crear un nuevo carrito si no existe
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Carrito nuevoCarrito = new Carrito();
            nuevoCarrito.setUsuario(usuario);
            carrito = carritoRepository.save(nuevoCarrito);
        }
        return convertToDTO(carrito);
    }

    @Override
    public CarritoDTO saveCarrito(CarritoDTO carritoDTO) {
        if (carritoDTO.getUsuarioId() == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioRepository.findById(carritoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = convertToEntity(carritoDTO);
        carrito.setUsuario(usuario);  // Asegurarnos de asignar el usuario al carrito
        Carrito savedCarrito = carritoRepository.save(carrito);

        return convertToDTO(savedCarrito);
    }

    @Override
    public void deleteCarritosByUsuarioId(Long usuarioId) {
        List<Carrito> carritos = carritoRepository.findAllByUsuarioId(usuarioId);
        System.out.println("Eliminando " + carritos.size() + " carritos para usuarioId: " + usuarioId);
        for (Carrito carrito : carritos) {
            System.out.println("Limpiando productos del carrito: " + carrito.getId());
            carrito.getProductos().clear();
            carritoRepository.save(carrito);
            System.out.println("Eliminando carrito: " + carrito.getId());
            carritoRepository.deleteById(carrito.getId());
        }
    }

    @Override
    public void agregarProducto(Long carritoId, Long productoId) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Añadir el producto al carrito
        carrito.getProductos().add(producto);

        //Actualiza el carrito
        carrito.actualizarTotal();
        // Guardar el carrito con el producto agregado
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

        // Asegúrate de que la lista de productos no sea null y está correctamente mapeada
        List<ProductoDTO> productosDTO = carrito.getProductos() != null
                ? carrito.getProductos().stream().map(this::convertToProductoDTO).collect(Collectors.toList())
                : new ArrayList<>();
        dto.setProductos(productosDTO);

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
        dto.setImage(producto.getImage());
        System.out.println("Producto ID: " + producto.getId() + ", Image: " + producto.getImage()); // Log para depurar
        return dto;
    }
}