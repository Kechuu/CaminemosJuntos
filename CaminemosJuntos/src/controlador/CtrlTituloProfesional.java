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
import modelo.TituloProfesional;

/**
 *
 * @author javier
 */
public class CtrlTituloProfesional {
    Connection cnx;
    CtrlTitulo ctrlTitulo;
    
    public CtrlTituloProfesional(Connection cnx)
    {
        this.cnx = cnx;
        ctrlTitulo = new CtrlTitulo(cnx);
    }
    
    public void crear(TituloProfesional ttl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO TituloProfesional (titulo, profesional, matricula) VALUES (?, ?, ?)");
            stmt.setInt(1, ttl.getTitulo().getIdTitulo());
            stmt.setInt(2, ttl.getProfesional().getIdProfesional());
            stmt.setString(3, ttl.getMatricula());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ASIGNAR Titulo al Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(TituloProfesional ttl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE TituloProfesional SET titulo = ?, profesional = ?, matricula = ? WHERE idTituloProfesional = ?");
            stmt.setInt(1, ttl.getTitulo().getIdTitulo());
            stmt.setInt(2, ttl.getProfesional().getIdProfesional());
            stmt.setString(3, ttl.getMatricula());
            stmt.setInt(4, ttl.getIdTituloProfesional());

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
            PreparedStatement stmt = cnx.prepareStatement("DELETE FROM TituloProfesional WHERE idTituloProfesional = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Titulo del Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public List<String[]> leerTitulos(int idPrfsnl)
    {
        List<String[]> lista = new ArrayList();
        String[] fila = new String[2];
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT titulo, matricula FROM TituloProfesional WHERE profesional = ?");
            stmt.setInt(1, idPrfsnl);

            rs = stmt.executeQuery();

            while(rs.next())
            {
                fila[0] = ctrlTitulo.leer(rs.getInt("titulo")).getTitulo();
                fila[1] = rs.getString("matricula");
                lista.add(fila);
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Titulos del Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lista;
    }
}