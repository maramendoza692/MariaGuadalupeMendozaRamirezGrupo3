package com.mmendozar.shopAll.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Categorias")
@Getter
@Setter
public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1905918649987807801L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoria_id")
	private Integer idCategoria;

	@Column(name = "nombre", unique = true)
	private String nombre;

}