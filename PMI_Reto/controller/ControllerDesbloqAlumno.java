package controller;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaDesbloqAlumno;
import view.VistaAdminAlumno;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerDesbloqAlumno {

	private VistaDesbloqAlumno vista;
	private VistaAdminAlumno vistaAdminAlumno;

	public ControllerDesbloqAlumno(VistaDesbloqAlumno vistaDesbloqAlumno, VistaAdminAlumno vistaAdminAlumno) {
		this.vista = vistaDesbloqAlumno;
		this.vistaAdminAlumno = vistaAdminAlumno;

		// Boton Buscar
		this.vista.getBotonBuscar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarAlumnosBloqueados();
			}
		});

		// Boton Volver
		this.vista.getBotonVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vistaDesbloqAlumno.cerrar();
				vistaAdminAlumno.iniciar();
			}
		});

		this.vista.getListaAlumnos().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Alumno seleccionado = vistaDesbloqAlumno.getListaAlumnos().getSelectedValue();
					if (seleccionado != null) {
						int respuesta = JOptionPane.showOptionDialog(vista,
								"¿Quieres desbloquear al alumno \"" + seleccionado.getNombre() + "\" con DNI \""
										+ seleccionado.getDni() + "\"?",
								"Confirmar desbloqueo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								new Object[] { "Sí", "No" }, "Sí");

						if (respuesta == JOptionPane.YES_OPTION) {

							seleccionado.setBloqueado(false);
							RepositorioAdministrador.desbloquearAlumno(seleccionado.getDni());
							recargarLista();
							JOptionPane.showMessageDialog(vistaDesbloqAlumno, "Alumno desbloqueado correctamente");
						} else {

						}
					}
				}
			}
		});

	}

	public void iniciar() {
		vista.iniciar();
		cargarAlumnosBloqueados("");
	}

	private void cargarAlumnosBloqueados(String filtro) {
		ArrayList<Alumno> bloqueados = RepositorioAdministrador.buscarAlumnosBloqueadosPorNombre(filtro);
		DefaultListModel<Alumno> modelo = vista.getModeloLista();
		modelo.clear();
		for (Alumno a : bloqueados) {
			modelo.addElement(a);
		}
	}

	private void buscarAlumnosBloqueados() {
		String nombre = vista.getTextoBusqueda().getText().trim();
		cargarAlumnosBloqueados(nombre);
	}

	public void recargarLista() {
		buscarAlumnosBloqueados();
	}
}
