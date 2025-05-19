package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

        // Panel para pedir el dni y la nueva contraseña
        JPanel contentPane = new JPanel(new GridLayout(4, 2, 10, 10));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        setContentPane(contentPane);

        contentPane.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        contentPane.add(txtDni);

        contentPane.add(new JLabel("Nueva Contraseña:"));
        txtNuevaContraseña = new JPasswordField();
        contentPane.add(txtNuevaContraseña);

        contentPane.add(new JLabel("Confirmar Contraseña:"));
        txtConfirmarContraseña = new JPasswordField();
        contentPane.add(txtConfirmarContraseña);

        btnVolver = new JButton("Volver");
        contentPane.add(btnVolver);

        btnRestablecer = new JButton("Restablecer");
        contentPane.add(btnRestablecer);
    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

    // Getters de los textfile y botones
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
