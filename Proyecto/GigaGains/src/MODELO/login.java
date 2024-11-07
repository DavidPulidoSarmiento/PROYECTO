package MODELO;

public class login {
    private String usuario;
    private String contraseña;
    private String rol; // Nuevo campo para almacenar el rol

    public login() {
    }

    public login(String usuario, String contraseña, String rol) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return contraseña;
    }

    public void setPass(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() { // Nuevo getter para el rol
        return rol;
    }

    public void setRol(String rol) { // Nuevo setter para el rol
        this.rol = rol;
    }
}
