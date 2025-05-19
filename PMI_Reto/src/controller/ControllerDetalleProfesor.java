package controller;

import modelo.Profesor;
import repositorios.RepositorioAdministrador;
import view.VistaDetalleProfesor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerDetalleProfesor {

    private VistaDetalleProfesor vista;
    private Profesor profesor;
    private ControllerBloqProfesor controladorPadre;

    public ControllerDetalleProfesor(VistaDetalleProfesor vista, Profesor profesor, ControllerBloqProfesor padre) {
        this.vista = vista;
        this.profesor = profesor;
        this.controladorPadre = padre;

        vista.mostrarDatosProfesor(profesor);

        vista.getBtnBloquear().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (profesor.isBloqueado()) {
                    JOptionPane.showMessageDialog(vista, "Este profesor ya está bloqueado.");
                    return;
                }

                boolean exito = RepositorioAdministrador.bloquearProfesor(profesor.getDni());
                if (exito) {
                    JOptionPane.showMessageDialog(vista, "Profesor bloqueado correctamente.");
                    vista.dispose();
                    controladorPadre.recargarLista();
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al bloquear al profesor.");
                }
            }
        });
    }
}
