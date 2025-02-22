package tema1.resueltos;

import java.awt.Rectangle;

public class Ejercicio1_0n {

	// [Uso de objetos / aliasing] 
	// Declara un objeto Rectangle (java.awt) llamado rect1 con coordenadas (10, 10) y dimensiones 50x100 (anchura x altura). 
	// Declara otra variable rect2 y haz que apunte al mismo objeto que rect1. 
	// Modifica las dimensiones de rect2 a 200x400. 
	// Imprime las dimensiones de rect1 y rect2 después del cambio. Explica el comportamiento observado debido al aliasing.

	public static void main(String[] args) {
		Rectangle rect1 = new Rectangle( 10, 10, 50, 100);
		Rectangle rect2 = rect1;
		rect2.setSize( 200, 400 );
		System.out.println( "Rectángulo 1 = " + rect1 + " / rectángulo 2 = " + rect2 );
	}
	
}
