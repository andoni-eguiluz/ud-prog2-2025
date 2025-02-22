package tema1.resueltos;

public class Vaca {

//	Crear una clase Vaca que tenga:
//		Atributos: color de pelo (String), edad (entero), nombre (String) 
//		Métodos: 
//		debes elegir tres constructores diferentes 
//		muu: método que hace que la vaca muja y diga su nombre y color de pelo 
//      	"Muuu...mi nombre es XXX y mi color de pelo es YYY"
//		compararEdad: dadas las edades de dos vacas (la que envía el mensaje y otra como parámetro) saca el nombre de la vaca más antigua "La vaca XXX es más vieja que la vaca YYY" 

	private static int numVacaCreada = 0;
	
	private String colorPelo;
	private int edad;
	private String nombre;
	
	/** Crea una nueva vaca
	 * @param nombre	Nombre
	 * @param edad	Edad (mayor o igual que 0)
	 * @param colorPelo	Color de pelo como un string
	 */
	public Vaca( String nombre, int edad, String colorPelo ) {
		this.colorPelo = colorPelo;
		this.edad = edad;
		this.nombre = nombre;
		numVacaCreada++;
		System.out.println("Se ha creado la vaca " + numVacaCreada );
	}
	
	/** Crea una nueva vaca con pelo "Blanco"
	 * @param nombre	Nombre de la vaca
	 * @param edad	Edad de la vaca (0 o mayor)
	 */
	public Vaca( String nombre, int edad ) {
		this.nombre = nombre;
		this.edad = edad;
		this.colorPelo = "Blanco";
		numVacaCreada++;
		System.out.println("Se ha creado la vaca " + numVacaCreada );
	}
	
	/** Crea una nueva vaca con edad 0 y pelo "Blanco"
	 * @param nombre	Nombre de la vaca
	 */
	public Vaca( String nombre ) {
		this.nombre = nombre;
		this.edad = 0;
		this.colorPelo = "Blanco";
		numVacaCreada++;
		System.out.println("Se ha creado la vaca " + numVacaCreada );
	}

	public String getColorPelo() {
		return colorPelo;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public String getNombre() {
		return nombre;
	}

	/** Devuelve una info de la vaca
	 * @return	Información de la vaca en formato "Muuu...mi nombre es XXX y mi color de pelo es YYY"
	 */
	public String muu() {
		return "Muuu...mi nombre es " + nombre + " y mi color de pelo es " + colorPelo;
	}

	/** dadas las edades de dos vacas (la que envía el mensaje y otra como parámetro) 
	* saca el mensaje a consola con el nombre de la vaca más antigua 
	* "La vaca XXX es más vieja que la vaca YYY"
	 * @param vaca2	Segunda vaca con la que comparar
	 */
	public void compararEdad( Vaca vaca2 ) {
		Vaca vacaMayor = this;
		Vaca vacaMenor = vaca2;
		if (vaca2.edad > this.edad) {
			vacaMayor = vaca2;
			vacaMenor = this;
		}
		System.out.println( "La vaca " + vacaMayor.nombre + " es más vieja que la vaca " + vacaMenor.nombre );
	}
	
}
