
package MODELO;


public class Plan {
    private int id;
    private String tipo;
    private int id_dieta;
    private int id_rutina;
    
    public Plan(){
        
    }

    public Plan(int id, String tipo, int id_dieta, int id_rutina) {
        this.id = id;
        this.tipo = tipo;
        this.id_dieta = id_dieta;
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

    public int getId_dieta() {
        return id_dieta;
    }

    public void setId_dieta(int id_dieta) {
        this.id_dieta = id_dieta;
    }

    public int getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(int id_rutina) {
        this.id_rutina = id_rutina;
    }
    
}
