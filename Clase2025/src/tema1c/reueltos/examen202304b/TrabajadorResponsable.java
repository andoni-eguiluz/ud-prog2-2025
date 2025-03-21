package tema1c.reueltos.examen202304b;

import java.util.ArrayList;

import tema1c.reueltos.examen202304b.gui.VentanaGrafica;

// TAREA 1
public class TrabajadorResponsable extends Trabajador {

	private ArrayList<Cargo> cargos;
	
	/** Crea un nuevo trabajador responsable sin tareas ni cargos asignados
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param ventana	Ventana del trabajador
	 * @param nombre del trabajador
	 * @param imagen nombre del trabajador
	 */
	public TrabajadorResponsable(int x, int y, VentanaGrafica ventana, String nombre, String imagen) {
		super(x, y, ventana, nombre, imagen);
		cargos = new ArrayList<>();
	}
	
	/** Crea un nuevo trabajador responsable en las coordenadas {@link #COORD_POR_DEFECTO}
	 * @param ventana	Ventana de la tarea
	 * @param nombre del trabajador
	 * @param imagen nombre del trabajador
	 */
	public TrabajadorResponsable(VentanaGrafica ventana, String nombre, String imagen) {
		this( COORD_POR_DEFECTO.x, COORD_POR_DEFECTO.y, ventana, nombre, imagen );
	}
	
	public ArrayList<Cargo> getCargos() {
		return cargos;
	}

	@Override
	public void dibujar() { // GROSOR, COLOR_BORDE, COLOR_FONDO cambiados a protected en Trabajador
		ventana.dibujaCirculo( x, y, TAMANYO/2 + 3, Trabajador.GROSOR, Trabajador.COLOR_BORDE, Trabajador.COLOR_FONDO );
		super.dibujar();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TrabajadorResponsable)) return false;
		TrabajadorResponsable t2 = (TrabajadorResponsable) obj;
		return super.equals(obj) && cargos.equals(t2.cargos);
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + cargos;
	}

}
