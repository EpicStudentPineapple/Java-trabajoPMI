package modelo;

public class Alumno extends Persona {

	// Atributos propios
	private int nivelIdioma;
	private String correo;

	public Alumno(String dni, String contraseña, String nombre, String apellido, String rol, int nivelIdioma,
			String correo) {
		super(dni, contraseña, nombre, apellido, rol);
		this.nivelIdioma = nivelIdioma;
		this.correo = correo;
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

	public int getNivelIdioma() {
		return nivelIdioma;
	}

	public void setNivelIdioma(int nivelIdioma) {
		this.nivelIdioma = nivelIdioma;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
