package com.mmendozar.shopAll.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductoDTOTest {

    private ProductoDTO productoDTO;

    @BeforeEach
    public void setUp() {
        productoDTO = new ProductoDTO();
    }

    @Test
    public void testGetSetNombre() {
        assertNull(productoDTO.getNombre());
        productoDTO.setNombre("Producto 1");
        assertEquals("Producto 1", productoDTO.getNombre());
    }

    @Test
    public void testGetSetDescripcion() {
        assertNull(productoDTO.getDescripcion());
        productoDTO.setDescripcion("Descripción del producto");
        assertEquals("Descripción del producto", productoDTO.getDescripcion());
    }

    @Test
    public void testGetSetPrecioUnitario() {
        assertNull(productoDTO.getPrecio());
        productoDTO.setPrecio(10.0);
        assertEquals(10.0, productoDTO.getPrecio(), 0.001);
    }

    @Test
    public void testGetSetIdCategoria() {
        assertEquals(0, productoDTO.getIdCategoria());
        productoDTO.setIdCategoria(1);
        assertEquals(1, productoDTO.getIdCategoria());
    }
}
