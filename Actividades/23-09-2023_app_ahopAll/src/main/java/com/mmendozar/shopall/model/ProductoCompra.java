package com.mmendozar.shopall.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Productos_Compra")
@Getter
@Setter
public class ProductoCompra implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7783714922325754361L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_comp_id")
	private Integer idProductoCompra;
	
	@Column(name = "cantidad")
	private Integer cantidad;

	@Column(name = "total")
	private Double total;
	 
	@MapsId("idCompra")
	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;

	@MapsId("idProducto")
	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	 

}
