package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VistaContraseña extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtDni;
    private JPasswordField txtNuevaContraseña;
    private JPasswordField txtConfirmarContraseña;
    private JButton btnVolver;
    private JButton btnRestablecer;

    public VistaContraseña() {
        setTitle("Restablecer Contraseña");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("DNI:"));
        txtDni = new JTextField();
        add(txtDni);

        add(new JLabel("Nueva Contraseña:"));
        txtNuevaContraseña = new JPasswordField();
        add(txtNuevaContraseña);

        add(new JLabel("Confirmar Contraseña:"));
        txtConfirmarContraseña = new JPasswordField();
        add(txtConfirmarContraseña);

        btnVolver = new JButton("Volver");
        add(btnVolver);

        btnRestablecer = new JButton("Restablecer");
        add(btnRestablecer);
    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public JPasswordField getTxtNuevaContraseña() {
        return txtNuevaContraseña;
    }

    public JPasswordField getTxtConfirmarContraseña() {
        return txtConfirmarContraseña;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnRestablecer() {
        return btnRestablecer;
    }
}
