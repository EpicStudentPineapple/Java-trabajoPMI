package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaEditarProfesorFormulario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoNivelExperiencia;
	private JTextField campoEspecializacionIdioma;
	private JTextField campoContraseña;
	private JCheckBox checkBloqueado;

	private JButton botonEditar;
	private JButton botonVolver;

	public VistaEditarProfesorFormulario() {
		setTitle("Editar Profesor");
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

		panelCampos.add(new JLabel("Nivel Experiencia:"));
		campoNivelExperiencia = new JTextField();
		panelCampos.add(campoNivelExperiencia);

		panelCampos.add(new JLabel("Especialización Idioma:"));
		campoEspecializacionIdioma = new JTextField();
		panelCampos.add(campoEspecializacionIdioma);

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

	public JTextField getCampoNivelExperiencia() {
		return campoNivelExperiencia;
	}

	public JTextField getCampoEspecializacionIdioma() {
		return campoEspecializacionIdioma;
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
