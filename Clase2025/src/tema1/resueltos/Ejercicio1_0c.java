package tema1.resueltos;

/** Solución ejercicio 1.0c  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 */
public class Ejercicio1_0c {
	public static void main(String[] args) {
		ejercicioTablaMultiplicar();
	}
	
	private static void ejercicioTablaMultiplicar() {
		cabecera();
		for (int i=0; i<10; i++) {
			System.out.print( "Tabla de " + i ); 
			for (int j=0; j<10; j++) {
				String espacios = " ";
				if (i*j<10) espacios = "  ";
				System.out.print( espacios + i*j ); 
			}
			System.out.println();
		}
	}
		// Saca a consola la cabecera de la tabla de multiplicar
		private static void cabecera() {
			System.out.print( "          " );
			for (int j=0; j<10; j++) {
				System.out.print( "  " + j ); 
			}
			System.out.println();
			System.out.println( "           -----------------------------" );
		}


}
