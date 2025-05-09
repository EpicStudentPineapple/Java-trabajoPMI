package controller;

import repositorios.RepositorioUsuario;
import view.VistaAlumno;
import view.VistaContraseña;

import javax.swing.*;

public class ControllerContraseña {

    private VistaContraseña vista;
    private VistaAlumno vistaAlumno;

    public ControllerContraseña(VistaContraseña vista, VistaAlumno vistaAlumno) {
        this.vista = vista;
        this.vistaAlumno = vistaAlumno;

        // Acción botón "Volver"
        vista.getBtnVolver().addActionListener(e -> {
            vista.cerrar();
            vistaAlumno.iniciar();
        });

        // Acción botón "Restablecer"
        vista.getBtnRestablecer().addActionListener(e -> {
            String dni = vista.getTxtDni().getText().trim();
            String nuevaContraseña = String.valueOf(vista.getTxtNuevaContraseña().getPassword());
            String confirmarContraseña = String.valueOf(vista.getTxtConfirmarContraseña().getPassword());

            if (dni.isEmpty() || nuevaContraseña.isEmpty() || confirmarContraseña.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nuevaContraseña.equals(confirmarContraseña)) {
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean actualizado = RepositorioUsuario.actualizarContraseña(dni, nuevaContraseña);

            if (actualizado) {
                JOptionPane.showMessageDialog(vista, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vista.cerrar();
                vistaAlumno.iniciar();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar la contraseña. Verifica el DNI.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void iniciar() {
        vista.iniciar();
    }
}
