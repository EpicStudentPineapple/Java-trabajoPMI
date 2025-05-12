package controller;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
import view.VistaAlumno;
import view.VistaCambiarDatos;
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
            ControllerCambiarDatos controladorCambiarDatos = new ControllerCambiarDatos(vistaCambiarDatos, vista, dni);
            vista.cerrar();            
            controladorCambiarDatos.iniciar();  
        });

        this.vista.getBtnCerrar().addActionListener(e -> {
            vista.cerrar();
            vistaLogin.iniciar();
        });

        this.vista.getBtnBaja().addActionListener(e -> {
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

    public VistaAlumno getVista() {
        return vista;
    }

    public void setVista(VistaAlumno vista) {
        this.vista = vista;
    }

    public VistaLogin getVistaLogin() {
        return vistaLogin;
    }

    public void setVistaLogin(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void iniciar() {
        vista.iniciar();
    }
}
