package com.mmendozar.shopAll.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmendozar.shopAll.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

	Optional<Usuario> findByEmail(String email);
	
	Optional<Usuario> findByNombre(String nombre);
}
