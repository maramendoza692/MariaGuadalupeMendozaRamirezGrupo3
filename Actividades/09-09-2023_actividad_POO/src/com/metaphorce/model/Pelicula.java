package com.metaphorce.model;

/**
 *
 * @author maria
 */
public class Pelicula {

	public int id;
	
	public String nombre;
	
	public boolean disponible;
	
	
	
	public Pelicula(int id, String nombre, boolean disponible) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.disponible = disponible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	
}