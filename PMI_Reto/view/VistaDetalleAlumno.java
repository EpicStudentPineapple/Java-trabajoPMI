package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import modelo.Alumno;

public class VistaDetalleAlumno extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel etiquetaDni;
    private JLabel etiquetaNombre;
    private JLabel etiquetaApellido;
    private JLabel etiquetaCorreo;
    private JLabel etiquetaBloqueado;

    private JButton btnBloquear;
    private JButton btnVolver;

    public VistaDetalleAlumno() {
        setTitle("Detalle Alumno");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 1, 0, 5));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        etiquetaDni = new JLabel();
        etiquetaNombre = new JLabel();
        etiquetaApellido = new JLabel();
        etiquetaCorreo = new JLabel();
        etiquetaBloqueado = new JLabel();
        btnBloquear = new JButton("Bloquear");
        btnVolver = new JButton("Volver");

        panel.add(etiquetaDni);
        panel.add(etiquetaNombre);
        panel.add(etiquetaApellido);
        panel.add(etiquetaCorreo);
        panel.add(etiquetaBloqueado);
        panel.add(btnBloquear);
        panel.add(btnVolver);

        add(panel);
    }

    public void mostrarDatosAlumno(Alumno alumno) {
        etiquetaDni.setText("DNI: " + alumno.getDni());
        etiquetaNombre.setText("Nombre: " + alumno.getNombre());
        etiquetaApellido.setText("Apellido: " + alumno.getApellido());
        etiquetaCorreo.setText("Correo: " + alumno.getCorreo());
        etiquetaBloqueado.setText("Bloqueado: " + (alumno.isBloqueado() ? "Sí" : "No"));
    }

    public JButton getBtnBloquear() {
        return btnBloquear;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void iniciar() {
        setVisible(true);
    }

    public void cerrar() {
        dispose();
    }
}
