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
    private JButton btnLogin;
    private JButton btnRegistrar;
    private JButton btnRestablecerContraseña;
    private JTextField dni;
    private JPasswordField password;

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnOlvidarContraseña() {
        return btnRestablecerContraseña;
    }

    public JTextField getTxtDni() {
        return dni;
    }

    public JTextField getTxtContraseña() {
        return password;
    }

    public VistaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("DNI"));
        dni = new JTextField(10);
        panel.add(dni);

        panel.add(new JLabel("Contraseña"));
        password = new JPasswordField(10);
        panel.add(password);

        panel.add(new JLabel(""));
        btnLogin = new JButton("Login");
        panel.add(btnLogin);

        panel.add(new JLabel(""));
        btnRegistrar = new JButton("Registrar");
        panel.add(btnRegistrar);

        panel.add(new JLabel(""));
        btnRestablecerContraseña = new JButton("Restablecer Contraseña");
        panel.add(btnRestablecerContraseña);

        contentPane.add(panel);
    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }
}
