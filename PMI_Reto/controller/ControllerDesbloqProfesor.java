package controller;

import modelo.Profesor;
import repositorios.RepositorioAdministrador;
import view.VistaDesbloqProfesor;
import view.VistaAdminProfesor;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerDesbloqProfesor {

	private VistaDesbloqProfesor vista;
	private VistaAdminProfesor vistaAdminProfesor;

	public ControllerDesbloqProfesor(VistaDesbloqProfesor vista, VistaAdminProfesor vistaAdminProfesor) {
		this.vista = vista;
		this.vistaAdminProfesor = vistaAdminProfesor;

		// Boton Buscar
		this.vista.getBotonBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarProfesoresBloqueados();
			}
		});

		// Boton Volver
		this.vista.getBotonVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				vistaAdminProfesor.iniciar();
			}
		});

		this.vista.getListaProfesores().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Profesor seleccionado = vista.getListaProfesores().getSelectedValue();
					if (seleccionado != null) {
						int respuesta = JOptionPane.showOptionDialog(vista,
								"¿Quieres desbloquear al profesor \"" + seleccionado.getNombre() + "\" con DNI \""
										+ seleccionado.getDni() + "\"?",
								"Confirmar desbloqueo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								new Object[] { "Sí", "No" }, "Sí");

						if (respuesta == JOptionPane.YES_OPTION) {
							boolean exito = RepositorioAdministrador.desbloquearProfesor(seleccionado.getDni());
							if (exito) {
								JOptionPane.showMessageDialog(vista, "Profesor desbloqueado correctamente");
								recargarLista();
							} else {
								JOptionPane.showMessageDialog(vista, "Error al desbloquear profesor");
							}
						} else {

						}
					}
				}
			}
		});
	}

	public void iniciar() {
		vista.iniciar();
		cargarProfesoresBloqueados("");
	}

	private void cargarProfesoresBloqueados(String nombre) {
		ArrayList<Profesor> bloqueados = RepositorioAdministrador.buscarProfesoresBloqueadosPorNombre(nombre);
		DefaultListModel<Profesor> modelo = vista.getModeloLista();
		modelo.clear();
		for (Profesor p : bloqueados) {
			modelo.addElement(p);
		}
	}

	private void buscarProfesoresBloqueados() {
		String nombre = vista.getTextoBusqueda().getText().trim();
		cargarProfesoresBloqueados(nombre);
	}

	public void recargarLista() {
		buscarProfesoresBloqueados();
	}
}
