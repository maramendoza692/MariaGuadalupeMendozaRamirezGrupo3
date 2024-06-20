package com.mmendozar.shopAll.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RespuestaGenerica {

    private int codigo;
    private boolean exito;
    private String mensaje;
    private List<Object> datos;

    public RespuestaGenerica(){
        this.datos = new ArrayList<>();
    }

    public RespuestaGenerica(int codigo, boolean exito, String mensaje) {
        super();
        this.codigo = codigo;
        this.exito = exito;
        this.mensaje = mensaje;
    }
}
