package tema2.resueltos.fechasEInterfaces;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pelicula implements Comparable<Pelicula> {
	private String nombre;
	private LocalDate fecha;
	private String categoria;

	public Pelicula(String nombre, LocalDate fecha, String categoria) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "dd/MM/yy" );
	@Override
	public String toString() {
		return nombre + " (" + categoria + ") - " + fecha.format(dtf);
	}

	@Override
	public int compareTo(Pelicula o) {
		return this.fecha.compareTo( o.fecha );
	}

	
}
