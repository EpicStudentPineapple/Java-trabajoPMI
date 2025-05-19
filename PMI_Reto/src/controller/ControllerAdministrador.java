package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VistaAdminAlumno;
import view.VistaAdminProfesor;
import view.VistaAdministrador;
import view.VistaLogin;

public class ControllerAdministrador {
	private VistaAdministrador vista;
	private VistaLogin vistaLogin;
	private String nombre;
	
	public ControllerAdministrador(VistaAdministrador vista,VistaLogin vistaLogin, String nombre) {
		this.vista = vista;
		this.vistaLogin = vistaLogin;
		this.nombre = nombre;
		
		// Boton para la vista adminAlumno
		this.vista.getBtnAlumnos().addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
			VistaAdminAlumno vistaAdminAlumno = new VistaAdminAlumno();
			new ControllerAdminAlumno (vistaAdminAlumno, vista);
			vista.cerrar();;
			vistaAdminAlumno.iniciar();
			}
		});
		
		// Boton para la vistaAdminProfesor
		this.vista.getBtnProfesores().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			VistaAdminProfesor vistaAdminProfesor = new VistaAdminProfesor();
			new ControllerAdminProfesor (vistaAdminProfesor, vista);
			vista.cerrar();;
			vistaAdminProfesor.iniciar();
			}
		});
		
		// Boton de cerrar sesion
		this.vista.getBtnCerrar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			vista.cerrar();
			vistaLogin.iniciar();
				
			}
		});
	}
	
	// Ponemos visible la vista
	public void iniciar() {
		vista.setVisible(true);
	}
}
