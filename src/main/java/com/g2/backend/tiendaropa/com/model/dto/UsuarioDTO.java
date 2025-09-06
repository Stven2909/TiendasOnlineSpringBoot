package com.g2.backend.tiendaropa.com.model.dto;

import com.g2.backend.tiendaropa.com.enums.MetodoPago;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El email debe ser valido")
    private String email;
    private List<MetodoPago> metodosPago;
    private String direccion;

    //le acabo de agregar esto
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    /// ////////////////////////////
    ///
    @Size(min = 8, max = 10, message = "El telefono debe tener entre 8 a 10 digitos")
    private String telefono;
}
