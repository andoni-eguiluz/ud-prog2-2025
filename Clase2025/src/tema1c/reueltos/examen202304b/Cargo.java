package tema1c.reueltos.examen202304b;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import tema1c.reueltos.examen202304b.gui.VentanaGrafica;

/** Clase que permite modelar cada cargo (rol de responsabilidad) de nuestro sistema
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Cargo extends ObjetoGT 
// TAREA 3
implements Interactivo 
{
	
	// Parte static
	
	/** Coordenada por defecto del cargo */
	public static final Point COORD_POR_DEFECTO = new Point( 10, 300 );
	/** Color por defecto del cargo */
	public static final Color COLOR_POR_DEFECTO = Color.CYAN;
	/** Radio del tamaño visual del cargo */
	public static final int RADIO = 50;
	/** Tipo de letra visual del cargo */
	public static final Font FONT = new Font( "Arial", Font.PLAIN, 16 );
	private static final float GROSOR = 1.0f;  // Grosor visual del borde del cargo
	private static final Color COLOR_BORDE = Color.BLACK; // Color visual del borde del cargo
	private static final Color COLOR_TEXTO = Color.BLACK;  // Color visual del texto del cargo
	
	// Número de cargos que se van creando con nombre automático (usado para calcular el nombre con número secuencial)
	private static int numCargo = 0; // Solo uno para todos los objetos
	
	// Parte no static
	
	private String nombre;
	private Color color;
	
	/** Crea un cargo nuevo
	 * @param x	Coordenada x del centro visual del cargo
	 * @param y	Coordenada y del centro visual del cago
	 * @param ventana	Ventana del cargo
	 * @param nombre	Nombre del cargo
	 * @param color	Color de dibujado de fondo del cargo
	 */
	public Cargo( int x, int y, VentanaGrafica ventana, String nombre, Color color ) {
		super( x, y, ventana );
		this.nombre = nombre;
		this.color = color;
	}
	
	/** Crea un cargo nuevo sin resolver en las coordenadas {@link #COORD_POR_DEFECTO} con el color {@link #COLOR_POR_DEFECTO} 
	 * y la descripción "Cargo #" con un número secuencial de número de cargo que va creciendo desde 1
	 * @param ventana	Ventana del cargo
	 */
	public Cargo( VentanaGrafica ventana ) {
		this( COORD_POR_DEFECTO.x, COORD_POR_DEFECTO.y, ventana, "Cargo " + (++numCargo), COLOR_POR_DEFECTO );
	}
	
	/** Crea un cargo nuevo copia de un cargo existente
	 * @param cargo	Cargo a copiar
	 */
	public Cargo( Cargo cargo ) {
		super( cargo.x, cargo.y, cargo.ventana );
		this.nombre = cargo.nombre;
		this.color = cargo.color;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void dibujar() {
		ventana.dibujaCirculo( x, y, RADIO, GROSOR, COLOR_BORDE, color );
		ventana.dibujaTextoCentrado( x-RADIO, y-RADIO/2, RADIO*2, RADIO, nombre, FONT, COLOR_TEXTO, true );
	}

	@Override
	public boolean contienePunto(int x, int y) {
		double dist = Math.sqrt( (x-this.x)*(x-this.x) + (y-this.y)*(y-this.y) );
		return (dist <= RADIO);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cargo)) {
			return false;
		}
		Cargo t2 = (Cargo) obj;
		return super.equals(obj) && nombre.equals(t2.nombre);
	}
	
	@Override
	public String toString() {
		return "Cargo " + nombre + " " + super.toString();
	}
	
	// TAREA 3
	
	@Override
	public void setTexto(String nuevoTexto) {
		setNombre(nuevoTexto);
	}
	
	@Override
	public String getTexto() {
		return getNombre();
	}
	

}
