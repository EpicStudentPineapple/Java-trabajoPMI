package controller;

import view.VistaAlumno;

public class ControllerAlumno {
	private VistaAlumno vista;

	public ControllerAlumno(VistaAlumno vista, String nombre) {
		this.vista = vista;
	}

	public void iniciar() {
		vista.setVisible(true);
	}

}
