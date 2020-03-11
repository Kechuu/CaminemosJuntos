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
import modelo.Sala;

/**
 *
 * @author javier
 */
public class CtrlSala {
    Connection cnx;
    public CtrlSala(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(Sala sl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Sala (nombre, borrado) VALUES (?, ?)");
            stmt.setString(1, sl.getNombre());
            stmt.setBoolean(2, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nueva Sala", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Sala sl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Sala SET nombre = ? WHERE idSala = ?");
            stmt.setString(1, sl.getNombre());
            stmt.setInt(2, sl.getIdSala());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Sala", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Sala SET borrado = true WHERE idSala = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Sala", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Sala SET borrado = false WHERE idSala = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Sala", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Sala leer(int id)
    {
        Sala sl = new Sala();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre FROM Sala WHERE idSala = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                sl.setIdSala(id);
                sl.setNombre(rs.getString("nombre"));
            }
            else
                sl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Sala", "ERROR!!!...", ERROR_MESSAGE);
        }

        return sl;
    }
    
    public Sala leer(String sl)
    {
        Sala sala = new Sala();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idSala, nombre, borrado FROM Sala WHERE sala = ?");
            stmt.setString(1, sl);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                sala.setIdSala(rs.getInt("idSala"));
                sala.setNombre(rs.getString("nombre"));
                sala.setBorrado(rs.getBoolean("borrado"));
            }
            else
                sala = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Sala", "ERROR!!!...", ERROR_MESSAGE);
        }

        return sala;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombre FROM Sala WHERE borrado = false ORDER BY nombre");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombre"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Salas", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
