package view;
import javax.swing.*;

import repositorios.ConectorBD;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class VistaNuevoSeguimientoAlumno extends JFrame {
    private JTextField dni, idCurso, asistencia, participacion, rendimiento;
    private JComboBox<String> nivelIdioma;
    private JButton botonAnadir, botonVolver;

    public VistaNuevoSeguimientoAlumno() {
        setTitle("Añadir nuevo seguimiento");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Crear campos de texto
        dni = new JTextField();
        idCurso = new JTextField();
        asistencia = new JTextField();
        participacion = new JTextField();
        rendimiento = new JTextField();

        // Crear JComboBox con los niveles fijos
        String[] nivelesIdioma = {"A1", "A2", "B1", "B2", "C1", "C2"};
        nivelIdioma = new JComboBox<>(nivelesIdioma);

        // Añadir componentes al panel
        panel.add(new JLabel("ID del alumno:"));
        panel.add(dni);
        panel.add(new JLabel("ID del curso:"));
        panel.add(idCurso);
        panel.add(new JLabel("Asistencia:"));
        panel.add(asistencia);
        panel.add(new JLabel("Participación:"));
        panel.add(participacion);
        panel.add(new JLabel("Rendimiento:"));
        panel.add(rendimiento);
        panel.add(new JLabel("Nivel de idioma:"));
        panel.add(nivelIdioma);

        // Botones
        botonAnadir = new JButton("Añadir");
        botonVolver = new JButton("Volver");

        // Añadir acciones a los botones
        botonAnadir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anadirSeguimiento();
            }
        });

        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAnadir);
        panelBotones.add(botonVolver);

        // Añadir todo al frame
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void anadirSeguimiento() {
        String idAlumno = dni.getText().trim();
        String idCurso = this.idCurso.getText().trim();
        String asistencia = this.asistencia.getText().trim();
        String participacion = this.participacion.getText().trim();
        String rendimiento = this.rendimiento.getText().trim();
        String nivel = (String) nivelIdioma.getSelectedItem();

        if (idAlumno.isEmpty() || idCurso.isEmpty() || asistencia.isEmpty()
                || participacion.isEmpty() || rendimiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.");
            return;
        }

        try {
            Connection conexion = ConectorBD.getConexion();
            String sql = "INSERT INTO seguimiento_alumno (id_alumno, id_curso, asistencia, participacion, rendimiento, nivel_idioma) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idAlumno));
            ps.setInt(2, Integer.parseInt(idCurso));
            ps.setDouble(3, Double.parseDouble(asistencia));
            ps.setString(4, participacion);
            ps.setString(5, rendimiento);
            ps.setString(6, nivel);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Seguimiento añadido correctamente.");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al añadir seguimiento: " + ex.getMessage());
        }
    }

    public void limpiarCampos() {
        dni.setText("");
        idCurso.setText("");
        asistencia.setText("");
        participacion.setText("");
        rendimiento.setText("");
        nivelIdioma.setSelectedIndex(0);
    }
    
    public JTextField getDni() {
        return dni;
    }

    public JComboBox<String> getNivelIdioma() {
        return nivelIdioma;
    }

    public JTextField getIdCurso() {
        return idCurso;
    }

    public JTextField getAsistencia() {
        return asistencia;
    }

    public JTextField getParticipacion() {
        return participacion;
    }

    public JTextField getRendimiento() {
        return rendimiento;
    }
    
    public JButton getBtnAnadir() {
        return botonAnadir;
    }

    public JButton getBtnVolver() {
        return botonVolver;
    }
}
