package com.mmendozar.shopAll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmendozar.shopAll.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

	Optional<Categoria> findByNombre(String nombre);
}
