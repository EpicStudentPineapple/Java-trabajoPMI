package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Profesor;
import repositorios.RepositorioAdministrador;
import view.VistaAdminProfesor;
import view.VistaEditarProfesor;
import view.VistaEditarProfesorFormulario;

public class ControllerEditarProfesor {

	private VistaEditarProfesor vista;
	private VistaAdminProfesor vistaAdminProfesor;

	public ControllerEditarProfesor(VistaEditarProfesor vista, VistaAdminProfesor vistaAdminProfesor) {
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
						VistaEditarProfesorFormulario vistaFormulario = new VistaEditarProfesorFormulario();
						ControllerEditarProfesorFormulario controllerFormulario = new ControllerEditarProfesorFormulario(
								vistaFormulario, ControllerEditarProfesor.this);
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
