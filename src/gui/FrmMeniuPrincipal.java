/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.MarcaController;
import controllers.MeniuPrincipalController;
import controllers.ModelController;
import controllers.SoferController;
import controllers.SoferiTiruriController;
import controllers.TirController;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.Sofer;
import tablemodel.ColumnResizer1;
import tablemodels.TableModelInregistrari;
/**
 *
 * @author Stefan
 */
public class FrmMeniuPrincipal extends javax.swing.JFrame {

    private SoferController soferController = new SoferController();
    private TirController tirController = new TirController();
    private MarcaController marcaController = new MarcaController();
    private ModelController modelController = new ModelController();
    private SoferiTiruriController soferiTiruriController = new SoferiTiruriController();
    private MeniuPrincipalController meniuPrincipalController;
    
    
    public FrmMeniuPrincipal() {
        ImageIcon i = new ImageIcon("src/resources/soferIcon.png");
        Image imagine = i.getImage();
        Image imagine1 = imagine.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        i = new ImageIcon(imagine1);
        ImageIcon i1 = new ImageIcon("src/resources/tirIcon.png");
        imagine = i1.getImage();
        imagine1 = imagine.getScaledInstance(14, 14, java.awt.Image.SCALE_SMOOTH);
        i1 = new ImageIcon(imagine1);
        initComponents();
        btnAdministrareSoferi.setIcon(i);
        btnAdministrareTiruri.setIcon(i1);
        
        lstLibere.setFixedCellHeight(70);
        
        ColumnResizer1.adjustColumnPreferredWidths(tblInregistrari);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tableModelInregistrari1 = new tablemodels.TableModelInregistrari();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblTiruriInCursa = new javax.swing.JLabel();
        lblTiruriLibere = new javax.swing.JLabel();
        lblTiruriInService = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstInCursa = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstLibere = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstInService = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnAdaugaInregistrare = new javax.swing.JButton();
        btnEditeazaInregistrare = new javax.swing.JButton();
        btnFinalizare = new javax.swing.JButton();
        rdbInDesfasurare = new javax.swing.JRadioButton();
        rdbFinalizate = new javax.swing.JRadioButton();
        rdbToate = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtFiltreaza = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblInregistrari = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstSoferiLiberi = new javax.swing.JList();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        lstSoferiFaraTir = new javax.swing.JList();
        meniu = new javax.swing.JMenuBar();
        meniuAdministrare = new javax.swing.JMenu();
        btnAdministrareSoferi = new javax.swing.JMenuItem();
        btnAdministrareTiruri = new javax.swing.JMenuItem();
        btnAdministrareMarci = new javax.swing.JMenuItem();
        btnAdministrareModele = new javax.swing.JMenuItem();
        btnAdministrareSoferiTiruri = new javax.swing.JMenuItem();
        meniuCreare = new javax.swing.JMenu();
        btnInregistrareNoua = new javax.swing.JMenuItem();
        meniuProfil = new javax.swing.JMenu();
        meniuLogOut = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informatii tiruri:");

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 110, 5);
        flowLayout1.setAlignOnBaseline(true);
        jPanel6.setLayout(flowLayout1);

        lblTiruriInCursa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTiruriInCursa.setText("In Cursa:");
        jPanel6.add(lblTiruriInCursa);

        lblTiruriLibere.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTiruriLibere.setText("Libere:");
        jPanel6.add(lblTiruriLibere);

