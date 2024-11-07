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
        String sql = "INSERT INTO plan (id, tipo, rutina_id, dieta_id, Estado) VALUES  (?,?,?,?, TRUE)";  // Se agrega la columna Estado y se establece en TRUE
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pla.getId());
            ps.setString(2, pla.getTipo());
            ps.setInt(3, pla.getId_dieta());
            ps.setInt(4, pla.getId_rutina());
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
        List<Plan> ListaPla = new ArrayList<>();
        String sql = "SELECT * FROM plan WHERE Estado = TRUE";  // Solo listar los planes con estado TRUE
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Plan pla = new Plan();
                pla.setId(rs.getInt("id"));
                pla.setTipo(rs.getString("tipo"));
                pla.setId_dieta(rs.getInt("dieta_id"));
                pla.setId_rutina(rs.getInt("rutina_id"));
                ListaPla.add(pla);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaPla;
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
        String sql = "UPDATE plan SET tipo = ?, dieta_id = ?, rutina_id = ? WHERE id = ? AND Estado = TRUE";  // Solo modifica si el plan está activo (Estado = TRUE)
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pla.getTipo());
            ps.setInt(2, pla.getId_dieta());
            ps.setInt(3, pla.getId_rutina());
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
}
