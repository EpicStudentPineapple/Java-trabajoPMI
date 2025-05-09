package controller;

import view.VistaAlumno;
import view.VistaCambiarDatos;

public class ControllerCambiarDatos {
	private VistaCambiarDatos vista;
	private VistaAlumno vistaAlumno;
	
	
	
	public ControllerCambiarDatos(VistaCambiarDatos vista, VistaAlumno vistaAlumno) {
		super();
		this.vista = vista;
		this.vistaAlumno = vistaAlumno;
	}



	public void iniciar() {
        vista.iniciar();
    }
}
