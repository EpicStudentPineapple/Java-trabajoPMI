package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VistaAdminAlumno;
import view.VistaAdministrador;
import view.VistaAñadirAlumno;
import view.VistaBloqAlumno;
import view.VistaDesbloqAlumno;
import view.VistaEditarAlumno;
import view.VistaEliminarAlumno;

public class ControllerAdminAlumno {

	private VistaAdminAlumno vista;
	private VistaAdministrador vistaAdministrador;

	public ControllerAdminAlumno(VistaAdminAlumno vista, VistaAdministrador vistaAdministrador) {
		this.vista = vista;
		this.vistaAdministrador = vistaAdministrador;

		this.vista.getBtnBloquear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaBloqAlumno vistaBloqAlumno = new VistaBloqAlumno();
				ControllerBloqAlumno controllerBloqAlumno = new ControllerBloqAlumno(vistaBloqAlumno, vista);
				vista.cerrar();
				controllerBloqAlumno.iniciar();
			}
		});

		this.vista.getBtnDesbloquear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaDesbloqAlumno vistaDesbloqAlumno = new VistaDesbloqAlumno();
				ControllerDesbloqAlumno controllerDesbloqAlumno = new ControllerDesbloqAlumno(vistaDesbloqAlumno,
						vista);
				vista.cerrar();
				controllerDesbloqAlumno.iniciar();
			}
		});

		this.vista.getBtnVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				vistaAdministrador.iniciar();
			}
		});
		this.vista.getBtnAñadir().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VistaAñadirAlumno vistaAñadirAlumno = new VistaAñadirAlumno();
				ControllerAñadirAlumno controllerAñadirAlumno = new ControllerAñadirAlumno(vistaAñadirAlumno, vista);
				vista.cerrar();
				vistaAñadirAlumno.iniciar();

			}
		});

		this.vista.getBtnEliminar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VistaEliminarAlumno vistaEliminarAlumno = new VistaEliminarAlumno();
				ControllerEliminarAlumno controllerEliminarAlumno = new ControllerEliminarAlumno(vistaEliminarAlumno,
						vista);
				vista.cerrar();
				controllerEliminarAlumno.iniciar();

			}
		});
		this.vista.getBtnEditar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VistaEditarAlumno vistaEditarAlumno = new VistaEditarAlumno();
				ControllerEditarAlumno controllerEditarAlumno = new ControllerEditarAlumno(vistaEditarAlumno, vista);
				vista.cerrar();
				controllerEditarAlumno.iniciar();
			}
		});
	}
}
