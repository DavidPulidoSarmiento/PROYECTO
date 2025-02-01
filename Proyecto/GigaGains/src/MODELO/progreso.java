
package MODELO;

public class progreso {
    // Atributos de la clase
    private int id;            // Identificador único del progreso
    private int usuario_id;    // Identificador del usuario al que pertenece el progreso
    private double peso;       // Peso del usuario en el momento del progreso
    private String fecha;      // Fecha en la que se registró el progreso

    // Constructor por defecto
    public progreso() {
    }

    // Constructor con parámetros para inicializar los valores
    public progreso(int id, int usuario_id, double peso, String fecha) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.peso = peso;
        this.fecha = fecha;
    }

    // Getter para obtener el valor de 'id'
    public int getId() {
        return id;
    }

    // Setter para establecer el valor de 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter para obtener el valor de 'usuario_id'
    public int getUsuario_id() {
        return usuario_id;
    }

    // Setter para establecer el valor de 'usuario_id'
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    // Getter para obtener el valor de 'peso'
    public double getPeso() {
        return peso;
    }

    // Setter para establecer el valor de 'peso'
    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Getter para obtener el valor de 'fecha'
    public String getFecha() {
        return fecha;
    }

    // Setter para establecer el valor de 'fecha'
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
