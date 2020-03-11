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
import modelo.ObraSocial;


/**
 *
 * @author javier
 */
public class CtrlObraSocial {
    Connection cnx;
    public CtrlObraSocial(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(ObraSocial brScl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO ObraSocial (nombreCompleto, nombreCorto, direccion, telefono, borrado) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, brScl.getNombreCompleto());
            stmt.setString(2, brScl.getNombreCorto());
            stmt.setString(3, brScl.getDireccion());
            stmt.setString(4, brScl.getTelefono());
            stmt.setBoolean(5, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nueva Obra Social", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(ObraSocial brScl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE ObraSocial SET nombreCompleto = ?, nombreCorto = ?, direccion = ?, telefono = ? WHERE idObraSocial = ?");
            stmt.setString(1, brScl.getNombreCompleto());
            stmt.setString(2, brScl.getNombreCorto());
            stmt.setString(3, brScl.getDireccion());
            stmt.setString(4, brScl.getTelefono());
            stmt.setInt(5, brScl.getIdObraSocial());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Obra Social", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE ObraSocial SET borrado = true WHERE idObraSocial = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Obra Social", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE ObraSocial SET borrado = false WHERE idObraSocial = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Obra Social", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public ObraSocial leer(int id)
    {
        ObraSocial brScl = new ObraSocial();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombreCompleto, nombreCorto, direccion, telefono, borrado FROM ObraSocial WHERE idObraSocial = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                brScl.setIdObraSocial(id);
                brScl.setNombreCompleto(rs.getString("nombreCompleto"));
                brScl.setNombreCorto(rs.getString("nombreCorto"));
                brScl.setDireccion(rs.getString("direccion"));
                brScl.setTelefono(rs.getString("telefono"));
                brScl.setBorrado(rs.getBoolean("borrado"));
            }
            else
                brScl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Obra Social", "ERROR!!!...", ERROR_MESSAGE);
        }

        return brScl;
    }
    
    public ObraSocial leer(String nmbr)
    {
        ObraSocial obraSocial = new ObraSocial();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idObraSocial, nombreCompleto, nombreCorto, direccion, telefono, borrado FROM ObraSocial WHERE nombreCorto = ?");
            stmt.setString(1, nmbr);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                obraSocial.setIdObraSocial(rs.getInt("idObraSocial"));
                obraSocial.setNombreCompleto(rs.getString("nombreCompleto"));
                obraSocial.setNombreCorto(rs.getString("nombreCorto"));
                obraSocial.setDireccion(rs.getString("direccion"));
                obraSocial.setTelefono(rs.getString("telefono"));
                obraSocial.setBorrado(rs.getBoolean("borrado"));
            }
            else
                obraSocial = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER ObraSocial", "ERROR!!!...", ERROR_MESSAGE);
        }

        return obraSocial;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombreCorto FROM ObraSocial WHERE borrado = false ORDER BY nombreCorto");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombreCorto"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Obras Sociales", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
    
    public List<ObraSocial> leerTodosCompleto()
    {
        ResultSet rs;
        ObraSocial nodo;
        List<ObraSocial> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombreCorto, nombreCompleto FROM ObraSocial WHERE borrado = false ORDER BY nombreCorto");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo = new ObraSocial();
                nodo.setNombreCorto(rs.getString("nombreCorto"));
                nodo.setNombreCompleto(rs.getString("nombreCompleto"));
                lista.add(nodo);
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Obras Sociales", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }

}
