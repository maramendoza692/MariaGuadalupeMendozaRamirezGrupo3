package com.mmendozar.shopAll.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mmendozar.shopAll.model.Categoria;
import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.dto.CategoriaDTO;
import com.mmendozar.shopAll.repository.CategoriaRepository;
import com.mmendozar.shopAll.repository.ProductoRepository;
import com.mmendozar.shopAll.utils.Constantes;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNombre("Hogar");
    }

    @Test
    void consultarTodas() {
        // Regresar una lista de categorías
        List<Categoria> categoriasMock = Arrays.asList(new Categoria(), new Categoria());
        when(categoriaRepository.findAll()).thenReturn(categoriasMock);

        // Llamar el método a probar
        Response<Categoria> result = categoriaService.consultarTodasCategorias();

        assertNotNull(result);
        assertEquals(Constantes.CONSULTA_CORRECTA, result.getMessage());
        assertEquals("OK", result.getStatus());
        assertEquals(categoriasMock, result.getList());
        assertEquals(categoriasMock.size(), result.getCount());

        verify(categoriaRepository).findAll();
    }

    @Test
    void guardarCategoria() {
    	// Configurar el mock para que devuelva un optional vacío (indicando que no existe ninguna categoría)
        when(categoriaRepository.findByNombre(anyString())).thenReturn(Optional.empty());
        // Regresar una categoría cuando se ha guardado
        when(categoriaRepository.save(any(Categoria.class))).thenAnswer(invocation -> {
            Categoria categoria = invocation.getArgument(0);
            categoria.setIdCategoria(1); 
            return categoria;
        });

        // LLamar al método a probar
        Response<Categoria> savedResult = categoriaService.guardarCategoria(categoriaDTO);

        // Confirmar resultados
        assertNotNull(savedResult);
        assertEquals(Constantes.GUARDADO_CORRECTAMENTE, savedResult.getMessage());
        assertEquals("OK", savedResult.getStatus());
        assertNotNull(savedResult.getData());
        assertNotNull(savedResult.getData().getIdCategoria());

        verify(categoriaRepository).findByNombre(anyString());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void eliminarCategoria() {
        when(categoriaRepository.findById(anyInt())).thenReturn(Optional.of(new Categoria()));
        
        when(productoRepository.findByIdCategoria(any(Categoria.class))).thenReturn(Collections.emptyList());

        Response<Integer> deletedResult = categoriaService.eliminarCategoria(1);

        assertNotNull(deletedResult);
        assertEquals(Constantes.ELIMINADO_CORRECTAMENTE, deletedResult.getMessage());
        assertEquals("OK", deletedResult.getStatus());
        assertEquals(1, deletedResult.getData());

        verify(categoriaRepository).findById(1);
        verify(productoRepository).findByIdCategoria(any(Categoria.class));
        verify(categoriaRepository).deleteById(1);
    }

    @Test
    void eliminarCategoriaDberiaRegresarError() {
        when(categoriaRepository.findById(anyInt())).thenReturn(Optional.of(new Categoria()));
       
        when(productoRepository.findByIdCategoria(any(Categoria.class))).thenReturn(Arrays.asList(new Producto()));

        Response<Integer> deletedResult = categoriaService.eliminarCategoria(1);

        assertNotNull(deletedResult);
        assertEquals(Constantes.CATEGORIA_CON_PRODUCTOS, deletedResult.getMessage());
        assertEquals("ERROR", deletedResult.getStatus());
        assertEquals(1, deletedResult.getData());

        verify(categoriaRepository).findById(1);
        verify(productoRepository).findByIdCategoria(any(Categoria.class));
        // Verificar que no se llame al método guardar
        verify(categoriaRepository, never()).deleteById(1);
    }

    @Test
    void actualizarCategoria() {
        when(categoriaRepository.findById(anyInt())).thenReturn(Optional.of(new Categoria()));
        
        when(categoriaRepository.findByNombre(anyString())).thenReturn(Optional.empty());
        
        when(categoriaRepository.save(any(Categoria.class))).thenAnswer(invocation -> {
            Categoria categoria = invocation.getArgument(0);
            categoria.setIdCategoria(1); 
            return categoria;
        });

        Response<Categoria> updatedResult = categoriaService.actualizarCategoria(1, categoriaDTO);

        assertNotNull(updatedResult);
        assertEquals(Constantes.ACTUALIZADO_CORRECTAMENTE, updatedResult.getMessage());
        assertEquals("OK", updatedResult.getStatus());
        assertNotNull(updatedResult.getData());
        assertNotNull(updatedResult.getData().getIdCategoria());

        verify(categoriaRepository).findById(1);
        verify(categoriaRepository).findByNombre(anyString());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void actualizarCategoriaDeberiaRetornarErrorCategoriaExistenteConMismoNombre() {
    	
        when(categoriaRepository.findById(anyInt())).thenReturn(Optional.of(new Categoria()));
        when(categoriaRepository.findByNombre(anyString())).thenReturn(Optional.of(new Categoria()));

        Response<Categoria> updatedResult = categoriaService.actualizarCategoria(1, categoriaDTO);

        assertNotNull(updatedResult);
        assertEquals(Constantes.EXISTENTE, updatedResult.getMessage());
        assertEquals("ERROR", updatedResult.getStatus());
        assertNull(updatedResult.getData());

        verify(categoriaRepository).findById(1);
        verify(categoriaRepository).findByNombre(anyString());
        // Verificar que no se llame al método guardar
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

}
