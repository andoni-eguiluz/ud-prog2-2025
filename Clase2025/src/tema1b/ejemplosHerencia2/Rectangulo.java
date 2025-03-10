package tema1b.ejemplosHerencia2;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Rectangulo extends Figura {
//	private int xCentro;  // NOOOOOOOO volverlos a poner
//	private int yCentro;
//	private Color color;
	private int anchura;
	private int altura;
	
	public Rectangulo(int xCentro, int yCentro, int anchura, int altura, Color color) {
		super(xCentro,yCentro,color);
// Como se incializan los atributos heredados en el constructor de Figura, no hace falta inicializarlos aquí
//		this.setxCentro( xCentro );
//		this.yCentro = yCentro;
//		this.color = color;
		this.anchura = anchura;
		this.altura = altura;
	}

//	public int getxCentro() {
//		return xCentro;
//	}
//
//	public void setxCentro(int xCentro) {
//		this.xCentro = xCentro;
//	}
//
//	public int getyCentro() {
//		return yCentro;
//	}
//
//	public void setyCentro(int yCentro) {
//		this.yCentro = yCentro;
//	}
//
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

//	public Color getColor() {
//		return color;
//	}
//	
//	public void setColor(Color color) {
//		this.color = color;
//	}
	
	@Override
	public String toString() {
		return "Rect " + super.toString();
//		return "Rect " + xCentro + "," + yCentro;
	}
	
	@Override
	public double getPerimetro() {
		return 2 * anchura + 2 * altura;
	}

	@Override
	public void dibujar(VentanaGrafica v) {
		v.dibujaRect( xCentro-anchura/2.0, yCentro-altura/2.0, anchura, altura, 1.0f, color.getColorAWT() );
	}
	
}
