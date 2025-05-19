package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Alumno;
import repositorios.RepositorioAdministrador;
import view.VistaDetalleAlumno;

public class ControllerDetalleAlumno {

    private VistaDetalleAlumno vista;
    private Alumno alumno;
    private ControllerBloqAlumno controladorPadre;

    public ControllerDetalleAlumno(VistaDetalleAlumno vista, Alumno alumno, ControllerBloqAlumno padre) {
        this.vista = vista;
        this.alumno = alumno;
        this.controladorPadre = padre;

        vista.mostrarDatosAlumno(alumno);

        // Boton bloquear
        vista.getBtnBloquear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alumno.getBloqueado()) {
                    JOptionPane.showMessageDialog(vista, "Este alumno ya está bloqueado.");
                    return;
                }

                boolean exito = RepositorioAdministrador.bloquearAlumno(alumno.getDni());
                if (exito) {
                    JOptionPane.showMessageDialog(vista, "Alumno bloqueado correctamente.");
                    vista.dispose();
                    controladorPadre.recargarLista();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al bloquear al alumno.");
                }
            }
        });
    }
}
