package Clases;

public abstract class Persona {

	// Atributos
	public String dni;
	public String contraseña;
	public String nombre;
	public String apellido;
	
	// Constructor
	public Persona(String dni, String contraseña, String nombre, String apellido) {
		super();
		this.dni = dni;
		this.contraseña = contraseña;
		this.nombre = nombre;
		this.apellido = apellido;
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

	// toString
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", contraseña=" + contraseña + ", nombre=" + nombre + ", apellido=" + apellido
				+ "]";
	}
	
	// Metodos abstractos
	public abstract void imparteClase();
	
	public abstract void estaInscrito();
}
