package tema1.ejemplosHerencia;

public class Figura {
	// Podrían ser private y entonces en las clases hijas no son visibles, pero se pueden acceder con set/get
	protected int xCentro;
	protected int yCentro;
	protected Color color = Color.AZUL;

	public Figura(int xCentro, int yCentro, Color color) {
		super();
		this.xCentro = xCentro;
		this.yCentro = yCentro;
		this.color = color;
	}

	public int getxCentro() {
		return xCentro;
	}

	public void setxCentro(int xCentro) {
		this.xCentro = xCentro;
	}

	public int getyCentro() {
		return yCentro;
	}

	public void setyCentro(int yCentro) {
		this.yCentro = yCentro;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		if (color==null) {
			System.err.println( "Error no se puede poner color nulo");
			return;
		}
		this.color = color;
	}

	@Override
	public String toString() {
		return xCentro + "," + yCentro + " (" + color + ")";
	}
	
	public double getPerimetro() {
		// TODO Esto podría ser mejorable no?
		return 0;
	}
	
	/** Mueve una figura
	 * @param difX	Diferencial + o - de x a mover
	 * @param difY	Diferenciar + o - de y a mover
	 */
	public void mover( int difX, int difY ) {
		xCentro += difX;
		yCentro += difY;
	}
	
}
