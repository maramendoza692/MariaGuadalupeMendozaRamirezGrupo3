package com.mmendozar.shopAll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmendozar.shopAll.model.Categoria;
import com.mmendozar.shopAll.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer>{

	List<Producto> findByIdCategoria(Categoria idCategoria);
	
	Optional<Producto> findByNombre(String nombre);
}
