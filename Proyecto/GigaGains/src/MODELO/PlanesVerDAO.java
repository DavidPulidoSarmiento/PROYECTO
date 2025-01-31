
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PlanesVerDAO {
    
    /** Conexión a la base de datos */
    Connection con;
    
    /** Instancia de la clase Conexion para obtener la conexión a la base de datos */
    Conexion cn = new Conexion();
    
    /** Objeto PreparedStatement para ejecutar consultas SQL */
    PreparedStatement ps;
    
    /** Resultado de la consulta SQL */
    ResultSet rs;
    
    public List<PlanesVer> obtenerRutinaPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT r.id, r.nombre FROM plan p JOIN rutinas r ON p.rutina_id = r.id WHERE p.tipo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombrePlan);  // Filtro por nombre de circuito
            rs = ps.executeQuery();

            while (rs.next()) {
                PlanesVer pla = new PlanesVer();
                pla.setRutina_id(rs.getInt("ID"));
                pla.setNombre_rutina(rs.getString("nombre"));
                listaRutina.add(pla);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutina;
    }
    
    public List<PlanesVer> obtenerDietaPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT d.id, d.tipo, d.proteinas, d.carbohidratos, d.calorias FROM plan p JOIN dietas d ON p.dieta_id = d.id WHERE p.tipo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombrePlan);  // Filtro por nombre de circuito
            rs = ps.executeQuery();

            while (rs.next()) {
                PlanesVer pla = new PlanesVer();
                pla.setDieta_id(rs.getInt("ID"));
                pla.setTipo_dieta(rs.getString("tipo"));
                pla.setProteinas(rs.getInt("proteinas"));
                pla.setCarbohidratos(rs.getInt("carbohidratos"));
                pla.setCalorias(rs.getInt("calorias"));
                listaRutina.add(pla);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutina;
    }
    
    aqui quede
    public List<PlanesVer> obtenerCircuitoPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT r.id, r.nombre FROM plan p JOIN rutinas r ON p.rutina_id = r.id WHERE p.tipo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombrePlan);  // Filtro por nombre de circuito
            rs = ps.executeQuery();

            while (rs.next()) {
                PlanesVer pla = new PlanesVer();
                pla.setRutina_id(rs.getInt("ID"));
                pla.setNombre_rutina(rs.getString("nombre"));
                listaRutina.add(pla);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutina;
    }
}
