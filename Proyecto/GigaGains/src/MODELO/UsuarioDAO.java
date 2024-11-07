package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    // Método para listar usuarios activos (Estado = TRUE)
    public List<Usuario> ListarUsuario() {
        List<Usuario> ListaUs = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE Estado = TRUE";  // Filtramos solo usuarios activos
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("id"));
                us.setNombre(rs.getString("nombre"));
                us.setEmail(rs.getString("email"));
                us.setFecha_de_nacimiento(rs.getString("fecha_de_nacimiento"));
                us.setFecha_de_registro(rs.getString("fecha_de_registro"));
                us.setGenero(rs.getString("genero"));
                us.setContraseña(rs.getString("contraseña"));
                us.setEstatura(rs.getFloat("estatura"));
                us.setPeso(rs.getFloat("peso"));
                us.setCondicion_especial(rs.getString("condicion_especial"));
                us.setId_plan(rs.getInt("id_plan"));
                ListaUs.add(us);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaUs;
    }

    // Método para modificar los datos de un usuario
    public boolean ModificarUsuario(Usuario usu) {
        String sql = "UPDATE usuario SET nombre=?, email=?, fecha_de_nacimiento=?, fecha_de_registro=?, genero=?, contraseña=?, estatura=?, peso=?, condicion_especial=?, id_plan=? WHERE id=? AND Estado = TRUE";  // Solo puede modificar usuarios activos
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getEmail());
            ps.setString(3, usu.getFecha_de_nacimiento());
            ps.setString(4, usu.getFecha_de_registro());
            ps.setString(5, usu.getGenero());
            ps.setString(6, usu.getContraseña());
            ps.setDouble(7, usu.getEstatura());
            ps.setDouble(8, usu.getPeso());
            ps.setString(9, usu.getCondicion_especial());
            ps.setInt(10, usu.getId_plan());
            ps.setInt(11, usu.getId());
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

    // Método para "eliminar" un usuario cambiando su estado a FALSE (inactivo)
    public boolean EliminarUsuario(int id) {
        String sql = "UPDATE usuario SET Estado = FALSE WHERE id = ?";  // Cambiar estado a FALSE en lugar de eliminar físicamente
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
}
