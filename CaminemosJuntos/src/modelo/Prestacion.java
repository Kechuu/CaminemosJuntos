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
public class Prestacion {
    private int idPrestacion;
    private Alumno alumno;
    private LugarPrestacion lugar;
    private Nivel nivel;

    public int getIdPrestacion() {
        return idPrestacion;
    }

    public void setIdPrestacion(int idPrestacion) {
        this.idPrestacion = idPrestacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LugarPrestacion getLugar() {
        return lugar;
    }

    public void setLugar(LugarPrestacion lugar) {
        this.lugar = lugar;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
}
