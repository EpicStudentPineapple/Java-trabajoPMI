package controller;

import view.VistaAlumno;
import view.VistaCambiarDatos;
import view.VistaCursos;
import view.VistaLogin;
import view.VistaProfesor;

public class ControllerAlumno {
    private VistaAlumno vista;
    private VistaLogin vistaLogin;
    private String nombre;
    
    public ControllerAlumno(VistaAlumno vistaAlumno, VistaLogin vistaLogin, String nombre) {
        this.vista = vistaAlumno;
        this.vistaLogin = vistaLogin;
        this.nombre = nombre;

        // Acción del botón Cursos
        this.vista.getBtnCursos().addActionListener(e -> {
            VistaCursos vistaCursos = new VistaCursos();
            VistaProfesor vistaProfesor = new VistaProfesor(nombre);  // Crear instancia de VistaProfesor
            ControllerCursos controladorCursos = new ControllerCursos(vistaCursos, vistaAlumno, nombre);  // Pasar VistaProfesor en lugar de VistaAlumno
            vista.cerrar();            
            controladorCursos.iniciar();     
        });

        // Acción del botón Datos
        this.vista.getBtnDatos().addActionListener(e -> {
            VistaCambiarDatos vistaCambiarDatos = new VistaCambiarDatos();
            //new ControllerCambiarDatos(vistaCambiarDatos, vista);
            vista.cerrar();
            //  vistaCambiarDatos.iniciar();
        });

        // Acción del botón Cerrar
        this.vista.getBtnCerrar().addActionListener(e -> {
            vista.cerrar();
            vistaLogin.iniciar();
        });
    }

    public void iniciar() {
        vista.iniciar();
    }
}
