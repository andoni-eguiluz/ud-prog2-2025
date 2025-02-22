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
	
	public int getNumSeguidores() {
		return numSeguidores;
	}
	
	/** Modifica el número de seguidores. Debe ser positivo o cero
	 * @param numSeguidores	Número nuevo. Si es negativo, el método no hace ninguna modificación. 
	 */
	public void setNumSeguidores( int numSeguidores ) {
		if (numSeguidores >= 0) {
			this.numSeguidores = numSeguidores;
		}
	}	
	
}
