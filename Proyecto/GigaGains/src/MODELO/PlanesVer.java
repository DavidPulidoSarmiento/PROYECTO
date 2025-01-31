
package MODELO;


public class PlanesVer {
    /** Identificador único de la rutina */
    private String plan_seleccionado;
    
    private int rutina_id;
        
    /** Nombre de la rutina */
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
        
    /** Nombre de la rutina */
    private String nombre_circuito;
    
    private int ejercicio_id;
    
    private String nombre_id;
    
    private String series;
    

    public PlanesVer() {
    }

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

    public String getPlan_seleccionado() {
        return plan_seleccionado;
    }

    public void setPlan_seleccionado(String plan_seleccionado) {
        this.plan_seleccionado = plan_seleccionado;
    }

    public int getRutina_id() {
        return rutina_id;
    }

    public void setRutina_id(int rutina_id) {
        this.rutina_id = rutina_id;
    }

    public String getNombre_rutina() {
        return nombre_rutina;
    }

    public void setNombre_rutina(String nombre_rutina) {
        this.nombre_rutina = nombre_rutina;
    }

    public int getDieta_id() {
        return dieta_id;
    }

    public void setDieta_id(int dieta_id) {
        this.dieta_id = dieta_id;
    }

    public String getTipo_dieta() {
        return tipo_dieta;
    }

    public void setTipo_dieta(String tipo_dieta) {
        this.tipo_dieta = tipo_dieta;
    }

    public int getProteinas() {
        return proteinas;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public int getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(int carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getCircuito_id() {
        return circuito_id;
    }

    public void setCircuito_id(int circuito_id) {
        this.circuito_id = circuito_id;
    }

    public String getNombre_circuito() {
        return nombre_circuito;
    }

    public void setNombre_circuito(String nombre_circuito) {
        this.nombre_circuito = nombre_circuito;
    }

    public int getEjercicio_id() {
        return ejercicio_id;
    }

    public void setEjercicio_id(int ejercicio_id) {
        this.ejercicio_id = ejercicio_id;
    }

    public String getNombre_id() {
        return nombre_id;
    }

    public void setNombre_id(String nombre_id) {
        this.nombre_id = nombre_id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
    
}
