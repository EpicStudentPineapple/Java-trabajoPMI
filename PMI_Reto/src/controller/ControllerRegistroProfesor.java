package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
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

        this.vistaProfesor.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarProfesor();
            }
        });

        this.vistaProfesor.getBtnVolver().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vistaRegistroPrincipal.setVisible(true);
                vistaProfesor.dispose();
            }
        });
    }

    private void registrarProfesor() {
        String nivelExperiencia = vistaProfesor.getComboNivelExperiencia().getSelectedItem().toString();
        String especializacion = vistaProfesor.getTxtEspecializacionIdioma().getText().trim();

        if (nivelExperiencia.isEmpty() || especializacion.isEmpty()) {
            JOptionPane.showMessageDialog(vistaProfesor, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean registrado = RepositorioUsuario.registrarProfesor(
                    vistaProfesor.getDni(),
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
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vistaProfesor, "Error al registrar en la base de datos: " + ex.getMessage(), "Error de SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}