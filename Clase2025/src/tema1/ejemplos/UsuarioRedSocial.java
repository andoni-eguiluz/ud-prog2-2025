package tema1.ejemplos;

public class UsuarioRedSocial {
	// Datos (atributos)
	String nombre = "";
	int numSeguidores;
	
	// Constructores
	
	/** Crea un nuevo usuario
	 * @param nombre	Nombre del usuario
	 * @param numSeguidores	Número de seguidores
	 */
	public UsuarioRedSocial( String nombre, int numSeguidores ) {
		// INICIALIZAR
		this.nombre = nombre;
		this.numSeguidores = numSeguidores;
	}
	
	/** Crea un usuario con nombre vacío y sin seguidores (0)
	 */
	public UsuarioRedSocial() {
		// Nada
	}
	
	/** Crea y devuelve un usuario VIP, que parte de 5k seguidores
	 * @param nombre	Nombre del usuario
	 */
	public UsuarioRedSocial( String nombre ) {
		this.nombre = nombre;
		this.numSeguidores = 5;
	}
	
	// Funciones (métodos)
	public double calcValorEconomico() {
		return 3.6 * this.numSeguidores;
	}

	/** Convierte a string el usuario
	 * @return	String con el formato nombre+tabulador+nºseguidores
	 */
	public String convierteAString() {
		return this.nombre + "\t" + this.numSeguidores;
	}
	
	public String toString() {
		return convierteAString();
	}
	
	/** Comprueba si el usuario this se debe ordenar después del usuario2
	 * Es decir, si tiene this menos seguidores que usuario2
	 * @param usuario2	Segundo usuario a comparar
	 * @return	Devuelve true si el this debe ir después que usuario2, false en caso contrario
	 */
	public boolean estaDespuesDe( UsuarioRedSocial usuario2 ) {
		return this.numSeguidores < usuario2.numSeguidores;
	}
	
}