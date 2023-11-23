package com.mmendozar.shopAll.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredencialesDTO {

	@NotNull(message = "Debe ingresar un email")
	@Email(message = "Debe ingresar un email válido")
    private String email;
	
	@NotNull(message = "Debe ingresar una contraseña")
    private String password;
}
