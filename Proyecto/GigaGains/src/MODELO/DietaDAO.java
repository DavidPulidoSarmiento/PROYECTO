
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class DietaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    public List<Dieta> ListarDieta() {
        List<Dieta> listaDieta = new ArrayList<>();
        String sql = "SELECT * FROM dietas WHERE Estado = TRUE";  // Filtra por Estado TRUE

        try {
            con = cn.getConnection();  // Conexión a la base de datos
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Dieta dieta = new Dieta();  // Crear un objeto Circuito por cada resultado
                dieta.setId(rs.getInt("ID"));
                dieta.setTipo(rs.getString("tipo"));
                dieta.setProteinas(rs.getInt("proteinas"));
                dieta.setCarbohidratos(rs.getInt("carbohidratos"));
                dieta.setCalorias(rs.getInt("calorias"));
                // Agregar el objeto a la lista
                listaDieta.add(dieta);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaDieta;  // Retornar la lista completa de circuitos
    }
    
    public boolean RegistrarDieta(Dieta die) {
        String sql = "INSERT INTO dietas (ID, tipo, proteinas, carbohidratos, Calorias) VALUES  (?,?,?,?,?)";  
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, die.getId());
            ps.setString(2, die.getTipo());
            ps.setInt(3, die.getProteinas());
            ps.setInt(4, die.getCarbohidratos());
            ps.setInt(5, die.getCalorias());
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
    public boolean EliminarDieta(int id) {
        String sql = "UPDATE dietas SET Estado = FALSE WHERE ID = ?";  // Cambia el estado a FALSE en lugar de eliminar
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
    public boolean ModificarDieta(Dieta die) {
        String sql = " UPDATE dietas SET tipo = ?, proteinas = ?, carbohidratos = ?, calorias = ?  WHERE dietas.ID = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, die.getTipo());
            ps.setInt(2, die.getProteinas());
            ps.setInt(3, die.getCarbohidratos());
            ps.setInt(4, die.getCalorias());
            ps.setInt(5, die.getId());
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
