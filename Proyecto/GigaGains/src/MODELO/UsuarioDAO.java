package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de acceso a datos para la entidad {@link Usuario}.
 * Proporciona métodos para interactuar con la base de datos relacionadas con los usuarios.
 */
public class UsuarioDAO {
    
    /** Conexión a la base de datos */
    Connection con;
    
    /** Objeto de conexión con la base de datos */
    Conexion cn = new Conexion();
    
    /** Sentencia preparada para ejecutar consultas SQL */
    PreparedStatement ps;
    
    /** Resultado de una consulta SQL */
    ResultSet rs;
    
    /**
     * Obtiene una lista de los usuarios activos (Estado = TRUE) desde la base de datos.
     * 
     * @return Lista de objetos {@link Usuario} que representan los usuarios activos en el sistema.
     */
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

    /**
     * Modifica los datos de un usuario en la base de datos.
     * Este método solo permite modificar los usuarios cuyo estado es {@code TRUE}.
     * 
     * @param usu El objeto {@link Usuario} que contiene los datos a modificar.
     * @return {@code true} si la modificación fue exitosa, {@code false} si ocurrió un error.
     */
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

    /**
     * "Elimina" un usuario cambiando su estado a {@code FALSE}.
     * Esto no elimina físicamente al usuario de la base de datos, sino que lo marca como inactivo.
     * 
     * @param id El identificador único del usuario a eliminar.
     * @return {@code true} si la operación fue exitosa, {@code false} si ocurrió un error.
     */
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
