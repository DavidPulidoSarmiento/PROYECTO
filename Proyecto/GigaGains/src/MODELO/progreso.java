
package MODELO;


public class progreso {
    private int id;
    private int usuario_id;
    private double peso;
    private String fecha;

    public progreso() {
    }

    public progreso(int id, int usuario_id, double peso, String fecha) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.peso = peso;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
