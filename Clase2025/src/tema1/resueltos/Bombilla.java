package tema1.resueltos;

/** Ejercicio 1.15a resuelto
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class Bombilla {
	private boolean estadoEncendido; // si está apagada o encendida (boolean). Por defecto: false
	private int potenciaEnVatios; // en vatios (int)
	private String color = ""; // color de la bombilla (String)
	private float horasVidaEstimada = 10; // horas que puede estar encendida (float). Por defecto: 10

	
	public Bombilla(boolean estadoEncendido, int potenciaEnVatios, String color, float horasVidaEstimada) {
		// super();
		this.estadoEncendido = estadoEncendido;
		this.potenciaEnVatios = potenciaEnVatios;
		this.color = color;
		this.horasVidaEstimada = horasVidaEstimada;
	}
	
	public Bombilla() {
	}

	public boolean isEstadoEncendido() {
		return estadoEncendido;
	}

	public void setEstadoEncendido(boolean estadoEncendido) {
		this.estadoEncendido = estadoEncendido;
	}

	public int getPotenciaEnVatios() {
		return potenciaEnVatios;
	}

	public void setPotenciaEnVatios(int potenciaEnVatios) {
		this.potenciaEnVatios = potenciaEnVatios;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getHorasVidaEstimada() {
		return horasVidaEstimada;
	}

	@Override
	public String toString() {
		return potenciaEnVatios + "W " + color +
				(estadoEncendido ? " encendida " : " apagada ") +
				horasVidaEstimada + " horas estimadas de duración";
	}

	
	
}
