package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaCursos extends JFrame{
	private static final long serialVersionUID = 1L;
	public VistaCursos() {
        setTitle("Cursos disponibles");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void iniciar() {
	    this.setVisible(true);
	}

	public void cerrar() {
	    this.dispose();
	}

}
