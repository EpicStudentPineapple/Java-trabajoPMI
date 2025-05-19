package view;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Alumno;

public class VistaEditarAlumno extends JFrame{

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textoBusqueda;
    private JButton botonBuscar;
    private JButton botonVolver;
    private JList<Alumno> listaAlumnos;
    private DefaultListModel<Alumno> modeloLista;

    public VistaEditarAlumno() {
        setTitle("Editar Alumno");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);


        JPanel panelBusqueda = new JPanel();
        JLabel etiquetaBusqueda = new JLabel("Buscar por nombre: ");
        textoBusqueda = new JTextField(15);
        botonBuscar = new JButton("Buscar");
        panelBusqueda.add(etiquetaBusqueda);
        panelBusqueda.add(textoBusqueda);
        panelBusqueda.add(botonBuscar);

       
        modeloLista = new DefaultListModel<>();
        listaAlumnos = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaAlumnos);

      
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

    
    public JTextField getTextoBusqueda() {
        return textoBusqueda;
    }

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public JList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public DefaultListModel<Alumno> getModeloLista() {
        return modeloLista;
    }
}
