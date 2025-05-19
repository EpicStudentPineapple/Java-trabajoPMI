package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import modelo.Profesor;

public class VistaDetalleProfesor extends JFrame {

    private JLabel lblDni, lblNombre, lblApellido, lblIdioma, lblExperiencia, lblBloqueado;
    private JButton btnBloquear;

    public VistaDetalleProfesor() {
        setTitle("Detalle Profesor");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        lblDni = new JLabel();
        lblNombre = new JLabel();
        lblApellido = new JLabel();
        lblIdioma = new JLabel();
        lblExperiencia = new JLabel();
        lblBloqueado = new JLabel();
        btnBloquear = new JButton("Bloquear");

        panel.add(lblDni);
        panel.add(lblNombre);
        panel.add(lblApellido);
        panel.add(lblIdioma);
        panel.add(lblExperiencia);
        panel.add(lblBloqueado);
        panel.add(btnBloquear);

        add(panel);
    }

    public void mostrarDatosProfesor(Profesor p) {
        lblDni.setText("DNI: " + p.getDni());
        lblNombre.setText("Nombre: " + p.getNombre());
        lblApellido.setText("Apellido: " + p.getApellido());
        lblIdioma.setText("Idioma: " + p.getEspecializacionIdioma());
        lblExperiencia.setText("Experiencia: " + p.getExperiencia());
        lblBloqueado.setText("Bloqueado: " + (p.isBloqueado() ? "Sí" : "No"));
    }

    public JButton getBtnBloquear() {
        return btnBloquear;
    }
}
