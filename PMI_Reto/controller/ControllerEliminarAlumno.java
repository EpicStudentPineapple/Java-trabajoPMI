package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaAdminAlumno;
import view.VistaDetalleAlumno;
import view.VistaEliminarAlumno;

public class ControllerEliminarAlumno {

	private VistaEliminarAlumno vista;
	private VistaAdminAlumno vistaAdminAlumno;

	public ControllerEliminarAlumno(VistaEliminarAlumno vista, VistaAdminAlumno vistaAdminAlumno) {
		this.vista = vista;
		this.vistaAdminAlumno = vistaAdminAlumno;

		// Boton Buscar
		this.vista.getBotonBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarAlumnos();
			}
		});

		this.vista.getListaAlumnos().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Alumno seleccionado = vista.getListaAlumnos().getSelectedValue();
					if (seleccionado != null) {
						int respuesta = JOptionPane.showOptionDialog(vista,
								"¿Quieres eliminar al alumno \"" + seleccionado.getNombre() + "\" con DNI \""
										+ seleccionado.getDni() + "\"?",
								"Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								new Object[] { "Sí", "No" }, "No");

						if (respuesta == JOptionPane.YES_OPTION) {
							boolean eliminado = RepositorioAdministrador.eliminarAlumno(seleccionado.getDni());
							if (eliminado) {
								recargarLista();
								JOptionPane.showMessageDialog(vista, "Alumno eliminado correctamente");
							} else {
								JOptionPane.showMessageDialog(vista, "Error al eliminar al alumno", "Error",
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
				vistaAdminAlumno.iniciar();
			}
		});
	}

	public void iniciar() {
		vista.iniciar();
		cargarAlumnosInicial();
	}

	private void cargarAlumnosInicial() {
		ArrayList<Alumno> todos = RepositorioAdministrador.buscarAlumnosPorNombre("");
		DefaultListModel<Alumno> modelo = vista.getModeloLista();
		modelo.clear();
		for (Alumno a : todos) {
			modelo.addElement(a);
		}
	}

	private void buscarAlumnos() {
		String nombre = vista.getTextoBusqueda().getText().trim();
		ArrayList<Alumno> encontrados = RepositorioAdministrador.buscarAlumnosPorNombre(nombre);
		DefaultListModel<Alumno> modelo = vista.getModeloLista();
		modelo.clear();
		for (Alumno a : encontrados) {
			modelo.addElement(a);
		}
	}

	public void recargarLista() {
		buscarAlumnos();
	}
}
