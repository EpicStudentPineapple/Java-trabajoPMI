package view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class VistaCambiarDatos extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField Nombre;
    private JTextField Apellido;
    private JTextField Correo;
    private JPasswordField Contrasena;
    private JButton botonGuardar;
    private JButton botonCancelar;

    public VistaCambiarDatos() {
        setTitle("Cambiar datos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

      
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(new EmptyBorder(15, 20, 15, 20)); 

        
        Nombre = new JTextField();
        Apellido = new JTextField();
        Correo = new JTextField();
        Contrasena = new JPasswordField();
        botonGuardar = new JButton("Guardar cambios");
        botonCancelar = new JButton("Cancelar");

        
        panel.add(new JLabel("Nuevo nombre:"));
        panel.add(Nombre);
        panel.add(new JLabel("Nuevo apellido:"));
        panel.add(Apellido);
        panel.add(new JLabel("Nuevo correo:"));
        panel.add(Correo);
        panel.add(new JLabel("Nueva contraseña:"));
        panel.add(Contrasena);
        panel.add(botonGuardar);
        panel.add(botonCancelar);

     
        add(panel);
    }

    public JButton getBtnGuardar() {
        return botonGuardar;
    }

    public JButton getBtnCancelar() {
        return botonCancelar;
    }

    public JTextField getTxtNombre() {
        return Nombre;
    }

    public JTextField getTxtApellido() {
        return Apellido;
    }

    public JTextField getTxtCorreo() {
        return Correo;
    }

    public JPasswordField getTxtContrasena() {
        return Contrasena;
    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }
}
