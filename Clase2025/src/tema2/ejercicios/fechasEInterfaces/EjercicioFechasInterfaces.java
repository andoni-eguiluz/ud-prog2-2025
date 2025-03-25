package tema2.ejercicios.fechasEInterfaces;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Iterator;

public class EjercicioFechasInterfaces {

	// Programa el siguiente main descomentando las líneas,
	// creando una clase base Pelicula y una clase contenedora Saga
	
	public static void main(String[] args) {
//		SagaPeliculas saga = new SagaPeliculas( "Marvel",
//			new Pelicula( "Iron Man", LocalDate.of(2008,5,2), "1" ),
//			new Pelicula( "Iron Man 2", LocalDate.of( 2010, 5, 7 ), "1" ),
//			new Pelicula( "Iron Man 3", LocalDate.of(2013, 5, 3), "2"),
//			new Pelicula( "The Incredible Hulk", LocalDate.of( 2008, 6, 13), "1" ),
//			new Pelicula( "Thor", LocalDate.of( 2011, 5, 6 ), "1" ),
//			new Pelicula( "Thor: The Dark World", LocalDate.of(2013, 11, 8), "2"),
//			new Pelicula( "Thor: Ragnarok", LocalDate.of(2017, 11, 3), "3"),
//			new Pelicula( "Captain America: The First Avenger", LocalDate.of(2011, 7, 22), "1"),
//			new Pelicula( "Captain America: The Winter Soldier", LocalDate.of(2014, 4, 4), "2"),
//			new Pelicula( "Capitán América: Civil War", LocalDate.of(2016, 5, 6), "3"),
//			new Pelicula( "Spider-Man: Homecoming", LocalDate.of(2017, 7, 7), "3"),
//			new Pelicula( "Spider-Man: Lejos de casa", LocalDate.of(2019, 7, 4), "3"),
//			new Pelicula( "Guardianes de la Galaxia", LocalDate.of(2014, 8, 1), "2"),
//			new Pelicula( "Guardianes de la Galaxia vol. 2", LocalDate.of(2017, 5, 5), "3"),
//			new Pelicula( "Ant-Man", LocalDate.of(2015, 7, 17), "2"),
//			new Pelicula( "Ant-Man and the Wasp", LocalDate.of(2018, 7, 6), "3"),
//			new Pelicula( "Doctor Strange", LocalDate.of(2016, 11, 4), "3"),
//			new Pelicula( "Black Panther", LocalDate.of(2018, 2, 16), "3"),
//			new Pelicula( "Capitana Marvel", LocalDate.of(2019, 3, 8), "3"),
//			new Pelicula( "The Avengers", LocalDate.of(2012, 5, 4), "1"),													
//			new Pelicula( "Avengers: Age of Ultron", LocalDate.of(2015, 5, 1), "2"),
//			new Pelicula( "Avengers: Endgame", LocalDate.of(2019, 4, 26), "3"),
//			new Pelicula( "Avengers: Infinity War", LocalDate.of(2018, 4, 27), "3")
//		);
		
//		System.out.println( saga );
		// Salida esperada: Marvel: [Iron Man (1) - 02/05/08, Iron Man 2 (1) - 07/05/10, Iron Man 3 (2) - 03/05/13, The Incredible Hulk (1) - 13/06/08, Thor (1) - 06/05/11, Thor: The Dark World (2) - 08/11/13, Thor: Ragnarok (3) - 03/11/17, Captain America: The First Avenger (1) - 22/07/11, Captain America: The Winter Soldier (2) - 04/04/14, Capitán América: Civil War (3) - 06/05/16, Spider-Man: Homecoming (3) - 07/07/17, Spider-Man: Lejos de casa (3) - 04/07/19, Guardianes de la Galaxia (2) - 01/08/14, Guardianes de la Galaxia vol. 2 (3) - 05/05/17, Ant-Man (2) - 17/07/15, Ant-Man and the Wasp (3) - 06/07/18, Doctor Strange (3) - 04/11/16, Black Panther (3) - 16/02/18, Capitana Marvel (3) - 08/03/19, The Avengers (1) - 04/05/12, Avengers: Age of Ultron (2) - 01/05/15, Avengers: Endgame (3) - 26/04/19, Avengers: Infinity War (3) - 27/04/18]
		
//		saga.ordenarDeOtroModo( new ComparadorNombres() );   // Por orden alfabético de nombres (con Comparator)
//		System.out.println( saga );
		// Salida esperada: Marvel: [Ant-Man (2) - 17/07/15, Ant-Man and the Wasp (3) - 06/07/18, Avengers: Age of Ultron (2) - 01/05/15, Avengers: Endgame (3) - 26/04/19, Avengers: Infinity War (3) - 27/04/18, Black Panther (3) - 16/02/18, Capitana Marvel (3) - 08/03/19, Capitán América: Civil War (3) - 06/05/16, Captain America: The First Avenger (1) - 22/07/11, Captain America: The Winter Soldier (2) - 04/04/14, Doctor Strange (3) - 04/11/16, Guardianes de la Galaxia (2) - 01/08/14, Guardianes de la Galaxia vol. 2 (3) - 05/05/17, Iron Man (1) - 02/05/08, Iron Man 2 (1) - 07/05/10, Iron Man 3 (2) - 03/05/13, Spider-Man: Homecoming (3) - 07/07/17, Spider-Man: Lejos de casa (3) - 04/07/19, The Avengers (1) - 04/05/12, The Incredible Hulk (1) - 13/06/08, Thor (1) - 06/05/11, Thor: Ragnarok (3) - 03/11/17, Thor: The Dark World (2) - 08/11/13]

//		saga.ordenarPorFecha();  // Por orden de fecha de película (con Comparable)
//		System.out.println( saga );
		// Salida esperada: Marvel: [Iron Man (1) - 02/05/08, The Incredible Hulk (1) - 13/06/08, Iron Man 2 (1) - 07/05/10, Thor (1) - 06/05/11, Captain America: The First Avenger (1) - 22/07/11, The Avengers (1) - 04/05/12, Iron Man 3 (2) - 03/05/13, Thor: The Dark World (2) - 08/11/13, Captain America: The Winter Soldier (2) - 04/04/14, Guardianes de la Galaxia (2) - 01/08/14, Avengers: Age of Ultron (2) - 01/05/15, Ant-Man (2) - 17/07/15, Capitán América: Civil War (3) - 06/05/16, Doctor Strange (3) - 04/11/16, Guardianes de la Galaxia vol. 2 (3) - 05/05/17, Spider-Man: Homecoming (3) - 07/07/17, Thor: Ragnarok (3) - 03/11/17, Black Panther (3) - 16/02/18, Avengers: Infinity War (3) - 27/04/18, Ant-Man and the Wasp (3) - 06/07/18, Capitana Marvel (3) - 08/03/19, Avengers: Endgame (3) - 26/04/19, Spider-Man: Lejos de casa (3) - 04/07/19]

		System.out.println( "\nRecorrido con iterator:" );
		// TODO - Recorrido con un iterator de saga (que implemente Iterable)
		
		System.out.println( "\nRecorrido con iterable sacando días de diferencia entre películas:" );
		// TODO - Recorrido directo de la saga con un for each

	}
	
}

