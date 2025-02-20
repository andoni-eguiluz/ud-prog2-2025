package tema1.ejemplos;

import java.awt.Point;

/** Código de prueba de uso de primeros objetos (en vez de tipos primitivos)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class UsandoObjetos {
	
	public static void main(String[] args) {
		usandoPoints();
		usandoStrings();
	}
	
	private static void usandoPoints() {
		// java.awt.Point punto  es lo mismo que hacer el import
		Point punto1 = new Point( 5, 2 );  // La creación de objetos siempre se hace con NEW
		Point punto2 = new Point( 5, 8 );
		System.out.println( "Punto 1 = " + punto1 + " - punto 2 = " + punto2 );
		System.out.println( "Distancia = " + punto1.distance( punto2 ) );  // Los objetos tienen funcionalidades PROPIAS (cada uno las suyas)
			// Sintaxis objeto.método(...)
			// Métodos en vez de operadores
		punto1.translate( +3, +3 );
		System.out.println( "Punto 1 trasladado 3,3 = " + punto1 );

		// Los objetos tienen datos propios (cada uno los suyos) - sintaxis objeto.atributo
		System.out.println( "Las x de los 2 puntos son: " + punto1.x + " y " + punto2.x );
		
		muevemeEstePunto( punto1, -3, 8 );
		System.out.println( "¿Se ha movido Punto 1?  " + punto1 );
		// ¿¿QUÉ TIENE ESTO DE RARO??
		// Hemos podido cambiar un parámetro ¿NO SE COPIABAN?
		// Los objetos funcionan con referencias (ver dibujo)
		// Lo que se copia al pasar los parámetros (por valor) en Java son las referencias
		
		Point punto3 = null;  // Si no se inicializa y se usa - error de compilación 
			// las variables locales objetos deben inicializarse
			// los atributos objetos se inicializan por defecto con null
			// null es una referencia NULA, es como decir que la variable no referencia a NINGÚN objeto
		System.out.println( "El punto 3 está con valor... " + punto3 );
		
		creameEstePunto( punto3 );
		System.out.println( "Punto 3 nuevo creado: " + punto3 );
		// Sin embargo, el método NO PUEDE CAMBIAR nuestra referencia (recuerda: se COPIA)
		// Si el objeto está creado, lo puede consultar y modificar. Pero no puede CAMBIAR de objeto.
		// Si el objeto no está creado, no lo puede crear

		Point punto4 = punto2;  // Esto se llama aliasing
		System.out.println( "Puntos 2,4 = " + punto2 + " / " + punto4 );
		System.out.println( "Cambiamos el 4" );
		punto4.x = -3;
		System.out.println( "Puntos 2,4 = " + punto2 + " / " + punto4 );
		// ¿POR QUÉ ha cambiado el 2 además del 4?
		// Porque son el mismo. Solo hay un punto, referenciado por dos variables.
		// Cuidado con esto, hay que tener conciencia de cuántos objetos tenemos y cuántas variables los referencian. 
		// Con primitivos, variable = dato. Una variable, un dato. Dos variables, dos datos.
		// Con objetos, Variable no es lo mismo que dato. Una variable puede ser un dato o ninguno (null). Dos variables pueden referenciar a solo un dato.
		// Con objetos la variable es la referencia a un dato(objeto) (o a ninguno).
		
		// ¿Son iguales, ya puestos?
		System.out.println( "Valores 1-2-3-4: " + punto1 + " / " + punto2 + " / " + punto3 + " / " + punto4 );
		System.out.println( "punto1 == punto2? " + (punto1==punto2) );
		System.out.println( "punto4 == punto2? " + (punto4==punto2) );
		// ¿Por qué 1==2 es false?  == compara las REFERENCIAS de los objetos
		System.out.println( "punto1 equals punto2? " + (punto1.equals(punto2)) );
		System.out.println( "punto4 equals punto2? " + (punto4.equals(punto2)) );
		// equals es un método en todos los objetos y compara el CONTENIDO de los objetos

		// Y en ese tiempo en el que la variable tiene null en lugar de objeto asociado... qué peligro hay?
		// Por ejemplo intentemos visualizar el punto si está en coordenadas positivas
		// Código incorrecto
//		if (punto3.getX() >= 0 && punto3.getY() >= 0) {
//			System.out.println( punto3 );
//		}
		// ERROR DE EJECUCIÓN!!!  NullPointerException
		// Recordáis la "cláusula de protección" del && con falso (en cortocircuito)?
		// Código correcto:
		if (punto3!=null && punto3.getX() >= 0 && punto3.getY() >= 0) {
			System.out.println( punto3 );
		}
		
		// Variables indefinidas:
		Point punto5;  // Variables locales pueden estar indefinidas
		// System.out.println( punto5 );  // Java no deja utilizarlas (error de compilación)

		// Atributos - no pueden ser indefinidos:
		System.out.println( punto6 );
		
		// Objetos que se quedan sin referencia (se pierden)
		new Point(8,3);   // Objeto perdido en el limbo de la memoria
	}
	
	static Point punto6;  // Atributo - Java inicializa a null
	
	
	/** Mueve un punto
	 * @param punto	Punto a mover
	 * @param x	Nueva coordenada x del punto (puede ser positiva o negativa)
	 * @param y	Nueva coordenada y del punto (ídem)
	 */
	private static void muevemeEstePunto( Point punto, int x, int y ) {
		System.out.println( "Punto original: " + punto );
		punto.setLocation( x, y );
		System.out.println( "Punto movido: " + punto );
	}
	
	private static void creameEstePunto( Point punto ) {
		punto = new Point( -4, 2 );
	}


	private static void usandoStrings() {
		String string1 = "Hola";  // Es lo mismo que new String("Hola"). Con los Strings Java nos ayuda
		// (Ahh, por eso String empieza en mayúscula)
		String string2 = new String("Adiós");
		System.out.println( "String 1 = " + string1 + " / String 2 = " + string2 );
		
		// También tiene métodos
		System.out.println( string1.charAt(2) );
		System.out.println( string1.toUpperCase() );
		System.out.println( "String 1 = " + string1 );  // El toUpperCase devuelve un String nuevo, no cambia el que ya había

		cambiameEsteString( string1, "Cambia" );
		System.out.println( "String 1 = " + string1 );
		// No cambia... porque String es INMUTABLE (muy pocas clases lo son)
	}
	
	private static void cambiameEsteString( String miString, String nuevo ) {
		miString = nuevo;
		
		// ... y es que no hay métodos que cambien
		miString.replaceAll( "Adiós", nuevo );   // ¿parece que cambia?
		// No cambia el string, crea nuevo. Con los objetos strings se están creando nuevos todo el rato
		System.out.println( miString + " - Y" + " esto" + " qué es?" );
		// String nuevo creado en caliente (que en este caso no se guarda en ningún sitio)
		
		// String es una clase que crea objetos INMUTABLES (no hay acceso a atributos, ningún método cambia internamente el string)
		// Point eran mutables
		// La mayor parte de los objetos son mutables
	}	
	
}