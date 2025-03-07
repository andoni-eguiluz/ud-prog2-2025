package tema1.ejemplosHerencia;

public class Linea extends Figura {
	private int longitud;
	private double angulo;
	
	public Linea(int xCentro, int yCentro, Color color, int longitud, double angulo) {
		super(xCentro, yCentro, color);
		this.longitud = longitud;
		this.angulo = angulo;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
	
	@Override
	public String toString() {
		return "Lin " + super.toString();
	}
	
	@Override	
	public double getPerimetro() {
		return longitud;
	}
	
}
