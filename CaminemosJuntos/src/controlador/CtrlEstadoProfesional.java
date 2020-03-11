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
import modelo.EstadoProfesional;

/**
 *
 * @author javier
 */
public class CtrlEstadoProfesional {
    Connection cnx;
    public CtrlEstadoProfesional(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(EstadoProfesional std)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO estadoProfesional (estadoProfesional) VALUES (?)");
            stmt.setString(1, std.getEstadoProfesional());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Estado", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(EstadoProfesional std)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE estadoProfesional SET estadoProfesional = ? WHERE idestadoProfesional = ?");
            stmt.setString(1, std.getEstadoProfesional());
            stmt.setInt(2, std.getIdestadoProfesional());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Estado", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM estadoProfesional WHERE idestadoProfesional = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Estado", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public EstadoProfesional leer(int id)
    {
        EstadoProfesional std = new EstadoProfesional();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT estadoProfesional FROM estadoProfesional WHERE idestadoProfesional = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                std.setIdestadoProfesional(id);
                std.setEstadoProfesional(rs.getString("estadoProfesional"));
            }
            else
                std = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Estado", "ERROR!!!...", ERROR_MESSAGE);
        }

        return std;
    }
    
    public EstadoProfesional leer(String std)
    {
        EstadoProfesional estado = new EstadoProfesional();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idestadoProfesional FROM estadoProfesional WHERE estadoProfesional = ?");
            stmt.setString(1, std);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                estado.setIdestadoProfesional(rs.getInt("idestadoProfesional"));
                estado.setEstadoProfesional(std);
            }
            else
                estado = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Estado", "ERROR!!!...", ERROR_MESSAGE);
        }

        return estado;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT estadoProfesional FROM estadoProfesional ORDER BY estadoProfesional");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("estadoProfesional"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Estados", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
