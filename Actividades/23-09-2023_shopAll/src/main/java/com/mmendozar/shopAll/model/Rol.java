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
@Table(name = "Roles")
@Getter
@Setter
public class Rol implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8819072575145798184L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
	private Integer idRol;

	@Column(name = "nombre")
	private String nombre;


}
