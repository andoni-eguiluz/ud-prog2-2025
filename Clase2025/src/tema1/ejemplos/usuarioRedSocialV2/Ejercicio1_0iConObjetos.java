package tema1.ejemplos.usuarioRedSocialV2;

import java.util.ArrayList;
import java.util.Arrays;

/** Solución ejercicio 1.0j  -  https://docs.google.com/document/d/1IjFLFCttA1PXIQjiK4nFGRaCODdvbT61_SRniIY6o1s/edit?tab=t.0
 * Pero ahora con objetos
 */
public class Ejercicio1_0iConObjetos {
	//	Crea dos arrays, uno con los nombres de 4 o 5 usuarios de cualquier red social que uses,
	//	y otro con sus seguidores. Realiza un programa que muestre por consola esos usuarios 
	//  primero sin ordenar y luego ordenados por número de seguidores 
	//	(de mayor a menor).

	public static void main(String[] args) {
//		String[] usuarios = { "@sama", "@BillGates", "@JeffBezos", "@elonmusk" };
//		int[] seguidores = { 3400, 66000, 6700, 217700 };
//		UsuarioRedSocial[] usuarios = {  // Crea e inicializa
//			new UsuarioRedSocial( "@sama", 3400 ),
//			new UsuarioRedSocial( "@BillGates", 66000 ),
//			new UsuarioRedSocial( "@JeffBezos", 6700 ),
//			new UsuarioRedSocial( "@elonmusk", 217700 )
//		};
		ArrayList<UsuarioRedSocial> usuarios = new ArrayList<UsuarioRedSocial>();
		usuarios.add( new UsuarioRedSocial( "@sama", 3400 ) );
		usuarios.add( new UsuarioRedSocial( "@BillGates", 66000 ) );
		usuarios.add( new UsuarioRedSocial( "@JeffBezos", 6700 ) );
		usuarios.add( new UsuarioRedSocial( "@elonmusk", 217700 ) );
		
		// Otra manera es crear y asignar aparte
		// UsuarioRedSocial[] usuarios = new UsuarioRedSocial[4];
		// usuarios[0] = UsuarioRedSocial( "@sama", 3400 );
		// etc.
		// System.out.println( Arrays.toString(usuarios) );
		System.out.println( usuarios );
		System.out.println( "Lista original:" );
		visualizarUsuariosYSeguidores( usuarios );
		ordenaUsuariosPorSeguidores( usuarios );
		System.out.println( "Lista ordenada:" );
		visualizarUsuariosYSeguidores( usuarios );
		
		// TODO: Caída de seguidores -3500 a todos los usuarios
		for (UsuarioRedSocial u : usuarios) {
			u.setNumSeguidores( u.getNumSeguidores() - 3500 );
//			if (u.getNumSeguidores() < 0) {
//				u.numSeguidores = 0;
//			}
		}
		visualizarUsuariosYSeguidores(usuarios);
		// System.out.println( Arrays.toString( usuarios ) );
		System.out.println( usuarios );
		
		// String = inmutable (no hay sets)
		String prueba = "Hola";
		prueba.toUpperCase();
		System.out.println( prueba );
		
		// Añadamos un usuario a la lista de 4
		usuarios.add( 2, new UsuarioRedSocial( "@sundarpichai", 5500 ) );
		System.out.println( usuarios );
		
		System.out.println( usuarios.indexOf( new UsuarioRedSocial( "@sundarpichai", 5500 ) ));
		
	}
	
	// Visualiza línea a línea usuario tabulador nº seguidores
	private static void visualizarUsuariosYSeguidores(ArrayList<UsuarioRedSocial> usuarios) {
		for (int i=0; i<usuarios.size(); i++) {
			System.out.println( usuarios.get(i).convierteAString() );
		}
	}
	
	private static void ordenaUsuariosPorSeguidores(ArrayList<UsuarioRedSocial> usuarios) {
		for (int pasada=0; pasada<usuarios.size()-1; pasada++) {
			for (int comp=0; comp<usuarios.size()-1; comp++) {  // TODO mejorar que solo se hagan las comparaciones necesarias
				boolean hayQueIntercambiar = usuarios.get(comp).estaDespuesDe( usuarios.get(comp+1) );
				if (hayQueIntercambiar) {
//					int aux = usuarios[comp].numSeguidores;
//					usuarios[comp].numSeguidores = usuarios[comp+1].numSeguidores;
//					usuarios[comp+1].numSeguidores = aux;
//					String auxNombre = usuarios[comp].nombre;
//					usuarios[comp].nombre = usuarios[comp+1].nombre;
//					usuarios[comp+1].nombre = auxNombre;
					UsuarioRedSocial aux = usuarios.get(comp);
					usuarios.set( comp, usuarios.get(comp+1) );
					usuarios.set( comp+1,  aux );
					System.out.println( "Intercambiados " + usuarios.get(comp).getNombre() + " con " + usuarios.get(comp+1).getNombre() );
				}
			}
		}
	}
	
}
