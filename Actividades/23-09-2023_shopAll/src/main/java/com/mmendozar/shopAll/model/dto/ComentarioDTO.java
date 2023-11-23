package com.mmendozar.shopAll.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComentarioDTO {

	@NotNull(message = "Debe ingresar una descripción")
	@Size(max = 500)
	@NotEmpty
	private String descripcion;
	
	@NotNull(message = "Debe ingresar el ID del comprador")
    @Positive(message = "Debe ingresar un idComprador válido")
	private Integer idComprador;
	
	@NotNull(message = "Debe ingresar el ID del producto")
    @Positive(message = "Debe ingresar un idProducto válido")
	private Integer IdProducto;
	
	private Date fecha;
}
