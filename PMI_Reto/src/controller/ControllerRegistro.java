package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.VistaLogin;
import view.VistaRegistro;
import view.VistaRegistroAlumno;
import view.VistaRegistroProfesor;

public class ControllerRegistro {

    private VistaRegistro vistaRegistro;
    private VistaLogin vistaLogin;

    public ControllerRegistro(VistaRegistro vistaRegistro, VistaLogin vistaLogin) {
        this.vistaRegistro = vistaRegistro;
        this.vistaLogin = vistaLogin;

        this.vistaRegistro.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continuarRegistro();
            }
        });

        this.vistaRegistro.getBtnVolver().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverALogin();
            }
        });
    }

    private void continuarRegistro() {
        String dni = vistaRegistro.getTxtDni().getText();
        String nombre = vistaRegistro.getTxtNombre().getText();
        String apellido = vistaRegistro.getTxtApellido().getText();
        String contraseña = new String(vistaRegistro.getTxtContraseña().getPassword());
        String confirmarContraseña = new String(vistaRegistro.getTxtConfirmarContraseña().getPassword());

        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dni.length() != 9) {
            JOptionPane.showMessageDialog(vistaRegistro, "El DNI debe tener 9 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(vistaRegistro, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (vistaRegistro.getRdbtnAlumno().isSelected()) {
            VistaRegistroAlumno vistaAlumno = new VistaRegistroAlumno(dni, nombre, apellido, contraseña);
            new ControllerRegistroAlumno(vistaAlumno, vistaRegistro, vistaLogin);
            vistaAlumno.setVisible(true);
            vistaRegistro.setVisible(false);
        } else if (vistaRegistro.getRdbtnProfesor().isSelected()) {
            VistaRegistroProfesor vistaProfesor = new VistaRegistroProfesor(dni, nombre, apellido, contraseña);
            new ControllerRegistroProfesor(vistaProfesor, vistaRegistro, vistaLogin);
            vistaProfesor.setVisible(true);
            vistaRegistro.setVisible(false);
        }
    }

    private void volverALogin() {
        vistaLogin.setVisible(true);
        vistaRegistro.dispose();
    }
}