package modelo;

public class Alumno extends Persona{


	// Atributos propios
	private int edad;
	private String correo;
	
	public Alumno(String dni, String contraseña, String nombre, String apellido, int edad, String correo) {
		super(dni, contraseña, nombre, apellido);
		this.edad = edad;
		this.correo = correo;
	}

	// Getters & Setters
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
		
	// toString
	@Override
	public String toString() {
		return "Alumno [edad=" + edad + ", correo=" + correo + "]";
	}
		
	// Metodos abstractos
	@Override
	public void imparteClase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void estaInscrito() {
		// TODO Auto-generated method stub
		
	}
}
