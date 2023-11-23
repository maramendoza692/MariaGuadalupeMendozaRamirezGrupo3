package com.mmendozar.shopAll.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoDTO {

	@NotNull(message = "Debe ingresar un nombre")
	@Size(max = 50, message = "Debe ingresar un nombre más corto")
    private String nombre;
	@NotNull(message = "Debe ingresar una descripción")
	@Size(max = 100, message = "Debe ingresar una descripción más corta")
    private String descripcion;
	@NotNull(message = "Debe ingresar el precio")
    private Double precio;
	@NotNull(message = "Debe ingresar la cantidad")
    private Integer cantDisponible;
	@NotNull(message = "Debe ingresar el id de la categoría")
    @Positive(message = "Debe ingresar un idCategoría válido")
    private int idCategoria;
	@NotNull(message = "Debe ingresar el id del vendedor")
    @Positive(message = "Debe ingresar un idVendedor válido")
    private int idVendedor;


}
