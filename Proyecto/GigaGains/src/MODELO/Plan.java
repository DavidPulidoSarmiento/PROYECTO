package MODELO;

/**
 * Clase que representa un plan de entrenamiento que vincula una dieta y una rutina.
 * Un plan tiene un tipo, un nombre de dieta, un identificador de dieta, un nombre de rutina 
 * y un identificador de rutina.
 */
public class Plan {
    
    /** Identificador único del plan */
    private int id;
    
    /** Tipo del plan (por ejemplo, "básico", "avanzado", etc.) */
    private String tipo;
    
    /** Nombre de la dieta asociada al plan */
    private String nombre_dieta;
    
    /** Identificador de la dieta asociada al plan */
    private int id_dieta;
    
    /** Nombre de la rutina asociada al plan */
    private String nombre_rutina;
    
    /** Identificador de la rutina asociada al plan */
    private int id_rutina;

    /**
     * Constructor por defecto. Inicializa un objeto Plan sin valores.
     */
    public Plan() {
    }

    /**
     * Constructor con parámetros para crear un objeto Plan con los valores proporcionados.
     * 
     * @param id El identificador único del plan.
     * @param tipo El tipo del plan (ej. "básico", "avanzado").
     * @param nombre_dieta El nombre de la dieta asociada al plan.
     * @param id_dieta El identificador de la dieta asociada al plan.
     * @param nombre_rutina El nombre de la rutina asociada al plan.
     * @param id_rutina El identificador de la rutina asociada al plan.
     */
    public Plan(int id, String tipo, String nombre_dieta, int id_dieta, String nombre_rutina, int id_rutina) {
        this.id = id;
        this.tipo = tipo;
        this.nombre_dieta = nombre_dieta;
        this.id_dieta = id_dieta;
        this.nombre_rutina = nombre_rutina;
        this.id_rutina = id_rutina;
    }

    /**
     * Obtiene el identificador único del plan.
     * 
     * @return El identificador del plan.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del plan.
     * 
     * @param id El identificador del plan.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo del plan.
     * 
     * @return El tipo del plan.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del plan.
     * 
     * @param tipo El tipo del plan.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el nombre de la dieta asociada al plan.
     * 
     * @return El nombre de la dieta.
     */
    public String getNombre_dieta() {
        return nombre_dieta;
    }

    /**
     * Establece el nombre de la dieta asociada al plan.
     * 
     * @param nombre_dieta El nombre de la dieta.
     */
    public void setNombre_dieta(String nombre_dieta) {
        this.nombre_dieta = nombre_dieta;
    }

    /**
     * Obtiene el identificador de la dieta asociada al plan.
     * 
     * @return El identificador de la dieta.
     */
    public int getId_dieta() {
        return id_dieta;
    }

    /**
     * Establece el identificador de la dieta asociada al plan.
     * 
     * @param id_dieta El identificador de la dieta.
     */
    public void setId_dieta(int id_dieta) {
        this.id_dieta = id_dieta;
    }

    /**
     * Obtiene el nombre de la rutina asociada al plan.
     * 
     * @return El nombre de la rutina.
     */
    public String getNombre_rutina() {
        return nombre_rutina;
    }

    /**
     * Establece el nombre de la rutina asociada al plan.
     * 
     * @param nombre_rutina El nombre de la rutina.
     */
    public void setNombre_rutina(String nombre_rutina) {
        this.nombre_rutina = nombre_rutina;
    }

    /**
     * Obtiene el identificador de la rutina asociada al plan.
     * 
     * @return El identificador de la rutina.
     */
    public int getId_rutina() {
        return id_rutina;
    }

    /**
     * Establece el identificador de la rutina asociada al plan.
     * 
     * @param id_rutina El identificador de la rutina.
     */
    public void setId_rutina(int id_rutina) {
        this.id_rutina = id_rutina;
    }
}
