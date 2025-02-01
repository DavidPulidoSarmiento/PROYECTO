
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
    
    /**
     * Obtiene las rutinas asociadas a un plan de ejercicio.
     * 
     * @param nombrePlan Nombre del plan de ejercicio.
     * @return List<PlanesVer> Lista de rutinas asociadas al plan.
     */
    public List<PlanesVer> obtenerRutinaPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT r.id, r.nombre FROM plan p JOIN rutinas r ON p.rutina_id = r.id WHERE p.tipo = ?";

        try {
            con = cn.getConnection();  // Establece la conexión con la base de datos
            ps = con.prepareStatement(sql);  // Prepara la consulta SQL
            ps.setString(1, nombrePlan);  // Asocia el nombre del plan al parámetro de la consulta
            rs = ps.executeQuery();  // Ejecuta la consulta

            // Itera sobre los resultados y agrega las rutinas a la lista
            while (rs.next()) {
                PlanesVer pla = new PlanesVer();  // Crea un objeto PlanesVer para almacenar los resultados
                pla.setRutina_id(rs.getInt("ID"));  // Establece el ID de la rutina
                pla.setNombre_rutina(rs.getString("nombre"));  // Establece el nombre de la rutina
                listaRutina.add(pla);  // Agrega el objeto PlanesVer a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error en caso de que ocurra una excepción
        }
        return listaRutina;  // Retorna la lista de rutinas asociadas al plan
    }
    
    /**
     * Obtiene las dietas asociadas a un plan de ejercicio.
     * 
     * @param nombrePlan Nombre del plan de ejercicio.
     * @return List<PlanesVer> Lista de dietas asociadas al plan.
     */
    public List<PlanesVer> obtenerDietaPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT d.id, d.tipo, d.proteinas, d.carbohidratos, d.calorias FROM plan p JOIN dietas d ON p.dieta_id = d.id WHERE p.tipo = ?";

        try {
            con = cn.getConnection();  // Establece la conexión con la base de datos
            ps = con.prepareStatement(sql);  // Prepara la consulta SQL
            ps.setString(1, nombrePlan);  // Asocia el nombre del plan al parámetro de la consulta
            rs = ps.executeQuery();  // Ejecuta la consulta

            // Itera sobre los resultados y agrega las dietas a la lista
            while (rs.next()) {
                PlanesVer pla = new PlanesVer();  // Crea un objeto PlanesVer para almacenar los resultados
                pla.setDieta_id(rs.getInt("ID"));  // Establece el ID de la dieta
                pla.setTipo_dieta(rs.getString("tipo"));  // Establece el tipo de dieta
                pla.setProteinas(rs.getInt("proteinas"));  // Establece el valor de proteínas
                pla.setCarbohidratos(rs.getInt("carbohidratos"));  // Establece el valor de carbohidratos
                pla.setCalorias(rs.getInt("calorias"));  // Establece el valor de calorías
                listaRutina.add(pla);  // Agrega el objeto PlanesVer a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error en caso de que ocurra una excepción
        }
        return listaRutina;  // Retorna la lista de dietas asociadas al plan
    }
    
    /**
     * Obtiene los circuitos asociados a un plan de ejercicio.
     * 
     * @param nombrePlan Nombre del plan de ejercicio.
     * @return List<PlanesVer> Lista de circuitos asociados al plan.
     */
    public List<PlanesVer> obtenerCircuitoPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT c.id, c.nombre FROM plan p JOIN rutinas r ON p.rutina_id = r.ID JOIN rutinas_circuitos rc ON rc.rutina_id = r.ID JOIN circuitos c ON rc.circuito_id = c.ID WHERE p.tipo = ?";

        try {
            con = cn.getConnection();  // Establece la conexión con la base de datos
            ps = con.prepareStatement(sql);  // Prepara la consulta SQL
            ps.setString(1, nombrePlan);  // Asocia el nombre del plan al parámetro de la consulta
            rs = ps.executeQuery();  // Ejecuta la consulta

            // Itera sobre los resultados y agrega los circuitos a la lista
            while (rs.next()) {
                PlanesVer pla = new PlanesVer();  // Crea un objeto PlanesVer para almacenar los resultados
                pla.setCircuito_id(rs.getInt("ID"));  // Establece el ID del circuito
                pla.setNombre_circuito(rs.getString("nombre"));  // Establece el nombre del circuito
                listaRutina.add(pla);  // Agrega el objeto PlanesVer a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error en caso de que ocurra una excepción
        }
        return listaRutina;  // Retorna la lista de circuitos asociados al plan
    }
    
    /**
     * Obtiene los ejercicios asociados a un plan de ejercicio.
     * 
     * @param nombrePlan Nombre del plan de ejercicio.
     * @return List<PlanesVer> Lista de ejercicios asociados al plan.
     */
    public List<PlanesVer> obtenerEjercicioPorPlan(String nombrePlan) {
        List<PlanesVer> listaRutina = new ArrayList<>();
        String sql = "SELECT e.ID AS ejercicio_id,  e.nombre AS ejercicio_nombre, ce.series AS serie, c.nombre AS circuito_nombre FROM plan p JOIN rutinas r ON p.rutina_id = r.ID JOIN rutinas_circuitos rc ON rc.rutina_id = r.ID JOIN circuitos c ON rc.circuito_id = c.ID JOIN circuitos_ejercicios ce ON ce.circuito_id = c.ID JOIN ejercicios e ON ce.ejercicio_id = e.ID WHERE p.tipo = ?";

        try {
            con = cn.getConnection();  // Establece la conexión con la base de datos
            ps = con.prepareStatement(sql);  // Prepara la consulta SQL
            ps.setString(1, nombrePlan);  // Asocia el nombre del plan al parámetro de la consulta
            rs = ps.executeQuery();  // Ejecuta la consulta

            // Itera sobre los resultados y agrega los ejercicios a la lista
            while (rs.next()) {
                PlanesVer pla = new PlanesVer();  // Crea un objeto PlanesVer para almacenar los resultados
                pla.setEjercicio_id(rs.getInt("ejercicio_id"));  // Establece el ID del ejercicio
                pla.setNombre_id(rs.getString("ejercicio_nombre"));  // Establece el nombre del ejercicio
                pla.setSeries(rs.getString("serie"));  // Establece el número de series del ejercicio
                pla.setNombre_circuito(rs.getString("circuito_nombre"));  // Establece el nombre del circuito
                listaRutina.add(pla);  // Agrega el objeto PlanesVer a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprime el error en caso de que ocurra una excepción
        }
        return listaRutina;  // Retorna la lista de ejercicios asociados al plan
    }
}
