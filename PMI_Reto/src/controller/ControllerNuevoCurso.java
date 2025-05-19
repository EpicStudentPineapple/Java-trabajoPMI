package controller;

import javax.swing.JOptionPane;

import repositorios.RepositorioCursos;
import view.VistaNuevoCurso;

public class ControllerNuevoCurso {
    private VistaNuevoCurso vnc;
    private ControllerCursoProfesor controladorPrincipal;

    public ControllerNuevoCurso(VistaNuevoCurso vnc, ControllerCursoProfesor controladorPrincipal) {
        this.vnc = vnc;
        this.controladorPrincipal = controladorPrincipal;
        agregarListeners();
    }

    // Metodo para añadir un nuevo curso
    private void agregarListeners() {
        vnc.getBtnAnadir().addActionListener(e -> {
            String idioma = vnc.getTxtIdioma().getText();
            String dia = vnc.getTxtDia().getText();
            String horario = vnc.getTxtHorario().getText();
            String dificultad = vnc.getTxtDificultad().getText();
            String programa = vnc.getTxtPrograma().getText();

            if (!idioma.isEmpty() && !dia.isEmpty() && !horario.isEmpty()
                && !dificultad.isEmpty() && !programa.isEmpty()) {
                RepositorioCursos.nuevoCurso(idioma, dia, horario, dificultad, programa);
                vnc.limpiarCampos();
                controladorPrincipal.refrescarCursos();
            } else {
                JOptionPane.showMessageDialog(vnc, "Todos los campos son obligatorios.");
            }
        });

        // Boton volver
        vnc.getBtnVolver().addActionListener(e -> vnc.dispose());
    }

    public void iniciar() {
    	vnc.setVisible(true);
    }
}
