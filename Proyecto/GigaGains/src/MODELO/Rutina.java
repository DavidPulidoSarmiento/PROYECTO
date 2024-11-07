
package MODELO;


public class Rutina {
    private int id;
    private String nombre;
    private int idcircuito;
    private String nombrecircuito;

    public Rutina() {
    }

    public Rutina(int id, String nombre, int idcircuito, String nombrecircuito) {
        this.id = id;
        this.nombre = nombre;
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
