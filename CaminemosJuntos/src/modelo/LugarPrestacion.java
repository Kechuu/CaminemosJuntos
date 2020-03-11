/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author javier
 */
public class LugarPrestacion {
    private int idlugarPrestacion;
    private String nombre;
    private String Direccion;
    private String telefono;
    private boolean borrado;

    public int getIdlugarPrestacion() {
        return idlugarPrestacion;
    }

    public void setIdlugarPrestacion(int idlugarPrestacion) {
        this.idlugarPrestacion = idlugarPrestacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
    
}
