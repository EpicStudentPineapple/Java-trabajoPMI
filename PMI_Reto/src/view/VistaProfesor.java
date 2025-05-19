package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.VistaLogin;

public class VistaProfesor extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnSeguimientoAlumno;
    private JButton btnCursos;
    private JButton btnCerrarSesion;
    private VistaLogin vistaLogin;

    public VistaProfesor(String nombre) {
        setTitle("Bienvenido " + nombre);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));

        btnSeguimientoAlumno = new JButton("Seguimiento de Alumno");
        btnCursos = new JButton("Cursos");
        btnCerrarSesion = new JButton("Cerrar Sesion");

        panelBotones.add(btnSeguimientoAlumno);
        panelBotones.add(btnCursos);
        panelBotones.add(btnCerrarSesion);
        
        contentPane.add(panelBotones, BorderLayout.CENTER);
    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

    // Getters
    public JButton getBtnSeguimientoAlumno() {
        return btnSeguimientoAlumno;
    }

    public JButton getBtnCursos() {
        return btnCursos;
    }
    
    public JButton getBtnCerrarSesion() {
    	return btnCerrarSesion;
    }
}
