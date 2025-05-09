package repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioUsuario {

    public static boolean verificarUsuario(String dni, String contraseña) {
        String queryCheck = "SELECT * FROM Persona WHERE dni = ? AND contraseña = ?";
        try (PreparedStatement checkStmt = ConectorBD.getConexion().prepareStatement(queryCheck)) {
            checkStmt.setString(1, dni);
            checkStmt.setString(2, contraseña);
            return checkStmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String obtenerRol(String dni) {
        String rol = "ninguno";
        String query = "SELECT rol FROM Persona WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setString(1, dni);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                rol = resultSet.getString("rol");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }

    public static String obtenerNombrePorDNI(String dni) {
        String query = "SELECT Nombre FROM Persona WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setString(1, dni);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registrarAlumno(String dni, String nombre, String apellido, String contraseña, int edad,
                                          String correo) {
        Connection conn = ConectorBD.getConexion();
        try {
            conn.setAutoCommit(false);

            String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setString(1, dni);
                stmtPersona.setString(2, nombre);
                stmtPersona.setString(3, apellido);
                stmtPersona.setString(4, contraseña);
                stmtPersona.setString(5, "alumno");
                stmtPersona.executeUpdate();
            }

            String sqlAlumno = "INSERT INTO Alumno (dni, edad, correo) VALUES (?, ?, ?)";
            try (PreparedStatement stmtAlumno = conn.prepareStatement(sqlAlumno)) {
                stmtAlumno.setString(1, dni);
                stmtAlumno.setInt(2, edad);
                stmtAlumno.setString(3, correo);
                stmtAlumno.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
        return false;
    }

    public static boolean registrarProfesor(String dni, String nombre, String apellido, String contraseña,
                                            String nivelExperiencia, String especializacion) {
        Connection conn = ConectorBD.getConexion();
        try {
            conn.setAutoCommit(false);

            String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña, rol) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setString(1, dni);
                stmtPersona.setString(2, nombre);
                stmtPersona.setString(3, apellido);
                stmtPersona.setString(4, contraseña);
                stmtPersona.setString(5, "profesor");
                stmtPersona.executeUpdate();
            }

            String sqlProfesor = "INSERT INTO Profesor (dni, nivelExperiencia, especializacionIdioma) VALUES (?, ?, ?)";
            try (PreparedStatement stmtProfesor = conn.prepareStatement(sqlProfesor)) {
                stmtProfesor.setString(1, dni);
                stmtProfesor.setString(2, nivelExperiencia);
                stmtProfesor.setString(3, especializacion);
                stmtProfesor.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
        return false;
    }
    public static boolean actualizarContraseña(String dni, String nuevaContraseña) {
        String query = "UPDATE Persona SET contraseña = ? WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setString(1, nuevaContraseña);
            stmt.setString(2, dni);
            stmt.executeUpdate();
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
