package com.mmendozar.shopAll.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Column(name = "compra_id", nullable = false)
	private Integer idCompra;

	@Column(name = "monto_total", nullable = false)
	private Double total;

	@ManyToOne(optional = false)
	@JoinColumn(name = "comprador_id", nullable = false)
	private Usuario idComprador;

	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "cantidad", nullable = false)
    private Integer cantidad;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<ProductoCompra> productoCompra;

}