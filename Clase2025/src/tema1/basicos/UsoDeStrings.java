package tema1.basicos;

/** Operativa básica con clase string
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class UsoDeStrings {
	public static void main(String[] args) {
		bases();
		metodos();
	}
	
	private static String s1;
	private static String s2;
	private static String s3;

	private static void bases() {
		s1 = "hola";
		// El new es implícito, esto es lo mismo que String s1 = new String("hola");
		s2 = "adiós Fernando";
		s3 = "hol";
		s3 = s3 + "a";
		// Dentro del string caben secuencias de escape con \: \n (salto de línea), \t (tab), \\ (backslash), \"...
		System.out.println( "s1 = " + s1 + "\ns2 = " + s2 + "\ns3 = " + s1 );
		// Ojo con la comparación: por == solo compara objetos 
		System.out.println( "s1 == s3? " + (s1==s3) ); // Cualquier valor concatenado con un string se convierte a string, como este boolean
		// El contenido lo compara equals
		System.out.println( "s1.equals(s3)? " + s1.equals(s3) );
	}
	
	private static void metodos() {
		// Comparación alfabética entre strings:
		System.out.println( s1 + " comparado con " + s2 + " = " + s1.compareTo(s2) + " (>0 significa alfabéticamente mayor que)" );
		System.out.println( s1 + " comparado con " + s3 + " = " + s1.compareTo(s3) + " (0 significa igual)" );
		System.out.println( s1 + " comparado con holo = " + s1.compareTo("holo") + " (<0 significa menor que)" );
		System.out.println( s1 + " comparado con holA = " + s1.compareTo("holA") + " (minúsculas MAYORES que mayúsculas en ASCII)");
		// Comprobar si un string está dentro de otro
		System.out.println( "s1.contains( \"ol\" ) = " + s1.contains( "ol" ) );
		System.out.println( "s1.indexOf( \"ol\" ) = " + s1.indexOf( "ol" ) + " (posición de carácter inicial)");
		System.out.println( "s1.indexOf( \"el\" ) = " + s1.indexOf( "el" ) + " (-1 es que no está)" );
		// Longitud
		System.out.println( "Longitud de \"" + s2 + "\" = " + s2.length() + " (indexado de 0 a " + (s2.length()-1) + ")" );
		// Sacar caracteres de un string
		System.out.println( "Los caracteres de \"" + s2 + "\" son:");
		for (int posi=0; posi<s2.length(); posi++) {
			System.out.print( " " + s2.charAt(posi) );
		}
		System.out.println();
		// Extraer substrings
		int posiEspacio = s2.indexOf(" ");
		if (posiEspacio!=-1) {
			System.out.println( "Substring de s2 desde " + (posiEspacio+1) + " = \"" + s2.substring(posiEspacio+1) + "\"" );
		}
		System.out.println( "Substring de s2 desde 1 (inclusive) a 3 (exclusive): \"" + s2.substring(1,3) + "\"" );
		
		System.out.println( "Hay más métodos interesantes en la clase String:" );
		System.out.println( "  equalsIgnoreCase, isEmpty, lastIndexOf, replace, replaceAll, split, trim, toLowerCase, toUpperCase... ¡explóralos!" );
	}
}
