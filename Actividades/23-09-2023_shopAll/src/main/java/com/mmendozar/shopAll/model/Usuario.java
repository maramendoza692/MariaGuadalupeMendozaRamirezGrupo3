package com.mmendozar.shopAll.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
public class Usuario implements Serializable{

	private static final long serialVersionUID = -1610146741230052415L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Integer idUsuario;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	@Column(name = "ape_paterno", nullable = false, length = 45)
	private String apePaterno;

	@Column(name = "ape_materno", nullable = false, length = 45)
	private String apeMaterno;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToOne()
    @JoinColumn(name = "rol_id")
	private Rol idRol;
}