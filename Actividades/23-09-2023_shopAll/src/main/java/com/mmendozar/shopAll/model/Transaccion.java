package com.mmendozar.shopAll.model;
import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "Transacciones")
@Getter
@Setter
public class Transaccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6800307902914157203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaccion_id")
	private Integer idTransaccion;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "monto_total")
	private Double total;

	@ManyToOne()
    @JoinColumn(name = "compra_id")
	private Compra idCompra;
}