
package MODELO;


public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String fecha_de_nacimiento;
    private String fecha_de_registro;
    private String genero;
    private String contraseña;
    private double estatura;
    private double peso;
    private String condicion_especial;
    private int id_plan;
    
    public Usuario(){
        
    }

    public Usuario(int id, String nombre, String email, String fecha_de_nacimiento, String fecha_de_registro, String genero, String contraseña, double estatura, double peso, String condicion_especial, int id_plan) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.fecha_de_registro = fecha_de_registro;
        this.genero = genero;
        this.contraseña = contraseña;
        this.estatura = estatura;
        this.peso = peso;
        this.condicion_especial = condicion_especial;
        this.id_plan = id_plan;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getFecha_de_registro() {
        return fecha_de_registro;
    }

    public void setFecha_de_registro(String fecha_de_registro) {
        this.fecha_de_registro = fecha_de_registro;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCondicion_especial() {
        return condicion_especial;
    }

    public void setCondicion_especial(String condicion_especial) {
        this.condicion_especial = condicion_especial;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }
    
    
}
