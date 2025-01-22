package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de gestionar las operaciones de acceso a la base de datos relacionadas con los circuitos.
 * Proporciona métodos para registrar, modificar, listar, eliminar y restaurar circuitos, así como para obtener información sobre ejercicios asociados a los circuitos.
 */
public class CircuitoDAO {
    
    /** Conexión a la base de datos */
    Connection con;
    
    /** Instancia de la clase Conexion para obtener la conexión a la base de datos */
    Conexion cn = new Conexion();
    
    /** Objeto PreparedStatement para ejecutar consultas SQL */
    PreparedStatement ps;
    
    /** Resultado de la consulta SQL */
    ResultSet rs;

    /**
     * Registra un nuevo circuito en la base de datos.
     * 
     * @param circuito El objeto Circuito que contiene la información a registrar.
     * @return true si el circuito fue registrado correctamente, false si ocurrió un error.
     */
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
    public boolean existeId(int id) {
    String sql = "SELECT COUNT(*) FROM circuitos WHERE id = ?";
    try {
        con = cn.getConnection();
            // Obtener el ID del grupo muscular
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Si el conteo es mayor a 0, el ID ya existe
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Si ocurre un error o no se encuentra, devolver false
}
    public boolean existeIdCiEj(int id) {
    String sql = "SELECT COUNT(*) FROM circuitos_ejercicios WHERE id = ?";
    try {
        con = cn.getConnection();
            // Obtener el ID del grupo muscular
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; // Si el conteo es mayor a 0, el ID ya existe
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Si ocurre un error o no se encuentra, devolver false
}
    /**
     * Lista todos los circuitos cuyo Estado es TRUE (activos).
     * 
     * @return Una lista de objetos Circuito representando los circuitos activos.
     */
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
                listaCircuitos.add(circuito);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaCircuitos;
    }

    /**
     * Lista todos los circuitos cuyo Estado es FALSE (inactivos).
     * 
     * @return Una lista de objetos Circuito representando los circuitos inactivos.
     */
    public List<Circuito> ListarCircuitosFalsos() {
        List<Circuito> listaCircuitos = new ArrayList<>();
        String sql = "SELECT * FROM circuitos WHERE Estado = FALSE";  // Filtra por Estado FALSE

        try {
            con = cn.getConnection();  // Conexión a la base de datos
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Circuito circuito = new Circuito();  // Crear un objeto Circuito por cada resultado
                circuito.setId(rs.getInt("ID"));
                circuito.setNombre(rs.getString("nombre"));
                listaCircuitos.add(circuito);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaCircuitos;
    }

    /**
     * Elimina (desactiva) un circuito cambiando su estado a FALSE.
     * 
     * @param id El identificador del circuito a eliminar.
     * @return true si el circuito fue desactivado correctamente, false si ocurrió un error.
     */
    public boolean EliminarCircuito(int id) {
        String sql = "UPDATE circuitos SET Estado = FALSE WHERE id = ?";
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

    /**
     * Restaura un circuito activando su estado a TRUE.
     * 
     * @param id El identificador del circuito a restaurar.
     * @return true si el circuito fue restaurado correctamente, false si ocurrió un error.
     */
    public boolean RestaurarCircuito(int id) {
        String sql = "UPDATE circuitos SET Estado = TRUE WHERE id = ?";
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

    /**
     * Elimina (desactiva) un ejercicio dentro de un circuito cambiando su estado a FALSE.
     * 
     * @param id El identificador del ejercicio en el circuito.
     * @return true si el ejercicio fue desactivado correctamente, false si ocurrió un error.
     */
    public boolean EliminarCircuitoEj(int id) {
        String sql = "UPDATE circuitos_ejercicios SET Estado = FALSE WHERE id = ?";
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
    public boolean RestaurarCircuitoEj(int id) {
        String sql = "UPDATE circuitos_ejercicios SET Estado = TRUE WHERE id = ?";
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

    /**
     * Modifica los datos de un circuito.
     * 
     * @param circuito El objeto Circuito con los nuevos datos.
     * @return true si el circuito fue modificado correctamente, false si ocurrió un error.
     */
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

    /**
     * Obtiene los nombres de todos los ejercicios activos.
     * 
     * @return Una lista de nombres de ejercicios.
     */
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
     * Obtiene todos los ejercicios asociados a un circuito específico.
     * 
     * @param nombreCircuito El nombre del circuito.
     * @return Una lista de objetos Circuito con los ejercicios y sus detalles asociados al circuito.
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEjercicios;
    }
    public List<Circuito> obtenerEjerciciosPorCircuitoFalsos(String nombreCircuito) {
        List<Circuito> listaEjercicios = new ArrayList<>();
        String sql = "SELECT ce.ID AS IDCircuitoEj, ce.ejercicio_id, e.nombre AS nombre_ejercicio, ce.series " +
                     "FROM circuitos_ejercicios ce " +
                     "JOIN circuitos c ON ce.circuito_id = c.id " +
                     "JOIN ejercicios e ON ce.ejercicio_id = e.id " +
                     "WHERE c.nombre = ? AND ce.Estado = FALSE";

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEjercicios;
    }

    /**
     * Obtiene los nombres de todos los circuitos activos.
     * 
     * @return Una lista de nombres de circuitos activos.
     */
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

    /**
     * Registra un ejercicio en un circuito específico.
     * 
     * @param circuito El objeto Circuito que contiene la información del ejercicio a agregar.
     * @return true si el ejercicio fue registrado correctamente, false si ocurrió un error.
     */
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

    /**
     * Modifica un ejercicio dentro de un circuito específico.
     * 
     * @param circuito El objeto Circuito que contiene la información modificada del ejercicio.
     * @return true si el ejercicio fue modificado correctamente, false si ocurrió un error.
     */
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

            // Actualizar el ejercicio en circuitos_ejercicios
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
 