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
import modelo.Actividad;


/**
 *
 * @author javier
 */
public class CtrlActividad {
    Connection cnx;
    public CtrlActividad(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(Actividad ctvdd)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Actividad (actividad, codigoActividad, borrado) VALUES (?, ?, ?)");
            stmt.setString(1, ctvdd.getActividad());
            stmt.setString(2, ctvdd.getCodigoActividad());
            stmt.setBoolean(3, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nueva Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Actividad ctvdd)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Actividad SET actividad = ?, codigoActividad = ? WHERE idActividad = ?");
            stmt.setString(1, ctvdd.getActividad());
            stmt.setString(2, ctvdd.getCodigoActividad());
            stmt.setInt(3, ctvdd.getIdActividad());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Actividad SET borrado = true WHERE idActividad = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Actividad SET borrado = false WHERE idActividad = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Actividad leer(int id)
    {
        Actividad ctvdd = new Actividad();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT actividad, codigoActividad, borrado FROM Actividad WHERE idActividad = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                ctvdd.setIdActividad(id);
                ctvdd.setActividad(rs.getString("actividad"));
                ctvdd.setCodigoActividad(rs.getString("codigoActividad"));
                ctvdd.setBorrado(rs.getBoolean("borrado"));
            }
            else
                ctvdd = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }

        return ctvdd;
    }
    
    public Actividad leerConActividad(String ctvdd)
    {
        Actividad actividad = new Actividad();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idActividad, actividad, codigoActividad, borrado FROM Actividad WHERE actividad = ?");
            stmt.setString(1, ctvdd);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setActividad(rs.getString("actividad"));
                actividad.setCodigoActividad(rs.getString("codigoActividad"));
                actividad.setBorrado(rs.getBoolean("borrado"));
            }
            else
                actividad = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }

        return actividad;
    }

    public Actividad leerConCodigo(String cdg)
    {
        Actividad actividad = new Actividad();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idActividad, actividad, codigoActividad, borrado FROM Actividad WHERE codigoActividad = ?");
            stmt.setString(1, cdg);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setActividad(rs.getString("actividad"));
                actividad.setCodigoActividad(rs.getString("codigoActividad"));
                actividad.setBorrado(rs.getBoolean("borrado"));
            }
            else
                actividad = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }

        return actividad;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT actividad FROM Actividad WHERE borrado = false ORDER BY actividad");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("actividad"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Actividades", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
