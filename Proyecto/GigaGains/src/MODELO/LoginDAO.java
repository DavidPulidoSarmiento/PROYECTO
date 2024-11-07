package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public login log(String usuario, String contraseña) {
        login l = new login();
        String sql = "SELECT * FROM usuario WHERE email = ? AND contraseña = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery(); // Asignar el resultado a rs
            if (rs.next()) {
                l.setUsuario(rs.getString("email")); // Asignar correctamente
                l.setPass(rs.getString("contraseña")); // Asignar correctamente
                l.setRol(rs.getString("rol_id")); // Asignar el rol del usuario
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return l;
    }
}
