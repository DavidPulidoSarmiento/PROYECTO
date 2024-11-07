
package MODELO;


public class Rutina {
    private int id;
    private String nombre;
    private int idcircuito;

    public Rutina() {
    }

    public Rutina(int id, String nombre, int idcircuito) {
        this.id = id;
        this.nombre = nombre;
        this.idcircuito = idcircuito;
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
    
}
