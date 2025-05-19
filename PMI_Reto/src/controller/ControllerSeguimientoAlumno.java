package controller;

import view.VistaActualizarSeguimientoAlumno;
import view.VistaNuevoSeguimientoAlumno;
import view.VistaProfesor;
import view.VistaSeguimientoAlumno;
import repositorios.RepositorioSeguimientoAlumno;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.SeguimientoAlumno;

public class ControllerSeguimientoAlumno {
    private VistaSeguimientoAlumno vista;
    private VistaProfesor vistaAnterior;
    private VistaNuevoSeguimientoAlumno vnsa;

    public ControllerSeguimientoAlumno(VistaSeguimientoAlumno vista, VistaProfesor vistaAnterior) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;

        // Boton volver
        this.vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        // Botón Añadir seguimiento
        this.vista.getBtnAnadir().addActionListener(e -> {
            VistaNuevoSeguimientoAlumno vistaNuevo = new VistaNuevoSeguimientoAlumno();
            ControllerNuevoSeguimientoAlumno controllerNuevo = new ControllerNuevoSeguimientoAlumno(vistaNuevo, vista);
            controllerNuevo.iniciar();
            vista.setVisible(false); // Oculta esta ventana mientras se abre la de añadir
        });

        // Botón Actualizar seguimiento
        this.vista.getBtnActualizar().addActionListener(e -> {
            int selectedIndex = vista.getListaSeguimientos().getSelectedIndex();
            if (selectedIndex != -1) {
                SeguimientoAlumno seguimiento = vista.seguimientos.get(selectedIndex);
                VistaActualizarSeguimientoAlumno nuevaVista = new VistaActualizarSeguimientoAlumno();
                ControllerActualizarSeguimientoAlumno controllerActualizar = new ControllerActualizarSeguimientoAlumno(nuevaVista, vista, seguimiento);
                controllerActualizar.iniciar();
                vista.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione un seguimiento para actualizar.");
            }
        });

        // Botón Eliminar seguimiento
        this.vista.getBtnEliminar().addActionListener(e -> {
            int selectedIndex = vista.getListaSeguimientos().getSelectedIndex();
            if (selectedIndex != -1) {
                SeguimientoAlumno seguimiento = vista.seguimientos.get(selectedIndex);
                int confirm = JOptionPane.showConfirmDialog(vista,
                    "¿Estás seguro de que deseas eliminar este seguimiento?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean exito = RepositorioSeguimientoAlumno.eliminarSeguimientoAlumno(
                        seguimiento.getDni(), seguimiento.getIdCurso());

                    if (exito) {
                        JOptionPane.showMessageDialog(vista, "Seguimiento eliminado correctamente.");
                        vista.getListaSeguimientos().clearSelection();
                        vista.actualizarSeguimientos();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error al eliminar el seguimiento.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione un seguimiento para eliminar.");
            }
        });

        // Busqueda en tiempo real
        vista.getTextBuscador().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filtrarSeguimientos();
            }

            public void removeUpdate(DocumentEvent e) {
                filtrarSeguimientos();
            }

            public void changedUpdate(DocumentEvent e) {
                filtrarSeguimientos();
            }
        });
    }

    private void filtrarSeguimientos() {
        String textoBusqueda = vista.getTextBuscador().getText().toLowerCase();
        vista.getModeloLista().clear();

        for (SeguimientoAlumno s : vista.seguimientos) {
            String textoSeguimiento = vista.getTextoSeguimiento(s).toLowerCase();
            if (textoSeguimiento.contains(textoBusqueda)) {
                vista.getModeloLista().addElement(vista.getTextoSeguimiento(s));
            }
        }
    }

    public void iniciar() {
        vista.setVisible(true);
        vista.actualizarSeguimientos();
    }
}
