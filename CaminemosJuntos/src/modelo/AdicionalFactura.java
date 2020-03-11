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
public class AdicionalFactura {
    private int idAdicionalFactura;
    private Factura factura;
    private String detalle;
    private float importe;

    public int getIdAdicionalFactura() {
        return idAdicionalFactura;
    }

    public void setIdAdicionalFactura(int idAdicionalFactura) {
        this.idAdicionalFactura = idAdicionalFactura;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
}
