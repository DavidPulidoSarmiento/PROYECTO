package MODELO;

/**
 * Representa un ejercicio físico en el sistema.
 * Esta clase contiene información sobre el ejercicio, como su nombre, descripción, visualización,
 * y los músculos que se trabajan en el ejercicio.
 */
public class Ejercicio {

    /** Identificador único del ejercicio */
    private int id;

    /** Nombre del ejercicio */
    private String nombre;

    /** Descripción detallada del ejercicio */
    private String descripcion;

    /** Enlace o referencia visual del ejercicio (por ejemplo, una imagen o video) */
    private String visual; 

    /** Identificador del músculo principal que se trabaja con este ejercicio */
    private int id_musculos_ocupados;

    /** Nombre del músculo principal que se trabaja con este ejercicio */
    private String nombreMusculo;

    /**
     * Constructor vacío de la clase {@link Ejercicio}.
     * Este constructor permite crear un objeto {@link Ejercicio} sin inicializar sus atributos.
     */
    public Ejercicio() {
        
    }

    /**
     * Constructor de la clase {@link Ejercicio} con parámetros para inicializar todos los atributos.
     * 
     * @param id El identificador único del ejercicio.
     * @param nombre El nombre del ejercicio.
     * @param descripcion Descripción detallada del ejercicio.
     * @param visual Enlace o referencia visual (imagen/video) del ejercicio.
     * @param id_musculos_ocupados Identificador del músculo principal que se trabaja con este ejercicio.
     * @param nombreMusculo El nombre del músculo principal que se trabaja con este ejercicio.
     */
    public Ejercicio(int id, String nombre, String descripcion, String visual, int id_musculos_ocupados, String nombreMusculo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.visual = visual;
        this.id_musculos_ocupados = id_musculos_ocupados;
        this.nombreMusculo = nombreMusculo;
    }

    /**
     * Obtiene el identificador único del ejercicio.
     * 
     * @return El identificador del ejercicio.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único del ejercicio.
     * 
     * @param id El identificador del ejercicio a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del ejercicio.
     * 
     * @return El nombre del ejercicio.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ejercicio.
     * 
     * @param nombre El nombre del ejercicio a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del ejercicio.
     * 
     * @return La descripción del ejercicio.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del ejercicio.
     * 
     * @param descripcion La descripción del ejercicio a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el enlace o referencia visual del ejercicio.
     * 
     * @return El enlace visual del ejercicio.
     */
    public String getVisual() {
        return visual;
    }

    /**
     * Establece el enlace o referencia visual del ejercicio.
     * 
     * @param visual El enlace visual del ejercicio a establecer.
     */
    public void setVisual(String visual) {
        this.visual = visual;
    }

    /**
     * Obtiene el identificador del músculo principal que se trabaja con este ejercicio.
     * 
     * @return El identificador del músculo principal.
     */
    public int getId_musculos_ocupados() {
        return id_musculos_ocupados;
    }

    /**
     * Establece el identificador del músculo principal que se trabaja con este ejercicio.
     * 
     * @param id_musculos_ocupados El identificador del músculo principal a establecer.
     */
    public void setId_musculos_ocupados(int id_musculos_ocupados) {
        this.id_musculos_ocupados = id_musculos_ocupados;
    }

    /**
     * Obtiene el nombre del músculo principal que se trabaja con este ejercicio.
     * 
     * @return El nombre del músculo principal.
     */
    public String getNombreMusculo() {
        return nombreMusculo;
    }

    /**
     * Establece el nombre del músculo principal que se trabaja con este ejercicio.
     * 
     * @param nombreMusculo El nombre del músculo principal a establecer.
     */
    public void setNombreMusculo(String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }
    
}
