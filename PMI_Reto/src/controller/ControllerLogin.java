package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import repositorios.ConectorBD;
import repositorios.RepositorioUsuario;
import view.VistaAlumno;
import view.VistaLogin;
import view.VistaProfesor;
import view.VistaRegistro;

public class ControllerLogin {
    
    private VistaLogin vista;
    
    public ControllerLogin(VistaLogin vista) {
        this.vista = vista;
        
        // Conectar a la base de datos
        ConectorBD.conectar();
        
        // Añadir listeners para los botones
        this.vista.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarUsuario();
            }
        });
        
        // Agregar el listener para el botón de registro si existe
        if (this.vista.getBtnRegistrar() != null) {
            this.vista.getBtnRegistrar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarRegistro();
                }
            });
        }
    }
    
    private void verificarUsuario() {
        String dni = vista.getTxtDni().getText();
        String contraseña = vista.getTxtContraseña().getText();
        
        if (dni.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Primero intentamos usar el RepositorioUsuario
        if (RepositorioUsuario.verificarUsuario(dni, contraseña)) {
            String tipoUsuario = RepositorioUsuario.obtenerTipoUsuario(dni);
            redirigirSegunTipoUsuario(tipoUsuario, dni);
        } else {
            // Si no funciona, intentamos con el método directo a la base de datos
            validarLoginDirecto(dni, contraseña);
        }
    }
    
    private void validarLoginDirecto(String dni, String contraseña) {
        Connection conn = ConectorBD.getConexion();
        try {
            String sql = "SELECT * FROM Persona WHERE dni = ? AND contraseña = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, dni);
            stmt.setString(2, contraseña);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Usuario autenticado correctamente
                JOptionPane.showMessageDialog(vista, "Bienvenido, " + rs.getString("nombre"), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                // Determinar si es alumno o profesor y redirigir
                String tipoUsuario = rs.getString("tipo"); // Suponiendo que hay una columna tipo
                redirigirSegunTipoUsuario(tipoUsuario, dni);
                
            } else {
                JOptionPane.showMessageDialog(vista, "DNI o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error al validar credenciales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void redirigirSegunTipoUsuario(String tipoUsuario, String dni) {
        if (tipoUsuario == null) {
            JOptionPane.showMessageDialog(vista, "Usuario sin rol asignado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        switch (tipoUsuario.toLowerCase()) {
            case "alumno":
                VistaAlumno vistaAlumno = new VistaAlumno();
                ControllerAlumno controladorAlumno = new ControllerAlumno(vistaAlumno);
                vista.dispose();
                controladorAlumno.iniciar();
                break;
                
            case "profesor":
                VistaProfesor vistaProfesor = new VistaProfesor();
                ControllerProfesor controladorProfesor = new ControllerProfesor(vistaProfesor);
                vista.dispose();
                controladorProfesor.iniciar();
                break;
                
            default:
                JOptionPane.showMessageDialog(vista, "Usuario sin rol válido (ni alumno ni profesor).", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarRegistro() {
        VistaRegistro vistaRegistro = new VistaRegistro();
        new ControllerRegistro(vistaRegistro, vista);
        vistaRegistro.setVisible(true);
        vista.setVisible(false);
    }
    
    public void iniciar() {
        vista.setVisible(true);
    }
}