package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import repositorios.ConectorBD;
import view.VistaLogin;
import view.VistaRegistro;
import view.VistaRegistroAlumno;
import view.VistaRegistroProfesor;

public class ControllerRegistro {
    
    private VistaRegistro vistaRegistro;
    private VistaLogin vistaLogin;
    
    public ControllerRegistro(VistaRegistro vistaRegistro, VistaLogin vistaLogin) {
        this.vistaRegistro = vistaRegistro;
        this.vistaLogin = vistaLogin;
        
        // Conectar a la base de datos si aún no está conectada
        if (ConectorBD.getConexion() == null) {
            ConectorBD.conectar();
        }
        
        // Añadir listeners para los botones
        this.vistaRegistro.getBtnRegistrar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continuarRegistro();
            }
        });
        
        this.vistaRegistro.getBtnVolver().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverALogin();
            }
        });
    }
    
    private void continuarRegistro() {
        // Validar campos
        String dni = vistaRegistro.getTxtDni().getText();
        String nombre = vistaRegistro.getTxtNombre().getText();
        String apellido = vistaRegistro.getTxtApellido().getText();
        String contraseña = new String(vistaRegistro.getTxtContraseña().getPassword());
        String confirmarContraseña = new String(vistaRegistro.getTxtConfirmarContraseña().getPassword());
        
        // Validaciones básicas
        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(vistaRegistro, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar formato DNI (9 caracteres)
        if (dni.length() != 9) {
            JOptionPane.showMessageDialog(vistaRegistro, "El DNI debe tener 9 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar que las contraseñas coincidan
        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(vistaRegistro, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Continuar según el tipo de usuario seleccionado
        if (vistaRegistro.getRdbtnAlumno().isSelected()) {
            VistaRegistroAlumno vistaAlumno = new VistaRegistroAlumno(dni, nombre, apellido, contraseña);
            new ControladorRegistroAlumno(vistaAlumno, vistaRegistro);
            vistaAlumno.setVisible(true);
            vistaRegistro.setVisible(false);
        } else if (vistaRegistro.getRdbtnProfesor().isSelected()) {
            VistaRegistroProfesor vistaProfesor = new VistaRegistroProfesor(dni, nombre, apellido, contraseña);
            new ControladorRegistroProfesor(vistaProfesor, vistaRegistro);
            vistaProfesor.setVisible(true);
            vistaRegistro.setVisible(false);
        }
    }
    
    private void volverALogin() {
        vistaLogin.setVisible(true);
        vistaRegistro.dispose();
    }
    
    // Clase interna para el controlador de registro de alumno
    private class ControladorRegistroAlumno {
        private VistaRegistroAlumno vistaAlumno;
        private VistaRegistro vistaRegistroPrincipal;
        
        public ControladorRegistroAlumno(VistaRegistroAlumno vistaAlumno, VistaRegistro vistaRegistroPrincipal) {
            this.vistaAlumno = vistaAlumno;
            this.vistaRegistroPrincipal = vistaRegistroPrincipal;
            
            // Añadir listeners
            this.vistaAlumno.getBtnRegistrar().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    registrarAlumno();
                }
            });
            
            this.vistaAlumno.getBtnVolver().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    vistaRegistroPrincipal.setVisible(true);
                    vistaAlumno.dispose();
                }
            });
        }
        
        private void registrarAlumno() {
            // Validar campos específicos de alumno
            String edad = vistaAlumno.getTxtEdad().getText();
            String correo = vistaAlumno.getTxtCorreo().getText();
            
            if (edad.isEmpty() || correo.isEmpty()) {
                JOptionPane.showMessageDialog(vistaAlumno, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar que la edad sea un número
            try {
                Integer.parseInt(edad);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(vistaAlumno, "La edad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar formato básico de correo
            if (!correo.contains("@") || !correo.contains(".")) {
                JOptionPane.showMessageDialog(vistaAlumno, "Formato de correo electrónico inválido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Registrar en la base de datos
            Connection conn = ConectorBD.getConexion();
            
            try {
                // Iniciar transacción
                conn.setAutoCommit(false);
                
                // Insertar en tabla Persona
                String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña) VALUES (?, ?, ?, ?)";
                PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona);
                stmtPersona.setString(1, vistaAlumno.getDni());
                stmtPersona.setString(2, vistaAlumno.getNombre());
                stmtPersona.setString(3, vistaAlumno.getApellido());
                stmtPersona.setString(4, vistaAlumno.getContraseña());
                stmtPersona.executeUpdate();
                
                // Insertar en tabla Alumno
                String sqlAlumno = "INSERT INTO Alumno (dni, edad, correo) VALUES (?, ?, ?)";
                PreparedStatement stmtAlumno = conn.prepareStatement(sqlAlumno);
                stmtAlumno.setString(1, vistaAlumno.getDni());
                stmtAlumno.setInt(2, Integer.parseInt(edad));
                stmtAlumno.setString(3, correo);
                stmtAlumno.executeUpdate();
                
                // Confirmar transacción
                conn.commit();
                
                JOptionPane.showMessageDialog(vistaAlumno, "Alumno registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Volver a la pantalla de login
                vistaLogin.setVisible(true);
                vistaAlumno.dispose();
                vistaRegistroPrincipal.dispose();
                
            } catch (SQLException e) {
                try {
                    // Revertir en caso de error
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                
                JOptionPane.showMessageDialog(vistaAlumno, "Error al registrar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Clase interna para el controlador de registro de profesor
    private class ControladorRegistroProfesor {
        private VistaRegistroProfesor vistaProfesor;
        private VistaRegistro vistaRegistroPrincipal;
        
        public ControladorRegistroProfesor(VistaRegistroProfesor vistaProfesor, VistaRegistro vistaRegistroPrincipal) {
            this.vistaProfesor = vistaProfesor;
            this.vistaRegistroPrincipal = vistaRegistroPrincipal;
            
            // Añadir listeners
            this.vistaProfesor.getBtnRegistrar().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    registrarProfesor();
                }
            });
            
            this.vistaProfesor.getBtnVolver().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    vistaRegistroPrincipal.setVisible(true);
                    vistaProfesor.dispose();
                }
            });
        }
        
        private void registrarProfesor() {
            // Validar campos específicos de profesor
            String nivelExperiencia = (String) vistaProfesor.getComboNivelExperiencia().getSelectedItem();
            String especializacion = vistaProfesor.getTxtEspecializacionIdioma().getText();
            
            if (especializacion.isEmpty()) {
                JOptionPane.showMessageDialog(vistaProfesor, "Debe ingresar la especialización en idioma", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Registrar en la base de datos
            Connection conn = ConectorBD.getConexion();
            
            try {
                // Iniciar transacción
                conn.setAutoCommit(false);
                
                // Insertar en tabla Persona
                String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña) VALUES (?, ?, ?, ?)";
                PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona);
                stmtPersona.setString(1, vistaProfesor.getDni());
                stmtPersona.setString(2, vistaProfesor.getNombre());
                stmtPersona.setString(3, vistaProfesor.getApellido());
                stmtPersona.setString(4, vistaProfesor.getContraseña());
                stmtPersona.executeUpdate();
                
                // Insertar en tabla Profesor
                String sqlProfesor = "INSERT INTO Profesor (dni, nivelExperiencia, especializacionIdioma) VALUES (?, ?, ?)";
                PreparedStatement stmtProfesor = conn.prepareStatement(sqlProfesor);
                stmtProfesor.setString(1, vistaProfesor.getDni());
                stmtProfesor.setString(2, nivelExperiencia);
                stmtProfesor.setString(3, especializacion);
                stmtProfesor.executeUpdate();
                
                // Confirmar transacción
                conn.commit();
                
                JOptionPane.showMessageDialog(vistaProfesor, "Profesor registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Volver a la pantalla de login
                vistaLogin.setVisible(true);
                vistaProfesor.dispose();
                vistaRegistroPrincipal.dispose();
                
            } catch (SQLException e) {
                try {
                    // Revertir en caso de error
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                
                JOptionPane.showMessageDialog(vistaProfesor, "Error al registrar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}