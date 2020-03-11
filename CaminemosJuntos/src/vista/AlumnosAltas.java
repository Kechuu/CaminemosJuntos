/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlAlumno;
import controlador.CtrlDiagnostico;
import controlador.CtrlAlumnoDiagnostico;
import controlador.CtrlDomicilio;
import controlador.CtrlEdificio;
import controlador.CtrlLugarDomicilio;
import controlador.CtrlLugarPrestacion;
import controlador.CtrlPrestacion;
import controlador.CtrlNivel;
import controlador.CtrlObraSocial;
import controlador.CtrlPersona;
import controlador.CtrlTelefono;
import controlador.CtrlTipoDocumento;
import controlador.CtrlTipoTelefono;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.AlumnoDiagnostico;
import modelo.Diagnostico;
import modelo.Domicilio;
import modelo.Edificio;
import modelo.LugarDomicilio;
import modelo.Persona;
import modelo.Prestacion;
import modelo.Telefono;

/**
 *
 * @author javier
 */
public class AlumnosAltas extends javax.swing.JInternalFrame {
    
    CtrlTipoDocumento ctrlTipoDocumento;
    CtrlTipoTelefono ctrlTipoTelefono;
    CtrlTelefono ctrlTelefono;
    CtrlNivel ctrlNivel;
    CtrlObraSocial ctrlObraSocial;
    CtrlPersona ctrlPersona;
    CtrlDomicilio ctrlDomicilio;
    CtrlEdificio ctrlEdificio;
    CtrlAlumno ctrlAlumno;
    CtrlLugarDomicilio ctrlLugarDomicilio;
    CtrlLugarPrestacion ctrlLugarPrestacion;
    CtrlPrestacion ctrlPrestacion;
    CtrlDiagnostico ctrlDiagnostico;
    CtrlAlumnoDiagnostico ctrlAlumnoDiagnostico;
    String[] lineaTablaTelefonos = new String[3];
    String[] lineaDiagnosticos = new String[2];
    DefaultTableModel modeloTelefonosMadre, modeloTelefonosPadre, modeloTelefonosTutor;
    DefaultTableModel modeloDiagnosticos, modeloDiagnosticosAlumno;
    
    Persona persona, madre, padre, tutor, persAlumno;
    Alumno alumno;
    LugarDomicilio prvncA, dprtmntA, lclddA, brrA, cllA;
    LugarDomicilio prvncM, dprtmntM, lclddM, brrM, cllM;
    LugarDomicilio prvncP, dprtmntP, lclddP, brrP, cllP;
    LugarDomicilio prvncT, dprtmntT, lclddT, brrT, cllT;
    List<String> lista;
    
