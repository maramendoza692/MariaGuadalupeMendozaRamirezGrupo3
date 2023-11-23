package com.mmendozar.shopAll.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
public class ProductoCompra {

	@EmbeddedId
    private ProductosCompraId idProdCompra;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	@Column(name = "monto_total", nullable = false)
	private Double total;
	
	@Column(name = "precio", nullable = false)
    private Double precio;


	@MapsId("idCompra")
	@ManyToOne
	@JoinColumn(name = "compra_id", nullable = false)
	private Compra compra;

	@MapsId("idProducto")
	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;


}