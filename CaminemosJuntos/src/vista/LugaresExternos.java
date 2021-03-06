/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlLugarPrestacion;
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
import modelo.LugarPrestacion;
import modelo.ObraSocial;

/**
 *
 * @author javier
 */
public class LugaresExternos extends javax.swing.JInternalFrame {

    LugarPrestacion lugarPrestacion, lugarPrestacionModificado = null;
    CtrlLugarPrestacion ctrlLugarPrestacion = null;
    DefaultTableModel modeloTabla = null;
    int id;
    boolean modifica = false;
    
    public LugaresExternos(Connection cnx) {
        lugarPrestacion = new LugarPrestacion();
        ctrlLugarPrestacion = new CtrlLugarPrestacion(cnx);
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
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        lblNombre = new javax.swing.JLabel();
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

        setTitle("ADMINISTRAR LUGARES EXTERNOS");
        setPreferredSize(new java.awt.Dimension(600, 500));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtNombre.setNextFocusableComponent(btnGrabar);
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
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

        lblNombre.setText("Nombre");

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
                        .addComponent(lblDireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTelefono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
        btnSalir.setNextFocusableComponent(txtNombre);
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

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        if(!txtNombre.getText().equals(""))
        {
            lugarPrestacion = ctrlLugarPrestacion.leer(txtNombre.getText());
            if(lugarPrestacion == null || lugarPrestacion.isBorrado())
            {
                datosON();
                btnGrabar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnBorrar.setEnabled(false);
            }
            else
            {
                if(modifica && lugarPrestacion.getIdlugarPrestacion() == lugarPrestacionModificado.getIdlugarPrestacion())
                {
                    datosON();
                    btnGrabar.setEnabled(true);
                    btnModificar.setEnabled(false);
                    btnBorrar.setEnabled(false);
                }
                else
                {
                    txtDireccion.setText(lugarPrestacion.getDireccion());
                    txtTelefono.setText(lugarPrestacion.getTelefono());
                    lblModificando.setVisible(false);

                    btnGrabar.setEnabled(false);
                    btnModificar.setEnabled(true);
                    btnBorrar.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        lugarPrestacionModificado = lugarPrestacion;
        modifica = true;
        txtNombre.requestFocus();
        datosON();
        lblModificando.setText("*Modificando "+txtNombre.getText());
        lblModificando.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(validarDatos())
        {
            if(modifica)
            {
                lugarPrestacionModificado.setNombre(txtNombre.getText().trim().toUpperCase());
                lugarPrestacionModificado.setDireccion(txtDireccion.getText().trim().toUpperCase());
                lugarPrestacionModificado.setTelefono(txtTelefono.getText().trim().toUpperCase());
                lugarPrestacionModificado.setBorrado(false);

                ctrlLugarPrestacion.editar(lugarPrestacionModificado);

                lblModificando.setVisible(false);
                modifica = false;
            }
            else
            {
                if(lugarPrestacion != null)
                {
                    ctrlLugarPrestacion.recuperar(lugarPrestacion.getIdlugarPrestacion());

                    lugarPrestacion.setNombre(txtNombre.getText().trim().toUpperCase());
                    lugarPrestacion.setDireccion(txtDireccion.getText().trim().toUpperCase());
                    lugarPrestacion.setTelefono(txtTelefono.getText().trim().toUpperCase());
                    lugarPrestacion.setBorrado(false);

                    ctrlLugarPrestacion.editar(lugarPrestacion);
                }
                else
                {
                    lugarPrestacion = new LugarPrestacion();
                    lugarPrestacion.setNombre(txtNombre.getText().trim().toUpperCase());
                    lugarPrestacion.setDireccion(txtDireccion.getText().trim().toUpperCase());
                    lugarPrestacion.setTelefono(txtTelefono.getText().trim().toUpperCase());
                    lugarPrestacion.setBorrado(false);
                    ctrlLugarPrestacion.crear(lugarPrestacion);
                }
            }
            txtNombre.requestFocus();
            txtNombre.setText("");
            limpiarDatos();
            datosOFF();
            cargarTabla();
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        btnGrabar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBorrar.setEnabled(false);
        datosOFF();
    }//GEN-LAST:event_txtNombreFocusGained

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(evt.getClickCount()==2)
        {
            lugarPrestacion = ctrlLugarPrestacion.leer(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            txtNombre.setText(lugarPrestacion.getNombre());
            txtDireccion.setText(lugarPrestacion.getDireccion());
            txtTelefono.setText(lugarPrestacion.getTelefono());
            
            txtNombre.requestFocus();
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if(JOptionPane.showConfirmDialog(this, "¿ELIMINAR "+txtNombre.getText().toUpperCase()+"?", "CONFIRMAR", YES_NO_OPTION, QUESTION_MESSAGE) == YES_OPTION)
        {    
            ctrlLugarPrestacion.borrar(lugarPrestacion.getIdlugarPrestacion());

            txtNombre.requestFocus();
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
        List<LugarPrestacion> lista = new ArrayList();

        lista = ctrlLugarPrestacion.leerTodosCompleto();
        modeloTabla.setColumnCount(0);
        modeloTabla.setRowCount(0);
        modeloTabla.addColumn("NOMBRE CORTO");
        modeloTabla.addColumn("DOMICILIO");

        for(int i=0; i<lista.size(); i++)
        {
            fila[0] = lista.get(i).getNombre();
            fila[1] = lista.get(i).getDireccion();
            modeloTabla.addRow(fila);
        }
        tabla.setModel(modeloTabla);
    }

    private void datosON()
    {
        txtDireccion.setEditable(true);
        txtTelefono.setEditable(true);
    }
    
    private void datosOFF()
    {
        txtDireccion.setEditable(false);
        txtTelefono.setEditable(false);
    }

    private void limpiarDatos()
    {
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    private boolean validarDatos()
    {
        boolean valida = true;
        
        if(txtNombre.getText().equals(""))
        {
            valida = false;
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre Corto de la Obra Social");
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
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
