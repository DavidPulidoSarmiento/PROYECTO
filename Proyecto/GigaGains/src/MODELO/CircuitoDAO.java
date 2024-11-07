package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CircuitoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    // Método para registrar un circuito completo con 6 ejercicios
    public boolean RegistrarCircuito(Circuito circuito) {
        String sql = "INSERT INTO circuitos (id, nombre, Estado) VALUES (?, ?, TRUE)";  // Estado se establece a TRUE por defecto
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, circuito.getId());
            ps.setString(2, circuito.getNombre());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    // Método para listar todos los circuitos cuyo Estado sea TRUE
    public List<Circuito> ListarCircuitos() {
        List<Circuito> listaCircuitos = new ArrayList<>();
        String sql = "SELECT * FROM circuitos WHERE Estado = TRUE";  // Filtra por Estado TRUE

        try {
            con = cn.getConnection();  // Conexión a la base de datos
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Circuito circuito = new Circuito();  // Crear un objeto Circuito por cada resultado
                circuito.setId(rs.getInt("ID"));
                circuito.setNombre(rs.getString("nombre"));
                // Agregar el objeto a la lista
                listaCircuitos.add(circuito);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaCircuitos;  // Retornar la lista completa de circuitos
    }

    // Método para "eliminar" un circuito (actualiza su Estado a FALSE)
    public boolean EliminarCircuito(int id) {
        String sql = "UPDATE circuitos SET Estado = FALSE WHERE id = ?";  // Cambia el Estado a FALSE
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
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

    // Método para modificar un circuito
    public boolean ModificarCircuito(Circuito circuito) {
        String sqlUpdate = "UPDATE circuitos SET nombre = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sqlUpdate);
            ps.setString(1, circuito.getNombre());
            ps.setInt(2, circuito.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    // Método para obtener los nombres de los ejercicios
    public List<String> obtenerNombresEjercicios() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM ejercicios WHERE Estado = TRUE";
        
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

    // Método para obtener los nombres de los circuitos
    public List<String> obtenerNombresCircuitos() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM circuitos WHERE Estado = TRUE";
        
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
