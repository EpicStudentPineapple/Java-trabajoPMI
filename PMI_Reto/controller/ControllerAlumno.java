package controller;

import javax.swing.JOptionPane;
import repositorios.RepositorioUsuario;
import view.VistaAlumno;
import view.VistaCambiarDatos;
import view.VistaCursos;
import view.VistaLogin;

public class ControllerAlumno {
	private VistaAlumno vista;
	private VistaLogin vistaLogin;
	private String nombre;
	private String dni;

	public ControllerAlumno(VistaAlumno vistaAlumno, VistaLogin vistaLogin, String nombre, String dni) {
		this.vista = vistaAlumno;
		this.vistaLogin = vistaLogin;
		this.nombre = nombre;
		this.dni = dni;

		// Boton para ver cursos
		this.vista.getBtnCursos().addActionListener(e -> {
			VistaCursos vistaCursos = new VistaCursos();
			ControllerCursos controladorCursos = new ControllerCursos(vistaCursos, vista, dni);
			vista.cerrar();
			controladorCursos.iniciar();
		});

		// Boton para cambiar datos
		this.vista.getBtnDatos().addActionListener(e -> {
			VistaCambiarDatos vistaCambiarDatos = new VistaCambiarDatos();
			ControllerCambiarDatos controladorCambiarDatos = new ControllerCambiarDatos(vistaCambiarDatos, vista, dni);
			vista.cerrar();
			controladorCambiarDatos.iniciar();
		});

		// Boton para cerrar sesión
		this.vista.getBtnCerrar().addActionListener(e -> {
			vista.cerrar();
			vistaLogin.iniciar();
		});

		// Boton para dar de baja
		this.vista.getBtnBaja().addActionListener(e -> {
			boolean eliminado = RepositorioUsuario.eliminarAlumnoPorDNI(dni);
			if (eliminado) {
				vista.cerrar();
				vistaLogin.iniciar();
				JOptionPane.showMessageDialog(null, "Te has dado de baja correctamente.");
			} else {
				JOptionPane.showMessageDialog(null, "Error al dar de baja.");
			}
		});
	}

	public void iniciar() {
		vista.iniciar();
	}
}
