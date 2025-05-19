package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class VistaAñadirAlumno extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCorreo;
	private JTextField txtEdad;
	private JPasswordField txtContraseña;
	private JButton btnAñadir;
	private JButton btnVolver;

	public VistaAñadirAlumno() {
		setTitle("Añadir Alumno");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 400);
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

		add(new JLabel("Edad:"));
		txtEdad = new JTextField();
		add(txtEdad);

		add(new JLabel("Correo:"));
		txtCorreo = new JTextField();
		add(txtCorreo);

		btnAñadir = new JButton("Añadir");
		add(btnAñadir);

		btnVolver = new JButton("Volver");
		add(btnVolver);
	}

	public JTextField getTxtDni() {
		return this.txtDni;
	}

	public JTextField getTxtNombre() {
		return this.txtNombre;
	}

	public JTextField getTxtApellido() {
		return this.txtApellido;
	}

	public JTextField getTxtCorreo() {
		return this.txtCorreo;
	}

	public JTextField getTxtEdad() {
		return this.txtEdad;
	}

	public JPasswordField getTxtContraseña() {
		return this.txtContraseña;
	}

	public JButton getBtnAñadir() {
		return this.btnAñadir;
	}

	public JButton getBtnVolver() {
		return this.btnVolver;
	}

	public void iniciar() {
		this.setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}
}
