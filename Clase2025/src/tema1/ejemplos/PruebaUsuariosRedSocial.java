package tema1.ejemplos;

public class PruebaUsuariosRedSocial {
	private static int numUsuarios = 2;
	public static void main(String[] args) {
		
		UsuarioRedSocial u = new UsuarioRedSocial();
		
		UsuarioRedSocial u2 = new UsuarioRedSocial();
		
		System.out.println( u.nombre + " - " + u.numSeguidores );
		u.nombre = "Andoni";
		u.numSeguidores = 1;
		System.out.println( u.nombre + " - " + u.numSeguidores );
		
		u = new UsuarioRedSocial( "Andoni", 1 );
		System.out.println( u.nombre + " - " + u.numSeguidores );
		
		UsuarioRedSocial uVip = new UsuarioRedSocial( "María" );
		System.out.println( uVip.nombre + " - " + uVip.numSeguidores );
		
		System.out.println( u.calcValorEconomico() );
		System.out.println( uVip.calcValorEconomico() );
		
	}
}
