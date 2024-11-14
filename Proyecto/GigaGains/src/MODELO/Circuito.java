package MODELO;

/**
 * Representa un Circuito que contiene información sobre un ejercicio dentro de un conjunto de circuitos.
 * Un circuito está relacionado con un ejercicio y contiene información sobre el nombre del ejercicio,
 * el número de series, y otros atributos asociados a su identificación.
 */
public class Circuito {
    
    /** Identificador único del circuito */
    private int id;
    
    /** Nombre del circuito */
    private String nombre;
    
    /** Identificador asociado al ejercicio del circuito */
    private int IDCircuitoEj;
    
    /** Identificador del ejercicio dentro del circuito */
    private int ejercicio_id;
    
    /** Nombre del ejercicio en el circuito */
    private String nombre_ejercicio;
    
    /** Número de series del ejercicio en el circuito */
    private String series;

    /**
     * Constructor vacío de la clase Circuito. Inicializa los atributos con valores por defecto.
     */
    public Circuito() {
    }

    /**
     * Constructor de la clase Circuito con parámetros para inicializar todos los atributos.
     * 
     * @param id Identificador del circuito.
     * @param nombre Nombre del circuito.
     * @param IDCircuitoEj Identificador del ejercicio en el circuito.
     * @param ejercicio_id Identificador del ejercicio.
     * @param nombre_ejercicio Nombre del ejercicio.
     * @param series Número de series del ejercicio en el circuito.
     */
    public Circuito(int id, String nombre, int IDCircuitoEj, int ejercicio_id, String nombre_ejercicio, String series) {
        this.id = id;
        this.nombre = nombre;
        this.IDCircuitoEj = IDCircuitoEj;
        this.ejercicio_id = ejercicio_id;
        this.nombre_ejercicio = nombre_ejercicio;
        this.series = series;
    }

    /**
     * Obtiene el identificador del circuito.
     * 
     * @return El identificador del circuito.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del circuito.
     * 
     * @param id El nuevo identificador del circuito.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del circuito.
     * 
     * @return El nombre del circuito.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del circuito.
     * 
     * @param nombre El nuevo nombre del circuito.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador del ejercicio en el circuito.
     * 
     * @return El identificador del ejercicio en el circuito.
     */
    public int getIDCircuitoEj() {
        return IDCircuitoEj;
    }

    /**
     * Establece el identificador del ejercicio en el circuito.
     * 
     * @param IDCircuitoEj El nuevo identificador del ejercicio en el circuito.
     */
    public void setIDCircuitoEj(int IDCircuitoEj) {
        this.IDCircuitoEj = IDCircuitoEj;
    }

    /**
     * Obtiene el identificador del ejercicio dentro del circuito.
     * 
     * @return El identificador del ejercicio.
     */
    public int getEjercicio_id() {
        return ejercicio_id;
    }

    /**
     * Establece el identificador del ejercicio dentro del circuito.
     * 
     * @param ejercicio_id El nuevo identificador del ejercicio.
     */
    public void setEjercicio_id(int ejercicio_id) {
        this.ejercicio_id = ejercicio_id;
    }

    /**
     * Obtiene el nombre del ejercicio en el circuito.
     * 
     * @return El nombre del ejercicio.
     */
    public String getNombre_ejercicio() {
        return nombre_ejercicio;
    }

    /**
     * Establece el nombre del ejercicio en el circuito.
     * 
     * @param nombre_ejercicio El nuevo nombre del ejercicio.
     */
    public void setNombre_ejercicio(String nombre_ejercicio) {
        this.nombre_ejercicio = nombre_ejercicio;
    }

    /**
     * Obtiene el número de series del ejercicio en el circuito.
     * 
     * @return El número de series.
     */
    public String getSeries() {
        return series;
    }

    /**
     * Establece el número de series del ejercicio en el circuito.
     * 
     * @param series El nuevo número de series del ejercicio.
     */
    public void setSeries(String series) {
        this.series = series;
    }
}
