package tema1c.reueltos.examen202304b;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import tema1c.reueltos.examen202304b.gui.VentanaGrafica;

/** Clase que permite modelar cada tarea de nuestro sistema
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Tarea extends ObjetoGT 
// TAREA 3
implements Interactivo 
{
	
	// Parte static
	
	/** Coordenada por defecto de la tarea */
	public static final Point COORD_POR_DEFECTO = new Point( 10, 30 );
	/** Color por defecto de la tarea */
	public static final Color COLOR_POR_DEFECTO = Color.YELLOW;
	/** Tamaño visual de la tarea */
	public static final Rectangle TAMANYO = new Rectangle( 150, 30 );
	/** Tipo de letra visual de la tarea */
	public static final Font FONT = new Font( "Arial", Font.PLAIN, 16 );
	private static final float GROSOR = 1.0f;  // Grosor visual del borde de la tarea
	private static final Color COLOR_BORDE = Color.BLACK; // Color visual del borde de la tarea
	private static final Color COLOR_TEXTO = Color.BLUE;  // Color visual del texto de la tarea
	private static final int OFFSET_TEXTO_X = 10; // Desplazamiento visual del texto con respecto a la esquina
	private static final int OFFSET_TEXTO_Y = 5; // Desplazamiento visual del texto con respecto a la esquina

	
	// Número de tareas que se van creando con nombre automático (usado para calcular el nombre con número secuencial)
	private static int numTarea = 0; // Solo uno para todos los objetos
	
	// Parte no static
	
	private String descripcion;
	private Color color;
	private boolean resuelto;
	
	/** Crea una tarea nueva sin resolver
	 * @param x	Coordenada x del centro visual de la tarea
	 * @param y	Coordenada y del centro visual de la tarea
	 * @param ventana	Ventana de la tarea
	 * @param descripcion	Descripción de la tarea
	 * @param color	Color de dibujado de fondo de la tarea
	 */
	public Tarea( int x, int y, VentanaGrafica ventana, String descripcion, Color color ) {
		super( x, y, ventana );
		this.descripcion = descripcion;
		this.color = color;
		resuelto = false;
	}
	
	/** Crea una tarea nueva sin resolver en las coordenadas {@link #COORD_POR_DEFECTO} con el color {@link #COLOR_POR_DEFECTO} 
	 * y la descripción "Tarea #" con un número secuencial de número de tarea que va creciendo desde 1
	 * @param ventana	Ventana de la tarea
	 */
	public Tarea( VentanaGrafica ventana ) {
		this( COORD_POR_DEFECTO.x, COORD_POR_DEFECTO.y, ventana, "Tarea " + (++numTarea), COLOR_POR_DEFECTO );
		// Sería lo mismo
		// super( COORD_POR_DEFECTO.x, COORD_POR_DEFECTO.y, ventana );
		// this.descripcion = "Tarea " + (++numTarea);
		// this.color = COLOR_POR_DEFECTO;
		// resuelto = false;
	}
	
	/** Crea una tarea nueva copia de una tarea existente
	 * @param tarea	Tarea a copiar
	 */
	public Tarea( Tarea tarea ) {
		super( tarea.x, tarea.y, tarea.ventana );
		this.descripcion = tarea.descripcion;
		this.color = tarea.color;
		this.resuelto = tarea.resuelto;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isResuelto() {
		return resuelto;
	}

	public void setResuelto(boolean resuelto) {
		this.resuelto = resuelto;
	}

	@Override
	public void dibujar() {
		ventana.dibujaRect( x-TAMANYO.width/2, y-TAMANYO.height/2, TAMANYO.width, TAMANYO.height, GROSOR, COLOR_BORDE, color );
		ventana.dibujaTexto( x-TAMANYO.width/2+OFFSET_TEXTO_X, y+OFFSET_TEXTO_Y, descripcion, FONT, COLOR_TEXTO );
	}

	@Override
	public boolean contienePunto(int x, int y) {
		return (x>=this.x-TAMANYO.width/2) && (x<=this.x+TAMANYO.width/2) && 
				(y>=this.y-TAMANYO.height/2) && (y<=this.y+TAMANYO.height/2);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tarea)) return false;
		Tarea t2 = (Tarea) obj;
		return descripcion.equals(t2.descripcion); // Ya cambiado para facilitar la tarea 4
		// return super.equals(obj) && descripcion.equals(t2.descripcion);
	}
	
	@Override
	public String toString() {
		return "Tarea " + descripcion + " " + super.toString();
	}

	// TAREA 3
	
	@Override
	public void setTexto(String nuevoTexto) {
		setDescripcion(nuevoTexto);
	}
	
	@Override
	public String getTexto() {
		return getDescripcion();
	}
	
	// TAREA 4
	@Override
	public int hashCode() {
		return descripcion.hashCode();
	}
	
}
