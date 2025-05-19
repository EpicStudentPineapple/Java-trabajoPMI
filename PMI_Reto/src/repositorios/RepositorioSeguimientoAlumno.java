package repositorios;

import java.sql.*;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import repositorios.ConectorBD;
import modelo.Alumno;
import modelo.Curso;
import modelo.SeguimientoAlumno;

public class RepositorioSeguimientoAlumno {

	// Metodo para actualizar el seugimiento del alumno
	public static boolean actualizarSeguimientoAlumno(String dni, int idCurso, int asistencia, int participacion, int rendimiento) {
		String sql = "UPDATE SeguimientoAlumno SET asistencia = ?, participacion = ?, rendimiento = ? WHERE dni = ? AND idCurso = ?";
		
		try (Connection conexion = ConectorBD.getConexion();
			 PreparedStatement ps = conexion.prepareStatement(sql)){
			
			ps.setInt(1, asistencia);
			ps.setInt(2, participacion);
			ps.setInt(3, rendimiento);
			ps.setString(4, dni);
			ps.setInt(5, idCurso);
			
			int filasActualizadas = ps.executeUpdate();
			if(filasActualizadas > 0) {
				JOptionPane.showMessageDialog(null, "Seguimiento actualizado correctamente.");
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "No se encontro el DNI o el Id del curso.");
				return false;
			}
		}catch(SQLException e) {
			 JOptionPane.showMessageDialog(null, "Error al actualizar el seguimiento del alumno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	// Metodo para insertar seguimiento de un alumno
	public static boolean insertarSeguimientoAlumno(String dni, int idCurso, String nivelIdioma, int asistencia, int participacion, int rendimiento) {
		String sql = "INSERT INTO SeguimientoAlumno (dni, idCurso, nivelIdioma, asistencia, participacion, rendimiento) VALUES (?, ?, ?, ?, ?, ?)";
		
		try(Connection conexion = ConectorBD.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql)){
			
			ps.setString(1, dni);
			ps.setInt(2, idCurso);
			ps.setString(3, nivelIdioma);
			ps.setInt(4, asistencia);
			ps.setInt(5, participacion);
			ps.setInt(6, rendimiento);
			
			int filasActualizadas = ps.executeUpdate();
			if(filasActualizadas > 0) {
				JOptionPane.showMessageDialog(null, "Seguimiento añadido correctamente");
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "Error al añadir el seguimiento del alumno.");
				return false;
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al añadir el seguimiento del alumno." + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	// Metodo para consultar los seguimientos de alumno
	public static ArrayList<SeguimientoAlumno> consultarSeguimientoAlumno() {
	    ArrayList<SeguimientoAlumno> lista = new ArrayList<>();

	    try (Connection con = ConectorBD.getConexion();
	         Statement stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM seguimientoAlumno")) {

	        while (rs.next()) {
	        	SeguimientoAlumno s = new SeguimientoAlumno(
	        			rs.getString("dni"),
	                    rs.getInt("idCurso"),
	                    rs.getString("nivelIdioma"),
	                    rs.getInt("asistencia"),
	                    rs.getInt("participacion"),
	                    rs.getInt("rendimiento")
	            );
	            lista.add(s);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	// Metodo para eliminar un seguimiento alumno
	public static boolean eliminarSeguimientoAlumno(String dni, int idCurso) {
		String sql = "DELETE FROM SeguimientoAlumno WHERE dni = ? AND idCurso = ?";
		
		try (Connection conexion = ConectorBD.getConexion();
				 PreparedStatement ps = conexion.prepareStatement(sql)) {
				
				ps.setString(1, dni);
				ps.setInt(2, idCurso);
				
				int filasEliminadas = ps.executeUpdate();
				
				if (filasEliminadas > 0) {
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "No se encontró el seguimiento para eliminar.");
					return false;
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al eliminar el seguimiento del alumno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		return false;
	}
	
	// Método para obtener todos los seguimientos como texto
	public static String obtenerSeguimientosComoTexto() {
	    StringBuilder sb = new StringBuilder();
	    
	    try (Connection conexion = ConectorBD.getConexion();
	         Statement stmt = conexion.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM SeguimientoAlumno")) {

	        while (rs.next()) {
	            String dni = rs.getString("dni");
	            int idCurso = rs.getInt("idCurso");
	            String nivelIdioma = rs.getString("nivelIdioma");
	            int asistencia = rs.getInt("asistencia");
	            int participacion = rs.getInt("participacion");
	            int rendimiento = rs.getInt("rendimiento");
	            
	            sb.append("DNI: ").append(dni).append(", Curso ID: ").append(idCurso)
	              .append(", Nivel: ").append(nivelIdioma)
	              .append(", Asistencia: ").append(asistencia)
	              .append(", Participación: ").append(participacion)
	              .append(", Rendimiento: ").append(rendimiento).append("\n");
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los seguimientos: " + e.getMessage());
	    }
	    
	    return sb.toString();
	}
}
