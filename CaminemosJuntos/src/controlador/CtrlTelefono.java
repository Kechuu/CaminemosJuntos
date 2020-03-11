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
import modelo.Telefono;


/**
 *
 * @author javier
 */
public class CtrlTelefono {
    Connection cnx;
    CtrlPersona ctrlPersona;
//    CtrlTituloProfesional ctrlTituloProfesional;
//    CtrlEstadoProfesional ctrlEstadoProfesional;
    CtrlTipoTelefono ctrlTipoTelefono;
    
    public CtrlTelefono(Connection cnx)
    {
        this.cnx = cnx;
        ctrlTipoTelefono = new CtrlTipoTelefono(cnx);
        ctrlPersona = new CtrlPersona(cnx);
        
    }
    
    public void crear(Telefono tlfn)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Telefono (codigoArea, numeroTelefono, persona, tipoTelefono) VALUES (?, ?, ?, ?)");
            stmt.setString(1, tlfn.getCodigoArea());
            stmt.setString(2, tlfn.getNumeroTelefono());
            stmt.setInt(3, tlfn.getPersona().getIdPersona());
            stmt.setInt(4, tlfn.getTipoTelefono().getIdtipoTelefono());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al Grabar telefono de la Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Telefono tlfn)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Telefono SET codigoArea = ?, numeroTelefono = ?, persona = ?, tipoTelefono = ? WHERE idTelefono = ?");
            stmt.setString(1, tlfn.getCodigoArea());
            stmt.setString(2, tlfn.getNumeroTelefono());
            stmt.setInt(3, tlfn.getPersona().getIdPersona());
            stmt.setInt(4, tlfn.getTipoTelefono().getIdtipoTelefono());
            stmt.setInt(7, tlfn.getIdTelefono());
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR telefono de la Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM Telefono WHERE idTelefono = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Telefono", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public List<Telefono> leerTelefonosDe(int id)
    {
        List<Telefono> lista = new ArrayList();
        Telefono telefono;
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idTelefono, codigoArea, numeroTelefono, tipoTelefono, persona FROM Telefono WHERE persona = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                telefono = new Telefono();
                telefono.setIdTelefono(rs.getInt("idTelefono"));
                telefono.setCodigoArea(rs.getString("codigoArea"));
                telefono.setNumeroTelefono(rs.getString("numeroTelefono"));
                telefono.setPersona(ctrlPersona.leer(rs.getInt("persona")));
                telefono.setTipoTelefono(ctrlTipoTelefono.leer(rs.getInt("tipoTelefono")));
                
                lista.add(telefono);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Telefonos de la Persona", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }
    

    public List<Telefono> leerTodos()
    {
        List<Telefono> lista = new ArrayList();
        Telefono telefono = new Telefono();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idTelefono, codigoArea, numeroTelefono, persona, tipoTelefono FROM Telefono");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                telefono.setIdTelefono(rs.getInt("idTelefono"));
                telefono.setCodigoArea(rs.getString("codigoArea"));
                telefono.setNumeroTelefono(rs.getString("numeroTelefono"));
                telefono.setPersona(ctrlPersona.leer(rs.getInt("persona")));
                telefono.setTipoTelefono(ctrlTipoTelefono.leer(rs.getInt("tipoTelefono")));
                
                lista.add(telefono);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Agenda Telefónica", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

}
