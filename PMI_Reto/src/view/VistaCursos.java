
package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import modelo.Curso;
import java.awt.*;

public class VistaCursos extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton botonBuscar;
    private JButton botonVolver;
    private JTextField textoBusqueda;
    private JList<Curso> listaCursos;
    private DefaultListModel<Curso> modeloLista;

    public VistaCursos() {
        setTitle("Cursos Disponibles");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Panel de busqueda
        JPanel panelBusqueda = new JPanel();
        JLabel etiquetaBusqueda = new JLabel("Buscar por idioma: ");
        textoBusqueda = new JTextField(15);
        botonBuscar = new JButton("Buscar");
        panelBusqueda.add(etiquetaBusqueda);
        panelBusqueda.add(textoBusqueda);
        panelBusqueda.add(botonBuscar);

        // Panel de cursos
        modeloLista = new DefaultListModel<>();
        listaCursos = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaCursos);

        // Boton Volver
        botonVolver = new JButton("Volver");

        // Panel con boton Volver
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonVolver);

        contentPane.add(panelBusqueda, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    public JButton getBtnBuscar() {
        return botonBuscar;
    }

    public JTextField getTextoBusqueda() {
        return textoBusqueda;
    }

    public DefaultListModel<Curso> getModeloLista() {
        return modeloLista;
    }

    public JButton getBtnVolver() {
        return botonVolver;
    }

    public JList<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(JList<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
}
