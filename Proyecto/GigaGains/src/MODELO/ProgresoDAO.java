
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgresoDAO {
    // Objeto de conexión con la base de datos
    Connection con;
    
    /** Objeto de conexión para obtener la conexión a la base de datos */
    Conexion cn = new Conexion();
    
    /** Sentencia preparada para ejecutar consultas SQL */
    PreparedStatement ps;
    
    /** Resultado de una consulta SQL */
    ResultSet rs;

    /**
     * Obtiene una lista de nombres de usuarios activos en la base de datos.
     * 
     * @return List<String> Lista de nombres de usuarios activos.
     */
    public List<String> obtenerNombresUsuarios() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM usuario WHERE Estado = TRUE";  // Consulta SQL para obtener los nombres de usuarios activos

        try {
            con = cn.getConnection();  // Establece la conexión con la base de datos
            ps = con.prepareStatement(sql);  // Prepara la consulta SQL
            rs = ps.executeQuery();  // Ejecuta la consulta

            // Itera sobre los resultados de la consulta y agrega los nombres a la lista
            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error en caso de que ocurra una excepción
        }

        return nombres;  // Retorna la lista de nombres de usuarios
    }

    /**
     * Obtiene el progreso de un usuario específico basado en su nombre.
     * 
     * @param nombreUsuario Nombre del usuario para obtener su progreso.
     * @return List<progreso> Lista de objetos de progreso asociados al usuario.
     */
    public List<progreso> obtenerProgresoUsuarios(String nombreUsuario) {
        List<progreso> listaUsuario = new ArrayList<>();
        String sql = "SELECT p.ID, p.Usuario_ID, p.Peso, p.last_completed FROM progreso p JOIN usuario u ON p.Usuario_ID = u.ID WHERE u.nombre = ?";  // Consulta SQL para obtener el progreso del usuario

        try {
            con = cn.getConnection();  // Establece la conexión con la base de datos
            ps = con.prepareStatement(sql);  // Prepara la consulta SQL
            ps.setString(1, nombreUsuario);  // Asocia el nombre del usuario al parámetro de la consulta
            rs = ps.executeQuery();  // Ejecuta la consulta

            // Itera sobre los resultados de la consulta y agrega el progreso a la lista
            while (rs.next()) {
                progreso pro = new progreso();  // Crea un nuevo objeto de tipo progreso
                pro.setId(rs.getInt("ID"));  // Establece el ID del progreso
                pro.setUsuario_id(rs.getInt("Usuario_ID"));  // Establece el ID del usuario
                pro.setPeso(rs.getInt("Peso"));  // Establece el peso del usuario
                pro.setFecha(rs.getString("last_completed"));  // Establece la fecha del último progreso
                listaUsuario.add(pro);  // Agrega el objeto progreso a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error en caso de que ocurra una excepción
        }

        return listaUsuario;  // Retorna la lista de objetos progreso
    }
}
