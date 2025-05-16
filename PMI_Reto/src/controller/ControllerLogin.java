package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import repositorios.RepositorioPersona;
import view.VistaAdministrador;
import view.VistaAlumno;
import view.VistaLogin;
import view.VistaProfesor;
import view.VistaRegistro;

public class ControllerLogin {

	private VistaLogin vista;

	public ControllerLogin(VistaLogin vista) {
		this.vista = vista;
		this.vista.getBtnLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verificarUsuario();

			}
		});
		this.vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRegistro();
            }
        });
	}

	private void verificarUsuario() {
	    String dni = vista.getTxtDni().getText();
	    String contraseña = vista.getTxtContraseña().getText();

	    if (RepositorioPersona.verificarUsuario(dni, contraseña)) {
	        String rol = RepositorioPersona.obtenerRol(dni);
	        String nombre = RepositorioPersona.obtenerNombrePorDNI(dni);

	        if (rol.equalsIgnoreCase("alumno")) {
	            VistaAlumno vistaAlumno = new VistaAlumno(nombre);
	            ControllerAlumno controladorAlumno = new ControllerAlumno(vistaAlumno, vista, nombre);
	            vista.dispose();
	            controladorAlumno.iniciar();
	        } else if (rol.equalsIgnoreCase("profesor")) {
	            VistaProfesor vistaProfesor = new VistaProfesor(nombre);
	            ControllerProfesor controladorProfesor = new ControllerProfesor(vistaProfesor, vista, nombre);
	            vista.dispose();
	            controladorProfesor.iniciar();
	        } else if (rol.equalsIgnoreCase("administrador")) {
	            VistaAdministrador vistaAdministrador = new VistaAdministrador(nombre);
	            ControllerAdministrador controladorAdministrador = new ControllerAdministrador(vistaAdministrador, nombre);
	            vista.dispose();
	            controladorAdministrador.iniciar();
	        } else {
	            JOptionPane.showMessageDialog(vista, "Usuario sin rol válido.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(vista, "DNI o contraseña incorrectos.");
	    }
	}


	public void iniciar() {
		vista.setVisible(true);
	}
    private void mostrarRegistro() {
        VistaRegistro vistaRegistro = new VistaRegistro();
        new ControllerRegistro(vistaRegistro, vista);
        vistaRegistro.setVisible(true);
        vista.setVisible(false);
    }

}
