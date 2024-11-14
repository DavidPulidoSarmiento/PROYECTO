package MODELO;

/**
 * Clase que representa un usuario del sistema.
 * Contiene los atributos y métodos relacionados con la información personal de un usuario.
 */
public class Usuario {
    
    /** Identificador único del usuario */
    private int id;
    
    /** Nombre del usuario */
    private String nombre;
    
    /** Correo electrónico del usuario */
    private String email;
    
    /** Fecha de nacimiento del usuario */
    private String fecha_de_nacimiento;
    
    /** Fecha de registro del usuario en el sistema */
    private String fecha_de_registro;
    
    /** Género del usuario */
    private String genero;
    
    /** Contraseña del usuario */
    private String contraseña;
    
    /** Estatura del usuario en metros */
    private double estatura;
    
    /** Peso del usuario en kilogramos */
    private double peso;
    
    /** Condiciones especiales del usuario, si las tuviera */
    private String condicion_especial;
    
    /** Identificador del plan asociado al usuario */
    private int id_plan;

    /**
     * Constructor por defecto de la clase Usuario.
     * Inicializa un objeto Usuario sin parámetros.
     */
    public Usuario(){
        
    }

    /**
     * Constructor con parámetros para crear un objeto Usuario con todos sus atributos inicializados.
     * 
     * @param id Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param email Correo electrónico del usuario.
     * @param fecha_de_nacimiento Fecha de nacimiento del usuario.
     * @param fecha_de_registro Fecha en la que el usuario se registró.
     * @param genero Género del usuario.
     * @param contraseña Contraseña del usuario.
     * @param estatura Estatura del usuario.
     * @param peso Peso del usuario.
     * @param condicion_especial Condiciones especiales del usuario.
     * @param id_plan Identificador del plan de entrenamiento asociado al usuario.
     */
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

    /**
     * Obtiene el identificador del usuario.
     * 
     * @return El identificador único del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     * 
     * @param id El identificador único del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     * 
     * @return La fecha de nacimiento del usuario en formato de cadena.
     */
    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     * 
     * @param fecha_de_nacimiento La fecha de nacimiento del usuario.
     */
    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    /**
     * Obtiene la fecha de registro del usuario en el sistema.
     * 
     * @return La fecha de registro del usuario.
     */
    public String getFecha_de_registro() {
        return fecha_de_registro;
    }

    /**
     * Establece la fecha de registro del usuario en el sistema.
     * 
     * @param fecha_de_registro La fecha de registro del usuario.
     */
    public void setFecha_de_registro(String fecha_de_registro) {
        this.fecha_de_registro = fecha_de_registro;
    }

    /**
     * Obtiene el género del usuario.
     * 
     * @return El género del usuario.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del usuario.
     * 
     * @param genero El género del usuario.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param contraseña La contraseña del usuario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene la estatura del usuario.
     * 
     * @return La estatura del usuario en metros.
     */
    public double getEstatura() {
        return estatura;
    }

    /**
     * Establece la estatura del usuario.
     * 
     * @param estatura La estatura del usuario en metros.
     */
    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    /**
     * Obtiene el peso del usuario.
     * 
     * @return El peso del usuario en kilogramos.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso del usuario.
     * 
     * @param peso El peso del usuario en kilogramos.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene las condiciones especiales del usuario, si las tuviera.
     * 
     * @return Las condiciones especiales del usuario (por ejemplo, enfermedades, lesiones, etc.).
     */
    public String getCondicion_especial() {
        return condicion_especial;
    }

    /**
     * Establece las condiciones especiales del usuario, si las tuviera.
     * 
     * @param condicion_especial Las condiciones especiales del usuario.
     */
    public void setCondicion_especial(String condicion_especial) {
        this.condicion_especial = condicion_especial;
    }

    /**
     * Obtiene el identificador del plan de entrenamiento asociado al usuario.
     * 
     * @return El identificador del plan de entrenamiento.
     */
    public int getId_plan() {
        return id_plan;
    }

    /**
     * Establece el identificador del plan de entrenamiento asociado al usuario.
     * 
     * @param id_plan El identificador del plan de entrenamiento.
     */
    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }
}
