
package MODELO;


public class Plan {
    private int id;
    private String tipo;
    private String nombre_dieta;
    private int id_dieta;
    private String nombre_rutina;
    private int id_rutina;
    
    public Plan(){
        
    }

    public Plan(int id, String tipo, String nombre_dieta, int id_dieta, String nombre_rutina, int id_rutina) {
        this.id = id;
        this.tipo = tipo;
        this.nombre_dieta = nombre_dieta;
        this.id_dieta = id_dieta;
        this.nombre_rutina = nombre_rutina;
        this.id_rutina = id_rutina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre_dieta() {
        return nombre_dieta;
    }

    public void setNombre_dieta(String nombre_dieta) {
        this.nombre_dieta = nombre_dieta;
    }

    public int getId_dieta() {
        return id_dieta;
    }

    public void setId_dieta(int id_dieta) {
        this.id_dieta = id_dieta;
    }

    public String getNombre_rutina() {
        return nombre_rutina;
    }

    public void setNombre_rutina(String nombre_rutina) {
        this.nombre_rutina = nombre_rutina;
    }

    public int getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(int id_rutina) {
        this.id_rutina = id_rutina;
    }

}
