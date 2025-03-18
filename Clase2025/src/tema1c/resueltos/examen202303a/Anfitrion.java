package tema1c.resueltos.examen202303a;

import java.util.ArrayList;

public class Anfitrion extends Usuario {
	protected ArrayList<Alojamiento> listaAlojamientos;

	public Anfitrion(String nombre, String apellidos, double saldo, ArrayList<Alojamiento> listaAlojamientos) {
		super(nombre, apellidos, saldo);
		this.listaAlojamientos = listaAlojamientos;
	}

	public Anfitrion() {
		super();
		listaAlojamientos = new ArrayList<>();
	}

	public ArrayList<Alojamiento> getListaAlojamientos() {
		return listaAlojamientos;
	}

	@Override
	public String toString() {
		return "Anfitrion [listaAlojamientos=" + listaAlojamientos + "] " + super.toString();
	}

}