    public AlumnosAltas(Connection cnx) {
        initComponents();
        ctrlTipoDocumento = new CtrlTipoDocumento(cnx);
        ctrlTipoTelefono = new CtrlTipoTelefono(cnx);
        ctrlTelefono = new CtrlTelefono(cnx);
        ctrlNivel = new CtrlNivel(cnx);
        ctrlObraSocial = new CtrlObraSocial(cnx);
        ctrlPersona = new CtrlPersona(cnx);
        ctrlDomicilio = new CtrlDomicilio(cnx);
        ctrlEdificio = new CtrlEdificio(cnx);
        ctrlAlumno = new CtrlAlumno(cnx);
        ctrlAlumnoDiagnostico = new CtrlAlumnoDiagnostico(cnx);
        ctrlLugarDomicilio = new CtrlLugarDomicilio(cnx);
        ctrlLugarPrestacion = new CtrlLugarPrestacion(cnx);
        ctrlPrestacion = new CtrlPrestacion(cnx);
        ctrlDiagnostico = new CtrlDiagnostico(cnx);
        
        modeloTelefonosMadre = (DefaultTableModel)tblTelefonosMadre.getModel();
        modeloTelefonosPadre = (DefaultTableModel)tblTelefonosPadre.getModel();
        modeloTelefonosTutor = (DefaultTableModel)tblTelefonosTutor.getModel();
        modeloDiagnosticos = (DefaultTableModel)tblDiagnosticos.getModel();
        modeloDiagnosticosAlumno = (DefaultTableModel)tblDiagnosticoAlumno.getModel();

        
        lista = ctrlLugarDomicilio.leerTodos(5);
        cargarCombo(cbxProvinciaAlumno, lista);
        cargarCombo(cbxProvinciaMadre, lista);
        cargarCombo(cbxProvinciaPadre, lista);
        cargarCombo(cbxProvinciaTutor, lista);
        
        lista = ctrlLugarDomicilio.leerTodos(4);
        cargarCombo(cbxLugarNacimiento, lista);

        lista = ctrlTipoDocumento.leerTodos();
        cargarCombo(cbxTipoDocAlumno, lista);
        cargarCombo(cbxTipoDocMadre, lista);
        cargarCombo(cbxTipoDocPadre, lista);
        cargarCombo(cbxTipoDocTutor, lista);
        
        lista = ctrlLugarPrestacion.leerTodos();
        cargarCombo(cbxLugar, lista);

        lista = ctrlNivel.leerTodos();
        cargarCombo(cbxNivel, lista);
        
        lista = ctrlObraSocial.leerTodos();
        cargarCombo(cbxObraSocial, lista);
        
        lista = ctrlTipoTelefono.leerTodos();
        cargarCombo(cbxTipoTelefonoMadre, lista);
        cargarCombo(cbxTipoTelefonoPadre, lista);
        cargarCombo(cbxTipoTelefonoTutor, lista);
        
        limpiarCombo(cbxDepartamentoAlumno);
        limpiarCombo(cbxDepartamentoMadre);
        limpiarCombo(cbxDepartamentoPadre);
        limpiarCombo(cbxDepartamentoTutor);

        limpiarCombo(cbxLocalidadAlumno);
        limpiarCombo(cbxLocalidadMadre);
        limpiarCombo(cbxLocalidadPadre);
        limpiarCombo(cbxLocalidadTutor);

        limpiarCombo(cbxBarrioAlumno);
        limpiarCombo(cbxBarrioMadre);
        limpiarCombo(cbxBarrioPadre);
        limpiarCombo(cbxBarrioTutor);

        limpiarCombo(cbxCalleAlumno);
        limpiarCombo(cbxCalleMadre);
        limpiarCombo(cbxCallePadre);
        limpiarCombo(cbxCalleTutor);
        
        
        cargarDiagnosticos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpSexoAlumno = new javax.swing.ButtonGroup();
        grpPension = new javax.swing.ButtonGroup();
        grpAsignacion = new javax.swing.ButtonGroup();
        grpIntegrado = new javax.swing.ButtonGroup();
        grpSexoMadre = new javax.swing.ButtonGroup();
        grpSexoPadre = new javax.swing.ButtonGroup();
        grpSexoTutor = new javax.swing.ButtonGroup();
        fichas = new javax.swing.JTabbedPane();
        pnlAlumno = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        pnlDatosPersonalesAlumno = new javax.swing.JPanel();
        lblTipoDocAlumno = new javax.swing.JLabel();
        cbxTipoDocAlumno = new javax.swing.JComboBox<>();
        lblDNIAlumno = new javax.swing.JLabel();
        txtDNIAlumno = new javax.swing.JTextField();
        lblApellidoAlumno = new javax.swing.JLabel();
        txtApellidoAlumno = new javax.swing.JTextField();
        lblNombreAlumno = new javax.swing.JLabel();
        txtNombreAlumno = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        lblLugarNacimiento = new javax.swing.JLabel();
        cbxLugarNacimiento = new javax.swing.JComboBox<>();
        pnlSexo = new javax.swing.JPanel();
        optOtroAlumno = new javax.swing.JRadioButton();
        optMujerAlumno = new javax.swing.JRadioButton();
        optHombreAlumno = new javax.swing.JRadioButton();
        pnlDomicilioAlumno = new javax.swing.JPanel();
        lblProvinciaAlumno = new javax.swing.JLabel();
        cbxProvinciaAlumno = new javax.swing.JComboBox<>();
        lblDepartamentoAlumno = new javax.swing.JLabel();
        cbxDepartamentoAlumno = new javax.swing.JComboBox<>();
        lblLocalidadAlumno = new javax.swing.JLabel();
        cbxLocalidadAlumno = new javax.swing.JComboBox<>();
        lblBarrioAlumno = new javax.swing.JLabel();
        cbxBarrioAlumno = new javax.swing.JComboBox<>();
        lblCalleAlumno = new javax.swing.JLabel();
        cbxCalleAlumno = new javax.swing.JComboBox<>();
        lblNroAlumno = new javax.swing.JLabel();
        txtNroAlumno = new javax.swing.JTextField();
        lblTorreAlumno = new javax.swing.JLabel();
        txtTorreAlumno = new javax.swing.JTextField();
        lblPisoAlumno = new javax.swing.JLabel();
        txtPisoAlumno = new javax.swing.JTextField();
        lblDeptoAlumno = new javax.swing.JLabel();
        txtDeptoAlumno = new javax.swing.JTextField();
        pnlDatosAcademicos = new javax.swing.JPanel();
        lblLegajo = new javax.swing.JLabel();
        txtLegajo = new javax.swing.JTextField();
        lblObraSocial = new javax.swing.JLabel();
        lblCUD = new javax.swing.JLabel();
        txtCUD = new com.toedter.calendar.JDateChooser();
        jPanel23 = new javax.swing.JPanel();
        optAsignacionNO = new javax.swing.JRadioButton();
        optAsignacionSI = new javax.swing.JRadioButton();
        lblAsignacion = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        optPensionNO = new javax.swing.JRadioButton();
        optPensionSI = new javax.swing.JRadioButton();
        lblPension = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        optIntegradoNO = new javax.swing.JRadioButton();
        optIntegradoSI = new javax.swing.JRadioButton();
        lblIntegrado = new javax.swing.JLabel();
        lblLugar = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        cbxLugar = new javax.swing.JComboBox<>();
        cbxNivel = new javax.swing.JComboBox<>();
        scrollDiagnosticos = new javax.swing.JScrollPane();
        tblDiagnosticos = new javax.swing.JTable();
        scrollDiagnosticoAlumno = new javax.swing.JScrollPane();
        tblDiagnosticoAlumno = new javax.swing.JTable();
        btnAgregarDiagnostico = new javax.swing.JButton();
        btnQuitarDiagnostico = new javax.swing.JButton();
        cbxObraSocial = new javax.swing.JComboBox<>();
        pnlMadre = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblDNIMadre = new javax.swing.JLabel();
        txtDNIMadre = new javax.swing.JTextField();
        lblApellidoMadre = new javax.swing.JLabel();
        txtApellidoMadre = new javax.swing.JTextField();
        lblNombreMadre = new javax.swing.JLabel();
        txtNombreMadre = new javax.swing.JTextField();
        lblFechaNacimientoMadre = new javax.swing.JLabel();
        txtFechaNacimientoMadre = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        optOtroMadre = new javax.swing.JRadioButton();
        optMujerMadre = new javax.swing.JRadioButton();
        optHombreMadre = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        cbxTipoTelefonoMadre = new javax.swing.JComboBox<>();
        txtTelefonoMadre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCodAreaMadre = new javax.swing.JTextField();
        lblCodAreaMadre = new javax.swing.JLabel();
        btnAgregarMadre = new javax.swing.JButton();
        btnQuitarMadre = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTelefonosMadre = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.
            }
        };
        lblTipoDocMadre = new javax.swing.JLabel();
        cbxTipoDocMadre = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        chkEsTutorMadre = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        lblProvinciaMadre = new javax.swing.JLabel();
        cbxProvinciaMadre = new javax.swing.JComboBox<>();
        lblDepartamentoMadre = new javax.swing.JLabel();
        cbxDepartamentoMadre = new javax.swing.JComboBox<>();
        lblLocalidadMadre = new javax.swing.JLabel();
        cbxLocalidadMadre = new javax.swing.JComboBox<>();
        lblBarrioMadre = new javax.swing.JLabel();
        cbxBarrioMadre = new javax.swing.JComboBox<>();
        lblCalleMadre = new javax.swing.JLabel();
        cbxCalleMadre = new javax.swing.JComboBox<>();
        lblNroMadre = new javax.swing.JLabel();
        txtNroMadre = new javax.swing.JTextField();
        lblTorreMadre = new javax.swing.JLabel();
        txtTorreMadre = new javax.swing.JTextField();
        lblPisoMadre = new javax.swing.JLabel();
        txtPisoMadre = new javax.swing.JTextField();
        lblDeptoMadre = new javax.swing.JLabel();
        txtDeptoMadre = new javax.swing.JTextField();
        pnlPadre = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblDNIPadre = new javax.swing.JLabel();
        txtDNIPadre = new javax.swing.JTextField();
        lblApellidoPadre = new javax.swing.JLabel();
        txtApellidoPadre = new javax.swing.JTextField();
        lblNombrePadre = new javax.swing.JLabel();
        txtNombrePadre = new javax.swing.JTextField();
        lblFechaNacimientoPadre = new javax.swing.JLabel();
        txtFechaNacimientoPadre = new com.toedter.calendar.JDateChooser();
        jPanel13 = new javax.swing.JPanel();
        optOtroPadre = new javax.swing.JRadioButton();
        optMujerPadre = new javax.swing.JRadioButton();
        optHombrePadre = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        cbxTipoTelefonoPadre = new javax.swing.JComboBox<>();
        txtTelefonoPadre = new javax.swing.JTextField();
        lblTelefonoPadre = new javax.swing.JLabel();
        txtCodAreaPadre = new javax.swing.JTextField();
        lblCodAreaPadre = new javax.swing.JLabel();
        btnAgregarPadre = new javax.swing.JButton();
        btnQuitarPadre = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTelefonosPadre = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.
            }
        };
        lblTipoDocPadre = new javax.swing.JLabel();
        cbxTipoDocPadre = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        chkEsTutorPadre = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        lblProvinciaPadre = new javax.swing.JLabel();
        cbxProvinciaPadre = new javax.swing.JComboBox<>();
        lblDepartamentoPadre = new javax.swing.JLabel();
        cbxDepartamentoPadre = new javax.swing.JComboBox<>();
        lblLocalidadPadre = new javax.swing.JLabel();
        cbxLocalidadPadre = new javax.swing.JComboBox<>();
        lblBarrioPadre = new javax.swing.JLabel();
        cbxBarrioPadre = new javax.swing.JComboBox<>();
        lblCallePadre = new javax.swing.JLabel();
        cbxCallePadre = new javax.swing.JComboBox<>();
        lblNroPadre = new javax.swing.JLabel();
        lblTorrePadre = new javax.swing.JLabel();
        txtTorrePadre = new javax.swing.JTextField();
        lblPisoPadre = new javax.swing.JLabel();
        txtPisoPadre = new javax.swing.JTextField();
        lblDeptoPadre = new javax.swing.JLabel();
        txtDeptoPadre = new javax.swing.JTextField();
        txtNroPadre = new javax.swing.JTextField();
        pnlTutor = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblDNITutor = new javax.swing.JLabel();
        txtDNITutor = new javax.swing.JTextField();
        lblApellidoTutor = new javax.swing.JLabel();
        txtApellidoTutor = new javax.swing.JTextField();
        lblNombreTutor = new javax.swing.JLabel();
        txtNombreTutor = new javax.swing.JTextField();
        lblFechaNacimientoTutor = new javax.swing.JLabel();
        txtFechaNacimientoTutor = new com.toedter.calendar.JDateChooser();
        jPanel16 = new javax.swing.JPanel();
        optOtroTutor = new javax.swing.JRadioButton();
        optMujerTutor = new javax.swing.JRadioButton();
        optHombreTutor = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        cbxTipoTelefonoTutor = new javax.swing.JComboBox<>();
        txtTelefonoTutor = new javax.swing.JTextField();
        lblTelefonoTutor = new javax.swing.JLabel();
        txtCodAreaTutor = new javax.swing.JTextField();
        lblCodAreaTutor = new javax.swing.JLabel();
        btnAgregarTutor = new javax.swing.JButton();
        btnQuitarTutor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTelefonosTutor = new JTable(){
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {

                return false; //Las celdas no son editables.
            }
        };
        lblTipoDocTutor = new javax.swing.JLabel();
        cbxTipoDocTutor = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        lblProvinciaTutor = new javax.swing.JLabel();
        cbxProvinciaTutor = new javax.swing.JComboBox<>();
        lblDepartamentoTutor = new javax.swing.JLabel();
        cbxDepartamentoTutor = new javax.swing.JComboBox<>();
        lblLocalidadTutor = new javax.swing.JLabel();
        cbxLocalidadTutor = new javax.swing.JComboBox<>();
        lblBarrioTutor = new javax.swing.JLabel();
        cbxBarrioTutor = new javax.swing.JComboBox<>();
        lblCalleTutor = new javax.swing.JLabel();
        cbxCalleTutor = new javax.swing.JComboBox<>();
        lblNroTutor = new javax.swing.JLabel();
        txtNroTutor = new javax.swing.JTextField();
        lblTorreTutor = new javax.swing.JLabel();
        txtTorreTutor = new javax.swing.JTextField();
        lblPisoTutor = new javax.swing.JLabel();
        txtPisoTutor = new javax.swing.JTextField();
        lblDeptoTutor = new javax.swing.JLabel();
        txtDeptoTutor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnGrabar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setTitle("INSCRIPCION DE ALUMNOS");
        setPreferredSize(new java.awt.Dimension(800, 700));

        fichas.setBackground(java.awt.Color.lightGray);
        fichas.setMaximumSize(null);
        fichas.setPreferredSize(new java.awt.Dimension(799, 505));
        fichas.setRequestFocusEnabled(false);

        pnlAlumno.setPreferredSize(new java.awt.Dimension(772, 750));

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        pnlDatosPersonalesAlumno.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Datos Personales del Alumno")), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnlDatosPersonalesAlumno.setPreferredSize(new java.awt.Dimension(726, 180));
        pnlDatosPersonalesAlumno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTipoDocAlumno.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblTipoDocAlumno.setText("Tipo Doc");
        pnlDatosPersonalesAlumno.add(lblTipoDocAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        pnlDatosPersonalesAlumno.add(cbxTipoDocAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 84, -1));

        lblDNIAlumno.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblDNIAlumno.setText("Nro");
        pnlDatosPersonalesAlumno.add(lblDNIAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        txtDNIAlumno.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtDNIAlumno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDNIAlumnoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDNIAlumnoFocusLost(evt);
            }
        });
        txtDNIAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDNIAlumnoMouseClicked(evt);
            }
        });
        txtDNIAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDNIAlumnoKeyPressed(evt);
            }
        });
        pnlDatosPersonalesAlumno.add(txtDNIAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 100, -1));

        lblApellidoAlumno.setText("Apellido");
        pnlDatosPersonalesAlumno.add(lblApellidoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 25));
        pnlDatosPersonalesAlumno.add(txtApellidoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 150, -1));

        lblNombreAlumno.setText("Nombres");
        pnlDatosPersonalesAlumno.add(lblNombreAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, 25));
        pnlDatosPersonalesAlumno.add(txtNombreAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 200, -1));

        lblFechaNacimiento.setText("Fecha de Nacimiento");
        pnlDatosPersonalesAlumno.add(lblFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 25));
        pnlDatosPersonalesAlumno.add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 135, -1));

        lblLugarNacimiento.setText("Lugar de Nacimiento");
        pnlDatosPersonalesAlumno.add(lblLugarNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, -1, 25));

        pnlDatosPersonalesAlumno.add(cbxLugarNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 170, -1));

        pnlSexo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpSexoAlumno.add(optOtroAlumno);
        optOtroAlumno.setText("Otro");

        grpSexoAlumno.add(optMujerAlumno);
        optMujerAlumno.setText("Mujer");
        optMujerAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optMujerAlumnoActionPerformed(evt);
            }
        });

        grpSexoAlumno.add(optHombreAlumno);
        optHombreAlumno.setText("Hombre");

        javax.swing.GroupLayout pnlSexoLayout = new javax.swing.GroupLayout(pnlSexo);
        pnlSexo.setLayout(pnlSexoLayout);
        pnlSexoLayout.setHorizontalGroup(
            pnlSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSexoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optHombreAlumno)
                .addGap(18, 18, 18)
                .addComponent(optMujerAlumno)
                .addGap(18, 18, 18)
                .addComponent(optOtroAlumno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSexoLayout.setVerticalGroup(
            pnlSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSexoLayout.createSequentialGroup()
                .addGroup(pnlSexoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optHombreAlumno)
                    .addComponent(optMujerAlumno)
                    .addComponent(optOtroAlumno))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pnlDatosPersonalesAlumno.add(pnlSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, 30));

        pnlDomicilioAlumno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domicilio del Alumno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        lblProvinciaAlumno.setText("Provincia");

        cbxProvinciaAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaAlumnoItemStateChanged(evt);
            }
        });

        lblDepartamentoAlumno.setText("Departamento");

        cbxDepartamentoAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoAlumnoItemStateChanged(evt);
            }
        });

        lblLocalidadAlumno.setText("Localidad");

        cbxLocalidadAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLocalidadAlumnoItemStateChanged(evt);
            }
        });

        lblBarrioAlumno.setText("Barrio");

        cbxBarrioAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBarrioAlumnoItemStateChanged(evt);
            }
        });

        lblCalleAlumno.setText("Calle/Mza");

        cbxCalleAlumno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCalleAlumnoItemStateChanged(evt);
            }
        });

        lblNroAlumno.setText("Nro/Lote");

        lblTorreAlumno.setText("Torre");

        lblPisoAlumno.setText("Piso");

        lblDeptoAlumno.setText("Depto");

        javax.swing.GroupLayout pnlDomicilioAlumnoLayout = new javax.swing.GroupLayout(pnlDomicilioAlumno);
        pnlDomicilioAlumno.setLayout(pnlDomicilioAlumnoLayout);
        pnlDomicilioAlumnoLayout.setHorizontalGroup(
            pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDomicilioAlumnoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDomicilioAlumnoLayout.createSequentialGroup()
                        .addGroup(pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDomicilioAlumnoLayout.createSequentialGroup()
                                .addComponent(lblCalleAlumno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxCalleAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroAlumno))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDomicilioAlumnoLayout.createSequentialGroup()
                                .addComponent(lblLocalidadAlumno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLocalidadAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblBarrioAlumno)))
                        .addGroup(pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDomicilioAlumnoLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(txtNroAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTorreAlumno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTorreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPisoAlumno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPisoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDeptoAlumno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDeptoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDomicilioAlumnoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxBarrioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlDomicilioAlumnoLayout.createSequentialGroup()
                        .addComponent(lblProvinciaAlumno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxProvinciaAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDepartamentoAlumno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDepartamentoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDomicilioAlumnoLayout.setVerticalGroup(
            pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDomicilioAlumnoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProvinciaAlumno)
                    .addComponent(cbxProvinciaAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartamentoAlumno)
                    .addComponent(cbxDepartamentoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalidadAlumno)
                    .addComponent(cbxLocalidadAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBarrioAlumno)
                    .addComponent(cbxBarrioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDomicilioAlumnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCalleAlumno)
                    .addComponent(cbxCalleAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNroAlumno)
                    .addComponent(txtNroAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTorreAlumno)
                    .addComponent(txtTorreAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPisoAlumno)
                    .addComponent(txtPisoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeptoAlumno)
                    .addComponent(txtDeptoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDatosAcademicos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Académicos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        pnlDatosAcademicos.setPreferredSize(new java.awt.Dimension(726, 335));

        lblLegajo.setText("Legajo");

        lblObraSocial.setText("Obra Social");

        lblCUD.setText("Vto CUD");

        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpAsignacion.add(optAsignacionNO);
        optAsignacionNO.setSelected(true);
        optAsignacionNO.setText("NO");
        optAsignacionNO.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        optAsignacionNO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        grpAsignacion.add(optAsignacionSI);
        optAsignacionSI.setText("SI");
        optAsignacionSI.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        optAsignacionSI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblAsignacion.setText("Asignacion Universal");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(lblAsignacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optAsignacionSI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optAsignacionNO))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(optAsignacionNO)
                .addComponent(optAsignacionSI)
                .addComponent(lblAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpPension.add(optPensionNO);
        optPensionNO.setSelected(true);
        optPensionNO.setText("NO");
        optPensionNO.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        optPensionNO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        grpPension.add(optPensionSI);
        optPensionSI.setText("SI");
        optPensionSI.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        optPensionSI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblPension.setText("Pensión");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addComponent(lblPension)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optPensionSI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optPensionNO))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(optPensionNO)
                .addComponent(optPensionSI)
                .addComponent(lblPension, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpIntegrado.add(optIntegradoNO);
        optIntegradoNO.setSelected(true);
        optIntegradoNO.setText("NO");
        optIntegradoNO.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        optIntegradoNO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        optIntegradoNO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optIntegradoNOActionPerformed(evt);
            }
        });

        grpIntegrado.add(optIntegradoSI);
        optIntegradoSI.setText("SI");
        optIntegradoSI.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        optIntegradoSI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        optIntegradoSI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optIntegradoSIActionPerformed(evt);
            }
        });

        lblIntegrado.setText("Integrado");

        lblLugar.setText("Lugar");

        lblNivel.setText("Nivel");

        cbxLugar.setEnabled(false);

        cbxNivel.setEnabled(false);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(lblIntegrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optIntegradoSI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(optIntegradoNO, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(lblLugar)
                        .addGap(5, 5, 5)
                        .addComponent(cbxLugar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNivel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optIntegradoNO)
                    .addComponent(optIntegradoSI)
                    .addComponent(lblIntegrado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLugar)
                    .addComponent(lblNivel)
                    .addComponent(cbxLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tblDiagnosticos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "diagnostico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDiagnosticos.getTableHeader().setReorderingAllowed(false);
        scrollDiagnosticos.setViewportView(tblDiagnosticos);

        tblDiagnosticoAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DIAGNOSTICO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDiagnosticoAlumno.getTableHeader().setReorderingAllowed(false);
        scrollDiagnosticoAlumno.setViewportView(tblDiagnosticoAlumno);

        btnAgregarDiagnostico.setText("<< Agregar");
        btnAgregarDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDiagnosticoActionPerformed(evt);
            }
        });

        btnQuitarDiagnostico.setText("Quitar >>");
        btnQuitarDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarDiagnosticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosAcademicosLayout = new javax.swing.GroupLayout(pnlDatosAcademicos);
        pnlDatosAcademicos.setLayout(pnlDatosAcademicosLayout);
        pnlDatosAcademicosLayout.setHorizontalGroup(
            pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                        .addComponent(scrollDiagnosticoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregarDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuitarDiagnostico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollDiagnosticos, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                        .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                                .addComponent(lblObraSocial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(lblCUD)
                                .addGap(4, 4, 4)
                                .addComponent(txtCUD, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                                .addComponent(lblLegajo)
                                .addGap(4, 4, 4)
                                .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        pnlDatosAcademicosLayout.setVerticalGroup(
            pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLegajo))
                .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCUD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDatosAcademicosLayout.createSequentialGroup()
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDatosAcademicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollDiagnosticos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollDiagnosticoAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosAcademicosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarDiagnostico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarDiagnostico)
                        .addGap(40, 40, 40))))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(pnlDatosPersonalesAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlDomicilioAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlDatosAcademicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatosPersonalesAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDomicilioAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDatosAcademicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        pnlAlumno.setViewportView(jPanel7);

        fichas.addTab("Alumno", pnlAlumno);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales de la Madre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        lblDNIMadre.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblDNIMadre.setText("Nro");

        txtDNIMadre.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtDNIMadre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDNIMadreFocusLost(evt);
            }
        });
        txtDNIMadre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDNIMadreMouseClicked(evt);
            }
        });
        txtDNIMadre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDNIMadreKeyPressed(evt);
            }
        });

        lblApellidoMadre.setText("Apellido");

        lblNombreMadre.setText("Nombres");

        lblFechaNacimientoMadre.setText("Fecha de Nacimiento");

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpSexoMadre.add(optOtroMadre);
        optOtroMadre.setText("Otro");

        grpSexoMadre.add(optMujerMadre);
        optMujerMadre.setText("Mujer");
        optMujerMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optMujerMadreActionPerformed(evt);
            }
        });

        grpSexoMadre.add(optHombreMadre);
        optHombreMadre.setText("Hombre");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optHombreMadre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optMujerMadre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optOtroMadre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optHombreMadre)
                    .addComponent(optMujerMadre)
                    .addComponent(optOtroMadre))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Telefonos")));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel4.setText("Numero");

        lblCodAreaMadre.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCodAreaMadre.setText("Cod Area");

        btnAgregarMadre.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnAgregarMadre.setText("Agregar");
        btnAgregarMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMadreActionPerformed(evt);
            }
        });

        btnQuitarMadre.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnQuitarMadre.setText("Quitar");
        btnQuitarMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarMadreActionPerformed(evt);
            }
        });

        tblTelefonosMadre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Telefono", "Cod Area", "Numero"
            }
        ));
        tblTelefonosMadre.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTelefonosMadre.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblTelefonosMadre);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCodAreaMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTelefonoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxTipoTelefonoMadre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCodAreaMadre)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuitarMadre)
                    .addComponent(btnAgregarMadre))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cbxTipoTelefonoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodAreaMadre)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodAreaMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarMadre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarMadre)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTipoDocMadre.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblTipoDocMadre.setText("Tipo Doc");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkEsTutorMadre.setText("Es Tutor");
        chkEsTutorMadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEsTutorMadreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEsTutorMadre)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEsTutorMadre)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblFechaNacimientoMadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaNacimientoMadre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblApellidoMadre)
                                .addGap(4, 4, 4)
                                .addComponent(txtApellidoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblTipoDocMadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxTipoDocMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDNIMadre)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblNombreMadre)
                                .addGap(4, 4, 4)
                                .addComponent(txtNombreMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDNIMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDNIMadre)
                            .addComponent(txtDNIMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoDocMadre)
                            .addComponent(cbxTipoDocMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellidoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaNacimientoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimientoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domicilio de la Madre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        lblProvinciaMadre.setText("Provincia");

        cbxProvinciaMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaMadreItemStateChanged(evt);
            }
        });

        lblDepartamentoMadre.setText("Departamento");

        cbxDepartamentoMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoMadreItemStateChanged(evt);
            }
        });

        lblLocalidadMadre.setText("Localidad");

        cbxLocalidadMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLocalidadMadreItemStateChanged(evt);
            }
        });

        lblBarrioMadre.setText("Barrio");

        cbxBarrioMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBarrioMadreItemStateChanged(evt);
            }
        });

        lblCalleMadre.setText("Calle/Mza");

        cbxCalleMadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCalleMadreItemStateChanged(evt);
            }
        });

        lblNroMadre.setText("Nro/Lote");

        lblTorreMadre.setText("Torre");

        lblPisoMadre.setText("Piso");

        lblDeptoMadre.setText("Depto");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lblLocalidadMadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLocalidadMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBarrioMadre))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(lblCalleMadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxCalleMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNroMadre)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtNroMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTorreMadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTorreMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPisoMadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPisoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDeptoMadre)
                                .addGap(10, 10, 10)
                                .addComponent(txtDeptoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxBarrioMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lblProvinciaMadre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxProvinciaMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDepartamentoMadre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDepartamentoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProvinciaMadre)
                    .addComponent(cbxProvinciaMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartamentoMadre)
                    .addComponent(cbxDepartamentoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalidadMadre)
                    .addComponent(cbxLocalidadMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBarrioMadre)
                    .addComponent(cbxBarrioMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCalleMadre)
                    .addComponent(cbxCalleMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNroMadre)
                    .addComponent(txtNroMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTorreMadre)
                    .addComponent(txtTorreMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPisoMadre)
                    .addComponent(txtPisoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeptoMadre)
                    .addComponent(txtDeptoMadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMadreLayout = new javax.swing.GroupLayout(pnlMadre);
        pnlMadre.setLayout(pnlMadreLayout);
        pnlMadreLayout.setHorizontalGroup(
            pnlMadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMadreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMadreLayout.setVerticalGroup(
            pnlMadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMadreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        fichas.addTab("Madre", pnlMadre);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales del Padre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        lblDNIPadre.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblDNIPadre.setText("D.N.I.");

        txtDNIPadre.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtDNIPadre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDNIPadreFocusLost(evt);
            }
        });
        txtDNIPadre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDNIPadreMouseClicked(evt);
            }
        });
        txtDNIPadre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDNIPadreKeyPressed(evt);
            }
        });

        lblApellidoPadre.setText("Apellido");

        lblNombrePadre.setText("Nombres");

        lblFechaNacimientoPadre.setText("Fecha de Nacimiento");

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpSexoPadre.add(optOtroPadre);
        optOtroPadre.setText("Otro");

        grpSexoPadre.add(optMujerPadre);
        optMujerPadre.setText("Mujer");
        optMujerPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optMujerPadreActionPerformed(evt);
            }
        });

        grpSexoPadre.add(optHombrePadre);
        optHombrePadre.setText("Hombre");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optHombrePadre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optMujerPadre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optOtroPadre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optHombrePadre)
                    .addComponent(optMujerPadre)
                    .addComponent(optOtroPadre))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Telefonos")));

        lblTelefonoPadre.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblTelefonoPadre.setText("Numero");

        lblCodAreaPadre.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCodAreaPadre.setText("Cod Area");

        btnAgregarPadre.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnAgregarPadre.setText("Agregar");
        btnAgregarPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPadreActionPerformed(evt);
            }
        });

        btnQuitarPadre.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnQuitarPadre.setText("Quitar");
        btnQuitarPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarPadreActionPerformed(evt);
            }
        });

        tblTelefonosPadre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Telefono", "Cod Area", "Numero"
            }
        ));
        jScrollPane3.setViewportView(tblTelefonosPadre);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblCodAreaPadre)
                        .addGap(28, 28, 28)
                        .addComponent(lblTelefonoPadre))
                    .addComponent(cbxTipoTelefonoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCodAreaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefonoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuitarPadre)
                    .addComponent(btnAgregarPadre))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cbxTipoTelefonoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodAreaPadre)
                            .addComponent(lblTelefonoPadre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodAreaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarPadre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarPadre)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTipoDocPadre.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblTipoDocPadre.setText("Tipo Doc");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkEsTutorPadre.setText("Es Tutor");
        chkEsTutorPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEsTutorPadreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEsTutorPadre)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEsTutorPadre)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(lblFechaNacimientoPadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaNacimientoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 140, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(lblApellidoPadre)
                                .addGap(4, 4, 4)
                                .addComponent(txtApellidoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombrePadre)
                                .addGap(4, 4, 4)
                                .addComponent(txtNombrePadre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(lblTipoDocPadre)
                                .addGap(7, 7, 7)
                                .addComponent(cbxTipoDocPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDNIPadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDNIPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDNIPadre)
                            .addComponent(txtDNIPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoDocPadre)
                            .addComponent(cbxTipoDocPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblApellidoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombrePadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombrePadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaNacimientoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimientoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domicilio del Padre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        lblProvinciaPadre.setText("Provincia");

        cbxProvinciaPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaPadreItemStateChanged(evt);
            }
        });

        lblDepartamentoPadre.setText("Departamento");

        cbxDepartamentoPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoPadreItemStateChanged(evt);
            }
        });

        lblLocalidadPadre.setText("Localidad");

        cbxLocalidadPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLocalidadPadreItemStateChanged(evt);
            }
        });

        lblBarrioPadre.setText("Barrio");

        cbxBarrioPadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBarrioPadreItemStateChanged(evt);
            }
        });

        lblCallePadre.setText("Calle/Mza");

        cbxCallePadre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCallePadreItemStateChanged(evt);
            }
        });

        lblNroPadre.setText("Nro/Lote");

        lblTorrePadre.setText("Torre");

        lblPisoPadre.setText("Piso");

        lblDeptoPadre.setText("Depto");

        txtNroPadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroPadreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                                .addComponent(lblCallePadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxCallePadre, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNroPadre))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                                .addComponent(lblLocalidadPadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLocalidadPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBarrioPadre)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(txtNroPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTorrePadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTorrePadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPisoPadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPisoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDeptoPadre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDeptoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxBarrioPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(lblProvinciaPadre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxProvinciaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDepartamentoPadre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDepartamentoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProvinciaPadre)
                    .addComponent(cbxProvinciaPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartamentoPadre)
                    .addComponent(cbxDepartamentoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalidadPadre)
                    .addComponent(cbxLocalidadPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBarrioPadre)
                    .addComponent(cbxBarrioPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCallePadre)
                    .addComponent(cbxCallePadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNroPadre)
                    .addComponent(lblTorrePadre)
                    .addComponent(txtTorrePadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPisoPadre)
                    .addComponent(txtPisoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeptoPadre)
                    .addComponent(txtDeptoPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNroPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPadreLayout = new javax.swing.GroupLayout(pnlPadre);
        pnlPadre.setLayout(pnlPadreLayout);
        pnlPadreLayout.setHorizontalGroup(
            pnlPadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPadreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPadreLayout.setVerticalGroup(
            pnlPadreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPadreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        fichas.addTab("Padre", pnlPadre);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales del Tutor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        jPanel15.setPreferredSize(new java.awt.Dimension(725, 300));

        lblDNITutor.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblDNITutor.setText("Nro");

        txtDNITutor.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtDNITutor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDNITutorFocusLost(evt);
            }
        });
        txtDNITutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDNITutorMouseClicked(evt);
            }
        });
        txtDNITutor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDNITutorKeyPressed(evt);
            }
        });

        lblApellidoTutor.setText("Apellido");

        lblNombreTutor.setText("Nombres");

        lblFechaNacimientoTutor.setText("Fecha de Nacimiento");

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        grpSexoTutor.add(optOtroTutor);
        optOtroTutor.setText("Otro");

        grpSexoTutor.add(optMujerTutor);
        optMujerTutor.setText("Mujer");
        optMujerTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optMujerTutorActionPerformed(evt);
            }
        });

        grpSexoTutor.add(optHombreTutor);
        optHombreTutor.setText("Hombre");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optHombreTutor)
                .addGap(18, 18, 18)
                .addComponent(optMujerTutor)
                .addGap(18, 18, 18)
                .addComponent(optOtroTutor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optHombreTutor)
                    .addComponent(optMujerTutor)
                    .addComponent(optOtroTutor))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Telefonos")));

        lblTelefonoTutor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblTelefonoTutor.setText("Numero");

        lblCodAreaTutor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCodAreaTutor.setText("Cod Area");

        btnAgregarTutor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnAgregarTutor.setText("Agregar");
        btnAgregarTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTutorActionPerformed(evt);
            }
        });

        btnQuitarTutor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnQuitarTutor.setText("Quitar");
        btnQuitarTutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarTutorActionPerformed(evt);
            }
        });

        tblTelefonosTutor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Telefono", "Cod Area", "Numero"
            }
        ));
        jScrollPane1.setViewportView(tblTelefonosTutor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxTipoTelefonoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCodAreaTutor)
                            .addComponent(txtCodAreaTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblTelefonoTutor)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuitarTutor)
                    .addComponent(btnAgregarTutor))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(cbxTipoTelefonoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodAreaTutor)
                            .addComponent(lblTelefonoTutor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodAreaTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarTutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarTutor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTipoDocTutor.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblTipoDocTutor.setText("Tipo Doc");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(lblTipoDocTutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxTipoDocTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDNITutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDNITutor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(lblFechaNacimientoTutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaNacimientoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(130, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(lblApellidoTutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNombreTutor)
                                .addGap(12, 12, 12)
                                .addComponent(txtNombreTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNITutor)
                    .addComponent(txtDNITutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoDocTutor)
                    .addComponent(cbxTipoDocTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaNacimientoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaNacimientoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Domicilio del Tutor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        lblProvinciaTutor.setText("Provincia");

        cbxProvinciaTutor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaTutorItemStateChanged(evt);
            }
        });

        lblDepartamentoTutor.setText("Departamento");

        cbxDepartamentoTutor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoTutorItemStateChanged(evt);
            }
        });

        lblLocalidadTutor.setText("Localidad");

        cbxLocalidadTutor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLocalidadTutorItemStateChanged(evt);
            }
        });

        lblBarrioTutor.setText("Barrio");

        cbxBarrioTutor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBarrioTutorItemStateChanged(evt);
            }
        });

        lblCalleTutor.setText("Calle/Mza");

        cbxCalleTutor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCalleTutorItemStateChanged(evt);
            }
        });

        lblNroTutor.setText("Nro/Lote");

        lblTorreTutor.setText("Torre");

        lblPisoTutor.setText("Piso");

        lblDeptoTutor.setText("Depto");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addComponent(lblCalleTutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxCalleTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroTutor))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addComponent(lblLocalidadTutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxLocalidadTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblBarrioTutor)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtNroTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTorreTutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTorreTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPisoTutor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPisoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxBarrioTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(lblProvinciaTutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxProvinciaTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDepartamentoTutor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDepartamentoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDeptoTutor)
                .addGap(10, 10, 10)
                .addComponent(txtDeptoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProvinciaTutor)
                    .addComponent(cbxProvinciaTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartamentoTutor)
                    .addComponent(cbxDepartamentoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalidadTutor)
                    .addComponent(cbxLocalidadTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBarrioTutor)
                    .addComponent(cbxBarrioTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCalleTutor)
                    .addComponent(cbxCalleTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNroTutor)
                    .addComponent(txtNroTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTorreTutor)
                    .addComponent(txtTorreTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPisoTutor)
                    .addComponent(txtPisoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeptoTutor)
                    .addComponent(txtDeptoTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlTutorLayout = new javax.swing.GroupLayout(pnlTutor);
        pnlTutor.setLayout(pnlTutorLayout);
        pnlTutorLayout.setHorizontalGroup(
            pnlTutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTutorLayout.setVerticalGroup(
            pnlTutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTutorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(419, 419, 419))
        );

        fichas.addTab("Tutor", pnlTutor);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnSalir.setText("salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(543, Short.MAX_VALUE)
                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGrabar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fichas, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fichas, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fichas.getAccessibleContext().setAccessibleName("Alumno");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCalleTutorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCalleTutorItemStateChanged
        if(cbxCalleTutor.getItemCount() > 0)
        {
            if(cbxCalleTutor.getSelectedIndex() == 0)
                cllT = null;
            else
                cllT = ctrlLugarDomicilio.leer(cbxCalleTutor.getSelectedItem().toString(), 1);
        }
    }//GEN-LAST:event_cbxCalleTutorItemStateChanged

    private void cbxBarrioTutorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBarrioTutorItemStateChanged
        if(cbxBarrioTutor.getItemCount() > 0)
        {
            if(cbxBarrioTutor.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxCalleTutor);
            }
            else
            {
                brrT = ctrlLugarDomicilio.leer(cbxBarrioTutor.getSelectedItem().toString(), 2);
                lista = ctrlLugarDomicilio.leerTodosDe(brrT);
                cargarCombo(cbxCalleTutor, lista);
            }
        }
    }//GEN-LAST:event_cbxBarrioTutorItemStateChanged

    private void cbxLocalidadTutorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLocalidadTutorItemStateChanged
        if(cbxLocalidadTutor.getItemCount() > 0)
        {
            if(cbxLocalidadTutor.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxBarrioTutor);
                limpiarCombo(cbxCalleTutor);
            }
            else
            {
                lclddT = ctrlLugarDomicilio.leer(cbxLocalidadTutor.getSelectedItem().toString(), 3);
                lista = ctrlLugarDomicilio.leerTodosDe(lclddT);
                cargarCombo(cbxBarrioTutor, lista);
            }
        }
    }//GEN-LAST:event_cbxLocalidadTutorItemStateChanged

    private void cbxDepartamentoTutorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoTutorItemStateChanged
        if(cbxDepartamentoTutor.getItemCount() > 0)
        {
            if(cbxDepartamentoTutor.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxLocalidadTutor);
                limpiarCombo(cbxBarrioTutor);
                limpiarCombo(cbxCalleTutor);
            }
            else
            {
                dprtmntT = ctrlLugarDomicilio.leer(cbxDepartamentoTutor.getSelectedItem().toString(), 4);
                lista = ctrlLugarDomicilio.leerTodosDe(dprtmntT);
                cargarCombo(cbxLocalidadTutor, lista);
            }
        }
    }//GEN-LAST:event_cbxDepartamentoTutorItemStateChanged

    private void cbxProvinciaTutorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaTutorItemStateChanged
        if(cbxProvinciaTutor.getItemCount() > 0)
        {
            if(cbxProvinciaTutor.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxDepartamentoTutor);
                limpiarCombo(cbxLocalidadTutor);
                limpiarCombo(cbxBarrioTutor);
                limpiarCombo(cbxCalleTutor);
            }
            else
            {
                prvncT = ctrlLugarDomicilio.leer(cbxProvinciaTutor.getSelectedItem().toString(), 5);
                lista = ctrlLugarDomicilio.leerTodosDe(prvncT);
                cargarCombo(cbxDepartamentoTutor, lista);
            }
        }
    }//GEN-LAST:event_cbxProvinciaTutorItemStateChanged

    private void optMujerTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optMujerTutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optMujerTutorActionPerformed

    private void txtNroPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroPadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroPadreActionPerformed

    private void cbxCallePadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCallePadreItemStateChanged
        if(cbxCallePadre.getItemCount() > 0)
        {
            if(cbxCallePadre.getSelectedIndex() == 0)
            cllP = null;
            else
            cllP = ctrlLugarDomicilio.leer(cbxCallePadre.getSelectedItem().toString(), 1);
        }
    }//GEN-LAST:event_cbxCallePadreItemStateChanged

    private void cbxBarrioPadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBarrioPadreItemStateChanged
        if(cbxBarrioPadre.getItemCount() > 0)
        {
            if(cbxBarrioPadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxCallePadre);
            }
            else
            {
                brrP = ctrlLugarDomicilio.leer(cbxBarrioPadre.getSelectedItem().toString(), 2);
                lista = ctrlLugarDomicilio.leerTodosDe(brrP);
                cargarCombo(cbxCallePadre, lista);
            }
        }
    }//GEN-LAST:event_cbxBarrioPadreItemStateChanged

    private void cbxLocalidadPadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLocalidadPadreItemStateChanged
        if(cbxLocalidadPadre.getItemCount() > 0)
        {
            if(cbxLocalidadPadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxBarrioPadre);
                limpiarCombo(cbxCallePadre);
            }
            else
            {
                lclddP = ctrlLugarDomicilio.leer(cbxLocalidadPadre.getSelectedItem().toString(), 3);
                lista = ctrlLugarDomicilio.leerTodosDe(lclddP);
                cargarCombo(cbxBarrioPadre, lista);
            }
        }
    }//GEN-LAST:event_cbxLocalidadPadreItemStateChanged

    private void cbxDepartamentoPadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoPadreItemStateChanged
        if(cbxDepartamentoPadre.getItemCount() > 0)
        {
            if(cbxDepartamentoPadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxLocalidadPadre);
                limpiarCombo(cbxBarrioPadre);
                limpiarCombo(cbxCallePadre);
            }
            else
            {
                dprtmntP = ctrlLugarDomicilio.leer(cbxDepartamentoPadre.getSelectedItem().toString(), 4);
                lista = ctrlLugarDomicilio.leerTodosDe(dprtmntP);
                cargarCombo(cbxLocalidadPadre, lista);
            }
        }
    }//GEN-LAST:event_cbxDepartamentoPadreItemStateChanged

    private void cbxProvinciaPadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaPadreItemStateChanged
        if(cbxProvinciaPadre.getItemCount() > 0)
        {
            if(cbxProvinciaPadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxDepartamentoPadre);
                limpiarCombo(cbxLocalidadPadre);
                limpiarCombo(cbxBarrioPadre);
                limpiarCombo(cbxCallePadre);
            }
            else
            {
                prvncP = ctrlLugarDomicilio.leer(cbxProvinciaPadre.getSelectedItem().toString(), 5);
                lista = ctrlLugarDomicilio.leerTodosDe(prvncP);
                cargarCombo(cbxDepartamentoPadre, lista);
            }
        }
    }//GEN-LAST:event_cbxProvinciaPadreItemStateChanged

    private void optMujerPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optMujerPadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optMujerPadreActionPerformed

    private void cbxCalleMadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCalleMadreItemStateChanged
        if(cbxCalleMadre.getItemCount() > 0)
        {
            if(cbxCalleMadre.getSelectedIndex() == 0)
                cllM = null;
            else
                cllM = ctrlLugarDomicilio.leer(cbxCalleMadre.getSelectedItem().toString(), 1);
        }
    }//GEN-LAST:event_cbxCalleMadreItemStateChanged

    private void cbxBarrioMadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBarrioMadreItemStateChanged
        if(cbxBarrioMadre.getItemCount() > 0)
        {
            if(cbxBarrioMadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxCalleMadre);
            }
            else
            {
                brrM = ctrlLugarDomicilio.leer(cbxBarrioMadre.getSelectedItem().toString(), 2);
                lista = ctrlLugarDomicilio.leerTodosDe(brrM);
                cargarCombo(cbxCalleMadre, lista);
            }
        }
    }//GEN-LAST:event_cbxBarrioMadreItemStateChanged

    private void cbxLocalidadMadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLocalidadMadreItemStateChanged
        if(cbxLocalidadMadre.getItemCount() > 0)
        {
            if(cbxLocalidadMadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxBarrioMadre);
                limpiarCombo(cbxCalleMadre);
            }
            else
            {
                lclddM = ctrlLugarDomicilio.leer(cbxLocalidadMadre.getSelectedItem().toString(), 3);
                lista = ctrlLugarDomicilio.leerTodosDe(lclddM);
                cargarCombo(cbxBarrioMadre, lista);
            }
        }
    }//GEN-LAST:event_cbxLocalidadMadreItemStateChanged

    private void cbxDepartamentoMadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoMadreItemStateChanged
        if(cbxDepartamentoMadre.getItemCount() > 0)
        {
            if(cbxDepartamentoMadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxLocalidadMadre);
                limpiarCombo(cbxBarrioMadre);
                limpiarCombo(cbxCalleMadre);
            }
            else
            {
                dprtmntM = ctrlLugarDomicilio.leer(cbxDepartamentoMadre.getSelectedItem().toString(), 4);
                lista = ctrlLugarDomicilio.leerTodosDe(dprtmntM);
                cargarCombo(cbxLocalidadMadre, lista);
            }
        }
    }//GEN-LAST:event_cbxDepartamentoMadreItemStateChanged

    private void cbxProvinciaMadreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaMadreItemStateChanged
        if(cbxProvinciaMadre.getItemCount() > 0)
        {
            if(cbxProvinciaMadre.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxDepartamentoMadre);
                limpiarCombo(cbxLocalidadMadre);
                limpiarCombo(cbxBarrioMadre);
                limpiarCombo(cbxCalleMadre);
            }
            else
            {
                prvncM = ctrlLugarDomicilio.leer(cbxProvinciaMadre.getSelectedItem().toString(), 5);
                lista = ctrlLugarDomicilio.leerTodosDe(prvncM);
                cargarCombo(cbxDepartamentoMadre, lista);
            }
        }
    }//GEN-LAST:event_cbxProvinciaMadreItemStateChanged

    private void optMujerMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optMujerMadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optMujerMadreActionPerformed

    private void cbxCalleAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCalleAlumnoItemStateChanged
        if(cbxCalleAlumno.getItemCount() > 0)
        {
            if(cbxCalleAlumno.getSelectedIndex() == 0)
            cllA = null;
            else
            cllA = ctrlLugarDomicilio.leer(cbxCalleAlumno.getSelectedItem().toString(), 1);
        }
    }//GEN-LAST:event_cbxCalleAlumnoItemStateChanged

    private void cbxBarrioAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBarrioAlumnoItemStateChanged
        if(cbxBarrioAlumno.getItemCount() > 0)
        {
            if(cbxBarrioAlumno.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxCalleAlumno);
            }
            else
            {
                brrA = ctrlLugarDomicilio.leer(cbxBarrioAlumno.getSelectedItem().toString(), 2);
                lista = ctrlLugarDomicilio.leerTodosDe(brrA);
                cargarCombo(cbxCalleAlumno, lista);
            }
        }
    }//GEN-LAST:event_cbxBarrioAlumnoItemStateChanged

    private void cbxLocalidadAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLocalidadAlumnoItemStateChanged
        if(cbxLocalidadAlumno.getItemCount() > 0)
        {
            if(cbxLocalidadAlumno.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxBarrioAlumno);
                limpiarCombo(cbxCalleAlumno);
            }
            else
            {
                lclddA = ctrlLugarDomicilio.leer(cbxLocalidadAlumno.getSelectedItem().toString(), 3);
                lista = ctrlLugarDomicilio.leerTodosDe(lclddA);
                cargarCombo(cbxBarrioAlumno, lista);
            }
        }
    }//GEN-LAST:event_cbxLocalidadAlumnoItemStateChanged

    private void cbxDepartamentoAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoAlumnoItemStateChanged
        if(cbxDepartamentoAlumno.getItemCount() > 0)
        {
            if(cbxDepartamentoAlumno.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxLocalidadAlumno);
                limpiarCombo(cbxBarrioAlumno);
                limpiarCombo(cbxCalleAlumno);
            }
            else
            {
                dprtmntA = ctrlLugarDomicilio.leer(cbxDepartamentoAlumno.getSelectedItem().toString(), 4);
                lista = ctrlLugarDomicilio.leerTodosDe(dprtmntA);
                cargarCombo(cbxLocalidadAlumno, lista);
            }
        }
    }//GEN-LAST:event_cbxDepartamentoAlumnoItemStateChanged

    private void cbxProvinciaAlumnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaAlumnoItemStateChanged
        if(cbxProvinciaAlumno.getItemCount() > 0)
        {
            if(cbxProvinciaAlumno.getSelectedIndex() == 0)
            {
                limpiarCombo(cbxDepartamentoAlumno);
                limpiarCombo(cbxLocalidadAlumno);
                limpiarCombo(cbxBarrioAlumno);
                limpiarCombo(cbxCalleAlumno);
            }
            else
            {
                prvncA = ctrlLugarDomicilio.leer(cbxProvinciaAlumno.getSelectedItem().toString(), 5);
                lista = ctrlLugarDomicilio.leerTodosDe(prvncA);
                cargarCombo(cbxDepartamentoAlumno, lista);
            }
        }
    }//GEN-LAST:event_cbxProvinciaAlumnoItemStateChanged

    private void optMujerAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optMujerAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optMujerAlumnoActionPerformed

    private void txtDNIAlumnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIAlumnoKeyPressed
        alumno = new Alumno();
        List<Diagnostico> diagnosticos;
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cargarDiagnosticos();
            persAlumno = ctrlPersona.leer(ctrlTipoDocumento.leer(cbxTipoDocAlumno.getSelectedItem().toString()).getIdtipoDocumento(), txtDNIAlumno.getText().trim());
            if(persAlumno == null)
            {
                //limpiarDatosPersonalesAlumno();
                datosPersonalesAlumnoOn();
                datosAcademicosOn();
                btnGrabar.setEnabled(true);
            }
            else
            {
                txtApellidoAlumno.setText(persAlumno.getApellido());
                txtNombreAlumno.setText(persAlumno.getNombre());
                txtFechaNacimiento.setDate(persAlumno.getFechaNacimiento());
                switch(persAlumno.getSexo())
                {
                    case 1: 
                    {
                        optHombreAlumno.setSelected(true);
                        break;
                    }
                    case 2: 
                    {
                        optMujerAlumno.setSelected(true);
                        break;
                    }
                    case 3: 
                    {
                        optOtroAlumno.setSelected(true);
                        break;
                    }
                }

                cllA = persAlumno.getDomicilio().getCalle();
                brrA = cllA.getDe();
                lclddA = brrA.getDe();
                dprtmntA = lclddA.getDe();
                prvncA = dprtmntA.getDe();
                cbxProvinciaAlumno.setSelectedItem(prvncA.getNombre());
                cbxDepartamentoAlumno.setSelectedItem(dprtmntA.getNombre());
                cbxLocalidadAlumno.setSelectedItem(lclddA.getNombre());
                cbxBarrioAlumno.setSelectedItem(brrA.getNombre());
                cbxCalleAlumno.setSelectedItem(persAlumno.getDomicilio().getCalle().getNombre());
                txtNroAlumno.setText(persAlumno.getDomicilio().getNumero());
                if(persAlumno.getDomicilio().getEdificio() != null)
                {
                    txtTorreAlumno.setText(persAlumno.getDomicilio().getEdificio().getTorre());
                    txtPisoAlumno.setText(persAlumno.getDomicilio().getEdificio().getPiso());
                    txtDeptoAlumno.setText(persAlumno.getDomicilio().getEdificio().getDepto());
                }

                alumno = ctrlAlumno.existe(persAlumno.getIdPersona());
                if(alumno != null)
                {
                    diagnosticos = ctrlAlumnoDiagnostico.alumno(alumno.getIdAlumno());
                    if(alumno.isIntegrado())
                    {    
                        optIntegradoSI.setSelected(true);
                        cbxLugar.setSelectedItem(ctrlLugarPrestacion.leer(ctrlPrestacion.leerPrestacionDe(alumno.getIdAlumno()).getLugar().getNombre()).getNombre());
                        cbxNivel.setSelectedItem(ctrlNivel.leer(ctrlPrestacion.leerPrestacionDe(alumno.getIdAlumno()).getNivel().getNivel()).getNivel());
                    }
                    else
                        optIntegradoNO.setSelected(true);
                    
                    if(alumno.isPension())
                        optPensionSI.setSelected(true);
                    else
                        optPensionNO.setSelected(true);
                    
                    if(alumno.isAsignacionUniversal())
                        optAsignacionSI.setSelected(true);
                    else
                        optAsignacionNO.setSelected(true);
                    
                    txtLegajo.setText(alumno.getLegajo());
                    txtCUD.setDate(alumno.getVtoCUD());
                    cbxObraSocial.setSelectedItem(alumno.getObraSocial().getNombreCorto());
                    
                    diagnosticos = ctrlAlumnoDiagnostico.alumno(alumno.getIdAlumno());
                    cargarDiagnosticosAlumno(diagnosticos);
                }
                else
                {
                    datosAcademicosOn();
                    btnGrabar.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_txtDNIAlumnoKeyPressed

    private void txtDNIAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDNIAlumnoMouseClicked
        if(evt.getClickCount() == 2)
        {
            datosPersonalesAlumnoOff();
            datosAcademicosOff();
            datosMadreOff();
            datosPadreOff();
            datosTutorOff();
            btnGrabar.setEnabled(false);
        }
    }//GEN-LAST:event_txtDNIAlumnoMouseClicked

    private void txtDNIAlumnoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDNIAlumnoFocusGained
        datosPersonalesAlumnoOff();
        datosAcademicosOff();
        datosMadreOff();
        datosPadreOff();
        datosTutorOff();
        btnGrabar.setEnabled(false);
    }//GEN-LAST:event_txtDNIAlumnoFocusGained

    private void optIntegradoNOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optIntegradoNOActionPerformed
        cbxLugar.setEnabled(false);
        cbxNivel.setEnabled(false);
    }//GEN-LAST:event_optIntegradoNOActionPerformed

    private void optIntegradoSIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optIntegradoSIActionPerformed
        cbxLugar.setEnabled(true);
        cbxNivel.setEnabled(true);
    }//GEN-LAST:event_optIntegradoSIActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if(validarAlumno() && validarMadre() && validarPadre() && validarTutor())
        {
            grabarMadre();
            grabarPadre();
            grabarTutor();
            grabarAlumno();
            btnGrabar.setEnabled(false);
        }
        else
            JOptionPane.showMessageDialog(this, "No se registró ningún datos", "ATENCIÓN!!!...", INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMadreActionPerformed
        boolean existe;
        if(cbxTipoTelefonoMadre.getSelectedIndex() == 0 || txtCodAreaMadre.getText().equals("") || txtTelefonoMadre.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Complete todos los datos del Teléfono", "DATOS INCOMPLETOS", ERROR_MESSAGE);
        }
        else    
        {
            existe = false;
            for(int i=0; i<modeloTelefonosMadre.getRowCount(); i++)
            {
                if(modeloTelefonosMadre.getValueAt(i, 1).equals(txtCodAreaMadre.getText()) && modeloTelefonosMadre.getValueAt(i, 2).equals(txtTelefonoMadre.getText()))
                {
                    existe = true;
                }
            }
            if(existe)
            {
                JOptionPane.showMessageDialog(this, "El Teléfono ya esta registrado", "TELEFONO EXISTENTE", INFORMATION_MESSAGE);
            }
            else
            {
                lineaTablaTelefonos[0] = cbxTipoTelefonoMadre.getSelectedItem().toString();
                lineaTablaTelefonos[1] = txtCodAreaMadre.getText();
                lineaTablaTelefonos[2] = txtTelefonoMadre.getText();
                modeloTelefonosMadre.addRow(lineaTablaTelefonos);
            }
        }   
    }//GEN-LAST:event_btnAgregarMadreActionPerformed

    private void btnQuitarMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarMadreActionPerformed
        if(modeloTelefonosMadre.getRowCount()>0)
        {
            if(tblTelefonosMadre.getSelectedRow() >= 0)
                modeloTelefonosMadre.removeRow(tblTelefonosMadre.getSelectedRow());
            else
                JOptionPane.showMessageDialog(this, "Seleccione un telefono", "ATENCION...", INFORMATION_MESSAGE);
                
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No hay telefonos para eliminar", "ATENCION...", INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnQuitarMadreActionPerformed

    private void btnAgregarPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPadreActionPerformed
        boolean existe;
        if(cbxTipoTelefonoPadre.getSelectedIndex() == 0 || txtCodAreaPadre.getText().equals("") || txtTelefonoPadre.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Complete todos los datos del Teléfono", "DATOS INCOMPLETOS", ERROR_MESSAGE);
        }
        else
        {
            existe = false;
            for(int i=0; i<modeloTelefonosPadre.getRowCount(); i++)
            {
                if(modeloTelefonosPadre.getValueAt(i, 1).equals(txtCodAreaPadre.getText()) && modeloTelefonosPadre.getValueAt(i, 2).equals(txtTelefonoPadre.getText()))
                {
                    existe = true;
                }
            }
            if(existe)
            {
                JOptionPane.showMessageDialog(this, "El Teléfono ya esta registrado", "TELEFONO EXISTENTE", INFORMATION_MESSAGE);
            }
            else
            {
                lineaTablaTelefonos[0] = cbxTipoTelefonoPadre.getSelectedItem().toString();
                lineaTablaTelefonos[1] = txtCodAreaPadre.getText();
                lineaTablaTelefonos[2] = txtTelefonoPadre.getText();
                modeloTelefonosPadre.addRow(lineaTablaTelefonos);
            }
        }   
    }//GEN-LAST:event_btnAgregarPadreActionPerformed

    private void btnAgregarTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTutorActionPerformed
        boolean existe;
        if(cbxTipoTelefonoTutor.getSelectedIndex() == 0 || txtCodAreaTutor.getText().equals("") || txtTelefonoTutor.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Complete todos los datos del Teléfono", "DATOS INCOMPLETOS", ERROR_MESSAGE);
        }
        else
        {
            existe = false;
            for(int i=0; i<modeloTelefonosTutor.getRowCount(); i++)
            {
                if(modeloTelefonosTutor.getValueAt(i, 1).equals(txtCodAreaTutor.getText()) && modeloTelefonosTutor.getValueAt(i, 2).equals(txtTelefonoTutor.getText()))
                {
                    existe = true;
                }
            }
            if(existe)
            {
                JOptionPane.showMessageDialog(this, "El Teléfono ya esta registrado", "TELEFONO EXISTENTE", INFORMATION_MESSAGE);
            }
            else
            {
                lineaTablaTelefonos[0] = cbxTipoTelefonoTutor.getSelectedItem().toString();
                lineaTablaTelefonos[1] = txtCodAreaTutor.getText();
                lineaTablaTelefonos[2] = txtTelefonoTutor.getText();
                modeloTelefonosTutor.addRow(lineaTablaTelefonos);
            }
        }   
    }//GEN-LAST:event_btnAgregarTutorActionPerformed

    private void btnQuitarPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarPadreActionPerformed
        if(modeloTelefonosPadre.getRowCount()>0)
        {
            if(tblTelefonosPadre.getSelectedRow() >= 0)
                modeloTelefonosPadre.removeRow(tblTelefonosPadre.getSelectedRow());
            else
                JOptionPane.showMessageDialog(this, "Seleccione un telefono", "ATENCION...", INFORMATION_MESSAGE);
                
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No hay telefonos para eliminar", "ATENCION...", INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnQuitarPadreActionPerformed

    private void btnQuitarTutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarTutorActionPerformed
        if(modeloTelefonosTutor.getRowCount()>0)
        {
            if(tblTelefonosTutor.getSelectedRow() >= 0)
                modeloTelefonosTutor.removeRow(tblTelefonosTutor.getSelectedRow());
            else
                JOptionPane.showMessageDialog(this, "Seleccione un telefono", "ATENCION...", INFORMATION_MESSAGE);
                
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No hay telefonos para eliminar", "ATENCION...", INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnQuitarTutorActionPerformed

    private void btnAgregarDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagnosticoActionPerformed
        if(modeloDiagnosticos.getRowCount() > 0)
        {
            if(tblDiagnosticos.getSelectedRow() >= 0)
            {
                lineaDiagnosticos[0] = modeloDiagnosticos.getValueAt(tblDiagnosticos.getSelectedRow(),0).toString();
                lineaDiagnosticos[1] = modeloDiagnosticos.getValueAt(tblDiagnosticos.getSelectedRow(),1).toString();
                modeloDiagnosticosAlumno.addRow(lineaDiagnosticos);
                modeloDiagnosticos.removeRow(tblDiagnosticos.getSelectedRow());
            }
            else
                JOptionPane.showMessageDialog(this, "No Seleccionó un Diagnóstico para Agregar", "Atención", ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(this, "No hay Diagnosticos para Agregar", "Atención", INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnAgregarDiagnosticoActionPerformed

    private void btnQuitarDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDiagnosticoActionPerformed
        if(modeloDiagnosticosAlumno.getRowCount() > 0)
        {
            if(tblDiagnosticoAlumno.getSelectedRow() >= 0)
            {
                lineaDiagnosticos[0] = modeloDiagnosticosAlumno.getValueAt(tblDiagnosticoAlumno.getSelectedRow(),0).toString();
                lineaDiagnosticos[1] = modeloDiagnosticosAlumno.getValueAt(tblDiagnosticoAlumno.getSelectedRow(),1).toString();
                modeloDiagnosticos.addRow(lineaDiagnosticos);
                modeloDiagnosticosAlumno.removeRow(tblDiagnosticoAlumno.getSelectedRow());
            }
            else
                JOptionPane.showMessageDialog(this, "No Seleccionó un Diagnóstico para Quitar", "Atención", ERROR_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(this, "No hay Diagnosticos para Quitar", "Atención", INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnQuitarDiagnosticoActionPerformed

    private void txtDNITutorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNITutorKeyPressed
        List<Telefono> telefonos;

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            tutor = ctrlPersona.leer(1, txtDNITutor.getText().trim());
            if(tutor == null)
            {
                datosTutorOn();
            }
            else
            {
                datosTutorOff();
                txtApellidoTutor.setText(tutor.getApellido());
                txtNombreTutor.setText(tutor.getNombre());
                txtFechaNacimientoTutor.setDate(tutor.getFechaNacimiento());
                switch(tutor.getSexo())
                {
                    case 1: 
                    {
                        optHombreTutor.setSelected(true);
                        break;
                    }
                    case 2: 
                    {
                        optMujerTutor.setSelected(true);
                        break;
                    }
                    case 3: 
                    {
                        optOtroTutor.setSelected(true);
                        break;
                    }
                }
                telefonos = ctrlTelefono.leerTelefonosDe(tutor.getIdPersona());
                cargarTelefonos("tutor",telefonos);

                cllT = tutor.getDomicilio().getCalle();
                brrT = cllT.getDe();
                lclddT = brrT.getDe();
                dprtmntT = lclddT.getDe();
                prvncT = dprtmntT.getDe();
                cbxProvinciaTutor.setSelectedItem(prvncT.getNombre());
                cbxDepartamentoTutor.setSelectedItem(dprtmntT.getNombre());
                cbxLocalidadTutor.setSelectedItem(lclddT.getNombre());
                cbxBarrioTutor.setSelectedItem(brrT.getNombre());
                cbxCalleTutor.setSelectedItem(tutor.getDomicilio().getCalle().getNombre());
                txtNroTutor.setText(tutor.getDomicilio().getNumero());
                if(tutor.getDomicilio().getEdificio() != null)
                {
                    txtTorreTutor.setText(tutor.getDomicilio().getEdificio().getTorre());
                    txtPisoTutor.setText(tutor.getDomicilio().getEdificio().getPiso());
                    txtDeptoTutor.setText(tutor.getDomicilio().getEdificio().getDepto());
                }
            }
        }
    }//GEN-LAST:event_txtDNITutorKeyPressed

    private void chkEsTutorMadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEsTutorMadreActionPerformed
        if(chkEsTutorMadre.isSelected())
        {    
            chkEsTutorPadre.setSelected(false);
            madreEsTutor();
            datosTutorOff();
        }
    }//GEN-LAST:event_chkEsTutorMadreActionPerformed

    private void chkEsTutorPadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEsTutorPadreActionPerformed
        if(chkEsTutorPadre.isSelected())
        {
            chkEsTutorMadre.setSelected(false);
            padreEsTutor();
            datosTutorOff();
        }
    }//GEN-LAST:event_chkEsTutorPadreActionPerformed

    private void txtDNIMadreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDNIMadreFocusLost
        List<Telefono> telefonos;
        
        madre = ctrlPersona.leer(1, txtDNIMadre.getText().trim());
        if(madre == null)
        {
            datosMadreOn();
        }
        else
        {
            datosMadreOff();
            txtApellidoMadre.setText(madre.getApellido());
            txtNombreMadre.setText(madre.getNombre());
            txtFechaNacimientoMadre.setDate(madre.getFechaNacimiento());
            switch(madre.getSexo())
            {
                case 1: 
                {
                    optHombreMadre.setSelected(true);
                    break;
                }
                case 2: 
                {
                    optMujerMadre.setSelected(true);
                    break;
                }
                case 3: 
                {
                    optOtroMadre.setSelected(true);
                    break;
                }
            }
            telefonos = ctrlTelefono.leerTelefonosDe(madre.getIdPersona());
            cargarTelefonos("madre",telefonos);

            cllM = madre.getDomicilio().getCalle();
            brrM = cllM.getDe();
            lclddM = brrM.getDe();
            dprtmntM = lclddM.getDe();
            prvncM = dprtmntM.getDe();
            cbxProvinciaMadre.setSelectedItem(prvncM.getNombre());
            cbxDepartamentoMadre.setSelectedItem(dprtmntM.getNombre());
            cbxLocalidadMadre.setSelectedItem(lclddM.getNombre());
            cbxBarrioMadre.setSelectedItem(brrM.getNombre());
            cbxCalleMadre.setSelectedItem(madre.getDomicilio().getCalle().getNombre());
            txtNroMadre.setText(madre.getDomicilio().getNumero());
            if(madre.getDomicilio().getEdificio() != null)
            {
                txtTorreMadre.setText(madre.getDomicilio().getEdificio().getTorre());
                txtPisoMadre.setText(madre.getDomicilio().getEdificio().getPiso());
                txtDeptoMadre.setText(madre.getDomicilio().getEdificio().getDepto());
            }
        }
    }//GEN-LAST:event_txtDNIMadreFocusLost

    private void txtDNIMadreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIMadreKeyPressed
        List<Telefono> telefonos;
                
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            madre = ctrlPersona.leer(1, txtDNIMadre.getText().trim());
            if(madre == null)
            {
                datosMadreOn();
            }
            else
            {
                datosMadreOff();
                txtApellidoMadre.setText(madre.getApellido());
                txtNombreMadre.setText(madre.getNombre());
                txtFechaNacimientoMadre.setDate(madre.getFechaNacimiento());
                switch(madre.getSexo())
                {
                    case 1: 
                    {
                        optHombreMadre.setSelected(true);
                        break;
                    }
                    case 2: 
                    {
                        optMujerMadre.setSelected(true);
                        break;
                    }
                    case 3: 
                    {
                        optOtroMadre.setSelected(true);
                        break;
                    }
                }
                telefonos = ctrlTelefono.leerTelefonosDe(madre.getIdPersona());
                cargarTelefonos("madre",telefonos);
                
                cllM = madre.getDomicilio().getCalle();
                brrM = cllM.getDe();
                lclddM = brrM.getDe();
                dprtmntM = lclddM.getDe();
                prvncM = dprtmntM.getDe();
                cbxProvinciaMadre.setSelectedItem(prvncM.getNombre());
                cbxDepartamentoMadre.setSelectedItem(dprtmntM.getNombre());
                cbxLocalidadMadre.setSelectedItem(lclddM.getNombre());
                cbxBarrioMadre.setSelectedItem(brrM.getNombre());
                cbxCalleMadre.setSelectedItem(madre.getDomicilio().getCalle().getNombre());
                txtNroMadre.setText(madre.getDomicilio().getNumero());
                if(madre.getDomicilio().getEdificio() != null)
                {
                    txtTorreMadre.setText(madre.getDomicilio().getEdificio().getTorre());
                    txtPisoMadre.setText(madre.getDomicilio().getEdificio().getPiso());
                    txtDeptoMadre.setText(madre.getDomicilio().getEdificio().getDepto());
                }
            }
        }
    }//GEN-LAST:event_txtDNIMadreKeyPressed

    private void txtDNIPadreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIPadreKeyPressed
        List<Telefono> telefonos;

        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            padre = ctrlPersona.leer(1, txtDNIPadre.getText().trim());
            if(padre == null)
            {
                datosPadreOn();
            }
            else
            {
                datosPadreOff();
                txtApellidoPadre.setText(padre.getApellido());
                txtNombrePadre.setText(padre.getNombre());
                txtFechaNacimientoPadre.setDate(padre.getFechaNacimiento());
                switch(padre.getSexo())
                {
                    case 1: 
                    {
                        optHombrePadre.setSelected(true);
                        break;
                    }
                    case 2: 
                    {
                        optMujerPadre.setSelected(true);
                        break;
                    }
                    case 3: 
                    {
                        optOtroPadre.setSelected(true);
                        break;
                    }
                }
                telefonos = ctrlTelefono.leerTelefonosDe(padre.getIdPersona());
                cargarTelefonos("padre",telefonos);

                cllP = padre.getDomicilio().getCalle();
                brrP = cllP.getDe();
                lclddP = brrP.getDe();
                dprtmntP = lclddP.getDe();
                prvncP = dprtmntP.getDe();
                cbxProvinciaPadre.setSelectedItem(prvncP.getNombre());
                cbxDepartamentoPadre.setSelectedItem(dprtmntP.getNombre());
                cbxLocalidadPadre.setSelectedItem(lclddP.getNombre());
                cbxBarrioPadre.setSelectedItem(brrP.getNombre());
                cbxCallePadre.setSelectedItem(padre.getDomicilio().getCalle().getNombre());
                txtNroPadre.setText(padre.getDomicilio().getNumero());
                if(padre.getDomicilio().getEdificio() != null)
                {
                    txtTorrePadre.setText(padre.getDomicilio().getEdificio().getTorre());
                    txtPisoPadre.setText(padre.getDomicilio().getEdificio().getPiso());
                    txtDeptoPadre.setText(padre.getDomicilio().getEdificio().getDepto());
                }
            }
        }
    }//GEN-LAST:event_txtDNIPadreKeyPressed

    private void txtDNIPadreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDNIPadreFocusLost
        List<Telefono> telefonos;
        padre = ctrlPersona.leer(1, txtDNIPadre.getText().trim());
        if(padre == null)
        {
            datosPadreOn();
        }
        else
        {
            datosPadreOff();
            txtApellidoPadre.setText(padre.getApellido());
            txtNombrePadre.setText(padre.getNombre());
            txtFechaNacimientoPadre.setDate(padre.getFechaNacimiento());
            switch(padre.getSexo())
            {
                case 1: 
                {
                    optHombrePadre.setSelected(true);
                    break;
                }
                case 2: 
                {
                    optMujerPadre.setSelected(true);
                    break;
                }
                case 3: 
                {
                    optOtroPadre.setSelected(true);
                    break;
                }
            }
            telefonos = ctrlTelefono.leerTelefonosDe(padre.getIdPersona());
            cargarTelefonos("padre",telefonos);

            cllP = padre.getDomicilio().getCalle();
            brrP = cllP.getDe();
            lclddP = brrP.getDe();
            dprtmntP = lclddP.getDe();
            prvncP = dprtmntP.getDe();
            cbxProvinciaPadre.setSelectedItem(prvncP.getNombre());
            cbxDepartamentoPadre.setSelectedItem(dprtmntP.getNombre());
            cbxLocalidadPadre.setSelectedItem(lclddP.getNombre());
            cbxBarrioPadre.setSelectedItem(brrP.getNombre());
            cbxCallePadre.setSelectedItem(padre.getDomicilio().getCalle().getNombre());
            txtNroPadre.setText(padre.getDomicilio().getNumero());
            if(padre.getDomicilio().getEdificio() != null)
            {
                txtTorrePadre.setText(padre.getDomicilio().getEdificio().getTorre());
                txtPisoPadre.setText(padre.getDomicilio().getEdificio().getPiso());
                txtDeptoPadre.setText(padre.getDomicilio().getEdificio().getDepto());
            }
        }
    }//GEN-LAST:event_txtDNIPadreFocusLost

    private void txtDNITutorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDNITutorFocusLost
        List<Telefono> telefonos;
        
        tutor = ctrlPersona.leer(1, txtDNITutor.getText().trim());
        if(tutor == null)
        {
            datosTutorOn();
        }
        else
        {
            datosTutorOff();
            txtApellidoTutor.setText(tutor.getApellido());
            txtNombreTutor.setText(tutor.getNombre());
            txtFechaNacimientoTutor.setDate(tutor.getFechaNacimiento());
            switch(tutor.getSexo())
            {
                case 1: 
                {
                    optHombreTutor.setSelected(true);
                    break;
                }
                case 2: 
                {
                    optMujerTutor.setSelected(true);
                    break;
                }
                case 3: 
                {
                    optOtroTutor.setSelected(true);
                    break;
                }
            }
            telefonos = ctrlTelefono.leerTelefonosDe(tutor.getIdPersona());
            cargarTelefonos("tutor",telefonos);

            cllM = tutor.getDomicilio().getCalle();
            brrM = cllM.getDe();
            lclddM = brrM.getDe();
            dprtmntM = lclddM.getDe();
            prvncM = dprtmntM.getDe();
            cbxProvinciaTutor.setSelectedItem(prvncM.getNombre());
            cbxDepartamentoTutor.setSelectedItem(dprtmntM.getNombre());
            cbxLocalidadTutor.setSelectedItem(lclddM.getNombre());
            cbxBarrioTutor.setSelectedItem(brrM.getNombre());
            cbxCalleTutor.setSelectedItem(tutor.getDomicilio().getCalle().getNombre());
            txtNroTutor.setText(tutor.getDomicilio().getNumero());
            if(tutor.getDomicilio().getEdificio() != null)
            {
                txtTorreTutor.setText(tutor.getDomicilio().getEdificio().getTorre());
                txtPisoTutor.setText(tutor.getDomicilio().getEdificio().getPiso());
                txtDeptoTutor.setText(tutor.getDomicilio().getEdificio().getDepto());
            }
        }
    }//GEN-LAST:event_txtDNITutorFocusLost

    private void txtDNIMadreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDNIMadreMouseClicked
        if(evt.getClickCount() == 2)
        {
            limpiarDatosMadre();
            datosMadreOff();
        }
    }//GEN-LAST:event_txtDNIMadreMouseClicked

    private void txtDNIPadreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDNIPadreMouseClicked
        if(evt.getClickCount() == 2)
        {
            limpiarDatosPadre();
            datosPadreOff();
        }                                      
    }//GEN-LAST:event_txtDNIPadreMouseClicked

    private void txtDNITutorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDNITutorMouseClicked
        if(evt.getClickCount() == 2)
        {
            limpiarDatosTutor();
            datosTutorOff();
        }                                      
    }//GEN-LAST:event_txtDNITutorMouseClicked

    private void txtDNIAlumnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDNIAlumnoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIAlumnoFocusLost

    private void limpiarDatosPersonalesAlumno()
    {
        txtDNIAlumno.setText("");
        txtApellidoAlumno.setText("");
        txtNombreAlumno.setText("");
        txtFechaNacimiento.setDate(new java.util.Date());
        limpiarCombo(cbxLugarNacimiento);
        cargarCombo(cbxLugarNacimiento, ctrlLugarDomicilio.leerTodos(4));
        optHombreAlumno.setSelected(false);
        optMujerAlumno.setSelected(false);
        optOtroAlumno.setSelected(false);
        limpiarCombo(cbxProvinciaAlumno);
        cargarCombo(cbxProvinciaAlumno, ctrlLugarDomicilio.leerTodos(5));
        limpiarCombo(cbxDepartamentoAlumno);
        limpiarCombo(cbxLocalidadAlumno);
        limpiarCombo(cbxBarrioAlumno);
        limpiarCombo(cbxCalleAlumno);
        txtNroAlumno.setText("");
        txtTorreAlumno.setText("");
        txtPisoAlumno.setText("");
        txtDeptoAlumno.setText("");
    }
    
    private void datosPersonalesAlumnoOn()
    {
        txtDNIAlumno.setEnabled(false);
        txtApellidoAlumno.setEnabled(true);
        txtNombreAlumno.setEnabled(true);
        txtFechaNacimiento.setEnabled(true);
        optHombreAlumno.setEnabled(true);
        optMujerAlumno.setEnabled(true);
        optOtroAlumno.setEnabled(true);
        cbxProvinciaAlumno.setEnabled(true);
        cbxDepartamentoAlumno.setEnabled(true);
        cbxLocalidadAlumno.setEnabled(true);
        cbxBarrioAlumno.setEnabled(true);
        cbxCalleAlumno.setEnabled(true);
        txtNroAlumno.setEnabled(true);
        txtTorreAlumno.setEnabled(true);
        txtPisoAlumno.setEnabled(true);
        txtDeptoAlumno.setEnabled(true);
    }
    
    private void datosPersonalesAlumnoOff()
    {
        txtDNIAlumno.setEnabled(true);
        txtApellidoAlumno.setEnabled(false);
        txtNombreAlumno.setEnabled(false);
        txtFechaNacimiento.setEnabled(false);
        optHombreAlumno.setEnabled(false);
        optMujerAlumno.setEnabled(false);
        optOtroAlumno.setEnabled(false);
        cbxProvinciaAlumno.setEnabled(false);
        cbxDepartamentoAlumno.setEnabled(false);
        cbxLocalidadAlumno.setEnabled(false);
        cbxBarrioAlumno.setEnabled(false);
        cbxCalleAlumno.setEnabled(false);
        txtNroAlumno.setEnabled(false);
        txtTorreAlumno.setEnabled(false);
        txtPisoAlumno.setEnabled(false);        
        txtDeptoAlumno.setEnabled(false);
    }
    
    private void limpiarDatosAcademicos()
    {
        txtLegajo.setText("");
        limpiarCombo(cbxObraSocial);
        txtCUD.setDate(new java.util.Date());
        optIntegradoSI.setSelected(false);
        optIntegradoNO.setSelected(false);
        optPensionSI.setSelected(false);
        optPensionNO.setSelected(false);
        optAsignacionSI.setSelected(false);
        optAsignacionNO.setSelected(false);
    }
    
    private void datosAcademicosOn()
    {
        cbxLugarNacimiento.setEnabled(true);
        txtLegajo.setEnabled(true);
        cbxObraSocial.setEnabled(true);
        txtCUD.setEnabled(true);
        optIntegradoSI.setEnabled(true);
        optIntegradoNO.setEnabled(true);
        optPensionSI.setEnabled(true);
        optPensionNO.setEnabled(true);
        optAsignacionSI.setEnabled(true);
        optAsignacionNO.setEnabled(true);
        tblDiagnosticoAlumno.setEnabled(true);
        tblDiagnosticos.setEnabled(true);
        btnAgregarDiagnostico.setEnabled(true);
        btnQuitarDiagnostico.setEnabled(true);
    }

    private void datosAcademicosOff()
    {
        cbxLugarNacimiento.setEnabled(false);
        txtLegajo.setEnabled(false);
        cbxObraSocial.setEnabled(false);
        txtCUD.setEnabled(false);
        optIntegradoSI.setEnabled(false);
        optIntegradoNO.setEnabled(false);
        cbxLugar.setEnabled(false);
        cbxNivel.setEnabled(false);
        optPensionSI.setEnabled(false);
        optPensionNO.setEnabled(false);
        optAsignacionSI.setEnabled(false);
        optAsignacionNO.setEnabled(false);
        tblDiagnosticoAlumno.setEnabled(false);
        tblDiagnosticos.setEnabled(false);
        btnAgregarDiagnostico.setEnabled(false);
        btnQuitarDiagnostico.setEnabled(false);
    }

    private void limpiarDatosMadre()
    {
        txtDNIMadre.setText("");
        txtApellidoMadre.setText("");
        txtNombreMadre.setText("");
        txtFechaNacimientoMadre.setDate(new java.util.Date());
        optHombreMadre.setSelected(false);
        optMujerMadre.setSelected(false);
        optOtroMadre.setSelected(false);
        limpiarCombo(cbxProvinciaMadre);
        cargarCombo(cbxProvinciaMadre, ctrlLugarDomicilio.leerTodos(5));
        limpiarCombo(cbxDepartamentoMadre);
        limpiarCombo(cbxLocalidadMadre);
        limpiarCombo(cbxBarrioMadre);
        limpiarCombo(cbxCalleMadre);
        txtNroMadre.setText("");
        txtTorreMadre.setText("");
        txtPisoMadre.setText("");
        txtDeptoMadre.setText("");
    }

    private void datosMadreOn()
    {
        txtDNIMadre.setEnabled(false);
        txtApellidoMadre.setEnabled(true);
        txtNombreMadre.setEnabled(true);
        txtFechaNacimientoMadre.setEnabled(true);
        optHombreMadre.setEnabled(true);
        optMujerMadre.setEnabled(true);
        optOtroMadre.setEnabled(true);
        btnAgregarMadre.setEnabled(true);
        btnQuitarMadre.setEnabled(true);
        cbxTipoTelefonoMadre.setEnabled(true);
        txtCodAreaMadre.setEnabled(true);
        txtTelefonoMadre.setEnabled(true);
        tblTelefonosMadre.setEnabled(true);
        cbxProvinciaMadre.setEnabled(true);
        cbxDepartamentoMadre.setEnabled(true);
        cbxLocalidadMadre.setEnabled(true);
        cbxBarrioMadre.setEnabled(true);
        cbxCalleMadre.setEnabled(true);
        txtNroMadre.setEnabled(true);
        txtTorreMadre.setEnabled(true);
        txtPisoMadre.setEnabled(true);
        txtDeptoMadre.setEnabled(true);
    }
    
    private void datosMadreOff()
    {
        txtDNIMadre.setEnabled(true);
        txtApellidoMadre.setEnabled(false);
        txtNombreMadre.setEnabled(false);
        txtFechaNacimientoMadre.setEnabled(false);
        optHombreMadre.setEnabled(false);
        optMujerMadre.setEnabled(false);
        optOtroMadre.setEnabled(false);
        btnAgregarMadre.setEnabled(false);
        btnQuitarMadre.setEnabled(false);
        cbxTipoTelefonoMadre.setEnabled(false);
        txtCodAreaMadre.setEnabled(false);
        txtTelefonoMadre.setEnabled(false);
        tblTelefonosMadre.setEnabled(false);
        cbxProvinciaMadre.setEnabled(false);
        cbxDepartamentoMadre.setEnabled(false);
        cbxLocalidadMadre.setEnabled(false);
        cbxBarrioMadre.setEnabled(false);
        cbxCalleMadre.setEnabled(false);
        txtNroMadre.setEnabled(false);
        txtTorreMadre.setEnabled(false);
        txtPisoMadre.setEnabled(false);        
        txtDeptoMadre.setEnabled(false);
    }
    
    private void limpiarDatosPadre()
    {
        txtDNIPadre.setText("");
        txtApellidoPadre.setText("");
        txtNombrePadre.setText("");
        txtFechaNacimientoPadre.setDate(new java.util.Date());
        optHombrePadre.setSelected(false);
        optMujerPadre.setSelected(false);
        optOtroPadre.setSelected(false);
        limpiarCombo(cbxProvinciaPadre);
        cargarCombo(cbxProvinciaPadre, ctrlLugarDomicilio.leerTodos(5));
        limpiarCombo(cbxDepartamentoPadre);
        limpiarCombo(cbxLocalidadPadre);
        limpiarCombo(cbxBarrioPadre);
        limpiarCombo(cbxCallePadre);
        txtNroPadre.setText("");
        txtTorrePadre.setText("");
        txtPisoPadre.setText("");
        txtDeptoPadre.setText("");
    }

    private void datosPadreOn()
    {
        txtDNIPadre.setEnabled(false);
        txtApellidoPadre.setEnabled(true);
        txtNombrePadre.setEnabled(true);
        txtFechaNacimientoPadre.setEnabled(true);
        optHombrePadre.setEnabled(true);
        optMujerPadre.setEnabled(true);
        optOtroPadre.setEnabled(true);
        btnAgregarPadre.setEnabled(true);
        btnQuitarPadre.setEnabled(true);
        cbxTipoTelefonoPadre.setEnabled(true);
        txtCodAreaPadre.setEnabled(true);
        txtTelefonoPadre.setEnabled(true);
        tblTelefonosPadre.setEnabled(true);
        cbxProvinciaPadre.setEnabled(true);
        cbxDepartamentoPadre.setEnabled(true);
        cbxLocalidadPadre.setEnabled(true);
        cbxBarrioPadre.setEnabled(true);
        cbxCallePadre.setEnabled(true);
        txtNroPadre.setEnabled(true);
        txtTorrePadre.setEnabled(true);
        txtPisoPadre.setEnabled(true);
        txtDeptoPadre.setEnabled(true);
    }
    
    private void datosPadreOff()
    {
        txtDNIPadre.setEnabled(true);
        txtApellidoPadre.setEnabled(false);
        txtNombrePadre.setEnabled(false);
        txtFechaNacimientoPadre.setEnabled(false);
        optHombrePadre.setEnabled(false);
        optMujerPadre.setEnabled(false);
        optOtroPadre.setEnabled(false);
        btnAgregarPadre.setEnabled(false);
        btnQuitarPadre.setEnabled(false);
        cbxTipoTelefonoPadre.setEnabled(false);
        txtCodAreaPadre.setEnabled(false);
        txtTelefonoPadre.setEnabled(false);
        tblTelefonosPadre.setEnabled(false);
        cbxProvinciaPadre.setEnabled(false);
        cbxDepartamentoPadre.setEnabled(false);
        cbxLocalidadPadre.setEnabled(false);
        cbxBarrioPadre.setEnabled(false);
        cbxCallePadre.setEnabled(false);
        txtNroPadre.setEnabled(false);
        txtTorrePadre.setEnabled(false);
        txtPisoPadre.setEnabled(false);        
        txtDeptoPadre.setEnabled(false);
    }

    private void limpiarDatosTutor()
    {
        txtDNITutor.setText("");
        txtApellidoTutor.setText("");
        txtNombreTutor.setText("");
        txtFechaNacimientoTutor.setDate(new java.util.Date());
        optHombreTutor.setSelected(false);
        optMujerTutor.setSelected(false);
        optOtroTutor.setSelected(false);
        limpiarCombo(cbxProvinciaTutor);
        cargarCombo(cbxProvinciaTutor, ctrlLugarDomicilio.leerTodos(5));
        limpiarCombo(cbxDepartamentoTutor);
        limpiarCombo(cbxLocalidadTutor);
        limpiarCombo(cbxBarrioTutor);
        limpiarCombo(cbxCalleTutor);
        txtNroTutor.setText("");
        txtTorreTutor.setText("");
        txtPisoTutor.setText("");
        txtDeptoTutor.setText("");
    }

    private void datosTutorOn()
    {
        txtDNITutor.setEnabled(false);
        txtApellidoTutor.setEnabled(true);
        txtNombreTutor.setEnabled(true);
        txtFechaNacimientoTutor.setEnabled(true);
        optHombreTutor.setEnabled(true);
        optMujerTutor.setEnabled(true);
        optOtroTutor.setEnabled(true);
        btnAgregarTutor.setEnabled(true);
        btnQuitarTutor.setEnabled(true);
        cbxTipoTelefonoTutor.setEnabled(true);
        txtCodAreaTutor.setEnabled(true);
        txtTelefonoTutor.setEnabled(true);
        tblTelefonosTutor.setEnabled(true);
        cbxProvinciaTutor.setEnabled(true);
        cbxDepartamentoTutor.setEnabled(true);
        cbxLocalidadTutor.setEnabled(true);
        cbxBarrioTutor.setEnabled(true);
        cbxCalleTutor.setEnabled(true);
        txtNroTutor.setEnabled(true);
        txtTorreTutor.setEnabled(true);
        txtPisoTutor.setEnabled(true);
        txtDeptoTutor.setEnabled(true);
    }
    
    private void datosTutorOff()
    {
        txtDNITutor.setEnabled(true);
        txtApellidoTutor.setEnabled(false);
        txtNombreTutor.setEnabled(false);
        txtFechaNacimientoTutor.setEnabled(false);
        optHombreTutor.setEnabled(false);
        optMujerTutor.setEnabled(false);
        optOtroTutor.setEnabled(false);
        btnAgregarTutor.setEnabled(false);
        btnQuitarTutor.setEnabled(false);
        cbxTipoTelefonoTutor.setEnabled(false);
        txtCodAreaTutor.setEnabled(false);
        txtTelefonoTutor.setEnabled(false);
        tblTelefonosTutor.setEnabled(false);
        cbxProvinciaTutor.setEnabled(false);
        cbxDepartamentoTutor.setEnabled(false);
        cbxLocalidadTutor.setEnabled(false);
        cbxBarrioTutor.setEnabled(false);
        cbxCalleTutor.setEnabled(false);
        txtNroTutor.setEnabled(false);
        txtTorreTutor.setEnabled(false);
        txtPisoTutor.setEnabled(false);        
        txtDeptoTutor.setEnabled(false);
    }
    
    private void cargarTelefonos(String prsn, List<Telefono> tlfn)
    {
        
        String telefono[] = new String[3];
        
        switch (prsn)
        {
            case "madre":
            {
                modeloTelefonosMadre.setRowCount(0);
                for(int i = 0; i < tlfn.size(); i++)
                {
                    telefono[0] = tlfn.get(i).getTipoTelefono().getTipoTelefono();
                    telefono[1] = tlfn.get(i).getCodigoArea();
                    telefono[2] = tlfn.get(i).getNumeroTelefono();
                    modeloTelefonosMadre.addRow(telefono);
                }
                tblTelefonosMadre.setModel(modeloTelefonosMadre);
                break;
            }
            case "padre":
            {
                modeloTelefonosPadre.setRowCount(0);
                for(int i = 0; i < tlfn.size(); i++)
                {
                    telefono[0] = tlfn.get(i).getTipoTelefono().getTipoTelefono();
                    telefono[1] = tlfn.get(i).getCodigoArea();
                    telefono[2] = tlfn.get(i).getNumeroTelefono();
                    modeloTelefonosPadre.addRow(telefono);
                }
                tblTelefonosPadre.setModel(modeloTelefonosPadre);
                break;
            }
            case "tutor":
            {
                modeloTelefonosTutor.setRowCount(0);
                for(int i = 0; i < tlfn.size(); i++)
                {
                    telefono[0] = tlfn.get(i).getTipoTelefono().getTipoTelefono();
                    telefono[1] = tlfn.get(i).getCodigoArea();
                    telefono[2] = tlfn.get(i).getNumeroTelefono();
                    modeloTelefonosTutor.addRow(telefono);
                }
                tblTelefonosTutor.setModel(modeloTelefonosTutor);
                break;
            }
        }
    }
    
    private void cargarDiagnosticos()
    {
        List<Diagnostico> diagnosticos;
        String[] fila = new String[2];

        modeloDiagnosticosAlumno.setColumnCount(0);
        modeloDiagnosticosAlumno.setRowCount(0);
        modeloDiagnosticosAlumno.addColumn("CODIGO");
        modeloDiagnosticosAlumno.addColumn("DIAGNOSTICOS");
        tblDiagnosticoAlumno.setModel(modeloDiagnosticosAlumno);

        modeloDiagnosticos.setColumnCount(0);
        modeloDiagnosticos.setRowCount(0);
        modeloDiagnosticos.addColumn("CODIGO");
        modeloDiagnosticos.addColumn("DIAGNOSTICOS");

        diagnosticos = ctrlDiagnostico.leerTodos();
        for(int i=0; i<diagnosticos.size(); i++)
        {
            fila[0] = diagnosticos.get(i).getCodigoDiagnostico();
            fila[1] = diagnosticos.get(i).getDiagnostico();
            modeloDiagnosticos.addRow(fila);
        }
        tblDiagnosticos.setModel(modeloDiagnosticos);
    }

    private void cargarDiagnosticosAlumno(List<Diagnostico> dgnstcs)
    {
        String[] fila = new String[2];
        modeloDiagnosticosAlumno.setColumnCount(0);
        modeloDiagnosticosAlumno.setRowCount(0);
        modeloDiagnosticosAlumno.addColumn("CODIGO");
        modeloDiagnosticosAlumno.addColumn("DIAGNOSTICOS");

        for(int i=0; i<dgnstcs.size(); i++)
        {
            fila[0] = dgnstcs.get(i).getCodigoDiagnostico();
            fila[1] = dgnstcs.get(i).getDiagnostico();
            modeloDiagnosticosAlumno.addRow(fila);
        }
        tblDiagnosticoAlumno.setModel(modeloDiagnosticosAlumno);
    }

    private void cargarCombo(JComboBox cbx, List<String> lst)
    {
        cbx.removeAllItems();
        cbx.addItem("Seleccione...");
        if(lst != null)
        {
            for(int i=0; i<lst.size(); i++)
            {
                cbx.addItem(lst.get(i));
            }
        }
    }

    private void limpiarCombo(JComboBox cbx)
    {
        cbx.removeAllItems();
        cbx.addItem("Seleccione...");
    }

    private boolean validarAlumno()
    {
        boolean valida = true;
        
        if(cbxTipoDocAlumno.getSelectedIndex() == 0 || txtDNIAlumno.getText().equalsIgnoreCase(""))
            valida = false;
        else if(txtApellidoAlumno.getText().equalsIgnoreCase("") || txtNombreAlumno.getText().equalsIgnoreCase(""))
                valida = false;
            else if(!txtFechaNacimiento.isValid() || cbxLugarNacimiento.getSelectedIndex() == 0)
                    valida = false;
                else if(!optHombreAlumno.isSelected() && !optMujerAlumno.isSelected() && !optOtroAlumno.isSelected())
                        valida = false;
                    else if(cbxProvinciaAlumno.getSelectedIndex() == 0 || cbxDepartamentoAlumno.getSelectedIndex() == 0 || cbxLocalidadAlumno.getSelectedIndex() == 0 || cbxBarrioAlumno.getSelectedIndex() == 0 || cbxCalleAlumno.getSelectedIndex() == 0 || txtNroAlumno.getText().equalsIgnoreCase(""))
                            valida = false;
                        else if(optIntegradoSI.isSelected())
                                if(cbxLugar.getSelectedIndex() == 0 && cbxNivel.getSelectedIndex() == 0)
                                    valida = false;
        
        if(!valida)
            JOptionPane.showMessageDialog(this, "Hay datos obligatorios del Alumno sin completar", "ATENCION...", ERROR_MESSAGE);
        
        return valida;
    }
    
    private boolean validarMadre()
    {
        boolean valida = true;
        
        if(cbxTipoDocMadre.getSelectedIndex() == 0 || txtDNIMadre.getText().equalsIgnoreCase(""))
            valida = false;
        else if(txtApellidoMadre.getText().equalsIgnoreCase("") || txtNombreMadre.getText().equalsIgnoreCase(""))
                valida = false;
            else if(!optHombreMadre.isSelected() && !optMujerMadre.isSelected() && !optOtroMadre.isSelected())
                    valida = false;
                else if(cbxProvinciaMadre.getSelectedIndex() == 0 || cbxDepartamentoMadre.getSelectedIndex() == 0 || cbxLocalidadMadre.getSelectedIndex() == 0 || cbxBarrioMadre.getSelectedIndex() == 0 || 
                        cbxCalleMadre.getSelectedIndex() == 0 || txtNroMadre.getText().equalsIgnoreCase(""))
                        valida = false;
                    if(tblTelefonosMadre.getRowCount() == 0)
                    {
                        valida = false;
                        JOptionPane.showMessageDialog(this, "Debe registrar al menos un teléfono de la Madre", "ATENCIÓN...", INFORMATION_MESSAGE);
                    }    
        if(!valida)
            JOptionPane.showMessageDialog(this, "Hay datos obligatorios de la Madre sin completar", "ATENCION...", ERROR_MESSAGE);
        
        return valida;
    }

    private boolean validarPadre()
    {
        boolean valida = true;
        
        if(cbxTipoDocPadre.getSelectedIndex() == 0 || txtDNIPadre.getText().equalsIgnoreCase(""))
            valida = false;
        else if(txtApellidoPadre.getText().equalsIgnoreCase("") || txtNombrePadre.getText().equalsIgnoreCase(""))
                valida = false;
            else if(!optHombrePadre.isSelected() && !optMujerPadre.isSelected() && !optOtroPadre.isSelected())
                    valida = false;
                else if(cbxProvinciaPadre.getSelectedIndex() == 0 || cbxDepartamentoPadre.getSelectedIndex() == 0 || cbxLocalidadPadre.getSelectedIndex() == 0 || cbxBarrioPadre.getSelectedIndex() == 0 || 
                        cbxCallePadre.getSelectedIndex() == 0 || txtNroPadre.getText().equalsIgnoreCase(""))
                        valida = false;
                    if(tblTelefonosPadre.getRowCount() == 0)
                    {
                        valida = false;
                        JOptionPane.showMessageDialog(this, "Debe registrar al menos un teléfono del Padre", "ATENCIÓN...", INFORMATION_MESSAGE);
                    }    
        if(!valida)
            JOptionPane.showMessageDialog(this, "Hay datos obligatorios del Padre sin completar", "ATENCION...", ERROR_MESSAGE);
        
        return valida;
    }

    private boolean validarTutor()
    {
        boolean valida = true;
        
        if(cbxTipoDocTutor.getSelectedIndex() == 0 || txtDNITutor.getText().equalsIgnoreCase(""))
            valida = false;
        else if(txtApellidoTutor.getText().equalsIgnoreCase("") || txtNombreTutor.getText().equalsIgnoreCase(""))
                valida = false;
            else if(!optHombreTutor.isSelected() && !optMujerTutor.isSelected() && !optOtroTutor.isSelected())
                    valida = false;
                else if(cbxProvinciaTutor.getSelectedIndex() == 0 || cbxDepartamentoTutor.getSelectedIndex() == 0 || cbxLocalidadTutor.getSelectedIndex() == 0 || cbxBarrioTutor.getSelectedIndex() == 0 || 
                        cbxCalleTutor.getSelectedIndex() == 0 || txtNroTutor.getText().equalsIgnoreCase(""))
                        valida = false;
                    if(tblTelefonosTutor.getRowCount() == 0)
                    {
                        valida = false;
                        JOptionPane.showMessageDialog(this, "Debe registrar al menos un teléfono del Tutor", "ATENCIÓN...", INFORMATION_MESSAGE);
                    }    
        if(!valida)
            JOptionPane.showMessageDialog(this, "Hay datos obligatorios del tutor sin completar", "ATENCION...", ERROR_MESSAGE);
        
        return valida;
    }

    private void madreEsTutor()
    {
        String[] fila = new String[3];
        
        tutor = madre;
        cbxTipoDocTutor.setSelectedItem(cbxTipoDocMadre.getSelectedItem());
        txtDNITutor.setText(txtDNIMadre.getText());
        txtApellidoTutor.setText(txtApellidoMadre.getText());
        txtNombreTutor.setText(txtNombreMadre.getText());
        txtFechaNacimientoTutor.setDate(txtFechaNacimientoMadre.getDate());
        optHombreTutor.setSelected(optHombreMadre.isSelected());
        optMujerTutor.setSelected(optMujerMadre.isSelected());
        optOtroTutor.setSelected(optOtroMadre.isSelected());
        cbxProvinciaTutor.setSelectedItem(cbxProvinciaMadre.getSelectedItem());
        cbxDepartamentoTutor.setSelectedItem(cbxDepartamentoMadre.getSelectedItem());
        cbxLocalidadTutor.setSelectedItem(cbxLocalidadMadre.getSelectedItem());
        cbxBarrioTutor.setSelectedItem(cbxBarrioMadre.getSelectedItem());
        cbxCalleTutor.setSelectedItem(cbxCalleMadre.getSelectedItem());
        txtNroTutor.setText(txtNroMadre.getText());
        txtTorreTutor.setText(txtTorreMadre.getText());
        txtPisoTutor.setText(txtPisoMadre.getText());
        txtDeptoTutor.setText(txtDeptoMadre.getText());
        modeloTelefonosTutor.setRowCount(0);
        for(int i=0; i<tblTelefonosMadre.getRowCount(); i++)
        {
            fila[0] = tblTelefonosMadre.getValueAt(i, 0).toString();
            fila[1] = tblTelefonosMadre.getValueAt(i, 1).toString();
            fila[2] = tblTelefonosMadre.getValueAt(i, 2).toString();
            modeloTelefonosTutor.addRow(fila);
        }
    }
    
    private void padreEsTutor()
    {
        String[] fila = new String[3];
        
        tutor = padre;
        cbxTipoDocTutor.setSelectedItem(cbxTipoDocPadre.getSelectedItem());
        txtDNITutor.setText(txtDNIPadre.getText());
        txtApellidoTutor.setText(txtApellidoPadre.getText());
        txtNombreTutor.setText(txtNombrePadre.getText());
        txtFechaNacimientoTutor.setDate(txtFechaNacimientoPadre.getDate());
        optHombreTutor.setSelected(optHombrePadre.isSelected());
        optMujerTutor.setSelected(optMujerPadre.isSelected());
        optOtroTutor.setSelected(optOtroPadre.isSelected());
        cbxProvinciaTutor.setSelectedItem(cbxProvinciaPadre.getSelectedItem());
        cbxDepartamentoTutor.setSelectedItem(cbxDepartamentoPadre.getSelectedItem());
        cbxLocalidadTutor.setSelectedItem(cbxLocalidadPadre.getSelectedItem());
        cbxBarrioTutor.setSelectedItem(cbxBarrioPadre.getSelectedItem());
        cbxCalleTutor.setSelectedItem(cbxCallePadre.getSelectedItem());
        txtNroTutor.setText(txtNroPadre.getText());
        txtTorreTutor.setText(txtTorrePadre.getText());
        txtPisoTutor.setText(txtPisoPadre.getText());
        txtDeptoTutor.setText(txtDeptoPadre.getText());
        modeloTelefonosTutor.setRowCount(0);
        for(int i=0; i<tblTelefonosPadre.getRowCount(); i++)
        {
            fila[0] = tblTelefonosPadre.getValueAt(i, 0).toString();
            fila[1] = tblTelefonosPadre.getValueAt(i, 1).toString();
            fila[2] = tblTelefonosPadre.getValueAt(i, 2).toString();
            modeloTelefonosTutor.addRow(fila);
        }
    }

    private void grabarMadre()
    {
        Edificio edificio;
        Domicilio domicilio;
        Telefono telefono = new Telefono();
        java.sql.Date fechaNacimiento;
        byte sexo = 1;
        
        if(optHombreMadre.isSelected())
            sexo = 1;
        else if(optMujerMadre.isSelected())
                sexo = 2;
             else if(optOtroMadre.isSelected())
                    sexo = 3;

        if(txtFechaNacimientoMadre.isValid())
            fechaNacimiento = new Date(txtFechaNacimientoMadre.getDate().getTime());
        else
            fechaNacimiento = null;

        edificio = ctrlEdificio.leer(txtPisoMadre.getText().toUpperCase(), txtDeptoMadre.getText().toUpperCase(), txtTorreMadre.getText().toUpperCase());
        if(edificio == null)
        {
            edificio = new Edificio();
            edificio.setDepto(txtDeptoMadre.getText().toUpperCase());
            edificio.setPiso(txtPisoMadre.getText().toUpperCase());
            edificio.setTorre(txtTorreMadre.getText().toUpperCase());
            
            ctrlEdificio.crear(edificio);
            edificio = ctrlEdificio.leer(txtPisoMadre.getText().toUpperCase(), txtDeptoMadre.getText().toUpperCase(), txtTorreMadre.getText().toUpperCase());
        }
        domicilio = ctrlDomicilio.leer(cllM.getIdLugarDomicilio(), txtNroMadre.getText().trim().toUpperCase(), edificio.getIdEdificio());
        if(domicilio == null)
        {
            domicilio = new Domicilio();
            domicilio.setCalle(cllM);
            domicilio.setNumero(txtNroMadre.getText().trim().toUpperCase());
            domicilio.setEdificio(edificio);
            domicilio.setBorrado(false);
            
            ctrlDomicilio.crear(domicilio);
            domicilio = ctrlDomicilio.leer(cllM.getIdLugarDomicilio(), txtNroMadre.getText().trim().toUpperCase(), edificio.getIdEdificio());
            
        }
        
        madre = ctrlPersona.leer(ctrlTipoDocumento.leer(cbxTipoDocMadre.getSelectedItem().toString()).getIdtipoDocumento(), txtDNIMadre.getText());
        if(madre == null)
        {
            madre = new Persona();
            madre.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocMadre.getSelectedItem().toString()));
            madre.setNroDocumento(txtDNIMadre.getText());
            madre.setApellido(txtApellidoMadre.getText().trim().toUpperCase());
            madre.setNombre(txtNombreMadre.getText().trim().toUpperCase());
            madre.setFechaNacimiento(fechaNacimiento);
            madre.setSexo(sexo);
            madre.setDomicilio(domicilio);
            madre.setBorrado(false);
        
            ctrlPersona.crear(madre);

            for(int i=0; i<tblTelefonosMadre.getRowCount(); i++)
            {
                telefono.setTipoTelefono(ctrlTipoTelefono.leer(tblTelefonosMadre.getValueAt(i, 0).toString()));
                telefono.setCodigoArea(tblTelefonosMadre.getValueAt(i, 1).toString());
                telefono.setNumeroTelefono(tblTelefonosMadre.getValueAt(i, 2).toString());
                telefono.setPersona(madre);
                ctrlTelefono.crear(telefono);
            }
        }
        else
        {
            if(madre.isBorrado())
            {
                ctrlPersona.recuperar(madre.getIdPersona());
                madre.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocMadre.getSelectedItem().toString()));
                madre.setNroDocumento(txtDNIMadre.getText());
                madre.setApellido(txtApellidoMadre.getText().trim().toUpperCase());
                madre.setNombre(txtNombreMadre.getText().trim().toUpperCase());
                madre.setFechaNacimiento(fechaNacimiento);
                madre.setSexo(sexo);
                madre.setDomicilio(domicilio);
                
                ctrlPersona.editar(madre);
            }
        }
    }

    private void grabarPadre()
    {
        Edificio edificio;
        Domicilio domicilio;
        Telefono telefono = new Telefono();
        java.sql.Date fechaNacimiento;
        byte sexo = 1;
        
        if(optHombrePadre.isSelected())
            sexo = 1;
        else if(optMujerPadre.isSelected())
                sexo = 2;
             else if(optOtroPadre.isSelected())
                    sexo = 3;

        if(txtFechaNacimientoPadre.isValid())
            fechaNacimiento = new Date(txtFechaNacimientoPadre.getDate().getTime());
        else
            fechaNacimiento = null;

        edificio = ctrlEdificio.leer(txtPisoPadre.getText().toUpperCase(), txtDeptoPadre.getText().toUpperCase(), txtTorrePadre.getText().toUpperCase());
        if(edificio == null)
        {
            edificio = new Edificio();
            edificio.setDepto(txtDeptoPadre.getText().toUpperCase());
            edificio.setPiso(txtPisoPadre.getText().toUpperCase());
            edificio.setTorre(txtTorrePadre.getText().toUpperCase());
            
            ctrlEdificio.crear(edificio);
            edificio = ctrlEdificio.leer(txtPisoPadre.getText().toUpperCase(), txtDeptoPadre.getText().toUpperCase(), txtTorrePadre.getText().toUpperCase());
        }

        domicilio = ctrlDomicilio.leer(cllT.getIdLugarDomicilio(), txtNroPadre.getText().trim().toUpperCase(), edificio.getIdEdificio());
        if(domicilio == null)
        {
            domicilio = new Domicilio();
            domicilio.setCalle(cllT);
            domicilio.setNumero(txtNroPadre.getText().trim().toUpperCase());
            domicilio.setEdificio(edificio);
            domicilio.setBorrado(false);
            
            ctrlDomicilio.crear(domicilio);
            domicilio = ctrlDomicilio.leer(cllT.getIdLugarDomicilio(), txtNroPadre.getText().trim().toUpperCase(), edificio.getIdEdificio());
            
        }
        
        padre = ctrlPersona.leer(ctrlTipoDocumento.leer(cbxTipoDocPadre.getSelectedItem().toString()).getIdtipoDocumento(), txtDNIPadre.getText());
        if(padre == null)
        {
            padre = new Persona();
            padre.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocPadre.getSelectedItem().toString()));
            padre.setNroDocumento(txtDNIPadre.getText());
            padre.setApellido(txtApellidoPadre.getText().trim().toUpperCase());
            padre.setNombre(txtNombrePadre.getText().trim().toUpperCase());
            padre.setFechaNacimiento(fechaNacimiento);
            padre.setSexo(sexo);
            padre.setDomicilio(domicilio);
            padre.setBorrado(false);
        
            ctrlPersona.crear(padre);

            for(int i=0; i<tblTelefonosPadre.getRowCount(); i++)
            {
                telefono.setTipoTelefono(ctrlTipoTelefono.leer(tblTelefonosPadre.getValueAt(i, 0).toString()));
                telefono.setCodigoArea(tblTelefonosPadre.getValueAt(i, 1).toString());
                telefono.setNumeroTelefono(tblTelefonosPadre.getValueAt(i, 2).toString());
                telefono.setPersona(padre);
                ctrlTelefono.crear(telefono);
            }

        }
        else
        {
            if(padre.isBorrado())
            {
                ctrlPersona.recuperar(padre.getIdPersona());
                padre.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocPadre.getSelectedItem().toString()));
                padre.setNroDocumento(txtDNIPadre.getText());
                padre.setApellido(txtApellidoPadre.getText().trim().toUpperCase());
                padre.setNombre(txtNombrePadre.getText().trim().toUpperCase());
                padre.setFechaNacimiento(fechaNacimiento);
                padre.setSexo(sexo);
                padre.setDomicilio(domicilio);
                
                ctrlPersona.editar(padre);
            }
        }
    }

    private void grabarTutor()
    {
        Edificio edificio;
        Domicilio domicilio;
        Telefono telefono = new Telefono();
        java.sql.Date fechaNacimiento;
        byte sexo = 1;
        
        if(optHombreTutor.isSelected())
            sexo = 1;
        else if(optMujerTutor.isSelected())
                sexo = 2;
             else if(optOtroTutor.isSelected())
                    sexo = 3;

        if(txtFechaNacimientoTutor.isValid())
            fechaNacimiento = new Date(txtFechaNacimientoTutor.getDate().getTime());
        else
            fechaNacimiento = null;

        edificio = ctrlEdificio.leer(txtPisoTutor.getText().toUpperCase(), txtDeptoTutor.getText().toUpperCase(), txtTorreTutor.getText().toUpperCase());
        if(edificio == null)
        {
            edificio = new Edificio();
            edificio.setDepto(txtDeptoTutor.getText().toUpperCase());
            edificio.setPiso(txtPisoTutor.getText().toUpperCase());
            edificio.setTorre(txtTorreTutor.getText().toUpperCase());
            
            ctrlEdificio.crear(edificio);
            edificio = ctrlEdificio.leer(txtPisoTutor.getText().toUpperCase(), txtDeptoTutor.getText().toUpperCase(), txtTorreTutor.getText().toUpperCase());
        }

        domicilio = ctrlDomicilio.leer(cllT.getIdLugarDomicilio(), txtNroTutor.getText().trim().toUpperCase(), edificio.getIdEdificio());
        if(domicilio == null)
        {
            domicilio = new Domicilio();
            domicilio.setCalle(cllT);
            domicilio.setNumero(txtNroTutor.getText().trim().toUpperCase());
            domicilio.setEdificio(edificio);
            domicilio.setBorrado(false);
            
            ctrlDomicilio.crear(domicilio);
            domicilio = ctrlDomicilio.leer(cllT.getIdLugarDomicilio(), txtNroTutor.getText().trim().toUpperCase(), edificio.getIdEdificio());
            
        }
        
        tutor = ctrlPersona.leer(ctrlTipoDocumento.leer(cbxTipoDocTutor.getSelectedItem().toString()).getIdtipoDocumento(), txtDNITutor.getText());
        if(tutor == null)
        {
            tutor = new Persona();
            tutor.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocTutor.getSelectedItem().toString()));
            tutor.setNroDocumento(txtDNITutor.getText());
            tutor.setApellido(txtApellidoTutor.getText().trim().toUpperCase());
            tutor.setNombre(txtNombreTutor.getText().trim().toUpperCase());
            tutor.setFechaNacimiento(fechaNacimiento);
            tutor.setSexo(sexo);
            tutor.setDomicilio(domicilio);
            tutor.setBorrado(false);
        
            ctrlPersona.crear(tutor);

            for(int i=0; i<tblTelefonosTutor.getRowCount(); i++)
            {
                telefono.setTipoTelefono(ctrlTipoTelefono.leer(tblTelefonosTutor.getValueAt(i, 0).toString()));
                telefono.setCodigoArea(tblTelefonosTutor.getValueAt(i, 1).toString());
                telefono.setNumeroTelefono(tblTelefonosTutor.getValueAt(i, 2).toString());
                telefono.setPersona(tutor);
                ctrlTelefono.crear(telefono);
            }

        }
        else
        {
            if(tutor.isBorrado())
            {
                ctrlPersona.recuperar(tutor.getIdPersona());
                tutor.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocTutor.getSelectedItem().toString()));
                tutor.setNroDocumento(txtDNITutor.getText());
                tutor.setApellido(txtApellidoTutor.getText().trim().toUpperCase());
                tutor.setNombre(txtNombreTutor.getText().trim().toUpperCase());
                tutor.setFechaNacimiento(fechaNacimiento);
                tutor.setSexo(sexo);
                tutor.setDomicilio(domicilio);
                
                ctrlPersona.editar(tutor);
            }
        }
    }

    private void grabarAlumno()
    {
        Edificio edificio;
        Domicilio domicilio;
        AlumnoDiagnostico diagnostico;
        Prestacion prestacion;
        
        java.sql.Date fechaNacimiento;
        byte sexo = 1;
        
        if(optHombreAlumno.isSelected())
            sexo = 1;
        else if(optMujerAlumno.isSelected())
                sexo = 2;
             else if(optOtroAlumno.isSelected())
                    sexo = 3;

        if(txtFechaNacimiento.isValid())
            fechaNacimiento = new Date(txtFechaNacimiento.getDate().getTime());
        else
            fechaNacimiento = null;

        edificio = ctrlEdificio.leer(txtPisoAlumno.getText().toUpperCase(), txtDeptoAlumno.getText().toUpperCase(), txtTorreAlumno.getText().toUpperCase());
        if(edificio == null)
        {
            edificio = new Edificio();
            edificio.setDepto(txtDeptoAlumno.getText().toUpperCase());
            edificio.setPiso(txtPisoAlumno.getText().toUpperCase());
            edificio.setTorre(txtTorreAlumno.getText().toUpperCase());
            
            ctrlEdificio.crear(edificio);
//            edificio = ctrlEdificio.leer(txtPisoAlumno.getText().toUpperCase(), txtDeptoTutor.getText().toUpperCase(), txtTorreTutor.getText().toUpperCase());
        }

        domicilio = ctrlDomicilio.leer(cllA.getIdLugarDomicilio(), txtNroAlumno.getText().trim().toUpperCase(), edificio.getIdEdificio());
        if(domicilio == null)
        {
            domicilio = new Domicilio();
            domicilio.setCalle(cllA);
            domicilio.setNumero(txtNroAlumno.getText().trim().toUpperCase());
            domicilio.setEdificio(edificio);
            domicilio.setBorrado(false);
            
            ctrlDomicilio.crear(domicilio);
//            domicilio = ctrlDomicilio.leer(cllT.getIdLugarDomicilio(), txtNroTutor.getText().trim().toUpperCase(), edificio.getIdEdificio());
        }
        
        persAlumno = ctrlPersona.leer(ctrlTipoDocumento.leer(cbxTipoDocAlumno.getSelectedItem().toString()).getIdtipoDocumento(), txtDNIAlumno.getText());
        if(persAlumno == null)
        {
            persAlumno = new Persona();
            persAlumno.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocAlumno.getSelectedItem().toString()));
            persAlumno.setNroDocumento(txtDNIAlumno.getText());
            persAlumno.setApellido(txtApellidoAlumno.getText().trim().toUpperCase());
            persAlumno.setNombre(txtNombreAlumno.getText().trim().toUpperCase());
            persAlumno.setFechaNacimiento(fechaNacimiento);
            persAlumno.setSexo(sexo);
            persAlumno.setDomicilio(domicilio);
            persAlumno.setBorrado(false);
        
            ctrlPersona.crear(persAlumno);
        }
        else
        {
            if(persAlumno.isBorrado())
            {
                ctrlPersona.recuperar(persAlumno.getIdPersona());
                persAlumno.setTipoDocumento(ctrlTipoDocumento.leer(cbxTipoDocAlumno.getSelectedItem().toString()));
                persAlumno.setNroDocumento(txtDNITutor.getText());
                persAlumno.setApellido(txtApellidoTutor.getText().trim().toUpperCase());
                persAlumno.setNombre(txtNombreTutor.getText().trim().toUpperCase());
                persAlumno.setFechaNacimiento(fechaNacimiento);
                persAlumno.setSexo(sexo);
                persAlumno.setDomicilio(domicilio);
                
                ctrlPersona.editar(persAlumno);
            }
        }
        alumno = ctrlAlumno.existe(persAlumno.getIdPersona());
        
        if(alumno == null)
        {
            alumno = new Alumno();
            alumno.setPersona(persAlumno);
            alumno.setLugarNacimiento(ctrlLugarDomicilio.leer(cbxLugarNacimiento.getSelectedItem().toString(),3));
            alumno.setLegajo(txtLegajo.getText().toUpperCase());
            alumno.setTutor(tutor);
            alumno.setMadre(madre);
            alumno.setPadre(padre);
            alumno.setIntegrado(optIntegradoSI.isSelected());
            alumno.setObraSocial(ctrlObraSocial.leer(cbxObraSocial.getSelectedItem().toString()));
            alumno.setVtoCUD(new java.sql.Date(txtCUD.getDate().getTime()));
            alumno.setPension(optPensionSI.isSelected());
            alumno.setAsignacionUniversal(optAsignacionSI.isSelected());
            alumno.setBorrado(false);
        
            ctrlAlumno.crear(alumno);
        }
        else
        {
            if(alumno.isBorrado())
            {
                ctrlAlumno.recuperar(alumno.getIdAlumno());
                alumno.setPersona(persAlumno);
                alumno.setLugarNacimiento(ctrlLugarDomicilio.leer(cbxLugarNacimiento.getSelectedItem().toString(),3));
                alumno.setLegajo(txtLegajo.getText().toUpperCase());
                alumno.setTutor(tutor);
                alumno.setMadre(madre);
                alumno.setPadre(padre);
                alumno.setIntegrado(optIntegradoSI.isSelected());
                alumno.setObraSocial(ctrlObraSocial.leer(cbxObraSocial.getSelectedItem().toString()));
                alumno.setVtoCUD(new java.sql.Date(txtCUD.getDate().getTime()));
                alumno.setPension(optPensionSI.isSelected());
                alumno.setAsignacionUniversal(optAsignacionSI.isSelected());
            }
            ctrlAlumno.editar(alumno);
        }
        for(int i=0; i<tblDiagnosticoAlumno.getRowCount(); i++)
        {
            diagnostico = new AlumnoDiagnostico();
            diagnostico.setAlumno(alumno);
            diagnostico.setDiagnostico(ctrlDiagnostico.leerConCodigo(tblDiagnosticoAlumno.getValueAt(i, 0).toString()));
            ctrlAlumnoDiagnostico.crear(diagnostico);
        }
    
        if(optIntegradoSI.isSelected())
        {
            prestacion = new Prestacion();
            prestacion.setAlumno(alumno);
            prestacion.setLugar(ctrlLugarPrestacion.leer(cbxLugar.getSelectedItem().toString()));
            prestacion.setNivel(ctrlNivel.leer(cbxNivel.getSelectedItem().toString()));
            ctrlPrestacion.crear(prestacion);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDiagnostico;
    private javax.swing.JButton btnAgregarMadre;
    private javax.swing.JButton btnAgregarPadre;
    private javax.swing.JButton btnAgregarTutor;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnQuitarDiagnostico;
    private javax.swing.JButton btnQuitarMadre;
    private javax.swing.JButton btnQuitarPadre;
    private javax.swing.JButton btnQuitarTutor;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxBarrioAlumno;
    private javax.swing.JComboBox<String> cbxBarrioMadre;
    private javax.swing.JComboBox<String> cbxBarrioPadre;
    private javax.swing.JComboBox<String> cbxBarrioTutor;
    private javax.swing.JComboBox<String> cbxCalleAlumno;
    private javax.swing.JComboBox<String> cbxCalleMadre;
    private javax.swing.JComboBox<String> cbxCallePadre;
    private javax.swing.JComboBox<String> cbxCalleTutor;
    private javax.swing.JComboBox<String> cbxDepartamentoAlumno;
    private javax.swing.JComboBox<String> cbxDepartamentoMadre;
    private javax.swing.JComboBox<String> cbxDepartamentoPadre;
    private javax.swing.JComboBox<String> cbxDepartamentoTutor;
    private javax.swing.JComboBox<String> cbxLocalidadAlumno;
    private javax.swing.JComboBox<String> cbxLocalidadMadre;
    private javax.swing.JComboBox<String> cbxLocalidadPadre;
    private javax.swing.JComboBox<String> cbxLocalidadTutor;
    private javax.swing.JComboBox<String> cbxLugar;
    private javax.swing.JComboBox<String> cbxLugarNacimiento;
    private javax.swing.JComboBox<String> cbxNivel;
    private javax.swing.JComboBox<String> cbxObraSocial;
    private javax.swing.JComboBox<String> cbxProvinciaAlumno;
    private javax.swing.JComboBox<String> cbxProvinciaMadre;
    private javax.swing.JComboBox<String> cbxProvinciaPadre;
    private javax.swing.JComboBox<String> cbxProvinciaTutor;
    private javax.swing.JComboBox<String> cbxTipoDocAlumno;
    private javax.swing.JComboBox<String> cbxTipoDocMadre;
    private javax.swing.JComboBox<String> cbxTipoDocPadre;
    private javax.swing.JComboBox<String> cbxTipoDocTutor;
    private javax.swing.JComboBox<String> cbxTipoTelefonoMadre;
    private javax.swing.JComboBox<String> cbxTipoTelefonoPadre;
    private javax.swing.JComboBox<String> cbxTipoTelefonoTutor;
    private javax.swing.JCheckBox chkEsTutorMadre;
    private javax.swing.JCheckBox chkEsTutorPadre;
    private javax.swing.JTabbedPane fichas;
    private javax.swing.ButtonGroup grpAsignacion;
    private javax.swing.ButtonGroup grpIntegrado;
    private javax.swing.ButtonGroup grpPension;
    private javax.swing.ButtonGroup grpSexoAlumno;
    private javax.swing.ButtonGroup grpSexoMadre;
    private javax.swing.ButtonGroup grpSexoPadre;
    private javax.swing.ButtonGroup grpSexoTutor;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblApellidoAlumno;
    private javax.swing.JLabel lblApellidoMadre;
    private javax.swing.JLabel lblApellidoPadre;
    private javax.swing.JLabel lblApellidoTutor;
    private javax.swing.JLabel lblAsignacion;
    private javax.swing.JLabel lblBarrioAlumno;
    private javax.swing.JLabel lblBarrioMadre;
    private javax.swing.JLabel lblBarrioPadre;
    private javax.swing.JLabel lblBarrioTutor;
    private javax.swing.JLabel lblCUD;
    private javax.swing.JLabel lblCalleAlumno;
    private javax.swing.JLabel lblCalleMadre;
    private javax.swing.JLabel lblCallePadre;
    private javax.swing.JLabel lblCalleTutor;
    private javax.swing.JLabel lblCodAreaMadre;
    private javax.swing.JLabel lblCodAreaPadre;
    private javax.swing.JLabel lblCodAreaTutor;
    private javax.swing.JLabel lblDNIAlumno;
    private javax.swing.JLabel lblDNIMadre;
    private javax.swing.JLabel lblDNIPadre;
    private javax.swing.JLabel lblDNITutor;
    private javax.swing.JLabel lblDepartamentoAlumno;
    private javax.swing.JLabel lblDepartamentoMadre;
    private javax.swing.JLabel lblDepartamentoPadre;
    private javax.swing.JLabel lblDepartamentoTutor;
    private javax.swing.JLabel lblDeptoAlumno;
    private javax.swing.JLabel lblDeptoMadre;
    private javax.swing.JLabel lblDeptoPadre;
    private javax.swing.JLabel lblDeptoTutor;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFechaNacimientoMadre;
    private javax.swing.JLabel lblFechaNacimientoPadre;
    private javax.swing.JLabel lblFechaNacimientoTutor;
    private javax.swing.JLabel lblIntegrado;
    private javax.swing.JLabel lblLegajo;
    private javax.swing.JLabel lblLocalidadAlumno;
    private javax.swing.JLabel lblLocalidadMadre;
    private javax.swing.JLabel lblLocalidadPadre;
    private javax.swing.JLabel lblLocalidadTutor;
    private javax.swing.JLabel lblLugar;
    private javax.swing.JLabel lblLugarNacimiento;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblNombreAlumno;
    private javax.swing.JLabel lblNombreMadre;
    private javax.swing.JLabel lblNombrePadre;
    private javax.swing.JLabel lblNombreTutor;
    private javax.swing.JLabel lblNroAlumno;
    private javax.swing.JLabel lblNroMadre;
    private javax.swing.JLabel lblNroPadre;
    private javax.swing.JLabel lblNroTutor;
    private javax.swing.JLabel lblObraSocial;
    private javax.swing.JLabel lblPension;
    private javax.swing.JLabel lblPisoAlumno;
    private javax.swing.JLabel lblPisoMadre;
    private javax.swing.JLabel lblPisoPadre;
    private javax.swing.JLabel lblPisoTutor;
    private javax.swing.JLabel lblProvinciaAlumno;
    private javax.swing.JLabel lblProvinciaMadre;
    private javax.swing.JLabel lblProvinciaPadre;
    private javax.swing.JLabel lblProvinciaTutor;
    private javax.swing.JLabel lblTelefonoPadre;
    private javax.swing.JLabel lblTelefonoTutor;
    private javax.swing.JLabel lblTipoDocAlumno;
    private javax.swing.JLabel lblTipoDocMadre;
    private javax.swing.JLabel lblTipoDocPadre;
    private javax.swing.JLabel lblTipoDocTutor;
    private javax.swing.JLabel lblTorreAlumno;
    private javax.swing.JLabel lblTorreMadre;
    private javax.swing.JLabel lblTorrePadre;
    private javax.swing.JLabel lblTorreTutor;
    private javax.swing.JRadioButton optAsignacionNO;
    private javax.swing.JRadioButton optAsignacionSI;
    private javax.swing.JRadioButton optHombreAlumno;
    private javax.swing.JRadioButton optHombreMadre;
    private javax.swing.JRadioButton optHombrePadre;
    private javax.swing.JRadioButton optHombreTutor;
    private javax.swing.JRadioButton optIntegradoNO;
    private javax.swing.JRadioButton optIntegradoSI;
    private javax.swing.JRadioButton optMujerAlumno;
    private javax.swing.JRadioButton optMujerMadre;
    private javax.swing.JRadioButton optMujerPadre;
    private javax.swing.JRadioButton optMujerTutor;
    private javax.swing.JRadioButton optOtroAlumno;
    private javax.swing.JRadioButton optOtroMadre;
    private javax.swing.JRadioButton optOtroPadre;
    private javax.swing.JRadioButton optOtroTutor;
    private javax.swing.JRadioButton optPensionNO;
    private javax.swing.JRadioButton optPensionSI;
    private javax.swing.JScrollPane pnlAlumno;
    private javax.swing.JPanel pnlDatosAcademicos;
    private javax.swing.JPanel pnlDatosPersonalesAlumno;
    private javax.swing.JPanel pnlDomicilioAlumno;
    private javax.swing.JPanel pnlMadre;
    private javax.swing.JPanel pnlPadre;
    private javax.swing.JPanel pnlSexo;
    private javax.swing.JPanel pnlTutor;
    private javax.swing.JScrollPane scrollDiagnosticoAlumno;
    private javax.swing.JScrollPane scrollDiagnosticos;
    private javax.swing.JTable tblDiagnosticoAlumno;
    private javax.swing.JTable tblDiagnosticos;
    private javax.swing.JTable tblTelefonosMadre;
    private javax.swing.JTable tblTelefonosPadre;
    private javax.swing.JTable tblTelefonosTutor;
    private javax.swing.JTextField txtApellidoAlumno;
    private javax.swing.JTextField txtApellidoMadre;
    private javax.swing.JTextField txtApellidoPadre;
    private javax.swing.JTextField txtApellidoTutor;
    private com.toedter.calendar.JDateChooser txtCUD;
    private javax.swing.JTextField txtCodAreaMadre;
    private javax.swing.JTextField txtCodAreaPadre;
    private javax.swing.JTextField txtCodAreaTutor;
    private javax.swing.JTextField txtDNIAlumno;
    private javax.swing.JTextField txtDNIMadre;
    private javax.swing.JTextField txtDNIPadre;
    private javax.swing.JTextField txtDNITutor;
    private javax.swing.JTextField txtDeptoAlumno;
    private javax.swing.JTextField txtDeptoMadre;
    private javax.swing.JTextField txtDeptoPadre;
    private javax.swing.JTextField txtDeptoTutor;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private com.toedter.calendar.JDateChooser txtFechaNacimientoMadre;
    private com.toedter.calendar.JDateChooser txtFechaNacimientoPadre;
    private com.toedter.calendar.JDateChooser txtFechaNacimientoTutor;
    private javax.swing.JTextField txtLegajo;
    private javax.swing.JTextField txtNombreAlumno;
    private javax.swing.JTextField txtNombreMadre;
    private javax.swing.JTextField txtNombrePadre;
    private javax.swing.JTextField txtNombreTutor;
    private javax.swing.JTextField txtNroAlumno;
    private javax.swing.JTextField txtNroMadre;
    private javax.swing.JTextField txtNroPadre;
    private javax.swing.JTextField txtNroTutor;
    private javax.swing.JTextField txtPisoAlumno;
    private javax.swing.JTextField txtPisoMadre;
    private javax.swing.JTextField txtPisoPadre;
    private javax.swing.JTextField txtPisoTutor;
    private javax.swing.JTextField txtTelefonoMadre;
    private javax.swing.JTextField txtTelefonoPadre;
    private javax.swing.JTextField txtTelefonoTutor;
    private javax.swing.JTextField txtTorreAlumno;
    private javax.swing.JTextField txtTorreMadre;
    private javax.swing.JTextField txtTorrePadre;
    private javax.swing.JTextField txtTorreTutor;
    // End of variables declaration//GEN-END:variables
}
