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
public class AsistenciaProfesional {
    private int idAsistenciaProfesional;
    private Date ingreso;
    private Date salida;
    private LugarPrestacion lugar;
    private Profesional profesional;

    public int getIdAsistenciaProfesional() {
        return idAsistenciaProfesional;
    }

    public void setIdAsistenciaProfesional(int idAsistenciaProfesional) {
        this.idAsistenciaProfesional = idAsistenciaProfesional;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public LugarPrestacion getLugar() {
        return lugar;
    }

    public void setLugar(LugarPrestacion lugar) {
        this.lugar = lugar;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
}
