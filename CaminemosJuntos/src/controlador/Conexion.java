package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ing. Javier Agustín Zamar
 */
public class Conexion {
   public static Connection conectar(String server, String db, String usr, String pass)
   {
       Connection conexion = null;
       String servidor;
       servidor = "jdbc:mysql://"+server+"/"+db;
     
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           conexion = (Connection) DriverManager.getConnection(servidor, usr, pass);
       }
       catch(ClassNotFoundException ex)
       {
            JOptionPane.showMessageDialog(null, ex.getMessage());
       }
       catch(Exception ex)
       {
            JOptionPane.showMessageDialog(null, ex.getMessage());
       }
       finally
       {
           return conexion;
       }
   }
   
   public static void desconectar(Connection cnx)
   {
       try {
           cnx.close();
       } catch (SQLException ex) {
           Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
