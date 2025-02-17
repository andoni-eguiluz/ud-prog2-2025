package tema1.resueltos;

public class Recuento {
	/** Recuenta los números que aparecen en el vector y visualiza el conteo de cada número en consola
	 * @param vector	Vector cuyos valores hay que contar
	 */
	public static void recontar( int[] vector ) {
		for (int numero=1; numero<=50; numero++) {
			int contador = 0;
			for (int i=0; i<vector.length; i++) {
				if (numero == vector[i]) {
					contador++;
				}
			}
			if (contador != 0) {
				System.out.println( "Número " + numero + " aparece " + contador +
									(contador==1 ? " vez" : " veces") );
			}
		}
	}
	
	// TODO ¿Cómo se recontarían números cualesquiera (si no sabemos el rango de valores que hay en el vector)?
}
