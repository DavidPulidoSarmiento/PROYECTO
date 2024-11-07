
package MODELO;


public class Ejercicio {
    private int id;
    private String nombre;
    private String descripcion;
    private String visual; 
    private int id_musculos_ocupados;
    private String nombreMusculo;
    
    public Ejercicio(){
        
    }

    public Ejercicio(int id, String nombre, String descripcion, String visual, int id_musculos_ocupados, String nombreMusculo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.visual = visual;
        this.id_musculos_ocupados = id_musculos_ocupados;
        this.nombreMusculo = nombreMusculo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVisual() {
        return visual;
    }

    public void setVisual(String visual) {
        this.visual = visual;
    }

    public int getId_musculos_ocupados() {
        return id_musculos_ocupados;
    }

    public void setId_musculos_ocupados(int id_musculos_ocupados) {
        this.id_musculos_ocupados = id_musculos_ocupados;
    }
    public String getNombreMusculo() {
        return nombreMusculo;
    }

    public void setNombreMusculo(String nombreMusculo) {
        this.nombreMusculo = nombreMusculo;
    }
    
}
