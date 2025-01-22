package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con los planes de entrenamiento.
 * Permite registrar, listar, modificar y eliminar planes, así como obtener nombres de dietas.
 */
public class PlanDAO {
    
    /** Instancia de la clase Conexion para manejar la conexión a la base de datos */
    Conexion cn = new Conexion();
    
    /** Conexión a la base de datos */
    Connection con;
    
    /** Sentencia SQL preparada para la ejecución de consultas */
    PreparedStatement ps;
    
    /** Resultado de la consulta SQL */
    ResultSet rs;

    /**
     * Registra un nuevo plan en la base de datos.
     * 
     * @param pla Objeto de tipo Plan con los datos del nuevo plan.
     * @return `true` si el plan fue registrado correctamente, `false` en caso contrario.
     */
    public boolean RegistrarPlan(Plan pla) {
        String sqlDieta = "SELECT ID FROM dietas WHERE tipo = ?";
        String sqlRutina = "SELECT ID FROM rutinas WHERE nombre = ?";
        String sql = "INSERT INTO plan (id, tipo, dieta_id, rutina_id, Estado) VALUES  (?,?,?,?, TRUE)";  // Se agrega la columna Estado y se establece en TRUE
        try {
            con = cn.getConnection();     
            ps = con.prepareStatement(sqlDieta);
            ps.setString(1, pla.getNombre_dieta());
            rs = ps.executeQuery();
        
            int idDieta = 0;
            if (rs.next()) {
                idDieta = rs.getInt("ID");
            } else {
                System.out.println("Dieta no encontrada.");
                return false;
            }

            ps = con.prepareStatement(sqlRutina);
            ps.setString(1, pla.getNombre_rutina());
            rs = ps.executeQuery();
        
            int idRutina = 0;
            if (rs.next()) {
                idRutina = rs.getInt("ID");
            } else {
                System.out.println("Ejercicio no encontrado.");
                return false;
            }
            
            ps = con.prepareStatement(sql);
            ps.setInt(1, pla.getId());
            ps.setString(2, pla.getTipo());
            ps.setInt(3, idDieta);
            ps.setInt(4, idRutina);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.print(e.toString());
            }         
        }
    }
    
    
    public boolean existeId(int id) {
    String sql = "SELECT COUNT(*) FROM plan WHERE id = ?";
    try {
        con = cn.getConnection();
            // Obtener el ID del grupo muscular
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Si el conteo es mayor a 0, el ID ya existe
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Si ocurre un error o no se encuentra, devolver false
}

    /**
     * Lista los planes cuyo estado es `TRUE` (activos).
     * 
     * @return Una lista de objetos `Plan` representando los planes activos.
     */
    public List<Plan> ListarPlan() {
        List<Plan> listaPla = new ArrayList<>();
        String sql = "SELECT p.ID AS ID, p.tipo AS tipo, d.tipo AS nombre_dieta, r.nombre AS nombre_rutina FROM plan p INNER JOIN dietas d ON p.dieta_id = d.ID INNER JOIN rutinas r ON p.rutina_id = r.ID WHERE p.Estado = TRUE";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan pla = new Plan();
                pla.setId(rs.getInt("ID"));
                pla.setTipo(rs.getString("tipo"));
                pla.setNombre_dieta(rs.getString("nombre_dieta"));
                pla.setNombre_rutina(rs.getString("nombre_rutina"));
                listaPla.add(pla);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar el plan: " + e.getMessage());
        }
        return listaPla;
    }
    public List<Plan> ListarPlanFalso() {
        List<Plan> listaPla = new ArrayList<>();
        String sql = "SELECT p.ID AS ID, p.tipo AS tipo, d.tipo AS nombre_dieta, r.nombre AS nombre_rutina FROM plan p INNER JOIN dietas d ON p.dieta_id = d.ID INNER JOIN rutinas r ON p.rutina_id = r.ID WHERE p.Estado = FALSE";
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan pla = new Plan();
                pla.setId(rs.getInt("ID"));
                pla.setTipo(rs.getString("tipo"));
                pla.setNombre_dieta(rs.getString("nombre_dieta"));
                pla.setNombre_rutina(rs.getString("nombre_rutina"));
                listaPla.add(pla);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar el plan: " + e.getMessage());
        }
        return listaPla;
    }
    
    /**
     * Cambia el estado de un plan a `FALSE` (en lugar de eliminarlo físicamente de la base de datos).
     * 
     * @param id El identificador del plan que se desea eliminar (desactivar).
     * @return `true` si el estado fue actualizado correctamente, `false` en caso contrario.
     */
    public boolean EliminarPlan(int id) {
        String sql = "UPDATE plan SET Estado = FALSE WHERE id = ?";  // Cambia el estado a FALSE en lugar de eliminar
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    public boolean RestaurarPlan(int id) {
        String sql = "UPDATE plan SET Estado = TRUE WHERE id = ?";  // Cambia el estado a FALSE en lugar de eliminar
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }
    
    /**
     * Modifica un plan existente en la base de datos.
     * Solo modifica planes cuyo estado es `TRUE`.
     * 
     * @param pla Objeto de tipo Plan con los datos modificados del plan.
     * @return `true` si el plan fue modificado correctamente, `false` en caso contrario.
     */
    public boolean ModificarPlan(Plan pla) {
        String sqlDieta = "SELECT ID FROM dietas WHERE tipo = ?";
        String sqlRutina = "SELECT ID FROM rutinas WHERE nombre = ?";
        String sqlUpdate = "UPDATE plan SET tipo = ?, dieta_id = ?, rutina_id = ? WHERE ID = ? AND Estado = TRUE";  // Solo modifica si el plan está activo (Estado = TRUE)
        try {
            con = cn.getConnection();
            
            // Obtener el ID de la dieta a partir de su nombre
            ps = con.prepareStatement(sqlDieta);
            ps.setString(1, pla.getNombre_dieta());
            rs = ps.executeQuery();
        
            int idDieta = 0;
            if (rs.next()) {
                idDieta = rs.getInt("ID");
            } else {
                System.out.println("Dieta no encontrada.");
                return false;
            }
            
            // Obtener el ID de la rutina a partir de su nombre
            ps = con.prepareStatement(sqlRutina);
            ps.setString(1, pla.getNombre_rutina());
            rs = ps.executeQuery();
        
            int idRutina = 0;
            if (rs.next()) {
                idRutina = rs.getInt("ID");
            } else {
                System.out.println("Dieta no encontrada.");
                return false;
            }

            ps = con.prepareStatement(sqlUpdate);
            ps.setString(1, pla.getTipo());
            ps.setInt(2, idDieta);
            ps.setInt(3, idRutina);
            ps.setInt(4, pla.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    /**
     * Obtiene una lista de los nombres de las dietas activas en la base de datos.
     * 
     * @return Una lista de nombres de dietas activas.
     */
    public List<String> obtenerNombresDietas() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT tipo FROM dietas WHERE Estado = TRUE";  // Solo nombres de dietas activas
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString("tipo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombres;
    }
}
