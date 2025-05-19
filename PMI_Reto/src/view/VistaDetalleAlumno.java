package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import modelo.Alumno;

public class VistaDetalleAlumno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblDni, lblNombre, lblApellido, lblCorreo, lblBloqueado;
    private JButton btnBloquear;

    public VistaDetalleAlumno() {
        setTitle("Detalle Alumno");
        setSize(300, 250);
        setLocationRelativeTo(null);

        // Panel principal con margen
        JPanel panel = new JPanel(new GridLayout(6, 1, 0, 5));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15)); 

        lblDni = new JLabel();
        lblNombre = new JLabel();
        lblApellido = new JLabel();
        lblCorreo = new JLabel();
        lblBloqueado = new JLabel();
        btnBloquear = new JButton("Bloquear");

        panel.add(lblDni);
        panel.add(lblNombre);
        panel.add(lblApellido);
        panel.add(lblCorreo);
        panel.add(lblBloqueado);
        panel.add(btnBloquear);

        add(panel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void mostrarDatosAlumno(Alumno alumno) {
        lblDni.setText("DNI: " + alumno.getDni());
        lblNombre.setText("Nombre: " + alumno.getNombre());
        lblApellido.setText("Apellido: " + alumno.getApellido());
        lblCorreo.setText("Correo: " + alumno.getCorreo());
        lblBloqueado.setText("Bloqueado: " + (alumno.isBloqueado() ? "Sí" : "No"));
    }

    public JButton getBtnBloquear() {
        return btnBloquear;
    }
}
