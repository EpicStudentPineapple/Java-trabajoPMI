package view;

import javax.swing.*;
import java.awt.*;

public class VistaActualizarSeguimientoAlumno extends JFrame {
    private JTextField txtDni, txtIdCurso, txtAsistencia, txtParticipacion, txtRendimiento, txtNivel;
    private JButton btnActualizar, btnVolver;

    public VistaActualizarSeguimientoAlumno() {
        setTitle("Actualizar Seguimiento");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5));

        add(new JLabel("DNI:"));
        txtDni = new JTextField();
        txtDni.setEditable(false); // No se puede editar
        add(txtDni);

        add(new JLabel("ID Curso:"));
        txtIdCurso = new JTextField();
        txtIdCurso.setEditable(false); // No editable
        add(txtIdCurso);

        add(new JLabel("Nivel Idioma:"));
        txtNivel = new JTextField();
        txtNivel.setEditable(false); // No editable
        add(txtNivel);

        add(new JLabel("Asistencia:"));
        txtAsistencia = new JTextField();
        add(txtAsistencia);

        add(new JLabel("Participación:"));
        txtParticipacion = new JTextField();
        add(txtParticipacion);

        add(new JLabel("Rendimiento:"));
        txtRendimiento = new JTextField();
        add(txtRendimiento);

        btnActualizar = new JButton("Actualizar");
        btnVolver = new JButton("Volver");

        add(btnActualizar);
        add(btnVolver);
    }

    // Getters
    public JTextField getTxtDni() { 
    	return txtDni; 
    }
    
    public JTextField getTxtIdCurso() { 
    	return txtIdCurso; 
    }
    
    public JTextField getTxtNivel() { 
    	return txtNivel; 
    }
    
    public JTextField getTxtAsistencia() { 
    	return txtAsistencia; 
    }
    
    public JTextField getTxtParticipacion() {
    	return txtParticipacion; 
    }
    
    public JTextField getTxtRendimiento() { 
    	return txtRendimiento;
    }
    
    public JButton getBtnActualizar() { 
    	return btnActualizar; 
    }
    
    public JButton getBtnVolver() { 
    	return btnVolver; 
    }
}