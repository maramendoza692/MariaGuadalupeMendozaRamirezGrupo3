package com.mmendozar.shopAll.model.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransaccionDTOTest {

    private TransaccionDTO transaccionDTO;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        transaccionDTO = new TransaccionDTO();

        // Configuración del validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGetSetIdCompra() {
        assertNull(transaccionDTO.getIdCompra());
        transaccionDTO.setIdCompra(1);
        assertEquals(1, transaccionDTO.getIdCompra());
    }

    @Test
    public void testIdCompraNotNull() {
        transaccionDTO.setIdCompra(null);
    }

    @Test
    public void testIdCompraValid() {
        transaccionDTO.setIdCompra(1);
        assertEquals(0, validator.validate(transaccionDTO).size());
    }

    @Test
    public void testIdCompraInvalid() {
        transaccionDTO.setIdCompra(-1);
    }
}
