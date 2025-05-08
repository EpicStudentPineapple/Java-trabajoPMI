package repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RepositorioUsuario {

    public static boolean verificarUsuario(String dni, String contraseña){
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

    public static String obtenerTipoUsuario(String dni) {
        String tipo = "ninguno";
        String queryAlumno = "SELECT dni FROM Alumno WHERE dni = ?";
        String queryProfesor = "SELECT dni FROM Profesor WHERE dni = ?";
        try (Connection con = ConectorBD.getConexion();
             PreparedStatement psAlumno = con.prepareStatement(queryAlumno);
             PreparedStatement psProfesor = con.prepareStatement(queryProfesor)) {
            psAlumno.setString(1, dni);
            if (psAlumno.executeQuery().next()) {
                tipo = "alumno";
            } else {
                psProfesor.setString(1, dni);
                if (psProfesor.executeQuery().next()) {
                    tipo = "profesor";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipo;
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

    public static boolean registrarAlumno(String dni, String nombre, String apellido, String contraseña, int edad, String correo) throws SQLException {
        Connection conn = ConectorBD.getConexion();
        try {
            conn.setAutoCommit(false);
            String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setString(1, dni);
                stmtPersona.setString(2, nombre);
                stmtPersona.setString(3, apellido);
                stmtPersona.setString(4, contraseña);
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
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public static boolean registrarProfesor(String dni, String nombre, String apellido, String contraseña, String nivelExperiencia, String especializacion) throws SQLException {
        Connection conn = ConectorBD.getConexion();
        try {
            conn.setAutoCommit(false);
            String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmtPersona = conn.prepareStatement(sqlPersona)) {
                stmtPersona.setString(1, dni);
                stmtPersona.setString(2, nombre);
                stmtPersona.setString(3, apellido);
                stmtPersona.setString(4, contraseña);
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
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}