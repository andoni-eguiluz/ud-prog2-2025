package tema1.resueltos;

/** Solución ejercicios 1.0f,g,h  - https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Ejercicio1_0fgh {

	public static void main(String[] args) {
		ejercicioBCDArrayNombres();  // 1.0.f-g-h
	}	

	// Ejercicios 1.0.f. / 1.0.g. / 1.0.h.
	private static void ejercicioBCDArrayNombres() {
		String[] nombres = { "Andoni", "Sonia", "Mateo", "Aitziber", "Alberto", "Marta" };
		for (int i=0; i<nombres.length; i++) {
			System.out.println( nombres[i] );
		}
		String nombreMenor = "zzz";
		for (int i=0; i<nombres.length; i++) {
			if (nombres[i].compareTo(nombreMenor)<0) {
				nombreMenor = nombres[i];
			}
		}
		System.out.println( "Nombre menor alfabético: " + nombreMenor );
		String nombreMayor = "A";
		for (int i=0; i<nombres.length; i++) {
			if (nombres[i].compareTo(nombreMayor)>0) {
				nombreMayor = nombres[i];
			}
		}
		System.out.println( "Nombre mayor alfabético: " + nombreMayor );
		ordenarNombres( nombres );
		System.out.println( "Lista ordenada:");
		for (int i=0; i<nombres.length; i++) {
			System.out.println( nombres[i] );
		}
	}
	
		// Algoritmo de ordenación bubble sort
		private static void ordenarNombres( String[] nombres ) {
			for (int j=1; j<nombres.length; j++) {
				for (int i=0; i<nombres.length-j; i++) {
					if (nombres[i].compareTo(nombres[i+1])>0) {
						String temp = nombres[i];
						nombres[i] = nombres[i+1];
						nombres[i+1] = temp;
					}
				}
			}
		}
	
}
