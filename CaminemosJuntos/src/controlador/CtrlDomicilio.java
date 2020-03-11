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
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import modelo.Domicilio;

/**
 *
 * @author javier
 */
public class CtrlDomicilio {
    Connection cnx;
    CtrlLugarDomicilio ctrlCalle;
    CtrlEdificio ctrlEdificio;
    
    public CtrlDomicilio(Connection cnx)
    {
        this.ctrlCalle = new CtrlLugarDomicilio(cnx);
        this.ctrlEdificio = new CtrlEdificio(cnx);
        this.cnx = cnx;
    }
    
    public void crear(Domicilio dmcl)
    {
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Domicilio (calle, numero, edificio, borrado) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, dmcl.getCalle().getIdLugarDomicilio());
            stmt.setString(2, dmcl.getNumero());
            if(dmcl.getEdificio()!=null)
                stmt.setInt(3, dmcl.getEdificio().getIdEdificio());
            else
                stmt.setInt(3, 0);
            stmt.setBoolean(4, false);
            
            stmt.execute();
            rs = stmt.executeQuery("SELECT MAX(idDomicilio) AS id FROM Domicilio");
            rs.next();
            dmcl.setIdDomicilio(rs.getInt("id"));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Domicilio dmcl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Domicilio SET calle = ?, numero = ?, edificio = ? WHERE idDomicilio = ?");
            stmt.setInt(1, dmcl.getCalle().getIdLugarDomicilio());
            stmt.setString(2, dmcl.getNumero());
            if(dmcl.getEdificio()!=null)
                stmt.setInt(3, dmcl.getEdificio().getIdEdificio());
            else
                stmt.setInt(3, 0);
            stmt.setInt(4, dmcl.getIdDomicilio());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Domicilio SET borrado = true WHERE idDomicilio = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Domicilio SET borrado = false WHERE idDomicilio = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Domicilio leer(int id)
    {
        Domicilio dmcl = new Domicilio();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT calle, numero, edificio, borrado FROM Domicilio WHERE idDomicilio = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dmcl.setIdDomicilio(id);
                dmcl.setCalle(ctrlCalle.leer(rs.getInt("calle")));
                dmcl.setNumero(rs.getString("numero"));
                dmcl.setBorrado(rs.getBoolean("borrado"));
            }
            else
                dmcl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dmcl;
    }
    
    public Domicilio leer(int cll, String nmr, int dfc)
    {
        Domicilio dmcl = new Domicilio();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idDomicilio, calle, numero, edificio, borrado FROM Domicilio WHERE calle = ? AND numero = ? AND edificio = ?");
            stmt.setInt(1, cll);
            stmt.setString(2, nmr);
            stmt.setInt(3, dfc);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dmcl.setIdDomicilio(rs.getInt("idDomicilio"));
                dmcl.setCalle(ctrlCalle.leer(rs.getInt("calle")));
                dmcl.setNumero(rs.getString("numero"));
                dmcl.setEdificio(ctrlEdificio.leer(rs.getInt("edificio")));
                dmcl.setBorrado(rs.getBoolean("borrado"));
            }
            else
                dmcl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Domicilio", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dmcl;
    }

}
