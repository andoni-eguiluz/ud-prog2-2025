package tema1c.reueltos.examen202304b;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import tema1c.reueltos.examen202304b.gui.VentanaGrafica;

public class Trabajador extends ObjetoGT {
	
	//================= Parte static
	
	/** Coordenada por defecto del trabajador */
	public static final Point COORD_POR_DEFECTO = new Point( 400, 50 );
	/** Tamaño visual del trabajador (diámetro del círculo) */
	public static final int TAMANYO = 300;
	/** Tamaño visual de la foto del trabajador (anchura y altura) */
	public static final int TAMANYO_FOTO = 80;
	protected static final Color COLOR_FONDO = new Color( 240, 240, 240 );  // Gris clarito - Color de fondo del trabajador
	protected static final float GROSOR = 1.0f;  // Grosor visual del borde del círculo del trabajador
	protected static final Color COLOR_BORDE = Color.MAGENTA; // Color visual del borde del círculo del trabajador
	
	//================= Parte no static

	String nombre;
	String imagen;
	ArrayList<Tarea> tareasAsignadas;

	/** Crea un nuevo trabajador sin tareas asignadas
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del trabajador
	 * @param nombre del trabajador
	 * @param imagen nombre del trabajador
	 */
	public Trabajador(int x, int y, VentanaGrafica ventana, String nombre, String imagen) {
		super(x, y, ventana);
		this.nombre = nombre;
		this.imagen = imagen;
		this.tareasAsignadas = new ArrayList<>();
	}
	
	/** Crea un nuevo trabajador en las coordenadas {@link #COORD_POR_DEFECTO}
	 * @param ventana	Ventana de la tarea
	 * @param nombre del trabajador
	 * @param imagen nombre del trabajador
	 */
	public Trabajador(VentanaGrafica ventana, String nombre, String imagen) {
		this( COORD_POR_DEFECTO.x, COORD_POR_DEFECTO.y, ventana, nombre, imagen );
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public ArrayList<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	@Override
	public void dibujar() {
		ventana.dibujaCirculo( x, y, TAMANYO/2, GROSOR, COLOR_BORDE, COLOR_FONDO );
		ventana.dibujaImagen( imagen, x, y, TAMANYO_FOTO, TAMANYO_FOTO, 1.0, 0.0, 1.0f );
	}

	@Override
	public boolean contienePunto(int x, int y) {
		double dist = Math.sqrt( (x-this.x)*(x-this.x) + (y-this.y)*(y-this.y) );
		System.out.println( (dist<=TAMANYO/2) + " - " + nombre );
		return dist<=TAMANYO/2;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Trabajador)) return false;
		Trabajador t2 = (Trabajador) obj;
		return super.equals(obj) && imagen.equals(t2.imagen) && nombre.equals(t2.nombre) && tareasAsignadas.equals(t2.tareasAsignadas);
	}
	
	@Override
	public String toString() {
		return "Trabajador/a " + nombre + " " + super.toString();
	}

}
