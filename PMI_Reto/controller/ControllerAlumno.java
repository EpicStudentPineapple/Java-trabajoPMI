package controller;

import view.VistaAlumno;
import view.VistaCambiarDatos;
import view.VistaCursos;
import view.VistaLogin;

public class ControllerAlumno {
    private VistaAlumno vista;
    private VistaLogin vistaLogin;
	private String nombre;
    
    public ControllerAlumno(VistaAlumno vistaAlumno, VistaLogin vistaLogin, String nombre) {
        this.vista = vistaAlumno;
        this.vistaLogin = vistaLogin;
        this.nombre = nombre;
        this.vista.getBtnCursos().addActionListener(e -> {
            VistaCursos vistaCursos = new VistaCursos();
            ControllerCursos controladorCursos = new ControllerCursos(vistaCursos, vista);
            vista.cerrar();            
            controladorCursos.iniciar();     
        });

        this.vista.getBtnDatos().addActionListener(e -> {
            VistaCambiarDatos vistaCambiarDatos = new VistaCambiarDatos();
            //new ControllerCambiarDatos(vistaCambiarDatos, vista);
            vista.cerrar();
           //  vistaCambiarDatos.iniciar();
        });

        this.vista.getBtnCerrar().addActionListener(e -> {
            vista.cerrar();
            vistaLogin.iniciar();
        });
    }

    public void iniciar() {
        vista.iniciar();
    }
}
