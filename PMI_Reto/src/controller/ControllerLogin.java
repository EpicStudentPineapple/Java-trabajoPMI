package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
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

		if (RepositorioUsuario.verificarUsuario(dni, contraseña)) {
			 String tipoUsuario = RepositorioUsuario.obtenerTipoUsuario(dni);
			 String nombre = RepositorioUsuario.obtenerNombrePorDNI(dni);
			if (tipoUsuario.equals("alumno")) {
				VistaAlumno vistaAlumno = new VistaAlumno(nombre);
				ControllerAlumno controladorAlumno = new ControllerAlumno(vistaAlumno, nombre);
				vista.dispose();
				controladorAlumno.iniciar();
			} else if (tipoUsuario.equals("profesor")) {
				VistaProfesor vistaProfesor = new VistaProfesor(nombre);
				ControllerProfesor controladorProfesor = new ControllerProfesor(vistaProfesor, nombre);
				vista.dispose();
				controladorProfesor.iniciar();
			} else {
				JOptionPane.showMessageDialog(vista, "Usuario sin rol asignado (ni alumno ni profesor).");
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
