package controller;

import javax.swing.JOptionPane;

import repositorios.RepositorioUsuario;
import view.VistaContraseña;
import view.VistaLogin;

public class ControllerContraseña {

    private VistaContraseña vista;
    private VistaLogin vistaLogin;

    public ControllerContraseña(VistaContraseña vista, VistaLogin vistaLogin) {
        this.vista = vista;
        this.vistaLogin = vistaLogin;

        // Boton Volver
        vista.getBtnVolver().addActionListener(e -> {
            vista.cerrar();
            vistaLogin.iniciar();
        });

        // Boton Restablecer
        vista.getBtnRestablecer().addActionListener(e -> {
            String dni = vista.getTxtDni().getText().trim();
            String nuevaContraseña = String.valueOf(vista.getTxtNuevaContraseña().getPassword());
            String confirmarContraseña = String.valueOf(vista.getTxtConfirmarContraseña().getPassword());

            // Validacion campos vacios
            if (dni.isEmpty() || nuevaContraseña.isEmpty() || confirmarContraseña.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validacion contraseñas iguales
            if (!nuevaContraseña.equals(confirmarContraseña)) {
                JOptionPane.showMessageDialog(vista, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar que el usuario exista en la base de datos
            boolean usuarioExiste = RepositorioUsuario.existeUsuario(dni);

            if (usuarioExiste) {
                boolean actualizado = RepositorioUsuario.actualizarContraseña(dni, nuevaContraseña);
                if (actualizado) {
                    JOptionPane.showMessageDialog(vista, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    vista.cerrar();
                    vistaLogin.iniciar();
                } else {
                    JOptionPane.showMessageDialog(vista, "No se pudo actualizar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vista, "El usuario con ese DNI no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void iniciar() {
        vista.iniciar();
    }
}
