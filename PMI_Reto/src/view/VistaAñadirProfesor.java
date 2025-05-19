package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaAñadirProfesor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JPasswordField txtContraseña;
	private JTextField txtNivelExperiencia;
	private JTextField txtEspecializacionIdioma;
	private JButton btnAñadir;
	private JButton btnVolver;

	public VistaAñadirProfesor() {
		setTitle("Añadir Profesor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 350);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(7, 2, 10, 10));

		((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

		add(new JLabel("DNI:"));
		txtDni = new JTextField();
		add(txtDni);

		add(new JLabel("Nombre:"));
		txtNombre = new JTextField();
		add(txtNombre);

		add(new JLabel("Apellido:"));
		txtApellido = new JTextField();
		add(txtApellido);

		add(new JLabel("Contraseña:"));
		txtContraseña = new JPasswordField();
		add(txtContraseña);

		add(new JLabel("Nivel de Experiencia:"));
		txtNivelExperiencia = new JTextField();
		add(txtNivelExperiencia);

		add(new JLabel("Especialización Idioma:"));
		txtEspecializacionIdioma = new JTextField();
		add(txtEspecializacionIdioma);

		btnAñadir = new JButton("Añadir");
		add(btnAñadir);

		btnVolver = new JButton("Volver");
		add(btnVolver);
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public JPasswordField getTxtContraseña() {
		return txtContraseña;
	}

	public JTextField getTxtNivelExperiencia() {
		return txtNivelExperiencia;
	}

	public JTextField getTxtEspecializacionIdioma() {
		return txtEspecializacionIdioma;
	}

	public JButton getBtnAñadir() {
		return btnAñadir;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void iniciar() {
		this.setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}
}
