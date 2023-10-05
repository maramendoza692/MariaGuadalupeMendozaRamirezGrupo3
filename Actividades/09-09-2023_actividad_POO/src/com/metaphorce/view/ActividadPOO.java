package com.metaphorce.view;

import com.metaphorce.model.GestorPeliculas;
import com.metaphorce.model.Pelicula;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author maria
 */
public class ActividadPOO {

    public static void main(String[] args) {
        GestorPeliculas gestorPeliculas = new GestorPeliculas();
		
	//Inicializar la lista con algunas películas
        Pelicula pelicula1 = new Pelicula(1, "La ventajas de ser invisible", true);
        Pelicula pelicula2 = new Pelicula(2, "Avengers: Infinity War", false);
        Pelicula pelicula3 = new Pelicula(3, "Avengers: Endgame", false);

        gestorPeliculas.agregarPelicula(pelicula1);
        gestorPeliculas.agregarPelicula(pelicula2);
        gestorPeliculas.agregarPelicula(pelicula3);
        
        
        //Iniciar un ciclo y mostrar un cuadro de diálogo con las opciones disponibles
        while (true) {
            String opcion = JOptionPane.showInputDialog("Seleccione una opción:\n" +
                    "1. Agregar película\n" +
                    "2. Eliminar película por id\n" +
                    "3. Mostrar todas las películas\n" +
                    "4. Mostrar películas disponibles\n" +
                    "5. Mostrar películas no disponibles\n" +
                    "6. Marcar película como disponible\n" +
                    "7. Salir");
            //Switch para manejar la respuesta seleccionada
            switch (opcion) {
                case "1":

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id:"));
                	String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
                    boolean disponible = Boolean.parseBoolean(JOptionPane.showInputDialog("Ingrese true si la película está disponible o false si no lo está"));
                    Pelicula nuevaPelicula = new Pelicula(id, nombre, disponible);
                    gestorPeliculas.agregarPelicula(nuevaPelicula);
                    JOptionPane.showMessageDialog(null, "Película agregada correctamente.");
                    break;

                case "2":
                    int eliminarPorId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la película a eliminar:"));
                    gestorPeliculas.eliminar(eliminarPorId);
                    JOptionPane.showMessageDialog(null, "Película eliminada correctamente.");
                    break;

                case "3":
                    List<Pelicula> consultarTodas = gestorPeliculas.obtenerPeliculas();
                    mostrarPeliculas("Peliculas:", consultarTodas);
                    break;

                case "4":
                    List<Pelicula> peliculasDisponibles = gestorPeliculas.obtenerPeliculasDisponibles();
                    mostrarPeliculas("Películas disponibles:", peliculasDisponibles);
                    break;

                case "5":
                    List<Pelicula> peliculasNoDisponibles = gestorPeliculas.obtenerPeliculasNoDisponibles();
                    mostrarPeliculas("Películas no disponibles:", peliculasNoDisponibles);
                    break;

                case "6":
                    int marcarDisponible = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la película que desea marcar como disponible:"));
                    gestorPeliculas.marcarPeliculaComoDisponible(marcarDisponible);
                    JOptionPane.showMessageDialog(null, "Película marcada como disponible correctamente.");
                    break;

                case "7":
                    JOptionPane.showMessageDialog(null, "Gracias por usar mi pequeño Gestor de Peliculas c:");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Ingrese una opción disponible en el menú");
                    break;
            }
        }
    }
    
    //Método utilizado para mostrar las películas por su tipo, ya sean todas, disponibles o no disponibles
    private static void mostrarPeliculas(String tipo, List<Pelicula> peliculas) {
	String mensaje = tipo + "\n";

	for (Pelicula pelicula : peliculas) {
	    mensaje += "Id: " + pelicula.getId() + ", ";
	    mensaje += "Nombre: " + pelicula.getNombre() + ", ";
	    mensaje += "Disponibilidad: " + pelicula.isDisponible() + "\n";
	}

	JOptionPane.showMessageDialog(null, mensaje);
    }
}