        lblTiruriInService.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTiruriInService.setText("In Service:");
        jPanel6.add(lblTiruriInService);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane4.setBorder(null);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(75, 300));

        lstInCursa.setBackground(new java.awt.Color(214, 217, 223));
        lstInCursa.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lstInCursa.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstInCursa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstInCursa.setInheritsPopupMenu(true);
        lstInCursa.setMaximumSize(new java.awt.Dimension(500, 500));
        lstInCursa.setMinimumSize(new java.awt.Dimension(100, 300));
        lstInCursa.setPreferredSize(new java.awt.Dimension(100, 300));
        lstInCursa.setValueIsAdjusting(true);
        jScrollPane4.setViewportView(lstInCursa);

        jPanel2.add(jScrollPane4);

        jScrollPane7.setBorder(null);
        jScrollPane7.setPreferredSize(new java.awt.Dimension(75, 300));

        lstLibere.setBackground(new java.awt.Color(214, 217, 223));
        lstLibere.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lstLibere.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstLibere.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstLibere.setInheritsPopupMenu(true);
        lstLibere.setMaximumSize(new java.awt.Dimension(500, 500));
        lstLibere.setMinimumSize(new java.awt.Dimension(100, 300));
        lstLibere.setPreferredSize(new java.awt.Dimension(100, 300));
        lstLibere.setValueIsAdjusting(true);
        jScrollPane7.setViewportView(lstLibere);

        jPanel2.add(jScrollPane7);

        jScrollPane3.setBorder(null);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(75, 300));

        lstInService.setBackground(new java.awt.Color(214, 217, 223));
        lstInService.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lstInService.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstInService.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstInService.setMaximumSize(new java.awt.Dimension(500, 500));
        lstInService.setPreferredSize(new java.awt.Dimension(100, 300));
        lstInService.setValueIsAdjusting(true);
        jScrollPane3.setViewportView(lstInService);

        jPanel2.add(jScrollPane3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ultimele inregistrari:");

        btnAdaugaInregistrare.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAdaugaInregistrare.setText("Adauga");
        btnAdaugaInregistrare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdaugaInregistrareActionPerformed(evt);
            }
        });
        jPanel3.add(btnAdaugaInregistrare);

        btnEditeazaInregistrare.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEditeazaInregistrare.setText("Editeaza");
        btnEditeazaInregistrare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditeazaInregistrareActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditeazaInregistrare);

        btnFinalizare.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnFinalizare.setText("Finalizeaza");
        btnFinalizare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizareActionPerformed(evt);
            }
        });
        jPanel3.add(btnFinalizare);

        buttonGroup1.add(rdbInDesfasurare);
        rdbInDesfasurare.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rdbInDesfasurare.setText("In desfasurare");
        rdbInDesfasurare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbInDesfasurareActionPerformed(evt);
            }
        });
        jPanel3.add(rdbInDesfasurare);

        buttonGroup1.add(rdbFinalizate);
        rdbFinalizate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rdbFinalizate.setText("Finalizate");
        rdbFinalizate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbFinalizateActionPerformed(evt);
            }
        });
        jPanel3.add(rdbFinalizate);

        buttonGroup1.add(rdbToate);
        rdbToate.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        rdbToate.setSelected(true);
        rdbToate.setText("Toate");
        rdbToate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbToateActionPerformed(evt);
            }
        });
        jPanel3.add(rdbToate);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("FIltreaza:");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 24));

        txtFiltreaza.setMinimumSize(new java.awt.Dimension(100, 24));
        txtFiltreaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltreazaActionPerformed(evt);
            }
        });
        txtFiltreaza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltreazaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltreazaKeyTyped(evt);
            }
        });
        jPanel7.add(txtFiltreaza, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 318, -1));

        jPanel3.add(jPanel7);

        tblInregistrari.setModel(tableModelInregistrari1);
        jScrollPane5.setViewportView(tblInregistrari);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(193, 193, 193))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addGap(46, 46, 46))
        );

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Soferi liberi:");

        jScrollPane8.setBorder(null);

        lstSoferiLiberi.setBackground(new java.awt.Color(214, 217, 223));
        lstSoferiLiberi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane8.setViewportView(lstSoferiLiberi);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel5))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("<html> Soferi fara<br> tir asociat:<br> </html>");

        jScrollPane9.setBorder(null);

        lstSoferiFaraTir.setBackground(new java.awt.Color(214, 217, 223));
        lstSoferiFaraTir.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane9.setViewportView(lstSoferiFaraTir);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        meniuAdministrare.setText("Administrare");
        meniuAdministrare.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        btnAdministrareSoferi.setText("Administrare Soferi");
        btnAdministrareSoferi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrareSoferiActionPerformed(evt);
            }
        });
        meniuAdministrare.add(btnAdministrareSoferi);

        btnAdministrareTiruri.setText("Administrare Tiruri");
        btnAdministrareTiruri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrareTiruriActionPerformed(evt);
            }
        });
        meniuAdministrare.add(btnAdministrareTiruri);

        btnAdministrareMarci.setText("Administrare Marci");
        btnAdministrareMarci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrareMarciActionPerformed(evt);
            }
        });
        meniuAdministrare.add(btnAdministrareMarci);

        btnAdministrareModele.setText("Administrare Modele");
        btnAdministrareModele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrareModeleActionPerformed(evt);
            }
        });
        meniuAdministrare.add(btnAdministrareModele);

        btnAdministrareSoferiTiruri.setText("Administrare Soferi-Tiruri");
        btnAdministrareSoferiTiruri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministrareSoferiTiruriActionPerformed(evt);
            }
        });
        meniuAdministrare.add(btnAdministrareSoferiTiruri);

        meniu.add(meniuAdministrare);

        meniuCreare.setText("New");
        meniuCreare.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        btnInregistrareNoua.setText("Inregistrare");
        btnInregistrareNoua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInregistrareNouaActionPerformed(evt);
            }
        });
        meniuCreare.add(btnInregistrareNoua);

        meniu.add(meniuCreare);

        meniuProfil.setText("Profil");
        meniuProfil.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        meniuProfil.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        meniuProfil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        meniu.add(meniuProfil);

        meniuLogOut.setText("jMenu1");
        meniu.add(meniuLogOut);

        setJMenuBar(meniu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdministrareSoferiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrareSoferiActionPerformed
        soferController.actionIndex(this);
    }//GEN-LAST:event_btnAdministrareSoferiActionPerformed

    private void btnAdministrareTiruriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrareTiruriActionPerformed
        tirController.actionIndex(this);
    }//GEN-LAST:event_btnAdministrareTiruriActionPerformed

    private void btnAdministrareMarciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrareMarciActionPerformed
        marcaController.actionIndex(this);
    }//GEN-LAST:event_btnAdministrareMarciActionPerformed

    private void btnAdministrareModeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrareModeleActionPerformed
        modelController.actionIndex(this);
    }//GEN-LAST:event_btnAdministrareModeleActionPerformed

    private void btnInregistrareNouaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInregistrareNouaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInregistrareNouaActionPerformed

    private void btnAdministrareSoferiTiruriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministrareSoferiTiruriActionPerformed
        // TODO add your handling code here:
        soferiTiruriController.actionIndex(this);
    }//GEN-LAST:event_btnAdministrareSoferiTiruriActionPerformed

    private void btnAdaugaInregistrareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdaugaInregistrareActionPerformed
        // TODO add your handling code here:
        meniuPrincipalController.actionCreate(this);
    }//GEN-LAST:event_btnAdaugaInregistrareActionPerformed

    private void btnEditeazaInregistrareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditeazaInregistrareActionPerformed
        // TODO add your handling code here:
        meniuPrincipalController.actionEdit(this);
    }//GEN-LAST:event_btnEditeazaInregistrareActionPerformed

    private void rdbToateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbToateActionPerformed
        // TODO add your handling code here:
        meniuPrincipalController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbToateActionPerformed

    private void rdbFinalizateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbFinalizateActionPerformed
        // TODO add your handling code here:
        meniuPrincipalController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbFinalizateActionPerformed

    private void rdbInDesfasurareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbInDesfasurareActionPerformed
        // TODO add your handling code here:
        meniuPrincipalController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbInDesfasurareActionPerformed

    private void btnFinalizareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizareActionPerformed
        // TODO add your handling code here:
        meniuPrincipalController.endInregistrare();
    }//GEN-LAST:event_btnFinalizareActionPerformed

    private void txtFiltreazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltreazaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltreazaActionPerformed

    private void txtFiltreazaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltreazaKeyReleased
        // TODO add your handling code here:
