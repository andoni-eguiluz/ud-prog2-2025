package tema1c.ejemplosInterfaces;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

/** Programa que gestiona figuras. Permite:
 * - Crear una figura nueva con click (eligiendo círculo, rectángulo, línea por defecto)
 *   en el punto del click
 * - Crear un círculo nuevo AZUL con ctrl+click en ese punto, pidiendo al usuario el radio
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class GestionFiguras {
	private static ArrayList<Figura> listaFigs = new ArrayList<>();
	private static VentanaGrafica vent = new VentanaGrafica( 600, 400, "Gestión de figuras" );
	
	public static void main(String[] args) {
		while (!vent.estaCerrada()) {
			Point click = vent.esperaAClick();
			// System.out.println( click );
			if (vent.getCodTeclaQueEstaPulsada() == KeyEvent.VK_CONTROL) {
				// Crear un círculo pidiendo el radio
				String resp = JOptionPane.showInputDialog( "Dame el radio:" );
				System.out.println( resp );
				// int radio = (int) resp;  // NOOOOOO
				int radio = Integer.parseInt(resp);
				Circulo c = new Circulo( click.x, click.y, Color.AZUL, radio );
				anyadirFigura( c );
			} else {
				// TODO
			}
		}
	}

	private static void anyadirFigura( Figura f ) {
		if (!listaFigs.contains(f)) {
			listaFigs.add( f );
			f.dibujar(vent);
			System.out.println( "Figura añadida: " + f );
		} else {
			JOptionPane.showMessageDialog( null, "Círculo repetido no se ha añadido" );
		}
	}
	
}
