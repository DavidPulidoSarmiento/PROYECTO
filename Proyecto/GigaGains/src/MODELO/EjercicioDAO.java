package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO que proporciona métodos para interactuar con la base de datos 
 * en relación a los ejercicios. Permite registrar, listar, modificar y 
 * eliminar ejercicios, así como obtener los nombres de los músculos ocupados.
 */
public class EjercicioDAO {

    /** Conexión a la base de datos */
    Connection con;

    /** Objeto de la clase {@link Conexion} para manejar la conexión a la base de datos */
    Conexion cn = new Conexion();

    /** Declaración de la sentencia SQL */
    PreparedStatement ps;

    /** Resultado de la consulta SQL */
    ResultSet rs;

    /**
     * Registra un nuevo ejercicio en la base de datos.
     * Este método primero obtiene el ID del grupo muscular asociado con el ejercicio
     * y luego inserta el ejercicio en la tabla `ejercicios`.
     * 
     * @param ej El objeto {@link Ejercicio} que se desea registrar.
     * @return {@code true} si el ejercicio se registró correctamente, 
     *         {@code false} si ocurrió algún error.
     */
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

    /**
     * Obtiene una lista de los ejercicios cuyo estado es {@code TRUE}.
     * 
     * @return Una lista de objetos {@link Ejercicio} que representan los ejercicios activos.
     */
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

    /**
     * Elimina un ejercicio cambiando su estado a {@code FALSE}.
     * 
     * @param id El ID del ejercicio que se desea "eliminar" (actualizar su estado).
     * @return {@code true} si el estado se actualizó correctamente a {@code FALSE},
     *         {@code false} si ocurrió un error.
     */
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

    /**
     * Modifica los detalles de un ejercicio en la base de datos.
     * Este método actualiza los atributos de un ejercicio, incluyendo el nombre, 
     * descripción, visualización y el grupo muscular asociado, si el ejercicio 
     * tiene un estado {@code TRUE}.
     * 
     * @param ej El objeto {@link Ejercicio} con los nuevos datos a actualizar.
     * @return {@code true} si el ejercicio fue actualizado correctamente, 
     *         {@code false} si ocurrió algún error o no se encontró el grupo muscular.
     */
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

    /**
     * Obtiene una lista con los nombres de todos los músculos ocupados (de la tabla `grupos_musculares`).
     * 
     * @return Una lista de cadenas con los nombres de los grupos musculares.
     */
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
