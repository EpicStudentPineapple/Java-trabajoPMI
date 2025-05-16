package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaBloqAlumno;
import view.VistaDetalleAlumno;

public class ControllerDetalleAlumno {

	private VistaDetalleAlumno vista;
	private Alumno alumno;
	private ControllerBloqAlumno controllerBloqAlumno;
	private VistaBloqAlumno vistaBloqAlumno;

	public ControllerDetalleAlumno(VistaDetalleAlumno vista, Alumno alumno, ControllerBloqAlumno controllerBloqAlumno,
			VistaBloqAlumno vistaBloqAlumno) {
		this.vista = vista;
		this.alumno = alumno;
		this.controllerBloqAlumno = controllerBloqAlumno;
		this.vistaBloqAlumno = vistaBloqAlumno;

		vista.mostrarDatosAlumno(alumno);

		vista.getBtnBloquear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (alumno.isBloqueado()) {
					JOptionPane.showMessageDialog(vista, "Este alumno ya está bloqueado.");
					return;
				}

				boolean exito = RepositorioAdministrador.bloquearAlumno(alumno.getDni());
				if (exito) {
					JOptionPane.showMessageDialog(vista, "Alumno bloqueado correctamente.");
					vista.dispose();
					controllerBloqAlumno.recargarLista();
				} else {
					JOptionPane.showMessageDialog(vista, "Error al bloquear al alumno.");
				}
			}
		});

		this.vista.getBtnVolver().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				vistaBloqAlumno.iniciar();

			}
		});
	}
}
