package com.mmendozar.shopAll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmendozar.shopAll.model.Categoria;
import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.ProductoDTO;
import com.mmendozar.shopAll.repository.CategoriaRepository;
import com.mmendozar.shopAll.repository.ProductoRepository;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	// --------- Consultar Todos ---------
	public Response<Producto> consultarTodos(){
		
		Response<Producto> response = new Response<Producto>();
		List<Producto> lista = productoRepository.findAll();
		
		if(lista.isEmpty()) {
			response.setMessage(Constantes.NO_HAY_RESULTADOS);
			
		}else {
			response.setMessage(Constantes.CONSULTA_CORRECTA);
		}
		response.setStatus("OK");
		response.setList(lista);
		response.setCount(lista.size());
		
		return response;
		
	}
	
	// --------- Guardar ---------
	public Response<Producto> guardarProducto(ProductoDTO productoDTO) {
	    Response<Producto> response = new Response<>();

	    Optional<Producto> optionalProducto = productoRepository.findByNombre(productoDTO.getNombre());

	    if (optionalProducto.isPresent()) {
	        response.setStatus("ERROR");
	        response.setMessage(Constantes.EXISTENTE);
	        return response;
	    }

	    Optional<Categoria> optionalCategoria = categoriaRepository.findById(productoDTO.getIdCategoria());
	    Optional<Usuario> optionalUsuario = usuarioRepository.findById(productoDTO.getIdVendedor());

	    if (!optionalCategoria.isPresent()) {
	        response.setStatus("ERROR");
	        response.setMessage(Constantes.CATEGORIA_NO_EXISTENTE);
	        return response;
	    }

	    if (!optionalUsuario.isPresent()) {
	        response.setStatus("ERROR");
	        response.setMessage(Constantes.USUARIO_NO_EXISTENTE);
	        return response;
	    }

	    Categoria categoria = optionalCategoria.get();
	    Usuario usuario = optionalUsuario.get();

	    Producto nuevoProducto = new Producto();
	    nuevoProducto.setNombre(productoDTO.getNombre());
	    nuevoProducto.setDescripcion(productoDTO.getDescripcion());
	    nuevoProducto.setCantDisponible(productoDTO.getCantDisponible());
	    nuevoProducto.setPrecio(productoDTO.getPrecio());
	    nuevoProducto.setIdCategoria(categoria);
	    nuevoProducto.setIdVendedor(usuario);

	    Producto productoGuardado = productoRepository.save(nuevoProducto);

	    response.setStatus("OK");
	    response.setMessage(Constantes.GUARDADO_CORRECTAMENTE);
	    response.setData(productoGuardado);

	    return response;
	}

	
	
	// --------- Eliminar ---------
	public Response<Integer> eliminarProducto(Integer idProducto) {
		Response<Integer> response = new Response<Integer>();
		productoRepository.deleteById(idProducto);
		
		response.setMessage(Constantes.ELIMINADO_CORRECTAMENTE);
		response.setStatus("OK");
		response.setData(idProducto);
		
		return response;
	}
	
	// --------- Actualizar ---------
	public Response<Producto> actualizarProducto(Integer idProducto, ProductoDTO productoDTO) {
	    Response<Producto> response = new Response<>();

	    Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
	    Optional<Categoria> optionalCategoria = categoriaRepository.findById(productoDTO.getIdCategoria());
	    Optional<Usuario> optionalUsuario = usuarioRepository.findById(productoDTO.getIdVendedor());

	    if (!optionalProducto.isPresent()) {
	        response.setStatus("ERROR");
	        response.setMessage(Constantes.PRODUCTO_NO_EXISTENTE);
	        return response;
	    }

	    if (!optionalCategoria.isPresent()) {
	        response.setStatus("ERROR");
	        response.setMessage(Constantes.CATEGORIA_NO_EXISTENTE);
	        return response;
	    }

	    if (!optionalUsuario.isPresent()) {
	        response.setStatus("ERROR");
	        response.setMessage(Constantes.USUARIO_NO_EXISTENTE);
	        return response;
	    }

	    Producto productoExistente = optionalProducto.get();
	    Categoria categoria = optionalCategoria.get();
	    Usuario usuario = optionalUsuario.get();

	    // Actualizar los campos del producto existente con la información proporcionada
	    productoExistente.setNombre(productoDTO.getNombre());
	    productoExistente.setDescripcion(productoDTO.getDescripcion());
	    productoExistente.setCantDisponible(productoDTO.getCantDisponible());
	    productoExistente.setPrecio(productoDTO.getPrecio());
	    productoExistente.setIdCategoria(categoria);
	    productoExistente.setIdVendedor(usuario);

	    try {
	        Producto productoActualizado = productoRepository.save(productoExistente);
	        response.setStatus("OK");
	        response.setMessage(Constantes.ACTUALIZADO_CORRECTAMENTE);
	        response.setData(productoActualizado);
	    } catch (Exception e) {
	        response.setStatus("ERROR");
	        response.setMessage(Constantes.ERROR);
	    }

	    return response;
	}


	
}
