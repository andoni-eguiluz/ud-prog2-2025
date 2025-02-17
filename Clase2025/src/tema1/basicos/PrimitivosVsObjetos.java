package tema1.basicos;

import java.awt.Point;

/** Clase de prueba de objetos - comparativa con tipos primitivos y observación de las diferencias
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class PrimitivosVsObjetos {
	public static void main(String[] args) {
		primitivos();
		objetos();
		objetosString();
	}

	// En los primitivos todo se comporta según lo esperado
	private static void primitivos() {
		int num1 = 25;
		int num2 = num1;
		int num3 = 2*12+1;
		if (num1==num2) {
			System.out.println( "num1 = num2" );
		}
		if (num1==num3) {
			System.out.println( "num1 = num3" );
		}
		num2 = 38;
		System.out.println( "num1 = " + num1 );
		borrar( num3 );
		System.out.println( num3 );
	}
	private static void borrar( int num ) {
		num = 0;  // Ojo que cambiando un parámetro NO SE CAMBIA el dato original (se modifica su COPIA)
	}
	
	// En los objetos... no tanto
	private static void objetos() {
		Point punto1 = new Point(0,5);
		Point punto2 = punto1;
		Point punto3 = new Point(0,5);
		if (punto1==punto2) {
			System.out.println( "p1 == p2" );
		}
		System.out.println( "p1=" + punto1 + " / p3=" + punto3 );
		System.out.println( "p1 == p3 ? " + (punto1==punto3) );
		System.out.println( "p1 equals p3 ? " + punto1.equals(punto3) );
		// if (punto.x == punto2.x && punto.y == punto2.y) {  
		// Sería la misma pregunta pero en esta definimos la igualdad arbitrariamente desde FUERA de la clase Point
		// equals define la igualdad DENTRO de la clase Point
		punto3.x = 5; // Point es mutable, sus atributos están expuestos y se pueden cambiar
		punto3.y = 7;
		System.out.println( "p1 = " + punto1 );  // Al modificar p3 no se modifica p1 - son distintos objetos
		borrar( punto2 );
		System.out.println( "p2 tras borrar = " + punto2 );  // p2 ha cambiado tras pasarlo a un método
		System.out.println( "p1 = " + punto1 );  // y p1 también!  (son referencias alias - 2 nombres referencian a un único valor-objeto)
		// borrar( new Point(p2) ); ¿Cómo podríamos evitar acceso? Podríamos pasar una copia. Así es imposible que el método modifique a p2
	}
	
	private static void objetosString() {
		// String --> inmutable
		String string1 = new String( "hola prog2ers" ); // "hola prog2ers";
		String string2 = string1;
		String string3 = new String( "hola " ); //"hola ";
		string3 = new String( string3 + "prog2ers" ); //string3 += "prog2ers";
		if (string1==string2) {
			System.out.println( "s1 == s2" );
		}
		System.out.println( "s1=" + string1 + " / s3=" + string3 );
		if (string1==string3) {
			System.out.println( "s1 == s3" );
		}
		if (string1.equals(string3)) {
			System.out.println( "s1 equals s3" );
		}
		borrar( string3 );
		java.lang.System.out.println( "S3 = " + string3 );		
	}
	
	// Se puede borrar un objeto en un método? En este caso no se puede - string es inmutable
	private static void borrar( String string ) {  
		// string = new String(""); // o lo que es lo mismo string = ""; - no cambia el objeto original, solo crea uno nuevo
		// ¿Y de otra forma?
		string.replaceAll("a", "b");  // Tampoco se cambia el objeto original, este replace DEVUELVE un string nuevo
		// Observa que no hay ningún método string.set... que pueda cambiar el objeto. Por eso es inmutable
	}
	
	// Se puede borrar/modificar un objeto mutable? Ojo que SÍ SE PUEDE
	// (pasar la referencia significa que se pasa acceso completo a lo que se pueda hacer con ese objeto)
	private static void borrar( Point punto ) {  
		punto.x = 0;  // Acceso directo a los atributos: objeto mutable
		punto.y = 0;
		// punto = new Point(0,0);  // Si hiciéramos esto, se crea un nuevo punto 
		// pero NO se cambia la referencia del objeto original (p2)
		// Observa que hay varios métodos punto.set... que permiten modificar el objeto. Por eso es mutable
	}
	
}
