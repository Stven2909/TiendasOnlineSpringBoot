package com.g2.backend.tiendaropa.com.service.impl;

import com.g2.backend.tiendaropa.com.model.dto.UsuarioDTO;
import com.g2.backend.tiendaropa.com.model.entity.Usuario;
import com.g2.backend.tiendaropa.com.repository.UsuarioRepository;
import com.g2.backend.tiendaropa.com.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setPassword(usuarioDTO.getPassword());
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }


    @Override
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario != null ? convertToDTO(usuario) : null;
    }

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setDireccion(usuarioDTO.getDireccion());
            usuario.setTelefono(usuarioDTO.getTelefono());

            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return convertToDTO(updatedUsuario);
        }
        return null; // O lanza una excepción
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO buscarPorEmailyPassword(String email, String password)
    {
        Usuario usuario = usuarioRepository.findByEmailAndPassword(email, password);
        return usuario != null ? convertToDTO(usuario) : null;
    }

    @Override
    public Page<UsuarioDTO> obtenerUsuariosFiltrados(String nombre, String apellido, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // --- PREPARANDO LOS PATRONES DE BÚSQUEDA ---
        // Si el nombre no es nulo/vacío, se crea el patrón "%nombre_en_minusculas%".
        // Si es nulo/vacío, se pasa null para que la consulta lo ignore.
        String nombrePattern = (nombre != null && !nombre.isEmpty()) ? "%" + nombre.toLowerCase() + "%" : null;
        String apellidoPattern = (apellido != null && !apellido.isEmpty()) ? "%" + apellido.toLowerCase() + "%" : null;

        // Llamar al método del repositorio con los filtros y paginación
        // Ahora pasamos los patrones ya formados
        Page<Usuario> usuarioPage = usuarioRepository.findByNombreOrApellidoContainingIgnoreCase(nombrePattern, apellidoPattern, pageable);

        // Convertir la page de entidades a un DTO
        return usuarioPage.map(this::convertToDTO);

    }


    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setEmail(usuario.getEmail());
        dto.setDireccion(usuario.getDireccion());
        dto.setTelefono(usuario.getTelefono());
        return dto;
    }
}
