package main;

import controller.ControllerLogin;
import repositorios.ConectorBD;
import view.VistaLogin;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ConectorBD.conectar();
		        //Cargamos la vista
		        VistaLogin vista = new VistaLogin();
		        //Le pasamos la vista al controlador
		        ControllerLogin controlador = new ControllerLogin(vista);
		        //El controlador manejara el boton cuando le demos a registrar.
		        
		        //Iniciamos la vista.
		        controlador.iniciar();
	 }


}
