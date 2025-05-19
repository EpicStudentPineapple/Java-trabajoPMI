package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaAdminAlumno;
import view.VistaBloqAlumno;
import view.VistaDetalleAlumno;

public class ControllerBloqAlumno {

    private VistaBloqAlumno vista;
    private VistaAdminAlumno vistaAdminAlumno;

    public ControllerBloqAlumno(VistaBloqAlumno vista, VistaAdminAlumno vistaAdminAlumno) {
        this.vista = vista;
        this.vistaAdminAlumno = vistaAdminAlumno;

        // Boton para buscar
        this.vista.getBotonBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumnos();
            }
        });

        // Mostramos la lista de alumnos
        this.vista.getListaAlumnos().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Alumno seleccionado = vista.getListaAlumnos().getSelectedValue();
                    if (seleccionado != null) {
                        VistaDetalleAlumno vistaDetalle = new VistaDetalleAlumno();
                        new ControllerDetalleAlumno(vistaDetalle, seleccionado, ControllerBloqAlumno.this);
                        vistaDetalle.setVisible(true);
                    }
                }
            }
        });
        
        // Boton volver
        this.vista.getBotonVolver().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.cerrar();
                vistaAdminAlumno.iniciar();
            }
        });
    }

    public void iniciar() {
    	vista.iniciar();
        cargarAlumnosInicial();
    }

    // Carga todos los alumnos al iniciar la vista
    private void cargarAlumnosInicial() {
        ArrayList<Alumno> todos = RepositorioAdministrador.buscarAlumnosPorNombre("");
        DefaultListModel<Alumno> modelo = vista.getModeloLista();
        modelo.clear();
        for (Alumno a : todos) {
            modelo.addElement(a);
        }
    }

    // Búsqueda por nombre
    private void buscarAlumnos() {
        String nombre = vista.getTextoBusqueda().getText().trim();
        ArrayList<Alumno> encontrados = RepositorioAdministrador.buscarAlumnosPorNombre(nombre);
        DefaultListModel<Alumno> modelo = vista.getModeloLista();
        modelo.clear();
        for (Alumno a : encontrados) {
            modelo.addElement(a);
        }
    }

    // Método público para recargar la lista desde otro controlador
    public void recargarLista() {
        buscarAlumnos(); // recarga con el criterio actual
    }
}
