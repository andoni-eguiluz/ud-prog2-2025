package tema1.resueltos;

/** Solución ejercicio 1.0j  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 */
public class Ejercicio1_0i {
	//	Crea dos arrays, uno con los nombres de 4 o 5 usuarios de cualquier red social que uses,
	//	y otro con sus seguidores. Realiza un programa que muestre por consola esos usuarios 
	//  primero sin ordenar y luego ordenados por número de seguidores 
	//	(de mayor a menor).

	public static void main(String[] args) {
		String[] usuarios = { "@sama", "@BillGates", "@JeffBezos", "@elonmusk" };
		int[] seguidores = { 3400, 66000, 6700, 217700 };
		System.out.println( "Lista original:" );
		visualizarUsuariosYSeguidores( usuarios, seguidores );
		ordenaUsuariosPorSeguidores( usuarios, seguidores );
		System.out.println( "Lista ordenada:" );
		visualizarUsuariosYSeguidores( usuarios, seguidores );
	}
	
	// Visualiza línea a línea usuario tabulador nº seguidores
	private static void visualizarUsuariosYSeguidores(String[] usuarios, int[] seguidores) {
		for (int i=0; i<usuarios.length; i++) {
			System.out.println( usuarios[i] + "\t" + seguidores[i] );
		}
	}
	
	private static void ordenaUsuariosPorSeguidores(String[] usuarios, int[] seguidores) {
		for (int pasada=0; pasada<usuarios.length-1; pasada++) {
			for (int comp=0; comp<usuarios.length-1; comp++) {  // TODO mejorar que solo se hagan las comparaciones necesarias
				boolean hayQueIntercambiar = seguidores[comp] < seguidores[comp+1];
				if (hayQueIntercambiar) {
					int aux = seguidores[comp];
					seguidores[comp] = seguidores[comp+1];
					seguidores[comp+1] = aux;
					String auxNombre = usuarios[comp];
					usuarios[comp] = usuarios[comp+1];
					usuarios[comp+1] = auxNombre;
				}
			}
		}
	}
	
}
