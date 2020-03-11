/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author javier
 */
public class Profesional {
    private int idProfesional;
    private Persona persona;
    private Date carnetSanitario;
    private Date malaPraxis;
    private Date superIntendencia;
    private EstadoProfesional estado;
    private List<String[]> titulos;
    private boolean borrado;

    public List<String[]> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<String[]> titulos) {
        this.titulos = titulos;
    }

    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getCarnetSanitario() {
        return carnetSanitario;
    }

    public void setCarnetSanitario(Date carnetSanitario) {
        this.carnetSanitario = carnetSanitario;
    }

    public Date getMalaPraxis() {
        return malaPraxis;
    }

    public void setMalaPraxis(Date malaPraxis) {
        this.malaPraxis = malaPraxis;
    }

    public Date getSuperIntendencia() {
        return superIntendencia;
    }

    public void setSuperIntendencia(Date superIntendencia) {
        this.superIntendencia = superIntendencia;
    }

    public EstadoProfesional getEstado() {
        return estado;
    }

    public void setEstado(EstadoProfesional estado) {
        this.estado = estado;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
    
    
}
