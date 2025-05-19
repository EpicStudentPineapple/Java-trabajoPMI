package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VistaAdminProfesor;
import view.VistaAdministrador;
import view.VistaAñadirProfesor;
import view.VistaBloqProfesor;
import view.VistaDesbloqProfesor;
import view.VistaEliminarProfesor;
import view.VistaEditarProfesor;

public class ControllerAdminProfesor {

	private VistaAdminProfesor vista;
	private VistaAdministrador vistaAdministrador;

	public ControllerAdminProfesor(VistaAdminProfesor vista, VistaAdministrador vistaAdministrador) {
		this.vista = vista;
		this.vistaAdministrador = vistaAdministrador;

		this.vista.getBtnBloquear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaBloqProfesor vistaBloqProfesor = new VistaBloqProfesor();
				ControllerBloqProfesor controllerBloqProfesor = new ControllerBloqProfesor(vistaBloqProfesor, vista);
				vista.cerrar();
				controllerBloqProfesor.iniciar();
			}
		});

		this.vista.getBtnDesbloquear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaDesbloqProfesor vistaDesbloqProfesor = new VistaDesbloqProfesor();
				ControllerDesbloqProfesor controllerDesbloqProfesor = new ControllerDesbloqProfesor(
						vistaDesbloqProfesor, vista);
				vista.cerrar();
				controllerDesbloqProfesor.iniciar();
			}
		});

		this.vista.getBtnAñadir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaAñadirProfesor vistaAñadirProfesor = new VistaAñadirProfesor();
				ControllerAñadirProfesor controllerAñadirProfesor = new ControllerAñadirProfesor(vistaAñadirProfesor,
						vista);
				vista.cerrar();
				vistaAñadirProfesor.iniciar();
			}
		});

		this.vista.getBtnEliminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaEliminarProfesor vistaEliminarProfesor = new VistaEliminarProfesor();
				ControllerEliminarProfesor controllerEliminarProfesor = new ControllerEliminarProfesor(
						vistaEliminarProfesor, vista);
				vista.cerrar();
				controllerEliminarProfesor.iniciar();
			}
		});

		this.vista.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaEditarProfesor vistaEditarProfesor = new VistaEditarProfesor();
				ControllerEditarProfesor controllerEditarProfesor = new ControllerEditarProfesor(vistaEditarProfesor,
						vista);
				vista.cerrar();
				controllerEditarProfesor.iniciar();
			}
		});

		this.vista.getBtnVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.cerrar();
				vistaAdministrador.iniciar();
			}
		});
	}
}
