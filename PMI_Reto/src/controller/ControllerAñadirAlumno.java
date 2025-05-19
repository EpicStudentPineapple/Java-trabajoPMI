package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import repositorios.RepositorioAdministrador;
import view.VistaAdminAlumno;
import view.VistaAñadirAlumno;

public class ControllerAñadirAlumno {

	private VistaAñadirAlumno vista;
	private VistaAdminAlumno vistaAdminAlumno;

	public ControllerAñadirAlumno(VistaAñadirAlumno vista, VistaAdminAlumno vistaAdminAlumno) {
		this.vista = vista;
		this.vistaAdminAlumno = vistaAdminAlumno;

		this.vista.getBtnAñadir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = vista.getTxtDni().getText().trim();
				String nombre = vista.getTxtNombre().getText().trim();
				String apellido = vista.getTxtApellido().getText().trim();

				char[] passChars = vista.getTxtContraseña().getPassword();
				String contrasena = new String(passChars).trim();
				Arrays.fill(passChars, ' ');

				String edadTexto = vista.getTxtEdad().getText().trim();
				String correo = vista.getTxtCorreo().getText().trim();

				if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contrasena.isEmpty()
						|| edadTexto.isEmpty() || correo.isEmpty()) {
					JOptionPane.showMessageDialog(vista, "Por favor, rellena todos los campos.", "Campos vacíos",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (dni.length() != 9) {
					JOptionPane.showMessageDialog(vista, "El DNI debe tener exactamente 9 caracteres.", "DNI inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (!correo.contains("@") || !correo.contains(".")) {
					JOptionPane.showMessageDialog(vista, "Introduce un correo electrónico válido.", "Correo inválido",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				int edad;
				try {
					edad = Integer.parseInt(edadTexto);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(vista, "La edad debe ser un número válido.", "Error de formato",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (RepositorioAdministrador.insertarAlumno(dni, nombre, apellido, contrasena, edad, correo)) {
					JOptionPane.showMessageDialog(vista, "Alumno añadido correctamente", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					vista.cerrar();
					vistaAdminAlumno.iniciar();
				} else {
					JOptionPane.showMessageDialog(vista, "No se pudo añadir al alumno.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.vista.getBtnVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				vistaAdminAlumno.iniciar();
			}
		});
	}

}
