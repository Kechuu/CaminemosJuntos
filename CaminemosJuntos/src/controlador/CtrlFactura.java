/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import modelo.Factura;


/**
 *
 * @author javier
 */
public class CtrlFactura {
    Connection cnx;
    CtrlObraSocial ctrlObraSocial;
    public CtrlFactura(Connection cnx)
    {
        this.cnx = cnx;
        ctrlObraSocial = new CtrlObraSocial(cnx);
        
    }
    
    public void crear(Factura fctr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Factura (numero, fechaFactura, obraSocial, estado, observacion, total) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, fctr.getNumero());
            stmt.setDate(2, fctr.getFechaFactura());
            stmt.setInt(3, fctr.getObraSocial().getIdObraSocial());
            stmt.setInt(4, 0);
            stmt.setString(5, fctr.getObservacion());
            stmt.setFloat(6, fctr.getTotal());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nueva Factura", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Factura fctr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Factura SET numero = ?, fechaFactura = ?, obraSocial = ?, estado = ?, observacion = ?, total = ? WHERE idFactura = ?");
            stmt.setString(1, fctr.getNumero());
            stmt.setDate(2, fctr.getFechaFactura());
            stmt.setInt(3, fctr.getObraSocial().getIdObraSocial());
            stmt.setInt(4, 0);
            stmt.setString(5, fctr.getObservacion());
            stmt.setFloat(6, fctr.getTotal());
            stmt.setInt(7, fctr.getIdFactura());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Factura", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Factura leer(int brScl, String nmr)
    {
        Factura fctr = new Factura();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idFactura, fechaFactura, estado, observacion, total FROM Factura WHERE obraSocial = ? AND numero = ?");
            stmt.setInt(1, brScl);
            stmt.setString(2, nmr);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                fctr.setIdFactura(rs.getInt("idFactura"));
                fctr.setNumero(nmr);
                fctr.setFechaFactura(rs.getDate("fechaFactura"));
                fctr.setObraSocial(ctrlObraSocial.leer(brScl));
                fctr.setEstado(rs.getInt("estado"));
                fctr.setObservacion(rs.getString("observacion"));
                fctr.setTotal(rs.getFloat("total"));
            }
            else
                fctr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Factura", "ERROR!!!...", ERROR_MESSAGE);
        }

        return fctr;
    }

    public Factura leer(int id)
    {
        Factura fctr = new Factura();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT numero, fechaFactura, obraSocial, estado, observacion, total FROM Factura WHERE idFactura = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                fctr.setIdFactura(id);
                fctr.setNumero(rs.getString("numero"));
                fctr.setFechaFactura(rs.getDate("fechaFactura"));
                fctr.setObraSocial(ctrlObraSocial.leer(rs.getInt("obraSocial")));
                fctr.setEstado(rs.getInt("estado"));
                fctr.setObservacion(rs.getString("observacion"));
                fctr.setTotal(rs.getFloat("total"));
            }
            else
                fctr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Factura", "ERROR!!!...", ERROR_MESSAGE);
        }

        return fctr;
    }

    public List<Factura> leerTodasDe(int brScl)
    {
        List<Factura> lista = new ArrayList();
        Factura fctr = new Factura();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idFactura, numero, fechaFactura, estado, observacion, total FROM Actividad WHERE obraSocial = ?");
            stmt.setInt(1, brScl);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                fctr.setIdFactura(rs.getInt("idFactura"));
                fctr.setNumero(rs.getString("numero"));
                fctr.setFechaFactura(rs.getDate("fechaFactura"));
                fctr.setObraSocial(ctrlObraSocial.leer(brScl));
                fctr.setEstado(rs.getInt("estado"));
                fctr.setObservacion(rs.getString("observacion"));
                fctr.setTotal(rs.getFloat("total"));
                
                lista.add(fctr);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Facturas de la Obra Social", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

}
