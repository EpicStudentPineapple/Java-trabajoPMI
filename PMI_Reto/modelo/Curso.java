package modelo;

public class Curso {

	// Atributos
	private int idCurso;
	private String idioma;
	private String dia;
	private String horario;
	private String dificultad;
	private String programa;
		
	// Constructor
	public Curso(int idCurso, String idioma, String dia, String horario, String dificultad, String programa) {
		super();
		this.idCurso = idCurso;
		this.idioma = idioma;
		this.dia = dia;
		this.horario = horario;
		this.dificultad = dificultad;
		this.programa = programa;
	}
	
	
	public Curso(String idioma, String dia, String horario, String dificultad, String programa) {
		super();
		this.idioma = idioma;
		this.dia = dia;
		this.horario = horario;
		this.dificultad = dificultad;
		this.programa = programa;
	}


	public Curso(String idioma2, String dificultad2) {
		this.idioma = idioma2;
		this.dificultad = dificultad2;
	}

	// Getters & Setters
	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}
	@Override
	public String toString() {
	    return idioma + " - " + dificultad; 
	}



}
