package tema1.resueltos;

// Crear una clase Vaca que tenga:
//  Atributos: color de pelo (String), edad (entero), nombre (String) 
//  Métodos: 
//   debes elegir tres constructores diferentes 
//   muu: método que hace que la vaca muja y diga su nombre y color de pelo 
//	   "Muuu...mi nombre es XXX y mi color de pelo es YYY"
//   compararEdad: dadas las edades de dos vacas (la que envía el mensaje y otra como parámetro) saca el nombre de la vaca más antigua "La vaca XXX es más vieja que la vaca YYY" 
// Programa principal: 
//  Crear tres instancias de la clase Vaca, llamadas miVaca1, miVaca2 y miVaca3. Para cada una de ellas utilizar un constructor diferente.
//  Hacer que las tres mujan.
//  Comparar la edad de miVaca1 y miVaca2.
//  Modificar el programa para que cada vez que se cree una instancia 
//	  de la clase vaca salga un mensaje indicando el número de vaca creada: "Se ha creado la vaca 1", etc. 

public class Ejercicio1_3 {
	public static void main(String[] args) {
		Vaca miVaca1 = new Vaca( "Lucía" );
		System.out.println( miVaca1.getNombre() + " - " + miVaca1.muu() );
		Vaca miVaca2 = new Vaca( "Jacinta", 3 );
		System.out.println( miVaca2.getNombre() + " - " + miVaca2.muu() );
		Vaca miVaca3 = new Vaca( "Pinta", 5, "gris jaspeado" );
		System.out.println( miVaca3.getNombre() + " - " + miVaca3.muu() );
		miVaca1.compararEdad( miVaca2 );
	}
}
