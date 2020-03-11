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
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import modelo.RangoHora;

/**
 *
 * @author javier
 */
public class CtrlRangoHora {
    Connection cnx;
    public CtrlRangoHora(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(RangoHora rng)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO rangoHora (desde, hasta, borrado) VALUES (?, ?, ?)");
            stmt.setTime(1, rng.getDesde());
            stmt.setTime(2, rng.getHasta());
            stmt.setBoolean(3, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Horario", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(RangoHora rng)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE rangoHora SET desde = ?, hasta = ? WHERE idrangoHora = ?");
            stmt.setTime(1, rng.getDesde());
            stmt.setTime(2, rng.getHasta());
            stmt.setInt(3, rng.getIdrangoHora());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Horario", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE rangoHora SET borrado = true WHERE idrangoHora = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Horario", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE rangoHora SET borrado = false WHERE idrangoHora = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Horario", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public RangoHora leer(int id)
    {
        RangoHora rng = new RangoHora();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT desde, hasta, borrado FROM rangoHora WHERE idrangoHora = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                rng.setIdrangoHora(id);
                rng.setDesde(rs.getTime("desde"));
                rng.setHasta(rs.getTime("hasta"));
                rng.setBorrado(rs.getBoolean("borrado"));
            }
            else
                rng = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Horario", "ERROR!!!...", ERROR_MESSAGE);
        }

        return rng;
    }
    
    public RangoHora leer(Time dsd, Time hst)
    {
        RangoHora rango = new RangoHora();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idrangoHora, borrado FROM rangoHora WHERE desde = ? AND hasta = ?");
            stmt.setTime(1, dsd);
            stmt.setTime(2, hst);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                rango.setIdrangoHora(rs.getInt("idrangoHora"));
                rango.setDesde(rs.getTime("desde"));
                rango.setHasta(rs.getTime("hasta"));
                rango.setBorrado(rs.getBoolean("borrado"));
            }
            else
                rango = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Horario", "ERROR!!!...", ERROR_MESSAGE);
        }

        return rango;
    }

    public List<Time[]> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<Time[]> lista = new ArrayList();
        Time[] nodo = new Time[2];
        
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT desde, hasta FROM rangoHora WHERE borrado = false ORDER BY desde");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo[0] = rs.getTime("desde");
                nodo[1] = rs.getTime("hasta");
                lista.add(nodo);
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Horarios", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
