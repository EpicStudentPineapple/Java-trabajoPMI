package controller;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
import view.VistaAlumno;
import view.VistaCambiarDatos;
import view.VistaContraseña;
import view.VistaCursos;
import view.VistaLogin;

public class ControllerAlumno {
    private VistaAlumno vista;
    private VistaLogin vistaLogin;
	private String nombre;
    private String dni;
    public ControllerAlumno(VistaAlumno vistaAlumno, VistaLogin vistaLogin, String nombre, String dni) {
        this.vista = vistaAlumno;
        this.vistaLogin = vistaLogin;
        this.nombre = nombre;
        this.dni = dni;
        this.vista.getBtnCursos().addActionListener(e -> {
            VistaCursos vistaCursos = new VistaCursos();
            ControllerCursos controladorCursos = new ControllerCursos(vistaCursos, vista);
            vista.cerrar();            
            controladorCursos.iniciar();     
        });

        this.vista.getBtnDatos().addActionListener(e -> {
            VistaCambiarDatos vistaCambiarDatos = new VistaCambiarDatos();
            ControllerCambiarDatos controladorCambiarDatos = new ControllerCambiarDatos(vistaCambiarDatos, vista);
            vista.cerrar();            
            controladorCambiarDatos.iniciar();  
        });
        
        this.vista.getBtnContraseña().addActionListener(e -> {
            VistaContraseña  vistaContraseña = new VistaContraseña();
            ControllerContraseña controladorContraseña = new ControllerContraseña(vistaContraseña, vista);
            vista.cerrar();            
            controladorContraseña.iniciar();  
        });

        this.vista.getBtnCerrar().addActionListener(e -> {
            vista.cerrar();
            vistaLogin.iniciar();
        });
        this.vista.getBtnBaja().addActionListener(e -> {
            // Aquí llamas a tu método de modelo para eliminar al alumno
            boolean eliminado = RepositorioUsuario.eliminarAlumnoPorDNI(dni);
            
            if (eliminado) {
                vista.cerrar();
                vistaLogin.iniciar();
                JOptionPane.showMessageDialog(null, "Te has dado de baja correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo dar de baja.");
            }
        });
    }

    public void iniciar() {
        vista.iniciar();
    }
}
