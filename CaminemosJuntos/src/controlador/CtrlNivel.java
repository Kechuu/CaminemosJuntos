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
import modelo.Nivel;

/**
 *
 * @author javier
 */
public class CtrlNivel {
    Connection cnx;
    public CtrlNivel(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(Nivel nvl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Nivel (nivel, borrado) VALUES (?, ?)");
            stmt.setString(1, nvl.getNivel());
            stmt.setBoolean(2, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Nivel", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Nivel nvl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Nivel SET nivel = ? WHERE idNivel = ?");
            stmt.setString(1, nvl.getNivel());
            stmt.setInt(2, nvl.getIdNivel());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Nivel", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Nivel SET borrado = true WHERE idNivel = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Nivel", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Nivel SET borrado = false WHERE idNivel = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Nivel", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Nivel leer(int id)
    {
        Nivel nvl = new Nivel();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nivel FROM Nivel WHERE idNivel = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                nvl.setIdNivel(id);
                nvl.setNivel(rs.getString("nivel"));
            }
            else
                nvl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Nivel", "ERROR!!!...", ERROR_MESSAGE);
        }

        return nvl;
    }
    
    public Nivel leer(String nvl)
    {
        Nivel nivel = new Nivel();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idNivel, nivel, borrado FROM Nivel WHERE nivel = ?");
            stmt.setString(1, nvl);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                nivel.setIdNivel(rs.getInt("idNivel"));
                nivel.setNivel(rs.getString("nivel"));
                nivel.setBorrado(rs.getBoolean("borrado"));
            }
            else
                nivel = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Nivel", "ERROR!!!...", ERROR_MESSAGE);
        }

        return nivel;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nivel FROM Nivel WHERE borrado = false ORDER BY nivel");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nivel"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Niveles", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
