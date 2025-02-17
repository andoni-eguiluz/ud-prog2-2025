package tema1.resueltos;

/** Solución ejercicio 1.0d  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 */
public class Ejercicio1_0d {

	// Ejercicio 1.0.d
	// Define un método primo que informe si un número entero (recibido como parámetro) 
	// es primo o no. Utiliza ese método desde el main para visualizar en consola 
	// todos los números primos entre 1 y 100.
	
	public static void main(String[] args) {
		System.out.println( "Lista de números primos:" );
		for (int i=1; i<=100; i++) {
			if (primo(i)) {
				System.out.println( i );
			}
		}
	}

	private static boolean primo( int num ) {
		boolean esPrimo = true;
		for (int divisor=2; divisor<num; divisor++) {  // Un número es primo si no es divisible por ninguno de 2 al num-1
			if (num % divisor == 0) {
				esPrimo = false;
				break;
			}
		}
		return esPrimo;
		// Este returno es lo mismo que:
		//		if (esPrimo) {
		//			return true;
		//		} else {
		//			return false;
		//		}
	}	
	
}
