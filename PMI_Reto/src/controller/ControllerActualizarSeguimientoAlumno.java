package controller;

import modelo.SeguimientoAlumno;
import repositorios.RepositorioSeguimientoAlumno;
import view.VistaActualizarSeguimientoAlumno;
import view.VistaSeguimientoAlumno;

import javax.swing.*;

public class ControllerActualizarSeguimientoAlumno {
    private VistaActualizarSeguimientoAlumno vista;
    private VistaSeguimientoAlumno vistaAnterior;
    private SeguimientoAlumno seguimiento;

    public ControllerActualizarSeguimientoAlumno(VistaActualizarSeguimientoAlumno vista, VistaSeguimientoAlumno vistaAnterior, SeguimientoAlumno seguimiento) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;
        this.seguimiento = seguimiento;

        // Rellenar campos con datos actuales
        vista.getTxtDni().setText(seguimiento.getDni());
        vista.getTxtIdCurso().setText(String.valueOf(seguimiento.getIdCurso()));
        vista.getTxtNivel().setText(seguimiento.getNivelIdioma());
        vista.getTxtAsistencia().setText(String.valueOf(seguimiento.getAsistencia()));
        vista.getTxtParticipacion().setText(String.valueOf(seguimiento.getParticipacion()));
        vista.getTxtRendimiento().setText(String.valueOf(seguimiento.getRendimiento()));

        // Acción para volver
        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });

        // Acción para actualizar
        vista.getBtnActualizar().addActionListener(e -> {
            try {
                int nuevaAsistencia = Integer.parseInt(vista.getTxtAsistencia().getText());
                int nuevaParticipacion = Integer.parseInt(vista.getTxtParticipacion().getText());
                int nuevoRendimiento = Integer.parseInt(vista.getTxtRendimiento().getText());

                boolean exito = RepositorioSeguimientoAlumno.actualizarSeguimientoAlumno(
                    seguimiento.getDni(),
                    seguimiento.getIdCurso(),
                    nuevaAsistencia,
                    nuevaParticipacion,
                    nuevoRendimiento
                );

                if (exito) {
                    vistaAnterior.actualizarSeguimientos();
                    vista.dispose();
                    vistaAnterior.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al actualizar el seguimiento.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Los valores de asistencia, participación y rendimiento deben ser números.");
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
