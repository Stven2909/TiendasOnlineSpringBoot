package com.g2.backend.tiendaropa.com.service.impl;

import com.g2.backend.tiendaropa.com.model.dto.FacturaDTO;
import com.g2.backend.tiendaropa.com.model.dto.ProductoDTO;
import com.g2.backend.tiendaropa.com.model.entity.Carrito;
import com.g2.backend.tiendaropa.com.model.entity.Factura;
import com.g2.backend.tiendaropa.com.model.entity.Producto;
import com.g2.backend.tiendaropa.com.model.entity.Usuario;
import com.g2.backend.tiendaropa.com.repository.CarritoRepository;
import com.g2.backend.tiendaropa.com.repository.FacturaRepository;
import com.g2.backend.tiendaropa.com.repository.ProductoRepository;
import com.g2.backend.tiendaropa.com.repository.UsuarioRepository;
import com.g2.backend.tiendaropa.com.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {
    @Autowired
    private FacturaRepository facturaRepository; //Repositorios

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarritoRepository carritoRepository;


    //Obtiene todas las facturas
    @Override
    public List<Factura> getFacturas() {
        return facturaRepository.findAll(); //retorna todas las facturas
    }

    //Obtiene factura por ID
    @Override
    public Factura getFacturaById(Long id) {
        return facturaRepository.findById(id) //La busca
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con ID: " + id)); //Error si no la encuentra
    }

    //Crea una nueva factura
    @Override
    public Factura createFactura(Factura factura) {
        return facturaRepository.save(factura); //Guarda la factura creada
    }


    //Actualiza una factura existente
    @Override
    public Factura updateFactura(Long id, Factura facturaDetails) {
        Factura factura = getFacturaById(id);//Busca por ID
        // Aquí se actualizan los campos de la factura según sea necesario
        factura.setTotal(facturaDetails.getTotal());
        factura.setFecha(facturaDetails.getFecha());
        return facturaRepository.save(factura); //Guarda los cambios
    }


    //Elimina factura por ID
    @Override
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);//La elimina
    }

    //Crea una factura a partir del carrito del usuario
    @Override
    public FacturaDTO crearFacturaDesdeCarrito(Long usuarioId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId); //Busca el carrito del user
        if (carrito == null || carrito.getProductos().isEmpty()) { //Verifica si esta vacio o no
            throw new RuntimeException("Carrito vacío o no encontrado"); //Lanza error si esta vacio o no existe
        }

        Factura factura = new Factura(); //Crea la nueva instancia
        factura.setUsuario(carrito.getUsuario()); //Asigna el usuario del carrito a la factura
        factura.setProductos(new ArrayList<>(carrito.getProductos())); //Copia los productos del carrito a la factura
        factura.setTotal(carrito.calcularTotal()); //Calcula total
        factura.setFecha(LocalDate.now());//Fecha

        factura = facturaRepository.save(factura); //Lo guarda

        // Limpiar el carrito
        carrito.getProductos().clear();
        carritoRepository.save(carrito);

        return convertToFacturaDTO(factura); //Convierte y retorna la factura a DTO
    }

    //Convierte la entidad a DTO
    private FacturaDTO convertToFacturaDTO(Factura factura) {
        FacturaDTO dto = new FacturaDTO(); //instancia del dto
        dto.setId(factura.getId());//Asigna ID de la factura
        dto.setUsuarioId(factura.getUsuario().getId()); //Asigna Id del usuario
        dto.setProductos(factura.getProductos().stream()//Mapea la lista de productos a DTO
                .map(this::convertToProductoDTO)//Usa el metodo para convertir
                .collect(Collectors.toList()));//Los pone en lista
        dto.setTotal(factura.getTotal());//Total
        dto.setFecha(factura.getFecha());//Fecha
        return dto;//regresa el DTO
    }

    //Convierte la entidad a DTO
    private ProductoDTO convertToProductoDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setImage(producto.getImage());
        System.out.println("Producto ID: " + producto.getId() + ", Image: " + producto.getImage()); // Log
        return dto;
    }


    //Crea factura desde el DTO
    @Override
    public Factura createFacturaDesdeDTO(FacturaDTO facturaDTO) {
        Factura factura = new Factura();
        Usuario usuario = usuarioRepository.findById(facturaDTO.getUsuarioId())//Busca por ID
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));//Lanza error si no encuentra

        factura.setUsuario(usuario);//Asigna el usuario a la factura
        factura.setFecha(facturaDTO.getFecha());//Fecha

        List<Producto> productos = new ArrayList<>();//Lista para productos
        for (ProductoDTO dto : facturaDTO.getProductos()) //Itera sobre el DTO
        {
            Producto producto = productoRepository.findById(dto.getId()) //Busca cada producto por ID
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + dto.getId() ));
            productos.add(producto); //Si lo encuentra lo agrega
        }
        factura.setProductos(productos); //Asigna la lista de productos a la factura
        factura.setTotal(factura.calcularTotal()); //Calcula y asigna el total
        return facturaRepository.save(factura); //Guarda la factura
    }
}