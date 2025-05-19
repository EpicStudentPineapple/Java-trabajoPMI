package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Profesor;
import repositorios.RepositorioAdministrador;
import view.VistaAdminProfesor;
import view.VistaEliminarProfesor;

public class ControllerEliminarProfesor {

	private VistaEliminarProfesor vista;
	private VistaAdminProfesor vistaAdminProfesor;

	public ControllerEliminarProfesor(VistaEliminarProfesor vista, VistaAdminProfesor vistaAdminProfesor) {
		this.vista = vista;
		this.vistaAdminProfesor = vistaAdminProfesor;

		this.vista.getBotonBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarProfesores();
			}
		});

		this.vista.getListaProfesores().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Profesor seleccionado = vista.getListaProfesores().getSelectedValue();
					if (seleccionado != null) {
						int respuesta = JOptionPane.showOptionDialog(vista,
								"¿Quieres eliminar al profesor \"" + seleccionado.getNombre() + "\" con DNI \""
										+ seleccionado.getDni() + "\"?",
								"Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								new Object[] { "Sí", "No" }, "No");

						if (respuesta == JOptionPane.YES_OPTION) {
							boolean eliminado = RepositorioAdministrador.eliminarProfesor(seleccionado.getDni());
							if (eliminado) {
								recargarLista();
								JOptionPane.showMessageDialog(vista, "Profesor eliminado correctamente");
							} else {
								JOptionPane.showMessageDialog(vista, "Error al eliminar al profesor", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});

		this.vista.getBotonVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				vistaAdminProfesor.iniciar();
			}
		});
	}

	public void iniciar() {
		vista.iniciar();
		cargarProfesoresInicial();
	}

	private void cargarProfesoresInicial() {
		ArrayList<Profesor> todos = RepositorioAdministrador.buscarProfesoresPorNombre("");
		DefaultListModel<Profesor> modelo = vista.getModeloLista();
		modelo.clear();
		for (Profesor p : todos) {
			modelo.addElement(p);
		}
	}

	private void buscarProfesores() {
		String nombre = vista.getTextoBusqueda().getText().trim();
		ArrayList<Profesor> encontrados = RepositorioAdministrador.buscarProfesoresPorNombre(nombre);
		DefaultListModel<Profesor> modelo = vista.getModeloLista();
		modelo.clear();
		for (Profesor p : encontrados) {
			modelo.addElement(p);
		}
	}

	public void recargarLista() {
		buscarProfesores();
	}
}
