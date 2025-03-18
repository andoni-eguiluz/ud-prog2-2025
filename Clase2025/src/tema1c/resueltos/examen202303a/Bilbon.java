package tema1c.resueltos.examen202303a;

import java.util.ArrayList;
import java.util.Random;

public class Bilbon {

//	Crea una clase Bilbon en el proyecto anterior que contenga un método main y haz lo siguiente dentro
//	de ese método:
//	a. Crea una lista de huespedes, una lista de anfitriones y una lista alojamientos (0.25 puntos).

	private static ArrayList<Huesped> listaHuespedes;
	private static ArrayList<Anfitrion> listaAnfitriones;
	private static ArrayList<Alojamiento> listaAlojamientos;

	public static void main(String[] args) {
		// Tarea a
		listaHuespedes = new ArrayList<>();
		listaAnfitriones = new ArrayList<>();
		listaAlojamientos = new ArrayList<>();
		
		crearAlojamientos();  // Tarea b
		crearPropietarios();  // Tarea c
		crearHuespedes();     // Tarea d
		confirmarReservas();  // Tarea e
	}
	
//	b. Añade a la lista de alojamientos 50 alojamientos con nombre correlativo (alojamiento-0,
//	alojamiento-1, etc.), sin descripción, precio aleatorio entre 50 y 150 euros la noche y con el
//	propietario a null (0.5 puntos).
	
	private static Random aleat = new Random();  // Variable para generar aleatorios
	
	private static void crearAlojamientos() {
		for (int i=0; i<50; i++) {
			String nombre = "alojamiento-" + i;
			int precio = 50 + aleat.nextInt( 101 );
			listaAlojamientos.add( new Alojamiento( null, nombre, "", precio ) );
		}
		System.out.println( listaAlojamientos );
	}
	
//	c. Añade a la lista de propietarios 50 anfitriones (anfitrion-0, anfitrion-1, etc.) y saldo aleatorio
//	entre 0 y 500 euros. Asigna al anfitrion-0 el alojamiento-0, al anfitrion-1 el alojamiento-1 y así
//	sucesivamente. Para asignar un alojamiento a un anfitrión es necesario hacer dos cosas: 1)
//	modificar el alojamiento para que su anfitrión sea ese y 2) añadir el alojamiento a la lista de
//	alojamientos del anfitrión (1.75 puntos).
	
	private static void crearPropietarios() {
		for (int i=0; i<50; i++) {
			String nombre = "anfitrión-" + i;
			int saldo = aleat.nextInt( 501 );
			ArrayList<Alojamiento> listaAls = new ArrayList<>();
			listaAls.add( listaAlojamientos.get( i ) );
			Anfitrion anf = new Anfitrion( nombre, nombre, saldo, listaAls );
			listaAlojamientos.get( i ).setAnfitrion( anf );
			listaAnfitriones.add( anf );
		}
		System.out.println( listaAnfitriones );
	}
	
	
//	d. Añade a la lista de huespedes 100 huéspedes (huesped-0, huesped-1, etc.) con un saldo
//	aleatorio entre 0 y 5000 euros y una lista de reservas que contenga 3 reservas. Para crear
//	estas reservas, elige un alojamiento al azar de la lista de alojamientos (calcula un número
//	aleatorio entre 0 y el size()-1 de la lista de alojamientos y obtén el alojamiento en esa
//	posición), define una duración de 3 días, no confirmada y con valoración aleatoria (2 puntos).
	
	private static void crearHuespedes() {
		for (int i=0; i<100; i++) {
			String nombre = "huesped-" + i;
			int saldo = aleat.nextInt( 5001 );
			ArrayList<Reserva> listaRes = new ArrayList<>();
			Huesped huesped = new Huesped( nombre, nombre, saldo, listaRes );
			for (int r=0; r<3; r++) {
				int alojAleatorio = aleat.nextInt( listaAlojamientos.size() );
				Reserva reserva = new Reserva( huesped, listaAlojamientos.get( alojAleatorio ), 3, false,
						TipoValoracion.values()[ aleat.nextInt( TipoValoracion.values().length ) ] );
				listaRes.add( reserva );
			}
			listaHuespedes.add( huesped );
		}
		System.out.println( listaHuespedes );
	}

//	e. Confirma todas las reservas del apartado anterior actualizando los saldos
//	correspondientemente (1 punto).	
	
	private static void confirmarReservas() {
		// Test (no lo pide el examen)
		System.out.print( "Saldos huéspedes ANTES:   " );
		for (Huesped h : listaHuespedes) {
			System.out.print( h.getSaldo() + " " );
		}
		// Fin test
		System.out.println();
		for (Huesped huesped : listaHuespedes) {
			for (Reserva reserva : huesped.getListaReservas()) {
				reserva.confirmar();
			}
		}
		// Test (no lo pide el examen)
		System.out.print( "Saldos huéspedes DESPUÉS: " );
		for (Huesped h : listaHuespedes) {
			System.out.print( h.getSaldo() + " " );
		}
		System.out.println();
		System.out.println( listaHuespedes );
		// Fin test
	}
	
}
