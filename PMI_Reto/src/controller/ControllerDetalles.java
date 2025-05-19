package controller;

import modelo.Curso;
import repositorios.RepositorioCursos;
import view.VistaDetalles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerDetalles {
	private VistaDetalles vistaDetalles;
	private String dniAlumno;

	public ControllerDetalles(VistaDetalles vistaDetalles, String dniAlumno) {
		this.vistaDetalles = vistaDetalles;
		this.dniAlumno = dniAlumno;

		// Boton volver
		vistaDetalles.getBtnVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vistaDetalles.cerrar();
			}
		});

		// Boton inscribirse
		vistaDetalles.getBtnInscribirse().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Curso curso = vistaDetalles.getCurso();

				if (RepositorioCursos.inscribirAlumnoEnCurso(dniAlumno, curso.getIdCurso())) {
					JOptionPane.showMessageDialog(null, "Inscripción realizada con éxito.");
				}

			}
		});
	}

	public void iniciar() {
		vistaDetalles.setVisible(true); 
	}
}
