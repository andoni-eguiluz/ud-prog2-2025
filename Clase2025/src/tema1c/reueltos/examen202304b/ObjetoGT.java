package tema1c.reueltos.examen202304b;

import tema1c.reueltos.examen202304b.gui.VentanaGrafica;

/** Objeto general de gestión de tareas, clase padre de la jerarquía de objetos que se dibujan en la ventana
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public abstract class ObjetoGT extends Object {
	protected int x;
	protected int y;
	protected VentanaGrafica ventana;
	
	/** Crea un nuevo objeto
	 * @param x	Posición x (horizontal)
	 * @param y	Posición y (vertical)
	 * @param ventana	Ventana gráfica en la que está ese objeto
	 */
	public ObjetoGT(int x, int y, VentanaGrafica ventana) {
		// super();  // Como si estuviera (Java lo añade si no llamamos a otro super)
		this.x = x;
		this.y = y;
		this.ventana = ventana;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public VentanaGrafica getVentana() {
		return ventana;
	}

	public void mover( int x, int y ) {
		setX( x );
		setY( y );
	}
	
	/** Dibuja el objeto en su ventana gráfica
	 */
	abstract public void dibujar();
	
	/** Comprueba si el objeto contiene un punto de la ventana
	 * @param x	Posición x del punto
	 * @param y	Posición y del punto
	 * @return	true si esa posición está dentro del objeto, false en caso contrario
	 */
	abstract public boolean contienePunto( int x, int y );
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ObjetoGT)) return false;
		ObjetoGT o2 = (ObjetoGT) obj;  // Miro este obj como lo que realmente es: un ObjetoGT
		return x==o2.x && y==o2.y && ventana==o2.ventana;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
		
}
