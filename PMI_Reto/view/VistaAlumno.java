package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaAlumno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnCursos;
    private JButton btnDatos;
    private JButton btnCerrar;

    private JButton btnBaja;
    public VistaAlumno(String nombre) {
        setTitle("Bienvenido " + nombre);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);


        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 10, 10));

        btnCursos = new JButton("Ver Cursos");
        panelBotones.add(btnCursos);

        btnDatos = new JButton("Cambiar Datos");
        panelBotones.add(btnDatos);
     
        
        btnCerrar = new JButton("Cerrar Sesión");
        panelBotones.add(btnCerrar);
        
        btnBaja = new JButton("Dar de baja");
        panelBotones.add(btnBaja);


        contentPane.add(panelBotones, BorderLayout.CENTER);
    }


    public JButton getBtnCursos() {
        return btnCursos;
    }

    public JButton getBtnDatos() {
        return btnDatos;
    }
    public JButton getBtnCerrar() {
        return btnCerrar;
    }
    public JButton getBtnBaja() {
        return btnBaja;
    }
    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

}