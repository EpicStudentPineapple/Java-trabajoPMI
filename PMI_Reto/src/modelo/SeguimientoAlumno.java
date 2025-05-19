package modelo;

public class SeguimientoAlumno {

	// Atributos
    private String dni;
    private int idCurso;
    private String nivelIdioma;
    private int asistencia;
    private int participacion;
    private int rendimiento;

    // Constructor
    public SeguimientoAlumno(String dni, int idCurso, String nivelIdioma, int asistencia, int participacion, int rendimiento) {
        this.dni = dni;
        this.idCurso = idCurso;
        this.nivelIdioma = nivelIdioma;
        this.asistencia = asistencia;
        this.participacion = participacion;
        this.rendimiento = rendimiento;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNivelIdioma() {
        return nivelIdioma;
    }

    public void setNivelIdioma(String nivelIdioma) {
        this.nivelIdioma = nivelIdioma;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public int getParticipacion() {
        return participacion;
    }

    public void setParticipacion(int participacion) {
        this.participacion = participacion;
    }

    public int getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(int rendimiento) {
        this.rendimiento = rendimiento;
    }

    // @toString
    @Override
    public String toString() {
        return "SeguimientoAlumno{" +
               "dni='" + dni + '\'' +
               ", idCurso=" + idCurso +
               ", nivelIdioma='" + nivelIdioma + '\'' +
               ", asistencia=" + asistencia +
               ", participacion=" + participacion +
               ", rendimiento=" + rendimiento +
               '}';
    }
}
