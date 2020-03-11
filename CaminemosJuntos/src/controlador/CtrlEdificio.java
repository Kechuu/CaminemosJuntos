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
import modelo.Edificio;

/**
 *
 * @author javier
 */
public class CtrlEdificio {
    Connection cnx;
    public CtrlEdificio(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(Edificio dfc)
    {
        ResultSet rs;
        PreparedStatement stmt = null;
        try
        {
            stmt = cnx.prepareStatement("INSERT INTO Edificio (piso, depto, torre) VALUES (?, ?, ?)");
            stmt.setString(1, dfc.getPiso());
            stmt.setString(2, dfc.getDepto());
            stmt.setString(3, dfc.getTorre());
            
            stmt.execute();

            rs = stmt.executeQuery("SELECT MAX(idEdificio) AS id FROM Edificio");
            rs.next();
            dfc.setIdEdificio(rs.getInt("id"));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Edificio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Edificio dfc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Edificio SET piso = ?, depto = ?, torre = ? WHERE idEdificio = ?");
            stmt.setString(1, dfc.getPiso());
            stmt.setString(2, dfc.getDepto());
            stmt.setString(3, dfc.getTorre());
            stmt.setInt(4, dfc.getIdEdificio());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Edificio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM Edificio WHERE idEdificio = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Edificio", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Edificio leer(int id)
    {
        Edificio dfc = new Edificio();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT piso, depto, torre FROM Edificio WHERE idEdificio = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dfc.setPiso(rs.getString("piso"));
                dfc.setDepto(rs.getString("piso"));
                dfc.setTorre(rs.getString("piso"));
                dfc.setIdEdificio(id);
            }
            else
                dfc = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Edificio", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dfc;
    }
    
    public Edificio leer(String ps, String dpt, String trr)
    {
        Edificio edificio = new Edificio();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idEdificio FROM Edificio WHERE piso = ? AND depto = ? AND torre = ?");
            stmt.setString(1, ps);
            stmt.setString(2, dpt);
            stmt.setString(3, trr);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                edificio.setPiso(ps);
                edificio.setDepto(dpt);
                edificio.setTorre(trr);
                edificio.setIdEdificio(rs.getInt("idEdificio"));
            }
            else
                edificio = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Puesto", "ERROR!!!...", ERROR_MESSAGE);
        }

        return edificio;
    }
}