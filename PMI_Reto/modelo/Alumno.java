package modelo;

public class Alumno extends Persona {

    // Atributos propios
    private int nivelIdioma;
    private String correo;
    private boolean bloqueado;  

 
    public Alumno(String dni, String contraseña, String nombre, String apellido, String rol, int nivelIdioma, String correo) {
        super(dni, contraseña, nombre, apellido, rol);
        this.nivelIdioma = nivelIdioma;
        this.correo = correo;
    }

  
  

    // Getter y setter para bloqueado
    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    // Métodos abstractos
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

    @Override
    public String toString() {
        return nombre + " " + apellido + " - DNI: " + dni;
    }
}
