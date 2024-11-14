package MODELO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase se encarga de gestionar la conexión con la base de datos MySQL.
 * Proporciona un método para obtener una conexión a la base de datos configurada.
 */
public class Conexion {

    /** Objeto de tipo Connection para manejar la conexión a la base de datos */
    Connection con;

    /**
     * Establece y devuelve una conexión a la base de datos MySQL.
     * 
     * @return La conexión a la base de datos, o null si ocurre un error al intentar establecer la conexión.
     */
    public Connection getConnection() {
        try {
            // URL de la base de datos, incluyendo el nombre de la base de datos y la zona horaria.
            String myBD = "jdbc:mysql://localhost:3306/gigagains?serverTimezone=UTC";
            
            // Establece la conexión utilizando el nombre de usuario y la contraseña.
            con = DriverManager.getConnection(myBD, "root", "#Aprendiz2024");
            
            // Retorna la conexión establecida.
            return con;
        } catch (SQLException e) {
            // En caso de error, imprime el error de la excepción.
            System.out.print(e.toString());
        }
        
        // Retorna null si ocurre un error al establecer la conexión.
        return null;
    }
}
