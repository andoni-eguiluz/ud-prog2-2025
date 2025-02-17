package tema1.resueltos;

/** Solución ejercicio 1.0j  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 */
public class Ejercicio1_0j {
	
	private static int TAMANYO_FIBS = 100; // Cambiar a 2000 si se quieren encontrar los límites de float y double
	public static void main(String[] args) {
		byte[] numsByte = new byte[TAMANYO_FIBS];
		short[] numsShort = new short[TAMANYO_FIBS];
		int[] numsInt = new int[TAMANYO_FIBS];
		long[] numsLong = new long[TAMANYO_FIBS];
		float[] numsFloat = new float[TAMANYO_FIBS];
		double[] numsDouble = new double[TAMANYO_FIBS];
		calculaFibs( numsByte );
		calculaFibs( numsShort );
		calculaFibs( numsInt );
		calculaFibs( numsLong );
		calculaFibs( numsFloat );
		calculaFibs( numsDouble );
		visualizaFibs(numsByte);
		visualizaFibs(numsShort);
		visualizaFibs(numsInt);
		visualizaFibs(numsLong);
		visualizaFibs(numsFloat);
		visualizaFibs(numsDouble);
	}
	
	private static void calculaFibs( byte[] numsFib ) {
		// Primeros números de fibonacci
		numsFib[0] = 1;
		numsFib[1] = 1;
		// Resto de números calculados
		for (int i=2; i<TAMANYO_FIBS; i++) {
			byte res = (byte) (numsFib[i-1] + numsFib[i-2]);
			if (res < numsFib[i-1]) {
				System.out.println( "Error en el cálculo de num. byte " + i );
				break;
			}
			numsFib[i] = res;
		}
	}
	
	private static void calculaFibs( short[] numsFib ) {
		// Primeros números de fibonacci
		numsFib[0] = 1;
		numsFib[1] = 1;
		// Resto de números calculados
		for (int i=2; i<TAMANYO_FIBS; i++) {
			short res = (short) (numsFib[i-1] + numsFib[i-2]);
			if (res < numsFib[i-1]) {
				System.out.println( "Error en el cálculo de num. short " + i );
				break;
			}
			numsFib[i] = res;
		}
	}
	
	private static void calculaFibs( int[] numsFib ) {
		// Primeros números de fibonacci
		numsFib[0] = 1;
		numsFib[1] = 1;
		// Resto de números calculados
		for (int i=2; i<TAMANYO_FIBS; i++) {
			int res = numsFib[i-1] + numsFib[i-2];
			if (res < numsFib[i-1]) {
				System.out.println( "Error en el cálculo de num. int " + i );
				break;
			}
			numsFib[i] = res;
		}
	}
	
	private static void calculaFibs( long[] numsFib ) {
		// Primeros números de fibonacci
		numsFib[0] = 1;
		numsFib[1] = 1;
		// Resto de números calculados
		for (int i=2; i<TAMANYO_FIBS; i++) {
			long res = numsFib[i-1] + numsFib[i-2];
			if (res < numsFib[i-1]) {
				System.out.println( "Error en el cálculo de num. long " + i );
				break;
			}
			numsFib[i] = res;
		}
	}
	
	private static void calculaFibs( float[] numsFib ) {
		// Primeros números de fibonacci
		numsFib[0] = 1;
		numsFib[1] = 1;
		// Resto de números calculados
		for (int i=2; i<TAMANYO_FIBS; i++) {
			float res = numsFib[i-1] + numsFib[i-2];
			if (res == Float.POSITIVE_INFINITY) {
				System.out.println( "Error en el cálculo de num. float " + i );
				break;
			}
			numsFib[i] = res;
		}
	}
	
	private static void calculaFibs( double[] numsFib ) {
		// Primeros números de fibonacci
		numsFib[0] = 1;
		numsFib[1] = 1;
		// Resto de números calculados
		for (int i=2; i<TAMANYO_FIBS; i++) {
			double res = numsFib[i-1] + numsFib[i-2];
			if (res == Double.POSITIVE_INFINITY) {
				System.out.println( "Error en el cálculo de num. double " + i );
				break;
			}
			numsFib[i] = res;
		}
	}
	
	private static void visualizaFibs( byte[] numsFib ) {
		System.out.println( "Fibonacci bytes: " );
		for (int i=0; i<TAMANYO_FIBS; i++) {
			if (numsFib[i]==0) {
				break;
			}
			System.out.print( numsFib[i] + " " );
		}
		System.out.println();
	}

	private static void visualizaFibs( short[] numsFib ) {
		System.out.println( "Fibonacci shorts: " );
		for (int i=0; i<TAMANYO_FIBS; i++) {
			if (numsFib[i]==0) {
				break;
			}
			System.out.print( numsFib[i] + " " );
		}
		System.out.println();
	}

	private static void visualizaFibs( int[] numsFib ) {
		System.out.println( "Fibonacci ints: " );
		for (int i=0; i<TAMANYO_FIBS; i++) {
			if (numsFib[i]==0) {
				break;
			}
			System.out.print( numsFib[i] + " " );
		}
		System.out.println();
	}

	private static void visualizaFibs( long[] numsFib ) {
		System.out.println( "Fibonacci longs: " );
		for (int i=0; i<TAMANYO_FIBS; i++) {
			if (numsFib[i]==0) {
				break;
			}
			System.out.print( numsFib[i] + " " );
		}
		System.out.println();
	}

	private static void visualizaFibs( float[] numsFib ) {
		System.out.println( "Fibonacci floats: " );
		for (int i=0; i<TAMANYO_FIBS; i++) {
			if (numsFib[i]==0) {
				break;
			}
			// System.out.print( numsFib[i] + " " );
			System.out.print( String.format( "%.0f ", numsFib[i] ) );
		}
		System.out.println();
	}
	private static void visualizaFibs( double[] numsFib ) {
		System.out.println( "Fibonacci doubles: " );
		for (int i=0; i<TAMANYO_FIBS; i++) {
			if (numsFib[i]==0) {
				break;
			}
			// System.out.print( numsFib[i] + " " );
			System.out.print( String.format( "%.0f ", numsFib[i] ) );
		}
		System.out.println();
	}
	
}
