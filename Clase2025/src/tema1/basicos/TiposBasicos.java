package tema1.basicos;

import java.util.Date;

/** Clase de ejemplo de Java para revisar los tipos de datos primitivos
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class TiposBasicos {
	
	// Mundo STATIC - de clase - solo una vez - siempre
	
	public static final int ANYO_CREACION = 2023;
	public static int datoEstatico; // = 0;  // Este atributo es una variable que existe todo el tiempo del programa y solo una vez	
	
	// Método principal
	public static void main(String[] params) {
		// ANYO_CREACION = 5;  // No se puede cambiar una constante
		int datoEstatico;
		datoEstatico = 5;
		System.out.println( datoEstatico );
		System.out.println( TiposBasicos.datoEstatico );
		tiposPrimitivos( 3, '3' );
		expresiones();
		ambito(5);
		System.out.println( potencia(3) );
		System.out.println( potencia(3,4) );
		System.out.println( potencia(3L,4L) );
		System.out.println( potencia(3, 4L) );
		return;
	}

	// Revisión de tipos primitivos
	public static void tiposPrimitivos( int par1, char par2 ) { 
		// Hay 8 tipos primitivos
		// 4 son numéricos enteros
		byte varByte1;  // Declaración - ámbito del método en el que se declara
			// byte = 8 bits = números en rango -128 a 127
		varByte1 = 5;
		// varByte1 = 129;  // NO cabe 129 en un byte
		short varShort;  // 2 bytes -32768 a 32767
		int varInt; // 4 bytes 
		System.out.println( "Valor menor de int: " + java.lang.Integer.MIN_VALUE );  // Sacar a consola los valores mínimo y máximo de un int
		System.out.println( "Valor mayor de int: " + Integer.MAX_VALUE );
		long varLong; // 8 bytes
		varLong = 12345678901L;  // L sufijo para literal entero LONG (los literales enteros por defecto son int)
		// int varintAVerSiCabe = varLong;  No se puede porque no cabe
		long varLong2 = 1L;
		int varIntAVerSiCabe = (int) varLong;  // Conversión explícita
		System.out.println( "Valor de 12345678901L = " + varLong );
		varLong = 12_345_678_901L;  // Los subrayados es como si no estuvieran (pueden marcar por ejemplo miles, para lectura)
		System.out.println( "Valor de 12_345_678_901L = " + varLong );
		System.out.println( "Valor menor de long: " + Long.MIN_VALUE );  // A consola los long mínimo y máximo
		System.out.println( "Valor mayor de long: " + Long.MAX_VALUE );
		System.out.println( "Hora actual en milis: " + System.currentTimeMillis() );
		System.out.println( "Fecha actual: " + new Date( System.currentTimeMillis() ) );
		System.out.println( "Fecha máxima: " + new Date( Long.MAX_VALUE ) );
		// Las declaraciones pueden ser con inicialización (se hace a menudo, es muy cómodo)
		int varInt2 = 17;
		
		// 2 tipos son numéricos reales
		float f1 = 3.0F; // otra manera (float) 3.2;  // Por defecto 3.2 es double   (4 bytes)
		double d1 = 3.2;  // (8 bytes)
		d1 = 0.25E5;  // Literal en formato exponencial: significa 0,25 * 10^5
		float  f2 = (float) d1;  // conversión explícita
		System.out.println( "Valor menor de float: " + Float.MIN_VALUE );
		System.out.println( "Valor mayor de float: " + Float.MAX_VALUE );
		System.out.println( "Valor menor de double: " + Double.MIN_VALUE );
		System.out.println( "Valor mayor de double: " + Double.MAX_VALUE );
		// REALES: NO COMPARAR CON ==  !=
		// Cómo comparar dos doubles?  aproximar en los decimales necesarios
		double double1 = 5.0031;
		double double2 = 5.0032;
		// if (double1==double2) {
		System.out.println( double1 + " y " + double2 + " son iguales? " + (double1==double2) );
		if (Math.abs(double2-double1) < 0.001) {
			System.out.println( double1 + " y " + double2 + " son *aproximadamente* iguales");
		}
		// REALES: NO HAY ERRORES DE VALOR EN EJECUCIÓN
		System.out.println( "División por cero real: " + (5.0/0.0) );
		
		// Los últimos 2 tipos primitivos:
		boolean varLogica = true; // true y false, solo hay dos literales
		boolean varLogica2 = (5 < 6) || (4 >= 8);  // Expresiones lógicas y de comparación aritmética devuelven boolean
			// Operadores de comparación: <  >  <=  >=  ==  !=
			// Operadores lógicos 
			// !    Unario postfijo, negación
			// && (and) y || (or)    Binarios
		char car = '5';  // char entre comillas simples y solo un car
		char car2 = 'A' + 1;  // Se pueden sumar y restar valores char, a veces es útil (hay conversión desde byte o short)
		System.out.println( "Carácter 'A' + 1 = " + car2 );
		System.out.println( "Carácter 'A' + 32 = " + (char)('A'+32) );
	}

	// Un poquito de expresiones:
	public static void expresiones() {
		// Aritméticas
		int i = 2 * 5;
		int j = i + i * 3;  // Precedencias (* antes que +)
		int k = (i + j) * 3;
		long largo = 5L + k;  // conversión implícita
		int noLargo = (int) (largo + 7);  // conversión explícita
		k++;
		// Precedencia general de operadores:
		// ++ -- !      (los de mayor precedencia - los primeros que se ejecutan)
		// * / %
		// + -          producto antes que suma
		// > < >= <=
		// == !=
		// &&           AND (&&) antes que OR (||)
		// ||
		// =            (el de menor precedencia - el último que se ejecuta)
		
		// Booleanas
		boolean logico = !(i < j) && (5 >= i || 3 >= j);
	}
	
	
	public static void ambito(int par1) {
		// Cuántos ámbitos hay? Dos principales
		// - Todas las vabiables locales de este método, más los parámetros (ámbito de método)
		// - Los atributos de la clase (static en este caso)
		int varInt = 3; // Atención al nombre: esta vble y el parámetro son diferentes a los de tiposPrimitivos aunque se llamen igual
		int datoEstatico = 5; // Se declara una variable que se llama igual que el atributo... "oculta" al atributo
		datoEstatico *= 7;  // Esto accede a la vble local, no al atributo
		TiposBasicos.datoEstatico = 7; // Se puede seguir accediendo al atributo, con el PREFIJO DE CLASE (al ser static)
		System.out.println( datoEstatico );  // Esto es de nuevo la local (sin prefijo)
		
		// Se pueden hacer ámbitos particulares más internos con bloques dentro de bloques, por ejemplo:
		{
			byte varByte2; // Declaración - ámbito del bloque en el que se declara
		}
		// varByte2 = 1;  // Fuera del bloque no se puede acceder
		
		// Las estructuras de control que introducen bloque funcionan también como bloques con variables internas
		// Por ejemplo el for con contador:
		for (int i=0; i<20; i++) {
			// Código de ejemplo
		}
		// i = 5;  // Fuera del bloque no tiene ámbito		
	}

	// Ejemplo de dos métodos sobrecargados. Se llaman igual pero tienen distinta signatura (diferentes parámetros)
	public static long potencia( int base, int exponente ) {
		long resul = 1;
		for (int i=0; i<exponente; i=i+1) {
			resul *= base;
		}
		int i=0;
		while (i<exponente) {
			int j = 5;
			resul *= base;
			i=i+1;
			System.out.println( j );
		}
		System.out.println( i );
		return resul;
	}
	
	public static long potencia( long base, long exponente ) {
		return (long) Math.pow(base, exponente);
	}
	
	public static long potencia( int base ) {
		// return Math.pow(dato, dato);
		boolean esError = base>0;
		if (esError) {
			return base*base;
		} else {
			return 0;
		}
	}

	
	
	// Mundo NO STATIC - de objetos - ninguna, una, o muchas veces - solo cuando se crean objetos
	// (En este ejemplo no lo utilizamos porque no creamos objetos)
	
	public int atributoEntero;  // Atributo de la clase
	public void metodoDePrueba(int datoHabil) {  // Parámetro del método
		this.atributoEntero = 3;
		System.out.println( "Hola mundo" );
	}

}
