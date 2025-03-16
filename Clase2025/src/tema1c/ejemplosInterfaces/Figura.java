package tema1c.ejemplosInterfaces;

import java.awt.Point;

import javax.swing.JOptionPane;

import utils.ventanas.ventanaBitmap.VentanaGrafica;

public abstract class Figura {
	
	/** Crea y devuelve una figura por defecto, preguntando al usuario qué figura se desea (círculo, rectángulo, línea o imagen)
	 * @param punto	Punto central donde crear la figura
	 * @return	figura creada
	 */
	public static Figura creaFiguraInteractiva( Point punto ) {
		Figura f = null;
		String[] opciones = new String[] { "Círculo", "Rectángulo", "Línea", "Imagen" };
		String resp = (String) JOptionPane.showInputDialog( null, "Figura a crear:", "Selección", JOptionPane.QUESTION_MESSAGE, null, opciones, "Círculo" );
		if (resp==null) { // Escape
			return f;
		} else if (resp.equals("Círculo")) {
			f = new Circulo();
		} else if (resp.equals("Rectángulo")) {
			f = new Rectangulo( 0, 0, 100, 50, Color.ROJO );  // Suponemos este rectángulo por defecto
		} else if (resp.equals("Línea")) {
			f = new Linea( 0, 0, Color.VERDE, 100, Math.PI/4 );  // Suponemos esta línea por defecto
		// Con la imagen
		} else if (resp.equals("Imagen")) {
			f = new Imagen( 0, 0, 100, 100, "logo-chatgpt.png" );
		}
		f.setxCentro( punto.x );
		f.setyCentro( punto.y );
		return f;
	}
	
	
	
	
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
	
	public abstract double getPerimetro();
//		// TODO Esto podría ser mejorable no?
//		return 0;
//	}
	
	/** Mueve una figura
	 * @param difX	Diferencial + o - de x a mover
	 * @param difY	Diferenciar + o - de y a mover
	 */
	public void mover( int difX, int difY ) {
		xCentro += difX;
		yCentro += difY;
	}
	
	public abstract void dibujar(VentanaGrafica v);
	
}
