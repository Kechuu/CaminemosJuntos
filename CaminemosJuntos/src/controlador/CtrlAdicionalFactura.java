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
import modelo.AdicionalFactura;
import modelo.Factura;


/**
 *
 * @author javier
 */
public class CtrlAdicionalFactura {
    Connection cnx;
    CtrlFactura ctrlFactura;
    public CtrlAdicionalFactura(Connection cnx)
    {
        this.cnx = cnx;
        ctrlFactura = new CtrlFactura(cnx);
        
    }
    
    public void crear(AdicionalFactura dcnl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO AdicionalFactura (factura, detalle, importe) VALUES (?, ?, ?)");
            stmt.setInt(1, dcnl.getFactura().getIdFactura());
            stmt.setString(2, dcnl.getDetalle());
            stmt.setFloat(3, dcnl.getImporte());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR Adicional a la Factura", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(AdicionalFactura dcnl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE AdicionalFactura SET factura = ?, detalle = ?, importe = ? WHERE idAdicionalFactura = ?");
            stmt.setInt(1, dcnl.getFactura().getIdFactura());
            stmt.setString(2, dcnl.getDetalle());
            stmt.setFloat(3, dcnl.getImporte());
            stmt.setInt(7, dcnl.getIdAdicionalFactura());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Adicional de la Factura", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public AdicionalFactura leer(int id)
    {
        AdicionalFactura dcnl = new AdicionalFactura();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT factura, detalle, importe FROM AdicionalFactura WHERE idAdicionalFactura = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dcnl.setIdAdicionalFactura(id);
                dcnl.setFactura(ctrlFactura.leer(rs.getInt("factura")));
                dcnl.setDetalle(rs.getString("detalle"));
                dcnl.setImporte(rs.getFloat("importe"));
            }
            else
                dcnl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Adicional de la Factura", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dcnl;
    }

    public List<AdicionalFactura> leerTodosDe(int fctr)
    {
        List<AdicionalFactura> lista = new ArrayList();
        AdicionalFactura dcnl = new AdicionalFactura();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idAdcionalFactura, detalle, importe FROM AdicionalFactura WHERE factura = ?");
            stmt.setInt(1, fctr);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                dcnl.setIdAdicionalFactura(rs.getInt("idAdicionalFactura"));
                dcnl.setFactura(ctrlFactura.leer(rs.getInt(fctr)));
                dcnl.setDetalle(rs.getString("detalle"));
                dcnl.setImporte(rs.getFloat("importe"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Adicionales de la Factura", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

}
