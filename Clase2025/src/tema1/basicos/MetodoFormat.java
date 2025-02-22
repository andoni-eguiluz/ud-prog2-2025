package tema1.basicos;

/** Ejemplos de método format de números en strings
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class MetodoFormat {

	// La clase format tiene un 
	public static void main(String[] args) {
		String mens = String.format( "Se puede maquetar un string incluyendo %s internos", "elementos" );
		System.out.println( mens );
		mens = String.format( "Los elementos %s y %s se referencian en el mismo orden", "uno", "dos" );
		System.out.println( mens );
		mens = String.format( "O se pueden cambiar poniendo su posición y $: %2$s y %1$s", "uno", "dos" );
		System.out.println( mens );
		mens = String.format( "Observa que tras la cadena de formateo se pueden indicar 'n' parámetros: %s, %s, %s, %s...", "uno", "dos", "tres", "cuatro" );
		System.out.println( mens );
		mens = String.format( "Los formateadores más habituales son 3. El primero es s para strings: %%s %s", "= string" );
		System.out.println( mens );
		System.out.println( "  (Observa cómo para indicar un % en el string de formato tienes que poner dos (%%)" );
		System.out.println( "Y cada formateador tiene modificadores." );
		mens = String.format( " Por ejemplo la longitud con un número n rellena a blancos %%10s -> '%10s'", "dato" );
		System.out.println( mens );
		mens = String.format( "El segundo es d para enteros decimales %%d -> '%d'", 15 );
		System.out.println( mens );
		mens = String.format( " Con modificador de longitud: %%10d -> '%10d'", 15 );
		System.out.println( mens );
		mens = String.format( " Rellenando a ceros con un 0 por delante: %%010d -> '%010d'", 15 );
		System.out.println( mens );
		mens = String.format( " Con . de miles: %%,10d -> '%,10d'", 1500000 );
		System.out.println( mens );
		mens = String.format( "Y el más interesante es f para números reales (flotantes) %%f -> '%f'", 2.5 );
		System.out.println( mens );
		mens = String.format( " Con modificador de longitud y decimales: %%7.2f -> '%7.2f'", 2.5 );
		System.out.println( mens );
		System.out.println( "  (Observa que redondea a los decimales que se indiquen)" );
		mens = String.format( " Con puntos de miles y coma decimal: %%,12.2f -> '%,12.2f'", 25000.0 );
		System.out.println( mens );
		mens = String.format( "Y por supuesto %s %d son combinables de %.1f maneras", "los", 3, 1000.1 );
		System.out.println( mens );
		System.out.println( "" );
		mens = String.format( "Tienes una descripción completa de las posibilidades de la cadena de formato en\n %s", "https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html#syntax" );
		System.out.println( mens );
	}

}
