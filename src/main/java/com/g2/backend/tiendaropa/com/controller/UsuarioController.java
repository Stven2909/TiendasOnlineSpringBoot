package com.g2.backend.tiendaropa.com.controller;

import com.g2.backend.tiendaropa.com.model.dto.UsuarioDTO;
import com.g2.backend.tiendaropa.com.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crearUsuario") //Maneja solicitudes POST
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuarioDTO));
    }

    @GetMapping("/{id}/obtenerUsuario")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id){
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build(); //Si encuentra el usuario retorna un estadio 200 OK y el objeto como respuesta
    }

    @GetMapping("/obtenerTodosLosUsuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios(){
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @PutMapping("/{id}/actualizarUsuario")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
        return usuarioActualizado != null ? ResponseEntity.ok(usuarioActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/eliminarUsuario")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return  ResponseEntity.noContent().build();
    }

    //filtrados
    /**
     * Obtiene una lista paginada y ordenada de usuarios, con filtros opcionales por nombre y apellido.
     * @param nombre Filtro opcional por nombre (búsqueda parcial, insensible a mayúsculas/minúsculas).
     * @param apellido Filtro opcional por apellido (búsqueda parcial, insensible a mayúsculas/minúsculas).
     * @param pageNo Número de la página a recuperar (por defecto 0).
     * @param pageSize Tamaño de la página (cantidad de elementos por página, por defecto 10).
     * @param sortBy Campo por el cual ordenar los resultados (por defecto 'nombre').
     * @param sortDir Dirección de la ordenación ('asc' para ascendente, 'desc' para descendente, por defecto 'asc').
     * @return ResponseEntity con un objeto Page de UsuarioDTOs.
     */
    @GetMapping("/filtrados") // Endpoint para filtrar usuarios
    public ResponseEntity<Page<UsuarioDTO>> obtenerUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "nombre") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        // Llama al servicio para obtener la página de usuarios filtrados
        Page<UsuarioDTO> usuarios = usuarioService.obtenerUsuariosFiltrados(
                nombre, apellido, pageNo, pageSize, sortBy, sortDir
        );
        return ResponseEntity.ok(usuarios); // Retorna la página completa de DTOs
    }

}