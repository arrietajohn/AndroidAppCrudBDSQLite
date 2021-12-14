package co.gov.mintic.misiontic.ciclo4.modelo.datos;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String cedula;
    private String clave;
    private String nombre;
    private String email;

    public Usuario() {

    }

    public Usuario(String cedula, String clave, String nombre, String email) {
        this.cedula = cedula;
        this.clave = clave;
        this.nombre = nombre;
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
}
