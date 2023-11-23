package com.mmendozar.shopAll.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;

import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Rol;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.UsuarioDTO;
import com.mmendozar.shopAll.repository.RolRepository;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("María");
        usuarioDTO.setApePaterno("Mendoza");
        usuarioDTO.setApeMaterno("Ramírez");
        usuarioDTO.setTelefono("4151173572");
        usuarioDTO.setEmail("mariagmenr@gmail.com");
        usuarioDTO.setPassword("seguridad2020");
        usuarioDTO.setIdRol(1);
    }

    @Test
    void consultarTodos() {
        // Regresar una lista de usuarios
        List<Usuario> usuariosMock = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuariosMock);

        // Llamae el método a probar
        Response<Usuario> result = usuarioService.consultarTodos();

        // Confrimar reusltados
        assertNotNull(result);
        assertEquals(Constantes.CONSULTA_CORRECTA, result.getMessage());
        assertEquals("OK", result.getStatus());
        assertEquals(usuariosMock, result.getList());
        assertEquals(usuariosMock.size(), result.getCount());

        verify(usuarioRepository).findAll();
    }

    @Test
    void guardarUsuario() {
        // Configurar mocks
        when(rolRepository.findById(anyInt())).thenReturn(Optional.of(new Rol()));
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setIdUsuario(1); 
            return usuario;
        });

        // Llamar el método a probar
        Response<Usuario> savedResult = usuarioService.guardarUsuario(usuarioDTO);

        // Verificar resultados
        assertNotNull(savedResult);
        assertEquals(Constantes.GUARDADO_CORRECTAMENTE, savedResult.getMessage());
        assertEquals("OK", savedResult.getStatus());
        assertNotNull(savedResult.getData());
        assertNotNull(savedResult.getData().getIdUsuario());

        verify(rolRepository).findById(anyInt());
        verify(passwordEncoder).encode(anyString());
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void eliminarUsuario() {
        Response<Integer> deletedResult = usuarioService.eliminarUsuario(1);

        assertNotNull(deletedResult);
        assertEquals(Constantes.ELIMINADO_CORRECTAMENTE, deletedResult.getMessage());
        assertEquals("OK", deletedResult.getStatus());
        assertEquals(1, deletedResult.getData());

        verify(usuarioRepository).deleteById(1);
    }

    @Test
    void actualizarUsuario() {
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(rolRepository.findById(anyInt())).thenReturn(Optional.of(new Rol()));
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario usuario = invocation.getArgument(0);
            usuario.setIdUsuario(1); 
            return usuario;
        });

        Response<Usuario> updatedResult = usuarioService.actualizarUsuario(1, usuarioDTO);

        assertNotNull(updatedResult);
        assertEquals(Constantes.ACTUALIZADO_CORRECTAMENTE, updatedResult.getMessage());
        assertEquals("OK", updatedResult.getStatus());
        assertNotNull(updatedResult.getData());
        assertNotNull(updatedResult.getData().getIdUsuario());

        verify(usuarioRepository).findById(1);
        verify(rolRepository).findById(anyInt());
        verify(passwordEncoder).encode(anyString());
        verify(usuarioRepository).save(any(Usuario.class));
    }
}
