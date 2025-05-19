package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
import view.*;

public class ControllerLogin {

    private VistaLogin vista;

    public ControllerLogin(VistaLogin vista) {
        this.vista = vista;

        this.vista.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarUsuario();
            }
        });

        this.vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRegistro();
            }
        });

        this.vista.getBtnOlvidarContraseña().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecuperacionContraseña();
            }
        });
    }

    private void verificarUsuario() {
        String dni = vista.getTxtDni().getText();
        String contraseña = vista.getTxtContraseña().getText();

        if (RepositorioUsuario.verificarUsuario(dni, contraseña)) {
            if (RepositorioUsuario.estaBloqueado(dni)) {
                JOptionPane.showMessageDialog(vista, "El usuario está bloqueado.");
                return; // No permite continuar
            }
            
            String rol = RepositorioUsuario.obtenerRol(dni);
            String nombre = RepositorioUsuario.obtenerNombrePorDNI(dni);

            switch (rol.toLowerCase()) {
                case "alumno":
                    VistaAlumno vistaAlumno = new VistaAlumno(nombre);
                    ControllerAlumno controladorAlumno = new ControllerAlumno(vistaAlumno, vista, nombre, dni);
                    vista.dispose();
                    controladorAlumno.iniciar();
                    break;
                case "profesor":
                    VistaProfesor vistaProfesor = new VistaProfesor(nombre);
                    ControllerProfesor controladorProfesor = new ControllerProfesor(vistaProfesor, nombre);
                    vista.dispose();
                    controladorProfesor.iniciar();
                    break;
                case "administrador":
                    VistaAdministrador vistaAdministrador = new VistaAdministrador(nombre);
                    ControllerAdministrador controladorAdministrador = new ControllerAdministrador(vistaAdministrador, vista, nombre);
                    vista.dispose();
                    controladorAdministrador.iniciar();
                    break;
                default:
                    JOptionPane.showMessageDialog(vista, "Usuario sin rol válido.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(vista, "DNI o contraseña incorrectos.");
        }
    }


    private void mostrarRegistro() {
        VistaRegistro vistaRegistro = new VistaRegistro();
        new ControllerRegistro(vistaRegistro, vista);
        vistaRegistro.setVisible(true);
        vista.setVisible(false);
    }

    private void mostrarRecuperacionContraseña() {
        VistaContraseña vistaContraseña = new VistaContraseña();
        ControllerContraseña controllerContraseña = new ControllerContraseña(vistaContraseña, vista);
        vista.setVisible(false);
        controllerContraseña.iniciar();
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
