package MODELO;

/**
 * Clase que representa la entidad de un inicio de sesión de usuario.
 * Contiene los atributos necesarios para almacenar la información de autenticación y el rol del usuario.
 */
public class login {

    /** Nombre de usuario para el inicio de sesión */
    private String usuario;
    
    /** Contraseña asociada al usuario */
    private String contrasena;
    
    /** Rol del usuario (e.g., "administrador", "usuario", etc.) */
    private String rol;

    /**
     * Constructor por defecto.
     * Crea una instancia de la clase {@link login} sin inicializar sus atributos.
     */
    public login() {
    }

    /**
     * Constructor con parámetros.
     * Crea una instancia de la clase {@link login} con los valores especificados.
     * 
     * @param usuario Nombre de usuario para el inicio de sesión.
     * @param contraseña Contraseña asociada al usuario.
     * @param rol Rol del usuario (e.g., "administrador", "usuario", etc.).
     */
    public login(String usuario, String contraseña, String rol) {
        this.usuario = usuario;
        this.contrasena = contraseña;
        this.rol = rol;
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario para el inicio de sesión.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param usuario El nombre de usuario para el inicio de sesión.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña asociada al usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPass() {
        return contrasena;
    }

    /**
     * Establece la contraseña asociada al usuario.
     * 
     * @param contraseña La contraseña del usuario.
     */
    public void setPass(String contraseña) {
        this.contrasena = contraseña;
    }

    /**
     * Obtiene el rol del usuario.
     * 
     * @return El rol del usuario (e.g., "administrador", "usuario").
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     * 
     * @param rol El rol del usuario (e.g., "administrador", "usuario").
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
