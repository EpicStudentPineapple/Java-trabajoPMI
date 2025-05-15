
package controller;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import modelo.Curso;
import repositorios.RepositorioCurso;
import view.VistaCursos;
import view.VistaAlumno;
import view.VistaDetalles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerCursos {
	private VistaCursos vista;
	private VistaAlumno vistaAlumno;
	private String dniAlumno;

	public ControllerCursos(VistaCursos vistaCursos, VistaAlumno vistaAlumno, String dniAlumno) {
		this.vista = vistaCursos;
		this.vistaAlumno = vistaAlumno;
		this.dniAlumno = dniAlumno;

		DefaultListModel<Curso> modeloLista = vistaCursos.getModeloLista();
		modeloLista.clear();
		for (Curso curso : RepositorioCurso.obtenerCursos()) {
			modeloLista.addElement(curso);
		}

		// Accion seleccionar curso
		this.vista.getListaCursos().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				Curso seleccionado = vista.getListaCursos().getSelectedValue();
				if (seleccionado != null) {
					// Obtener datos del curso
					Curso cursoCompleto = RepositorioCurso.obtenerCursoPorId(seleccionado.getIdCurso());
					VistaDetalles vistaDetalles = new VistaDetalles(cursoCompleto, dniAlumno);
					ControllerDetalles controllerDetalles = new ControllerDetalles(vistaDetalles, dniAlumno);
					controllerDetalles.iniciar();

				}
			}
		});
		this.vista.getBtnBuscar().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String textoBusqueda = vista.getTextoBusqueda().getText().trim();

		        DefaultListModel<Curso> modeloLista = vista.getModeloLista();
		        modeloLista.clear();

		        if (textoBusqueda.isEmpty()) {
		            for (Curso curso : RepositorioCurso.obtenerCursos()) {
		                modeloLista.addElement(curso);
		            }
		        } else {
		            for (Curso curso : RepositorioCurso.buscarCursosPorIdioma(textoBusqueda)) {
		                modeloLista.addElement(curso);
		            }

		            if (modeloLista.isEmpty()) {
		                JOptionPane.showMessageDialog(vista, "No se encontraron resultados", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
		            }
		        }
		    }
		});

		// Boton Volver
		this.vista.getBtnVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vistaCursos.dispose();
				vistaAlumno.setVisible(true);
			}
		});
	}

	public void iniciar() {
		vista.setVisible(true); 
	}
}
