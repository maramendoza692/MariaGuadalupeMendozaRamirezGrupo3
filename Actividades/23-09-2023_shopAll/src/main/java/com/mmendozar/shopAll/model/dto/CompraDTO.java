package com.mmendozar.shopAll.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@Validated
public class CompraDTO {

	private int idCompra;
    @NotNull(message = "Debe ingresar un usuario")
    @Positive(message = "Debe ingresar un usuario válido")
    private int idUsuario;
    @NotNull(message = "Debe ingresar el total de la compra")   
    private double total;
    @NotNull(message = "Debe ingresar la cantidad")
    private int cantidad;   
    private Date fecha;
    @Valid
    private List<ProductoAddDTO> productos;
}
