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
import modelo.LugarPrestacion;

/**
 *
 * @author javier
 */
public class CtrlLugarPrestacion {
    Connection cnx;
    public CtrlLugarPrestacion(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(LugarPrestacion lgr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO lugarPrestacion (nombre, direccion, telefono, borrado) VALUES (?, ?, ?, ?)");
            stmt.setString(1, lgr.getNombre());
            stmt.setString(2, lgr.getDireccion());
            stmt.setString(3, lgr.getTelefono());
            stmt.setBoolean(4, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Lugar de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(LugarPrestacion lgr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE lugarPrestacion SET nombre = ?, direccion = ?, telefono = ? WHERE idlugarPrestacion = ?");
            stmt.setString(1, lgr.getNombre());
            stmt.setString(2, lgr.getDireccion());
            stmt.setString(3, lgr.getTelefono());
            stmt.setInt(4, lgr.getIdlugarPrestacion());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Lugar de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE lugarPrestacion SET borrado = true WHERE idlugarPrestacion = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Lugar de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE lugarPrestacion SET borrado = false WHERE idlugarPrestacion = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Lugar de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public LugarPrestacion leer(int id)
    {
        LugarPrestacion lgr = new LugarPrestacion();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre, direccion, telefono, borrado FROM lugarPrestacion WHERE idlugarPrestacion = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lgr.setIdlugarPrestacion(id);
                lgr.setNombre(rs.getString("nombre"));
                lgr.setDireccion(rs.getString("direccion"));
                lgr.setTelefono(rs.getString("telefono"));
                lgr.setBorrado(rs.getBoolean("borrado"));
            }
            else
                lgr = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lgr;
    }
    
    public LugarPrestacion leer(String lgr)
    {
        LugarPrestacion lugar = new LugarPrestacion();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idlugarPrestacion, nombre, direccion, telefono, borrado FROM lugarPrestacion WHERE nombre = ?");
            stmt.setString(1, lgr);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lugar.setIdlugarPrestacion(rs.getInt("idlugarPrestacion"));
                lugar.setNombre(rs.getString("nombre"));
                lugar.setDireccion(rs.getString("direccion"));
                lugar.setTelefono(rs.getString("telefono"));
                lugar.setBorrado(rs.getBoolean("borrado"));
            }
            else
                lugar = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Lugar de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lugar;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre FROM lugarPrestacion WHERE borrado = false ORDER BY nombre");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Lugares de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
    
    public List<LugarPrestacion> leerTodosCompleto()
    {
        int i = 0;
        ResultSet rs;
        LugarPrestacion nodo;
        List<LugarPrestacion> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre, direccion FROM lugarPrestacion WHERE borrado = false ORDER BY nombre");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo = new LugarPrestacion();
                nodo.setNombre(rs.getString("nombre"));
                nodo.setDireccion(rs.getString("direccion"));
                lista.add(nodo);
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Lugares de Prestacion", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }    
}
