package Clases;

public class Profesor extends Persona{

	// Atributos propios
	private String idioma;
	private String experiencia;
	
	public Profesor(String dni, String contraseña, String nombre, String apellido, String idioma, String experiencia) {
		super(dni, contraseña, nombre, apellido);
		idioma = this.idioma;
		experiencia = this.experiencia;
		// TODO Auto-generated constructor stub
	}

	
	// Getters & Setters
	public String getIdioma() {
		return idioma;
	}


	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}


	public String getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
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
