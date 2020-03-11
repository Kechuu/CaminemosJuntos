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
import modelo.Diagnostico;


/**
 *
 * @author javier
 */
public class CtrlDiagnostico {
    Connection cnx;
    public CtrlDiagnostico(Connection cnx)
    {
        this.cnx = cnx;
    }
    
    public void crear(Diagnostico dgnstc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Diagnostico (diagnostico, codigoDiagnostico, borrado) VALUES (?, ?, ?)");
            stmt.setString(1, dgnstc.getDiagnostico());
            stmt.setString(2, dgnstc.getCodigoDiagnostico());
            stmt.setBoolean(3, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Diagnostico", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Diagnostico dgnstc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Diagnostico SET diagnostico = ?, codigoDiagnostico = ? WHERE idDiagnostico = ?");
            stmt.setString(1, dgnstc.getDiagnostico());
            stmt.setString(2, dgnstc.getCodigoDiagnostico());
            stmt.setInt(3, dgnstc.getIdDiagnostico());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Diagnostico", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Diagnostico SET borrado = true WHERE idDiagnostico = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Diagnostico", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Diagnostico SET borrado = false WHERE idDiagnostico = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Diagnostico", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Diagnostico leer(int id)
    {
        Diagnostico dgnstc = new Diagnostico();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT diagnostico, codigoDiagnostico, borrado FROM Diagnostico WHERE idDiagnostico = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                dgnstc.setIdDiagnostico(id);
                dgnstc.setDiagnostico(rs.getString("diagnostico"));
                dgnstc.setCodigoDiagnostico(rs.getString("codigoDiagnostico"));
                dgnstc.setBorrado(rs.getBoolean("borrado"));
            }
            else
                dgnstc = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Diagnostico", "ERROR!!!...", ERROR_MESSAGE);
        }

        return dgnstc;
    }
    
    public Diagnostico leerConDiagnostico(String dgnstc)
    {
        Diagnostico diagnostico = new Diagnostico();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idDiagnostico, diagnostico, codigoDiagnostico, borrado FROM Diagnostico WHERE diagnostico = ?");
            stmt.setString(1, dgnstc);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                diagnostico.setIdDiagnostico(rs.getInt("idDiagnostico"));
                diagnostico.setDiagnostico(rs.getString("diagnostico"));
                diagnostico.setCodigoDiagnostico(rs.getString("codigoDiagnostico"));
                diagnostico.setBorrado(rs.getBoolean("borrado"));
            }
            else
                diagnostico = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Diagnostico", "ERROR!!!...", ERROR_MESSAGE);
        }

        return diagnostico;
    }

    public Diagnostico leerConCodigo(String cdg)
    {
        Diagnostico diagnostico = new Diagnostico();
        ResultSet rs = null;
            
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idDiagnostico, diagnostico, codigoDiagnostico, borrado FROM Diagnostico WHERE codigoDiagnostico = ?");
            stmt.setString(1, cdg);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                diagnostico.setIdDiagnostico(rs.getInt("idDiagnostico"));
                diagnostico.setDiagnostico(rs.getString("diagnostico"));
                diagnostico.setCodigoDiagnostico(rs.getString("codigoDiagnostico"));
                diagnostico.setBorrado(rs.getBoolean("borrado"));
            }
            else
                diagnostico = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Diagnostico", "ERROR!!!...", ERROR_MESSAGE);
        }

        return diagnostico;
    }

    public List<Diagnostico> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<Diagnostico> lista = new ArrayList();
        Diagnostico nodo;

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idDiagnostico, codigoDiagnostico, diagnostico, borrado FROM Diagnostico WHERE borrado = false ORDER BY diagnostico");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo = new Diagnostico();
                nodo.setIdDiagnostico(rs.getInt("idDiagnostico"));
                nodo.setCodigoDiagnostico(rs.getString("codigoDiagnostico"));
                nodo.setDiagnostico(rs.getString("diagnostico"));
                nodo.setBorrado(rs.getBoolean("borrado"));
                lista.add(nodo);
            }
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Diagnosticos", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
}
