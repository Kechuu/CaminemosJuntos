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
import modelo.Persona;
import modelo.Profesional;


/**
 *
 * @author javier
 */
public class CtrlProfesional {
    Connection cnx;
    CtrlPersona ctrlPersona;
    CtrlTituloProfesional ctrlTituloProfesional;
    CtrlEstadoProfesional ctrlEstadoProfesional;
    
    public CtrlProfesional(Connection cnx)
    {
        this.cnx = cnx;
        ctrlPersona = new CtrlPersona(cnx);
        ctrlTituloProfesional = new CtrlTituloProfesional(cnx);
        ctrlEstadoProfesional = new CtrlEstadoProfesional(cnx);
    }
    
    public void crear(Profesional prfsnl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Profesional (persona, borrado, carnetSanitario, malaPraxis, superIntendencia, estado) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, prfsnl.getPersona().getIdPersona());
            stmt.setBoolean(2, prfsnl.isBorrado());
            stmt.setDate(3, prfsnl.getCarnetSanitario());
            stmt.setDate(4, prfsnl.getMalaPraxis());
            stmt.setDate(5, prfsnl.getSuperIntendencia());
            stmt.setInt(6, prfsnl.getEstado().getIdestadoProfesional());
            
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Profesional prfsnl)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Profesional SET persona = ?, borrado = ?, carnetSanitario = ?, malaPraxis = ?, superIntendencia = ?, estado = ? WHERE idProfesional = ?");
            stmt.setInt(1, prfsnl.getPersona().getIdPersona());
            stmt.setBoolean(2, prfsnl.isBorrado());
            stmt.setDate(3, prfsnl.getCarnetSanitario());
            stmt.setDate(4, prfsnl.getMalaPraxis());
            stmt.setDate(5, prfsnl.getSuperIntendencia());
            stmt.setInt(6, prfsnl.getEstado().getIdestadoProfesional());
            stmt.setInt(7, prfsnl.getIdProfesional());
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Datos del Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Profesional SET borrado = true WHERE idProfesional = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Profesional SET borrado = false WHERE idProfesional = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Profesional leer(int id)
    {
        Profesional prfsnl = new Profesional();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT persona, borrado, carnetSanitario, malaPraxis, superIntendencia, estado FROM Profesional WHERE idProfesional = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                prfsnl.setIdProfesional(id);
                prfsnl.setPersona(ctrlPersona.leer(rs.getInt("persona")));
                prfsnl.setBorrado(rs.getBoolean("borrado"));
                prfsnl.setCarnetSanitario(rs.getDate("carnetSanitario"));
                prfsnl.setMalaPraxis(rs.getDate("malaPraxis"));
                prfsnl.setSuperIntendencia(rs.getDate("superIntendencia"));
                prfsnl.setEstado(ctrlEstadoProfesional.leer(rs.getInt("estado")));
                prfsnl.setTitulos(ctrlTituloProfesional.leerTitulos(id));
            }
            else
                prfsnl = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Profesional", "ERROR!!!...", ERROR_MESSAGE);
        }

        return prfsnl;
    }
    

    public List<String> leerTodos()
    {
        int i = 0;
        Persona persona;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT persona FROM Profesional WHERE borrado = false");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                persona = ctrlPersona.leer(rs.getInt("persona"));
                lista.add(persona.getApellido()+", "+persona.getNombre());
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Profesionales", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }

}
