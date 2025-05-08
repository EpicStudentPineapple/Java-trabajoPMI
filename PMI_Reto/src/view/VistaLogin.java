package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButtonRegistrar;
	private JTextField dni;
	private JPasswordField password;


	/**
	 * Launch the application.
	 */
	/*El main para cargar una vista ejecutandola directamente.
	 * public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
    public JButton getBtnLogin() {
    	return btnNewButton;
    }
    public JButton getBtnRegistrar() {
    	return btnNewButtonRegistrar;
    }
   public JTextField getTxtDni() {
	   return dni;
   }
   public JTextField getTxtContraseña() {
	   return password;
   }

	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 filas, 2 columnas

		panel.add(new JLabel("DNI"));
		dni = new JTextField(10);
		panel.add(dni);

		panel.add(new JLabel("Contraseña"));
		password = new JPasswordField(10);
		panel.add(password);

		panel.add(new JLabel("")); // Celda vacía para mantener alineación
		btnNewButton = new JButton("Login");
		panel.add(btnNewButton);
		panel.add(new JLabel("")); // Celda vacía para mantener alineación
		btnNewButtonRegistrar = new JButton("Registrar");
		panel.add(btnNewButtonRegistrar);
		contentPane.add(panel);
		
		
	}

}