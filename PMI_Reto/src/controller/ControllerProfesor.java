package controller;

import javax.swing.JOptionPane;

import view.VistaLogin;
import view.VistaProfesor;

public class ControllerProfesor {
	private VistaProfesor vista;
	public ControllerProfesor (VistaProfesor vista) {
		this.vista = vista;
	}

    public void iniciar() {
        vista.setVisible(true);
    }

}