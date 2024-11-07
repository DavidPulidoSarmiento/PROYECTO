package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class RutinaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // Método para registrar una rutina
    public boolean RegistrarRutina(Rutina ru){
        String sql = "INSERT INTO rutinas (ID, nombre, Estado) VALUES  (?,?, TRUE)";  // Se agrega Estado por defecto en TRUE
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ru.getId());
            ps.setString(2, ru.getNombre());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e){
                System.out.print(e.toString());
            }
        }
    }
    
    // Método para listar rutinas con estado TRUE
    public List<Rutina> ListarRutina() {
        List<Rutina> ListaCl = new ArrayList<>();
        String sql = "SELECT * FROM rutinas WHERE Estado = TRUE";  // Filtra solo rutinas activas
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rutina cl = new Rutina();
                cl.setId(rs.getInt("ID")); // Cambiar a rutina_id
                cl.setNombre(rs.getString("nombre")); // Cambiar a nombre_rutina
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            // Asegúrate de cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return ListaCl;
    }

    // Método para eliminar una rutina cambiando su estado a FALSE
    public boolean EliminarRutina(int id) {
        String sql = "UPDATE rutinas SET Estado = FALSE WHERE ID = ?";  // Cambia el estado a FALSE en lugar de eliminar
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
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    // Método para modificar una rutina (solo si su estado es TRUE)
    public boolean ModificarRutina(Rutina ru) {
        String sql = "UPDATE rutinas SET nombre = ? WHERE ID = ? AND Estado = TRUE";  // Solo modifica si Estado es TRUE
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ru.getNombre());
            ps.setInt(2, ru.getId());
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

    // Método para obtener los nombres de las rutinas activas
    public List<String> obtenerNombresRutinas() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM rutinas WHERE Estado = TRUE";  // Solo nombres de rutinas activas
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
}
