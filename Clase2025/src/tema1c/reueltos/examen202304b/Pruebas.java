package tema1c.reueltos.examen202304b;

import java.awt.Color;
import java.awt.Point;

import tema1c.reueltos.examen202304b.gui.VentanaGrafica;

/** Clase de pruebas del ejercicio de gestión de tareas
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Pruebas {
	public static void main(String[] args) {
		// ObjetoGT o = new ObjetoGT( 100, 100, new VentanaGrafica( 1000, 800, "Prueba" ) );  // No se puede instanciar
		// pruebaTareas();
		pruebaTrabajadores();
	}
	
	@SuppressWarnings("unused")
	private static void pruebaTareas() {
		VentanaGrafica v = new VentanaGrafica( 1000, 800, "Prueba de tareas" );
		Tarea t1 = new Tarea( 200, 200, v, "Prueba tarea", Color.ORANGE );
		t1.dibujar();
		Tarea t2 = new Tarea( v );  // Tareas por defecto
		Tarea t3 = new Tarea( v );
		t2.mover( 400,  200 );
		t3.mover( 400,  400 );
		t2.dibujar();
		t3.dibujar();
		v.setMensaje( "Pulsa el botón para identificar si haces click en una tarea" );
		Tarea tareas[] = new Tarea[] { t1, t2, t3 };
		while (!v.estaCerrada()) {
			Point p = v.getRatonPulsado();
			if (p!=null) {
				boolean pulsada = false;
				for (Tarea t : tareas) {
					if (t.contienePunto( p.x, p.y )) {
						pulsada = true;
						v.setMensaje( "Pulsada la tarea " + t + " en " + p );
						break;
					}
				}
				if (!pulsada) {
					v.setMensaje( "No se ha pulsado ninguna tarea en " + p );
				}
			}
			v.espera( 20 );
		}
	}
	
	private static void pruebaTrabajadores() {
		VentanaGrafica v = new VentanaGrafica( 1000, 800, "Prueba de trabajadores" );
		Trabajador t1 = new Trabajador( 200, 200, v, "Chris", "gui/chris.png" );
		t1.dibujar();
		Trabajador t2 = new Trabajador( v, "Julia", "gui/julia.png" );
		Trabajador t3 = new Trabajador( v, "Natalie", "gui/natalie.png" );
		t3.mover( 600,  400 );
		t2.dibujar();
		t3.dibujar();
		v.setMensaje( "Pulsa el botón para identificar si haces click en un trabajador" );
		Trabajador[] trabajadores = { t1, t2, t3 };
		while (!v.estaCerrada()) {
			Point p = v.getRatonPulsado();
			if (p!=null) {
				boolean pulsado = false;
				for (Trabajador t : trabajadores) {
					if (t.contienePunto( p.x, p.y )) {
						pulsado = true;
						v.setMensaje( "Pulsado el trabajador " + t + " en " + p );
						break;
					}
				}
				if (!pulsado) {
					v.setMensaje( "No se ha pulsado ningún trabajador en " + p );
				}
			}
			v.espera( 20 );
		}
	}

	
}
