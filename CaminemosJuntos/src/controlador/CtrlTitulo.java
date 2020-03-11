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
import modelo.Titulo;

/**
 *
 * @author javier
 */
public class CtrlTitulo {
    Connection cnx;
    public CtrlTitulo(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(Titulo ttl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Titulo (titulo, borrado) VALUES (?, ?)");
            stmt.setString(1, ttl.getTitulo());
            stmt.setBoolean(2, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Titulo", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Titulo ttl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Titulo SET titulo = ? WHERE idTitulo = ?");
            stmt.setString(1, ttl.getTitulo());
            stmt.setInt(2, ttl.getIdTitulo());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Titulo", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Titulo SET borrado = true WHERE idTitulo = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Titulo", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Titulo SET borrado = false WHERE idTitulo = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Titulo", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Titulo leer(int id)
    {
        Titulo ttl = new Titulo();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT titulo FROM Titulo WHERE idTitulo = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                ttl.setIdTitulo(id);
                ttl.setTitulo(rs.getString("titulo"));
            }
            else
                ttl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Titulo", "ERROR!!!...", ERROR_MESSAGE);
        }

        return ttl;
    }
    
    public Titulo leer(String ttl)
    {
        Titulo titulo = new Titulo();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idTitulo, titulo, borrado FROM Titulo WHERE titulo = ?");
            stmt.setString(1, ttl);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                titulo.setIdTitulo(rs.getInt("idTitulo"));
                titulo.setTitulo(rs.getString("titulo"));
                titulo.setBorrado(rs.getBoolean("borrado"));
            }
            else
                titulo = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Titulo", "ERROR!!!...", ERROR_MESSAGE);
        }

        return titulo;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT titulo FROM Titulo WHERE borrado = false ORDER BY titulo");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("titulo"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Titulos", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
