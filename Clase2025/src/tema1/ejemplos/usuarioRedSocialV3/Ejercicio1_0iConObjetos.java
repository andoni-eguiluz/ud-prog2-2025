package tema1.ejemplos.usuarioRedSocialV3;

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

	static RedSocial x;
	static RedSocial linkedin;

	static ArrayList<RedSocial> listaRRSS;
	
	public static void main(String[] args) {
//		String[] usuarios = { "@sama", "@BillGates", "@JeffBezos", "@elonmusk" };
//		int[] seguidores = { 3400, 66000, 6700, 217700 };
//		UsuarioRedSocial[] usuarios = {  // Crea e inicializa
//			new UsuarioRedSocial( "@sama", 3400 ),
//			new UsuarioRedSocial( "@BillGates", 66000 ),
//			new UsuarioRedSocial( "@JeffBezos", 6700 ),
//			new UsuarioRedSocial( "@elonmusk", 217700 )
//		};
//		ArrayList<UsuarioRedSocial> usuarios = new ArrayList<UsuarioRedSocial>();
//		usuarios.add( new UsuarioRedSocial( "@sama", 3400 ) );
//		usuarios.add( new UsuarioRedSocial( "@BillGates", 66000 ) );
//		usuarios.add( new UsuarioRedSocial( "@JeffBezos", 6700 ) );
//		usuarios.add( new UsuarioRedSocial( "@elonmusk", 217700 ) );
		x = new RedSocial( "x", "x.com" );
		// x.getListaUsuarios().add  se podría hacer
		x.anyadirUsuario( new UsuarioRedSocial( "@sama", 3400 ) );
		x.anyadirUsuario( new UsuarioRedSocial( "@BillGates", 66000 ) );
		x.anyadirUsuario( new UsuarioRedSocial( "@JeffBezos", 6700 ) );
		x.anyadirUsuario( new UsuarioRedSocial( "@elonmusk", 217700 ) );
		
		// Otra manera es crear y asignar aparte
		// UsuarioRedSocial[] usuarios = new UsuarioRedSocial[4];
		// usuarios[0] = UsuarioRedSocial( "@sama", 3400 );
		// etc.
		// System.out.println( Arrays.toString(usuarios) );
		System.out.println( x );
		System.out.println( "Lista original:" );
		x.visualizarUsuariosYSeguidores();
		x.ordenaUsuariosPorSeguidores();
		System.out.println( "Lista ordenada:" );
		x.visualizarUsuariosYSeguidores();
		
		// TODO: Caída de seguidores -3500 a todos los usuarios
//		for (UsuarioRedSocial u : usuarios) {
//			u.setNumSeguidores( u.getNumSeguidores() - 3500 );
////			if (u.getNumSeguidores() < 0) {
////				u.numSeguidores = 0;
////			}
//		}
		x.incrementarNumSeguidores(-3500 );
		
		x.visualizarUsuariosYSeguidores();
		// System.out.println( Arrays.toString( usuarios ) );
		System.out.println( x );
		
		// String = inmutable (no hay sets)
		String prueba = "Hola";
		prueba.toUpperCase();
		System.out.println( prueba );
		
		// Añadamos un usuario a la lista de 4
		x.anyadirUsuario( new UsuarioRedSocial( "@sundarpichai", 5500 ) );
		System.out.println( x );
		
		// System.out.println( usuarios.indexOf( new UsuarioRedSocial( "@sundarpichai", 5500 ) ));
		System.out.println( x.buscarUsuario(new UsuarioRedSocial( "@sundarpichai", 5500 )));

		
		// Crear linkedin
		linkedin = new RedSocial( "linkedin", "linkedin.com", TipoRedSocial.PROFESIONAL );
		linkedin.anyadirUsuario( new UsuarioRedSocial( "@sama", 32342 ) );
		linkedin.anyadirUsuario( new UsuarioRedSocial( "@BillGates", 6230 ) );
		System.out.println( linkedin );
		
	}
	
}
