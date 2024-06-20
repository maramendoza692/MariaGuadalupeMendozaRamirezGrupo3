package com.mmendozar.shopAll.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmendozar.shopAll.model.Comentario;
import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.ComentarioDTO;
import com.mmendozar.shopAll.repository.ComentarioRepository;
import com.mmendozar.shopAll.repository.ProductoRepository;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ProductoRepository productoRepository;


    // --------- Consultar Todos ---------
    public Response<Comentario> consultarTodosComentarios() {
        Response<Comentario> response = new Response<>();
        List<Comentario> listaComentarios = comentarioRepository.findAll();

        if (listaComentarios.isEmpty()) {
            response.setMessage(Constantes.NO_HAY_RESULTADOS);
        } else {
            response.setMessage(Constantes.CONSULTA_CORRECTA);
        }

        response.setStatus("OK");
        response.setList(listaComentarios);
        response.setCount(listaComentarios.size());

        return response;
    }

    // --------- Guardar ---------
    public Response<Comentario> guardarComentario(ComentarioDTO comentarioDto) {
        Response<Comentario> response = new Response<>();

        // Obtener el comprador y el producto asociados
        Optional<Usuario> optionalComprador = usuarioRepository.findById(comentarioDto.getIdComprador());
        Optional<Producto> optionalProducto = productoRepository.findById(comentarioDto.getIdProducto());

        if (!optionalComprador.isPresent()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.COMPRADOR_NO_EXISTENTE);
            response.setData(null);
            return response;
        }

        if (!optionalProducto.isPresent()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.PRODUCTO_NO_EXISTENTE);
            response.setData(null);
            return response;
        }

        Usuario comprador = optionalComprador.get();
        Producto producto = optionalProducto.get();

        Comentario comentario = new Comentario();
        comentario.setDescripcion(comentarioDto.getDescripcion());
        comentario.setFecha(new Date(System.currentTimeMillis()));
        comentario.setIdComprador(comprador);
        comentario.setProductoId(producto);

        Comentario comentarioNuevo = comentarioRepository.save(comentario);

        response.setStatus("OK");
        response.setMessage(Constantes.GUARDADO_CORRECTAMENTE);
        response.setData(comentarioNuevo);

        return response;
    }


    // --------- Eliminar ---------
    public Response<Integer> eliminarComentario(Integer idComentario) {
        Response<Integer> response = new Response<>();
        comentarioRepository.deleteById(idComentario);

        response.setStatus("OK");
        response.setMessage(Constantes.ELIMINADO_CORRECTAMENTE);
        response.setData(idComentario);

        return response;
    }

    // --------- Actualizar ---------
    public Response<Comentario> actualizarComentario(Integer idComentario, ComentarioDTO comentarioDto) {
        Response<Comentario> response = new Response<>();

        Optional<Comentario> optionalComentario = comentarioRepository.findById(idComentario);

        if (optionalComentario.isEmpty()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.COMENTARIO_NO_EXISTENTE);
            response.setData(null);
            return response;
        }

        Comentario comentarioExistente = optionalComentario.get();

        // Verificación del Producto
        Optional<Producto> optionalProducto = productoRepository.findById(comentarioDto.getIdProducto());
        if (optionalProducto.isEmpty()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.PRODUCTO_NO_EXISTENTE);
            response.setData(null);
            return response;
        }
        Producto producto = optionalProducto.get();

        // Verificación del Usuario
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(comentarioDto.getIdComprador());
        if (optionalUsuario.isEmpty()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.COMPRADOR_NO_EXISTENTE);
            response.setData(null);
            return response;
        }
        Usuario usuario = optionalUsuario.get();

        // Actualizar los campos del comentario existente con la información proporcionada
        comentarioExistente.setDescripcion(comentarioDto.getDescripcion());
        comentarioExistente.setFecha(comentarioDto.getFecha());
        comentarioExistente.setIdComprador(usuario);
        comentarioExistente.setProductoId(producto);

        Comentario comentarioActualizado = comentarioRepository.save(comentarioExistente);

        response.setStatus("OK");
        response.setMessage(Constantes.ACTUALIZADO_CORRECTAMENTE);
        response.setData(comentarioActualizado);

        return response;
    }


}
