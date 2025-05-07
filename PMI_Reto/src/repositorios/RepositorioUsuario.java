package repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioUsuario {

    // Método para verificar si el usuario y la contraseña son correctos
    public static boolean verificarUsuario(String usuario, String password) {
        String queryCheck = "SELECT * FROM persona WHERE correo = ? AND contraseña = ?"; // Asegúrate de que el nombre de la tabla y las columnas coincidan

        try (PreparedStatement checkStmt = ConectorBD.getConexion().prepareStatement(queryCheck)) {
            checkStmt.setString(1, usuario);
            checkStmt.setString(2, password);

            ResultSet resultSet = checkStmt.executeQuery();
            if (resultSet.next()) {
                return true; // Si existe el usuario y la contraseña, retorna true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no existe el usuario o la contraseña no es correcta
    }

   
}
