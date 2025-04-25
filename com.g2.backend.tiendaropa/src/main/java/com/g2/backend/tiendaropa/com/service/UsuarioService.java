package com.g2.backend.tiendaropa.com.service;

import com.g2.backend.tiendaropa.com.model.dto.UsuarioDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO obtenerUsuarioPorId(Long Id);
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    UsuarioDTO actualizarUsuario(Long Id, UsuarioDTO usuarioDTO);
    void eliminarUsuario (Long Id);

}