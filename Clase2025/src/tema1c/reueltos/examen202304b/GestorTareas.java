package tema1c.reueltos.examen202304b;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

import tema1c.reueltos.examen202304b.gui.VentanaGrafica;


/** Clase principal del gestor de tareas
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class GestorTareas {

	/** Método principal de ejecución del ejercicio
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		GestorTareas mgt = new GestorTareas();
		mgt.run();
	}
	
	// =================== Constantes static
	
	public static final int ANCHURA_VENTANA = 1000;
	public static final int ALTURA_VENTANA = 800;
	
	// =================== Parte no static
	
	VentanaGrafica ventana;
	String nick;
	String password;
	ArrayList<ObjetoGT> listaObjetos;
	Boton botonNuevaTarea;
	Boton botonNuevoCargo;
	Boton botonTrash;

	// TAREA 4 - mapa a utilizar (¿falta hacer algo?)
	private HashMap<Tarea,ArrayList<Point>> mapaTareas
	// TAREA 4: inicialización
		= new HashMap<>();
	
	/** Crea el gestor de tareas con una ventana gráfica asociada de tamaño {@link #ANCHURA_VENTANA} x {@link #ALTURA_VENTANA}
	 */
	public GestorTareas() {
		ventana = new VentanaGrafica( ANCHURA_VENTANA, ALTURA_VENTANA, "Gestor de tareas" );
		ventana.setDibujadoInmediato( false );  // Preparar la ventana para doble buffer (evita el parpadeo en las animaciones)
		listaObjetos = new ArrayList<>();
	}
	
	public ArrayList<ObjetoGT> getListaObjetos() {
		return listaObjetos;
	}

	/** Finaliza el gestor de tareas, cerrando su ventana
	 */
	public void fin() {
		ventana.acaba();
	}
			
	/** Ejecuta el gestor de tareas, lanzando el proceso de ejecución principal
	 */
	public void run() {
		boolean okLogin = login();
		if (okLogin) {
			initDatos();
			interaccion();
		}
	}

	// Proceso de login - devuelve true si es correcto
	private boolean login() {
		nick = JOptionPane.showInputDialog( "Bienvenido/a al gestor de tareas. ¿Nombre de usuario?" );
		if (nick==null) return false; // Si no hay nick se acaba
		password = JOptionPane.showInputDialog( "Hola " + nick + ". Introduce password:" );
		if (password==null) return false; // Si no hay password también 
		// TODO Gestión de validación de usuario
		return true;
	}
	
	// Proceso de inicializar datos de la ventana de trabajo
	private void initDatos() {
		// TAREA 1
		listaObjetos.add( new TrabajadorResponsable( 450, 200, ventana, "Chris", "gui/chris.png" ) );
		listaObjetos.add( new TrabajadorResponsable( 800, 200, ventana, "Julia", "gui/julia.png" ) );
		listaObjetos.add( new Trabajador( 450, 600, ventana, "Natalie", "gui/natalie.png" ) );
		listaObjetos.add( new Trabajador( 800, 600, ventana, "Jim", "gui/jim.png" ) );
		botonNuevaTarea = new Boton( 40, 40, ventana, "NUEVA", "gui/add.png" );
		botonNuevoCargo = new Boton( 40, 320, ventana, "CARGO", "gui/add2.png" );
		botonTrash = new Boton( 40, 760, ventana, "PAPELERA", "gui/trash.png" );
		listaObjetos.add( botonNuevaTarea );
		listaObjetos.add( botonNuevoCargo );
		listaObjetos.add( botonTrash );
		ventana.setMensaje( "Pulsa el botón de añadir si quieres añadir tarea, arrastra tarea para asignarla." );
		// TAREA 2
		botonTrash.setRespondeAClick( false );
	}
	
	// Proceso de gestión de interacción de ratón por parte del usuario
	private void interaccion() {
		dibujaTodo();
		while (!ventana.estaCerrada()) {
			Point p = ventana.getRatonPulsado();
			if (p!=null) {
				for (int i=listaObjetos.size()-1; i>=0; i--) { // Atención: la recorremos al revés para que se detecten los clicks primero en los elementos más "ontop"
					ObjetoGT o = listaObjetos.get(i);
					if (o.contienePunto( p.x, p.y )) {
						pulsadoObjeto( o, p );
						break;
					}
				}
			}
			ventana.espera( 20 );
		}
	}
	
	// Acción si se pulsa un objeto con el ratón
	private void pulsadoObjeto( ObjetoGT objPulsado, Point pPulsado ) {
		// TAREA 2
		if (objPulsado instanceof Clicable && ((Clicable)objPulsado).estaRespondiendoAClick()) {  // Tratamiento de botón: pulsado espera al click = pulsación
			Point p = ventana.getRatonPulsado();
			boolean click = true;
			while (p!=null) {
				ventana.espera( 20 ); // Pequeña espera
				if (!p.equals(pPulsado)) {
					click = false;
					break;
				}
				p = ventana.getRatonPulsado();
			}
			if (click) {
				if (objPulsado == botonNuevaTarea) {
					ventana.setMensaje( "Creada nueva tarea" );
				} else if (objPulsado == botonNuevoCargo){
					ventana.setMensaje( "Creado nuevo cargo" );
				}
				((Clicable)objPulsado).ejecuta( this );
				dibujaTodo();
			}
		// TAREA 3
		} else if (objPulsado instanceof Interactivo || objPulsado instanceof Trabajador) {  // Tratamiento de tarea o trabajador: puede ser un click o un drag
			Point p = ventana.getRatonPulsado();
			while (p!=null && p.equals(pPulsado)) {  // Esperar a que se mueva o se suelte
				ventana.espera( 20 ); // Pequeña espera
				p = ventana.getRatonPulsado();
			}
			if (p==null) { // Se ha soltado: es un click - solo se considera en algunos casos
				if (objPulsado instanceof Interactivo) {
					Interactivo interactivo = (Interactivo) objPulsado;
					if (ventana.isBotonIzquierdo()) { // Es botón izquierdo - click: cambio de nombre
						String nuevoTexto = JOptionPane.showInputDialog( "Cambia el texto de " + interactivo.getTexto(), interactivo.getTexto() );
						if (nuevoTexto==null) return; // Si no hay texto no se cambia nada y se acaba
						interactivo.setTexto( nuevoTexto );
						ventana.setMensaje( "Cambiado texto a " + nuevoTexto );
					} else { // Es botón derecho - click: cambio de color de tarea
						String[] opciones = { "Verde", "Azul", "Rojo", "Naranja", "Amarillo" };
						Color[] opcionesColor = { Color.GREEN, Color.CYAN, Color.RED, Color.ORANGE, Color.YELLOW };
						String seleccion = (String) JOptionPane.showInputDialog( null, "Elige un color:", "Selección", JOptionPane.QUESTION_MESSAGE, null, opciones, "Amarillo" );
						if (seleccion!=null) {
							int donde = Arrays.asList( opciones ).indexOf( seleccion );
							interactivo.setColor( opcionesColor[donde] );
						}
					}
					dibujaTodo();
				}
			} else { // Se ha movido: es un drag
				dragObjeto( objPulsado, pPulsado, p );
			}
		}
	}
	
	private void dragObjeto( ObjetoGT obj, Point pInicial, Point pActual ) {
		// TAREA 3
		if (obj instanceof Trabajador || obj instanceof Interactivo) {  // Solo se puede hacer drag para tarea, cargo o trabajador
			int xInicial = obj.getX();
			int yInicial = obj.getY();
			Trabajador trabObjetivo = null;
			// Mientras haya drag, visualizarlo
			while (pActual!=null) {
				int difX = pActual.x - pInicial.x;
				int difY = pActual.y - pInicial.y;
				obj.mover( obj.getX()+difX, obj.getY()+difY );
				dibujaTodo( obj );  // Dibuja el objeto móvil "encima" para que se vea siempre mientas se hace el drag
				ventana.espera( 20 ); // Pequeña espera
				pInicial = pActual;
				pActual = ventana.getRatonPulsado();
			}
			// Al final del drag, si es una tarea o cargo hay que moverlo
			// TAREA 3
			if (obj instanceof Interactivo) {
				// comprobar si está dentro de algún trabajador o de la papelera
				boolean dragCorrecto = false;
				for (ObjetoGT gt : listaObjetos) {
					if (gt.contienePunto( obj.getX(), obj.getY() )) {
						if (gt instanceof Trabajador) {  // Es un trabajador: asignarla
							// TAREA 1
							if (!(gt instanceof TrabajadorResponsable) && obj instanceof Cargo) { // Salvo que sea un cargo a un trabajador no responsable
								break;
							}
							trabObjetivo = (Trabajador) gt;
							dragCorrecto = true;
							break;
						} else if (gt==botonTrash) {  // Es la papelera: borrarla
							listaObjetos.remove( obj );
							dragCorrecto = true;
							break;
						}
					}
				}
				if (!dragCorrecto) {  // Si el drag no es correcto, volver a su sitio
					obj.mover( xInicial, yInicial );  // Podría hacerse con animación: lo hacemos de golpe (cambiar si se desea animado)
				}
				// Si es una tarea, Reasignar la tarea al trabajador que corresponda quitándosela a otro si la tuviera
				if (dragCorrecto && obj instanceof Tarea) {
					Tarea tarea = (Tarea) obj;
					movidaTarea( new Tarea( tarea ), tarea.getX(), tarea.getY() );
					for (ObjetoGT gt : listaObjetos) {
						if (gt instanceof Trabajador) {
							Trabajador trab = (Trabajador) gt;
							if (trab.getTareasAsignadas().contains( tarea )) {
								trab.getTareasAsignadas().remove( tarea );
								break;
							}
						}
					}
					if (trabObjetivo!=null) {
						trabObjetivo.getTareasAsignadas().add( tarea );
					}
				// TAREA 1
				} else if (dragCorrecto && obj instanceof Cargo) {
					Cargo cargo = (Cargo) obj;
					for (ObjetoGT gt : listaObjetos) {
						if (gt instanceof TrabajadorResponsable) {
							TrabajadorResponsable trab = (TrabajadorResponsable) gt;
							if (trab.getCargos().contains( cargo )) {
								trab.getCargos().remove( cargo );
								break;
							}
						}
					}
					if (trabObjetivo!=null && trabObjetivo instanceof TrabajadorResponsable) {
						((TrabajadorResponsable)trabObjetivo).getCargos().add(cargo);
					}
				}
			}
			dibujaTodo();
		}
	}
	
	private void movidaTarea( Tarea tareaParaMapa, int x, int y ) {
		// TAREA 4 - Incorporar esta tareaParaMapa -ya duplicada- en el mapa, con las coordenadas recibidas
		if (!mapaTareas.containsKey(tareaParaMapa)) {
			// Primera vez que llega la tarea - se añade la lista
			mapaTareas.put( tareaParaMapa, new ArrayList<>() );
		}
		// En cualquiera de los casos, se añade el punto a la lista
		mapaTareas.get( tareaParaMapa ).add( new Point( x, y ) );
	}

	// Dibuja todo
	private void dibujaTodo() {
		dibujaTodo( null );
	}
	
	// Dibuja todo y el objeto indicado lo dibuja el último (para que quede por encima)
	private void dibujaTodo( ObjetoGT objetoFinal ) {
		ventana.borra();
		for (ObjetoGT o : listaObjetos) {
			o.dibujar();
		}
		if (objetoFinal!=null) {
			objetoFinal.dibujar();
		}
		ventana.repaint();  // Dibuja por doble buffer (evitando el parpadeo)
		// TODO Quitar si no se quiere el log - Log en consola en cada dibujado
		System.out.println( "Dibujando ventana. Lista de objetos dibujados:" );
		for (ObjetoGT gt : listaObjetos) {
			System.out.println( "  " + gt );
		}
		// TAREA 4 - Mostrar el mapa de movimientos de tareas
		for (Tarea tarea : mapaTareas.keySet()) {
			System.out.println( tarea + " - movimientos: " + mapaTareas.get( tarea ) );
		}
	}

}
