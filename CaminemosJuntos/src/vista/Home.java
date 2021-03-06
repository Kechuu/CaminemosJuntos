/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;

/**
 *
 * @author javier
 */
public class Home extends javax.swing.JFrame {
    public static Connection conexion;
    public Home() {
        initComponents();
        Login vLogin = new Login(conexion);
        panelPrincipal.add(vLogin);
        vLogin.setVisible(true);
    }
    public static void setConexion(Connection cnx)
    {
        conexion = cnx;
    }
    
    public static void cerrar()
    {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        panelPrincipal = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        menuAlumno = new javax.swing.JMenu();
        itemAlumnoInscripcion = new javax.swing.JMenuItem();
        menuProfesional = new javax.swing.JMenu();
        itemTitulos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuObraSocial = new javax.swing.JMenu();
        itemObraSocialAdministrar = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        menuLugares = new javax.swing.JMenu();
        itemProvincias = new javax.swing.JMenuItem();
        itemDepartamentos = new javax.swing.JMenuItem();
        itemLocalidades = new javax.swing.JMenuItem();
        itemBarrios = new javax.swing.JMenuItem();
        itemCalles = new javax.swing.JMenuItem();
        itemLugaresExternos = new javax.swing.JMenuItem();
        menuVarios = new javax.swing.JMenu();
        itemDiagnosticos = new javax.swing.JMenuItem();
        itemNivelesIntegracion = new javax.swing.JMenuItem();
        itemTipoDocumento = new javax.swing.JMenuItem();
        itemTipoTelefono = new javax.swing.JMenuItem();
        menuSistema = new javax.swing.JMenu();
        itemIniciarSesion = new javax.swing.JMenuItem();
        itemSalir = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuAlumno.setMnemonic('f');
        menuAlumno.setText("Alumno");

        itemAlumnoInscripcion.setText("Inscripción");
        itemAlumnoInscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAlumnoInscripcionActionPerformed(evt);
            }
        });
        menuAlumno.add(itemAlumnoInscripcion);

        menuBar.add(menuAlumno);

        menuProfesional.setMnemonic('e');
        menuProfesional.setText("Profesional");

        itemTitulos.setMnemonic('d');
        itemTitulos.setText("Titulos");
        itemTitulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTitulosActionPerformed(evt);
            }
        });
        menuProfesional.add(itemTitulos);

        jMenuItem2.setText("Ingresar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuProfesional.add(jMenuItem2);

        menuBar.add(menuProfesional);

        menuObraSocial.setMnemonic('h');
        menuObraSocial.setText("Obra Social");

        itemObraSocialAdministrar.setMnemonic('c');
        itemObraSocialAdministrar.setText("Administrar");
        itemObraSocialAdministrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemObraSocialAdministrarActionPerformed(evt);
            }
        });
        menuObraSocial.add(itemObraSocialAdministrar);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        menuObraSocial.add(aboutMenuItem);

        menuBar.add(menuObraSocial);

        menuLugares.setText("Lugares");

        itemProvincias.setText("Provincias");
        itemProvincias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProvinciasActionPerformed(evt);
            }
        });
        menuLugares.add(itemProvincias);

        itemDepartamentos.setText("Departamentos");
        itemDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDepartamentosActionPerformed(evt);
            }
        });
        menuLugares.add(itemDepartamentos);

        itemLocalidades.setText("Localidades");
        itemLocalidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLocalidadesActionPerformed(evt);
            }
        });
        menuLugares.add(itemLocalidades);

        itemBarrios.setText("Barrios");
        itemBarrios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBarriosActionPerformed(evt);
            }
        });
        menuLugares.add(itemBarrios);

        itemCalles.setText("Calles");
        itemCalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCallesActionPerformed(evt);
            }
        });
        menuLugares.add(itemCalles);

        itemLugaresExternos.setText("Lugares externos");
        itemLugaresExternos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLugaresExternosActionPerformed(evt);
            }
        });
        menuLugares.add(itemLugaresExternos);

        menuBar.add(menuLugares);

        menuVarios.setText("Varios");

        itemDiagnosticos.setText("Diagnósticos");
        itemDiagnosticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDiagnosticosActionPerformed(evt);
            }
        });
        menuVarios.add(itemDiagnosticos);

        itemNivelesIntegracion.setText("Niveles de Integracion");
        itemNivelesIntegracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNivelesIntegracionActionPerformed(evt);
            }
        });
        menuVarios.add(itemNivelesIntegracion);

        itemTipoDocumento.setMnemonic('x');
        itemTipoDocumento.setText("Tipos de Documentos");
        itemTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTipoDocumentoActionPerformed(evt);
            }
        });
        menuVarios.add(itemTipoDocumento);

        itemTipoTelefono.setText("Tipos de Telefonos");
        itemTipoTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTipoTelefonoActionPerformed(evt);
            }
        });
        menuVarios.add(itemTipoTelefono);

        menuBar.add(menuVarios);

        menuSistema.setText("Sistema");

        itemIniciarSesion.setText("Iniciar Sesion");
        itemIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIniciarSesionActionPerformed(evt);
            }
        });
        menuSistema.add(itemIniciarSesion);

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        menuSistema.add(itemSalir);

        menuBar.add(menuSistema);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTipoDocumentoActionPerformed
        TipoDeDocumentos vTipoDeDocumentos = new TipoDeDocumentos(conexion);
        panelPrincipal.add(vTipoDeDocumentos);
        vTipoDeDocumentos.setVisible(true);
    }//GEN-LAST:event_itemTipoDocumentoActionPerformed

    private void itemIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIniciarSesionActionPerformed
        Login vLogin = new Login(conexion);
        panelPrincipal.add(vLogin);
        vLogin.setVisible(true);
    }//GEN-LAST:event_itemIniciarSesionActionPerformed

    private void itemTitulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTitulosActionPerformed
        Titulos vTitulos = new Titulos(conexion);
        panelPrincipal.add(vTitulos);
        vTitulos.setVisible(true);
    }//GEN-LAST:event_itemTitulosActionPerformed

    private void itemProvinciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProvinciasActionPerformed
        Provincias vProvincias = new Provincias(conexion);
        panelPrincipal.add(vProvincias);
        vProvincias.setVisible(true);
    }//GEN-LAST:event_itemProvinciasActionPerformed

    private void itemDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDepartamentosActionPerformed
        Departamentos vDepartamentos = new Departamentos(conexion);
        panelPrincipal.add(vDepartamentos);
        vDepartamentos.setVisible(true);
    }//GEN-LAST:event_itemDepartamentosActionPerformed

    private void itemLocalidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLocalidadesActionPerformed
        Localidades vLocalidades = new Localidades(conexion);
        panelPrincipal.add(vLocalidades);
        vLocalidades.setVisible(true);
    }//GEN-LAST:event_itemLocalidadesActionPerformed

    private void itemBarriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBarriosActionPerformed
        Barrios vBarrios = new Barrios(conexion);
        panelPrincipal.add(vBarrios);
        vBarrios.setVisible(true);
    }//GEN-LAST:event_itemBarriosActionPerformed

    private void itemCallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCallesActionPerformed
        Calles vCalles = new Calles(conexion);
        panelPrincipal.add(vCalles);
        vCalles.setVisible(true);
    }//GEN-LAST:event_itemCallesActionPerformed

    private void itemAlumnoInscripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAlumnoInscripcionActionPerformed
        AlumnosAltas vAlumnosAltas = new AlumnosAltas(conexion);
        panelPrincipal.add(vAlumnosAltas);
        vAlumnosAltas.setVisible(true);
    }//GEN-LAST:event_itemAlumnoInscripcionActionPerformed

    private void itemObraSocialAdministrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemObraSocialAdministrarActionPerformed
        ObrasSociales vObrasSociales = new ObrasSociales(conexion);
        panelPrincipal.add(vObrasSociales);
        vObrasSociales.setVisible(true);
    }//GEN-LAST:event_itemObraSocialAdministrarActionPerformed

    private void itemLugaresExternosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLugaresExternosActionPerformed
        LugaresExternos vLugaresExternos = new LugaresExternos(conexion);
        panelPrincipal.add(vLugaresExternos);
        vLugaresExternos.setVisible(true);
    }//GEN-LAST:event_itemLugaresExternosActionPerformed

    private void itemTipoTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTipoTelefonoActionPerformed
        TipoDeTelefonos vTipoDeTelefonos = new TipoDeTelefonos(conexion);
        panelPrincipal.add(vTipoDeTelefonos);
        vTipoDeTelefonos.setVisible(true);
    }//GEN-LAST:event_itemTipoTelefonoActionPerformed

    private void itemNivelesIntegracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNivelesIntegracionActionPerformed
        Niveles vNiveles = new Niveles(conexion);
        panelPrincipal.add(vNiveles);
        vNiveles.setVisible(true);
    }//GEN-LAST:event_itemNivelesIntegracionActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemDiagnosticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDiagnosticosActionPerformed
        Diagnosticos vDiagnosticos = new Diagnosticos(conexion);
        panelPrincipal.add(vDiagnosticos);
        vDiagnosticos.setVisible(true);
    }//GEN-LAST:event_itemDiagnosticosActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Profesionales vProfesionales = new Profesionales(conexion);
        panelPrincipal.add(vProfesionales);
        vProfesionales.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem itemAlumnoInscripcion;
    private javax.swing.JMenuItem itemBarrios;
    private javax.swing.JMenuItem itemCalles;
    private javax.swing.JMenuItem itemDepartamentos;
    private javax.swing.JMenuItem itemDiagnosticos;
    private javax.swing.JMenuItem itemIniciarSesion;
    private javax.swing.JMenuItem itemLocalidades;
    private javax.swing.JMenuItem itemLugaresExternos;
    private javax.swing.JMenuItem itemNivelesIntegracion;
    private javax.swing.JMenuItem itemObraSocialAdministrar;
    private javax.swing.JMenuItem itemProvincias;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenuItem itemTipoDocumento;
    private javax.swing.JMenuItem itemTipoTelefono;
    private javax.swing.JMenuItem itemTitulos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu menuAlumno;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuLugares;
    private javax.swing.JMenu menuObraSocial;
    private javax.swing.JMenu menuProfesional;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JMenu menuVarios;
    private javax.swing.JDesktopPane panelPrincipal;
    // End of variables declaration//GEN-END:variables

}
