package com.mmendozar.shopAll.model.dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductoAddDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidProductoAddDTO() {
        ProductoAddDTO productoAddDTO = new ProductoAddDTO();
        productoAddDTO.setIdProducto(1);
        productoAddDTO.setCantidad(5);
        productoAddDTO.setPrecioUnitario(100.0);
        productoAddDTO.setTotal(500.0);

        Set<ConstraintViolation<ProductoAddDTO>> violations = validator.validate(productoAddDTO);

        assertTrue(violations.isEmpty());
    }

}