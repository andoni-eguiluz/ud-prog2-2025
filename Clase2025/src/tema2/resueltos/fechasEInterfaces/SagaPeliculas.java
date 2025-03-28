package tema2.resueltos.fechasEInterfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class SagaPeliculas implements Iterable<Pelicula> {

	private String nombreSaga;
	private ArrayList<Pelicula> listaPelis = new ArrayList<>();
	
	public SagaPeliculas( String nombreSaga ) {
		this.nombreSaga = nombreSaga;
	}
	
	public SagaPeliculas( String nombreSaga, Pelicula... peliculas ) {
		this.nombreSaga = nombreSaga;
		listaPelis.addAll( Arrays.asList( peliculas ) );
	}
	
	public ArrayList<Pelicula> getListaPelis() {
		return listaPelis;
	}
	
	@Override
	public String toString() {
		return nombreSaga + ": " + listaPelis;
	}
	
	public void ordenarPorFecha() {
		listaPelis.sort(null);
	}

	public void ordenarDeOtroModo(Comparator<Pelicula> comp) {
		listaPelis.sort(comp);
	}

	@Override
	public Iterator<Pelicula> iterator() {
		return new Iterador( this );
	}	
	
}

class Iterador implements Iterator<Pelicula> {
	private SagaPeliculas saga;
	private int recorridoActual;
	
	public Iterador(SagaPeliculas saga) {
		super();
		this.saga = saga;
		recorridoActual = 0;
	}
	
	@Override
	public boolean hasNext() {
		return recorridoActual < saga.getListaPelis().size();
	}
	
	@Override
	public Pelicula next() {
		Pelicula ret = saga.getListaPelis().get( recorridoActual );
		recorridoActual++;
		return ret;
	}
}