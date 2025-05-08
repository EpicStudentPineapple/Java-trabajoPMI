	package repositorios;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	
	
	public class ConectorBD {
	
	    private static Connection conexion;
	
	    public static Connection getConexion() {
	        return conexion;
	    }
	
	    // Método para establecer la conexión a la base de datos
	    public static void conectar() {
	        try {
	            // Cargamos el driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            System.out.println("Driver cargado");
	            try {
	                // Establecemos la conexión con la BD
	                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/escuela_idiomas", "root", "admin");
	                System.out.println("Conexión establecida");
	            } catch (Exception e) {
	                System.out.println("Error en la conexión");
	            }
	        } catch (Exception e) {
	            System.out.println("Error en el driver");
	        }
	    }
	
	    
	
	    // Método para cerrar la conexión
	    public static void cerrarConexion() throws SQLException {
	        conexion.close();
	    }
	}
