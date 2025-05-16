package view;

import javax.swing.*;
import java.awt.*;

public class VistaNuevoCurso extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public JTextField txtIdioma = new JTextField(20);
    public JTextField txtDia = new JTextField(20);
    public JTextField txtHorario = new JTextField(20);
    public JTextField txtDificultad = new JTextField(20);
    public JTextField txtPrograma = new JTextField(20);
    
    public JButton btnAnadir = new JButton("Añadir");
    public JButton btnVolver = new JButton("Volver");

    public VistaNuevoCurso() {
        setTitle("Nuevo Curso");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));
        
        add(new JLabel("Idioma:"));
        add(txtIdioma);
        add(new JLabel("Día:"));
        add(txtDia);
        add(new JLabel("Horario:"));
        add(txtHorario);
        add(new JLabel("Dificultad:"));
        add(txtDificultad);
        add(new JLabel("Programa:"));
        add(txtPrograma);
        add(btnAnadir);
        add(btnVolver);
    }

    public void limpiarCampos() {
        txtIdioma.setText("");
        txtDia.setText("");
        txtHorario.setText("");
        txtDificultad.setText("");
        txtPrograma.setText("");
    }

    // Getters para los campos y botones
    public JTextField getTxtIdioma() {
        return txtIdioma;
    }

    public JTextField getTxtDia() {
        return txtDia;
    }

    public JTextField getTxtHorario() {
        return txtHorario;
    }

    public JTextField getTxtDificultad() {
        return txtDificultad;
    }

    public JTextField getTxtPrograma() {
        return txtPrograma;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
