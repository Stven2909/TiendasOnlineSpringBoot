package com.g2.backend.tiendaropa.com.repository;

import com.g2.backend.tiendaropa.com.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    //metodo para lo del login
    Usuario findByEmailAndPassword(String email, String password);

//Consulta para filtrado con paginacion y ordenacion
    @Query("SELECT u FROM Usuario u WHERE " +
            "(:nombrePattern IS NULL OR LOWER(u.nombre) LIKE :nombrePattern) AND " + //Filtra por nombre
            "(:apellidoPattern IS NULL OR LOWER(u.apellido) LIKE :apellidoPattern)") //Filtra por apellidos
    Page<Usuario> findByNombreOrApellidoContainingIgnoreCase( //Metodo para obtener una pagina de usuarios filtrados por nombre/apellidos
            @Param("nombrePattern") String nombrePattern,   // Ahora se espera el patrón completo (ej. "%juan%")
            @Param("apellidoPattern") String apellidoPattern, // Ahora se espera el patrón completo (ej. "%perez%")
            Pageable pageable);
}

