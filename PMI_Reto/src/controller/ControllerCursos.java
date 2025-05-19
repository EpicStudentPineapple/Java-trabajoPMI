
package controller;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import modelo.Curso;
import repositorios.RepositorioCursos;
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
		for (Curso curso : RepositorioCursos.obtenerCursos()) {
			modeloLista.addElement(curso);
		}

		// Accion para seleccionar cursos
		this.vista.getListaCursos().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				Curso seleccionado = vista.getListaCursos().getSelectedValue();
				if (seleccionado != null) {
					Curso cursoCompleto = RepositorioCursos.obtenerCursoPorId(seleccionado.getIdCurso());
					VistaDetalles vistaDetalles = new VistaDetalles(cursoCompleto, dniAlumno);
					ControllerDetalles controllerDetalles = new ControllerDetalles(vistaDetalles, dniAlumno);
					controllerDetalles.iniciar();

				}
			}
		});
		
		// Boton buscar
		this.vista.getBtnBuscar().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String textoBusqueda = vista.getTextoBusqueda().getText().trim();

		        DefaultListModel<Curso> modeloLista = vista.getModeloLista();
		        modeloLista.clear();

		        if (textoBusqueda.isEmpty()) {
		            for (Curso curso : RepositorioCursos.obtenerCursos()) {
		                modeloLista.addElement(curso);
		            }
		        } else {
		            for (Curso curso : RepositorioCursos.buscarCursosPorIdioma(textoBusqueda)) {
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
