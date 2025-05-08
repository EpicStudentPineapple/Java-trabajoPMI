package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
import view.VistaLogin;
import view.VistaRegistro;
import view.VistaRegistroAlumno;

public class ControllerRegistroAlumno {

    private VistaRegistroAlumno vistaAlumno;
    private VistaRegistro vistaRegistroPrincipal;
    private VistaLogin vistaLogin;

    public ControllerRegistroAlumno(VistaRegistroAlumno vistaAlumno, VistaRegistro vistaRegistroPrincipal, VistaLogin vistaLogin) {
        this.vistaAlumno = vistaAlumno;
        this.vistaRegistroPrincipal = vistaRegistroPrincipal;
        this.vistaLogin = vistaLogin;

        this.vistaAlumno.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarAlumno();
            }
        });

        this.vistaAlumno.getBtnVolver().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vistaRegistroPrincipal.setVisible(true);
                vistaAlumno.dispose();
            }
        });
    }

    private void registrarAlumno() {
        String edad = vistaAlumno.getTxtEdad().getText();
        String correo = vistaAlumno.getTxtCorreo().getText();

        if (edad.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(vistaAlumno, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int edadInt;
        try {
            edadInt = Integer.parseInt(edad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaAlumno, "La edad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(vistaAlumno, "Formato de correo electrónico inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean registrado = RepositorioUsuario.registrarAlumno(
                    vistaAlumno.getDni(),
                    vistaAlumno.getNombre(),
                    vistaAlumno.getApellido(),
                    vistaAlumno.getContraseña(),
                    edadInt,
                    correo
            );

            if (registrado) {
                JOptionPane.showMessageDialog(vistaAlumno, "Alumno registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vistaLogin.setVisible(true);
                vistaAlumno.dispose();
                vistaRegistroPrincipal.dispose();
            } else {
                JOptionPane.showMessageDialog(vistaAlumno, "No se pudo registrar al alumno (registro duplicado u otro motivo)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vistaAlumno, "Error al registrar en la base de datos: " + ex.getMessage(), "Error de SQL", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
