package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnAlumnos;
    private JButton btnProfesores;
    private JButton btnCerrar;
    
    public VistaAdministrador(String nombre) {
        setTitle("Bienvenido Administrador " + nombre);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 5, 5));
        
        btnAlumnos = new JButton("Alumnos"); // Boton alumnos
        contentPane.add(btnAlumnos);
        
        btnProfesores = new JButton("Profesores"); // Boton profesores
        contentPane.add(btnProfesores);
        
        btnCerrar = new JButton("Cerrar Sesión"); // Boton cerrar sesion
        contentPane.add(btnCerrar);
    }

    // Getters & Setters de los botones
	public JButton getBtnAlumnos() {
		return btnAlumnos;
	}

	public void setBtnAlumnos(JButton btnAlumnos) {
		this.btnAlumnos = btnAlumnos;
	}

	public JButton getBtnProfesores() {
		return btnProfesores;
	}

	public void setBtnProfesores(JButton btnProfesores) {
		this.btnProfesores = btnProfesores;
	}

	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public void setBtnCerrar(JButton btnCerrar) {
		this.btnCerrar = btnCerrar;
	}
	public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }
}
