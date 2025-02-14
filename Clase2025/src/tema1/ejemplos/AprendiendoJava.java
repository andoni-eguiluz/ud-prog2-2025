package tema1.ejemplos;

public class AprendiendoJava {

	public static void main(String[] args) {
		inicializar();
		verTodos();
		verHastaMayorQue( 25 );
		verHastaMayorQue( 47 );
		int mayor = 0;
		calcularElMayor( mayor );
		System.out.println( "Mayor: " + mayor );  // No funciona
		System.out.println( "Mayor: " + mayor() );
		// sumaimparesrestapares (devolviendo esa suma)
		System.out.println( "Suma impares-pares = " + sumaImparesRestaPares() );
		// visualizahastaqueunoseamenor
		visualizaHastaQueUnoSeaMenor();
		// Ejercicio 1.0 a-b-c-d  y  i-j-k-l
	}
	
	public static void visualizaHastaQueUnoSeaMenor() {
		int i=0;
		do {
			System.out.print( vector[i] + " " );
			i++;
		} while (i<vector.length && vector[i] > vector[i-1]);
	}
	
	/** Calcula la suma de todos los impares restando todos los pares
	 * @return	Resultado
	 */
	public static int sumaImparesRestaPares() {
		int suma = 0;
		for (int dato : vector) {
			if (dato % 2 == 1) {
				suma += dato;
			} else {
				suma -= dato;
			}
		}
		return suma;
	}

	/** Calcula el valor mayor del vector y lo devuelve
	 * @return	El valor mayor
	 */
	static int mayor() {
		int mayor = 0;
		for (int dato : vector) {
			if (dato > mayor) {
				mayor = dato;
			}
		}
		return mayor;
	}
		
	
	// Java pasa los parámetros por VALOR (copia), no por REFERENCIA
	// el método NUNCA puede cambiar mis variables
	// Andoni nos está engañando???
	static void calcularElMayor( int mayor ) {
		for (int dato : vector) {
			if (dato > mayor) {
				mayor = dato;
				// System.out.println( "Cambio: " + mayor );
			}
		}
		// System.out.println( "Mayor total:" + mayor );
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
		while (indice < vector.length && vector[indice] <= umbralMayor) {
			System.out.print( vector[indice] + " " );
			indice++;
		}
		System.out.println();
	}

	
}
