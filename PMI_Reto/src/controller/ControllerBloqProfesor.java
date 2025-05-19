package controller;

import modelo.Profesor;
import repositorios.RepositorioAdministrador;
import view.VistaAdminProfesor;
import view.VistaBloqProfesor;
import view.VistaDetalleProfesor;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerBloqProfesor {

    private VistaBloqProfesor vista;
    private VistaAdminProfesor vistaAdminProfesor;

    public ControllerBloqProfesor(VistaBloqProfesor vista, VistaAdminProfesor vistaAdminProfesor) {
        this.vista = vista;
        this.vistaAdminProfesor = vistaAdminProfesor;

        // Boton buscar
        this.vista.getBotonBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProfesores();
            }
        });

        // Boton volver
        this.vista.getBotonVolver().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.cerrar();
                vistaAdminProfesor.iniciar();
            }
        });

        // Mostramos lista de profesores
        this.vista.getListaProfesores().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Profesor seleccionado = vista.getListaProfesores().getSelectedValue();
                    if (seleccionado != null) {
                        VistaDetalleProfesor detalle = new VistaDetalleProfesor();
                        new ControllerDetalleProfesor(detalle, seleccionado, ControllerBloqProfesor.this);
                        detalle.setVisible(true);
                    }
                }
            }
        });
    }

    public void iniciar() {
        vista.iniciar();
        cargarProfesoresInicial();
    }

    // Metodo para cargar la lista de profesores
    private void cargarProfesoresInicial() {
        ArrayList<Profesor> todos = RepositorioAdministrador.buscarProfesoresPorNombre("");
        DefaultListModel<Profesor> modelo = vista.getModeloLista();
        modelo.clear();
        for (Profesor p : todos) {
            modelo.addElement(p);
        }
    }

    // Metodo para buscar profesores
    private void buscarProfesores() {
        String nombre = vista.getTextoBusqueda().getText().trim();
        ArrayList<Profesor> encontrados = RepositorioAdministrador.buscarProfesoresPorNombre(nombre);
        DefaultListModel<Profesor> modelo = vista.getModeloLista();
        modelo.clear();
        for (Profesor p : encontrados) {
            modelo.addElement(p);
        }
    }

    public void recargarLista() {
        buscarProfesores();
    }
}
