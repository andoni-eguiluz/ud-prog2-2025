package tema1.ejemplosHerencia;

public class Rectangulo {
	private int xCentro;
	private int yCentro;
	private Color color;
	private int anchura;
	private int altura;
	
	public Rectangulo(int xCentro, int yCentro, int anchura, int altura, Color color) {
		this.xCentro = xCentro;
		this.yCentro = yCentro;
		this.anchura = anchura;
		this.altura = altura;
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

	public int getAnchura() {
		return anchura;
	}

	public void setAnchura(int anchura) {
		this.anchura = anchura;
	}

	public int getAltura() {
		return altura;
	}

	// TODO Controlar alturas no negativas y lo mismo con anchuras 
	
	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return xCentro + "," + yCentro;
	}
	
}
