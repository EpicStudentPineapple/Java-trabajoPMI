package controller;

import view.VistaLogin;

public class ControllerLogin {
	
	private VistaLogin vista;
	
	public  ControllerLogin(VistaLogin vista) {
        this.vista = vista;}
	
	public void iniciar() {
        vista.setVisible(true);
    }
}
