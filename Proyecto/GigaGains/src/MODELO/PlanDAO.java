package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PlanDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Método para registrar un plan
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
    
    // Método para listar los planes cuyo estado sea TRUE
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
    
    // Método para cambiar el estado del plan a FALSE (en lugar de eliminarlo)
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
    
    // Método para modificar un plan
    public boolean ModificarPlan(Plan pla) {
        String sqlDieta = "SELECT ID FROM dietas WHERE tipo = ?";
        String sqlRutina = "SELECT ID FROM rutinas WHERE nombre = ?";
        String sqlUpdate = "UPDATE plan SET tipo = ?, dieta_id = ?, rutina_id = ? WHERE ID = ? AND Estado = TRUE";  // Solo modifica si el plan está activo (Estado = TRUE)
        try {
            con = cn.getConnection();
            
            // Obtener el ID del circuito a partir de su nombre
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
            
            // Obtener el ID del circuito a partir de su nombre
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
    
    public List<String> obtenerNombresDietas() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT tipo FROM dietas WHERE Estado = TRUE";  // Solo nombres de rutinas activas
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
