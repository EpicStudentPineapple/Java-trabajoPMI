package controller;

import view.VistaAlumno;
import view.VistaCursos;

public class ControllerCursos {
	private VistaCursos vista;
	private VistaAlumno vistaAlumno;
	public ControllerCursos(VistaCursos vistaCursos, VistaAlumno vistaAlumno) {
		super();
		this.vista = vistaCursos;
		this.vistaAlumno = vistaAlumno;
	}
	
	 public void iniciar() {
	        vista.iniciar();
	    }
}
