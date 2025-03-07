package tema1.ejemplos.usuarioRedSocialV3;

import java.util.ArrayList;

public class RedSocial {
	// Atributos
	private ArrayList<UsuarioRedSocial> listaUsuarios;  // null
	private String nombre;
	private String url;
	private TipoRedSocial tipo = TipoRedSocial.GENERALISTA;

	// Constructores
	
	/**
	 * @param nombre	Nuevo nombre. Si es vacío no se cambia y se saca mens. de error a consola de error
	 * @param url
	 * @param tipo
	 */
	public RedSocial(String nombre, String url, TipoRedSocial tipo) {
		// super();  // Herencia
		this.setNombre( nombre );
		this.setUrl( url );
		this.tipo = tipo;
		this.listaUsuarios = new ArrayList<UsuarioRedSocial>();
	}

	/** Crea una nueva rs con tipo TipoRedSocial.GENERALISTA
	 * @param nombre	Nuevo nombre. Si es vacío no se cambia y se saca mens. de error a consola de error
	 * @param url	Url de esa rs
	 */
	public RedSocial(String nombre, String url) {
		this.setNombre( nombre );
		this.setUrl( url );
		this.listaUsuarios = new ArrayList<>();
	}

	// Getters y setters
		
	public String getNombre() {
		return nombre;
	}

	/** Cambia el nombre del usuario
	 * @param nombre	Nuevo nombre. Si es vacío no se cambia y se saca mens. de error a consola de error
	 */
	public void setNombre(String nombre) {
		if (nombre.isEmpty()) {  // (nombre.equals( "" )) {
			System.err.println( "Error - intento de poner nombre vacío");
//		} else {
//			this.nombre = nombre;
//		}
			return;
		}
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TipoRedSocial getTipo() {
		return tipo;
	}

	public void setTipo(TipoRedSocial tipo) {
		this.tipo = tipo;
	}

	public ArrayList<UsuarioRedSocial> getListaUsuarios() {
		return listaUsuarios;
	}
	
	// Funcionalidad

	public void anyadirUsuario( UsuarioRedSocial u ) {
		this.listaUsuarios.add( u );
	}
	
	// TODO Otro método que permita añadir en una posición determinada
	
	// Visualiza línea a línea usuario tabulador nº seguidores
	public void visualizarUsuariosYSeguidores() {
		for (int i=0; i<listaUsuarios.size(); i++) {
			System.out.println( listaUsuarios.get(i).convierteAString() );
		}
	}
	
	public void ordenaUsuariosPorSeguidores() {
		for (int pasada=0; pasada<listaUsuarios.size()-1; pasada++) {
			for (int comp=0; comp<listaUsuarios.size()-1; comp++) {  // TODO mejorar que solo se hagan las comparaciones necesarias
				boolean hayQueIntercambiar = listaUsuarios.get(comp).estaDespuesDe( listaUsuarios.get(comp+1) );
				if (hayQueIntercambiar) {
					UsuarioRedSocial aux = listaUsuarios.get(comp);
					listaUsuarios.set( comp, listaUsuarios.get(comp+1) );
					listaUsuarios.set( comp+1,  aux );
					System.out.println( "Intercambiados " + listaUsuarios.get(comp).getNombre() + " con " + listaUsuarios.get(comp+1).getNombre() );
				}
			}
		}
	}
	
	/** Modifica los seguidores de todos los usuarios de  la red social
	 * @param diferencial	Número a sumar o restar (pos. o neg.) a cada usuario
	 */
	public void incrementarNumSeguidores( int diferencial ) {
		for (UsuarioRedSocial u : listaUsuarios) {
			u.setNumSeguidores( u.getNumSeguidores() + diferencial );
		}
	}
	
	/** Busca un usuario en la red social
	 * @param u	Usuario a buscar
	 * @return	-1 si no se encuentra, índice de la posición si se encuentra
	 */
	public int buscarUsuario( UsuarioRedSocial u ) {
		return listaUsuarios.indexOf( u );
	}

	// toString
	public String toString() {
		return nombre + " / " + listaUsuarios.toString();
	}
	
}
