package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que maneja la interacción con la base de datos para las operaciones 
 * relacionadas con el login de usuarios. Permite verificar la autenticación 
 * de un usuario mediante su correo electrónico y contraseña.
 */
public class LoginDAO {

    /** Conexión a la base de datos */
    Connection con;

    /** Declaración de la sentencia SQL preparada */
    PreparedStatement ps;

    /** Resultado de la consulta SQL */
    ResultSet rs;

    /** Objeto para gestionar la conexión con la base de datos */
    Conexion cn = new Conexion();

    /**
     * Verifica las credenciales de un usuario (correo electrónico y contraseña) 
     * en la base de datos y devuelve un objeto {@link login} con los datos 
     * del usuario si las credenciales son correctas.
     * 
     * @param usuario El correo electrónico del usuario.
     * @param contraseña La contraseña del usuario.
     * @return Un objeto {@link login} con los detalles del usuario autenticado 
     *         si las credenciales son correctas, o un objeto vacío si no se 
     *         encuentra una coincidencia.
     */
    public login log(String usuario, String contraseña) {
        login l = new login();
        String sql = "SELECT * FROM usuario WHERE email = ? AND contraseña = ?"; // Consulta para verificar las credenciales

        try {
            con = cn.getConnection();  // Establecer la conexión a la base de datos
            ps = con.prepareStatement(sql);  // Preparar la consulta SQL
            ps.setString(1, usuario);  // Establecer el valor para el correo electrónico
            ps.setString(2, contraseña);  // Establecer el valor para la contraseña
            rs = ps.executeQuery();  // Ejecutar la consulta y obtener el resultado

            // Si se encuentra un usuario que coincida con las credenciales
            if (rs.next()) {
                l.setUsuario(rs.getString("email"));  // Asignar el correo electrónico al objeto login
                l.setPass(rs.getString("contraseña"));  // Asignar la contraseña al objeto login
                l.setRol(rs.getString("rol_id"));  // Asignar el rol del usuario al objeto login
            }
        } catch (SQLException e) {
            System.out.println(e.toString());  // Manejo de excepciones si ocurre un error en la consulta
        } finally {
            try {
                // Cerrar los recursos de la base de datos
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();  // Manejo de excepciones durante el cierre de recursos
            }
        }

        return l;  // Devolver el objeto login con los detalles del usuario o vacío si no se encuentra
    }
}
