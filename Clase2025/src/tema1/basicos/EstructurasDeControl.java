package tema1.basicos;

/** Ejemplos de las estructuras de control en Java
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EstructurasDeControl {

	public static void main(String[] args) {
		repetitivas();
		alternativas();
		ejemploIfsEnLinea();
		cortocircuito();
	}
	
	// Ejemplo estructuras repetitivas
	private static void repetitivas() {
		// Un bucle de 0 a 9 de varias maneras:
		// For con contador
		for (int i=0; i<10; i++) {
			System.out.print( " " + i );
		}
		// System.out.println( i ); // No se puede, i no tiene ámbito
		System.out.println();
		// Foreach  (recorre TODOS los elementos de una estructura secuencial -por ejemplo un array-)
		int[] valores = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i : valores) {
			System.out.print( " " + i );
		}
		System.out.println();
		// Repetitiva condicional de 0 a n (puede ejecutarse ninguna vez):
		int i=0;
		while (i<10) {
			System.out.print( " " + i );
			System.out.println( valores[i]);
			i++;
		}
		System.out.println();
		// Repetitiva condicional de 1 a n (se ejecuta siempre al menos una vez):
		//  (pregunta: ¿for es de 0 a n o de 1 a n?)
		i = 0;
		do {
			System.out.print( " " + i );
			i++;
		} while (i<10);
		System.out.println();

		// En cualquiera de los bucles se puede usar:
		// * break; para salir incondicionalmente
		// * continue; para volver al principio de la siguiente iteración
		// Ejemplo:
		// Recorrer hasta encontrar una condición de final (break)
		int[] valores2 = { 0, 1, 2, 3, 4, -5, 6, 7, 8, 9 };
		for (int j : valores2) {
			if (j<0) { break; }  // En cuanto encuentra este valor el bucle ya no sigue
			System.out.print( " " + j );
		}
		System.out.println();
		// Recorrer ignorando valores específicos (continue)
		for (int j : valores2) {
			if (j<0) { continue; }  // Estos valores no se procesan, pero el bucle sigue con los siguientes
			System.out.print( " " + j );
		}
		System.out.println();
		// Ojo con los bloques de una sentencia
		if (valores[0]==0)
			System.out.println( "otra cosa" );
			System.out.println( valores[1] );  // ¿está dentro del if?
	}
	
	private static void alternativas() {
		// Condicional simple
		int val = 5;
		if (val>=0) {
			System.out.println( "El valor es positivo" );
		}
		
		// Condicional compuesta
		if (val>=0) {
			System.out.println( "El valor es positivo" );
		} else {
			System.out.println( "El valor es negativo" );
		}
		
		// Condicional compuesta múltiple
		if (val>0) {
			System.out.println( "El valor es positivo" );
		} else if (val==0){
			System.out.println( "El valor es cero" );
		} else {
			System.out.println( "El valor es negativo" );
		}
		
		// Condicional múltiple (equivalente al if-else-else pero la condición debe ser un
		// valor único que se concreta en una lista de posibilidades
		switch (val) {   // expresión solo puede ser byte, short, int, char, String
			case 0: {
				System.out.println( "valor 0" );
				break; // Ojo al break!!!  (si no se pone sigue ejecutando las otras ramas)
			}
			case 1: {
				System.out.println( "valor 1" );
				break; // Idem
			}
			default: {  // Si no es ninguno de los anteriores
				System.out.println( "El valor no es ni 0 ni 1" );
			}
		}
	}
	
	/** Ejemplo del if ternario (if de línea) vs. if normal
	 */
	private static void ejemploIfsEnLinea() {
		// 1.- Retorno con if / else
		System.out.println( "|-7| = " + valorAbsoluto1( -7 ) );
		// 2.- Retorno con if en línea
		System.out.println( "|-7| = " + valorAbsoluto2( -7 ) );
	}

		// Implementación con if / else
		private static int valorAbsoluto1( int valor ) {
			if (valor>=0)
				return valor;
			else
				return -valor;
		}
		
		// Implementación con if en línea
		private static int valorAbsoluto2( int valor ) {
			return (valor>=0) ? valor : -valor;
			// sintaxis:    expresión-condicional ? expresión-valor1 : expresión-valor2
		}

	// Todas las expresiones booleanas se evalúan en cortocircuito
	private static void cortocircuito() {
		int dividendo = 5;
		int divisor = 0;
		// Observa dónde ocurre el error de ejecución y dónde no ocurre:
		if (divisor!=0 && dividendo/divisor > 0) {
			System.out.println( "La división es mayor que 0" );
		}
		if (dividendo/divisor > 0 && divisor!=0) {
			System.out.println( "La división es mayor que 0" );
		}
	}
	
		
}
