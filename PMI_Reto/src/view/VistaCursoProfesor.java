package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import modelo.Curso;

public class VistaCursoProfesor extends JFrame {
    private static final long serialVersionUID = 1L;

    public DefaultListModel<String> modeloLista; 
    public JList<String> listaCursos;        
    public JButton btnVolver;
    public JButton btnAnadirCurso;
    public JButton btnEliminarCurso;
    public ArrayList<Curso> cursos;
    public JTextField txtBuscador;
    public JButton btnBuscar;
    
    public VistaCursoProfesor() {
        setTitle("Cursos disponibles");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Panel de busqueda
        JPanel panelBusqueda = new JPanel(new BorderLayout(5,5));
        JLabel buscar = new JLabel("Buscar:");
        txtBuscador = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(buscar, BorderLayout.WEST);
        panelBusqueda.add(txtBuscador, BorderLayout.CENTER);
        panelBusqueda.add(btnBuscar, BorderLayout.EAST);
        contentPane.add(panelBusqueda, BorderLayout.NORTH);
        
        // Lista de cursos
        modeloLista = new DefaultListModel<>();
        listaCursos = new JList<>(modeloLista);
        listaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaCursos);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnVolver = new JButton("Volver");
        btnAnadirCurso = new JButton("Añadir Curso");
        btnEliminarCurso = new JButton("Eliminar Curso");

        panelBotones.add(btnVolver);
        panelBotones.add(btnAnadirCurso);
        panelBotones.add(btnEliminarCurso);
        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

    // Métodos para trabajar con la lista
    public void setCursos(ArrayList<Curso> cursos) {
        modeloLista.clear();
        this.cursos = cursos; 
        for (Curso curso : cursos) {
            modeloLista.addElement(formatearCurso(curso));
        }
    }

    private String formatearCurso(Curso curso) {
        return "Curso: " + curso.getIdCurso() + 
               " | Idioma: " + curso.getIdioma() +
               " | Dia: " + curso.getDia() +
               " | Horario: " + curso.getHorario() + 
               " | Dificultad: " + curso.getDificultad() + 
               " | Programa: " + curso.getPrograma();
    }

    public Curso getCursoSeleccionado() {
        int indice = listaCursos.getSelectedIndex();
        if (indice >= 0 && cursos != null && indice < cursos.size()) {
            return cursos.get(indice);
        }
        return null;
    }

    // Getters para los botones
    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JButton getBtnAnadirCurso() {
        return btnAnadirCurso;
    }

    public JButton getBtnEliminarCurso() {
        return btnEliminarCurso;
    }
    
    public JTextField getTextBuscador() {
    	return txtBuscador;
    }
    
    public JButton getBtnBuscar() {
    	return btnBuscar;
    }
}
