package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaAdminProfesor extends JFrame {

	private JPanel contentPane;
	private JButton btnBloquear;
	private JButton btnDesbloquear;
	private JButton btnVolver;
	private JButton btnAñadir;
	private JButton btnEditar;
	private JButton btnEliminar;
	private static final long serialVersionUID = 1L;

	public VistaAdminProfesor() {
		setTitle("Profesores ");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 2, 5, 5));

		btnBloquear = new JButton("Bloquear");
		contentPane.add(btnBloquear);

		btnDesbloquear = new JButton("Desbloquear");
		contentPane.add(btnDesbloquear);

		btnAñadir = new JButton("Añadir");
		contentPane.add(btnAñadir);

		btnEditar = new JButton("Editar");
		contentPane.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		contentPane.add(btnEliminar);

		btnVolver = new JButton("Volver");
		contentPane.add(btnVolver);
	}

	public JButton getBtnBloquear() {
		return btnBloquear;
	}

	public void setBtnBloquear(JButton btnBloquear) {
		this.btnBloquear = btnBloquear;
	}

	public JButton getBtnDesbloquear() {
		return btnDesbloquear;
	}

	public void setBtnDesbloquear(JButton btnDesbloquear) {
		this.btnDesbloquear = btnDesbloquear;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	public JButton getBtnAñadir() {
		return btnAñadir;
	}

	public void setBtnAñadir(JButton btnAñadir) {
		this.btnAñadir = btnAñadir;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public void iniciar() {
		this.setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}
}
