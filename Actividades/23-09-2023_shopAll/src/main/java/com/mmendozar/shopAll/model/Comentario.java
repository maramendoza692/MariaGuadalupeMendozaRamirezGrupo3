package com.mmendozar.shopAll.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "Comentarios")
@Getter
@Setter
public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3423592127697265050L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comentario_id")
	private Integer idComenatrio;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne( optional = false)
	@JoinColumn(name = "comprador_id", nullable = false)
	private Usuario idComprador;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto productoId;	
}