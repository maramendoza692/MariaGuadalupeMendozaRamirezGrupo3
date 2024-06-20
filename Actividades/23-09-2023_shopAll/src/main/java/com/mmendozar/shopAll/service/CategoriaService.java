package com.mmendozar.shopAll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmendozar.shopAll.model.Categoria;
import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.dto.CategoriaDTO;
import com.mmendozar.shopAll.repository.CategoriaRepository;
import com.mmendozar.shopAll.repository.ProductoRepository;
import com.mmendozar.shopAll.utils.Constantes;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    // --------- Consultar Todos ---------
    public Response<Categoria> consultarTodasCategorias() {
        Response<Categoria> response = new Response<>();
        List<Categoria> listaCategorias = categoriaRepository.findAll();

        if (listaCategorias.isEmpty()) {
            response.setMessage(Constantes.NO_HAY_RESULTADOS);
        } else {
            response.setMessage(Constantes.CONSULTA_CORRECTA);
        }

        response.setStatus("OK");
        response.setList(listaCategorias);
        response.setCount(listaCategorias.size());

        return response;
    }

    // --------- Guardar ---------
    public Response<Categoria> guardarCategoria(CategoriaDTO categoriaDTO) {
        Response<Categoria> response = new Response<>();

        Categoria categoriaNueva = new Categoria();
        categoriaNueva.setNombre(categoriaDTO.getNombre()); 

        Optional<Categoria> optionalCategoria = categoriaRepository.findByNombre(categoriaNueva.getNombre());

        if (optionalCategoria.isPresent()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.EXISTENTE);
            response.setData(null);
            return response;
        }

        categoriaRepository.save(categoriaNueva);

        response.setStatus("OK");
        response.setMessage(Constantes.GUARDADO_CORRECTAMENTE);
        response.setData(categoriaNueva);

        return response;
    }


    // --------- Eliminar ---------
    public Response<Integer> eliminarCategoria(Integer idCategoria) {
        Response<Integer> response = new Response<>();

        // Verificar si hay productos asociados a esta categoría
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(idCategoria);

        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();

            
            List<Producto> productos = productoRepository.findByIdCategoria(categoria);

            if (!productos.isEmpty()) {
                // Si hay productos, no eliminar la categoría
                response.setStatus("ERROR");
                response.setMessage(Constantes.CATEGORIA_CON_PRODUCTOS);
                response.setData(idCategoria);
                return response;
            }
        }

        // Si no hay productos asociados, eliminar la categoría
        categoriaRepository.deleteById(idCategoria);

        response.setStatus("OK");
        response.setMessage(Constantes.ELIMINADO_CORRECTAMENTE);
        response.setData(idCategoria);

        return response;
    }


    // --------- Actualizar ---------
    public Response<Categoria> actualizarCategoria(Integer idCategoria, CategoriaDTO categoriaDto) {
        Response<Categoria> response = new Response<>();

        Optional<Categoria> optionalCategoriaExistente = categoriaRepository.findById(idCategoria);

        if (optionalCategoriaExistente.isPresent()) {
            Categoria categoriaExistente = optionalCategoriaExistente.get();

            // Verificar si existe otra categoría con el mismo nombre
            Optional<Categoria> optionalCategoria = categoriaRepository.findByNombre(categoriaDto.getNombre());

            if (optionalCategoria.isPresent() && !optionalCategoria.get().equals(categoriaExistente)) {
                // Si ya existe otra categoría con el mismo nombre, no actualizar
                response.setStatus("ERROR");
                response.setMessage(Constantes.EXISTENTE);
                response.setData(null);
            } else {
                // Actualizar la categoría existente
                categoriaExistente.setNombre(categoriaDto.getNombre());

                Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);

                response.setStatus("OK");
                response.setMessage(Constantes.ACTUALIZADO_CORRECTAMENTE);
                response.setData(categoriaActualizada);
            }
        } else {
            // La categoría con el id especificado no existe
            response.setStatus("ERROR");
            response.setMessage(Constantes.CATEGORIA_NO_EXISTENTE);
        }

        return response;
    }

}
