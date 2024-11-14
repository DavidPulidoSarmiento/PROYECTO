
package MODELO;


public class Rutina {
    private int id;
    private String nombre;
    private int idru_cir;
    private int idcircuito;
    private String nombrecircuito;

    public Rutina() {
    }

    public Rutina(int id, String nombre, int idru_cir, int idcircuito, String nombrecircuito) {
        this.id = id;
        this.nombre = nombre;
        this.idru_cir = idru_cir;
        this.idcircuito = idcircuito;
        this.nombrecircuito = nombrecircuito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdru_cir() {
        return idru_cir;
    }

    public void setIdru_cir(int idru_cir) {
        this.idru_cir = idru_cir;
    }

    public int getIdcircuito() {
        return idcircuito;
    }

    public void setIdcircuito(int idcircuito) {
        this.idcircuito = idcircuito;
    }

    public String getNombrecircuito() {
        return nombrecircuito;
    }

    public void setNombrecircuito(String nombrecircuito) {
        this.nombrecircuito = nombrecircuito;
    }

    
}
