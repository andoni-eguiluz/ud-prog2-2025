package tema1.ejemplos.usuarioRedSocialV3;

public class UsuarioRedSocial {
	// Datos (atributos)
	private String nombre = "";
	private int numSeguidores;
	private String dni;
	
	// Constructores
	
	/** Crea un nuevo usuario
	 * @param nombre	Nombre del usuario
	 * @param numSeguidores	Número de seguidores
	 */
	public UsuarioRedSocial( String nombre, int numSeguidores ) {
		// INICIALIZAR
		this.nombre = nombre;
		// this.numSeguidores = numSeguidores;
		this.setNumSeguidores( numSeguidores );
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
	
	// getters y setters
	
	/** Devuelve el número de seguidores
	 * @return	Número EN MILES
	 */
	public int getNumSeguidores() {
		return numSeguidores;
	}
	
	public int getNumSeguidoresEnUnidades() {
		return numSeguidores*1000;
	}

	/** Cambia el número de seguidores
	 * @param numSeguidores	Número nuevo. Si es menor que cero se deja en cero
	 */
	public void setNumSeguidores( int numSeguidores ) {
		this.numSeguidores = numSeguidores;
		if (numSeguidores < 0) {
			this.numSeguidores = 0;
		}
	}
	
	public void setNumSeguidores() {
		// TODO - buscar en la web el número actual
	}
	
	public String getNombre() {
		return nombre;
	}

	/** Devuelve el número esperable de likes
	 * @return	Devuelve ese número de forma estimada con estadísticas internas
	 */
	public double getNumEsperableLikes() {
		return 20.0 * numSeguidores;
	}
	

	// Atributo invariable, inmutable
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}

	public String getDni() {
		return dni;
	}

	// Otros métodos
	
	/** Convierte a string el usuario
	 * @return	String con el formato nombre+tabulador+nºseguidores
	 */
	public String convierteAString() {
		return this.nombre + "\t" + this.numSeguidores;
	}
	
	public String toString() {
		return convierteAString();
	}
	
	// Método equals!!
	public boolean equals( Object o2 ) {
		System.out.println( "Comparo " + this + " con " + o2 );
		UsuarioRedSocial u2 = (UsuarioRedSocial) o2;
		return this.nombre.equals( u2.nombre );
	}

	public double calcValorEconomico() {
		return 3.6 * this.numSeguidores;
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