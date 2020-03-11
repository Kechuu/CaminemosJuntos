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
import modelo.AlumnoActividad;

/**
 *
 * @author javier
 */
public class CtrlAlumnoActividad {
    Connection cnx;
    CtrlAlumno ctrlAlumno;
    CtrlActividad ctrlActividad;
    
    
    public CtrlAlumnoActividad(Connection cnx)
    {
        this.cnx = cnx;
        ctrlActividad = new CtrlActividad(cnx);
        ctrlAlumno = new CtrlAlumno(cnx);
    }
    
    public void crear(AlumnoActividad ctvdd)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO alumnoActividad (alumno, actividad, cantidad, fechaInscripcion, cicloLectivo) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, ctvdd.getAlumno().getIdAlumno());
            stmt.setInt(2, ctvdd.getActividad().getIdActividad());
            stmt.setInt(3, ctvdd.getCantidad());
            stmt.setDate(4, ctvdd.getFechaInscripcion());
            stmt.setInt(5, ctvdd.getCicloLectivo());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al Grabar Actividad del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(AlumnoActividad ctvdd)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE alumnoActividad SET alumno = ?, actividad = ?, cantidad = ?, fechaInscripcion = ?, cicloLectivo = ? WHERE idalumnoActividad = ?");
            stmt.setInt(1, ctvdd.getAlumno().getIdAlumno());
            stmt.setInt(2, ctvdd.getActividad().getIdActividad());
            stmt.setInt(3, ctvdd.getCantidad());
            stmt.setDate(4, ctvdd.getFechaInscripcion());
            stmt.setInt(5, ctvdd.getCicloLectivo());
            stmt.setInt(6, ctvdd.getIdAlumnoActividad());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Actividad del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM alumnoActividad WHERE idalumnoActividad = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Actividad del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public AlumnoActividad leer(int lmnCtvdd)
    {
        AlumnoActividad alumnoActividad = new AlumnoActividad();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idalumnoActividad, alumno, actividad, cantidad, fechaIngreso, cicloLectivo FROM alumnoActividad WHERE idalumnoActividad = ?");
            stmt.setInt(1, lmnCtvdd);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                alumnoActividad.setIdAlumnoActividad(rs.getInt("idalumnoActividad"));
                alumnoActividad.setAlumno(ctrlAlumno.leer(rs.getInt("alumno")));
                alumnoActividad.setActividad(ctrlActividad.leer(rs.getInt("actividad")));
                alumnoActividad.setCantidad(rs.getInt("cantidad"));
                alumnoActividad.setFechaInscripcion(rs.getDate("fechaIncripcion"));
                alumnoActividad.setCicloLectivo(rs.getInt("cicloLectivo"));
            }
            else
                alumnoActividad = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Actividad", "ERROR!!!...", ERROR_MESSAGE);
        }
        
        return alumnoActividad;
    }
    
    public List<AlumnoActividad> alumno(int id)
    {
        List<AlumnoActividad> lista = new ArrayList() ;
        AlumnoActividad nodo = new AlumnoActividad();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idalumnoActividad, alumno, actividad, cantidad, fechaIngreso, cicloLectivo FROM alumnoActividad WHERE alumno = ?");
            stmt.setInt(1, id);
            
            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo.setIdAlumnoActividad(rs.getInt("idalumnoActividad"));
                nodo.setAlumno(ctrlAlumno.leer(rs.getInt("alumno")));
                nodo.setActividad(ctrlActividad.leer(rs.getInt("actividad")));
                nodo.setCantidad(rs.getInt("cantidad"));
                nodo.setFechaInscripcion(rs.getDate("fechaIncripcion"));
                nodo.setCicloLectivo(rs.getInt("cicloLectivo"));
                
                lista.add(nodo);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Actividades del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }    
}