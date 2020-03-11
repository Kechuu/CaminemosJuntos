/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Time;

/**
 *
 * @author javier
 */
public class RangoHora {
    private int idrangoHora;
    private Time desde;
    private Time hasta;
    private boolean borrado;

    public int getIdrangoHora() {
        return idrangoHora;
    }

    public void setIdrangoHora(int idrangoHora) {
        this.idrangoHora = idrangoHora;
    }

    public Time getDesde() {
        return desde;
    }

    public void setDesde(Time desde) {
        this.desde = desde;
    }

    public Time getHasta() {
        return hasta;
    }

    public void setHasta(Time hasta) {
        this.hasta = hasta;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
    
}
