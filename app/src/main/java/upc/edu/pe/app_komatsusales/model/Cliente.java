package upc.edu.pe.app_komatsusales.model;

/**
 * Created by Alicia on 10/10/2016.
 */

public class Cliente {

    private String Descripcion;
    private String Direccion;
    private String Email;
    private String IdCliente;
    private String RazonSocial;
    private String Referencia;
    private String Ruc;
    private String Telefono;
    private String TipoCliente;
    private String TipoPago;

    public Cliente(String descripcion, String direccion, String email,
                   String idCliente, String razonSocial, String referencia,
                   String ruc, String telefono, String tipoCliente, String tipoPago) {
        this.Descripcion = descripcion;
        this.Direccion = direccion;
        this. Email = email;
        this.IdCliente = idCliente;
        this.RazonSocial = razonSocial;
        this.Referencia = referencia;
        this.Ruc = ruc;
        this.Telefono = telefono;
        this.TipoCliente = tipoCliente;
        this.TipoPago = tipoPago;
    }

    public Cliente(String razonSocial, String ruc){
        setRazonSocial(razonSocial);
        setRuc(ruc);
    }
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getTipoCliente() {
        return TipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        TipoCliente = tipoCliente;
    }

    public String getTipoPago() {
        return TipoPago;
    }

    public void setTipoPago(String tipoPago) {
        TipoPago = tipoPago;
    }
}
