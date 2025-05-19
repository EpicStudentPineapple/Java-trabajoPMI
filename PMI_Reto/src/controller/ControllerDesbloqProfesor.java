package controller;

import modelo.Profesor;
import repositorios.RepositorioAdministrador;
import view.VistaDesbloqProfesor;
import view.VistaAdminProfesor;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerDesbloqProfesor {

    private VistaDesbloqProfesor vistaDesbloqProfesor;
    private VistaAdminProfesor vistaAdminProfesor;

    public ControllerDesbloqProfesor(VistaDesbloqProfesor vistaDesbloqProfesor, VistaAdminProfesor vistaAdminProfesor) {
        this.vistaDesbloqProfesor = vistaDesbloqProfesor;
        this.vistaAdminProfesor = vistaAdminProfesor;

        // Acción botón Buscar
        this.vistaDesbloqProfesor.getBotonBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProfesoresBloqueados();
            }
        });

        // Acción botón Volver
        this.vistaDesbloqProfesor.getBotonVolver().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaDesbloqProfesor.cerrar();
                vistaAdminProfesor.iniciar();
            }
        });

        // Selección lista
        this.vistaDesbloqProfesor.getListaProfesores().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Profesor seleccionado = vistaDesbloqProfesor.getListaProfesores().getSelectedValue();
                    if (seleccionado != null) {
                        int respuesta = JOptionPane.showConfirmDialog(
                            vistaDesbloqProfesor,
                            "¿Quieres desbloquear al profesor \"" + seleccionado.getNombre() + "\" con DNI \"" + seleccionado.getDni() + "\"?",
                            "Confirmar desbloqueo",
                            JOptionPane.YES_NO_OPTION
                        );
                        if (respuesta == JOptionPane.YES_OPTION) {
                            boolean exito = RepositorioAdministrador.desbloquearProfesor(seleccionado.getDni());
                            if (exito) {
                                JOptionPane.showMessageDialog(vistaDesbloqProfesor, "Profesor desbloqueado correctamente");
                                recargarLista();
                            } else {
                                JOptionPane.showMessageDialog(vistaDesbloqProfesor, "Error al desbloquear profesor");
                            }
                        } else {
                            vistaDesbloqProfesor.cerrar();
                            vistaAdminProfesor.iniciar();
                        }
                    }
                }
            }
        });
    }

    public void iniciar() {
        vistaDesbloqProfesor.iniciar();
        cargarProfesoresBloqueados("");
    }

    // Metodo para cargar la lista de profesores bloqueados
    private void cargarProfesoresBloqueados(String filtro) {
        ArrayList<Profesor> bloqueados = RepositorioAdministrador.buscarProfesoresBloqueadosPorNombre(filtro);
        DefaultListModel<Profesor> modelo = vistaDesbloqProfesor.getModeloLista();
        modelo.clear();
        for (Profesor p : bloqueados) {
            modelo.addElement(p);
        }
    }

    // Metodo para buscar profesores bloqueados
    private void buscarProfesoresBloqueados() {
        String nombre = vistaDesbloqProfesor.getTextoBusqueda().getText().trim();
        cargarProfesoresBloqueados(nombre);
    }

    // Metodo para recargar la lista de los profesores
    public void recargarLista() {
        buscarProfesoresBloqueados();
    }
}
