package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import modelo.SeguimientoAlumno;
import repositorios.RepositorioSeguimientoAlumno;

public class VistaSeguimientoAlumno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnVolver;
    private JButton btnAnadir;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JList<String> listaSeguimientos;
    private DefaultListModel<String> modeloLista;
    public ArrayList<SeguimientoAlumno> seguimientos;
    public JTextField txtBuscador;
    public JButton btnBuscar;
    
    public VistaSeguimientoAlumno() {
        setTitle("Seguimiento de Alumnos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Panel de busqueda
        JPanel panelBusqueda = new JPanel(new BorderLayout(5,5));
        JLabel buscar = new JLabel("Buscador");
        txtBuscador = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(buscar, BorderLayout.WEST);
        panelBusqueda.add(txtBuscador, BorderLayout.CENTER);
        panelBusqueda.add(btnBuscar, BorderLayout.EAST);
        contentPane.add(panelBusqueda, BorderLayout.NORTH);
        
        // Inicializar el modelo de lista
        modeloLista = new DefaultListModel<>();
        listaSeguimientos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaSeguimientos);
        contentPane.add(scroll, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 0));
        btnVolver = new JButton("Volver");
        btnAnadir = new JButton("Añadir");
        btnActualizar = new JButton("ActualizarDatos");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnVolver);
        panelBotones.add(btnAnadir);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    // Método para asignar la lista de seguimientos
    public void setSeguimientos(ArrayList<SeguimientoAlumno> seguimientos) {
        this.seguimientos = seguimientos;
        actualizarLista();
    }

    // Método para actualizar la lista en la interfaz
    public void actualizarLista() {
        modeloLista.clear();
        for (SeguimientoAlumno s : seguimientos) {
            modeloLista.addElement(mostrarSeguimientos(s));
        }
    }

    private String mostrarSeguimientos(SeguimientoAlumno s) {
        return "DNI: " + s.getDni() +
               " | Curso: " + s.getIdCurso() +
               " | Nivel: " + s.getNivelIdioma() +
               " | Asistencia: " + s.getAsistencia() +
               " | Participación: " + s.getParticipacion() +
               " | Rendimiento: " + s.getRendimiento();
    }

    // Método para actualizar los seguimientos en el área de texto
    public void actualizarSeguimientos() {
    	ArrayList<SeguimientoAlumno> nuevosSeguimientos = RepositorioSeguimientoAlumno.consultarSeguimientoAlumno();
        setSeguimientos(nuevosSeguimientos);
   }

    // Getters para los botones y la lista
    public JButton getBtnVolver() { 
        return btnVolver; 
    }
    public JButton getBtnAnadir() { 
        return btnAnadir; 
    }
    public JButton getBtnActualizar() { 
        return btnActualizar; 
    }
    public JButton getBtnEliminar() { 
        return btnEliminar; 
    }
    public JButton getBtnBuscar() {
    	return btnBuscar;
    }
    public JList<String> getListaSeguimientos() { 
        return listaSeguimientos; 
    }
    
    public JTextField getTextBuscador() {
    	return txtBuscador;
    }

    public DefaultListModel<String> getModeloLista() {
        return modeloLista;
    }

    public String getTextoSeguimiento(SeguimientoAlumno s) {
        return mostrarSeguimientos(s);
    }
    
    // Métodos para obtener los detalles del seguimiento seleccionado
    public String getDni() {
        int selectedIndex = listaSeguimientos.getSelectedIndex();
        if (selectedIndex != -1) {
            SeguimientoAlumno seguimiento = seguimientos.get(selectedIndex);
            return seguimiento.getDni();
        }
        return null;
    }

    public int getIdCurso() {
        int selectedIndex = listaSeguimientos.getSelectedIndex();
        if (selectedIndex != -1) {
            SeguimientoAlumno seguimiento = seguimientos.get(selectedIndex);
            return seguimiento.getIdCurso();
        }
        return 0;
    }

    public int getAsistencia() {
        int selectedIndex = listaSeguimientos.getSelectedIndex();
        if (selectedIndex != -1) {
            SeguimientoAlumno seguimiento = seguimientos.get(selectedIndex);
            return seguimiento.getAsistencia();
        }
        return 0;
    }

    public int getParticipacion() {
        int selectedIndex = listaSeguimientos.getSelectedIndex();
        if (selectedIndex != -1) {
            SeguimientoAlumno seguimiento = seguimientos.get(selectedIndex);
            return seguimiento.getParticipacion();
        }
        return 0;
    }

    public int getRendimiento() {
        int selectedIndex = listaSeguimientos.getSelectedIndex();
        if (selectedIndex != -1) {
            SeguimientoAlumno seguimiento = seguimientos.get(selectedIndex);
            return seguimiento.getRendimiento();
        }
        return 0;
    }

    public String getNivelIdioma() {
        int selectedIndex = listaSeguimientos.getSelectedIndex();
        if (selectedIndex != -1) {
            SeguimientoAlumno seguimiento = seguimientos.get(selectedIndex);
            return seguimiento.getNivelIdioma();
        }
        return null;
    }
}
