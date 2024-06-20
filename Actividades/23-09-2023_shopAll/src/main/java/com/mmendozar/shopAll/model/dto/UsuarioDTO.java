package com.mmendozar.shopAll.model.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Getter
@Setter
public class UsuarioDTO {
	
	@NotNull(message = "Debe ingresar un nombre")
	@Size(max = 50, message = "Debe ingresar un nombre más corto")
    String nombre;
	@NotNull(message = "Debe ingresar un apellido")
	@Size(max = 50, message = "Debe ingresar un apellido más corto")
    String apePaterno;
	
    String apeMaterno;
    @Size(min = 10, message = "Debe ingresar un teléfono de 10 caracteres")
    String telefono;
    @NotNull(message = "Debe ingresar un email")
	@Email(message = "Debe ingresar un email válido")
    String email;
    @NotNull(message = "Debe ingresar una contraseña")
	@Size(min = 8, message = "La contraseña debe contener mínimo 8 caracteres")
    String password;
    @NotNull(message = "Debe ingresar el id del rol")
    @Positive(message = "Debe ingresar un idRol válido")
    Integer idRol;


}