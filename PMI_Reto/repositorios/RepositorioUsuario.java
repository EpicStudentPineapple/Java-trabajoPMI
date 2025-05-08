package repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioUsuario {

    // Método para verificar si el usuario y la contraseña son correctos
	public static boolean verificarUsuario(String dni, String contraseña){
	    String queryCheck = "SELECT * FROM Persona WHERE dni = ? AND contraseña = ?";
	    
	    try (PreparedStatement checkStmt = ConectorBD.getConexion().prepareStatement(queryCheck)) {
	        checkStmt.setString(1, dni);
	        checkStmt.setString(2, contraseña);
	        ResultSet resultSet = checkStmt.executeQuery();
	        if(resultSet.next()) {
	            return true; // Usuario existe
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // No existe
	}



	public static String obtenerTipoUsuario(String dni) {
	    String tipo = "ninguno";
	    String queryAlumno = "SELECT dni FROM Alumno WHERE dni = ?";
	    String queryProfesor = "SELECT dni FROM Profesor WHERE dni = ?";

	    try (Connection con = ConectorBD.getConexion();
	         PreparedStatement psAlumno = con.prepareStatement(queryAlumno);
	         PreparedStatement psProfesor = con.prepareStatement(queryProfesor)) {
	        
	        // Verificar si es un alumno
	        psAlumno.setString(1, dni);
	        ResultSet rsAlumno = psAlumno.executeQuery();
	        if (rsAlumno.next()) {
	            tipo = "alumno";
	        } else {
	            // Si no es alumno, verificar si es profesor
	            psProfesor.setString(1, dni);
	            ResultSet rsProfesor = psProfesor.executeQuery();
	            if (rsProfesor.next()) {
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
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            return rs.getString("Nombre");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}