package tema1c.resueltos.examen202303a;

import java.util.ArrayList;

public class Huesped extends Usuario {
	protected ArrayList<Reserva> listaReservas;

	public Huesped(String nombre, String apellidos, double saldo, ArrayList<Reserva> listaReservas) {
		super(nombre, apellidos, saldo);
		this.listaReservas = listaReservas;
	}

	public Huesped() {
		super();
		listaReservas = new ArrayList<>();
	}

	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}

	@Override
	public String toString() {
		return "Huesped [listaReservas=" + listaReservas + "] " + super.toString();
	}	
	
}
