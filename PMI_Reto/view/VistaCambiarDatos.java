package view;

import javax.swing.*;
import java.awt.*;

public class VistaCambiarDatos extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoCorreo;
    private JPasswordField campoContrasena;
    private JButton botonGuardar;
    private JButton botonCancelar;

    public VistaCambiarDatos() {
        setTitle("Cambiar datos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Crear componentes
        campoNombre = new JTextField();
        campoApellido = new JTextField();
        campoCorreo = new JTextField();
        campoContrasena = new JPasswordField();
        botonGuardar = new JButton("Guardar cambios");
        botonCancelar = new JButton("Cancelar");

        // Añadir componentes a la ventana
        add(new JLabel("Nuevo nombre:"));
        add(campoNombre);
        add(new JLabel("Nuevo apellido:"));
        add(campoApellido);
        add(new JLabel("Nuevo correo:"));
        add(campoCorreo);
        add(new JLabel("Nueva contraseña:"));
        add(campoContrasena);
        add(botonGuardar);
        add(botonCancelar);
    }

    public JButton getBtnGuardar() {
        return botonGuardar;
    }

    public JButton getBtnCancelar() {
        return botonCancelar;
    }

    public JTextField getTxtNombre() {
        return campoNombre;
    }

    public JTextField getTxtApellido() {
        return campoApellido;
    }

    public JTextField getTxtCorreo() {
        return campoCorreo;
    }

    public JPasswordField getTxtContrasena() {
        return campoContrasena;
    }
    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

}
