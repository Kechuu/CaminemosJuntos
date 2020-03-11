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
import modelo.TipoTelefono;

/**
 *
 * @author javier
 */
public class CtrlTipoTelefono {
    Connection cnx;
    public CtrlTipoTelefono(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(TipoTelefono tp)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO tipoTelefono (tipoTelefono) VALUES (?)");
            stmt.setString(1, tp.getTipoTelefono());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Tipo de Telefono", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(TipoTelefono tp)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE tipoTelefono SET tipoTelefono = ? WHERE idtipoTelefono = ?");
            stmt.setString(1, tp.getTipoTelefono());
            stmt.setInt(2, tp.getIdtipoTelefono());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Tipo de Telefono", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM tipoTelefono WHERE idtipoTelefono = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Tipo de Telefono", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public TipoTelefono leer(int id)
    {
        TipoTelefono tp = new TipoTelefono();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT tipoTelefono FROM tipoTelefono WHERE idtipoTelefono = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                tp.setIdtipoTelefono(id);
                tp.setTipoTelefono(rs.getString("tipoTelefono"));
            }
            else
                tp = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Tipo de Telefono", "ERROR!!!...", ERROR_MESSAGE);
        }

        return tp;
    }
    
    public TipoTelefono leer(String tp)
    {
        TipoTelefono tipo = new TipoTelefono();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idtipoTelefono FROM tipoTelefono WHERE tipoTelefono = ?");
            stmt.setString(1, tp);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                tipo.setIdtipoTelefono(rs.getInt("idtipoTelefono"));
                tipo.setTipoTelefono(tp);
            }
            else
                tipo = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Tipo de Telefono", "ERROR!!!...", ERROR_MESSAGE);
        }

        return tipo;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT tipoTelefono FROM tipoTelefono ORDER BY tipoTelefono");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("tipoTelefono"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Tipos de Telefono", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
