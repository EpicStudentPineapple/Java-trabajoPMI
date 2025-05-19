package modelo;

public abstract class Persona {

	// Atributos
	protected String dni;
	protected String contraseña;
	protected String nombre;
	protected String apellido;
	protected String rol;
	
	// Constructor

	public Persona(String dni, String contraseña, String nombre, String apellido, String rol) {
		super();
		this.dni = dni;
		this.contraseña = contraseña;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
	}
	// Getters & Setters
	
	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", contraseña=" + contraseña + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", rol=" + rol + "]";
	}

	// Metodos abstractos
	public abstract void imparteClase();
	
	public abstract void estaInscrito();
}
