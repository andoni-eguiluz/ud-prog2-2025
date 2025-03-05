package utils.ventanas.ventanaBitmap;
import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/** Clase ventana sencilla para dibujado directo a la ventana
 * v 1.2.5 - Añadidas funciones de lectura múltiple (pideTextoMultiple, pideCheckBoxMultiple, esperaEntradaMultiple, leeIntMultiple, leeDoubleMultiple, leeCheckBoxMultiple, leeStringMultiple)
 * v 1.2.4 - Añadido dibujaRect con rotación
 * v 1.2.3 - Añadido dibujado de flecha con ángulo
 * v 1.2.2 - Añadido método getRatonClicado. Mejorado método esperaAClick para devolver coordenada de click de ratón
 * v 1.2.1 - Incorpora posibilidad de cambiar el color de fondo y el soporte para clic derecho y central  (gracias a https://github.com/LNDF)
 * v 1.2 - Incorpora posibilidad de cambio de zoom y offset (desplamiento) de origen
 * v 1.1.6 - Incorpora método para dibujar texto centrado
 * v 1.1.5 - Incorpora método para cambiar el tipo de letra de la línea de mensajes, método para consultar el mensaje actual
 * v 1.1.4 - Incorpora métodos para pedir datos desde teclado
 */
public class VentanaGrafica {
	
	// ====================================================
	//   Parte estática - pruebas de funcionamiento
	// ====================================================

