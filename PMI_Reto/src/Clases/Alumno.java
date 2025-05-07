package Clases;

public class Alumno extends Persona{

	// Atributos propios
	private int nivelIdioma; 
	
	
	public Alumno(String dni, String contraseña, String nombre, String apellido, int nivelIdioma) {
		super(dni, contraseña, nombre, apellido);
		nivelIdioma = this.nivelIdioma;
		// TODO Auto-generated constructor stub
	}

	sd
	// Getters & Setters
	public int getNivelIdioma() {
		return nivelIdioma;
	}

	public void setNivelIdioma(int nivelIdioma) {
		this.nivelIdioma = nivelIdioma;
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
