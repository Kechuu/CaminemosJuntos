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
import modelo.TipoDocumento;

/**
 *
 * @author javier
 */
public class CtrlTipoDocumento {
    Connection cnx;
    public CtrlTipoDocumento(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(TipoDocumento tp)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO tipoDocumento (tipoDocumento, nombreCorto, borrado) VALUES (?, ?, ?)");
            stmt.setString(1, tp.getTipoDocumento());
            stmt.setString(2, tp.getNombreCorto());
            stmt.setBoolean(3, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Tipo de Documento", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(TipoDocumento tp)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE tipoDocumento SET tipoDocumento = ?, nombreCorto = ?, borrado = ? WHERE idtipoDocumento = ?");
            stmt.setString(1, tp.getTipoDocumento());
            stmt.setString(2, tp.getNombreCorto());
            stmt.setBoolean(3, false);
            stmt.setInt(4, tp.getIdtipoDocumento());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Tipo de Documento", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE tipoDocumento SET borrado = true WHERE idtipoDocumento = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Tipo de Documento", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE tipoDocumento SET borrado = false WHERE idtipoDocumento = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Tipo de Documento", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public TipoDocumento leer(int id)
    {
        TipoDocumento tp = new TipoDocumento();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT tipoDocumento, nombreCorto, borrado FROM tipoDocumento WHERE idtipoDocumento = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                tp.setIdtipoDocumento(id);
                tp.setTipoDocumento(rs.getString("tipoDocumento"));
                tp.setNombreCorto(rs.getString("nombreCorto"));
                tp.setBorrado(rs.getBoolean("borrado"));
            }
            else
                tp = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Tipo de Documento", "ERROR!!!...", ERROR_MESSAGE);
        }

        return tp;
    }
    
    public TipoDocumento leer(String nmbrCrt)
    {
        TipoDocumento tp = new TipoDocumento();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idtipoDocumento, tipoDocumento, borrado FROM tipoDocumento WHERE nombreCorto = ?");
            stmt.setString(1, nmbrCrt);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                tp.setIdtipoDocumento(rs.getInt("idtipoDocumento"));
                tp.setTipoDocumento(rs.getString("tipoDocumento"));
                tp.setNombreCorto(nmbrCrt);
                tp.setBorrado(rs.getBoolean("borrado"));
            }
            else
                tp = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Tipo de Documento", "ERROR!!!...", ERROR_MESSAGE);
        }

        return tp;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombreCorto FROM tipoDocumento WHERE borrado = false ORDER BY nombreCorto");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombreCorto"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Tipos de Documentos", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
    
    public List<TipoDocumento> leerTodosCompleto()
    {
        int i = 0;
        ResultSet rs;
        TipoDocumento nodo;
        List<TipoDocumento> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombreCorto, tipoDocumento FROM tipoDocumento WHERE borrado = false ORDER BY nombreCorto");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo = new TipoDocumento();
                nodo.setNombreCorto(rs.getString("nombreCorto"));
                nodo.setTipoDocumento(rs.getString("tipoDocumento"));
                lista.add(nodo);
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Tipos de Documentos", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
