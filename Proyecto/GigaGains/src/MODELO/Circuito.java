
package MODELO;

public class Circuito {
    private int id;
    private String nombre;
    private int IDCircuitoEj;
    private int ejercicio_id;
    private String nombre_ejercicio;
    private String series;
    
    public Circuito() {
    }

    public Circuito(int id, String nombre, int IDCircuitoEj, int ejercicio_id, String nombre_ejercicio, String series) {
        this.id = id;
        this.nombre = nombre;
        this.IDCircuitoEj = IDCircuitoEj;
        this.ejercicio_id = ejercicio_id;
        this.nombre_ejercicio = nombre_ejercicio;
        this.series = series;
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

    public int getIDCircuitoEj() {
        return IDCircuitoEj;
    }

    public void setIDCircuitoEj(int IDCircuitoEj) {
        this.IDCircuitoEj = IDCircuitoEj;
    }

    public int getEjercicio_id() {
        return ejercicio_id;
    }

    public void setEjercicio_id(int ejercicio_id) {
        this.ejercicio_id = ejercicio_id;
    }

    public String getNombre_ejercicio() {
        return nombre_ejercicio;
    }

    public void setNombre_ejercicio(String nombre_ejercicio) {
        this.nombre_ejercicio = nombre_ejercicio;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }
      
}