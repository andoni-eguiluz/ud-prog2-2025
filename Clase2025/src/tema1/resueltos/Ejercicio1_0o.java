package tema1.resueltos;

import java.math.BigInteger;

// Crea un programa en Java que calcule el número de Fibonacci 100 usando BigInteger, 
// ya que los valores superan el límite de los enteros normales. 
// Haz también el factorial de 50 (100!) usando BigInteger. 
// Define métodos para ambos cálculos para poder calcular diferentes valores de fibonacci o factorial. 
// Observa las constantes para inicializar 0 y 1 de esta clase y los métodos para sumar y multiplicar.

public class Ejercicio1_0o {
	public static void main(String[] args) {
		// TODO medir tiempos de diferencia entre hacerlo con BigInteger y double
		// System.currentTimeMillis() --> tiempo en milisegundos (1/1/1970)
		//  System.nanoTime() --> tiempo en nanos
		long tiempoActual = System.nanoTime();
		System.out.println( fibonacci( 100 ) );
		System.out.println( "Ha tardado " + (System.nanoTime() - tiempoActual) + " nanosegundos." );
		
		System.out.println( factorial(200) );
	}
	
	/** Calcula el número de fibonacci de cualquier posición n usando BigInteger con toda la precisión
	 * @param n	Número de fibonacci a calcular, debe ser mayor que 1, entendiendo que fib(0) = 0 y fib(1) = 1
	 * @return	fibonacci(n)
	 */
	public static BigInteger fibonacci( int n ) {
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		for (int i=2; i <= n; i++) {
			BigInteger suma = a.add( b );
			a = b;
			b = suma;
		}
		return b;
	}
	
	public static BigInteger factorial( int n ) {
		BigInteger resultado = BigInteger.ONE;
		for (int i=2; i<=n; i++) {
			resultado = resultado.multiply( BigInteger.valueOf( i ) );
		}
		return resultado;
	}
	
}

// Diferencia de fibonacci 100 con doubles y BigInteger
// 354224848179262000000  (double)
// 354224848179261915075  (BigInteger)
