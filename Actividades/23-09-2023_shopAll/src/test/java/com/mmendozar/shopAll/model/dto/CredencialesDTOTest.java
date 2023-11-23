package com.mmendozar.shopAll.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CredencialesDTOTest {

    private CredencialesDTO credencialesDTO;

    @BeforeEach
    public void setUp() {
        credencialesDTO = new CredencialesDTO();

    }

    @Test
    public void testGetSetEmail() {
        assertNull(credencialesDTO.getEmail());
        credencialesDTO.setEmail("correo@gmail.com.com");
        assertEquals("correo@gmail.com.com", credencialesDTO.getEmail());
    }

    @Test
    public void testEmailNotNull() {
        credencialesDTO.setEmail(null);
    }

    @Test
    public void testEmailInvalid() {
        credencialesDTO.setEmail("correo");
    }

    @Test
    public void testGetSetPassword() {
        assertNull(credencialesDTO.getPassword());
        credencialesDTO.setPassword("contraseña123");
        assertEquals("contraseña123", credencialesDTO.getPassword());
    }

    @Test
    public void testPasswordNotNull() {
        credencialesDTO.setPassword(null);
    }
}

