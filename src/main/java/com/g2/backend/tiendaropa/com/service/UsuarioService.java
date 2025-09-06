package com.g2.backend.tiendaropa.com.service;

import com.g2.backend.tiendaropa.com.model.dto.UsuarioDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuarioPorId(Long Id);
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    UsuarioDTO actualizarUsuario(Long Id, UsuarioDTO usuarioDTO);
    void eliminarUsuario (Long Id);
    UsuarioDTO buscarPorEmailyPassword(String email, String password);

    // Retorna una página de UsuarioDTOs, recibe criterios de filtrado, paginación y ordenación.
    Page<UsuarioDTO> obtenerUsuariosFiltrados(
            String nombre,    // Nuevo filtro por nombre
            String apellido,  // Nuevo filtro por apellido
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir
    );


}