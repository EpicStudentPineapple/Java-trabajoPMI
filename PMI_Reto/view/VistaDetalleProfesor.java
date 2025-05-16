package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import modelo.Profesor;

public class VistaDetalleProfesor extends JFrame {

    private JLabel etiquetaDni;
    private JLabel etiquetaNombre;
    private JLabel etiquetaApellido;
    private JLabel etiquetaIdioma;
    private JLabel etiquetaExperiencia;
    private JLabel etiquetaBloqueado;

    private JButton btnBloquear;
    private JButton btnVolver;

    public VistaDetalleProfesor() {
        setTitle("Detalle Profesor");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 1, 5, 5));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        etiquetaDni = new JLabel();
        etiquetaNombre = new JLabel();
        etiquetaApellido = new JLabel();
        etiquetaIdioma = new JLabel();
        etiquetaExperiencia = new JLabel();
        etiquetaBloqueado = new JLabel();
        btnBloquear = new JButton("Bloquear");
        btnVolver = new JButton("Volver");

        panel.add(etiquetaDni);
        panel.add(etiquetaNombre);
        panel.add(etiquetaApellido);
        panel.add(etiquetaIdioma);
        panel.add(etiquetaExperiencia);
        panel.add(etiquetaBloqueado);
        panel.add(btnBloquear);
        panel.add(btnVolver);

        add(panel);
    }

    public void mostrarDatosProfesor(Profesor profesor) {
        etiquetaDni.setText("DNI: " + profesor.getDni());
        etiquetaNombre.setText("Nombre: " + profesor.getNombre());
        etiquetaApellido.setText("Apellido: " + profesor.getApellido());
        etiquetaIdioma.setText("Idioma: " + profesor.getIdioma());
        etiquetaExperiencia.setText("Experiencia: " + profesor.getExperiencia());
        etiquetaBloqueado.setText("Bloqueado: " + (profesor.isBloqueado() ? "Sí" : "No"));
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
