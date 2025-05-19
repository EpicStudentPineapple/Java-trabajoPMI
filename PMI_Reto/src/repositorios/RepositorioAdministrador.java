package repositorios;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
    public static boolean insertarAlumno(String dni, String nombre, String apellido, String contrasena, int edad,
			String correo) {
		Connection conn = null;
		PreparedStatement stmtPersona = null;
		PreparedStatement stmtAlumno = null;

		try {
			conn = ConectorBD.getConexion();
			conn.setAutoCommit(false);

			String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña, rol) VALUES (?, ?, ?, ?, 'alumno')";
			stmtPersona = conn.prepareStatement(sqlPersona);
			stmtPersona.setString(1, dni);
			stmtPersona.setString(2, nombre);
			stmtPersona.setString(3, apellido);
			stmtPersona.setString(4, contrasena);
			stmtPersona.executeUpdate();

			String sqlAlumno = "INSERT INTO Alumno (dni, edad, correo) VALUES (?, ?, ?)";
			stmtAlumno = conn.prepareStatement(sqlAlumno);
			stmtAlumno.setString(1, dni);
			stmtAlumno.setInt(2, edad);
			stmtAlumno.setString(3, correo);
			stmtAlumno.executeUpdate();

			conn.commit();
			return true;

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			if (e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "Ya existe un alumno con este DNI", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar alumno: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			return false;

		} finally {
			try {
				if (stmtAlumno != null)
					stmtAlumno.close();
				if (stmtPersona != null)
					stmtPersona.close();
				if (conn != null)
					conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean insertarProfesor(String dni, String nombre, String apellido, String contrasena,
			String nivelExperiencia, String especializacionIdioma) {
		Connection conn = null;
		PreparedStatement stmtPersona = null;
		PreparedStatement stmtProfesor = null;

		try {
			conn = ConectorBD.getConexion();
			conn.setAutoCommit(false);

			String sqlPersona = "INSERT INTO Persona (dni, nombre, apellido, contraseña, rol) VALUES (?, ?, ?, ?, 'profesor')";
			stmtPersona = conn.prepareStatement(sqlPersona);
			stmtPersona.setString(1, dni);
			stmtPersona.setString(2, nombre);
			stmtPersona.setString(3, apellido);
			stmtPersona.setString(4, contrasena);
			stmtPersona.executeUpdate();

			String sqlProfesor = "INSERT INTO Profesor (dni, nivelExperiencia, especializacionIdioma) VALUES (?, ?, ?)";
			stmtProfesor = conn.prepareStatement(sqlProfesor);
			stmtProfesor.setString(1, dni);
			stmtProfesor.setString(2, nivelExperiencia);
			stmtProfesor.setString(3, especializacionIdioma);
			stmtProfesor.executeUpdate();

			conn.commit();
			return true;

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}

			if (e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "Ya existe un profesor con este DNI", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al insertar profesor: " + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			return false;

		} finally {
			try {
				if (stmtProfesor != null)
					stmtProfesor.close();
				if (stmtPersona != null)
					stmtPersona.close();
				if (conn != null)
					conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean eliminarAlumno(String dni) {
		String query = "DELETE FROM Persona WHERE dni = ? AND rol = 'alumno'";
		try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
			stmt.setString(1, dni);
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean eliminarProfesor(String dni) {
		String query = "DELETE FROM Persona WHERE dni = ? AND rol = 'profesor'";
		try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
			stmt.setString(1, dni);
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean editarAlumno(Alumno alumno) {
		Connection conn = null;
		PreparedStatement stmtPersona = null;
		PreparedStatement stmtAlumno = null;

		try {
			conn = ConectorBD.getConexion();
			conn.setAutoCommit(false);

			String sqlPersona = "UPDATE Persona SET nombre = ?, apellido = ?, contraseña = ?, bloqueado = ? WHERE dni = ?";
			stmtPersona = conn.prepareStatement(sqlPersona);
			stmtPersona.setString(1, alumno.getNombre());
			stmtPersona.setString(2, alumno.getApellido());
			stmtPersona.setString(3, alumno.getContraseña());
			stmtPersona.setBoolean(4, alumno.getBloqueado());
			stmtPersona.setString(5, alumno.getDni());
			stmtPersona.executeUpdate();

			String sqlAlumno = "UPDATE Alumno SET edad = ?, correo = ? WHERE dni = ?";
			stmtAlumno = conn.prepareStatement(sqlAlumno);
			stmtAlumno.setInt(1, alumno.getEdad());
			stmtAlumno.setString(2, alumno.getCorreo());
			stmtAlumno.setString(3, alumno.getDni());
			stmtAlumno.executeUpdate();

			conn.commit();
			return true;

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Error al editar alumno: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (stmtAlumno != null)
					stmtAlumno.close();
				if (stmtPersona != null)
					stmtPersona.close();
				if (conn != null)
					conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean editarProfesor(Profesor profesor) {
		Connection conn = null;
		PreparedStatement stmtPersona = null;
		PreparedStatement stmtProfesor = null;

		try {
			conn = ConectorBD.getConexion();
			conn.setAutoCommit(false);

			String sqlPersona = "UPDATE Persona SET nombre = ?, apellido = ?, contraseña = ?, bloqueado = ? WHERE dni = ?";
			stmtPersona = conn.prepareStatement(sqlPersona);
			stmtPersona.setString(1, profesor.getNombre());
			stmtPersona.setString(2, profesor.getApellido());
			stmtPersona.setString(3, profesor.getContraseña());
			stmtPersona.setBoolean(4, profesor.isBloqueado());
			stmtPersona.setString(5, profesor.getDni());
			stmtPersona.executeUpdate();

			String sqlProfesor = "UPDATE Profesor SET nivelExperiencia = ?, especializacionIdioma = ? WHERE dni = ?";
			stmtProfesor = conn.prepareStatement(sqlProfesor);
			stmtProfesor.setString(1, profesor.getExperiencia());
			stmtProfesor.setString(2, profesor.getEspecializacionIdioma());
			stmtProfesor.setString(3, profesor.getDni());
			stmtProfesor.executeUpdate();

			conn.commit();
			return true;

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Error al editar profesor: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return false;

		} finally {
			try {
				if (stmtProfesor != null)
					stmtProfesor.close();
				if (stmtPersona != null)
					stmtPersona.close();
				if (conn != null)
					conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
