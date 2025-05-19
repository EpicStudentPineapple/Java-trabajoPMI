package controller;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaDesbloqAlumno;
import view.VistaAdminAlumno;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControllerDesbloqAlumno {

    private VistaDesbloqAlumno vistaDesbloqAlumno;
    private VistaAdminAlumno vistaAdminAlumno;

    public ControllerDesbloqAlumno(VistaDesbloqAlumno vistaDesbloqAlumno, VistaAdminAlumno vistaAdminAlumno) {
        this.vistaDesbloqAlumno = vistaDesbloqAlumno;
        this.vistaAdminAlumno = vistaAdminAlumno;

        // Acción botón Buscar
        this.vistaDesbloqAlumno.getBotonBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAlumnosBloqueados();
            }
        });

        // Acción botón Volver
        this.vistaDesbloqAlumno.getBotonVolver().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaDesbloqAlumno.cerrar();
                vistaAdminAlumno.iniciar();
            }
        });

        // Selección lista - desbloquear alumno seleccionado
        this.vistaDesbloqAlumno.getListaAlumnos().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Alumno seleccionado = vistaDesbloqAlumno.getListaAlumnos().getSelectedValue();
                    if (seleccionado != null) {
                    	int respuesta = JOptionPane.showConfirmDialog(
                    		    vistaDesbloqAlumno,
                    		    "¿Quieres desbloquear al alumno \"" + seleccionado.getNombre() + "\" con DNI \"" + seleccionado.getDni() + "\"?",
                    		    "Confirmar desbloqueo",
                    		    JOptionPane.YES_NO_OPTION
                    		);
                        
                        if (respuesta == JOptionPane.YES_OPTION) {
                            // Desbloquear alumno
                            seleccionado.setBloqueado(false);
                            RepositorioAdministrador.desbloquearAlumno(seleccionado.getDni()); // Actualiza en BD
                            recargarLista();
                            JOptionPane.showMessageDialog(vistaDesbloqAlumno, "Alumno desbloqueado correctamente");
                        } else {
                            // Si es NO, cerrar la vista de desbloqueo y volver a admin
                            vistaDesbloqAlumno.cerrar();
                            vistaAdminAlumno.iniciar();
                        }
                    }
                }
            }
        });

    }

    public void iniciar() {
        vistaDesbloqAlumno.iniciar();
        cargarAlumnosBloqueados("");
    }

    // Metodo para cargar los alumno bloqueados
    private void cargarAlumnosBloqueados(String filtro) {
        ArrayList<Alumno> bloqueados = RepositorioAdministrador.buscarAlumnosBloqueadosPorNombre(filtro);
        DefaultListModel<Alumno> modelo = vistaDesbloqAlumno.getModeloLista();
        modelo.clear();
        for (Alumno a : bloqueados) {
            modelo.addElement(a);
        }
    }

    // Metodo para buscar los alumnos bloqueados
    private void buscarAlumnosBloqueados() {
        String nombre = vistaDesbloqAlumno.getTextoBusqueda().getText().trim();
        cargarAlumnosBloqueados(nombre);
    }

    // Metodo para recargar la lista
    public void recargarLista() {
        buscarAlumnosBloqueados();
    }
}
