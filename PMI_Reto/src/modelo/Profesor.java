package modelo;

public class Profesor extends Persona{

	// Atributos propios
	private String especializacionIdioma;
	private String experiencia;
	
	public Profesor(String dni, String contraseña, String nombre, String apellido, String especializacionIdioma, String experiencia) {
		super(dni, contraseña, nombre, apellido);
		especializacionIdioma = this.especializacionIdioma;
		experiencia = this.experiencia;
		// TODO Auto-generated constructor stub
	}

	
	// Getters & Setters
	public String getEspecializacionIdioma() {
		return especializacionIdioma;
	}


	public void setIdioma(String especializacionIdioma) {
		this.especializacionIdioma = especializacionIdioma;
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