//        meniuPrincipalController.actionFilter();
    }//GEN-LAST:event_txtFiltreazaKeyReleased

    private void txtFiltreazaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltreazaKeyTyped
        // TODO add your handling code here:
                meniuPrincipalController.actionFilter();
    }//GEN-LAST:event_txtFiltreazaKeyTyped

    public JList<String> getLstInCursa() {
        return lstInCursa;
    }

    public JList<String> getLstInService() {
        return lstInService;
    }

    public JList<String> getLstLibere() {
        return lstLibere;
    }

    public JTable getTblInregistrari() {
        return tblInregistrari;
    }

    public TableModelInregistrari getTableModelInregistrari1() {
        return tableModelInregistrari1;
    }

    public JButton getBtnEditeazaInregistrare() {
        return btnEditeazaInregistrare;
    }

    public JButton getBtnAdaugaInregistrare() {
        return btnAdaugaInregistrare;
    }

    public JRadioButton getRdbToate() {
        return rdbToate;
    }

    public JRadioButton getRdbFinalizate() {
        return rdbFinalizate;
    }

    public JRadioButton getRdbInDesfasurare() {
        return rdbInDesfasurare;
    }

    public void setMeniuPrincipalController(MeniuPrincipalController meniuPrincipalController) {
        this.meniuPrincipalController = meniuPrincipalController;
    }

    public JList<Sofer> getLstSoferiLiberi() {
        return lstSoferiLiberi;
    }

    public JList getLstSoferiFaraTir() {
        return lstSoferiFaraTir;
    }
    
    public JButton getBtnFinalizare() {
        return btnFinalizare;
    }

    public JTextField getTxtFiltreaza() {
        return txtFiltreaza;
    }
   
    
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
            java.util.logging.Logger.getLogger(FrmMeniuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMeniuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMeniuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMeniuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmMeniuPrincipal f = new FrmMeniuPrincipal();
                f.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdaugaInregistrare;
    private javax.swing.JMenuItem btnAdministrareMarci;
    private javax.swing.JMenuItem btnAdministrareModele;
    private javax.swing.JMenuItem btnAdministrareSoferi;
    private javax.swing.JMenuItem btnAdministrareSoferiTiruri;
    private javax.swing.JMenuItem btnAdministrareTiruri;
    private javax.swing.JButton btnEditeazaInregistrare;
    private javax.swing.JButton btnFinalizare;
    private javax.swing.JMenuItem btnInregistrareNoua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblTiruriInCursa;
    private javax.swing.JLabel lblTiruriInService;
    private javax.swing.JLabel lblTiruriLibere;
    private javax.swing.JList<String> lstInCursa;
    private javax.swing.JList<String> lstInService;
    private javax.swing.JList<String> lstLibere;
    private javax.swing.JList lstSoferiFaraTir;
    private javax.swing.JList lstSoferiLiberi;
    private javax.swing.JMenuBar meniu;
    private javax.swing.JMenu meniuAdministrare;
    private javax.swing.JMenu meniuCreare;
    private javax.swing.JMenu meniuLogOut;
    private javax.swing.JMenu meniuProfil;
    private javax.swing.JRadioButton rdbFinalizate;
    private javax.swing.JRadioButton rdbInDesfasurare;
    private javax.swing.JRadioButton rdbToate;
    private tablemodels.TableModelInregistrari tableModelInregistrari1;
    private javax.swing.JTable tblInregistrari;
    private javax.swing.JTextField txtFiltreaza;
    // End of variables declaration//GEN-END:variables
}
