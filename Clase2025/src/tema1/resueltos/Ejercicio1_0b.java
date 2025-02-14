package tema1.resueltos;

public class Ejercicio1_0b {
	public static void main(String[] args) {
		System.out.println( mcd( 14, 28 ) );
		System.out.println( mcd( 7, 28 ) );
		System.out.println( mcd( 15, 25 ) );
		System.out.println( mcd( 17, 28 ) );
	}
	
	public static int mcd( int num1, int num2 ) {
		for (int divisor=Math.min(num1, num2); divisor>1; divisor--) {
			if (num1%divisor == 0 && num2%divisor == 0) {
				return divisor;
			}
		}
		return 1;
	}
	
}
