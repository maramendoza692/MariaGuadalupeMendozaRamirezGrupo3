package com.mmendozar.shopAll.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransaccionDTO {

	@NotNull(message = "Debe ingresar el id de la compra")
    @Positive(message = "Debe ingresar un id válido")
	private Integer idCompra;
}
