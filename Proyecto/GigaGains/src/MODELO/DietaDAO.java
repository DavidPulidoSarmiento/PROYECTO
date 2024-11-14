package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad {@link Dieta}.
 * Esta clase se encarga de interactuar con la base de datos para gestionar las dietas.
 */
public class DietaDAO {

    /** Objeto de conexión a la base de datos */
    Connection con;

    /** Instancia de la clase {@link Conexion} para obtener la conexión a la base de datos */
    Conexion cn = new Conexion();

    /** Declaración de objetos PreparedStatement para ejecutar consultas SQL */
    PreparedStatement ps;

    /** Resultado de la consulta SQL */
    ResultSet rs;

    /**
     * Lista todas las dietas activas (Estado = TRUE) desde la base de datos.
     * 
     * @return Una lista de objetos {@link Dieta} que representan las dietas activas.
     */
    public List<Dieta> ListarDieta() {
        List<Dieta> listaDieta = new ArrayList<>();
        String sql = "SELECT * FROM dietas WHERE Estado = TRUE";  // Filtra por Estado TRUE

        try {
            con = cn.getConnection();  // Establece la conexión a la base de datos
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Dieta dieta = new Dieta();  // Crear un objeto Dieta por cada resultado
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
        return listaDieta;  // Retorna la lista completa de dietas
    }

    /**
     * Registra una nueva dieta en la base de datos.
     * 
     * @param die Un objeto {@link Dieta} con la información de la dieta a registrar.
     * @return {@code true} si la dieta se registra correctamente, {@code false} si ocurre un error.
     */
    public boolean RegistrarDieta(Dieta die) {
        String sql = "INSERT INTO dietas (ID, tipo, proteinas, carbohidratos, Calorias) VALUES  (?,?,?,?,?)";  
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, die.getId());
            ps.setString(2, die.getTipo());
            ps.setInt(3, die.getProteinas());
            ps.setInt(4, die.getCarbohidratos());
            ps.setInt(5, die.getCalorias());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());  // Muestra el error en un cuadro de diálogo
            return false;
        } finally {
            try {
                con.close();  // Cierra la conexión a la base de datos
            } catch (SQLException e) {
                System.out.print(e.toString());
            }         
        }
    }

    /**
     * Elimina (en realidad cambia el estado a {@code FALSE}) una dieta en la base de datos.
     * 
     * @param id El identificador de la dieta a eliminar.
     * @return {@code true} si la dieta se elimina correctamente (cambia su estado a {@code FALSE}),
     *         {@code false} si ocurre un error.
     */
    public boolean EliminarDieta(int id) {
        String sql = "UPDATE dietas SET Estado = FALSE WHERE ID = ?";  // Cambia el estado a FALSE
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
                con.close();  // Cierra la conexión a la base de datos
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    /**
     * Modifica los detalles de una dieta existente en la base de datos.
     * 
     * @param die Un objeto {@link Dieta} con los nuevos datos de la dieta.
     * @return {@code true} si la dieta se modifica correctamente, {@code false} si ocurre un error.
     */
    public boolean ModificarDieta(Dieta die) {
        String sql = "UPDATE dietas SET tipo = ?, proteinas = ?, carbohidratos = ?, calorias = ? WHERE dietas.ID = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, die.getTipo());
            ps.setInt(2, die.getProteinas());
            ps.setInt(3, die.getCarbohidratos());
            ps.setInt(4, die.getCalorias());
            ps.setInt(5, die.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();  // Cierra la conexión a la base de datos
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
