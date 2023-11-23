package com.mmendozar.shopAll.model.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoriaDTOTest {

    private CategoriaDTO categoriaDTO;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        categoriaDTO = new CategoriaDTO();

        // Configuración del validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGetSetNombre() {
        assertNull(categoriaDTO.getNombre());
        categoriaDTO.setNombre("Nombre de la categoría");
        assertEquals("Nombre de la categoría", categoriaDTO.getNombre());
    }

    @Test
    public void testNombreNotNull() {
        categoriaDTO.setNombre(null);
        
    }

    @Test
    public void testNombreNotEmpty() {
        categoriaDTO.setNombre("");
    }

    @Test
    public void testNombreMaxLength() {
        categoriaDTO.setNombre("Nombre de la categoría con más de 50 caracteres para superar la validación de longitud");
    }

    @Test
    public void testNombreValid() {
        categoriaDTO.setNombre("Nombre válido");
        assertEquals(0, validator.validate(categoriaDTO).size());
    }
}
