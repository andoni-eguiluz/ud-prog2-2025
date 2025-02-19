package tema1.resueltos;

/** Solución ejercicio 1.0l  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 */
public class CreaVector {
	
	// Un vector de 100 enteros se crea con esta declaración: int[] vector = new int[100]; 
	// Haz una clase CreaVector cuyo método principal cree un vector de 100 enteros aleatorios 
	// con valor de 1 a 50 (El método Math.random() crea un valor aleatorio entre 0.0 y 0.99). 
	// Que ese método llame al método recontar() de otra clase Recuento, pasándole el vector. 
	// El recuento debe sacar el recuento de todos los números que aparezcan (indicando cuántas veces aparece cada número).
	
	public static void main(String[] args) {
		int[] vector = new int[20];
		CreaVector.inicializar( vector );
		Recuento.recontar( vector );
	}
	
	
	/** Crea un vector nuevo de 20 posiciones y lo rellena con números aleatorios del 1 al 50
	 */
	static void inicializar(int[] vector) {
		for (int i=0; i<20; i++) {
			vector[i] = (int) (Math.random() * 50) + 1;
		}
	}
	

}
