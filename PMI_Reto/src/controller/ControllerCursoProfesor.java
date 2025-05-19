package controller;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;

import modelo.Curso;
import repositorios.RepositorioCursos;
import view.VistaCursoProfesor;
import view.VistaNuevoCurso;
import view.VistaProfesor;

public class ControllerCursoProfesor {
    private VistaCursoProfesor vcp;
    private VistaProfesor vp;
    private ArrayList<Curso> todosLosCursos;  // Lista completa para filtrar

    public ControllerCursoProfesor(VistaCursoProfesor vcp, VistaProfesor vp) {
        this.vcp = vcp;
        this.vp = vp;

        // Botón Añadir nuevo curso: abrir la ventana y crear su controlador
        vcp.getBtnAnadirCurso().addActionListener(e -> {
            VistaNuevoCurso vnc = new VistaNuevoCurso();
            ControllerNuevoCurso cnc = new ControllerNuevoCurso(vnc, this);
            cnc.iniciar();
            vnc.setVisible(true);
        });

        // Botón Eliminar curso
        vcp.getBtnEliminarCurso().addActionListener(e -> {
            Curso cursoSeleccionado = vcp.getCursoSeleccionado();
            if (cursoSeleccionado != null) {
                int confirmacion = JOptionPane.showConfirmDialog(vcp,
                        "¿Quieres eliminar el curso seleccionado?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    RepositorioCursos.eliminarCursos(cursoSeleccionado.getIdCurso());
                    refrescarCursos();
                }
            } else {
                JOptionPane.showMessageDialog(vcp, "Por favor, selecciona un curso para eliminar.");
            }
        });

        // Botón Volver
        vcp.getBtnVolver().addActionListener(e -> {
            vcp.cerrar();
            vp.setVisible(true);
        });

        // Añadimos listener para el buscador en tiempo real
        vcp.getBtnBuscar().addActionListener(e ->{
        	filtrarCursos();
        });
    }

    public void iniciar() {
        todosLosCursos = RepositorioCursos.obtenerCursos();  // Guardamos la lista completa
        vcp.setCursos(todosLosCursos);  // Mostramos lista completa
        vcp.iniciar();
    }

    // Método para refrescar lista de cursos
    public void refrescarCursos() {
        todosLosCursos = RepositorioCursos.obtenerCursos();
        vcp.setCursos(todosLosCursos);
    }
    
    public void filtrarCursos() {
    	String texto = vcp.getTextBuscador().getText().toLowerCase().trim();
    	if (texto.isEmpty()) {
    		vcp.setCursos(todosLosCursos);
    		return;
    	}
    	
    	ArrayList<Curso> filtrados = new ArrayList<>();
        for (Curso c : todosLosCursos) {
            if (c.getIdioma().toLowerCase().contains(texto)
             || c.getDia().toLowerCase().contains(texto)
             || c.getHorario().toLowerCase().contains(texto)
             || c.getDificultad().toLowerCase().contains(texto)
             || c.getPrograma().toLowerCase().contains(texto)) {
                filtrados.add(c);
            }
        }

        vcp.setCursos(filtrados);
    }
}
