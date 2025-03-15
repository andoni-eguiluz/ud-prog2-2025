package tema1c.ejemplosInterfaces;

import java.awt.Font;
import java.util.ArrayList;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class PruebaFiguras {
	
	public static void main(String[] args) {
		Rectangulo r1 = new Rectangulo( 50, 25, 40, 30, Color.AZUL );
		System.out.println( r1 + " -> perímetro = " + r1.getPerimetro() );
		Circulo c1 = new Circulo( 20, 30, Color.AMARILLO, 15 );
		System.out.println( c1 + " -> perímetro = " + c1.getPerimetro());
		Linea l1 = new Linea( 10, 10, Color.ROJO, 200, Math.PI/4 );
		System.out.println( l1 + " -> perímetro = " + l1.getPerimetro());
		
		// No se puede en clases abstractas
//		Figura f1 = new Figura(10,10,Color.VERDE);
//		System.out.println( f1 + " -> perímetro = " + f1.getPerimetro());
		
		// Y... ¿cómo mover todas las figuras?
		r1.mover( 5, -5 );
		c1.mover( 5, -5 );
		l1.mover( 5, -5 );
//		f1.mover( 5, -5 );
		
		VentanaGrafica v = new VentanaGrafica( 800, 600, "Prueba figuras" );
		v.dibujaTexto( 200, 150, "Bienvenida/o!", new Font( "Arial", Font.PLAIN, 14 ), java.awt.Color.BLUE );
		r1.dibujar( v );
		c1.dibujar( v );
		l1.dibujar( v );
		
		Rectangulo r2 = new Rectangulo( 100, 120, 180, 33, Color.VERDE );
		r2.dibujar(v);
		
		ArrayList<Rectangulo> lR = new ArrayList<>();
		lR.add( r1 );
		lR.add( r2 );
		
		v.borra();
		for (Rectangulo r : lR) {
			r.dibujar(v);
		}
		// Lista de círculos
		// Lista de lineas 
		// ....
		
		// Y... ¿tiene sentido la figura f1?
		
		// Pues... ¿lo podemos hacer más generalizable?
	
		// Polimorfismo de datos
		Figura f = c1;
		f = r1;
		f = l1;
		f.dibujar( v );  // Polimorfismo de código
		
		ArrayList<Figura> lFiguras = new ArrayList<>();  // Lista polimórfica
		lFiguras.add( c1 );
		lFiguras.add( l1 );
		lFiguras.addAll( lR );
		lFiguras.add( new Circulo(10) );
		lFiguras.add( new Imagen( 250, 150, 200, 100, "logo-chatgpt.png" ));
		
		double sumPerims = 0;
		int sumRadios = 0;
		for (Figura fig : lFiguras) {
			System.out.println( "Figura " + fig );
			fig.dibujar(v);
			sumPerims += fig.getPerimetro();
			// sumRadios += fig.getRadio();
			if (fig instanceof Circulo) {
				System.out.println( "Círculo " + fig );
				Circulo circ = (Circulo) fig;  // 2 pasos
				sumRadios += circ.getRadio();
				// sumRadios += ((Circulo) fig).getRadio();  // 1 paso
			}
		}
		System.out.println( "Perímetro total: " + sumPerims );
		System.out.println( "Radio total: " + sumRadios );
		
		// Rotar todo lo rotable una vuelta completa
		// 100 veces roto 2PI / 100
		//v.getJFrame().setLocation( 2000, 100 );
		for (int i=0; i<100; i++) {
			v.borra();
			for (Figura fig : lFiguras) {
				// fig.rotar no puedo
//				if (fig instanceof Linea) {
//					((Linea)fig).rotar( 2*Math.PI/100.0 );
//					fig.dibujar(v);
//				} else if (fig instanceof Imagen) {
//					((Imagen)fig).rotar( 2*Math.PI/100.0 );
//					fig.dibujar(v);
//				}
				if (fig instanceof Rotable) {
					((Rotable)fig).rotar( 2*Math.PI/100.0 );
					fig.dibujar(v);
				}
			}
			v.espera( 50 );
		}
		
	}
	
}
