package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaEditarAlumnoFormulario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoEdad;
	private JTextField campoCorreo;
	private JTextField campoContraseña;
	private JCheckBox checkBloqueado;

	private JButton botonEditar;
	private JButton botonVolver;

	public VistaEditarAlumnoFormulario() {
		setTitle("Editar Alumno");
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel panelCampos = new JPanel(new GridLayout(6, 2, 10, 10));
		panelCampos.setBorder(new EmptyBorder(15, 15, 15, 15));

		panelCampos.add(new JLabel("Nombre:"));
		campoNombre = new JTextField();
		panelCampos.add(campoNombre);

		panelCampos.add(new JLabel("Apellido:"));
		campoApellido = new JTextField();
		panelCampos.add(campoApellido);

		panelCampos.add(new JLabel("Edad:"));
		campoEdad = new JTextField();
		panelCampos.add(campoEdad);

		panelCampos.add(new JLabel("Correo:"));
		campoCorreo = new JTextField();
		panelCampos.add(campoCorreo);

		panelCampos.add(new JLabel("Contraseña:"));
		campoContraseña = new JTextField();
		panelCampos.add(campoContraseña);

		panelCampos.add(new JLabel("¿Bloqueado?"));
		checkBloqueado = new JCheckBox();
		panelCampos.add(checkBloqueado);

		JPanel panelBotones = new JPanel();
		botonEditar = new JButton("Editar");
		botonVolver = new JButton("Volver");
		panelBotones.add(botonEditar);
		panelBotones.add(botonVolver);

		add(panelCampos, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
	}

	public void iniciar() {
		setVisible(true);
	}

	public void cerrar() {
		dispose();
	}

	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public JTextField getCampoApellido() {
		return campoApellido;
	}

	public JTextField getCampoEdad() {
		return campoEdad;
	}

	public JTextField getCampoCorreo() {
		return campoCorreo;
	}

	public JTextField getCampoContraseña() {
		return campoContraseña;
	}

	public JCheckBox getCheckBloqueado() {
		return checkBloqueado;
	}

	public JButton getBotonEditar() {
		return botonEditar;
	}

	public JButton getBotonVolver() {
		return botonVolver;
	}
}
