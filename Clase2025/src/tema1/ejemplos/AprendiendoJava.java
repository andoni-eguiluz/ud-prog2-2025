package tema1.ejemplos;

public class AprendiendoJava {

	public static void main(String[] args) {
		inicializar();
		verTodos();
		verHastaMayorQue( 25 );
		verHastaMayorQue( 47 );
		// int mayor = 0;
		// calcularElMayor( mayor );
		// System.out.println( "Mayor: " + mayor );
		// System.out.println( "Mayor: " + mayor() );
		// sumaimparesrestapares
		// visualizahastaqueunoseamenor
	}

	static int[] vector;
	
	// Crea un vector nuevo de 20 posiciones y lo rellena con números aleatorios del 1 al 50
	
	/* Com. bloque
	 */

	
	/** Crea un vector nuevo de 20 posiciones y lo rellena con números aleatorios del 1 al 50
	 */
	static void inicializar() {
		vector = new int[20];
		for (int i=0; i<20; i++) {
			vector[i] = (int) (Math.random() * 50) + 1;
			// System.out.println( vector[i] );
		}
		// i = 5;   Fuera de ámbito
	}
	
	/** Saca el vector a consola en una sola línea separando los elementos con espacio
	 */
	static void verTodos() {
		for (int valor : vector) {
			System.out.print( valor + " " );
		}
		System.out.println();
	}
	
	/** Saca el vector a consola de izquierda a derecha hasta que algún valor sea mayor que el umbral
	 * en una sola línea separado por espacios
	 * @param umbralMayor
	 */
	static void verHastaMayorQue( int umbralMayor ) {
		int indice = 0;
		while (vector[indice] <= umbralMayor) {
			System.out.print( vector[indice] + " " );
			indice++;
		}
		System.out.println();
	}

	
}
