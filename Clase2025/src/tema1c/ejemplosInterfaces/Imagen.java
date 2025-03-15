package tema1c.ejemplosInterfaces;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public class Imagen extends Rectangulo implements Rotable {
	private String nombre;
	private double anguloDeGiro = 0.0; // Radianes

	public Imagen(int xCentro, int yCentro, int anchura, int altura, String nombre) {
		super(xCentro, yCentro, anchura, altura, Color.AZUL);
		this.nombre = nombre;
	}

	
	@Override
	public void dibujar(VentanaGrafica v) {
		v.dibujaImagen( "logo-chatgpt.png", xCentro, yCentro, getAnchura(), getAltura(), 1.0, anguloDeGiro, 1.0f );
	}

	@Override
	public void rotar(double angulo) {
		anguloDeGiro += angulo;
	}
	
	
	
	// TODO Implementar también estos

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


}
