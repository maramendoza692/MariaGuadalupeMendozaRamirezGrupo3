package com.mmendozar.shopAll.service;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.mmendozar.shopAll.exceptions.EntityNotFoundException;
import com.mmendozar.shopAll.model.Compra;
import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.ProductoCompra;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.CompraDTO;
import com.mmendozar.shopAll.model.dto.ProductoAddDTO;
import com.mmendozar.shopAll.repository.CompraRepository;
import com.mmendozar.shopAll.repository.ProductoCompraRepository;
import com.mmendozar.shopAll.repository.ProductoRepository;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ComprasServiceTest {

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;


    @Mock
    private ProductoCompraRepository productoCompraRepository;

    @InjectMocks
    private CompraService compraService;

    private CompraDTO compraDto;
    private Usuario usuario;
    private Producto producto;

    @BeforeEach
    void setUp() {
        // Crear datos de prueba
        usuario = new Usuario();
        usuario.setIdUsuario(1);;

        producto = new Producto();
        producto.setIdProducto(1);
        producto.setPrecio(100.0);
        producto.setCantDisponible(10);

        ProductoAddDTO productoAddDTO = new ProductoAddDTO();
        productoAddDTO.setIdProducto(1);
        productoAddDTO.setCantidad(1);

        compraDto = new CompraDTO();
        compraDto.setIdUsuario(1);
        compraDto.setProductos(Arrays.asList(productoAddDTO));
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void generarCompraExitosa() {
        // Configurar comportamiento del mock
        when(usuarioRepository.findById(any(Integer.class))).thenReturn(Optional.of(usuario));
        when(productoRepository.findById(any(Integer.class))).thenReturn(Optional.of(producto));
        when(compraRepository.save(any(Compra.class))).then(i -> {
            Compra compra = i.getArgument(0);
            // Simula la generación de un ID para la compra
            compra.setIdCompra(1); // Asumiendo que el tipo del ID es Integer
            return compra;
        });


        // Ejecutar el servicio a probar
        Response<Compra> respuesta = compraService.generarCompra(compraDto);

        // Verificaciones y aserciones
        assertEquals(Constantes.MENSAJE_EXITO_COMPRA, respuesta.getMessage());
        assertNotNull(compraDto.getIdCompra());
        assertEquals(100.0 * 1, compraDto.getTotal());

        // Verificar interacciones con los mocks
        verify(compraRepository).save(any(Compra.class));
        verify(productoCompraRepository).save(any(ProductoCompra.class));
    }

    @Test
    void generarCompraConUsuarioInexistente() {
        // Configurar comportamiento del mock para lanzar la excepción
        when(usuarioRepository.findById(any(Integer.class))).thenThrow(new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE));

        // Ejecutar y verificar que se lanza la excepción
        assertThrows(EntityNotFoundException.class, () -> compraService.generarCompra(compraDto));
    }

    


}

