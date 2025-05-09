package view;

import javax.swing.JFrame;

public class VistaAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;

    public VistaAdministrador(String nombre) {
        setTitle("Bienvenido Administrador " + nombre);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
