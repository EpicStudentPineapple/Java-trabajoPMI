package controller;

import view.VistaProfesor;

public class ControllerProfesor {
	private VistaProfesor vista;
	public ControllerProfesor (VistaProfesor vista, String nombre) {
		this.vista = vista;
	}

    public void iniciar() {
        vista.setVisible(true);
    }

}
