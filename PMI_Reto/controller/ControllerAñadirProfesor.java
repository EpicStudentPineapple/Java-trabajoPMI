package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import repositorios.RepositorioAdministrador;
import view.VistaAdminProfesor;
import view.VistaAñadirProfesor;

public class ControllerAñadirProfesor {

	private VistaAñadirProfesor vista;
	private VistaAdminProfesor vistaAdminProfesor;

	public ControllerAñadirProfesor(VistaAñadirProfesor vista, VistaAdminProfesor vistaAdminProfesor) {
		this.vista = vista;
		this.vistaAdminProfesor = vistaAdminProfesor;

		this.vista.getBtnAñadir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = vista.getTxtDni().getText().trim();
				String nombre = vista.getTxtNombre().getText().trim();
				String apellido = vista.getTxtApellido().getText().trim();

				char[] passChars = vista.getTxtContraseña().getPassword();
				String contrasena = new String(passChars).trim();
				Arrays.fill(passChars, ' ');

				String nivelExperiencia = vista.getTxtNivelExperiencia().getText().trim();
				String especializacionIdioma = vista.getTxtEspecializacionIdioma().getText().trim();

				if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contrasena.isEmpty()
						|| nivelExperiencia.isEmpty() || especializacionIdioma.isEmpty()) {
					JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.", "Campos vacíos",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (dni.length() != 9) {
					JOptionPane.showMessageDialog(vista, "El DNI debe tener exactamente 9 caracteres.", "DNI inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (RepositorioAdministrador.insertarProfesor(dni, nombre, apellido, contrasena, nivelExperiencia,
						especializacionIdioma)) {
					JOptionPane.showMessageDialog(vista, "Profesor añadido correctamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					vista.cerrar();
					vistaAdminProfesor.iniciar();
				} else {
					JOptionPane.showMessageDialog(vista, "No se pudo añadir al profesor.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.vista.getBtnVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				vistaAdminProfesor.iniciar();
			}
		});
	}
}
