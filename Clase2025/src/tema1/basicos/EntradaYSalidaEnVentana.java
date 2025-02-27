package tema1.basicos;

import javax.swing.JOptionPane;

/** Clase de ejemplo de entrada y salida de datos por ventanas de diálogo (pop-ups)
 * con la clase JOptionPane de Java
 * @author andoni.eguiluz at ingenieria.deusto.es
 */
public class EntradaYSalidaEnVentana {

	public static void main(String[] args) {
		// Ejemplo de salida de texto multilínea por ventana
		JOptionPane.showMessageDialog( null, "Hola!\nEsto es un ejemplo de E/S básica\nbasada en ventanas interactivas\nPulsa <Continuar> para seguir" );
		String input;
		// Ejemplo de entrada de string por teclado
		input = JOptionPane.showInputDialog( "Introduce un string cualquiera:" );
		// Ejemplo de salida de string por teclado
		JOptionPane.showMessageDialog( null, "El string introducido es: " + input );
		System.out.println( "El string introducido es: " + input );  // Salida equivalente a consola
		// Ejemplo de conversión de string a entero
		input = JOptionPane.showInputDialog( "Introduce un entero cualquiera:" );
		int valor = Integer.parseInt( input );
		JOptionPane.showMessageDialog( null, "El entero introducido es: " + valor );
		System.out.println( "El entero introducido es: " + valor );
		// Ejemplo de entrada de un valor entre varias opciones
		String[] opciones = { "Verde", "Azul", "Rojo" };
		input = (String) JOptionPane.showInputDialog( null, "Elige un color:", "Selección", JOptionPane.QUESTION_MESSAGE, null, opciones, "Verde" );
		JOptionPane.showMessageDialog( null, "El color elegido es: " + input );
		System.out.println( "El color elegido es: " + input );
		// Ejemplo de entrada por botón de confirmación
		int opcion = JOptionPane.showConfirmDialog( null, "¿Te ha gustado la experiencia?", "Selección con botón", JOptionPane.YES_NO_CANCEL_OPTION );
		JOptionPane.showMessageDialog( null, "La opción elegida es: " + opcion );
		// Si no se elige ninguna es -1
		// Si se elige alguna, es una constante numérica de estas: JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION
		// La opciones que se pueden pedir son: JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_CANCEL_OPTION,or JOptionPane.OK_CANCEL_OPTION
	}

}
