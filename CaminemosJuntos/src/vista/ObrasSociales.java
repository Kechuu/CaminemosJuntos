/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlObraSocial;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ObraSocial;

/**
 *
 * @author javier
 */
public class ObrasSociales extends javax.swing.JInternalFrame {

    ObraSocial obraSocial, obraSocialModificado = null;
    CtrlObraSocial ctrlObraSocial = null;
    DefaultTableModel modeloTabla = null;
    int id;
    boolean modifica = false;
    
    public ObrasSociales(Connection cnx) {
        obraSocial = new ObraSocial();
        ctrlObraSocial = new CtrlObraSocial(cnx);
        modeloTabla = new DefaultTableModel();
        
        initComponents();
        limpiarDatos();
        lblModificando.setVisible(false);
        cargarTabla();
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtNombreCorto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        lnlNombreCorto = new javax.swing.JLabel();
        lblNombreCompleto = new javax.swing.JLabel();
        txtNombreCompleto = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnGrabar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblModificando = new javax.swing.JLabel();

        setTitle("ADMINISTRAR OBRAS SOCIALES");
        setPreferredSize(new java.awt.Dimension(600, 500));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtNombreCorto.setNextFocusableComponent(btnGrabar);
        txtNombreCorto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreCortoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreCortoFocusLost(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        lnlNombreCorto.setText("Nombre Corto");

        lblNombreCompleto.setText("Nombre Completo");

        lblDireccion.setText("Dirección");

        lblTelefono.setText("Telefono");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNombreCompleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCompleto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lnlNombreCorto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTelefono)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCorto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnlNombreCorto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreCompleto)
                    .addComponent(txtNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btnGrabar.setText("Grabar");
        btnGrabar.setNextFocusableComponent(btnModificar);
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.setNextFocusableComponent(btnBorrar);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.setNextFocusableComponent(btnSalir);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnSalir.setText("salir");
        btnSalir.setNextFocusableComponent(txtNombreCorto);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblModificando.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblModificando.setForeground(java.awt.Color.red);
        lblModificando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblModificando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModificando, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNombreCortoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCortoFocusLost
        if(!txtNombreCorto.getText().equals(""))
        {
            obraSocial = ctrlObraSocial.leer(txtNombreCorto.getText());
            if(obraSocial == null || obraSocial.isBorrado())
            {
                datosON();
                btnGrabar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnBorrar.setEnabled(false);
            }
            else
            {
                if(modifica && obraSocial.getIdObraSocial() == obraSocialModificado.getIdObraSocial())
                {
                    datosON();
                    btnGrabar.setEnabled(true);
                    btnModificar.setEnabled(false);
                    btnBorrar.setEnabled(false);
                }
                else
                {
                    txtNombreCompleto.setText(obraSocial.getNombreCompleto());
                    txtDireccion.setText(obraSocial.getDireccion());
                    txtTelefono.setText(obraSocial.getTelefono());
                    lblModificando.setVisible(false);

                    btnGrabar.setEnabled(false);
                    btnModificar.setEnabled(true);
                    btnBorrar.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_txtNombreCortoFocusLost

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        obraSocialModificado = obraSocial;
        modifica = true;
        txtNombreCorto.requestFocus();
        datosON();
        lblModificando.setText("*Modificando "+txtNombreCorto.getText());
        lblModificando.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(validarDatos())
        {
            if(modifica)
            {
                obraSocialModificado.setNombreCorto(txtNombreCorto.getText().trim().toUpperCase());
                obraSocialModificado.setNombreCompleto(txtNombreCompleto.getText().trim().toUpperCase());
                obraSocialModificado.setDireccion(txtDireccion.getText().trim().toUpperCase());
                obraSocialModificado.setTelefono(txtTelefono.getText().trim().toUpperCase());
                obraSocialModificado.setBorrado(false);

                ctrlObraSocial.editar(obraSocialModificado);

                lblModificando.setVisible(false);
                modifica = false;
            }
            else
            {
                if(obraSocial != null)
                {
                    ctrlObraSocial.recuperar(obraSocial.getIdObraSocial());

                    obraSocial.setNombreCorto(txtNombreCorto.getText().trim().toUpperCase());
                    obraSocial.setNombreCompleto(txtNombreCompleto.getText().trim().toUpperCase());
                    obraSocial.setDireccion(txtDireccion.getText().trim().toUpperCase());
                    obraSocial.setTelefono(txtTelefono.getText().trim().toUpperCase());
                    obraSocial.setBorrado(false);

                    ctrlObraSocial.editar(obraSocial);
                }
                else
                {
                    obraSocial = new ObraSocial();
                    obraSocial.setNombreCorto(txtNombreCorto.getText().trim().toUpperCase());
                    obraSocial.setNombreCompleto(txtNombreCompleto.getText().trim().toUpperCase());
                    obraSocial.setDireccion(txtDireccion.getText().trim().toUpperCase());
                    obraSocial.setTelefono(txtTelefono.getText().trim().toUpperCase());
                    obraSocial.setBorrado(false);
                    ctrlObraSocial.crear(obraSocial);
                }
            }
            txtNombreCorto.requestFocus();
            txtNombreCorto.setText("");
            limpiarDatos();
            datosOFF();
            cargarTabla();
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void txtNombreCortoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreCortoFocusGained
        btnGrabar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBorrar.setEnabled(false);
        datosOFF();
    }//GEN-LAST:event_txtNombreCortoFocusGained

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(evt.getClickCount()==2)
        {
            obraSocial = ctrlObraSocial.leer(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            txtNombreCorto.setText(obraSocial.getNombreCorto());
            txtNombreCompleto.setText(obraSocial.getNombreCompleto());
            txtDireccion.setText(obraSocial.getDireccion());
            txtTelefono.setText(obraSocial.getTelefono());
            
            txtNombreCorto.requestFocus();
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if(JOptionPane.showConfirmDialog(this, "¿ELIMINAR "+txtNombreCorto.getText().toUpperCase()+"?", "CONFIRMAR", YES_NO_OPTION, QUESTION_MESSAGE) == YES_OPTION)
        {    
            ctrlObraSocial.borrar(obraSocial.getIdObraSocial());

            txtNombreCorto.requestFocus();
            limpiarDatos();            
            cargarTabla();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "LA OPERACION HA SIDO CANCELADA", "CANCELADO", INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    public void cargarTabla()
    {
        String[] fila = new String[2];
        List<ObraSocial> lista = new ArrayList();

        lista = ctrlObraSocial.leerTodosCompleto();
        modeloTabla.setColumnCount(0);
        modeloTabla.setRowCount(0);
        modeloTabla.addColumn("NOMBRE CORTO");
        modeloTabla.addColumn("NOMBRE COMPLETO");

        for(int i=0; i<lista.size(); i++)
        {
            fila[0] = lista.get(i).getNombreCorto();
            fila[1] = lista.get(i).getNombreCompleto();
            modeloTabla.addRow(fila);
        }
        tabla.setModel(modeloTabla);
    }

    private void datosON()
    {
        txtNombreCompleto.setEditable(true);
        txtDireccion.setEditable(true);
        txtTelefono.setEditable(true);
    }
    
    private void datosOFF()
    {
        txtNombreCompleto.setEditable(false);
        txtDireccion.setEditable(false);
        txtTelefono.setEditable(false);
    }

    private void limpiarDatos()
    {
        txtNombreCorto.setText("");
        txtNombreCompleto.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    private boolean validarDatos()
    {
        boolean valida = true;
        
        if(txtNombreCorto.getText().equals(""))
        {
            valida = false;
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre Corto de la Obra Social");
        }
        if(txtNombreCompleto.getText().equals(""))
        {
            valida = false;
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre Completo de la Obra Social");
        }
        if(txtDireccion.getText().equals(""))
        {
            valida = false;
            JOptionPane.showMessageDialog(null, "Ingrese la Dirección de la Obra Social");
        }
        if(txtTelefono.getText().equals(""))
        {
            valida = false;
            JOptionPane.showMessageDialog(null, "Ingrese un Telefono de la Obra Social");
        }
        return valida;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblModificando;
    private javax.swing.JLabel lblNombreCompleto;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lnlNombreCorto;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombreCompleto;
    private javax.swing.JTextField txtNombreCorto;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}