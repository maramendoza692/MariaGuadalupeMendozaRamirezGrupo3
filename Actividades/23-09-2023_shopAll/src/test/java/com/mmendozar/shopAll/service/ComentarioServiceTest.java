package com.mmendozar.shopAll.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mmendozar.shopAll.model.Comentario;
import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.ComentarioDTO;
import com.mmendozar.shopAll.repository.ComentarioRepository;
import com.mmendozar.shopAll.repository.ProductoRepository;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ComentarioService comentarioService;

    private ComentarioDTO comentarioDTO;

    @BeforeEach
    void setUp() {
        comentarioDTO = new ComentarioDTO();
        comentarioDTO.setDescripcion("Excelente producto");
        comentarioDTO.setIdComprador(1);
        comentarioDTO.setIdProducto(1);
        comentarioDTO.setFecha(new Date(System.currentTimeMillis()));
    }

    @Test
    void consultarTodos() {
        // Regresar una lista de comentarios
        List<Comentario> comentariosMock = Arrays.asList(new Comentario(), new Comentario());
        when(comentarioRepository.findAll()).thenReturn(comentariosMock);

        // Llamar método a probar
        Response<Comentario> result = comentarioService.consultarTodosComentarios();

        // Confirmar
        assertNotNull(result);
        assertEquals(Constantes.CONSULTA_CORRECTA, result.getMessage());
        assertEquals("OK", result.getStatus());
        assertEquals(comentariosMock, result.getList());
        assertEquals(comentariosMock.size(), result.getCount());

        verify(comentarioRepository).findAll();
    }

    @Test
    void guardarComentario() {
        // Regresar un optional usuario
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        // Regresar un optional producto
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(new Producto()));
        // Regresar un comentario cuando se ha guardado
        when(comentarioRepository.save(any(Comentario.class))).thenAnswer(invocation -> {
            Comentario comentario = invocation.getArgument(0);
            comentario.setIdComenatrio(1);
            return comentario;
        });

        // LLamar al método a probar
        Response<Comentario> savedResult = comentarioService.guardarComentario(comentarioDTO);

        assertNotNull(savedResult);
        assertEquals(Constantes.GUARDADO_CORRECTAMENTE, savedResult.getMessage());
        assertEquals("OK", savedResult.getStatus());
        assertNotNull(savedResult.getData());
        assertNotNull(savedResult.getData().getIdComenatrio());

        verify(usuarioRepository).findById(anyInt());
        verify(productoRepository).findById(anyInt());
        verify(comentarioRepository).save(any(Comentario.class));
    }

    @Test
    void guardarComentarioDebeRetornarErrorSiCompradorNoExiste() {
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.empty());
        Response<Comentario> savedResult = comentarioService.guardarComentario(comentarioDTO);

        assertNotNull(savedResult);
        assertEquals(Constantes.COMPRADOR_NO_EXISTENTE, savedResult.getMessage());
        assertEquals("ERROR", savedResult.getStatus());
        assertNull(savedResult.getData());

        verify(usuarioRepository).findById(anyInt());
        verify(productoRepository).findById(anyInt());
        // Verificar que no se llame al método guardar
        verify(comentarioRepository, never()).save(any(Comentario.class));
    }

    @Test
    void guardarComentarioDebeRetornarErrorSiProductoNoExiste() {
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(productoRepository.findById(anyInt())).thenReturn(Optional.empty());

        Response<Comentario> savedResult = comentarioService.guardarComentario(comentarioDTO);

        assertNotNull(savedResult);
        assertEquals(Constantes.PRODUCTO_NO_EXISTENTE, savedResult.getMessage());
        assertEquals("ERROR", savedResult.getStatus());
        assertNull(savedResult.getData());

        verify(usuarioRepository).findById(anyInt());
        verify(productoRepository).findById(anyInt());
        // Verificar que no se llame al método guardar
        verify(comentarioRepository, never()).save(any(Comentario.class));
    }

    @Test
    void eliminarComentario() {
        Response<Integer> deletedResult = comentarioService.eliminarComentario(1);

        assertNotNull(deletedResult);
        assertEquals(Constantes.ELIMINADO_CORRECTAMENTE, deletedResult.getMessage());
        assertEquals("OK", deletedResult.getStatus());
        assertEquals(1, deletedResult.getData());

        verify(comentarioRepository).deleteById(1);
    }

    @Test
    void actualizarComentario() {
        when(comentarioRepository.findById(anyInt())).thenReturn(Optional.of(new Comentario()));
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(new Producto()));
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(new Usuario()));
        when(comentarioRepository.save(any(Comentario.class))).thenAnswer(invocation -> {
            Comentario comentario = invocation.getArgument(0);
            comentario.setIdComenatrio(1); 
            return comentario;
        });

        Response<Comentario> updatedResult = comentarioService.actualizarComentario(1, comentarioDTO);

        assertNotNull(updatedResult);
        assertEquals(Constantes.ACTUALIZADO_CORRECTAMENTE, updatedResult.getMessage());
        assertEquals("OK", updatedResult.getStatus());
        assertNotNull(updatedResult.getData());
        assertNotNull(updatedResult.getData().getIdComenatrio());

        verify(comentarioRepository).findById(1);
        verify(productoRepository).findById(anyInt());
        verify(usuarioRepository).findById(anyInt());
        verify(comentarioRepository).save(any(Comentario.class));
    }


    @Test
    void actualizarComentarioDebeRetornarErrorSiCompradorNoExiste() {
        when(comentarioRepository.findById(anyInt())).thenReturn(Optional.of(new Comentario()));
        when(productoRepository.findById(anyInt())).thenReturn(Optional.of(new Producto()));
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.empty());

        Response<Comentario> updatedResult = comentarioService.actualizarComentario(1, comentarioDTO);

        assertNotNull(updatedResult);
        assertEquals(Constantes.COMPRADOR_NO_EXISTENTE, updatedResult.getMessage());
        assertEquals("ERROR", updatedResult.getStatus());
        assertNull(updatedResult.getData());

        verify(comentarioRepository).findById(1);
        verify(productoRepository).findById(anyInt());
        verify(usuarioRepository).findById(anyInt());
        // Verificar que no se llame al método guardar
        verify(comentarioRepository,  never()).save(any(Comentario.class));
    }
}

