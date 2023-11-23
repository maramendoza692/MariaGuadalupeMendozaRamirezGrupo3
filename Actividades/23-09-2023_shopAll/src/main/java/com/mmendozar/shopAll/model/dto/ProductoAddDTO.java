package com.mmendozar.shopAll.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
public class ProductoAddDTO {

    @NotNull(message = "Debe ingresar un id de producto")
    @Positive(message = "Debe ingresar un id de producto")
    private  int idProducto;
    @NotNull(message = "Debe ingresar una cantidad")
    private int cantidad;
    @NotNull(message = "Debe ingresar un precio")
    private double precioUnitario;
    @NotNull(message = "Debe ingresar un monto")
    private double total;

    public  ProductoAddDTO(){

    }

    public ProductoAddDTO(int idProducto, int cantidad){
        super();
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
}
