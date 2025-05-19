package controller;

import javax.swing.JOptionPane;

import repositorios.RepositorioPersona;
import view.VistaAlumno;
import view.VistaCambiarDatos;

public class ControllerCambiarDatos {
    private VistaCambiarDatos vista;
    public ControllerCambiarDatos(VistaCambiarDatos vista, VistaAlumno vistaAlumno, String dni) {
        this.vista = vista;
        
        // Boton guardar
        this.vista.getBtnGuardar().addActionListener(e -> {
            String nuevoNombre = vista.getTxtNombre().getText();
            String nuevoApellido = vista.getTxtApellido().getText();
            String nuevoCorreo = vista.getTxtCorreo().getText();
            String nuevaContraseña = new String(vista.getTxtContrasena().getPassword());

            if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoCorreo.isEmpty() || nuevaContraseña.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.");
                return;
            }

            boolean actualizado = RepositorioPersona.actualizarNombreApellido(dni, nuevoNombre, nuevoApellido);
            boolean actualizadoCorreo = RepositorioPersona.actualizarCorreo(dni, nuevoCorreo);
            boolean actualizadoContraseña = RepositorioPersona.actualizarContraseña(dni, nuevaContraseña);

            if (actualizado && actualizadoCorreo && actualizadoContraseña) {
                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");
                vista.cerrar();
                vistaAlumno.iniciar();  
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar los datos.");
            }
        });


        // Boton cancelar
        this.vista.getBtnCancelar().addActionListener(e -> {
            vista.cerrar();
            vistaAlumno.iniciar();  
        });
    }

    public void iniciar() {
        vista.iniciar();
    }
}
