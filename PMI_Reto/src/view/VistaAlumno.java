package view;

import javax.swing.JFrame;

public class VistaAlumno extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public VistaAlumno(String nombre) {
		setTitle("Bienvenido "+nombre);
		setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}