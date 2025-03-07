package tema1.ejemplosHerencia;

public class Circulo extends Figura {
//	private int xCentro;
//	private int yCentro;
//	private Color color;
	private int radio = 5;

	public Circulo(int xCentro, int yCentro, Color color, int radio) {
		super( xCentro, yCentro, color );
// Como se incializan los atributos heredados en el constructor de Figura, no hace falta inicializarlos aquí
//		this.xCentro = xCentro;
//		this.yCentro = yCentro;
//		this.color = color;
		this.radio = radio;
	}
	
	/** Crea un círculo en 0,0 de color azul
	 * @param radio	Radio del nuevo círculo (positivo)
	 */
	public Circulo( int radio ) {
		super( 0, 0, Color.AZUL );
		this.radio = radio;
	}
	
	/** Crea un círculo en la coordenada 10,10
	 * amarillo y de radio 5
	 */ 
	public Circulo() {
		super( 10, 10, Color.AMARILLO );
		radio = 5;
	}
	
	
//	public int getxCentro() {
//		return xCentro;
//	}
//	public void setxCentro(int xCentro) {
//		this.xCentro = xCentro;
//	}
//	public int getyCentro() {
//		return yCentro;
//	}
//	public void setyCentro(int yCentro) {
//		this.yCentro = yCentro;
//	}
//	public Color getColor() {
//		return color;
//	}
//	public void setColor(Color color) {
//		this.color = color;
//	}
	public int getRadio() {
		return radio;
	}
	
	// TODO control de error radio negativo
	public void setRadio(int radio) {
		this.radio = radio;
	}

	@Override
	public String toString() {
		return "Circ " + super.toString();
//		return "Circ " + xCentro + "," + yCentro;
	}

	@Override
	public double getPerimetro() {
		return Math.PI * 2 * radio;
	}
	
	
	
}
