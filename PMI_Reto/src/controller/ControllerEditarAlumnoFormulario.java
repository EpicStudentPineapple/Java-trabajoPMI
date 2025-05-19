package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaEditarAlumnoFormulario;
import view.VistaEditarAlumno;

public class ControllerEditarAlumnoFormulario {

	private VistaEditarAlumnoFormulario vista;
	private ControllerEditarAlumno controllerEditarAlumno;
	private Alumno alumno;

	public ControllerEditarAlumnoFormulario(VistaEditarAlumnoFormulario vista,
			ControllerEditarAlumno controllerEditarAlumno) {
		this.vista = vista;
		this.controllerEditarAlumno = controllerEditarAlumno;
	}

	public void iniciar(Alumno alumno) {
		this.alumno = alumno;

		vista.getCampoNombre().setText(alumno.getNombre());
		vista.getCampoApellido().setText(alumno.getApellido());
		vista.getCampoEdad().setText(String.valueOf(alumno.getEdad()));
		vista.getCampoCorreo().setText(alumno.getCorreo());
		vista.getCampoContraseña().setText(alumno.getContraseña());
		vista.getCheckBloqueado().setSelected(alumno.isBloqueado());

		this.vista.getBotonEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarAlumno();
			}
		});

		this.vista.getBotonVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				controllerEditarAlumno.iniciar();
			}
		});

		vista.iniciar();
	}

	private void editarAlumno() {
		String nombre = vista.getCampoNombre().getText().trim();
		String apellido = vista.getCampoApellido().getText().trim();
		String edadTexto = vista.getCampoEdad().getText().trim();
		String correo = vista.getCampoCorreo().getText().trim();
		String contraseña = vista.getCampoContraseña().getText().trim();
		boolean bloqueado = vista.getCheckBloqueado().isSelected();

		if (nombre.isEmpty() || apellido.isEmpty() || edadTexto.isEmpty() || correo.isEmpty() || contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Por favor, completa todos los campos.");
			return;
		}

		int edad = 0;
		try {
			edad = Integer.parseInt(edadTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vista, "La edad debe ser un número válido.");
			return;
		}

		if (!correo.contains("@") || !correo.contains(".")) {
			JOptionPane.showMessageDialog(vista, "El correo debe contener '@' y '.'");
			return;
		}

		alumno.setNombre(nombre);
		alumno.setApellido(apellido);
		alumno.setEdad(edad);
		alumno.setCorreo(correo);
		alumno.setContraseña(contraseña);
		alumno.setBloqueado(bloqueado);

		boolean exito = RepositorioAdministrador.editarAlumno(alumno);

		if (exito) {
			JOptionPane.showMessageDialog(vista, "Alumno editado correctamente.");
			vista.cerrar();
			controllerEditarAlumno.recargarLista();
			controllerEditarAlumno.iniciar();
		} else {
			JOptionPane.showMessageDialog(vista, "Error al editar el alumno.");
		}
	}

}