	private static VentanaGrafica v;
	/** Método main de prueba de la clase
	 * @param args	No utilizado 
	 */
	public static void main(String[] args) {
		v = new VentanaGrafica( 800, 600, "Test Ventana Gráfica" );
		v.anyadeBoton( "Pon/quita dibujado inmediato", new ActionListener() {  // Para ver cómo se ve con flickering si se dibujan cosas una a una
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setDibujadoInmediato( !v.isDibujadoInmediato() );
				v.setMensaje( "Dibujado inmediato está " + (v.isDibujadoInmediato() ? "ACTIVADO" : "DESACTIVADO") );
			}
		});
		v.setDibujadoInmediato( false );
		Object opcion = JOptionPane.showInputDialog( v.getJFrame(), "¿Qué quieres probar?",
				"Selección de test", JOptionPane.QUESTION_MESSAGE, null, 
				new String[] { "Movimiento", "Giros", "Tiro", "Texto", "Zoom", "Desplazamiento", "Petición texto" }, "Movimiento" );
		if ( "Movimiento".equals( opcion ) ) {
			movimiento();
		} else if ( "Giros".equals( opcion ) ) {
			giros();
		} else if ( "Tiro".equals( opcion ) ) {
			tiro();
		} else if ( "Texto".equals( opcion ) ) {
			texto();
		} else if ( "Zoom".equals( opcion ) ) {
			zoom();
		} else if ( "Desplazamiento".equals( opcion ) ) {
			desplazamiento();
		} else if ( "Petición texto".equals( opcion ) ) {
			peticionTexto();
		}
	}

	// Prueba 1: escudo verde que se mueve y sube y baja
	private static void movimiento() {
		int altura = v.getAltura();
		boolean subiendo = true;
		for (int i=0; i<=800; i++) {
			v.borra();
			v.dibujaTexto( i+100, 100+(i/4), "texto móvil", new Font( "Arial", Font.PLAIN, 16 ), Color.black );
			v.dibujaImagen( "img/UD-green.png", i, altura, 1.0, 0.0, 1.0f );
			if (subiendo) {
				altura--;
				if (altura<=0) subiendo = false;
			} else {
				altura++;
				if (altura>=v.getAltura()) subiendo = true;
			}
			v.repaint();
			v.espera( 10 );
		}
		v.espera( 5000 );
		v.acaba();
	}
	
	// Prueba 2: escudos y formas girando y zoomeando
	private static void giros() {
		for (int i=0; i<=1000; i++) {
			v.borra();
			v.dibujaImagen( "img/UD-green.png", 100, 100, 0.5+i/200.0, Math.PI / 100 * i, 0.9f );
			v.dibujaImagen( "img/UD-magenta.png", 500, 100, 100, 50, 1.2, Math.PI / 100 * i, 0.1f );
			v.dibujaRect( 20, 20, 160, 160, 0.5f, Color.red );
			v.dibujaRect( 0, 0, 100, 100, 1.5f, Color.blue );
			v.dibujaCirculo( 500, 100, 50, 1.5f, Color.orange );
			v.dibujaPoligono( 2.3f, Color.magenta, true, 
				new Point(200,250), new Point(300,320), new Point(400,220) );
			v.repaint();
			v.espera( 10 );
		}
		v.espera( 5000 );
		v.acaba();
	}

	// Prueba 3: tiro parabólico
	private static void tiro() {
		boolean seguir = true;
		v.setMensaje( "Click ratón para disparar (con fuerza y ángulo)");
		double xLanz = 20;
		double yLanz = v.getAltura()-20;
		while (seguir) {
			Point pMovto = v.getRatonMovido();
			Point pPuls = v.getRatonPulsado();
			v.borra();
			v.dibujaCirculo( xLanz, yLanz, 10, 3.0f, Color.MAGENTA );
			if (pPuls!=null) {  // Se hace click: disparar!
				disparar( xLanz, yLanz, pPuls.getX(), pPuls.getY() );
			} else if (pMovto!=null) {  // No se hace click: dibujar flecha
				v.dibujaFlecha( xLanz, yLanz, pMovto.getX(), pMovto.getY(), 2.0f, Color.GREEN, 25 );
			}
			v.repaint();
			if (v.estaCerrada()) seguir = false;  // Acaba cuando el usuario cierra la ventana
			v.espera( 20 ); // Pausa 20 msg (aprox 50 frames/sg)
		}
	}
		// Hace un disparo con la velocidad marcada por el vector con gravedad
		private static void disparar( double x1, double y1, double x2, double y2 ) {
			double velX = x2-x1; double velY = y2-y1;
			double G = 9.8; // Aceleración de la gravedad
			dispararVA( x1, y1, velX, velY, G );
		}
		// Hace un disparo con la velocidad y ángulo indicados
		private static void dispararVA( double x1, double y1, double velX, double velY, double acel ) {
			v.setMensaje( "Calculando disparo con velocidad (" + velX + "," + velY + ")" );
			double x = x1; double y = y1;  // Punto de disparo
			int pausa = 10; // msg de pausa de animación
			double tempo = pausa / 1000.0; // tiempo entre frames de animación (en segundos)
			do {
				v.dibujaCirculo( x, y, 1.0, 1.0f, Color.blue );  // Dibuja punto
				x = x + velX * tempo; // Mueve x (según la velocidad)
				y = y + velY * tempo;// Mueve y (según la velocidad)
				velY = velY + acel * 10 * tempo; // Cambia la velocidad de y (por efecto de la gravedad)
				v.repaint();
				v.espera( pausa ); // Pausa 20 msg (aprox 50 frames/sg)
			} while (y<y1);  // Cuando pasa hacia abajo la vertical se para
			v.espera( 2000 ); // Pausa de 2 segundos
			v.setMensaje( "Vuelve a disparar!" );
		}
	
	// Prueba 4: petición de texto en la ventana
	private static void texto() {
		v.setDibujadoInmediato( true );
		v.dibujaImagen( "img/UD-roller.jpg", 400, 300, 1.0, 0.0, 1.0f );
		Font f = new Font( "Arial", Font.PLAIN, 30 );
		String t1 = v.leeTexto( 100, 100, 200, 50, "Modifica texto", f, Color.magenta );
		v.setMensaje( "Introduce texto" );
		v.dibujaTexto( 100, 200, "Texto introducido: " + t1, f, Color.white );
		v.setMensaje( "Introduce texto otra vez" );
		t1 = v.leeTexto( 100, 300, 200, 50, "", f, Color.blue );
		v.setMensaje( "Textos leídos." );
		v.dibujaTexto( 100, 400, "Texto introducido: " + t1, f, Color.white );
		v.espera( 5000 );
		v.acaba();
	}		
	
	// Prueba 5: zoom y movimiento de referencia de la ventana
	private static void zoom() {
		boolean sigue = true;
		int offset = 0;
		double zoom = 1.0;
		boolean bajando = true;
		while (sigue) {
			dibujoEjemplo( false );
			while (v.isControlPulsado()) v.espera(10);  // Con Ctrl se pausa la animación
			v.espera( 10 );
			if (offset<200) {  // Amplía el offset hasta 200,200
				offset += 4;
				v.setOffsetDibujo( new Point( offset, offset ) );
			} else if (bajando) {
				zoom *= 0.99; // Va bajando el zoom
				v.setEscalaDibujo( zoom );
				if (zoom < 0.5) {  // A partir de 0.5 empieza a subir
					bajando = false; 
				}
			}
			if (!bajando) {
				zoom *= 1.01; // Va subiendo el zoom
				v.setEscalaDibujo( zoom );
				if (zoom > 3.0) {  // Al llegar a 3 se para el programa
					sigue = false;
				}
			}
			v.setMensaje( "Offset " + offset + " - zoom " + zoom );
		}
		while (!v.estaCerrada()) {
			dibujoEjemplo( true );
			Point antMouse = null;
			Point mouse = v.getRatonPulsado();
			Point origen = mouse;
			boolean clickEnOrigen = false;
			if (origen!=null) {
				Point2D.Double puntoClick = v.getPuntoEnEscala( origen );
				if (Math.abs(puntoClick.x)*v.getEscalaDibujo()<5 && Math.abs(puntoClick.y)*v.getEscalaDibujo()<5) {
					clickEnOrigen = true;
				}
			}
			boolean soloClick = true;
			while (mouse!=null) {
				if (clickEnOrigen) {
					int distX = mouse.x-origen.x; // Solo se considera el drag en la x (derecha crecer, izquierda decrecer)
					double incZoom;
					if (distX>=0) {
						incZoom = 1.0 + distX/100.0;  // 100 pixels a la derecha duplica
					} else {
						incZoom = 1.0 + distX/500.0;  // 500 pixels a la derecha minimiza al máximo
					}
					double nuevoZoom = zoom * incZoom;
					if (nuevoZoom < 0.01) nuevoZoom = 0.01;  // Mínimo zoom
					v.setEscalaDibujo( nuevoZoom );
					dibujoEjemplo( true );
					v.setMensaje( "Zoom = " + nuevoZoom );
				} else if (antMouse!=null) {
					v.setOffsetDibujo( mouse.x-antMouse.x, mouse.y-antMouse.y );
					dibujoEjemplo( true );
				}
				v.espera( 10 );
				antMouse = mouse;
				mouse = v.getRatonPulsado();
				if (mouse!=null && !antMouse.equals(mouse)) soloClick = false;
			}
			zoom = v.getEscalaDibujo();
			if (soloClick && origen!=null) {
				String mens = String.format( "  (click en punto anterior (%4.2f,%4.2f)", v.getPuntoEnEscala(origen).x, v.getPuntoEnEscala(origen).y );
				int xOffset = v.getOffsetDibujo().x;
				int yOffset = v.getOffsetDibujo().y;
				v.setEjeYInvertido( !v.getEjeYInvertido() );
				v.setOffsetDibujo( new Point( xOffset, v.getAltura() - yOffset ) );
				v.setMensaje( "Eje Y invertido = " + (v.getEjeYInvertido() ? "SI" : "NO" ) + mens + ", nuevo " + String.format( "(%4.2f,%4.2f)", v.getPuntoEnEscala(origen).x, v.getPuntoEnEscala(origen).y ) + ")");
				dibujoEjemplo( true );
			}
			v.espera( 10 );
		}
		v.espera( 5000 );
		v.acaba();
	}
		private static void dibujoEjemplo( boolean conMensaje ) {
			v.borra();
			v.dibujaEjes( 100, Color.BLACK, 1.0f );
			v.dibujaLinea( -100, 50, 800, 50, 0.5f, Color.CYAN );
			v.dibujaTexto( 100, 50, "texto dibujado", new Font( "Arial", Font.PLAIN, 16 ), Color.black );
			v.dibujaRect( 100, 100, 200, 50, 0.5f, Color.ORANGE );
			v.dibujaTextoCentrado( 100, 100, 200, 50, "texto centrado entre 100 y 300", new Font( "Arial", Font.PLAIN, 16 ), Color.green, true );
			v.dibujaImagen( "img/UD-green.png", 400, 150, 1.0, Math.PI/6.0, 0.4f );
			v.dibujaCirculo( 400, 150, 100.0, 1.0f, Color.BLACK );
			v.dibujaFlecha( 100, 350, 300, 550, 2.0f, Color.MAGENTA, 10 );
			v.dibujaCirculo( 400, 350, 50.0, 2.0f, Color.BLUE, Color.YELLOW );
			v.dibujaPoligono( 3.0f, Color.GREEN, true, new Point( 100, 400 ), new Point( 400, 410 ), new Point( 380, 440 ), new Point( 110, 450 ) );
			v.dibujaRect( 550, 350, 120,  60,  2.0f, Color.MAGENTA, Color.CYAN );
			if (conMensaje) v.dibujaTexto( 10, -20, "Drag para desplazar, drag sobre origen para zoom, Click inversión eje Y", new Font( "Arial", Font.PLAIN, 16 ), Color.RED );
			v.repaint();
		}

	
	// Prueba 6: Desplazamiento de pantalla en mundo virtual mayor que lo que se ve en pantalla
	private static void desplazamiento() {
		v.setDibujadoInmediato( false );
		// Hay un elemento que se dibuja siguiendo al centro de pantalla (empieza 400,300) y otros fijos 
		// El personaje del centro se mueve con cursores y la pantalla se mueve con él
		int xPersonaje = 400;
		int yPersonaje = 300;
		double zoom = 1.0;
		v.setMensaje( "Mueve con cursores. Zoom con +/-" );
		while (!v.estaCerrada()) {
			// Movimiento de personaje
			if (v.isTeclaPulsada(KeyEvent.VK_UP)) {
				yPersonaje -= 5;
			}
			if (v.isTeclaPulsada(KeyEvent.VK_DOWN)) {
				yPersonaje += 5;
			}
			if (v.isTeclaPulsada(KeyEvent.VK_LEFT)) {
				xPersonaje -= 5;
			}
			if (v.isTeclaPulsada(KeyEvent.VK_RIGHT)) {
				xPersonaje += 5;
			}
			// Cambio de zoom
			if (v.isTeclaPulsada(KeyEvent.VK_PLUS)) {
				zoom = zoom *1.01;
				v.setEscalaDibujo(zoom);
			} 
			if (v.isTeclaPulsada(KeyEvent.VK_MINUS)) {
				zoom = zoom / 1.01;
				v.setEscalaDibujo(zoom);
			} 
			v.setOffsetDibujo( new Point( (int) (xPersonaje - v.getAnchuraConEscala()/2), (int) (yPersonaje - v.getAlturaConEscala()/2) ) );
			v.borra();
			v.dibujaCirculo( 0, 0, 80, 5f, Color.PINK, Color.MAGENTA );  // Elemento fijo
			v.dibujaImagen( "img/UD-blue-girable.png", 500, 200, 1.0, 0.0, 1.0f );  // Elemento fijo
			v.dibujaImagen( "img/sonic.png", xPersonaje, yPersonaje, 1.0, 0.0, 1.0f );  // Elemento móvil
			// picking adaptado a coordenadas de ventana
			Point click = v.getRatonPulsado();
			if (click!=null) {
				double xClick = v.getXDePixelEnCoordsVentana( click.x );
				double yClick = v.getYDePixelEnCoordsVentana( click.y );
				System.out.println( click + " -> (" + xClick + "," + yClick + " offset " + v.getOffsetDibujo() + " zoom" + v.getEscalaDibujo() );
				v.dibujaCirculo( xClick, yClick, 10, 2f, Color.MAGENTA, Color.CYAN );
				v.dibujaLinea( xClick, yClick-5, xClick, yClick+5, 1f, Color.BLACK );
				v.dibujaLinea( xClick-5, yClick, xClick+5, yClick, 1f, Color.BLACK );
			}
			v.repaint();
			v.espera(10);
		}
		v.acaba();
	}		
		
	// Prueba 7: Petición de texto, uno a uno y en bloque con botón
	private static void peticionTexto() {
		v.setDibujadoInmediato( true );
		Font font = new Font( "Arial", Font.PLAIN, 20 );
		// Petición texto único
		v.dibujaTexto( 50, 40, "Introduce texto 1", font, Color.BLUE );
		String entrada = v.leeTexto( 250, 10, 200, 40, "<texto>", font, Color.MAGENTA );
		v.dibujaTexto( 50, 80, "Texto introducido: " + entrada, font, Color.BLACK );
		// Petición múltiple
		v.dibujaTexto( 50, 120, "Texto múltiple 1 (int)", font, Color.GREEN );
		v.dibujaTexto( 50, 160, "Texto múltiple 2 (double)", font, Color.GREEN );
		v.dibujaTexto( 50, 200, "Texto múltiple 3 (check)", font, Color.GREEN );
		v.dibujaTexto( 50, 240, "Texto múltiple 4 (String)", font, Color.GREEN );
		v.pideTextoMultiple( 300, 90, 200, 40, "<num 1>", font, Color.MAGENTA );
		v.pideTextoMultiple( 300, 130, 150, 40, "<num 2>", font, Color.MAGENTA );
		v.pideCheckBoxMultiple( 300, 170, 40, 40, true );
		v.pideTextoMultiple( 300, 210, 100, 40, "<texto 4>", font, Color.MAGENTA );
		String boton = v.esperaEntradaMultiple( "Aceptar", "Cancelar" );
		int entrada1 = v.leeIntMultiple(1);
		double entrada2 = v.leeDoubleMultiple(2);
		Boolean entrada3 = v.leeCheckBoxMultiple(3);
		String entrada4 = v.leeStringMultiple(4);
		v.dibujaTexto( 50, 280, "Valores introducidos: " + entrada1 + " / " + entrada2 + " / " + entrada3 + " / " + entrada4, font, Color.BLACK );
		v.dibujaTexto( 50, 320, "Botón pulsado: " + boton, font, Color.BLACK );
		// Otra petición múltiple
		v.dibujaTexto( 50, 360, "Y otra petición (int)", font, Color.GREEN );
		v.dibujaTexto( 50, 400, "Y otra petición (string)", font, Color.GREEN );
		v.pideTextoMultiple( 300, 330, 200, 40, "<num>", font, Color.MAGENTA );
		v.pideTextoMultiple( 300, 370, 150, 40, "<texto>", font, Color.MAGENTA );
		boton = v.esperaEntradaMultiple( "Aceptar", "Cancelar" );
		entrada1 = v.leeIntMultiple(1);
		entrada4 = v.leeStringMultiple(2);
		v.dibujaTexto( 50, 440, "Valores introducidos: " + entrada1 + " / " + entrada4, font, Color.BLACK );
		v.dibujaTexto( 50, 480, "Botón pulsado: " + boton, font, Color.BLACK );
		v.dibujaTexto( 50, 520, "Borrando en 3 segundos...", font, Color.RED );
		v.espera( 3000 );
		// Y otra tras borrar
		v.borra();
		v.dibujaTexto( 50, 160, "Última petición (double)", font, Color.GREEN );
		v.dibujaTexto( 50, 200, "Y otra petición (string)", font, Color.GREEN );
		v.pideTextoMultiple( 300, 130, 200, 40, "<num>", font, Color.MAGENTA );
		v.pideTextoMultiple( 300, 170, 150, 40, "<texto>", font, Color.MAGENTA );
		boton = v.esperaEntradaMultiple( "Aceptar", "Cancelar", "Ignorar" );
		entrada1 = v.leeIntMultiple(1);
		entrada2 = v.leeDoubleMultiple(2);
		v.dibujaTexto( 50, 240, "Valores introducidos: " + entrada1 + " / " + entrada2, font, Color.BLACK );
		v.dibujaTexto( 50, 280, "Botón pulsado: " + boton, font, Color.BLACK );
	}
	
		
	// Parte estática de datos comunes 
	// Métodos estáticos
		
	private static int codTeclaTecleada = 0;
	private static int codTeclaActualmentePulsada = 0;
	private static HashSet<Integer> teclasPulsadas = new HashSet<Integer>();
	private static boolean controlActivo = false;
	// Inicializa el control de teclado
	private static void init() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher( new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					teclasPulsadas.add( e.getKeyCode() );
					codTeclaActualmentePulsada = e.getKeyCode();
					if (e.getKeyCode() == KeyEvent.VK_CONTROL) controlActivo = true; 
				} else if (e.getID() == KeyEvent.KEY_RELEASED) {
					teclasPulsadas.remove( e.getKeyCode() );
					if (e.getKeyCode() == KeyEvent.VK_CONTROL) controlActivo = false; 
					codTeclaTecleada = e.getKeyCode();
					codTeclaActualmentePulsada = 0;
				} else if (e.getID() == KeyEvent.KEY_TYPED) {
				}
				return false;   // false = enviar el evento al comp
			} } );
	}
	
	static {  // Inicializar en la carta de la clase
		init();
	}

	
	// ====================================================
	//   Parte no estática - objeto VentanaGrafica
	// ====================================================
	
	private JFrame ventana;         // Ventana que se visualiza
	private boolean cerrada;        // Lógica de cierre (false al inicio)
	private JPanel panel;           // Panel principal
	private JLabel lMens;           // Etiqueta de texto de mensajes en la parte inferior
	private BufferedImage buffer;   // Buffer gráfico de la ventana
	private Graphics2D graphics;    // Objeto gráfico sobre el que dibujar (del buffer)
	private Point pointPressed;     // Coordenada pulsada de ratón (si existe)
	private Point pointClicked;     // Coordenada clicada de ratón (si existe)
	private boolean botonIzquierdo; // Información de si el último botón pulsado es izquierdo o no lo es
	private boolean botonDerecho;   // Información de si el ultimo botón pulsado es derecho o no lo es
	private boolean botonMedio;     // Información de si el ultimo botón pulsado es el del medio o no lo es
	private Point pointMoved;       // Coordenada pasada de ratón (si existe)
	private Point pointMovedPrev;   // Coordenada pasada anterior de ratón (si existe)
	private boolean dibujadoInmediato = true; // Refresco de dibujado en cada orden de dibujado
	private Point offsetInicio = new Point( 0, 0 );  // Offset de inicio de coordenadas ((0,0) por defecto)
	private double escalaDibujo = 1.0;               // Escala de dibujado (1=1 píxeles por defecto)
	private boolean ejeYInvertido = true;            // Eje Y invertido con respecto a la representación matemática clásica (por defecto true -crece hacia abajo-)
	private Color colorFondo = Color.white;          // El color de fondo
	
		private Object lock = new Object();  // Tema de sincronización de hilos para que el programador usuario no necesite usarlos
	
	/** Construye una nueva ventana gráfica con fondo blanco y la visualiza en el centro de la pantalla
	 * @param anchura	Anchura en píxels (valor positivo) de la zona de pintado
	 * @param altura	Altura en píxels (valor positivo) de la zona de pintado
	 * @param titulo	Título de la ventana
	 */
	public VentanaGrafica( int anchura, int altura, String titulo ) {
		this(anchura, altura, titulo, Color.WHITE );
	}
	
	/** Construye una nueva ventana gráfica y la visualiza en el centro de la pantalla
	 * @param anchura	Anchura en píxels (valor positivo) de la zona de pintado
	 * @param altura	Altura en píxels (valor positivo) de la zona de pintado
	 * @param titulo	Título de la ventana
	 * @param colorDeFondo  El color de fondo de la ventana gráfica
	 */
	@SuppressWarnings("serial")
	public VentanaGrafica(int anchura, int altura, String titulo, Color colorDeFondo) {
		this.colorFondo = colorDeFondo;
		setLookAndFeel();
		cerrada = false;
		ventana = new JFrame( titulo );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// ventana.setSize( anchura, altura ); -- se hace en función del tamaño preferido del panel de dibujo
		buffer = new BufferedImage( 2000, 1500, BufferedImage.TYPE_INT_ARGB );
		graphics = buffer.createGraphics();
		graphics.setPaint ( colorFondo );
		graphics.fillRect ( 0, 0, 2000, 1500 );
		panel = new JPanel() {
			{
				setLayout( new BorderLayout() );
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				((Graphics2D)g).drawImage( buffer, null, 0, 0 );
			}
		};
		panel.setPreferredSize( new Dimension( anchura, altura ));
		panel.setLayout( null );  // Layout nulo para cuando saquemos componentes encima del dibujo
		lMens = new JLabel( " " );
		ventana.getContentPane().add( panel, BorderLayout.CENTER );
		ventana.getContentPane().add( lMens, BorderLayout.SOUTH );
		ventana.pack();
		ventana.setLocationRelativeTo( null );
		ventana.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrada = true;
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// Elimina pulsación de teclas para que no haya pulsación incorrecta al conmutar ventanas
				codTeclaActualmentePulsada = 0;
				codTeclaTecleada = 0;
				teclasPulsadas.clear();
			}
		});
		panel.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				synchronized (lock) {
					pointPressed = null;
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				synchronized (lock) {
					pointPressed = e.getPoint();
					botonIzquierdo = SwingUtilities.isLeftMouseButton(e);
					botonDerecho = SwingUtilities.isRightMouseButton(e);
					botonMedio = SwingUtilities.isMiddleMouseButton(e);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				synchronized (lock) {
					pointClicked = e.getPoint();
					botonIzquierdo = SwingUtilities.isLeftMouseButton(e);
					botonDerecho = SwingUtilities.isRightMouseButton(e);
					botonMedio = SwingUtilities.isMiddleMouseButton(e);
				}
			}
		});
		panel.addMouseMotionListener( new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				synchronized (lock) {
					pointMoved = e.getPoint();
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				synchronized (lock) {
					pointPressed = e.getPoint();
					botonIzquierdo = SwingUtilities.isLeftMouseButton(e);
					botonDerecho = SwingUtilities.isRightMouseButton(e);
					botonMedio = SwingUtilities.isMiddleMouseButton(e);
				}
			}
		});
		Runnable r = new Runnable() {
			@Override
			public void run() {
				ventana.setVisible( true );
			}
		};
		if (SwingUtilities.isEventDispatchThread()) {
			r.run();
		} else {
			try {
				SwingUtilities.invokeAndWait( r );
			} catch (InvocationTargetException | InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	// Intenta poner el look & feel nimbus (solo la primera vez que se crea una ventana)
		private static boolean lookAndFeelIntentado = false;
	private void setLookAndFeel() {
		if (lookAndFeelIntentado) return;
		lookAndFeelIntentado = true;
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            return;
		        }
		    }
		} catch (Exception e) {} // Si no está disponible nimbus, no se hace nada
	}
	
	/** Espera un tiempo y sigue
	 * @param milis	Milisegundos a esperar
	 */
	public void espera( long milis ) {
		try {
			Thread.sleep( milis );
		} catch (InterruptedException e) {
		}
	}
	/** Espera hasta que ocurra una pulsación y suelta de ratón
	 * @return	Devuelve la coordenada de click si ha ocurrido un click (pulsación y suelta en la misma coordenada, null si ha habido un drag (pulsación y suelta en distintas coordenadas)
	 */
	public Point esperaAClick() {
		Point posibleClick = null;
		while (posibleClick==null && !estaCerrada()) {  // Espera a pulsación...
			posibleClick = getRatonPulsado();
		}
		Point sueltaClick = posibleClick;
		while (sueltaClick!=null && !estaCerrada()) {  // ...y espera a suelta
			sueltaClick = getRatonPulsado();
			if (sueltaClick!=null && !sueltaClick.equals(posibleClick)) {
				posibleClick = null;  // Se anula el click si hay un drag
			}
		}
		return posibleClick;
	}
	/** Cierra la ventana (también ocurre cuando se pulsa el icono de cierre)
	 */
	public void acaba() {
		if (!cerrada) ventana.dispose();
		cerrada = true;
	}
	
	/** Devuelve el objeto ventana (JFrame) correspondiente a la ventana gráfica
	 * @return	Objeto JFrame de la ventana
	 */
	public JFrame getJFrame() {
		return ventana;
	}
	
	/** Consultor de estado de visibilidad de la ventana
	 * @return	false si sigue activa, true si ya se ha cerrado
	 */
	public boolean estaCerrada() {
		return cerrada;
	}
	
	/** Devuelve el punto donde está el ratón pulsado en este momento
	 * @return	Punto relativo a la ventana, null si el ratón no está siendo pulsado
	 */
	public Point getRatonPulsado() {
		synchronized (lock) {
			return pointPressed;
		}
	}
	
	/** Devuelve el punto donde se ha hecho click (pulsa y suelta en la misma coordenada) al ratón por última vez. Una vez llamado, se borra el punto (solo se devuelve una vez cada click).
	 * Si han ocurrido varios clicks antes de llamar a este método, solo se devuelve el último.
	 * @return	Punto relativo a la ventana, null si el ratón no ha tenido ningún click desde la última llamada
	 */
	public Point getRatonClicado() {
		synchronized (lock) {
			Point lastClick = pointClicked;
			pointClicked = null;
			return lastClick;
		}
	}
	
	/** Devuelve la información de si el último botón pulsado es izquierdo o no lo es
	 * @return	true si el último botón de ratón pulsado es el izquierdo
	 */
	public boolean isBotonIzquierdo() {
		return botonIzquierdo;
	}
	
	/** Devuelve la información de si el último botón pulsado es derecho o no lo es
	 * @return	true si el último botón de ratón pulsado es el izquierdo
	 */
	public boolean isBotonDerecho() {
		return botonDerecho;
	}
	
	/** Devuelve la información de si el último botón pulsado es el del medio o no lo es
	 * @return	true si el último botón de ratón pulsado es el izquierdo
	 */
	public boolean isBotonMedio() {
		return botonMedio;
	}
	
	/** Devuelve el punto donde está el ratón en este momento
	 * @return	Punto relativo a la ventana, null si el ratón no se ha movido nunca
	 */
	public Point getRatonMovido() {
		synchronized (lock) {
			return pointMoved;
		}
	}
	
	/** Devuelve el último vector de movimiento del ratón (desde la última vez que se llamó a este mismo método)
	 * @return	Punto relativo a la ventana, null si el ratón no se ha movido nunca
	 */
	public Point getVectorRatonMovido() {
		synchronized (lock) {
			Point ret = new Point( 0, 0 );
			if (pointMovedPrev!=null) {
				ret.setLocation( pointMoved.getX()-pointMovedPrev.getX(), pointMoved.getY()- pointMovedPrev.getY() );
			}
			pointMovedPrev = pointMoved;
			return ret;
		}
	}	
	
	/** Cambia el mensaje de la ventana (línea inferior de mensajes)
	 * @param mensaje	Texto de mensaje
	 */
	public void setMensaje( String mensaje ) {
		if (mensaje==null || mensaje.isEmpty())
			lMens.setText( " " );
		else
			lMens.setText( mensaje );
	}
	
	/** Devuelve el mensaje actual de la ventana (línea inferior de mensajes)
	 * @return	Mensaje actual
	 */
	public String getMensaje() {
		return lMens.getText();
	}
	
	/** Cambia el tipo de letra de la línea inferior de mensajes
	 * @param font	Tipo de letra a utilizar
	 */
	public void setMensajeFont( Font font ) {
		lMens.setFont( font );
	}
	
	/** Devuelve el panel de dibujo de la ventana
	 * @return	Panel donde se dibuja en la ventana
	 */
	public JPanel getPanelDibujo() {
		return panel;
	}
	
	/** Devuelve la altura del panel de dibujo de la ventana
	 * @return	Altura del panel principal (última coordenada y) en píxels
	 */
	public int getAltura() {
		return panel.getHeight()-1;
	}
	
	/** Devuelve la altura del panel de dibujo de la ventana
	 * @return	Altura del panel principal (última coordenada y) en unidades de dibujo (aplicando la escala, si la tiene)
	 */
	public double getAlturaConEscala() {
		return panel.getHeight()/escalaDibujo;
	}
	
	/** Devuelve la anchura del panel de dibujo de la ventana
	 * @return	Anchura del panel principal (última coordenada x) en píxels
	 */
	public int getAnchura() {
		return panel.getWidth()-1;
	}
	
	/** Devuelve la anchura del panel de dibujo de la ventana
	 * @return	Anchura del panel principal (última coordenada x) en unidades de dibujo (aplicando la escala, si la tiene)
	 */
	public double getAnchuraConEscala() {
		return panel.getWidth()/escalaDibujo;
	}
	
	/** Borra toda la ventana (pinta de color blanco)
	 */
	public void borra() {
		graphics.setColor( colorFondo );
		graphics.fillRect( 0, 0, panel.getWidth()+2, panel.getHeight()+2 );
		panel.removeAll();  // Por si había componentes de lectura texto previos
		if (dibujadoInmediato) repaint();
	}

	/** Devuelve la coordenada x del espacio de ventana correspondiente a una x de pixels reales sobre el panel gráfico de la ventana
	 * @param xPixel	X de pixels reales en la ventana gráfica
	 * @return	X correspondiente del espacio de ventana, aplicando desplazamiento y escala
	 */
	public double getXDePixelEnCoordsVentana( double xPixel ) {
		return (xPixel + offsetInicio.x * escalaDibujo) / escalaDibujo;
	}
	
	/** Devuelve la coordenada y del espacio de ventana correspondiente a una y de pixels reales sobre el panel gráfico de la ventana
	 * @param yPixel	Y de pixels reales en la ventana gráfica
	 * @return	Y correspondiente del espacio de ventana, aplicando desplazamiento y escala
	 */
	public double getYDePixelEnCoordsVentana( double yPixel ) {
		return (yPixel + offsetInicio.y * escalaDibujo) / escalaDibujo / (ejeYInvertido?1.0:-1.0);
	}
	
		// Convierte x de coordenadas propuestas a coordenadas visuales (con zoom y desplazamiento)
		private double calcX( double x ) {
			return x * escalaDibujo - offsetInicio.x * escalaDibujo;
		}
		// Convierte y de coordenadas propuestas a coordenadas visuales (con zoom y desplazamiento)
		private double calcY( double y ) {
			return (ejeYInvertido?1.0:-1.0) * y * escalaDibujo - offsetInicio.y * escalaDibujo;
		}

	/** Dibuja un rectángulo en la ventana
	 * @param rectangulo	Rectángulo a dibujar
	 * @param grosor	Grueso de la línea del rectángulo (en píxels)
	 * @param color  	Color del rectángulo
	 */
	public void dibujaRect( Rectangle rectangulo, float grosor, Color color ) {
		dibujaRect( rectangulo.getX(), rectangulo.getY(), rectangulo.getX()+rectangulo.getWidth(), rectangulo.getY()+rectangulo.getHeight(), grosor, color );
	}
	
	/** Dibuja un rectángulo en la ventana
	 * @param x	Coordenada x de la esquina superior izquierda del rectángulo
	 * @param y	Coordenada y de la esquina superior izquierda del rectángulo
	 * @param anchura	Anchura del rectángulo (en píxels) 
	 * @param altura	Altura del rectángulo (en píxels)
	 * @param grosor	Grueso del rectángulo (en píxels)
	 * @param color  	Color del rectángulo
	 */
	public void dibujaRect( double x, double y, double anchura, double altura, float grosor, Color color ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		if (ejeYInvertido)
			graphics.drawRect( (int)Math.round(calcX(x)), (int)Math.round(calcY(y)), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		else
			graphics.drawRect( (int)Math.round(calcX(x)), (int)Math.round(calcY(y))-(int)Math.round(altura*escalaDibujo), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		if (dibujadoInmediato) repaint();
	}

	/** Dibuja un rectángulo relleno en la ventana
	 * @param x	Coordenada x de la esquina superior izquierda del rectángulo
	 * @param y	Coordenada y de la esquina superior izquierda del rectángulo
	 * @param anchura	Anchura del rectángulo (en píxels) 
	 * @param altura	Altura del rectángulo (en píxels)
	 * @param grosor	Grueso del rectángulo (en píxels)
	 * @param color  	Color de la línea del rectángulo
	 * @param colorRell	Color del relleno del rectángulo
	 */
	public void dibujaRect( double x, double y, double anchura, double altura, float grosor, Color color, Color colorRell ) {
		graphics.setColor( colorRell );
		graphics.setStroke( new BasicStroke( grosor ));
		if (ejeYInvertido)
			graphics.fillRect( (int)Math.round(calcX(x)), (int)Math.round(calcY(y)), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		else
			graphics.fillRect( (int)Math.round(calcX(x)), (int)Math.round(calcY(y))-(int)Math.round(altura*escalaDibujo), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		graphics.setColor( color );
		if (ejeYInvertido)
			graphics.drawRect( (int)Math.round(calcX(x)), (int)Math.round(calcY(y)), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		else
			graphics.drawRect( (int)Math.round(calcX(x)), (int)Math.round(calcY(y))-(int)Math.round(altura*escalaDibujo), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		if (dibujadoInmediato) repaint();
	}

	/** Dibuja un rectángulo relleno en la ventana
	 * @param x	Coordenada x del centro del rectángulo
	 * @param y	Coordenada y del centro del rectángulo
	 * @param anchura	Anchura del rectángulo (en píxels) 
	 * @param altura	Altura del rectángulo (en píxels)
	 * @param grosor	Grueso del rectángulo (en píxels)
	 * @param color  	Color de la línea del rectángulo
	 * @param colorRell	Color del relleno del rectángulo
	 * @param rotacion	Rotación del rectángulo en radianes
	 */
	public void dibujaRect( double x, double y, double anchura, double altura, float grosor, Color color, Color colorRell, double rotacion ) {
		graphics.translate( calcX(x)*escalaDibujo, calcY(y)*escalaDibujo );
		graphics.rotate( rotacion, 0, 0 );  // Incorporar al gráfico la rotación definida
		graphics.setColor( colorRell );
		graphics.setStroke( new BasicStroke( grosor ));
		if (ejeYInvertido)
			graphics.fillRect( (int)Math.round(calcX(-anchura/2)), (int)Math.round(calcY(-altura/2)), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		else
			graphics.fillRect( (int)Math.round(calcX(-anchura/2)), (int)Math.round(calcY(-altura/2))-(int)Math.round(altura*escalaDibujo), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		graphics.setColor( color );
		if (ejeYInvertido)
			graphics.drawRect( (int)Math.round(calcX(-anchura/2)), (int)Math.round(calcY(-altura/2)), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		else
			graphics.drawRect( (int)Math.round(calcX(-anchura/2)), (int)Math.round(calcY(-altura/2))-(int)Math.round(altura*escalaDibujo), (int)Math.round(anchura*escalaDibujo), (int)Math.round(altura*escalaDibujo) );
		graphics.setTransform( new AffineTransform() );  // Restaurar graphics  (sin rotación ni traslación)
		if (dibujadoInmediato) repaint();
	}
	
	/** Dibuja un rectángulo azul en la ventana
	 * @param x	Coordenada x de la esquina superior izquierda del rectángulo
	 * @param y	Coordenada y de la esquina superior izquierda del rectángulo
	 * @param anchura	Anchura del rectángulo (en píxels) 
	 * @param altura	Altura del rectángulo (en píxels)
	 * @param grosor	Grueso del rectángulo (en píxels)
	 */
	public void dibujaRect( double x, double y, double anchura, double altura, float grosor ) {
		dibujaRect( x, y, anchura, altura, grosor, Color.blue );
	}
	
	/** Borra un rectángulo en la ventana
	 * @param x	Coordenada x de la esquina superior izquierda del rectángulo
	 * @param y	Coordenada y de la esquina superior izquierda del rectángulo
	 * @param anchura	Anchura del rectángulo (en píxels) 
	 * @param altura	Altura del rectángulo (en píxels)
	 * @param grosor	Grueso del rectángulo (en píxels)
	 */
	public void borraRect( double x, double y, double anchura, double altura, float grosor ) {
		dibujaRect( x, y, anchura, altura, grosor, colorFondo );
	}
	
	/** Dibuja un círculo relleno en la ventana
	 * @param x	Coordenada x del centro del círculo
	 * @param y	Coordenada y del centro del círculo
	 * @param radio	Radio del círculo (en píxels) 
	 * @param grosor	Grueso del círculo (en píxels)
	 * @param color  	Color del círculo
	 * @param colorRelleno  	Color de relleno del círculo
	 */
	public void dibujaCirculo( double x, double y, double radio, float grosor, Color color, Color colorRelleno ) {
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.setColor( colorRelleno );
		graphics.fillOval( (int)Math.round(calcX(x)-radio*escalaDibujo), (int)Math.round(calcY(y)-radio*escalaDibujo), (int)Math.round(radio*2*escalaDibujo), (int)Math.round(radio*2*escalaDibujo) );
		graphics.setColor( color );
		graphics.drawOval( (int)Math.round(calcX(x)-radio*escalaDibujo), (int)Math.round(calcY(y)-radio*escalaDibujo), (int)Math.round(radio*2*escalaDibujo), (int)Math.round(radio*2*escalaDibujo) );
		if (dibujadoInmediato) repaint();
	}
	
	/** Dibuja un círculo en la ventana
	 * @param x	Coordenada x del centro del círculo
	 * @param y	Coordenada y del centro del círculo
	 * @param radio	Radio del círculo (en píxels) 
	 * @param grosor	Grueso del círculo (en píxels)
	 * @param color  	Color del círculo
	 */
	public void dibujaCirculo( double x, double y, double radio, float grosor, Color color ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.drawOval( (int)Math.round(calcX(x)-radio*escalaDibujo), (int)Math.round(calcY(y)-radio*escalaDibujo), (int)Math.round(radio*2*escalaDibujo), (int)Math.round(radio*2*escalaDibujo) );
		if (dibujadoInmediato) repaint();
	}
	
	/** Dibuja un círculo azul en la ventana
	 * @param x	Coordenada x del centro del círculo
	 * @param y	Coordenada y del centro del círculo
	 * @param radio	Radio del círculo (en píxels) 
	 * @param grosor	Grueso del círculo (en píxels)
	 */
	public void dibujaCirculo( double x, double y, double radio, float grosor ) {
		dibujaCirculo( x, y, radio, grosor, Color.blue );
	}
	
	/** Borra un círculo en la ventana
	 * @param x	Coordenada x del centro del círculo
	 * @param y	Coordenada y del centro del círculo
	 * @param radio	Radio del círculo (en píxels) 
	 * @param grosor	Grueso del círculo (en píxels)
	 */
	public void borraCirculo( double x, double y, double radio, float grosor ) {
		dibujaCirculo( x, y, radio, grosor, colorFondo );
	}
	
	/** Dibuja una línea en la ventana
	 * @param linea	a dibujar
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param color  	Color de la línea
	 */
	public void dibujaLinea( Line2D linea, float grosor, Color color ) {
		dibujaLinea( linea.getX1(), linea.getY1(), linea.getX2(), linea.getY2(), grosor, color );
	}
	
	/** Dibuja una línea en la ventana
	 * @param x	Coordenada x de un punto de la línea 
	 * @param y	Coordenada y de un punto de la línea
	 * @param x2	Coordenada x del segundo punto de la línea 
	 * @param y2	Coordenada y del segundo punto de la línea
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param color  	Color de la línea
	 */
	public void dibujaLinea( double x, double y, double x2, double y2, float grosor, Color color ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.drawLine( (int)Math.round(calcX(x)), (int)Math.round(calcY(y)), (int)Math.round(calcX(x2)), (int)Math.round(calcY(y2)) );
		if (dibujadoInmediato) repaint();
	}
	
	/** Dibuja una línea azul en la ventana
	 * @param x	Coordenada x de un punto de la línea 
	 * @param y	Coordenada y de un punto de la línea
	 * @param x2	Coordenada x del segundo punto de la línea 
	 * @param y2	Coordenada y del segundo punto de la línea
	 * @param grosor	Grueso de la línea (en píxels)
	 */
	public void dibujaLinea( double x, double y, double x2, double y2, float grosor ) {
		dibujaLinea( x, y, x2, y2, grosor, Color.blue );
	}
	
	/** Borra una línea en la ventana
	 * @param x	Coordenada x de un punto de la línea 
	 * @param y	Coordenada y de un punto de la línea
	 * @param x2	Coordenada x del segundo punto de la línea 
	 * @param y2	Coordenada y del segundo punto de la línea
	 * @param grosor	Grueso de la línea (en píxels)
	 */
	public void borraLinea( double x, double y, double x2, double y2, float grosor ) {
		dibujaLinea( x, y, x2, y2, grosor, colorFondo );
	}

	/** Dibuja una flecha en la ventana
	 * @param linea	a dibujar (el segundo punto es la punta de la flecha)
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param color  	Color de la línea
	 */
	public void dibujaFlecha( Line2D linea, float grosor, Color color ) {
		dibujaFlecha( linea.getX1(), linea.getY1(), linea.getX2(), linea.getY2(), grosor, color );
	}
	
	/** Dibuja una flecha en la ventana
	 * @param x	Coordenada x de un punto de la línea 
	 * @param y	Coordenada y de un punto de la línea
	 * @param x2	Coordenada x del segundo punto de la línea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la línea (el de la flecha)
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param color  	Color de la línea
	 */
	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor, Color color ) {
		dibujaFlecha( x, y, x2, y2, grosor, color, 10 );
	}
	
	/** Dibuja una flecha en la ventana
	 * @param x	Coordenada x de un punto de la línea 
	 * @param y	Coordenada y de un punto de la línea
	 * @param x2	Coordenada x del segundo punto de la línea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la línea (el de la flecha)
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param color  	Color de la línea
	 * @param largoFl	Pixels de largo de la flecha
	 */
	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor, Color color, int largoFl ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		graphics.drawLine( (int)Math.round(calcX(x)), (int)Math.round(calcY(y)), (int)Math.round(calcX(x2)), (int)Math.round(calcY(y2)) );
		double angulo = Math.atan2( y2-y, x2-x ) + Math.PI;
		if (!ejeYInvertido) angulo = Math.atan2( y-y2, x2-x ) + Math.PI;
		double angulo1 = angulo - Math.PI / 10;  // La flecha se forma rotando 1/10 de Pi hacia los dos lados
		double angulo2 = angulo + Math.PI / 10;
		graphics.drawLine( (int)Math.round(calcX(x2)), (int)Math.round(calcY(y2)),
				(int)Math.round(calcX(x2)+largoFl*Math.cos(angulo1)), (int)Math.round(calcY(y2)+largoFl*Math.sin(angulo1)) );
		graphics.drawLine( (int)Math.round(calcX(x2)), (int)Math.round(calcY(y2)), 
				(int)Math.round(calcX(x2)+largoFl*Math.cos(angulo2)), (int)Math.round(calcY(y2)+largoFl*Math.sin(angulo2)) );
		if (dibujadoInmediato) repaint();
	}
	
	/** Dibuja una flecha en la ventana
	 * @param x	Coordenada x de punto central
	 * @param y	Coordenada y de punto central
	 * @param angulo	Ángulo de la flecha (en radianes)
	 * @param largo	Longitud de la flecha (en píxeles)
	 * @param largoFl	Pixels de largo del dibujo de flecha del final
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param color  	Color de la línea
	 */
	public void dibujaFlecha( double x, double y, double angulo, double largo, int largoFl, float grosor, Color color ) {
		double xDest = x + Math.cos(angulo)*largo/2;
		double yDest = y + Math.sin(angulo)*largo/2;
		double xIni = x - Math.cos(angulo)*largo/2;
		double yIni = y - Math.sin(angulo)*largo/2;
		dibujaFlecha( xIni, yIni, xDest, yDest, grosor, color, largoFl );
	}
	
	/** Dibuja una línea azul en la ventana
	 * @param x	Coordenada x de un punto de la línea 
	 * @param y	Coordenada y de un punto de la línea
	 * @param x2	Coordenada x del segundo punto de la línea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la línea (el de la flecha)
	 * @param grosor	Grueso de la línea (en píxels)
	 */
	public void dibujaFlecha( double x, double y, double x2, double y2, float grosor ) {
		dibujaFlecha( x, y, x2, y2, grosor, Color.blue );
	}
	
	/** Borra una línea en la ventana
	 * @param x	Coordenada x de un punto de la línea 
	 * @param y	Coordenada y de un punto de la línea
	 * @param x2	Coordenada x del segundo punto de la línea (el de la flecha)
	 * @param y2	Coordenada y del segundo punto de la línea (el de la flecha)
	 * @param grosor	Grueso de la línea (en píxels)
	 */
	public void borraFlecha( double x, double y, double x2, double y2, float grosor ) {
		dibujaFlecha( x, y, x2, y2, grosor, colorFondo );
	}
	
	/** Dibuja un polígono en la ventana
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param color  	Color de la línea
	 * @param cerrado	true si el polígono se cierra (último punto con el primero), false en caso contrario
	 * @param punto		Puntos a dibujar (cada punto se enlaza con el siguiente)
	 */
	public void dibujaPoligono( float grosor, Color color, boolean cerrado, Point2D... punto ) {
		graphics.setColor( color );
		graphics.setStroke( new BasicStroke( grosor ));
		if (punto.length<2) return;
		Point2D puntoIni = punto[0];
		Point2D puntoAnt = punto[0];
		Point2D pto = null;
		int numPto = 1;
		do {
			pto = punto[numPto];
			graphics.drawLine( (int)Math.round(calcX(puntoAnt.getX())), (int)Math.round(calcY(puntoAnt.getY())), (int)Math.round(calcX(pto.getX())), (int)Math.round(calcY(pto.getY())) );
			puntoAnt = pto;
			numPto++;
		} while (numPto<punto.length);
		if (cerrado) {
			graphics.drawLine( (int)Math.round(calcX(pto.getX())), (int)Math.round(calcY(pto.getY())), (int)Math.round(calcX(puntoIni.getX())), (int)Math.round(calcY(puntoIni.getY())) );
		}
		if (dibujadoInmediato) repaint();
	}
	
	/** Borra un polígono en la ventana
	 * @param grosor	Grueso de la línea (en píxels)
	 * @param cerrado	true si el polígono se cierra (último punto con el primero), false en caso contrario
	 * @param punto		Puntos a borrar (cada punto se enlaza con el siguiente)
	 */
	public void borraPoligono( float grosor, boolean cerrado, Point2D... punto ) {
		dibujaPoligono( grosor, colorFondo, cerrado, punto );
	}

	/** Dibuja un polígono regular en la ventana
	 * @param x	Coordenada x de centro del polígono
	 * @param y	Coordenada y de centro del polígono
	 * @param lados	Número de lados (debe ser mayor o igual que 3, de no serlo no se dibuja nada)
	 * @param radio	Longitud del radio del círculo en el que se inscribe el polígono
	 * @param giro	Giro en radianes en sentido horario del punto inicial del polígono (0 lo inicia en la vertical hacia arriba)
	 * @param grosor	Grueso de la línea de dibujo del polígono (en píxels)
	 * @param color  	Color de la línea de dibujo del polígono
	 */
	public void dibujaPoligonoRegular( double x, double y, int lados, double radio, double giro, float grosor, Color color ) {
		if (lados<=2) return; // Empezamos con triángulos
		Point2D[] arrayVertices = new Point2D[lados];  // Array de puntos de vértice del polígono
		// Calculamos los vértices:
		double giroVertice = giro;
		int numVertice = 0;
		while (numVertice < lados) {
			double pY = -Math.cos(giroVertice) * radio;
			double pX = Math.sin(giroVertice) * radio;
			arrayVertices[numVertice] = new Point2D.Double( pX+x, pY+y );
			giroVertice += (Math.PI*2/lados);
			numVertice++;
		}
		dibujaPoligono( grosor, color, true, arrayVertices );
	}
	
	/** Dibuja un texto en la ventana
	 * @param x	Coordenada x de la esquina inferior izquierda del rectángulo
	 * @param y	Coordenada y de la esquina inferior izquierda del rectángulo
	 * @param texto	Texto a dibujar 
	 * @param font	Tipo de letra con el que dibujar el texto
	 * @param color	Color del texto
	 */
	public void dibujaTexto( double x, double y, String texto, Font font, Color color ) {
		graphics.setColor( color );
		graphics.setFont( font );
		graphics.drawString( texto, (int)Math.round(calcX(x)), (int)Math.round(calcY(y)) );
		if (dibujadoInmediato) repaint();
	}
	
	/** Dibuja un texto en la ventana, centrado en un rectángulo dado
	 * @param x	Coordenada x de la esquina superior izquierda del rectángulo
	 * @param y	Coordenada y de la esquina superior izquierda del rectángulo
	 * @param anchura	Anchura del rectángulo
	 * @param altura	Altura del rectángulo
	 * @param texto	Texto a dibujar 
	 * @param font	Tipo de letra con el que dibujar el texto
	 * @param color	Color del texto
	 */
	public void dibujaTextoCentrado( double x, double y, double anchura, double altura, String texto, Font font, Color color ) {
		Rectangle2D rect = graphics.getFontMetrics(font).getStringBounds(texto, graphics);  // Dimensiones del texto que se va a pintar
		graphics.setColor( color );
		graphics.setFont( font );
		double xCalc = calcX(x) + anchura/2.0*escalaDibujo - rect.getWidth()/2.0;
		double yCalc = calcY(y) + altura*escalaDibujo - graphics.getFontMetrics(font).getDescent() - (altura*escalaDibujo - rect.getHeight())/2.0;
		graphics.drawString( texto, (float)calcX(xCalc), (float)calcY(yCalc) );
		if (dibujadoInmediato) repaint();
	}

	/** Dibuja un texto en la ventana, centrado en un rectángulo dado
	 * @param x	Coordenada x de la esquina superior izquierda del rectángulo
	 * @param y	Coordenada y de la esquina superior izquierda del rectángulo
	 * @param anchura	Anchura del rectángulo
	 * @param altura	Altura del rectángulo
	 * @param texto	Texto a dibujar 
	 * @param font	Tipo de letra con el que dibujar el texto
	 * @param color	Color del texto
	 * @param ajustaSiMayor	true si se disminuye el texto hasta que quepa (si no cabe), false se dibuja con el tamaño indicado.
	 */
	public void dibujaTextoCentrado( double x, double y, double anchura, double altura, String texto, Font font, Color color, boolean ajustaSiMayor ) {
		Rectangle2D rect = graphics.getFontMetrics(font).getStringBounds(texto, graphics);  // Dimensiones del texto que se va a pintar
		while (rect.getWidth() > anchura*escalaDibujo || rect.getHeight() > altura*escalaDibujo) {
			font = new Font( font.getName(), font.getStyle(), font.getSize() - 1 );
			rect = graphics.getFontMetrics(font).getStringBounds(texto, graphics);  // Dimensiones del texto que se va a pintar
		}
		graphics.setColor( color );
		graphics.setFont( font );
		double xCalc = calcX(x) + anchura/2.0*escalaDibujo - rect.getWidth()/2.0;
		double yCalc = calcY(y) + (ejeYInvertido?1.0:-1.0)*(altura*escalaDibujo - graphics.getFontMetrics(font).getDescent() - (altura*escalaDibujo - rect.getHeight())/2.0) + (ejeYInvertido?0.0:rect.getHeight()/2);
		// Vista del cálculo de y (invertida):
		//   Inicio texto y     
		//                |
		//                |
		//                |               -altura en blanco/2  ^
		//                |   -descent  ^                      |
		//       +altura  v             |
		graphics.drawString( texto, (float)xCalc, (float)yCalc );
		if (dibujadoInmediato) repaint();
	}
	
	/** Devuelve el objeto de gráfico sobre el que pintar, correspondiente al 
	 * panel principal de la ventana. Después de actualizar graphics hay que llamar a {@link #repaint()}
	 * si se quiere que se visualice en pantalla
	 * @return	Objeto gráfico principal de la ventana
	 */
	public Graphics2D getGraphics() {
		return graphics;
	}
	
	/** Repinta la ventana. En caso de que el dibujado inmediato esté desactivado,
	 * es imprescindible llamar a este método para que la ventana gráfica se refresque.
	 */
	public void repaint() {
		panel.repaint();
		// panel.paintImmediately( 0, 0, panel.getWidth(), panel.getHeight() );
	}
	
	/** Repinta la ventana de forma inmediata. En caso de que el dibujado inmediato esté desactivado,
	 * es imprescindible llamar a este método para que la ventana gráfica se refresque.
	 */
	public void pintadoInmediato() {
		panel.paintImmediately( 0, 0, panel.getWidth(), panel.getHeight() );
		lMens.paintImmediately( 0, 0, lMens.getWidth(), lMens.getHeight() );
	}

	/** Indica si la tecla Ctrl está siendo pulsada en este momento
	 * @return	true si está pulsada, false en caso contrario
	 */
	public boolean isControlPulsado() {
		return controlActivo;
	}
	
	/** Devuelve el código de la tecla pulsada actualmente
	 * @return	código de tecla pulsada, 0 si no hay ninguna. Si hay varias, se devuelve la última que se pulsó.<br>
	 * 			Si se pulsan varias a la vez, tras soltar la primera este método devuelve 0.
	 */
	public int getCodTeclaQueEstaPulsada() {
		return codTeclaActualmentePulsada;
	}
	
	/** Devuelve el código de la última tecla tecleada (pulsada y soltada). Tras eso, borra la tecla (solo se devuelve una vez)
	 * @return	Código de la última tecla tecleada. Si no ha sido tecleada ninguna o ya se ha consultado, se devuelve 0.
	 */
	public int getCodUltimaTeclaTecleada() {
		int ret = codTeclaTecleada;
		codTeclaTecleada = 0;
		return ret;
	}

	/** Devuelve la información de si una tecla está o no pulsada actualmente
	 * @param codTecla	Código de tecla a chequear
	 * @return	true si la tecla está pulsada, false en caso contrario.
	 */
	public boolean isTeclaPulsada( int codTecla ) {
		return teclasPulsadas.contains(codTecla);
	}
	
	/** Pone modo de dibujado (por defecto el modo es de dibujado inmediato = true)
	 * @param dibujadoInmediato	true si cada orden de dibujado inmediatamente pinta la ventana. false si se
	 * van acumulando las órdenes y se pinta la ventana solo al hacer un {@link #repaint()}.
	 */
	public void setDibujadoInmediato( boolean dibujadoInmediato ) {
		this.dibujadoInmediato = dibujadoInmediato;
	}

	/** Informa del modo de dibujado (por defecto el modo es de dibujado inmediato = true)
	 * @return true si cada orden de dibujado inmediatamente pinta la ventana. false si se
	 * van acumulando las órdenes y se pinta la ventana solo al hacer un {@link #repaint()}.
	 */
	public boolean isDibujadoInmediato() {
		return dibujadoInmediato;
	}

		// Variable local para guardar las imágenes y no recargarlas cada vez
		private static volatile HashMap<String,ImageIcon> recursosGraficos = new HashMap<>();
		
	/** Carga una imagen de un fichero gráfico y la dibuja en la ventana. Si la imagen no puede cargarse, no se dibuja nada.
	 * El recurso gráfico se busca en el paquete de esta clase o en la clase llamadora.
	 * El recurso gráfico se carga en memoria, de modo que al volver a dibujar la misma imagen, no se vuelve a cargar ya de fichero
	 * @param recursoGrafico	Nombre del fichero (path absoluto desde la carpeta raíz de clases del proyecto o relativo desde este paquete)  (p. ej. "img/prueba.png")
	 * @param centroX	Coordenada x de la ventana donde colocar el centro de la imagen 
	 * @param centroY	Coordenada y de la ventana donde colocar el centro de la imagen
	 * @param anchuraDibujo	Pixels de anchura con los que dibujar la imagen (se escala de acuerdo a su tamaño)
	 * @param alturaDibujo	Pixels de altura con los que dibujar la imagen (se escala de acuerdo a su tamaño)
	 * @param zoom	Zoom a aplicar (mayor que 1 aumenta la imagen, menor que 1 y mayor que 0 la disminuye)
	 * @param radsRotacion	Rotación en radianes
	 * @param opacity	Opacidad (0.0f = totalmente transparente, 1.0f = totalmente opaca)
	 */
	public void dibujaImagen( String recursoGrafico, double centroX, double centroY, 
			int anchuraDibujo, int alturaDibujo, double zoom, double radsRotacion, float opacity ) {
		ImageIcon ii = getRecursoGrafico(recursoGrafico); if (ii==null) return;
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR); // Configuración para mejor calidad del gráfico escalado
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		graphics.translate( calcX(centroX)-anchuraDibujo/2*escalaDibujo, calcY(centroY)-alturaDibujo/2*escalaDibujo );
		graphics.rotate( radsRotacion, anchuraDibujo/2*escalaDibujo, alturaDibujo/2*escalaDibujo );  // Incorporar al gráfico la rotación definida
		graphics.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, opacity ) ); // Incorporar la transparencia definida
        int anchoDibujado = (int)Math.round(anchuraDibujo*zoom*escalaDibujo);  // Calcular las coordenadas de dibujado con el zoom, siempre centrado en el label
        int altoDibujado = (int)Math.round(alturaDibujo*zoom*escalaDibujo);
        int difAncho = (int) ((anchuraDibujo* escalaDibujo - anchoDibujado) / 2);  // Offset x para centrar
        int difAlto = (int) ((alturaDibujo* escalaDibujo - altoDibujado) / 2);     // Offset y para centrar
        // graphics.drawRect( difAncho, difAlto, anchoDibujado, altoDibujado );
        graphics.drawImage( ii.getImage(), difAncho, difAlto, anchoDibujado, altoDibujado, null);  // Dibujar la imagen con el tamaño calculado tras aplicar el zoom
		graphics.setTransform( new AffineTransform() );  // Restaurar graphics  (sin rotación ni traslación)
		graphics.setComposite(AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1f ));  // Restaurar graphics (pintado sin transparencia)
		if (dibujadoInmediato) repaint();
	}

	/** Carga una imagen de un fichero gráfico y la dibuja en la ventana. Si la imagen no puede cargarse, no se dibuja nada.
	 * El recurso gráfico se busca en el paquete de esta clase o en la clase llamadora.
	 * El recurso gráfico se carga en memoria, de modo que al volver a dibujar la misma imagen, no se vuelve a cargar ya de fichero
	 * @param recursoGrafico	Nombre del fichero (path absoluto desde la carpeta raíz de clases del proyecto o relativo desde este paquete)  (p. ej. "img/prueba.png")
	 * @param centroX	Coordenada x de la ventana donde colocar el centro de la imagen 
	 * @param centroY	Coordenada y de la ventana donde colocar el centro de la imagen
	 * @param zoom	Zoom a aplicar (mayor que 1 aumenta la imagen, menor que 1 y mayor que 0 la disminuye)
	 * @param radsRotacion	Rotación en radianes
	 * @param opacity	Opacidad (0.0f = totalmente transparente, 1.0f = totalmente opaca)
	 */
	public void dibujaImagen( String recursoGrafico, double centroX, double centroY, 
			double zoom, double radsRotacion, float opacity ) {
		ImageIcon ii = getRecursoGrafico(recursoGrafico); if (ii==null) return;
		dibujaImagen( recursoGrafico, centroX, centroY, ii.getIconWidth(), ii.getIconHeight(), zoom, radsRotacion, opacity);
	}
		// Intenta cargar el recurso gráfico del mapa interno, de la clase actual, o de la clase llamadora. Devuelve null si no se ha podido hacer
		private ImageIcon getRecursoGrafico( String recursoGrafico ) {
			ImageIcon ii = recursosGraficos.get( recursoGrafico );
			if (ii==null) {
				try {
					ii = new ImageIcon( VentanaGrafica.class.getResource( recursoGrafico ));
					recursosGraficos.put( recursoGrafico, ii );
				} catch (NullPointerException e) {  // Mirar si está en la clase llamadora en lugar de en la ventana gráfica
					StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
					for (int i=1; i<stElements.length; i++) {
						StackTraceElement ste = stElements[i];
						if ( !ste.getClassName().endsWith("VentanaGrafica") ) {  // Busca la clase llamadora a VentanaGrafica y busca ahí el recurso
							try {
								Class<?> c = Class.forName( ste.getClassName() );
								URL url = c.getResource( recursoGrafico );
								if (url==null) return null;
								ii = new ImageIcon( url );
								recursosGraficos.put( recursoGrafico, ii );
								return ii;
							} catch (ClassNotFoundException e1) {
								return null;
							}
						}
					}
					return null;
				}			
			}
			return ii;
		}
	
	
		private transient JPanel pBotonera = null;
	/** Añade un botón de acción a la botonera superior
	 * @param texto	Texto del botón
	 * @param evento	Evento a lanzar en la pulsación del botón
	 */
	public void anyadeBoton( String texto, ActionListener evento ) {
		JButton b = new JButton( texto );
		if (pBotonera==null) {
			pBotonera = new JPanel();
			pBotonera.add( b );
			ventana.getContentPane().add( pBotonera, BorderLayout.NORTH );
			ventana.revalidate();
		} else {
			pBotonera.add( b );
			pBotonera.revalidate();
		}
		b.addActionListener( evento );
	}

	/** Añade un escuchador al cambio de tamaño del panel de dibujado de la ventana
	 * @param l	Escuchador de cambio de tamaño a añadir
	 */
	public void addComponentListener( ComponentListener l ) {
		panel.addComponentListener( l );
	}
	
	/** Elimina un escuchador de cambio de tamaño del panel de dibujado de la ventana
	 * @param l	Escuchador de cambio de tamaño a eliminar
	 */
	public void removeComponentListener( ComponentListener l ) {
		panel.removeComponentListener( l );
	}
	
	/** Añade un escuchador de ventana a la ventana
	 * @param l	Escuchador a añadir
	 */
	public void addWindowListener( WindowListener l ) {
		ventana.addWindowListener( l );
	}
	
	/** Elimina un escuchador de ventana de la ventana
	 * @param l	Escuchador a eliminar
	 */
	public void removeWindowListener( WindowListener l ) {
		ventana.removeWindowListener( l );
	}

		// Variable local para cuadro de texto
		private volatile JTextField tfEntrada;
		private volatile boolean finLecturaTexto;
		private volatile String retornoLecturaTexto;
		
	/** Saca un cuadro en la ventana y permite que el usuario introduzca un texto. La llamada
	 * solo retorna después de que el usuario introduzca el texto y pulse [Enter] o [Escape] (o salga del cuadro de texto).
	 * Si se pulsa [Escape] se devuelve null
	 * @param x	Coordenada x de la esquina superior izquierda del cuadro
	 * @param y	Coordenada y de la esquina superior izquierda del cuadro
	 * @param anchura	Anchura en píxels del cuadro de texto
	 * @param altura	Altura en píxels del cuadro de texto
	 * @param texto	Texto inicial en el cuadro
	 * @param font	Tipo de letra con el que sacar el texto en ese cuadro
	 * @param color	Color del texto
	 * @return	Texto leído antes de la pulsación de enter, null si se pulsa escape o se saca el foco del cuadro (por ejemplo al cambiar de ventana)
	 */
	public String leeTexto( double x, double y, int anchura, int altura, String texto, Font font, Color color ) {
		long iniLectura = System.currentTimeMillis();
		if (tfEntrada==null) { 
			tfEntrada = new JTextField();
			tfEntrada.addKeyListener( new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (System.currentTimeMillis()-iniLectura<100) return; // Evita pulsaciones anteriores
					if (e.getKeyCode()==KeyEvent.VK_ENTER) {  // Se acaba cuando se pulsa enter
						finLecturaTexto = true;
						retornoLecturaTexto = tfEntrada.getText();
						tfEntrada.setVisible( false );
						panel.repaint();
					} else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {  // Se acaba cuando se pulsa escape
						finLecturaTexto = true;
						retornoLecturaTexto = null;
						tfEntrada.setVisible( false );
						panel.repaint();
					}
				}
			});
			tfEntrada.addFocusListener( new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if (!finLecturaTexto) {  // Si ya ha acabado la edición no se considera el evento de foco
						finLecturaTexto = true;
						retornoLecturaTexto = null;
						tfEntrada.setVisible( false );
						panel.repaint();
					}
				}
			});
			panel.add( tfEntrada );
		}
		finLecturaTexto = false;
		tfEntrada.setBounds( (int)calcX(x), (int)calcY(y) - (ejeYInvertido?0:(int)(altura*escalaDibujo)), (int)(anchura*escalaDibujo), (int)(altura*escalaDibujo) );
		tfEntrada.setFont( font );
		tfEntrada.setForeground( color );
		tfEntrada.setText( texto==null ? "" : texto );
		tfEntrada.setSelectionStart( 0 );
		tfEntrada.setSelectionEnd( tfEntrada.getText().length() );
		tfEntrada.setVisible( true );
		tfEntrada.requestFocus();
		while (!finLecturaTexto) {
			try { Thread.sleep( 100 ); } catch (Exception e) {}
		}
		return retornoLecturaTexto;
	}

		// Atributos locales para lectura múltiple de texto
		private volatile ArrayList<Component> listaEntradas = new ArrayList<>();
		private volatile ArrayList<String> listaBotones = null;
		private volatile String botonPulsado = null;
		private transient JPanel pBotoneraMultiple = null;
	
	/** Saca un cuadro de petición de texto en la ventana pero no hace nada hasta que se llame posteriormente al método #esperaEntradaMultiple.
	 * En ese momento se pedirán todos los textos acumulados con este método y el #pideCheckBoxMultiple.
	 * Este método puede utilizarse para leer strings, enteros o reales.
	 * @param x	Coordenada x de la esquina superior izquierda del cuadro
	 * @param y	Coordenada y de la esquina superior izquierda del cuadro
	 * @param anchura	Anchura en píxels del cuadro de texto
	 * @param altura	Altura en píxels del cuadro de texto
	 * @param texto	Texto inicial en el cuadro
	 * @param font	Tipo de letra con el que sacar el texto en ese cuadro
	 * @param color	Color del texto
	 */
	public void pideTextoMultiple( double x, double y, int anchura, int altura, String texto, Font font, Color color ) {
		JTextField tfEntradaMultiple = new JTextField();
		tfEntradaMultiple.setBounds( (int)calcX(x), (int)calcY(y) - (ejeYInvertido?0:(int)(altura*escalaDibujo)), (int)(anchura*escalaDibujo), (int)(altura*escalaDibujo) );
		tfEntradaMultiple.setFont( font );
		tfEntradaMultiple.setForeground( color );
		tfEntradaMultiple.setText( texto==null ? "" : texto );
		tfEntradaMultiple.setSelectionStart( 0 );
		tfEntradaMultiple.setSelectionEnd( tfEntradaMultiple.getText().length() );
		tfEntradaMultiple.setVisible( true );
		panel.add( tfEntradaMultiple );
		if (listaBotones==null) {
			listaBotones = new ArrayList<>();
			listaEntradas.clear();
		}
		listaEntradas.add( tfEntradaMultiple );
	}

	/** Saca un cuadro de petición de texto en la ventana pero no hace nada hasta que se llame posteriormente al método #esperaEntradaMultiple.
	 * En ese momento se pedirán todos los textos acumulados con este método y el #pideTextoMultiple.
	 * Este método solo puede utilizarse para leer valores booleanos.
	 * @param x	Coordenada x de la esquina superior izquierda del checkbox
	 * @param y	Coordenada y de la esquina superior izquierda del checkbox
	 * @param anchura	Anchura en píxels del checkbox
	 * @param altura	Altura en píxels del checkbox
	 * @param valorInicial	Valor inicial del checkbox (false o true)
	 */
	public void pideCheckBoxMultiple( double x, double y, int anchura, int altura, boolean valorInicial ) {
		JCheckBox cbEntradaMultiple = new JCheckBox();
		cbEntradaMultiple.setBounds( (int)calcX(x), (int)calcY(y) - (ejeYInvertido?0:(int)(altura*escalaDibujo)), (int)(anchura*escalaDibujo), (int)(altura*escalaDibujo) );
		cbEntradaMultiple.setSelected( valorInicial );
	    cbEntradaMultiple.setIcon(new CheckboxPersonalizado(anchura, altura));
		cbEntradaMultiple.setVisible( true );
		panel.add( cbEntradaMultiple );
		if (listaBotones==null) {
			listaBotones = new ArrayList<>();
			listaEntradas.clear();
		}
		listaEntradas.add( cbEntradaMultiple );
		cbEntradaMultiple.repaint();
	}
	
		class CheckboxPersonalizado implements Icon {
			int anc = 10;
			int alt = 10;
			public CheckboxPersonalizado (int anc, int alt){
				this.anc = anc;
				this.alt = alt;
			}
			protected int getDimension() {
				return Math.max( anc, alt );
			}
			public void paintIcon(Component component, Graphics g, int x, int y) {
				ButtonModel buttonModel = ((AbstractButton) component).getModel();
				if (component.hasFocus() || !component.isEnabled()) {
					g.setColor(Color.LIGHT_GRAY);
				} else {
					g.setColor(Color.WHITE);
				}
				((Graphics2D)g).setStroke( new BasicStroke(2f) );
				g.fillRect( 0, 0, anc, alt );
				g.setColor(Color.BLACK);
				g.drawRect( 0, 0, anc, alt );
				if (buttonModel.isSelected()) {
					g.setColor(Color.GRAY);
					((Graphics2D)g).setStroke( new BasicStroke(3f) );
					g.drawLine( 3, 3, anc-3, alt-3 );
					g.drawLine( 3, alt-3, anc-3, 3 );
				}
			}
	
			public int getIconWidth() {
				return getDimension();
			}
	
			public int getIconHeight() {
				return getDimension();
			}
		}	
	
	/** Saca botones en la ventana y permite que el usuario pulse alguno de ellos, introduciendo datos previamente en todas las entradas de texto o checkboxes
	 * generados con #pideTextoMultiple o #pideCheckBoxMultiple.
	 * Retorna después de pulsado alguno de los botones
	 * @param boton1	Primer botón a indicar para que el usuario acabe la lectura.
	 * @param botones	Serie de botones opcionales adicionales, también acaban la lectura.
	 * @return	Texto del botón pulsado por el usuario para acabar la lectura, null si no había textos de entrada
	 */
	public String esperaEntradaMultiple( String boton1, String... botones ) {
		botonPulsado = null;
		finLecturaTexto = false;
		if (listaBotones==null || listaEntradas.size()==0) {
			return null;
		}
		listaEntradas.get(0).requestFocus();
		listaBotones.clear();
		JButton b = new JButton( boton1 );
		b.addActionListener( new EventoBoton( boton1 ) );
		if (pBotoneraMultiple==null) {
			pBotoneraMultiple = new JPanel();
			pBotoneraMultiple.add( b );
			ventana.getContentPane().add( pBotoneraMultiple, BorderLayout.SOUTH );
		} else {
			pBotoneraMultiple.removeAll();
			pBotoneraMultiple.add( b );
		}
		for (String otroBoton : botones) {
			JButton b2 = new JButton( otroBoton );
			b2.addActionListener( new EventoBoton( otroBoton ) );
			pBotoneraMultiple.add( b2 );
		}
		pBotoneraMultiple.revalidate();
		pBotoneraMultiple.repaint();
		while (!finLecturaTexto) {
			try { Thread.sleep( 100 ); } catch (Exception e) {}
		}
		ventana.getContentPane().remove( pBotoneraMultiple );
		ventana.revalidate();
		pBotoneraMultiple = null;
		listaBotones = null;
		for (Component c : listaEntradas) {
			c.setEnabled( false );
			// panel.remove( c );
		}
		panel.revalidate();
		return botonPulsado;
	}
		private class EventoBoton implements ActionListener {
			private String nombre;
			public EventoBoton( String nombre ) {
				this.nombre = nombre;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				botonPulsado = nombre;
				finLecturaTexto = true;
			}
		}

	/** Devuelve el entero leído en una entrada múltiple. Puede llamarse a este método después de ejecutar {@link #esperaEntradaMultiple(String, String...)}
	 * @param numTextoEntrada	Número secuencial de texto de entrada que quiere leerse (1 para el primero, 2 para el siguiente, etc.)
	 * @return	Entero leído en el texto de entrada indicado. #Integer.MAX_INT si es un entero erróneo o el número indicado es incorrecto
	 */
	public int leeIntMultiple( int numTextoEntrada ) {
		if (numTextoEntrada<1 || numTextoEntrada>listaEntradas.size()) {
			return Integer.MAX_VALUE;
		}
		try {
			return Integer.parseInt( ((JTextField) listaEntradas.get(numTextoEntrada-1)).getText() );
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}
	
	/** Devuelve el entero leído en una entrada múltiple. Puede llamarse a este método después de ejecutar {@link #esperaEntradaMultiple(String, String...)}
	 * @param numTextoEntrada	Número secuencial de texto de entrada que quiere leerse (1 para el primero, 2 para el siguiente, etc.)
	 * @return	Valor real leído en el texto de entrada indicado. #Double.NaN si es un real erróneo o el número indicado es incorrecto
	 */
	public double leeDoubleMultiple( int numTextoEntrada ) {
		if (numTextoEntrada<1 || numTextoEntrada>listaEntradas.size()) {
			return Double.NaN;
		}
		try {
			return Double.parseDouble( ((JTextField) listaEntradas.get(numTextoEntrada-1)).getText() );
		} catch (Exception e) {
			return Double.NaN;
		}
	}
	
	/** Devuelve el entero leído en una entrada múltiple. Puede llamarse a este método después de ejecutar {@link #esperaEntradaMultiple(String, String...)}
	 * @param numEntrada	Número secuencial de entrada que quiere leerse (1 para el primero, 2 para el siguiente, etc.). Debe corresponder a una entrada checkbox
	 * @return	Valor lógico leído en el campo de entrada indicado. null si el número indicado es incorrecto
	 */
	public Boolean leeCheckBoxMultiple( int numEntrada ) {
		if (numEntrada<1 || numEntrada>listaEntradas.size()) {
			return null;
		}
		try {
			return ((JCheckBox) listaEntradas.get(numEntrada-1)).isSelected();
		} catch (Exception e) {
			return null;
		}
	}

	/** Devuelve el entero leído en una entrada múltiple. Puede llamarse a este método después de ejecutar {@link #esperaEntradaMultiple(String, String...)}
	 * @param numTextoEntrada	Número secuencial de texto de entrada que quiere leerse (1 para el primero, 2 para el siguiente, etc.)
	 * @return	String leído en el texto de entrada indicado. null si el número indicado es incorrecto
	 */
	public String leeStringMultiple( int numTextoEntrada ) {
		if (numTextoEntrada<1 || numTextoEntrada>listaEntradas.size()) {
			return null;
		}
		try {
			return ((JTextField) listaEntradas.get(numTextoEntrada-1)).getText();
		} catch (Exception e) {
			return null;
		}
	}
	
	/** Muestra un cuadro de diálogo encima de la ventana y espera a que el usuario lo pulse
	 * @param titulo	Título del diálogo
	 * @param mensaje	Texto a mostrar
	 */
	public void sacaDialogo( String titulo, String mensaje ) {
		JOptionPane.showMessageDialog( ventana, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE );
	}
	
	/** Muestra un cuadro de diálogo de selección de color encima de la ventana y permite al usuario seleccionar un color
	 * @param mens	Mensaje de título del diálogo
	 * @return	Color seleccionado, null si no se ha seleccionado ninguno
	 */
	public Color eligeColor( String mens ) {
		JColorChooser jcc = new JColorChooser();
		int resp = JOptionPane.showConfirmDialog( ventana, jcc, mens, JOptionPane.OK_CANCEL_OPTION );
		return (resp==JOptionPane.OK_OPTION) ? jcc.getColor() : null;
	}
	
	/** Modifica el offset de las coordenadas de la ventana (moviendo el punto de origen 0,0 al indicado)
	 * @param offset	Punto de offset nuevo de dibujado (los cambios no tendrán efecto hasta la siguiente operación de dibujado)
	 */
	public void setOffsetDibujo( Point offset ) {
		offsetInicio = offset;
	}
	
	/** Modifica el offset de las coordenadas de la ventana (moviendo el punto de origen 0,0 al indicado)
	 * @param incrX	Incremento de x de offset
	 * @param incrY	Incremento de y de offset
	 */
	public void setOffsetDibujo( int incrX, int incrY ) {
		offsetInicio.translate( incrX, incrY );
	}

	/** Consulta el offset de las coordenadas de la ventana
	 * @return	Punto actual de offset de dibujado
	 */
	public Point getOffsetDibujo() {
		return offsetInicio;
	}
	
	/** Modifica la escala de dibujado de la ventana
	 * @param escala	Nueva escala (1.0 escala 1=1). Por ejemplo con 0.5 el dibujo se escala a la mitad de tamaño, con 2.0 se escala al doble de tamaño
	 */
	public void setEscalaDibujo( double escala ) {
		this.escalaDibujo = escala;
	}
	
	/** Devuelve la escala de dibujado de la ventana
	 * @return Escala de dibujado (1.0 escala 1=1)
	 */
	public double getEscalaDibujo() {
		return escalaDibujo;
	}
	
	/** Modifica la inversión del eje Y. Actualiza el offset en Y siempre al borde de panel actual (superior o inferior).
	 * @param invertido	true para invertido con respecto a matemáticas clásicas (crece hacia abajo), false para habitual (crece hacia arriba)
	 */
	public void setEjeYInvertido( boolean invertido ) {
		if (ejeYInvertido) {  // Estaba creciente hacia abajo (lo habitual en pantalla) y hay que ponerlo hacia arriba
			offsetInicio.y = getAltura();
		} else {  // Estaba creciente hacia arriba (lo habitual en mates) y hay que ponerlo hacia abajo
			offsetInicio.y = 0;
		}
		ejeYInvertido = invertido;
	}
	
	/** Informa sobre la inversión del eje Y.
	 * @return	true indica eje invertido con respecto a matemáticas clásicas (crece hacia abajo), false para habitual (crece hacia arriba)
	 */
	public boolean getEjeYInvertido() {
		return ejeYInvertido;
	}
	
	/** Establece el color de fondo
	 * @param color El color de fondo a establecer
	 */
	public void setColorFondo(Color color) {
		colorFondo = color;
		graphics.setPaint(color);
	}
	
	/** Devuelve el color de fondo
	 * @return El color de fondo actual
	 */
	public Color getColorFondo() {
		return colorFondo;
	}
	
	/** Devuelve un punto del panel de la ventana convertido a coordenadas de offset y escala
	 * @param punto	Punto lógico en píxels visuales de la ventana
	 * @return	Ese punto convertido a coordenadas de acuerdo al zoom y offset actual
	 */
	public Point2D.Double getPuntoEnEscala( Point punto ) {
		double x = punto.x-offsetInicio.x;
		double y = punto.y-offsetInicio.y;
		if (!ejeYInvertido) {
			y = -y;
		}
		return new Point2D.Double( x/escalaDibujo, y/escalaDibujo );
	}
	
	/** Dibuja los ejes de coordenadas
	 * @param marcaUnidad	Número positivo en el que se marca la unidad del eje (se dibujará con una flecha)
	 * @param colorEjes	Color de los ejes
	 * @param grosor	Grosor de los ejes
	 */
	public void dibujaEjes( int marcaUnidad, Color colorEjes, float grosor ) {
		dibujaLinea( 0, -marcaUnidad*100, 0, marcaUnidad*100, grosor, colorEjes );
		dibujaLinea( -marcaUnidad*100, 0, marcaUnidad*100, 0, grosor, colorEjes );
		dibujaCirculo( 0, 0, 2/escalaDibujo, 1.0f, colorEjes, colorEjes);
		dibujaFlecha( 0, 0, 0, marcaUnidad, grosor, colorEjes, 7 );
		dibujaFlecha( 0, 0, marcaUnidad, 0, grosor, colorEjes, 7 );
	}
	
}
