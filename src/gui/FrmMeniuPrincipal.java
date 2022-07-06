/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.MarcaController;
import controllers.ModelController;
import controllers.SoferController;
import controllers.TirController;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Stefan
 */
public class FrmMeniuPrincipal extends javax.swing.JFrame {

    private SoferController soferController = new SoferController();
    private TirController tirController = new TirController();
    private MarcaController marcaController = new MarcaController();
    private ModelController modelController = new ModelController();
    
    public FrmMeniuPrincipal() throws IOException {
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblInfoSoferi = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        meniu = new javax.swing.JMenuBar();
        meniuAdministrare = new javax.swing.JMenu();
        btnAdministrareSoferi = new javax.swing.JMenuItem();
        btnAdministrareTiruri = new javax.swing.JMenuItem();
        btnAdministrareMarci = new javax.swing.JMenuItem();
        btnAdministrareModele = new javax.swing.JMenuItem();
        btnAdministrareSoferiTiruri = new javax.swing.JMenuItem();
        meniuCreare = new javax.swing.JMenu();
        btnInregistrareNoua = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblInfoSoferi.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblInfoSoferi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfoSoferi.setText("Informatii soferi:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoSoferi, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoSoferi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informatii tiruri:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ultimele inregistrari (dublu click pentru mai multe informatii)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 161, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        setJMenuBar(meniu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                try {
                    new FrmMeniuPrincipal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FrmMeniuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnAdministrareMarci;
    private javax.swing.JMenuItem btnAdministrareModele;
    private javax.swing.JMenuItem btnAdministrareSoferi;
    private javax.swing.JMenuItem btnAdministrareSoferiTiruri;
    private javax.swing.JMenuItem btnAdministrareTiruri;
    private javax.swing.JMenuItem btnInregistrareNoua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblInfoSoferi;
    private javax.swing.JMenuBar meniu;
    private javax.swing.JMenu meniuAdministrare;
    private javax.swing.JMenu meniuCreare;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
