package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaAdminAlumno;
import view.VistaEditarAlumno;
import view.VistaEditarAlumnoFormulario;

public class ControllerEditarAlumno {

	private VistaEditarAlumno vista;
	private VistaAdminAlumno vistaAdminAlumno;

	public ControllerEditarAlumno(VistaEditarAlumno vista, VistaAdminAlumno vistaAdminAlumno) {
		this.vista = vista;
		this.vistaAdminAlumno = vistaAdminAlumno;

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
						VistaEditarAlumnoFormulario vistaFormulario = new VistaEditarAlumnoFormulario();
						ControllerEditarAlumnoFormulario controllerFormulario = new ControllerEditarAlumnoFormulario(
								vistaFormulario, ControllerEditarAlumno.this);
						controllerFormulario.iniciar(seleccionado);
						vista.cerrar();
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
