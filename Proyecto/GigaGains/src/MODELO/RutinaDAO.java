package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja las operaciones de acceso a datos relacionadas con las rutinas de ejercicios.
 * Permite registrar, listar, modificar y eliminar rutinas, así como asociarlas a circuitos.
 */
public class RutinaDAO {

    /** Objeto de conexión a la base de datos */
    Conexion cn = new Conexion();
    
    /** Conexión con la base de datos */
    Connection con;
    
    /** Sentencia SQL preparada */
    PreparedStatement ps;
    
    /** Resultado de la consulta */
    ResultSet rs;

    /**
     * Registra una nueva rutina en la base de datos.
     * 
     * @param ru Objeto de tipo Rutina que contiene los datos de la rutina a registrar.
     * @return true si la rutina se registró correctamente, false si hubo un error.
     */
    public boolean RegistrarRutina(Rutina ru) {
        String sql = "INSERT INTO rutinas (ID, nombre, Estado) VALUES  (?,?, TRUE)";
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
            } catch (SQLException e) {
                System.out.print(e.toString());
            }
        }
    }

    /**
     * Lista todas las rutinas activas (Estado = TRUE).
     * 
     * @return Una lista de objetos Rutina que representan las rutinas activas en la base de datos.
     */
    public List<Rutina> ListarRutina() {
        List<Rutina> ListaCl = new ArrayList<>();
        String sql = "SELECT * FROM rutinas WHERE Estado = TRUE";  // Filtra solo rutinas activas
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rutina cl = new Rutina();
                cl.setId(rs.getInt("ID"));
                cl.setNombre(rs.getString("nombre"));
                ListaCl.add(cl);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
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

    /**
     * Elimina una rutina cambiando su estado a FALSE (no la elimina físicamente).
     * 
     * @param id Identificador de la rutina a eliminar.
     * @return true si la rutina se eliminó correctamente, false si hubo un error.
     */
    public boolean EliminarRutina(int id) {
        String sql = "UPDATE rutinas SET Estado = FALSE WHERE ID = ?";
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

    /**
     * Modifica los datos de una rutina en la base de datos (solo si el estado es TRUE).
     * 
     * @param ru Objeto de tipo Rutina con los datos a actualizar.
     * @return true si la rutina se modificó correctamente, false si hubo un error.
     */
    public boolean ModificarRutina(Rutina ru) {
        String sql = "UPDATE rutinas SET nombre = ? WHERE ID = ? AND Estado = TRUE";
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

    /**
     * Obtiene los nombres de todas las rutinas activas (Estado = TRUE).
     * 
     * @return Una lista de nombres de rutinas activas.
     */
    public List<String> obtenerNombresRutinas() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM rutinas WHERE Estado = TRUE";
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
     * Obtiene los circuitos asociados a una rutina específica.
     * 
     * @param nombreRutina El nombre de la rutina para la que se desean obtener los circuitos.
     * @return Una lista de objetos Rutina con los circuitos asociados a la rutina especificada.
     */
    public List<Rutina> obtenerCircuitoporRutina(String nombreRutina) {
        List<Rutina> listaRutinas = new ArrayList<>();
        String sql = "SELECT rc.ID AS idcircuito, rc.circuito_id, c.nombre AS nombrecircuito FROM rutinas_circuitos rc JOIN rutinas r ON rc.rutina_id = r.id JOIN circuitos c ON rc.circuito_id = c.id WHERE r.nombre = ? AND rc.Estado = TRUE";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreRutina);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rutina rutina = new Rutina();
                rutina.setIdru_cir(rs.getInt("idcircuito"));
                rutina.setNombrecircuito(rs.getString("nombrecircuito"));
                listaRutinas.add(rutina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutinas;
    }

    /**
     * Asocia una rutina a un circuito en la tabla `rutinas_circuitos`.
     * 
     * @param rutina Objeto de tipo Rutina que contiene los datos de la rutina y del circuito.
     * @return true si la asociación se realiza correctamente, false si hubo un error.
     */
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

    /**
     * Modifica la asociación entre una rutina y un circuito.
     * 
     * @param rutina Objeto de tipo Rutina que contiene los nuevos datos de la rutina y el circuito.
     * @return true si la modificación se realiza correctamente, false si hubo un error.
     */
    public boolean ModificarRutina_circuito(Rutina rutina) {
        String sqlRutina = "SELECT ID FROM rutinas WHERE nombre = ?";
        String sqlCircuito = "SELECT ID FROM circuitos WHERE nombre = ?";
        String sqlUpdate = "UPDATE rutinas_circuitos SET rutina_id = ?, circuito_id = ? WHERE ID = ? AND Estado = TRUE";
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

    /**
     * Elimina una asociación de rutina y circuito cambiando su estado a FALSE.
     * 
     * @param id El identificador de la asociación a eliminar.
     * @return true si la eliminación se realizó correctamente, false si hubo un error.
     */
    public boolean EliminarRutinasCi(int id) {
        String sql = "UPDATE rutinas_circuitos SET Estado = FALSE WHERE id = ?";
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
