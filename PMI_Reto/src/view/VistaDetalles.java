package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Curso;

public class VistaDetalles extends JFrame {
    private static final long serialVersionUID = 1L;
    private String Dni;
    private Curso curso;
    private JButton btnVolver;
    private JButton btnInscribirse;

    public VistaDetalles(Curso curso, String Dni) {
        super("Detalles del Curso");
        this.curso = curso;
        this.Dni = Dni;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(350, 300));
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.setBorder(new EmptyBorder(10, 15, 10, 15));
        panel.add(new JLabel("Idioma: " + curso.getIdioma()));
        panel.add(new JLabel("Días: " + curso.getDia()));
        panel.add(new JLabel("Horario: " + curso.getHorario()));
        panel.add(new JLabel("Dificultad: " + curso.getDificultad()));
        panel.add(new JLabel("Programa: " + curso.getPrograma()));


        JPanel panelBotones = new JPanel(new FlowLayout());
        btnVolver = new JButton("Volver");
        btnInscribirse = new JButton("Inscribirse");

        panelBotones.add(btnVolver);
        panelBotones.add(btnInscribirse);

       
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnInscribirse() {
        return btnInscribirse;
    }

    public Curso getCurso() {
        return curso;
    }

    public void cerrar() {
        this.dispose(); 
    }
}