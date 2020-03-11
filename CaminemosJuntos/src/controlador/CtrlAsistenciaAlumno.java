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
import modelo.AsistenciaAlumno;
import modelo.Horario;

/**
 *
 * @author javier
 */
public class CtrlAsistenciaAlumno {
    Connection cnx;
    CtrlAlumno ctrlAlumno;
    CtrlActividad ctrlActividad;
    
    
    public CtrlAsistenciaAlumno(Connection cnx)
    {
        this.cnx = cnx;
        ctrlActividad = new CtrlActividad(cnx);
        ctrlAlumno = new CtrlAlumno(cnx);
    }
    
    public void crear(AsistenciaAlumno sstnc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO asistenciaAlumno (alumno, actividad, ingreso, salida) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, sstnc.getAlumno().getIdAlumno());
            stmt.setInt(2, sstnc.getActividad().getIdActividad());
            stmt.setDate(3, sstnc.getIngreso());
            stmt.setDate(4, sstnc.getSalida());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al Grabar Asistencia del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(AsistenciaAlumno sstnc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE asistenciaAlumno SET alumno = ?, actividad = ?, ingreso = ?, salida = ? WHERE idasistenciaAlumno = ?");
            stmt.setInt(1, sstnc.getAlumno().getIdAlumno());
            stmt.setInt(2, sstnc.getActividad().getIdActividad());
            stmt.setDate(3, sstnc.getIngreso());
            stmt.setDate(4, sstnc.getSalida());
            stmt.setInt(5, sstnc.getIdasistenciaAlumno());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Asistencia del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM asistenciaAlumno WHERE idasistenciaAlumno = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Asistencia del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public List<AsistenciaAlumno> alumno(int id)
    {
        List<AsistenciaAlumno> lista = new ArrayList() ;
        AsistenciaAlumno nodo = new AsistenciaAlumno();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idasistenciaAlumno, alumno, actividad, ingreso, salida FROM asistenciaAlumno WHERE alumno = ? AND borrado = false");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo.setIdasistenciaAlumno(rs.getInt("idasistenciaAlumno"));
                nodo.setAlumno(ctrlAlumno.leer(rs.getInt("alumno")));
                nodo.setActividad(ctrlActividad.leer(rs.getInt("actividad")));
                nodo.setIngreso(rs.getDate("ingreso"));
                nodo.setSalida(rs.getDate("salida"));
                
                lista.add(nodo);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Asistencias del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }    
}