package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import modelo.Profesor;
import repositorios.RepositorioAdministrador;
import view.VistaEditarProfesorFormulario;

public class ControllerEditarProfesorFormulario {

	private VistaEditarProfesorFormulario vista;
	private ControllerEditarProfesor controllerEditarProfesor;
	private Profesor profesor;

	public ControllerEditarProfesorFormulario(VistaEditarProfesorFormulario vista,
			ControllerEditarProfesor controllerEditarProfesor) {
		this.vista = vista;
		this.controllerEditarProfesor = controllerEditarProfesor;
	}

	public void iniciar(Profesor profesor) {
		this.profesor = profesor;

		vista.getCampoNombre().setText(profesor.getNombre());
		vista.getCampoApellido().setText(profesor.getApellido());
		vista.getCampoNivelExperiencia().setText(profesor.getExperiencia());
		vista.getCampoEspecializacionIdioma().setText(profesor.getEspecializacionIdioma());
		vista.getCampoContraseña().setText(profesor.getContraseña());
		vista.getCheckBloqueado().setSelected(profesor.isBloqueado());

		vista.getBotonEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarProfesor();
			}
		});

		vista.getBotonVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				controllerEditarProfesor.iniciar();
			}
		});

		vista.iniciar();
	}

	private void editarProfesor() {
		String nombre = vista.getCampoNombre().getText().trim();
		String apellido = vista.getCampoApellido().getText().trim();
		String nivelExperiencia = vista.getCampoNivelExperiencia().getText().trim();
		String especializacionIdioma = vista.getCampoEspecializacionIdioma().getText().trim();
		String contraseña = vista.getCampoContraseña().getText().trim();
		boolean bloqueado = vista.getCheckBloqueado().isSelected();

		if (nombre.isEmpty() || apellido.isEmpty() || nivelExperiencia.isEmpty() || especializacionIdioma.isEmpty()
				|| contraseña.isEmpty()) {
			JOptionPane.showMessageDialog(vista, "Por favor, completa todos los campos.");
			return;
		}

		profesor.setNombre(nombre);
		profesor.setApellido(apellido);
		profesor.setExperiencia(nivelExperiencia);
		profesor.setEspecializacionIdioma(especializacionIdioma);
		profesor.setContraseña(contraseña);
		profesor.setBloqueado(bloqueado);

		boolean exito = RepositorioAdministrador.editarProfesor(profesor);

		if (exito) {
			JOptionPane.showMessageDialog(vista, "Profesor editado correctamente.");
			vista.cerrar();
			controllerEditarProfesor.recargarLista();
			controllerEditarProfesor.iniciar();
		} else {
			JOptionPane.showMessageDialog(vista, "Error al editar el profesor.");
		}
	}
}
