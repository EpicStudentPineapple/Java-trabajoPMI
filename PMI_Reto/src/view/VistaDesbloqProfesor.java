package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.Profesor;
import java.awt.*;

public class VistaDesbloqProfesor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textoBusqueda;
    private JButton botonBuscar;
    private JButton botonVolver;
    private JList<Profesor> listaProfesores;
    private DefaultListModel<Profesor> modeloLista;

    public VistaDesbloqProfesor() {
        setTitle("Desbloquear Profesor");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Panel búsqueda
        JPanel panelBusqueda = new JPanel();
        JLabel etiquetaBusqueda = new JLabel("Buscar por nombre: ");
        textoBusqueda = new JTextField(15);
        botonBuscar = new JButton("Buscar");
        panelBusqueda.add(etiquetaBusqueda);
        panelBusqueda.add(textoBusqueda);
        panelBusqueda.add(botonBuscar);

        // Lista profesores bloqueados
        modeloLista = new DefaultListModel<>();
        listaProfesores = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaProfesores);

        // Botón volver
        botonVolver = new JButton("Volver");
        JPanel panelAbajo = new JPanel();
        panelAbajo.add(botonVolver);

        contentPane.add(panelBusqueda, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panelAbajo, BorderLayout.SOUTH);
    }

    public void iniciar() {
        this.setVisible(true);
    }

    public void cerrar() {
        this.dispose();
    }

    // Getters
    public JTextField getTextoBusqueda() {
        return textoBusqueda;
    }

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public JList<Profesor> getListaProfesores() {
        return listaProfesores;
    }

    public DefaultListModel<Profesor> getModeloLista() {
        return modeloLista;
    }
}
