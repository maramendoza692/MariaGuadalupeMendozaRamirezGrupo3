package com.mmendozar.shopAll.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ComentarioDTOTest {

    private ComentarioDTO comentarioDTO;

    @BeforeEach
    public void setUp() {
        comentarioDTO = new ComentarioDTO();

    }

    @Test
    public void testGetSetDescripcion() {
        assertNull(comentarioDTO.getDescripcion());
        comentarioDTO.setDescripcion("Descripción del comentario");
        assertEquals("Descripción del comentario", comentarioDTO.getDescripcion());
    }

    @Test
    public void testDescripcionNotNull() {
        comentarioDTO.setDescripcion(null);
    }

    @Test
    public void testDescripcionNotEmpty() {
        comentarioDTO.setDescripcion("");
    }

    @Test
    public void testDescripcionMaxLength() {
        comentarioDTO.setDescripcion("Descripción del comentario con más de 500 caracteres para superar la validación de longituddddddddddddddddddddddd");
    }

    @Test
    public void testGetSetIdComprador() {
        assertNull(comentarioDTO.getIdComprador());
        comentarioDTO.setIdComprador(1);
        assertEquals(1, comentarioDTO.getIdComprador());
    }

    @Test
    public void testIdCompradorNotNull() {
        comentarioDTO.setIdComprador(null);
    }

    @Test
    public void testIdCompradorPositive() {
        comentarioDTO.setIdComprador(-1);
    }

    @Test
    public void testGetSetIdProducto() {
        assertNull(comentarioDTO.getIdProducto());
        comentarioDTO.setIdProducto(1);
        assertEquals(1, comentarioDTO.getIdProducto());
    }

    @Test
    public void testIdProductoNotNull() {
        comentarioDTO.setIdProducto(null);
    }

    @Test
    public void testIdProductoPositive() {
        comentarioDTO.setIdProducto(-1);
    }

    @Test
    public void testGetSetFecha() {
        assertNull(comentarioDTO.getFecha());
        Date fecha = new Date();
        comentarioDTO.setFecha(fecha);
        assertEquals(fecha, comentarioDTO.getFecha());
    }
}

