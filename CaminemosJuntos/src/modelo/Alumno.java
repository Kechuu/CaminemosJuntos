/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author javier
 */
public class Alumno {
    private int idAlumno;
    private Persona persona;
    private LugarDomicilio lugarNacimiento;
    private String legajo;
    private Persona tutor;
    private Persona madre;
    private Persona padre;
    private boolean integrado;
    private ObraSocial obraSocial;
    private Date vtoCUD;
    private boolean pension;
    private boolean asignacionUniversal;
    private boolean borrado;

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public Persona getTutor() {
        return tutor;
    }

    public void setTutor(Persona tutor) {
        this.tutor = tutor;
    }

    public Persona getMadre() {
        return madre;
    }

    public void setMadre(Persona madre) {
        this.madre = madre;
    }

    public Persona getPadre() {
        return padre;
    }

    public void setPadre(Persona padre) {
        this.padre = padre;
    }

    public boolean isIntegrado() {
        return integrado;
    }

    public void setIntegrado(boolean integrado) {
        this.integrado = integrado;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Date getVtoCUD() {
        return vtoCUD;
    }

    public void setVtoCUD(Date vtoCUD) {
        this.vtoCUD = vtoCUD;
    }

    public boolean isPension() {
        return pension;
    }

    public void setPension(boolean pension) {
        this.pension = pension;
    }

    public boolean isAsignacionUniversal() {
        return asignacionUniversal;
    }

    public void setAsignacionUniversal(boolean asignacionUniversal) {
        this.asignacionUniversal = asignacionUniversal;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    /**
     * @return the lugarNacimiento
     */
    public LugarDomicilio getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * @param lugarNacimiento the lugarNacimiento to set
     */
    public void setLugarNacimiento(LugarDomicilio lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }
}
