package tema1c.resueltos.examen202303a;

public class Reserva implements Confirmable {
	private Huesped huesped;
	private Alojamiento alojamiento;
	private int numDias;
	private boolean confirmada;
	private TipoValoracion valoracion;
	
	public Reserva(Huesped huesped, Alojamiento alojamiento, int numDias, boolean confirmada,
			TipoValoracion valoracion) {
		super();
		this.huesped = huesped;
		this.alojamiento = alojamiento;
		this.numDias = numDias;
		this.confirmada = confirmada;
		this.valoracion = valoracion;
	}

	public Reserva() {
		super();
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public int getNumDias() {
		return numDias;
	}

	public void setNumDias(int numDias) {
		this.numDias = numDias;
	}

	public boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

	public TipoValoracion getValoracion() {
		return valoracion;
	}

	public void setValoracion(TipoValoracion valoracion) {
		this.valoracion = valoracion;
	}

	@Override
	public String toString() {
		return "Reserva [huesped=" + huesped.getNombre() + ", alojamiento=" + alojamiento + ", numDias=" + numDias + ", confirmada="
				+ confirmada + ", valoracion=" + valoracion + "]";
	}
	
//	Todas las reservas son confirmables, es
//	decir, implementan un método confirmar que comprueba si el saldo del huésped 
//	es superior al coste de la reserva (dias * precio) y si es así, 
//	marca como confirmada la reserva y actualiza el saldo del huésped
//	(retándole el coste de la reserva) 
//	y el del anfitrión (sumándole el coste de la reserva).
	@Override
	public void confirmar() {
		double costeReserva = numDias * alojamiento.getPrecio();
		if (huesped.getSaldo() >= costeReserva) {
			confirmada = true;
			huesped.setSaldo( huesped.getSaldo() - costeReserva);
			// Anfitrion.setSaldo  -- nooooooooooo
			// Anfitrion anf = new Anfitrion();  --- tampocoooooooo
			// anf.setSaldo( ... );
			alojamiento.getAnfitrion().setSaldo( 
				alojamiento.getAnfitrion().getSaldo() + costeReserva );
		}
	}
		
}
