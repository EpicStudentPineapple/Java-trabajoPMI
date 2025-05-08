package controller;

import view.VistaAlumno;
import view.VistaProfesor;

public class ControllerAlumno {
	private VistaAlumno vista;

	public ControllerAlumno(VistaAlumno vista) {
		this.vista = vista;
	}

	public void iniciar() {
		vista.setVisible(true);
	}

}
