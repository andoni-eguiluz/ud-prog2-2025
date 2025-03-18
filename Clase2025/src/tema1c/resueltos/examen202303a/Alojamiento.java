package tema1c.resueltos.examen202303a;

public class Alojamiento {
	private Anfitrion anfitrion;
	private String nombre = "Nombre";
	private String descripcion = "Descripción";
	private double precio;
	
	public Alojamiento(Anfitrion anfitrion, String nombre, String descripcion, double precio) {
		super();
		this.anfitrion = anfitrion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Alojamiento() {
		super();
	}

	public Anfitrion getAnfitrion() {
		return anfitrion;
	}

	public void setAnfitrion(Anfitrion anfitrion) {
		this.anfitrion = anfitrion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Alojamiento [anfitrion=" + (anfitrion==null ? "nulo" : anfitrion.getNombre()) + 
				", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}

}
