package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import repositorios.RepositorioPersona;
import view.VistaLogin;
import view.VistaRegistro;
import view.VistaRegistroProfesor;

public class ControllerRegistroProfesor {

    private VistaRegistroProfesor vistaProfesor;
    private VistaRegistro vistaRegistroPrincipal;
    private VistaLogin vistaLogin;

    public ControllerRegistroProfesor(VistaRegistroProfesor vistaProfesor, VistaRegistro vistaRegistroPrincipal, VistaLogin vistaLogin) {
        this.vistaProfesor = vistaProfesor;
        this.vistaRegistroPrincipal = vistaRegistroPrincipal;
        this.vistaLogin = vistaLogin;

        // Boton registrar
        this.vistaProfesor.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarProfesor();
            }
        });

        // Boton volver
        this.vistaProfesor.getBtnVolver().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vistaRegistroPrincipal.setVisible(true);
                vistaProfesor.dispose();
            }
        });
    }

    // Metodo para registrar profesor
    private void registrarProfesor() {
        String nivelExperiencia = vistaProfesor.getComboNivelExperiencia().getSelectedItem().toString();
        String especializacion = vistaProfesor.getTxtEspecializacionIdioma().getText().trim();
        String dni = vistaProfesor.getDni();

        if (nivelExperiencia.isEmpty() || especializacion.isEmpty()) {
            JOptionPane.showMessageDialog(vistaProfesor, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (RepositorioPersona.existeUsuario(dni)) {
            JOptionPane.showMessageDialog(vistaProfesor, "Ya existe un usuario con ese DNI", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean registrado = RepositorioPersona.registrarProfesor(
                dni,
                vistaProfesor.getNombre(),
                vistaProfesor.getApellido(),
                vistaProfesor.getContraseña(),
                nivelExperiencia,
                especializacion
            );

            if (registrado) {
                JOptionPane.showMessageDialog(vistaProfesor, "Profesor registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vistaLogin.setVisible(true);
                vistaProfesor.dispose();
                vistaRegistroPrincipal.dispose();
            } else {
                JOptionPane.showMessageDialog(vistaProfesor, "No se pudo registrar al profesor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaProfesor, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
