package upc.edu.pe.app_komatsusales.model;

/**
 * Created by Alicia on 25/09/2016.
 */

public class Usuario {

    private String usuario;
    private String password;

    public Usuario(String usuario, String password) {
        this.setUsuario(usuario);
        this.setPassword(password);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
