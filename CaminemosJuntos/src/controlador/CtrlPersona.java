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
import modelo.Persona;


/**
 *
 * @author javier
 */
public class CtrlPersona {
    Connection cnx;
    CtrlTipoDocumento ctrlTipo;
    CtrlDomicilio ctrlDomicilio;
    public CtrlPersona(Connection cnx)
    {
        this.cnx = cnx;
        this.ctrlTipo = new CtrlTipoDocumento(cnx);
        this.ctrlDomicilio = new CtrlDomicilio(cnx);
    }
    
    public void crear(Persona prsn)
    {
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Persona (tipoDocumento, nroDocumento, apellido, nombre, fechaNacimiento, sexo, domicilio, borrado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, prsn.getTipoDocumento().getIdtipoDocumento());
            stmt.setString(2, prsn.getNroDocumento());
            stmt.setString(3, prsn.getApellido());
            stmt.setString(4, prsn.getNombre());
            stmt.setDate(5, prsn.getFechaNacimiento());
            stmt.setInt(6, prsn.getSexo());
            stmt.setInt(7, prsn.getDomicilio().getIdDomicilio());
            stmt.setBoolean(8, false);
            
            stmt.execute();

            rs = stmt.executeQuery("SELECT MAX(idPersona) AS id FROM Persona");
            rs.next();
            prsn.setIdPersona(rs.getInt("id"));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nueva Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Persona prsn)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Persona SET tipoDocumento = ?, nroDocumento = ?, apellido = ?, nombre = ?, fechaNacimiento = ?, sexo = ?, domicilio = ?  WHERE idPersona = ?");
            stmt.setInt(1, prsn.getTipoDocumento().getIdtipoDocumento());
            stmt.setString(2, prsn.getNroDocumento());
            stmt.setString(3, prsn.getApellido());
            stmt.setString(4, prsn.getNombre());
            stmt.setDate(5, prsn.getFechaNacimiento());
            stmt.setInt(6, prsn.getSexo());
            stmt.setInt(7, prsn.getDomicilio().getIdDomicilio());
            stmt.setInt(8, prsn.getIdPersona());
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Datos de Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Persona SET borrado = true WHERE idPersona = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Persona SET borrado = false WHERE idPersona = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Persona", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Persona leer(int id)
    {
        Persona prsn = new Persona();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT tipoDocumento, nroDocumento, apellido, nombre, fechaNacimiento, sexo, domicilio, borrado FROM Persona WHERE idPersona = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                prsn.setIdPersona(id);
                prsn.setTipoDocumento(ctrlTipo.leer(rs.getString("tipoDocumento")));
                prsn.setNroDocumento(rs.getString("nroDocumento"));
                prsn.setApellido(rs.getString("apellido"));
                prsn.setNombre(rs.getString("nombre"));
                prsn.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                prsn.setSexo(rs.getByte("sexo"));
                prsn.setDomicilio(ctrlDomicilio.leer(rs.getInt("domicilio")));
                prsn.setBorrado(rs.getBoolean("borrado"));
            }
            else
                prsn = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Persona", "ERROR!!!...", ERROR_MESSAGE);
        }

        return prsn;
    }
    
    public Persona leer(int tp, String nr)
    {
        Persona prsn = new Persona();
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idPersona, tipoDocumento, nroDocumento, apellido, nombre, fechaNacimiento, sexo, domicilio, borrado FROM Persona WHERE tipoDocumento = ? AND nroDocumento = ?");
            stmt.setInt(1, tp);
            stmt.setString(2, nr);

            rs = stmt.executeQuery();
            
            if(rs.next())
            {
                prsn.setIdPersona(rs.getInt("idPersona"));
                prsn.setTipoDocumento(ctrlTipo.leer(rs.getString("tipoDocumento")));
                prsn.setNroDocumento(rs.getString("nroDocumento"));
                prsn.setApellido(rs.getString("apellido"));
                prsn.setNombre(rs.getString("nombre"));
                prsn.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                prsn.setSexo(rs.getByte("sexo"));
                prsn.setDomicilio(ctrlDomicilio.leer(rs.getInt("domicilio")));
                prsn.setBorrado(rs.getBoolean("borrado"));
            }
            else
                prsn = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Persona", "ERROR!!!...", ERROR_MESSAGE);
        }

        return prsn;
    }

/*    public List<String> leerTodos()
    {
        int i = 0;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT nombreCorto FROM ObraSocial WHERE borrado = false ORDER BY nombreCorto");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                lista.add(rs.getString("nombreCorto"));
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Obras Sociales", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }
*/
}
