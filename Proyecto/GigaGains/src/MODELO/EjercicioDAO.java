package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EjercicioDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    // Método para registrar un ejercicio
    public boolean RegistrarEjercicio(Ejercicio ej) {
        String sqlMusculo = "SELECT ID FROM grupos_musculares WHERE nombre = ?";
        String sqlEjercicio = "INSERT INTO ejercicios(id, nombre, descripcion, visual, grupo_muscular_id, Estado) VALUES (?, ?, ?, ?, ?, TRUE)";  // Estado a TRUE por defecto
        try {
            con = cn.getConnection();

            // Obtener el ID del grupo muscular
            ps = con.prepareStatement(sqlMusculo);
            ps.setString(1, ej.getNombreMusculo());
            rs = ps.executeQuery();

            if (rs.next()) {
                int idMusculo = rs.getInt("ID");

                // Insertar el ejercicio
                ps = con.prepareStatement(sqlEjercicio);
                ps.setInt(1, ej.getId());
                ps.setString(2, ej.getNombre());
                ps.setString(3, ej.getDescripcion());
                ps.setString(4, ej.getVisual());
                ps.setInt(5, idMusculo);
                ps.executeUpdate();
                return true;
            } else {
                System.out.println("Grupo muscular no encontrado: " + ej.getNombreMusculo());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar ejercicio: " + e.getMessage());
            return false;
        }
    }

    // Método para listar ejercicios cuyo estado sea TRUE
    public List<Ejercicio> ListarEjercicio() {
        List<Ejercicio> listaEj = new ArrayList<>();
        String sql = "SELECT e.ID, e.nombre, e.descripcion, e.visual, m.nombre AS nombre_musculo " +
                     "FROM ejercicios e " +
                     "INNER JOIN grupos_musculares m ON e.grupo_muscular_id = m.ID " +
                     "WHERE e.Estado = TRUE";  // Solo ejercicios con estado TRUE

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ejercicio ej = new Ejercicio();
                ej.setId(rs.getInt("ID"));
                ej.setNombre(rs.getString("nombre"));
                ej.setDescripcion(rs.getString("descripcion"));
                ej.setVisual(rs.getString("visual"));
                ej.setNombreMusculo(rs.getString("nombre_musculo"));
                listaEj.add(ej);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ejercicios: " + e.getMessage());
        }
        return listaEj;
    }

    // Método para "eliminar" un ejercicio (actualiza su Estado a FALSE)
    public boolean EliminarEjercicio(int id) {
        String sql = "UPDATE ejercicios SET Estado = FALSE WHERE ID = ?";  // Cambia el estado a FALSE en lugar de eliminar
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado del ejercicio a FALSE: " + e.getMessage());
            return false;
        }
    }

    // Método para modificar un ejercicio
    public boolean ModificarEjercicio(Ejercicio ej) {
        String sqlMusculo = "SELECT ID FROM grupos_musculares WHERE nombre = ?";
        String sqlUpdate = "UPDATE ejercicios SET nombre = ?, descripcion = ?, visual = ?, grupo_muscular_id = ? WHERE ID = ? AND Estado = TRUE";  // Solo actualiza si el estado es TRUE
        try {
            con = cn.getConnection();

            // Obtener el ID del grupo muscular
            ps = con.prepareStatement(sqlMusculo);
            ps.setString(1, ej.getNombreMusculo());
            rs = ps.executeQuery();

            if (rs.next()) {
                int idMusculo = rs.getInt("ID");

                // Actualizar el ejercicio
                ps = con.prepareStatement(sqlUpdate);
                ps.setString(1, ej.getNombre());
                ps.setString(2, ej.getDescripcion());
                ps.setString(3, ej.getVisual());
                ps.setInt(4, idMusculo);
                ps.setInt(5, ej.getId());
                ps.executeUpdate();
                return true;
            } else {
                System.out.println("Grupo muscular no encontrado: " + ej.getNombreMusculo());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar ejercicio: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener los nombres de los músculos ocupados
    public List<String> obtenerNombresMusculosOcupados() {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT nombre FROM grupos_musculares";

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
