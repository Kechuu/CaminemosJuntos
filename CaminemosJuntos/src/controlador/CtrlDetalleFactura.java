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
import modelo.DetalleFactura;


/**
 *
 * @author javier
 */
public class CtrlDetalleFactura {
    Connection cnx;
    CtrlFactura ctrlFactura;
    CtrlAlumnoActividad ctrlAlumnoActividad;
    
    public CtrlDetalleFactura(Connection cnx)
    {
        this.cnx = cnx;
        ctrlFactura = new CtrlFactura(cnx);
        ctrlAlumnoActividad = new CtrlAlumnoActividad(cnx);
        
    }
    
    public void crear(DetalleFactura dtll)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Factura (factura, mes, year, importe, alumnoActividad, fechaPago) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, dtll.getFactura().getIdFactura());
            stmt.setInt(2, dtll.getMes());
            stmt.setInt(3, dtll.getYear());
            stmt.setFloat(4, dtll.getImporte());
            stmt.setInt(5, dtll.getAlumnoActividad().getIdAlumnoActividad());
            stmt.setDate(6, dtll.getFechaPago());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR Detalle de Factura", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(DetalleFactura dtll)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE DetalleFactura SET factura = ?, mes = ?, year = ?, importe = ?, alumnoActividad = ?, fechaPago = ? WHERE idDetalleFactura = ?");
            stmt.setInt(1, dtll.getFactura().getIdFactura());
            stmt.setInt(2, dtll.getMes());
            stmt.setInt(3, dtll.getYear());
            stmt.setFloat(4, dtll.getImporte());
            stmt.setInt(5, dtll.getAlumnoActividad().getIdAlumnoActividad());
            stmt.setDate(6, dtll.getFechaPago());
            stmt.setInt(7, dtll.getIdDetalleFactura());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Detalle de Factura", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public DetalleFactura leer(int id)
    {
        DetalleFactura dtll = new DetalleFactura();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT factura, mes, year, importe, alumnoActividad, fechaPago, FROM DetalleFactura WHERE idDetalleFactura = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dtll.setIdDetalleFactura(id);
                dtll.setFactura(ctrlFactura.leer(rs.getInt("Factura")));
                dtll.setMes(rs.getInt("mes"));
                dtll.setYear(rs.getInt("year"));
                dtll.setImporte(rs.getFloat("importe"));
                dtll.setAlumnoActividad(ctrlAlumnoActividad.leer(rs.getInt("alumnoActividad")));
                dtll.setFechaPago(rs.getDate("fechaPago"));
            }
            else
                dtll = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Detalle de la Factura", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dtll;
    }

    public List<DetalleFactura> detalle(int fctr )
    {
        List<DetalleFactura> lista = new ArrayList();
        DetalleFactura dtll = new DetalleFactura();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idDetalleFactura, mes, year, importe, alumnoActividad, fechaPago, FROM DetalleFactura WHERE factura = ?");
            stmt.setInt(1, fctr);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                dtll.setIdDetalleFactura(rs.getInt("idDetalleFactura"));
                dtll.setFactura(ctrlFactura.leer(fctr));
                dtll.setMes(rs.getInt("mes"));
                dtll.setYear(rs.getInt("year"));
                dtll.setImporte(rs.getFloat("importe"));
                dtll.setAlumnoActividad(ctrlAlumnoActividad.leer(rs.getInt("alumnoActividad")));
                dtll.setFechaPago(rs.getDate("fechaPago"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Detalles de la Factura", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

}
