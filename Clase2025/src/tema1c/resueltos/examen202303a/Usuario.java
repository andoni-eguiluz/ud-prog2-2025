package tema1c.resueltos.examen202303a;

public class Usuario {
	// STATIC
	private static int codigoSiguienteUsuario = 1;

	// Otra manera: guardar códigos que han ido apareciendo para comprobar si son correctos:
	// private static ArrayList<Integer> listaCodigosYaExistentes = new ArrayList<>();
	
	// NO STATIC
	protected int codigo;
	protected String nombre;
	protected String apellidos;
	protected double saldo;
	
	public Usuario(String nombre, String apellidos, double saldo) {
		// this();  Equivale a las siguientes tres líneas
		super();
		this.codigo = codigoSiguienteUsuario;
		codigoSiguienteUsuario++;
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.saldo = saldo;
	}

	public Usuario() {
		super();
		this.codigo = codigoSiguienteUsuario;
		codigoSiguienteUsuario++;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", saldo=" + saldo
				+ "]";
	}
	
	
}
