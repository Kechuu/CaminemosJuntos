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
public class LugarDomicilio {
    private int idLugarDomicilio;
    private String nombre;
    private LugarDomicilio de;
    private int nivel;

    public int getIdLugarDomicilio() {
        return idLugarDomicilio;
    }

    public void setIdLugarDomicilio(int idLugarDomicilio) {
        this.idLugarDomicilio = idLugarDomicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LugarDomicilio getDe() {
        return de;
    }

    public void setDe(LugarDomicilio de) {
        this.de = de;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
