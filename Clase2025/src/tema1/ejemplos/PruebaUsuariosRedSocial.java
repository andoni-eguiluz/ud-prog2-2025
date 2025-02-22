package tema1.ejemplos;

public class PruebaUsuariosRedSocial {
	private static int numUsuarios = 2;
	public static void main(String[] args) {
		
		UsuarioRedSocial u = new UsuarioRedSocial();
		
		UsuarioRedSocial u2 = new UsuarioRedSocial();
		
		System.out.println( u.nombre + " - " + u.getNumSeguidores() );
		u.nombre = "Andoni";
		u.setNumSeguidores( 1 );
		System.out.println( u.nombre + " - " + u.getNumSeguidores() );
		
		u = new UsuarioRedSocial( "Andoni", 1 );
		System.out.println( u.nombre + " - " + u.getNumSeguidores() );
		
		UsuarioRedSocial uVip = new UsuarioRedSocial( "María" );
		System.out.println( uVip.nombre + " - " + uVip.getNumSeguidores() );
		
		System.out.println( u.calcValorEconomico() );
		System.out.println( uVip.calcValorEconomico() );
		
		uVip.setNumSeguidores( -3 );

		System.out.println( uVip.calcValorEconomico() );

	}
}
