package tema1.resueltos;

import java.awt.Dimension;

//[Uso de objetos] Declara un objeto de la clase Dimension (java.awt) para representar el tamaño de una pantalla
//con un ancho de 1920 píxeles y una altura de 1080 píxeles. Muestra en consola las dimensiones de la pantalla. 
//Modifica la dimensión para representar una pantalla de 1366x768 píxeles. Vuelve a mostrar las nuevas dimensiones en la consola. 
//Crea un método esPantallaGrande(Dimension d) que reciba un objeto Dimension y devuelva true si el ancho es mayor o igual a 1920 
//y el alto mayor o igual a 1080. Usa este método para verificar si la pantalla es grande antes y después del cambio de dimensiones.

public class Ejercicio1_0m {
	public static final int ANCHO_PANT_GRANDE = 1920;
	public static final int ALTO_PANT_GRANDE = 1080;
	
	public static void main(String[] args) {
		Dimension dim1 = new Dimension( 1920, 1080 );
		System.out.println( "Dimensiones de pantalla = " + dim1.getWidth() + "x" + dim1.getHeight() );
		System.out.println( "¿Es grande? " + (esPantallaGrande(dim1) ? "sí" : "no") );
		// dim1.width = 1366;   si lo hacemos con atributo
		dim1.setSize( 1366, 768 );  // Con un método los dos a la vez
		System.out.println( "Dimensiones de pantalla = " + dim1.getWidth() + "x" + dim1.getHeight() );
		System.out.println( "¿Es grande? " + (esPantallaGrande(dim1) ? "sí" : "no") );
	}
	
	/** Reciba un objeto Dimension e informa de si es grande,
	 * entendiendo como grande - constantes ANCHO_PANT_GRANDE y ALTO_PANT_GRANDE 
	 * @param d	dimensión recibida
	 * @return	true si es mayor o igual al tamaño grande 
	 */
	public static boolean esPantallaGrande( Dimension d ) {
		return d.getWidth() >= ANCHO_PANT_GRANDE && d.getHeight() >= ALTO_PANT_GRANDE;
	}
}
