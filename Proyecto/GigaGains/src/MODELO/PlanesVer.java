
package MODELO;


public class PlanesVer {
    /** Identificador único del plan seleccionado */
    private String plan_seleccionado;
    
    private int rutina_id;
        
    /** Nombre de la rutina asociada al plan */
    private String nombre_rutina;
    
    private int dieta_id;
    
    private String tipo_dieta;
    
    /** Cantidad de proteínas en la dieta (en gramos) */
    private int proteinas;

    /** Cantidad de carbohidratos en la dieta (en gramos) */
    private int carbohidratos;

    /** Cantidad de calorías en la dieta */
    private int calorias;
    
    private int circuito_id;
        
    /** Nombre del circuito asociado al plan */
    private String nombre_circuito;
    
    private int ejercicio_id;
    
    private String nombre_id;
    
    private String series;
    

    /** Constructor vacío */
    public PlanesVer() {
    }

    /** Constructor con parámetros para inicializar todos los atributos */
    public PlanesVer(String plan_seleccionado, int rutina_id, String nombre_rutina, int dieta_id, String tipo_dieta, int proteinas, int carbohidratos, int calorias, int circuito_id, String nombre_circuito, int ejercicio_id, String nombre_id, String series) {
        this.plan_seleccionado = plan_seleccionado;
        this.rutina_id = rutina_id;
        this.nombre_rutina = nombre_rutina;
        this.dieta_id = dieta_id;
        this.tipo_dieta = tipo_dieta;
        this.proteinas = proteinas;
        this.carbohidratos = carbohidratos;
        this.calorias = calorias;
        this.circuito_id = circuito_id;
        this.nombre_circuito = nombre_circuito;
        this.ejercicio_id = ejercicio_id;
        this.nombre_id = nombre_id;
        this.series = series;
    }

    /** Obtener el plan seleccionado */
    public String getPlan_seleccionado() {
        return plan_seleccionado;
    }

    /** Establecer el plan seleccionado */
    public void setPlan_seleccionado(String plan_seleccionado) {
        this.plan_seleccionado = plan_seleccionado;
    }

    /** Obtener el id de la rutina */
    public int getRutina_id() {
        return rutina_id;
    }

    /** Establecer el id de la rutina */
    public void setRutina_id(int rutina_id) {
        this.rutina_id = rutina_id;
    }

    /** Obtener el nombre de la rutina */
    public String getNombre_rutina() {
        return nombre_rutina;
    }

    /** Establecer el nombre de la rutina */
    public void setNombre_rutina(String nombre_rutina) {
        this.nombre_rutina = nombre_rutina;
    }

    /** Obtener el id de la dieta */
    public int getDieta_id() {
        return dieta_id;
    }

    /** Establecer el id de la dieta */
    public void setDieta_id(int dieta_id) {
        this.dieta_id = dieta_id;
    }

    /** Obtener el tipo de dieta */
    public String getTipo_dieta() {
        return tipo_dieta;
    }

    /** Establecer el tipo de dieta */
    public void setTipo_dieta(String tipo_dieta) {
        this.tipo_dieta = tipo_dieta;
    }

    /** Obtener la cantidad de proteínas en la dieta */
    public int getProteinas() {
        return proteinas;
    }

    /** Establecer la cantidad de proteínas en la dieta */
    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    /** Obtener la cantidad de carbohidratos en la dieta */
    public int getCarbohidratos() {
        return carbohidratos;
    }

    /** Establecer la cantidad de carbohidratos en la dieta */
    public void setCarbohidratos(int carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    /** Obtener la cantidad de calorías en la dieta */
    public int getCalorias() {
        return calorias;
    }

    /** Establecer la cantidad de calorías en la dieta */
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    /** Obtener el id del circuito asociado al plan */
    public int getCircuito_id() {
        return circuito_id;
    }

    /** Establecer el id del circuito asociado al plan */
    public void setCircuito_id(int circuito_id) {
        this.circuito_id = circuito_id;
    }

    /** Obtener el nombre del circuito */
    public String getNombre_circuito() {
        return nombre_circuito;
    }

    /** Establecer el nombre del circuito */
    public void setNombre_circuito(String nombre_circuito) {
        this.nombre_circuito = nombre_circuito;
    }

    /** Obtener el id del ejercicio asociado al plan */
    public int getEjercicio_id() {
        return ejercicio_id;
    }

    /** Establecer el id del ejercicio asociado al plan */
    public void setEjercicio_id(int ejercicio_id) {
        this.ejercicio_id = ejercicio_id;
    }

    /** Obtener el nombre del ejercicio */
    public String getNombre_id() {
        return nombre_id;
    }

    /** Establecer el nombre del ejercicio */
    public void setNombre_id(String nombre_id) {
        this.nombre_id = nombre_id;
    }

    /** Obtener las series del ejercicio */
    public String getSeries() {
        return series;
    }

    /** Establecer las series del ejercicio */
    public void setSeries(String series) {
        this.series = series;
    }
}
