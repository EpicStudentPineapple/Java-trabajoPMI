package controller;

import view.VistaAdministrador;

public class ControllerAdministrador {
	private VistaAdministrador vista;

	public ControllerAdministrador(VistaAdministrador vista, String nombre) {
		this.vista = vista;
	}

	public void iniciar() {
		vista.setVisible(true);
	}
}
