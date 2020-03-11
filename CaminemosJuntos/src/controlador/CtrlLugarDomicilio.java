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
import modelo.LugarDomicilio;

/**
 *
 * @author javier
 */
public class CtrlLugarDomicilio {
    Connection cnx;
    public CtrlLugarDomicilio(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(LugarDomicilio lgr)
    {
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO lugarDomicilio (nombre, nivel, de) VALUES (?, ?, ?)");
            stmt.setString(1, lgr.getNombre());
            stmt.setInt(2, lgr.getNivel());
            if(lgr.getDe() != null)
                stmt.setInt(3, lgr.getDe().getIdLugarDomicilio());
            else
                stmt.setInt(3, 0);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(LugarDomicilio lgr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE lugarDomicilio SET nombre = ?, de = ?, nivel = ? WHERE idLugarDomicilio = ?");
            stmt.setString(1, lgr.getNombre());
            if(lgr.getDe() != null)
                stmt.setInt(2, lgr.getDe().getIdLugarDomicilio());
            else
                stmt.setInt(2, 0);
            stmt.setInt(3, lgr.getNivel());
            stmt.setInt(4, lgr.getIdLugarDomicilio());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM lugarDomicilio WHERE idLugarDomicilio = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public LugarDomicilio leer(int id)
    {
        LugarDomicilio lgr = new LugarDomicilio();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre, de, nivel FROM lugarDomicilio WHERE idLugarDomicilio = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(rs.getString("nombre"));
                lgr.setDe(leer(rs.getInt("de")));
                lgr.setNivel(rs.getInt("nivel"));
                lgr.setIdLugarDomicilio(id);
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }
    
    public LugarDomicilio leer(String nmbr, int nvl)
    {
        LugarDomicilio lgr = new LugarDomicilio();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idLugarDomicilio, de FROM lugarDomicilio WHERE nombre = ? AND nivel = ?");
            stmt.setString(1, nmbr);
            stmt.setInt(2, nvl);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(nmbr);
                lgr.setDe(leer(rs.getInt("de")));
                lgr.setNivel(nvl);
                lgr.setIdLugarDomicilio(rs.getInt("idLugarDomicilio"));
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }
    
    public LugarDomicilio leer(String nmbr, int nvl, int d)
    {
        LugarDomicilio lgr = new LugarDomicilio();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idLugarDomicilio FROM lugarDomicilio WHERE nombre = ? AND nivel = ? AND de = ?");
            stmt.setString(1, nmbr);
            stmt.setInt(2, nvl);
            stmt.setInt(3, d);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setNombre(nmbr);
                lgr.setDe(leer(d));
                lgr.setNivel(nvl);
                lgr.setIdLugarDomicilio(rs.getInt("idLugarDomicilio"));
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }

    public List<String> leerTodos(int nvl)
    {
        List<String> lista = new ArrayList();
        ResultSet rs;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre FROM lugarDomicilio WHERE nivel = ? ORDER BY nombre");
            stmt.setInt(1, nvl);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugares", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

    public List<String> leerTodosDe(LugarDomicilio lgr)
    {
        List<String> lista = new ArrayList();
        ResultSet rs;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre FROM lugarDomicilio WHERE de = ?");
            stmt.setInt(1, lgr.getIdLugarDomicilio());

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugares", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }
    
}