
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
    
    public List<PlanesVer> obtenerCircuitoPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT c.id, c.nombre FROM plan p JOIN rutinas r ON p.rutina_id = r.ID JOIN rutinas_circuitos rc ON rc.rutina_id = r.ID JOIN circuitos c ON rc.circuito_id = c.ID WHERE p.tipo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombrePlan);  // Filtro por nombre de circuito
            rs = ps.executeQuery();

            while (rs.next()) {
                PlanesVer pla = new PlanesVer();
                pla.setCircuito_id(rs.getInt("ID"));
                pla.setNombre_circuito(rs.getString("nombre"));
                listaRutina.add(pla);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutina;
    }
    
    public List<PlanesVer> obtenerEjercicioPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT e.ID AS ejercicio_id,  e.nombre AS ejercicio_nombre, ce.series AS serie, c.nombre AS circuito_nombre FROM plan p JOIN rutinas r ON p.rutina_id = r.ID JOIN rutinas_circuitos rc ON rc.rutina_id = r.ID JOIN circuitos c ON rc.circuito_id = c.ID JOIN circuitos_ejercicios ce ON ce.circuito_id = c.ID JOIN ejercicios e ON ce.ejercicio_id = e.ID WHERE p.tipo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombrePlan);  // Filtro por nombre de circuito
            rs = ps.executeQuery();

            while (rs.next()) {
                PlanesVer pla = new PlanesVer();
                pla.setEjercicio_id(rs.getInt("ejercicio_id"));
                pla.setNombre_id(rs.getString("ejercicio_nombre"));
                pla.setSeries(rs.getString("serie"));
                pla.setNombre_circuito(rs.getString("circuito_nombre"));
                listaRutina.add(pla);  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutina;
    }
}
