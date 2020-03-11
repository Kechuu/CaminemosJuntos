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
import modelo.AlumnoDiagnostico;
import modelo.Diagnostico;

/**
 *
 * @author javier
 */
public class CtrlAlumnoDiagnostico {
    Connection cnx;
    CtrlDiagnostico ctrlDiagnostico;
    CtrlAlumno ctrlAlumno;
    
    public CtrlAlumnoDiagnostico(Connection cnx)
    {
        this.cnx = cnx;
        ctrlDiagnostico = new CtrlDiagnostico(cnx);
        ctrlAlumno = new CtrlAlumno(cnx);
    }
    
    public void crear(AlumnoDiagnostico dgnstc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO AlumnoDiagnostico (alumno, diagnostico) VALUES (?, ?)");
            stmt.setInt(1, dgnstc.getAlumno().getIdAlumno());
            stmt.setInt(2, dgnstc.getDiagnostico().getIdDiagnostico());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al Grabar Diagnóstico del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(AlumnoDiagnostico dgnstc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE AlumnoDiagnostico SET alumno = ?, diagnostico = ? WHERE idAlumnoDiagnostico = ?");
            stmt.setInt(1, dgnstc.getAlumno().getIdAlumno());
            stmt.setInt(2, dgnstc.getDiagnostico().getIdDiagnostico());
            stmt.setInt(3, dgnstc.getIdAlumnoDiagnostico());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Diagnóstico del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM AlumnoDiagnostico WHERE idAlumnoDiagnostico = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Diagnostico del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public List<Diagnostico> alumno(int idLmn)
    {
        List<Diagnostico> lista = new ArrayList() ;
        Diagnostico diagnostico;
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT diagnostico FROM AlumnoDiagnostico WHERE alumno = ?");
            stmt.setInt(1, idLmn);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                diagnostico = new Diagnostico();
                diagnostico.setIdDiagnostico(ctrlDiagnostico.leer(rs.getInt("diagnostico")).getIdDiagnostico());
                diagnostico.setBorrado(ctrlDiagnostico.leer(rs.getInt("diagnostico")).isBorrado());
                diagnostico.setDiagnostico(ctrlDiagnostico.leer(rs.getInt("diagnostico")).getDiagnostico());
                diagnostico.setCodigoDiagnostico(ctrlDiagnostico.leer(rs.getInt("diagnostico")).getCodigoDiagnostico());
                
                lista.add(diagnostico);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Diagnosticos del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }
}