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
        	ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registrarAlumno(String dni, String nombre, String apellido, String contraseña, int edad, String correo) {
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

    public static boolean registrarProfesor(String dni, String nombre, String apellido, String contraseña, String nivelExperiencia, String especializacion) {
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
    public static boolean eliminarAlumnoPorDNI(String dni) {
        String queryAlumno = "DELETE FROM Alumno WHERE dni = ?";
        String queryPersona = "DELETE FROM Persona WHERE dni = ?";
        
        try (Connection conn = ConectorBD.getConexion()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtAlumno = conn.prepareStatement(queryAlumno);
                 PreparedStatement stmtPersona = conn.prepareStatement(queryPersona)) {
                
                stmtAlumno.setString(1, dni);
                stmtAlumno.executeUpdate();

                stmtPersona.setString(1, dni);
                stmtPersona.executeUpdate();
                
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean actualizarDatosUsuario(String dni, String nuevoNombre, String nuevoApellido, String nuevoCorreo, String nuevaContraseña) {
        String queryPersona = "UPDATE Persona SET nombre = ?, apellido = ?, contraseña = ? WHERE dni = ?";
        String queryCorreo = "UPDATE Alumno SET correo = ? WHERE dni = ?";

        try (Connection conn = ConectorBD.getConexion()) {
            conn.setAutoCommit(false);

            try (
                PreparedStatement stmtPersona = conn.prepareStatement(queryPersona);
                PreparedStatement stmtCorreo = conn.prepareStatement(queryCorreo)
            ) {
                // Actualiza tabla Persona
                stmtPersona.setString(1, nuevoNombre);
                stmtPersona.setString(2, nuevoApellido);
                stmtPersona.setString(3, nuevaContraseña);
                stmtPersona.setString(4, dni);
                stmtPersona.executeUpdate();

                // Actualiza correo en tabla Alumno
                stmtCorreo.setString(1, nuevoCorreo);
                stmtCorreo.setString(2, dni);
                stmtCorreo.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean actualizarNombreApellido(String dni, String nuevoNombre, String nuevoApellido) {
        String query = "UPDATE Persona SET nombre = ?, apellido = ? WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevoApellido);
            stmt.setString(3, dni);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    public static boolean actualizarCorreo(String dni, String nuevoCorreo) {
        String query = "UPDATE Alumno SET correo = ? WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setString(1, nuevoCorreo);
            stmt.setString(2, dni);
            stmt.executeUpdate(); 
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;   
        }
    }
    public static boolean verificarDniCorreo(String dni, String correo) {
        String queryCheck = "SELECT * FROM Alumno WHERE dni = ? AND correo = ?";
        try (PreparedStatement checkStmt = ConectorBD.getConexion().prepareStatement(queryCheck)) {
            checkStmt.setString(1, dni);
            checkStmt.setString(2, correo);
            ResultSet rs = checkStmt.executeQuery();
            return rs.next();  // Si existe un registro que coincida, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Si no existe, retorna false
    }
    public static boolean existeUsuario(String dni) {
        String query = "SELECT * FROM Persona WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Si existe al menos un registro con ese DNI
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }





}
