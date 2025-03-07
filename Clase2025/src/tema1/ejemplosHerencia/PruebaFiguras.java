package tema1.ejemplosHerencia;

public class PruebaFiguras {
	
	public static void main(String[] args) {
		Rectangulo r1 = new Rectangulo( 50, 25, 40, 30, Color.AZUL );
		System.out.println( r1 + " -> perímetro = " + r1.getPerimetro() );
		Circulo c1 = new Circulo( 20, 30, Color.AMARILLO, 15 );
		System.out.println( c1 + " -> perímetro = " + c1.getPerimetro());
		Linea l1 = new Linea( 10, 35, Color.ROJO, 23, 5 );
		System.out.println( l1 + " -> perímetro = " + l1.getPerimetro());
		
		Figura f1 = new Figura(10,10,Color.VERDE);
		System.out.println( f1 + " -> perímetro = " + f1.getPerimetro());
		
		// Y... ¿cómo mover todas las figuras?
		r1.mover( 5, -5 );
		c1.mover( 5, -5 );
		l1.mover( 5, -5 );
		f1.mover( 5, -5 );
		
		// Y... ¿tiene sentido la figura f1?
		
	}
	
}
