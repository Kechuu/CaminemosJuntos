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
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import modelo.Prestacion;

/**
 *
 * @author javier
 */
public class CtrlPrestacion {
    Connection cnx;
    CtrlLugarPrestacion ctrlLugar;
    CtrlAlumno ctrlAlumno;
    CtrlNivel ctrlNivel;
    
    public CtrlPrestacion(Connection cnx)
    {
        this.cnx = cnx;
        ctrlLugar = new CtrlLugarPrestacion(cnx);
        ctrlAlumno = new CtrlAlumno(cnx);
        ctrlNivel = new CtrlNivel(cnx);
    }
    
    public void crear(Prestacion prstcn)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Prestacion (alumno, lugar, nivel) VALUES (?, ?, ?)");
            stmt.setInt(1, prstcn.getAlumno().getIdAlumno());
            stmt.setInt(2, prstcn.getLugar().getIdlugarPrestacion());
            stmt.setInt(3, prstcn.getNivel().getIdNivel());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al Grabar Prestación del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Prestacion prstcn)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Prestacion SET alumno = ?, lugar = ?, nivel = ? WHERE idPrestacion = ?");
            stmt.setInt(1, prstcn.getAlumno().getIdAlumno());
            stmt.setInt(2, prstcn.getLugar().getIdlugarPrestacion());
            stmt.setInt(3, prstcn.getNivel().getIdNivel());
            stmt.setInt(4, prstcn.getIdPrestacion());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Integración del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM Prestacion WHERE idPrestacion = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Integración del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Prestacion leerPrestacionDe(int idLmn)
    {
        Prestacion prestacion = new Prestacion() ;
        int[] nodo = new int[2];
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT lugar, nivel FROM Prestacion WHERE alumno = ?");
            stmt.setInt(1, idLmn);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                prestacion.setAlumno(ctrlAlumno.leer(idLmn)); 
                prestacion.setLugar(ctrlLugar.leer(rs.getInt("lugar")));
                prestacion.setNivel(ctrlNivel.leer(rs.getInt("nivel")));
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Integracion del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }

        return prestacion;
    }
}