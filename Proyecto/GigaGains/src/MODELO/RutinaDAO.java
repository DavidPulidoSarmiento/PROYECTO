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
    
    
    public List<Rutina> obtenerCircuitoporRutina(String nombreRutina) {
    List<Rutina> listaRutinas = new ArrayList<>();
    String sql = "SELECT rc.ID AS idcircuito, rc.circuito_id, c.nombre AS nombrecircuito FROM rutinas_circuitos rc JOIN rutinas r ON rc.rutina_id = r.id  JOIN circuitos c ON rc.circuito_id = c.id  WHERE r.nombre = ? AND rc.Estado = TRUE";

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, nombreRutina);  // Filtro por nombre de circuito
        rs = ps.executeQuery();

        while (rs.next()) {
            Rutina rutina = new Rutina();
            rutina.setIdru_cir(rs.getInt("idcircuito"));
            rutina.setNombrecircuito(rs.getString("nombrecircuito"));
            listaRutinas.add(rutina);  // Agrega a la lista de ejercicios
        }
        } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    return listaRutinas;
    }
    
    public boolean RegistrarRutina_Circuito(Rutina rutina) {
    String sqlRutina = "SELECT ID FROM rutinas WHERE nombre = ?";
    String sqlCircuito = "SELECT ID FROM circuitos WHERE nombre = ?";
    String sqlCEjercicio = "INSERT INTO rutinas_circuitos(id, rutina_id, circuito_id) VALUES (?, ?, ?)";
    
    try {
        con = cn.getConnection();
        
        
        ps = con.prepareStatement(sqlRutina);
        ps.setString(1, rutina.getNombre());
        rs = ps.executeQuery();
        
        int idRutina = 0;
        if (rs.next()) {
            idRutina = rs.getInt("ID");
        } else {
            System.out.println("Ejercicio no encontrado.");
            return false;
        }
        
        
        ps = con.prepareStatement(sqlCircuito);
        ps.setString(1, rutina.getNombrecircuito());
        rs = ps.executeQuery();
        
        int idCircuito = 0;
        if (rs.next()) {
            idCircuito = rs.getInt("ID");
        } else {
            System.out.println("Circuito no encontrado.");
            return false;
        }


        // Insertar en circuitos_ejercicios con los IDs obtenidos
        ps = con.prepareStatement(sqlCEjercicio);
        ps.setInt(1, rutina.getIdru_cir());
        ps.setInt(2, idRutina);
        ps.setInt(3, idCircuito);
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
    public boolean ModificarRutina_circuito(Rutina rutina) {
        String sqlRutina = "SELECT ID FROM rutinas WHERE nombre = ?";
        String sqlCircuito = "SELECT ID FROM circuitos WHERE nombre = ?";
        String sqlUpdate = "UPDATE rutinas_circuitos SET rutina_id = ?, circuito_id = ? WHERE ID = ? AND Estado = TRUE";  // Solo actualiza si el estado es TRUE
        try {
        con = cn.getConnection();
        
        
        ps = con.prepareStatement(sqlRutina);
        ps.setString(1, rutina.getNombre());
        rs = ps.executeQuery();
        
        int idRutina = 0;
        if (rs.next()) {
            idRutina = rs.getInt("ID");
        } else {
            System.out.println("Ejercicio no encontrado.");
            return false;
        }
        
        
        ps = con.prepareStatement(sqlCircuito);
        ps.setString(1, rutina.getNombrecircuito());
        rs = ps.executeQuery();
        
        int idCircuito = 0;
        if (rs.next()) {
            idCircuito = rs.getInt("ID");
        } else {
            System.out.println("Circuito no encontrado.");
            return false;
        }


        // Insertar en circuitos_ejercicios con los IDs obtenidos
        ps = con.prepareStatement(sqlUpdate);
        ps.setInt(1, idRutina);
        ps.setInt(2, idCircuito);
        ps.setInt(3, rutina.getIdru_cir());
        ps.executeUpdate();
        return true;
        
    } catch (SQLException e) {
            System.out.println("Error al modificar ejercicio: " + e.getMessage());
            return false;
        }
    }
    public boolean EliminarRutinasCi(int id) {
        String sql = "UPDATE rutinas_circuitos SET Estado = FALSE WHERE id = ?";  // Cambia el Estado a FALSE
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
}

