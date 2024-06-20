package com.mmendozar.shopAll.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmendozar.shopAll.exceptions.EntityNotFoundException;
import com.mmendozar.shopAll.model.Compra;
import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.ProductoCompra;
import com.mmendozar.shopAll.model.ProductosCompraId;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.CompraDTO;
import com.mmendozar.shopAll.model.dto.ProductoAddDTO;
import com.mmendozar.shopAll.repository.CompraRepository;
import com.mmendozar.shopAll.repository.ProductoCompraRepository;
import com.mmendozar.shopAll.repository.ProductoRepository;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

import jakarta.transaction.Transactional;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProductoCompraRepository productoCompraRepository;
	
	// --------- Consultar Todas ---------
	public Response<Compra> consultarTodos(){
		
		Response<Compra> response = new Response<Compra>();
		List<Compra> lista = compraRepository.findAll();
		
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
	
	// ---------- Generar compra --------
	@Transactional
	public Response<Compra> generarCompra( CompraDTO compraDto) {
		
		Response<Compra> response = new Response<Compra>();
		
		Compra compra = null;
		Compra compraHeader = new Compra();
		compraHeader.setFecha(new Date());
		compraHeader.setCantidad(compraDto.getProductos().size());
		//Buscar al usuario para asignarlo a la compra
		Usuario comprador = usuarioRepository.findById(compraDto.getIdUsuario())
				.orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));
		compraHeader.setIdComprador(comprador);
		double totalCompra = 0.0;
		
		for(ProductoAddDTO productoJson : compraDto.getProductos()) {
			//Por cada id encontrado, ir  a la BD por el objeto producto
			Producto productoBD =productoRepository.findById(productoJson.getIdProducto())
					.orElseThrow(() -> new EntityNotFoundException("El producto con id "+productoJson.getIdProducto()+" no existe."));
			//multiplicar el precio del producto por la cantidad
			totalCompra += productoBD.getPrecio() * productoJson.getCantidad();
			//Actualizar
			productoJson.setPrecioUnitario(productoBD.getPrecio());
			productoJson.setTotal(productoBD.getPrecio() * productoJson.getCantidad());
		}
		
		//Asignar el total a la compra
		compraHeader.setTotal(totalCompra);;
		//Guardar la compra
		compra = compraRepository.save(compraHeader);
		//Guardar los productos en la tabal relacional producto_compra
		for(ProductoAddDTO productoJson :compraDto.getProductos()) {
			//Buscar el producto en la bd
			Producto productoBd = productoRepository.findById(productoJson.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("El producto con id "+productoJson.getIdProducto()+" No existe."));
			ProductoCompra productoCompra = new ProductoCompra();
			//Crear un id compuesto
			ProductosCompraId id = new ProductosCompraId();
            id.setIdCompra(compraHeader.getIdCompra());
            id.setIdProducto(productoBd.getIdProducto());
            productoCompra.setIdProdCompra(id);
            //Guardar la entidad producto y la entidad compra
            productoCompra.setCompra(compraHeader);
            productoCompra.setProducto(productoBd);
            //Guardar datos calculados
            productoCompra.setCantidad(productoJson.getCantidad());
            productoCompra.setPrecio(productoJson.getPrecioUnitario());
            productoCompra.setTotal(productoBd.getPrecio() * productoJson.getCantidad());
            // Guardar la relación ProductosCompra en la base de datos
            productoCompraRepository.save(productoCompra);
		}
		//Asignamos los valores al dtro para retroalimentar al usuario en al compra
        compraDto.setIdCompra(compraHeader.getIdCompra());
        compraDto.setTotal(compraHeader.getTotal());
        compraDto.setCantidad(compraHeader.getCantidad());
        compraDto.setFecha(compraHeader.getFecha());
        
		
		
        response.setStatus("OK");
	    response.setMessage(Constantes.MENSAJE_EXITO_COMPRA);
	    response.setData(compra);
		return response;
		
	}
	
	
}
