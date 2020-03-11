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
import modelo.AsistenciaProfesional;
import modelo.TituloProfesional;

/**
 *
 * @author javier
 */
public class CtrlAsistenciaProfesional {
    Connection cnx;
    CtrlLugarPrestacion ctrlLugar;
    CtrlProfesional ctrlProfesional;
    
    public CtrlAsistenciaProfesional(Connection cnx)
    {
        this.cnx = cnx;
        ctrlLugar = new CtrlLugarPrestacion(cnx);
        ctrlProfesional = new CtrlProfesional(cnx);
    }
    
    public void crear(AsistenciaProfesional sstnc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO AsistenciaProfesional (ingreso, salida, lugar, profesional) VALUES (?, ?, ?, ?)");
            stmt.setDate(1, sstnc.getIngreso());
            stmt.setDate(2, sstnc.getSalida());
            stmt.setInt(3, sstnc.getLugar().getIdlugarPrestacion());
            stmt.setInt(4,sstnc.getProfesional().getIdProfesional());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al Grabar Asistencia del Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(AsistenciaProfesional sstnc)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE AsistenciaProfesional SET ingreso = ?, salida = ?, lugar = ?, profesional = ? WHERE idAsistenciaProfesional = ?");
            stmt.setDate(1, sstnc.getIngreso());
            stmt.setDate(2, sstnc.getSalida());
            stmt.setInt(3, sstnc.getLugar().getIdlugarPrestacion());
            stmt.setInt(4,sstnc.getProfesional().getIdProfesional());
            stmt.setInt(5, sstnc.getIdAsistenciaProfesional());

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Titulos del Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM AsistenciaProfesional WHERE idAsistenciaProfesional = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Asistencia del Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public List<AsistenciaProfesional> leerAsistenciasDe(int idPrfsnl)
    {
        List<AsistenciaProfesional> lista = new ArrayList();
        AsistenciaProfesional nodo = new AsistenciaProfesional();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idAsistenciaProfesional, ingreso, salida, lugar FROM AsistenciaProfesional WHERE profesional = ?");
            stmt.setInt(1, idPrfsnl);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                nodo.setIdAsistenciaProfesional(rs.getInt("idAsistenciaProfesional"));
                nodo.setIngreso(rs.getDate("ingreso"));
                nodo.setSalida(rs.getDate("salida"));
                nodo.setLugar(ctrlLugar.leer(rs.getInt("lugar")));
                
                lista.add(nodo);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Asistencias del Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }
}