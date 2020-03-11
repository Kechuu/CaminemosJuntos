/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlLugarDomicilio;
import java.sql.Connection;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.LugarDomicilio;
import javax.swing.JComboBox;


/**
 *
 * @author javier
 */
public class Barrios extends javax.swing.JInternalFrame {

    LugarDomicilio lugar, lugarAModificar, prvnc, dprtmnt, lcldd = null;
    CtrlLugarDomicilio ctrlLugar = null;
    DefaultTableModel modeloTabla = null;
    int id;
    boolean modifica = false;
    List<String> listaProvincias, listaDepartamentos, listaLocalidades = null;
    
    public Barrios(Connection cnx){
        lugar = new LugarDomicilio();
        ctrlLugar = new CtrlLugarDomicilio(cnx);
        modeloTabla = new DefaultTableModel();
        
        initComponents();

        lblModificando.setVisible(false);

        listaProvincias = ctrlLugar.leerTodos(5);
        if(listaProvincias.size() > 0)
        {    
            cargarCombo(cbxProvincias, listaProvincias);
            prvnc = ctrlLugar.leer(cbxProvincias.getSelectedItem().toString(),5);
            listaDepartamentos = ctrlLugar.leerTodosDe(prvnc);
            if(listaDepartamentos.size() > 0)
            {    
                cargarCombo(cbxDepartamentos, listaDepartamentos);
                dprtmnt = ctrlLugar.leer(cbxDepartamentos.getSelectedItem().toString(), 4);
                listaLocalidades = ctrlLugar.leerTodosDe(dprtmnt);
                if(listaLocalidades.size() > 0)
                {
                    cargarCombo(cbxLocalidades,listaLocalidades);
                    lcldd = ctrlLugar.leer(cbxLocalidades.getSelectedItem().toString(), 3);
                    cargarTabla();
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblProvincia = new javax.swing.JLabel();
        cbxProvincias = new javax.swing.JComboBox<>();
        lblDepto = new javax.swing.JLabel();
        cbxDepartamentos = new javax.swing.JComboBox<>();
        lblLocalidad = new javax.swing.JLabel();
        cbxLocalidades = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnGrabar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNuevo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.

            }
        };
        lblModificando = new javax.swing.JLabel();

        setTitle("Barrios");
        setPreferredSize(new java.awt.Dimension(385, 457));

        lblProvincia.setText("Provincia");

        cbxProvincias.setNextFocusableComponent(cbxDepartamentos);
        cbxProvincias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciasItemStateChanged(evt);
            }
        });
        cbxProvincias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbxProvinciasFocusGained(evt);
            }
        });

        lblDepto.setText("Depto");

        cbxDepartamentos.setNextFocusableComponent(txtNuevo);
        cbxDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentosItemStateChanged(evt);
            }
        });
        cbxDepartamentos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbxDepartamentosFocusGained(evt);
            }
        });

        lblLocalidad.setText("Localidad");

        cbxLocalidades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLocalidadesItemStateChanged(evt);
            }
        });

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
        btnSalir.setNextFocusableComponent(cbxProvincias);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txtNuevo.setNextFocusableComponent(btnGrabar);
        txtNuevo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNuevoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNuevoFocusLost(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblModificando.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblModificando.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDepto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxDepartamentos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProvincia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxProvincias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLocalidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLocalidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblModificando, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProvincia)
                            .addComponent(cbxProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDepto)
                            .addComponent(cbxDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocalidad)
                            .addComponent(cbxLocalidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblModificando, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNuevoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoFocusLost
        if(!txtNuevo.getText().equals("") && lcldd != null)
        {
            lugar = ctrlLugar.leer(txtNuevo.getText(), 2, lcldd.getIdLugarDomicilio());
            if(lugar != null)
            {
                btnGrabar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnBorrar.setEnabled(true);
            }
            else
            {
                btnGrabar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnBorrar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtNuevoFocusLost

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        lugarAModificar = lugar;
        modifica = true;
        txtNuevo.requestFocus();
        lblModificando.setText("*MODIFICANDO "+txtNuevo.getText());
        lblModificando.setVisible(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(modifica)
        {
            lugarAModificar.setNombre(txtNuevo.getText().trim().toUpperCase());

            ctrlLugar.editar(lugarAModificar);
            
            lblModificando.setVisible(false);
            modifica = false;
        }
        else
        {
            lugar = new LugarDomicilio();
            lugar.setNombre(txtNuevo.getText().trim().toUpperCase());
            lugar.setNivel(2);
            lugar.setDe(lcldd);

            ctrlLugar.crear(lugar);
        }
        txtNuevo.requestFocus();
        txtNuevo.setText("");
        cargarTabla();
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void txtNuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNuevoFocusGained
        btnGrabar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBorrar.setEnabled(false);
    }//GEN-LAST:event_txtNuevoFocusGained

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if(evt.getClickCount()==2)
        {
            txtNuevo.setText(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            txtNuevo.requestFocus();
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if(JOptionPane.showConfirmDialog(this, "¿ELIMINAR "+txtNuevo.getText().toUpperCase()+"?", "CONFIRMAR", YES_NO_OPTION, QUESTION_MESSAGE) == YES_OPTION)
        {    
            ctrlLugar.borrar(lugar.getIdLugarDomicilio());

            txtNuevo.requestFocus();
            txtNuevo.setText("");
            cargarTabla();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "LA OPERACION HA SIDO CANCELADA", "CANCELADO", INFORMATION_MESSAGE);
        }
       
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void cbxProvinciasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciasItemStateChanged
        if(cbxProvincias.getItemCount()>0)
        {
            prvnc = ctrlLugar.leer(cbxProvincias.getSelectedItem().toString(), 5);
            listaDepartamentos = ctrlLugar.leerTodosDe(prvnc);
            cargarCombo(cbxDepartamentos, listaDepartamentos);
        }
    }//GEN-LAST:event_cbxProvinciasItemStateChanged

    private void cbxProvinciasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxProvinciasFocusGained
        btnGrabar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBorrar.setEnabled(false);
    }//GEN-LAST:event_cbxProvinciasFocusGained

    private void cbxDepartamentosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbxDepartamentosFocusGained
        btnGrabar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBorrar.setEnabled(false);
    }//GEN-LAST:event_cbxDepartamentosFocusGained

    private void cbxDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentosItemStateChanged
        if(cbxDepartamentos.getItemCount() > 0)
        {
            dprtmnt = ctrlLugar.leer(cbxDepartamentos.getSelectedItem().toString(), 4);
            listaLocalidades = ctrlLugar.leerTodosDe(dprtmnt);
            cargarCombo(cbxLocalidades, listaLocalidades);
        }
    }//GEN-LAST:event_cbxDepartamentosItemStateChanged

    private void cbxLocalidadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLocalidadesItemStateChanged
        if(cbxLocalidades.getItemCount() > 0)
        {
            lcldd = ctrlLugar.leer(cbxLocalidades.getSelectedItem().toString(), 3);
            cargarTabla();
        }
    }//GEN-LAST:event_cbxLocalidadesItemStateChanged

    public void cargarTabla()
    {
        if(lcldd != null)
        {
            String[] fila = new String[1];
            List<String> listaLugares = null;

            listaLugares = ctrlLugar.leerTodosDe(lcldd);

            modeloTabla.setColumnCount(0);
            modeloTabla.setRowCount(0);
            modeloTabla.addColumn("BARRIOS");

            for(int i=0; i<listaLugares.size(); i++)
            {
                fila[0] = listaLugares.get(i);
                modeloTabla.addRow(fila);
            }
            tabla.setModel(modeloTabla);
        }
    }
    
    public void cargarCombo(JComboBox cbx, List lst)
    {
        cbx.removeAllItems();
        for(int i=0; i<lst.size(); i++)
        {
            cbx.addItem(lst.get(i).toString());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxDepartamentos;
    private javax.swing.JComboBox<String> cbxLocalidades;
    private javax.swing.JComboBox<String> cbxProvincias;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDepto;
    private javax.swing.JLabel lblLocalidad;
    private javax.swing.JLabel lblModificando;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNuevo;
    // End of variables declaration//GEN-END:variables
}
