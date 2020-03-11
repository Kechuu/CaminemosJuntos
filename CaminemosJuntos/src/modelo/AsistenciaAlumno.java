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
public class AsistenciaAlumno {
    private int idasistenciaAlumno;
    private Alumno alumno;
    private Actividad actividad;
    private Date ingreso;
    private Date salida;

    public int getIdasistenciaAlumno() {
        return idasistenciaAlumno;
    }

    public void setIdasistenciaAlumno(int idasistenciaAlumno) {
        this.idasistenciaAlumno = idasistenciaAlumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
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
    
}
