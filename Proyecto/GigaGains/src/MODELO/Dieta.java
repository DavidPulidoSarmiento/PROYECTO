package MODELO;

/**
 * Representa una dieta con información sobre su tipo y contenido nutricional.
 * La clase almacena detalles como las proteínas, carbohidratos y calorías de una dieta.
 */
public class Dieta {

    /** Identificador único de la dieta */
    private int id;

    /** Tipo de dieta (por ejemplo, baja en carbohidratos, alta en proteínas, etc.) */
    private String tipo;

    /** Cantidad de proteínas en la dieta (en gramos) */
    private int proteinas;

    /** Cantidad de carbohidratos en la dieta (en gramos) */
    private int carbohidratos;

    /** Cantidad de calorías en la dieta */
    private int calorias;

    /**
     * Constructor vacío de la clase Dieta.
     * Inicializa una instancia de Dieta sin valores predeterminados.
     */
    public Dieta() {
    }

    /**
     * Constructor de la clase Dieta con parámetros específicos para inicializar los atributos.
     * 
     * @param id Identificador único de la dieta.
     * @param tipo Tipo de dieta (por ejemplo, baja en carbohidratos, alta en proteínas).
     * @param proteinas Cantidad de proteínas en la dieta (en gramos).
     * @param carbohidratos Cantidad de carbohidratos en la dieta (en gramos).
     * @param calorias Cantidad de calorías en la dieta.
     */
    public Dieta(int id, String tipo, int proteinas, int carbohidratos, int calorias) {
        this.id = id;
        this.tipo = tipo;
        this.proteinas = proteinas;
        this.carbohidratos = carbohidratos;
        this.calorias = calorias;
    }

    /**
     * Obtiene el identificador único de la dieta.
     * 
     * @return El identificador de la dieta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador único de la dieta.
     * 
     * @param id El identificador de la dieta a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de dieta.
     * 
     * @return El tipo de dieta.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de dieta.
     * 
     * @param tipo El tipo de dieta a establecer.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la cantidad de proteínas en la dieta.
     * 
     * @return La cantidad de proteínas (en gramos).
     */
    public int getProteinas() {
        return proteinas;
    }

    /**
     * Establece la cantidad de proteínas en la dieta.
     * 
     * @param proteinas La cantidad de proteínas (en gramos) a establecer.
     */
    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    /**
     * Obtiene la cantidad de carbohidratos en la dieta.
     * 
     * @return La cantidad de carbohidratos (en gramos).
     */
    public int getCarbohidratos() {
        return carbohidratos;
    }

    /**
     * Establece la cantidad de carbohidratos en la dieta.
     * 
     * @param carbohidratos La cantidad de carbohidratos (en gramos) a establecer.
     */
    public void setCarbohidratos(int carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    /**
     * Obtiene la cantidad de calorías en la dieta.
     * 
     * @return La cantidad de calorías en la dieta.
     */
    public int getCalorias() {
        return calorias;
    }

    /**
     * Establece la cantidad de calorías en la dieta.
     * 
     * @param calorias La cantidad de calorías a establecer.
     */
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
}
