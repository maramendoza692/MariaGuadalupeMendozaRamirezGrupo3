package com.mmendozar.shopAll.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoriaDTO {

	@NotEmpty
	@NotNull(message = "Debe ingresar el nombre de la categoría")
	@Size(max = 50)
	private String nombre;
}
