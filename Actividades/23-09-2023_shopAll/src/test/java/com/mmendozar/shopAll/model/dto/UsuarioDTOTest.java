package com.mmendozar.shopAll.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDTOTest {

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    public void setUp() {
        usuarioDTO = new UsuarioDTO();
    }

    @Test
    public void testGetSetNombre() {
        assertNull(usuarioDTO.getNombre());
        usuarioDTO.setNombre("María");
        assertEquals("María", usuarioDTO.getNombre());
    }
    
    @Test
    public void testGetSetApePaterno() {
        assertNull(usuarioDTO.getApePaterno());
        usuarioDTO.setNombre("Mendoza");
        assertEquals("Mendoza", usuarioDTO.getNombre());
    }

    @Test
    public void testNombreNotNull() {
        usuarioDTO.setNombre(null);
    }
    
    @Test
    public void testApePaternoNotNull() {
        usuarioDTO.setApePaterno(null);
    }
    
    @Test
    public void testEmailNotNull() {
        usuarioDTO.setEmail(null);
    }
    @Test
    public void testNombreMaxLength() {
        usuarioDTO.setNombre("NombreConMásDe50CaracteresNombreConMasDe50CARACTEREEEEEEEEEEEEEEEEEEES");
    }

    @Test
    public void testIdRolNotNull() {
        usuarioDTO.setIdRol(null);
    }

    @Test
    public void testIdRolInvalid() {
        usuarioDTO.setIdRol(-1);
    }
}
