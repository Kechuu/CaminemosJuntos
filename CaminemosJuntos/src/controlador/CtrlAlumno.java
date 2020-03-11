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
import modelo.Alumno;
import modelo.Persona;


/**
 *
 * @author javier
 */
public class CtrlAlumno {
    Connection cnx;
    CtrlPersona ctrlPersona;
    CtrlObraSocial ctrlObraSocial;
    
    public CtrlAlumno(Connection cnx)
    {
        this.cnx = cnx;
        ctrlPersona = new CtrlPersona(cnx);
        ctrlObraSocial = new CtrlObraSocial(cnx);
    }
    
    public void crear(Alumno lmn)
    {
        ResultSet rs;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("INSERT INTO Alumno (persona, lugarNacimiento, legajo, tutor, madre, padre, integrado, obraSocial, vtoCUD, pension, asignacionUniversal, borrado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");            
            stmt.setInt(1, lmn.getPersona().getIdPersona());
            stmt.setInt(2, lmn.getLugarNacimiento().getIdLugarDomicilio());
            stmt.setString(3, lmn.getLegajo());
            stmt.setInt(4, lmn.getTutor().getIdPersona());
            stmt.setInt(5, lmn.getMadre().getIdPersona());
            stmt.setInt(6, lmn.getPadre().getIdPersona());
            stmt.setBoolean(7, lmn.isIntegrado());
            stmt.setInt(8, lmn.getObraSocial().getIdObraSocial());
            stmt.setDate(9, lmn.getVtoCUD());
            stmt.setBoolean(10, lmn.isPension());
            stmt.setBoolean(11, lmn.isAsignacionUniversal());
            stmt.setBoolean(12, false);
            
            stmt.execute();
            
            rs = stmt.executeQuery("SELECT MAX(idAlumno) AS id FROM Alumno");
            rs.next();
            lmn.setIdAlumno(rs.getInt("id"));
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al CREAR nuevo Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }
    
    public void editar(Alumno lmn)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Alumno SET persona = ?, legajo = ?, tutor = ?, madre = ?, padre = ?, integrado = ?, obraSocial = ?, vtoCUD = ?, pension = ?, asigancionUniversal = ?, borrado = ? WHERE idalumno = ?");
            stmt.setInt(1, lmn.getPersona().getIdPersona());
            stmt.setString(2, lmn.getLegajo());
            stmt.setInt(3, lmn.getTutor().getIdPersona());
            stmt.setInt(4, lmn.getMadre().getIdPersona());
            stmt.setInt(5, lmn.getPadre().getIdPersona());
            stmt.setBoolean(6, lmn.isIntegrado());
            stmt.setInt(7, lmn.getObraSocial().getIdObraSocial());
            stmt.setDate(8, lmn.getVtoCUD());
            stmt.setBoolean(9, lmn.isPension());
            stmt.setBoolean(10, lmn.isAsignacionUniversal());
            stmt.setBoolean(11, false);
            stmt.setInt(12, lmn.getIdAlumno());
            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al MODIFICAR Datos del Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void borrar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Alumno SET borrado = true WHERE idalumno = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al ELIMINAR Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public void recuperar(int id)
    {
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("UPDATE Alumno SET borrado = false WHERE idalumno = ?");
            stmt.setInt(1, id);

            stmt.execute();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al RECUPERAR Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }
    }

    public Alumno leer(int id)
    {
        Alumno lmn = new Alumno();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT persona, legajo, tutor, madre, padre, integrado, obraSocial, vtoCUD, pension, asignacionUniversal, borrado FROM Alumno WHERE idAlumno = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lmn.setIdAlumno(id);
                lmn.setPersona(ctrlPersona.leer(rs.getInt("persona")));
                lmn.setLegajo(rs.getString("legajo"));
                lmn.setTutor(ctrlPersona.leer(rs.getInt("tutor")));
                lmn.setMadre((rs.getInt("madre") == 0) ? null : ctrlPersona.leer(rs.getInt("madre")));
                lmn.setPadre((rs.getInt("padre") == 0) ? null : ctrlPersona.leer(rs.getInt("padre")));
                lmn.setIntegrado(rs.getBoolean("integrado"));
                lmn.setObraSocial(ctrlObraSocial.leer(rs.getInt("obraSocial")));
                lmn.setVtoCUD(rs.getDate("vtoCUD"));
                lmn.setPension(rs.getBoolean("pension"));
                lmn.setAsignacionUniversal(rs.getBoolean("asignacionUniversal"));
                lmn.setBorrado(rs.getBoolean("borrado"));
            }
            else
                lmn = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lmn;
    }
    
    public Alumno existe(int prsn)
    {
        Alumno lmn = new Alumno();
        ResultSet rs = null;
        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT idAlumno, legajo, tutor, madre, padre, integrado, obraSocial, vtoCUD, pension, asignacionUniversal, borrado FROM Alumno WHERE persona = ?");
            stmt.setInt(1, prsn);

            rs = stmt.executeQuery();

            if(rs.next())
            {
                lmn.setIdAlumno(rs.getInt("idAlumno"));
                lmn.setPersona(ctrlPersona.leer(prsn));
                lmn.setLegajo(rs.getString("legajo"));
                lmn.setTutor(ctrlPersona.leer(rs.getInt("tutor")));
                lmn.setMadre((rs.getInt("madre") == 0) ? null : ctrlPersona.leer(rs.getInt("madre")));
                lmn.setPadre((rs.getInt("padre") == 0) ? null : ctrlPersona.leer(rs.getInt("padre")));
                lmn.setIntegrado(rs.getBoolean("integrado"));
                lmn.setObraSocial(ctrlObraSocial.leer(rs.getInt("obraSocial")));
                lmn.setVtoCUD(rs.getDate("vtoCUD"));
                lmn.setPension(rs.getBoolean("pension"));
                lmn.setAsignacionUniversal(rs.getBoolean("asignacionUniversal"));
                lmn.setBorrado(rs.getBoolean("borrado"));
            }
            else
                lmn = null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error al LEER Alumno", "ERROR!!!...", ERROR_MESSAGE);
        }

        return lmn;
    }

    public List<String> leerTodos()
    {
        int i = 0;
        Persona persona;
        ResultSet rs;
        List<String> lista = new ArrayList();

        try
        {
            PreparedStatement stmt = cnx.prepareStatement("SELECT persona FROM Alumno WHERE borrado = false");

            rs = stmt.executeQuery();

            while(rs.next())
            {
                persona = ctrlPersona.leer(rs.getInt("persona"));
                lista.add(persona.getApellido()+", "+persona.getNombre());
            }
            
        }
        catch(SQLException ex)
        {
                JOptionPane.showMessageDialog(null, "Error al LEER Alumnos", "ERROR!!!...", ERROR_MESSAGE);
        }
        return lista;
    }

}
