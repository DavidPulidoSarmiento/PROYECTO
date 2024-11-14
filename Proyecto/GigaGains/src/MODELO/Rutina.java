package MODELO;

/**
 * Representa una rutina de ejercicios asociada a un circuito en un sistema de gestión de entrenamientos.
 * Esta clase contiene los detalles de la rutina, como su nombre y la relación con un circuito específico.
 */
public class Rutina {
    
    /** Identificador único de la rutina */
    private int id;
    
    /** Nombre de la rutina */
    private String nombre;
    
    /** Identificador del vínculo entre la rutina y el circuito */
    private int idru_cir;
    
    /** Identificador del circuito al que pertenece la rutina */
    private int idcircuito;
    
    /** Nombre del circuito al que está asociada la rutina */
    private String nombrecircuito;

    /**
     * Constructor por defecto de la clase Rutina.
     * Inicializa los atributos de la clase con valores predeterminados.
     */
    public Rutina() {
    }

    /**
     * Constructor parametrizado de la clase Rutina.
     * 
     * @param id Identificador único de la rutina.
     * @param nombre Nombre de la rutina.
     * @param idru_cir Identificador del vínculo entre la rutina y el circuito.
     * @param idcircuito Identificador del circuito al que pertenece la rutina.
     * @param nombrecircuito Nombre del circuito asociado a la rutina.
     */
    public Rutina(int id, String nombre, int idru_cir, int idcircuito, String nombrecircuito) {
        this.id = id;
        this.nombre = nombre;
        this.idru_cir = idru_cir;
        this.idcircuito = idcircuito;
        this.nombrecircuito = nombrecircuito;
    }

    /**
     * Obtiene el identificador único de la rutina.
     * 
     * @return El identificador de la rutina.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la rutina.
     * 
     * @param id El identificador de la rutina.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la rutina.
     * 
     * @return El nombre de la rutina.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la rutina.
     * 
     * @param nombre El nombre de la rutina.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador del vínculo entre la rutina y el circuito.
     * 
     * @return El identificador del vínculo entre la rutina y el circuito.
     */
    public int getIdru_cir() {
        return idru_cir;
    }

    /**
     * Establece el identificador del vínculo entre la rutina y el circuito.
     * 
     * @param idru_cir El identificador del vínculo entre la rutina y el circuito.
     */
    public void setIdru_cir(int idru_cir) {
        this.idru_cir = idru_cir;
    }

    /**
     * Obtiene el identificador del circuito al que pertenece la rutina.
     * 
     * @return El identificador del circuito.
     */
    public int getIdcircuito() {
        return idcircuito;
    }

    /**
     * Establece el identificador del circuito al que pertenece la rutina.
     * 
     * @param idcircuito El identificador del circuito.
     */
    public void setIdcircuito(int idcircuito) {
        this.idcircuito = idcircuito;
    }

    /**
     * Obtiene el nombre del circuito asociado a la rutina.
     * 
     * @return El nombre del circuito.
     */
    public String getNombrecircuito() {
        return nombrecircuito;
    }

    /**
     * Establece el nombre del circuito asociado a la rutina.
     * 
     * @param nombrecircuito El nombre del circuito.
     */
    public void setNombrecircuito(String nombrecircuito) {
        this.nombrecircuito = nombrecircuito;
    }
}
