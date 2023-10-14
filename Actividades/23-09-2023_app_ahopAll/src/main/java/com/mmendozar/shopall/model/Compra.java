package com.mmendozar.shopall.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Compras")
@Getter
@Setter
public class Compra implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5045415685876625344L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compra_id")
	private Integer idProducto;
	
	@Column(name = "monto_total")
	private Double total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comprador_id")
	private Usuario idComprador;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<ProductoCompra> productoCompra;
	
}
