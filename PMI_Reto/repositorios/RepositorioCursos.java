package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Curso;

public class RepositorioCursos {

    public static ArrayList<Curso> obtenerCursos() {
        ArrayList<Curso> cursos = new ArrayList<>();
        String query = "SELECT idCurso, idioma, dificultad FROM curso";

        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idCurso = rs.getInt("idCurso");
                String idioma = rs.getString("idioma");
                String dificultad = rs.getString("dificultad");
                cursos.add(new Curso(idCurso, idioma, null, null, dificultad, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public static ArrayList<Curso> buscarCursosPorIdioma(String idioma) {
        ArrayList<Curso> cursos = new ArrayList<>();
        String query = "SELECT idCurso, idioma, dificultad FROM curso WHERE idioma LIKE ?";

        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setString(1, "%" + idioma + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idCurso = rs.getInt("idCurso");
                String idiomaCurso = rs.getString("idioma");
                String dificultad = rs.getString("dificultad");

                cursos.add(new Curso(idCurso, idiomaCurso, null, null, dificultad, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public static Curso obtenerCursoPorId(int id) {
        String query = "SELECT * FROM curso WHERE idCurso = ?";
        try (PreparedStatement stmt = ConectorBD.getConexion().prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(
                    rs.getInt("idCurso"),
                    rs.getString("idioma"),
                    rs.getString("dia"),
                    rs.getString("horario"),
                    rs.getString("dificultad"),
                    rs.getString("programa")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean inscribirAlumnoEnCurso(String dniAlumno, int idCurso) {
        String comprobarQuery = "SELECT COUNT(*) FROM SeguimientoAlumno WHERE dni = ? AND idCurso = ?";
        String insertarQuery = "INSERT INTO SeguimientoAlumno (dni, idCurso, asistencia, participacion, rendimiento) VALUES (?, ?, 1, 1, 1)";

        try (PreparedStatement comprobarStmt = ConectorBD.getConexion().prepareStatement(comprobarQuery)) {
            comprobarStmt.setString(1, dniAlumno);
            comprobarStmt.setInt(2, idCurso);

            ResultSet rs = comprobarStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Ya estás inscrito en este curso.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al comprobar la inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (PreparedStatement insertarStmt = ConectorBD.getConexion().prepareStatement(insertarQuery)) {
            insertarStmt.setString(1, dniAlumno);
            insertarStmt.setInt(2, idCurso);
            return insertarStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al inscribir al alumno.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
  