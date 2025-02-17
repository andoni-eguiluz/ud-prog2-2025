package tema1.resueltos;

import java.util.Date;

/** Solución ejercicio 1.0a  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 */
public class Ejercicio1_0a {
	public static void main(String[] args) {
		tiposPrimitivos();
	}

	public static void tiposPrimitivos() { 
		// Hay 8 tipos primitivos
		// 4 son numéricos enteros
		byte varByte1;  // Declaración - ámbito del método en el que se declara
			// byte = 8 bits = números en rango -128 a 127
		varByte1 = 5;
		// varByte1 = 129;  // NO cabe 129 en un byte
		short varShort;  // 2 bytes -32768 a 32767
		varShort = 5;
		// varShort = 32768;  // NO cabe en un short
		int varInt; // 4 bytes 
		varInt = 32768;  // Sí cabe en 4 bytes
		System.out.println( "Entero mínimo: " + java.lang.Integer.MIN_VALUE );  // Sacar a consola los valores mínimo y máximo de un int
		System.out.println( "Entero máximo: " + Integer.MAX_VALUE );
		long varLong; // 8 bytes
		varLong = 12345678901L;  // L sufijo para literal entero LONG (los literales enteros por defecto son int)
		// int varintAVerSiCabe = varLong;  No se puede porque no cabe
		long varLong2 = 1L;
		int varIntAVerSiCabe = (int) varLong;  // Conversión explícita
		System.out.println( varLong );
		varLong = 12_345_678_901L;  // Los subrayados es como si no estuvieran (pueden marcar por ejemplo miles, para lectura)
		System.out.println( varLong );
		System.out.println( Long.MIN_VALUE );  // A consola los long mínimo y máximo
		System.out.println( Long.MAX_VALUE );
		System.out.println( "Ahora: " + System.currentTimeMillis() );
		System.out.println( new Date( System.currentTimeMillis() ) );
		System.out.println( new Date( Long.MAX_VALUE ) );
		// Las declaraciones pueden ser con inicialización (se hace a menudo, es muy cómodo)
		int varInt2 = 17;
		
		// 2 tipos son numéricos reales
		float f1 = 3.0F; // otra manera (float) 3.2;  // Por defecto 3.2 es double   (4 bytes)
		double d1 = 3.2;  // (8 bytes)
		float  f2 = (float) d1;  // conversión explícita
		System.out.println( Float.MIN_VALUE );
		System.out.println( Float.MAX_VALUE );
		System.out.println( Double.MIN_VALUE );
		System.out.println( Double.MAX_VALUE );
		// NO COMPARAR CON ==  !=
		// Cómo comparar dos doubles?  aprox en 3 decimales
		double double1 = 5.0031;
		double double2 = 5.0032;
		// if (double1==double2) {
		if (Math.abs(double2-double1) < 0.001) {
			System.out.println( "Son iguales");
		}
		
		// Los últimos 2 tipos primitivos:
		boolean varLogica = true; // false solo hay dos literales
		boolean varLogica2 = (5 < 6) || (4 >= 8);
		char car = '5';  // char entre comillas simples y solo un car
		char car2 = 'A' + 1;
		System.out.println( car2 );
		
		// Ejercicio 1.0a
		// Define ocho variables de cada uno de los tipos primitivos (byte, short, int, long, float, double, char, boolean). 
		// Comprueba en qué casos puedes pasar valores entre ellas y qué ocurre. 
		// Comprueba en qué casos no puedes pasar esos valores, y mira si en algún caso puedes hacerlo con conversión explícita (casting).
		
		varInt = varByte1;  // de byte a int o a short o a long sí
		varShort = varByte1;
		// varByte = varInt;  // Pero al revés (perdiendo rango) no
		varByte1 = (byte) varInt; // Se puede con conversión explícita
		// Pero ojo que se puede perder información!!! (si el valor no estaba en el rango -128 a 127 se pierde info)
		System.out.println( "Antes de convertir a byte: " + varLong );
		varByte1 = (byte) varLong;
		System.out.println( "Antes de convertir a byte: " + varByte1 );
		
		// Pasa lo mismo con enteros (menor precisión) a reales (mayor precisión)
		
		// Pasa lo mismo con floats (menor precisión) a doubles (mayor precisión)
	}

	// Ver tema1.basicos.TiposBasicos.java
	
}
