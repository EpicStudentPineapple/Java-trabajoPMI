package repositorios;

import java.sql.*;
import java.util.ArrayList;
import modelo.Alumno;
import modelo.Profesor;

public class RepositorioAdministrador {

	
	// Metodo para buscar los alumnos por nombre
    public static ArrayList<Alumno> buscarAlumnosPorNombre(String nombre) {
        ArrayList<Alumno> lista = new ArrayList<>();
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.contraseña, p.rol, p.bloqueado, a.correo "
                + "FROM Persona p JOIN Alumno a ON p.dni = a.dni WHERE p.nombre LIKE ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Alumno a = new Alumno(rs.getString("dni"), rs.getString("contraseña"), rs.getString("nombre"),
                            rs.getString("apellido"), rs.getString("rol"), 0, rs.getString("correo"));
                    a.setBloqueado(rs.getBoolean("bloqueado"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Metodo para bloquear a un alumno
    public static boolean bloquearAlumno(String dni) {
        String sql = "UPDATE Persona SET bloqueado = true WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, dni);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para buscar a un profesor por el nombre
    public static ArrayList<Profesor> buscarProfesoresPorNombre(String nombre) {
        ArrayList<Profesor> lista = new ArrayList<>();
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.contraseña, p.rol, p.bloqueado, pr.nivelExperiencia, pr.especializacionIdioma "
                + "FROM Persona p JOIN Profesor pr ON p.dni = pr.dni WHERE p.nombre LIKE ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Profesor profe = new Profesor(rs.getString("dni"), rs.getString("contraseña"), rs.getString("nombre"),
                            rs.getString("apellido"), rs.getString("rol"), rs.getString("especializacionIdioma"),
                            rs.getString("nivelExperiencia"));
                    profe.setBloqueado(rs.getBoolean("bloqueado"));
                    lista.add(profe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Metodo para bloquear a un profesor
    public static boolean bloquearProfesor(String dni) {
        String sql = "UPDATE Persona SET bloqueado = true WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, dni);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para buscar a los alumnos bloqueados por el nombre
    public static ArrayList<Alumno> buscarAlumnosBloqueadosPorNombre(String nombre) {
        ArrayList<Alumno> lista = new ArrayList<>();
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.contraseña, p.rol, p.bloqueado, a.correo "
                   + "FROM Persona p JOIN Alumno a ON p.dni = a.dni "
                   + "WHERE p.nombre LIKE ? AND p.bloqueado = true";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Alumno a = new Alumno(rs.getString("dni"), rs.getString("contraseña"), rs.getString("nombre"),
                            rs.getString("apellido"), rs.getString("rol"), 0, rs.getString("correo"));
                    a.setBloqueado(rs.getBoolean("bloqueado"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Metodo para desbloquear a un alumno
    public static boolean desbloquearAlumno(String dni) {
        String sql = "UPDATE Persona SET bloqueado = false WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, dni);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo para buscar a profesores bloqueados por nombre
    public static ArrayList<Profesor> buscarProfesoresBloqueadosPorNombre(String nombre) {
        ArrayList<Profesor> lista = new ArrayList<>();
        String sql = "SELECT p.dni, p.nombre, p.apellido, p.contraseña, p.rol, p.bloqueado, pr.nivelExperiencia, pr.especializacionIdioma "
                   + "FROM Persona p JOIN Profesor pr ON p.dni = pr.dni WHERE p.nombre LIKE ? AND p.bloqueado = true";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Profesor profe = new Profesor(rs.getString("dni"), rs.getString("contraseña"), rs.getString("nombre"),
                            rs.getString("apellido"), rs.getString("rol"), rs.getString("especializacionIdioma"),
                            rs.getString("nivelExperiencia"));
                    profe.setBloqueado(rs.getBoolean("bloqueado"));
                    lista.add(profe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Metodo para desbloquear a profesores
    public static boolean desbloquearProfesor(String dni) {
        String sql = "UPDATE Persona SET bloqueado = false WHERE dni = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(sql)) {
            stmt.setString(1, dni);
            int filas = stmt.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
