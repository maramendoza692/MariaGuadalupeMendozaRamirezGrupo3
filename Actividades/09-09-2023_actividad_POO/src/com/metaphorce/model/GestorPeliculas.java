package com.metaphorce.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class GestorPeliculas {
    public List<Pelicula> peliculas = new ArrayList<>();
	
	//Agregar peliculas a la lista
	public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }
	
	//Eliminar una pelicula mendiante su id
	public void eliminar(int id) {
        peliculas.removeIf(pelicula -> pelicula.getId() == id);
    }
	 
	//Consultar todas
    public List<Pelicula> obtenerPeliculas() {
        return this.peliculas;
    }
    
    //Consultar películas disponibles 
    public List<Pelicula> obtenerPeliculasDisponibles() {
      return peliculas.stream()
              .filter(pelicula -> pelicula.isDisponible()== true).toList();
    }
    
    //Consultar películas no disponibles 
    public List<Pelicula> obtenerPeliculasNoDisponibles() {
      return peliculas.stream()
              .filter(pelicula -> pelicula.isDisponible()== false).toList();
    }
	 
    //Marcar pelicula como disponible
    public void marcarPeliculaComoDisponible(int id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                pelicula.setDisponible(true);
                break; //Fin del ciclo al encontrar la película y marcarla como disponible
            }
        }
    }
}
