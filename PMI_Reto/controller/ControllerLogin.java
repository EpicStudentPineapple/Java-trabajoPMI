package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
import view.VistaAlumno;
import view.VistaLogin;
import view.VistaProfesor;

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
	}

	private void verificarUsuario() {
		String dni = vista.getTxtDni().getText();
		String contraseña = vista.getTxtContraseña().getText();

		if (RepositorioUsuario.verificarUsuario(dni, contraseña)) {
			String tipoUsuario = RepositorioUsuario.obtenerTipoUsuario(dni);
			if (tipoUsuario.equals("alumno")) {
				VistaAlumno vistaAlumno = new VistaAlumno();
				ControllerAlumno controladorAlumno = new ControllerAlumno(vistaAlumno);
				vista.dispose();
				controladorAlumno.iniciar();
			} else if (tipoUsuario.equals("profesor")) {
				VistaProfesor vistaProfesor = new VistaProfesor();
				ControllerProfesor controladorProfesor = new ControllerProfesor(vistaProfesor);
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

}
