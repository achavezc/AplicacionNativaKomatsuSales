package upc.edu.pe.app_komatsusales.model;

/**
 * Created by Alicia on 26/09/2016.
 */

public class Personal {

    private String Apellidos;
    private String Cargo;
    private String DNI;
    private String Email;
    private String IdCargo;
    private String IdPersonal;
    private String Nombre;
    private String Sexo;
    private String Telefono;


    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(String idCargo) {
        IdCargo = idCargo;
    }

    public String getIdPersonal() {
        return IdPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        IdPersonal = idPersonal;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
