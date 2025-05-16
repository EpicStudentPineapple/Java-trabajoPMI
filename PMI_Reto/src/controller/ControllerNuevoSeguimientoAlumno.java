package controller;

import view.VistaNuevoSeguimientoAlumno;
import view.VistaSeguimientoAlumno;
import repositorios.RepositorioSeguimientoAlumno;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ControllerNuevoSeguimientoAlumno {

    private VistaNuevoSeguimientoAlumno vistaNuevo;
    private VistaSeguimientoAlumno vistaAnterior;

    public ControllerNuevoSeguimientoAlumno(VistaNuevoSeguimientoAlumno vistaNuevo, VistaSeguimientoAlumno vistaAnterior) {
        this.vistaNuevo = vistaNuevo;
        this.vistaAnterior = vistaAnterior;

        // Acción del botón Volver
        this.vistaNuevo.getBtnVolver().addActionListener(e -> {
            vistaNuevo.dispose();
            vistaAnterior.setVisible(true);
        });

        // Acción del botón Añadir
        this.vistaNuevo.getBtnAnadir().addActionListener(e -> {
            JTextField dni = vistaNuevo.getDni();
            JComboBox<String> nivelIdioma = vistaNuevo.getNivelIdioma();

            // Validación de campos obligatorios
            if (dni.getText().isEmpty() || nivelIdioma.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(vistaNuevo, "Por favor, complete todos los campos obligatorios.");
                return;
            }

            int idCurso, asistencia, participacion, rendimiento;
            try {
                idCurso = Integer.parseInt(vistaNuevo.getIdCurso().getText().trim());
                asistencia = Integer.parseInt(vistaNuevo.getAsistencia().getText().trim());
                participacion = Integer.parseInt(vistaNuevo.getParticipacion().getText().trim());
                rendimiento = Integer.parseInt(vistaNuevo.getRendimiento().getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaNuevo, "Asegúrese de que los campos numéricos contengan valores válidos.");
                return;
            }

            boolean exito = RepositorioSeguimientoAlumno.insertarSeguimientoAlumno(
                    dni.getText().trim(), idCurso, (String) nivelIdioma.getSelectedItem(), asistencia, participacion, rendimiento
            );

            if (exito) {
                JOptionPane.showMessageDialog(vistaNuevo, "Seguimiento añadido correctamente.");
                vistaNuevo.limpiarCampos();
                vistaAnterior.actualizarSeguimientos();
            } else {
                JOptionPane.showMessageDialog(vistaNuevo, "No se pudo añadir el seguimiento. Inténtelo de nuevo.");
            }
        });
    }

    public void iniciar() {
        vistaNuevo.setVisible(true);
    }
}
