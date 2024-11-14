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
    public List<Circuito> ListarCircuitosFalsos() {
        List<Circuito> listaCircuitos = new ArrayList<>();
        String sql = "SELECT * FROM circuitos WHERE Estado = FALSE";  // Filtra por Estado TRUE

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
    public boolean RestaurarCircuito(int id) {
        String sql = "UPDATE circuitos SET Estado = TRUE WHERE id = ?";  // Cambia el Estado a FALSE
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
    
    public boolean EliminarCircuitoEj(int id) {
        String sql = "UPDATE circuitos_ejercicios SET Estado = FALSE WHERE id = ?";  // Cambia el Estado a FALSE
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

    /**
     *
     * @param nombreCircuito
     * @return
     */
    public List<Circuito> obtenerEjerciciosPorCircuito(String nombreCircuito) {
    List<Circuito> listaEjercicios = new ArrayList<>();
    String sql = "SELECT ce.ID AS IDCircuitoEj, ce.ejercicio_id, e.nombre AS nombre_ejercicio, ce.series " +
                 "FROM circuitos_ejercicios ce " +
                 "JOIN circuitos c ON ce.circuito_id = c.id " +
                 "JOIN ejercicios e ON ce.ejercicio_id = e.id " +
                 "WHERE c.nombre = ? AND ce.Estado = TRUE";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, nombreCircuito);  // Filtro por nombre de circuito
        rs = ps.executeQuery();

        while (rs.next()) {
            Circuito circuito = new Circuito();
            circuito.setIDCircuitoEj(rs.getInt("IDCircuitoEj"));
            circuito.setEjercicio_id(rs.getInt("ejercicio_id"));
            circuito.setNombre_ejercicio(rs.getString("nombre_ejercicio"));
            circuito.setSeries(rs.getString("series"));
            listaEjercicios.add(circuito);  // Agrega a la lista de ejercicios
        }
        } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    return listaEjercicios;
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
    
    public boolean RegistrarCircuito_ejercicio(Circuito circuito) {
    String sqlCircuito = "SELECT ID FROM circuitos WHERE nombre = ?";
    String sqlEjercicio = "SELECT ID FROM ejercicios WHERE nombre = ?";
    String sqlCEjercicio = "INSERT INTO circuitos_ejercicios(id, circuito_id, ejercicio_id, series) VALUES (?, ?, ?, ?)";
    
    try {
        con = cn.getConnection();
        
        // Obtener el ID del circuito a partir de su nombre
        
        ps = con.prepareStatement(sqlCircuito);
        ps.setString(1, circuito.getNombre());
        rs = ps.executeQuery();
        
        int idCircuito = 0;
        if (rs.next()) {
            idCircuito = rs.getInt("ID");
        } else {
            System.out.println("Circuito no encontrado.");
            return false;
        }

        // Obtener el ID del ejercicio a partir de su nombre
        ps = con.prepareStatement(sqlEjercicio);
        ps.setString(1, circuito.getNombre_ejercicio());
        rs = ps.executeQuery();
        
        int idEjercicio = 0;
        if (rs.next()) {
            idEjercicio = rs.getInt("ID");
        } else {
            System.out.println("Ejercicio no encontrado.");
            return false;
        }

        // Insertar en circuitos_ejercicios con los IDs obtenidos
        ps = con.prepareStatement(sqlCEjercicio);
        ps.setInt(1, circuito.getIDCircuitoEj());
        ps.setInt(2, idCircuito);
        ps.setInt(3, idEjercicio);
        ps.setString(4, circuito.getSeries());
        ps.executeUpdate();
        return true;
        
    } catch (SQLException e) {
        System.out.println("Error al registrar ejercicio en el circuito: " + e.getMessage());
        return false;
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
    public boolean ModificarCircuito_Ejercicio(Circuito circuito) {
        String sqlCircuito = "SELECT ID FROM circuitos WHERE nombre = ?";
        String sqlEjercicio = "SELECT ID FROM ejercicios WHERE nombre = ?";
        String sqlUpdate = "UPDATE circuitos_ejercicios SET circuito_id = ?, ejercicio_id = ?, series = ? WHERE ID = ? AND Estado = TRUE";  // Solo actualiza si el estado es TRUE
        try {
            con = cn.getConnection();
        
        // Obtener el ID del circuito a partir de su nombre
        ps = con.prepareStatement(sqlCircuito);
        ps.setString(1, circuito.getNombre());
        rs = ps.executeQuery();
        
        int idCircuito = 0;
        if (rs.next()) {
            idCircuito = rs.getInt("ID");
        } else {
            System.out.println("Circuito no encontrado.");
            return false;
        }

        // Obtener el ID del ejercicio a partir de su nombre
        ps = con.prepareStatement(sqlEjercicio);
        ps.setString(1, circuito.getNombre_ejercicio());
        rs = ps.executeQuery();
        
        int idEjercicio = 0;
        if (rs.next()) {
            idEjercicio = rs.getInt("ID");
        } else {
            System.out.println("Ejercicio no encontrado.");
            return false;
        }

        // Insertar en circuitos_ejercicios con los IDs obtenidos
        ps = con.prepareStatement(sqlUpdate);
     
        ps.setInt(1, idCircuito);
        ps.setInt(2, idEjercicio);
        ps.setString(3, circuito.getSeries());
        ps.setInt(4, circuito.getIDCircuitoEj());
        ps.executeUpdate();
        return true;
            
        } catch (SQLException e) {
            System.out.println("Error al modificar ejercicio: " + e.getMessage());
            return false;
        }
    }
}


    