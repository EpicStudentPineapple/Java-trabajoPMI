package modelo;

public class Profesor extends Persona {

	// Atributos propios
	private String idioma;
	private String experiencia;
	 private boolean bloqueado;

	public Profesor(String dni, String contraseña, String nombre, String apellido, String rol, String idioma,
			String experiencia) {
		super(dni, contraseña, nombre, apellido, rol);
		this.idioma = idioma;
		this.experiencia = experiencia;
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

	   // Getter y setter para bloqueado
    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

	@Override
	public void imparteClase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void estaInscrito() {
		// TODO Auto-generated method stub
		
	}
    @Override
    public String toString() {
        return nombre + " " + apellido + " - DNI: " + dni;
    }
}