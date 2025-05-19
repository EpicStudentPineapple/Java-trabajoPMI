package modelo;

public class Profesor extends Persona {

	// Atributos propios
	
	private String experiencia;
	private String especializacionIdioma;
	private boolean bloqueado;

	 // Constructor
	public Profesor(String dni, String contraseña, String nombre, String apellido, String rol, String especializacionIdioma, String experiencia) {
		super(dni, contraseña, nombre, apellido, rol);
		this.especializacionIdioma = especializacionIdioma;
		this.experiencia = experiencia;
	}

	// Getters & Setters


	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

    public boolean getBloqueado() {
        return bloqueado;
    }
	// Getter y setter para bloqueado
	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getEspecializacionIdioma() {
		return especializacionIdioma;
	}

	public void setEspecializacionIdioma(String especializacionIdioma) {
		this.especializacionIdioma = especializacionIdioma;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido + " - DNI: " + dni;
	}
}

