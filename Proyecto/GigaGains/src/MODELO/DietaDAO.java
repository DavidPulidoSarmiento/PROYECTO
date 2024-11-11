
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
}
