package modelo;

public class Alumno extends Persona {

	// Atributos propios
	private int nivelIdioma;
	private String correo;
	private boolean bloqueado;
	private int edad;

    // Constructor
	public Alumno(String dni, String contraseña, String nombre, String apellido, String rol, int nivelIdioma, String correo) {
		super(dni, contraseña, nombre, apellido, rol);
		this.nivelIdioma = nivelIdioma;
		this.correo = correo;
	}
	
    // Getters & setters
    public boolean getBloqueado() {
        return bloqueado;
    }
    	
    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
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

	public int getEdad() {
		return edad;
	}
   
	public void setEdad(int edad) {
		this.edad = edad;
	}

    // toString
    @Override
    public String toString() {
        return nombre + " " + apellido + " - DNI: " + dni;
    }
}
