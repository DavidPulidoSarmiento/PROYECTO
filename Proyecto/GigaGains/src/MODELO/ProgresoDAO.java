
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProgresoDAO {
    Connection con;
    
    /** Objeto de conexión con la base de datos */
    Conexion cn = new Conexion();
    
    /** Sentencia preparada para ejecutar consultas SQL */
    PreparedStatement ps;
    
    /** Resultado de una consulta SQL */
    ResultSet rs;
    public List<String> obtenerNombresUsuarios() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM usuario WHERE Estado = TRUE";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                nombres.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombres;
    }
    
    public List<progreso> obtenerProgresoUsuarios(String nombreUsuario) {
        List<progreso> listaUsuario = new ArrayList<>();
        String sql = "SELECT p.ID, p.Usuario_ID, p.Peso, p.last_completed FROM progreso p JOIN usuario u ON p.Usuario_ID = u.ID WHERE u.nombre = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);  // Filtro por nombre de circuito
            rs = ps.executeQuery();

            while (rs.next()) {
                progreso pro = new progreso();
                pro.setId(rs.getInt("ID"));
                pro.setUsuario_id(rs.getInt("Usuario_ID"));
                pro.setPeso(rs.getInt("Peso"));
                pro.setFecha(rs.getString("last_completed"));
                listaUsuario.add(pro);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuario;
    }
}
