package tema1b.ejemplosHerencia2;

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
		
		for (Figura fig : lFiguras) {
			fig.dibujar(v);
		}
		
	}
	
}
