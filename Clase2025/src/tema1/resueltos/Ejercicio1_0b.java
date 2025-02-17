package tema1.resueltos;

/** Solución ejercicio 1.0b  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 */
public class Ejercicio1_0b {
	public static void main(String[] args) {
		int mcd = mcd(70, 28);
		System.out.println( "MCD 70, 28 = " + mcd );
		mcd = mcd(70, 28, 140);
		System.out.println( "MCD 70, 28, 140 = " + mcd );
		long mcdLong = mcd(70L, 28);
		System.out.println( "MCD 70L, 28 = " + mcdLong );
		mcdLong = mcd(70, 28L);
		System.out.println( "MCD 70, 28L = " + mcdLong );
		mcdLong = mcd(70L, 28L);
		System.out.println( "MCD 70L, 28L = " + mcdLong );
		mcdLong = mcd(70, 28, 140L);
		System.out.println( "MCD 70, 28, 140L = " + mcdLong );
	}
	
	/** Calcula y devuelve el máximo común divisor de dos números enteros
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int mcd( int num1, int num2 ) {
		System.out.println( "   (llamando a mcd de 2 ints)" );
		// Lógica para cálculo del MCD: 
		// Se podría recorrer de 2 al número buscando divisores comunes, y el último que encontremos es el mcd.
		// Si recorremos del número a 2 descendiendo, el primero que encontremos es el mcd y podemos directamente cortar:
		for (int divisor=Math.min(num1, num2); divisor>1; divisor--) {
			if (num1%divisor == 0 && num2%divisor == 0) {
				return divisor;  // Si encontramos un divisor común, es el MCD
			}
		}
		return 1; // Si no lo hemos encontrado, el MCD es 1
	}

	/** Calcula y devuelve el máximo común divisor de tres números enteros
	 * @param num1
	 * @param num2
	 * @param num3
	 * @return
	 */
	public static int mcd( int num1, int num2, int num3 ) {
		System.out.println( "   (llamando a mcd de 3 ints)" );
		for (int divisor=Math.min(Math.min(num1, num2), num3); divisor>1; divisor--) {
			if (num1%divisor == 0 && num2%divisor == 0 && num3%divisor == 0) {
				return divisor;
			}
		}
		return 1;
	}
	
	/** Calcula y devuelve el máximo común divisor de dos números enteros
	 */
	public static long mcd( long num1, long num2 ) {
		System.out.println( "   (llamando a mcd de 2 longs)" );
		for (long divisor=Math.min(num1, num2); divisor>1; divisor--) {
			if (num1%divisor == 0 && num2%divisor == 0) {
				return divisor;
			}
		}
		return 1;
	}
	
	/** Calcula y devuelve el máximo común divisor de tres números enteros
	 */
	public static long mcd( long num1, long num2, long num3 ) {
		System.out.println( "   (llamando a mcd de 3 longs)" );
		for (long divisor=Math.min(Math.min(num1, num2), num3); divisor>1; divisor--) {
			if (num1%divisor == 0 && num2%divisor == 0 && num3%divisor == 0) {
				return divisor;
			}
		}
		return 1;
	}

}
