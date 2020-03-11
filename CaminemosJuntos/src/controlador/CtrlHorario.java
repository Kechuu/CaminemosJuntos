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
import modelo.Horario;

/**
 *
 * @author javier
 */
public class CtrlHorario {
    Connection cnx;
    CtrlProfesional ctrlProfesional;
    CtrlAlumno ctrlAlumno;
    CtrlActividad ctrlActividad;
    CtrlRangoHora ctrlRangoHora;
    CtrlSala ctrlSala;
    
    
    public CtrlHorario(Connection cnx)
    {
        this.cnx = cnx;
        ctrlActividad = new CtrlActividad(cnx);
        ctrlAlumno = new CtrlAlumno(cnx);
        ctrlProfesional = new CtrlProfesional(cnx);
        ctrlRangoHora = new CtrlRangoHora(cnx);
        ctrlSala = new CtrlSala(cnx);
    }
    
    public void crear(Horario hrr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Horario (alumno, profesional, actividad, dia, rangoHora, sala, borrado) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, hrr.getAlumno().getIdAlumno());
            stmt.setInt(2, hrr.getProfesional().getIdProfesional());
            stmt.setInt(3, hrr.getActividad().getIdActividad());
            stmt.setInt(4, hrr.getDia());
            stmt.setInt(5, hrr.getRangoHora().getIdrangoHora());
            stmt.setInt(6, hrr.getSala().getIdSala());
            stmt.setBoolean(7, false);
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al Grabar Horario del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Horario hrr)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Horario SET alumno = ?, profesional = ?, actividad = ?, dia = ?, rangoHora = ?, sala = ?, borrado = ? WHERE idHorario = ?");
            stmt.setInt(1, hrr.getAlumno().getIdAlumno());
            stmt.setInt(2, hrr.getProfesional().getIdProfesional());
            stmt.setInt(3, hrr.getActividad().getIdActividad());
            stmt.setInt(4, hrr.getDia());
            stmt.setInt(5, hrr.getRangoHora().getIdrangoHora());
            stmt.setInt(6, hrr.getSala().getIdSala());
            stmt.setBoolean(7, false);
            stmt.setInt(8, hrr.getIdHorario());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Horario del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Horario SET borrado = true WHERE idHorario = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Horario del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Horario SET borrado = false WHERE idHorario = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Horario del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public List<Horario> leerTodos()
    {
        List<Horario> lista = new ArrayList() ;
        Horario nodo = new Horario();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idHorario, alumno, profesional, actividad, dia, rangoHora, sala FROM Horario WHERE borrado = false");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo.setIdHorario(rs.getInt("idHorario"));
                nodo.setAlumno(ctrlAlumno.leer(rs.getInt("alumno")));
                nodo.setProfesional(ctrlProfesional.leer(rs.getInt("profesional")));
                nodo.setActividad(ctrlActividad.leer(rs.getInt("actividad")));
                nodo.setDia(rs.getInt("dia"));
                nodo.setRangoHora(ctrlRangoHora.leer(rs.getInt("rangoHora")));
                nodo.setSala(ctrlSala.leer(rs.getInt("sala")));
                nodo.setBorrado(false);
                
                lista.add(nodo);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER grilla de horarios", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }

    public List<Horario> alumno(int id)
    {
        List<Horario> lista = new ArrayList() ;
        Horario nodo = new Horario();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idHorario, alumno, profesional, actividad, dia, rangoHora, sala FROM Horario WHERE alumno = ? AND borrado = false");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo.setIdHorario(rs.getInt("idHorario"));
                nodo.setAlumno(ctrlAlumno.leer(rs.getInt("alumno")));
                nodo.setProfesional(ctrlProfesional.leer(rs.getInt("profesional")));
                nodo.setActividad(ctrlActividad.leer(rs.getInt("actividad")));
                nodo.setDia(rs.getInt("dia"));
                nodo.setRangoHora(ctrlRangoHora.leer(rs.getInt("rangoHora")));
                nodo.setSala(ctrlSala.leer(rs.getInt("sala")));
                nodo.setBorrado(false);
                
                lista.add(nodo);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Horarios del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }    
}