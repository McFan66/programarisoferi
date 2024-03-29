/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.SoferiTiruriController;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;

/**
 *
 * @author Vlad
 */
public class FrmAdministrareSoferiTiruri extends javax.swing.JFrame {

    /**
     * Creates new form FrmAdministrareSoferiTiruri
     */
    private SoferiTiruriController soferiTiruriController;
    
    public FrmAdministrareSoferiTiruri() {
        initComponents();
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSoferiTiruri = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnAdauga = new javax.swing.JButton();
        btnEditeaza = new javax.swing.JButton();
        btnActiveazaInactiveaza = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        rdbActive = new javax.swing.JRadioButton();
        rdbInactive = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrare Soferi-Tiruri");

        tblSoferiTiruri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSoferiTiruri.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(tblSoferiTiruri);

        btnAdauga.setText("Adauga");
        btnAdauga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdaugaActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdauga);

        btnEditeaza.setText("Editeaza");
        btnEditeaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditeazaActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditeaza);

        btnActiveazaInactiveaza.setText("Activeaza");
        btnActiveazaInactiveaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveazaInactiveazaActionPerformed(evt);
            }
        });
        jPanel2.add(btnActiveazaInactiveaza);

        buttonGroup1.add(rdbActive);
        rdbActive.setSelected(true);
        rdbActive.setText("Active");
        rdbActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbActiveActionPerformed(evt);
            }
        });
        jPanel3.add(rdbActive);

        buttonGroup1.add(rdbInactive);
        rdbInactive.setText("Inactive");
        rdbInactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbInactiveActionPerformed(evt);
            }
        });
        jPanel3.add(rdbInactive);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addGap(110, 110, 110)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

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
    }// </editor-fold>//GEN-END:initComponents

    public void setSoferiTiruriController(SoferiTiruriController soferiTiruriController) {
        this.soferiTiruriController = soferiTiruriController;
    }

    public JTable getTblSoferiTiruri() {
        return tblSoferiTiruri;
    }

    public JButton getBtnAdauga() {
        return btnAdauga;
    }

    public JButton getBtnEditeaza() {
        return btnEditeaza;
    }

    public JButton getBtnActiveazaInactiveaza() {
        return btnActiveazaInactiveaza;
    }
    
    private void btnAdaugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdaugaActionPerformed
        // TODO add your handling code here
        soferiTiruriController.actionCreate(this);
    }//GEN-LAST:event_btnAdaugaActionPerformed

    private void btnEditeazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditeazaActionPerformed
        // TODO add your handling code here:
        soferiTiruriController.actionUpdate(this);
    }//GEN-LAST:event_btnEditeazaActionPerformed

    private void btnActiveazaInactiveazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveazaInactiveazaActionPerformed
        // TODO add your handling code here:
        soferiTiruriController.actionToggleActiv();
    }//GEN-LAST:event_btnActiveazaInactiveazaActionPerformed

    private void rdbActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbActiveActionPerformed
        // TODO add your handling code here:
        soferiTiruriController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbActiveActionPerformed

    private void rdbInactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbInactiveActionPerformed
        // TODO add your handling code here:
        soferiTiruriController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbInactiveActionPerformed

    public JRadioButton getRdbActive() {
        return rdbActive;
    }

    public JRadioButton getRdbInactive() {
        return rdbInactive;
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
            java.util.logging.Logger.getLogger(FrmAdministrareSoferiTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareSoferiTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareSoferiTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareSoferiTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdministrareSoferiTiruri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActiveazaInactiveaza;
    private javax.swing.JButton btnAdauga;
    private javax.swing.JButton btnEditeaza;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbActive;
    private javax.swing.JRadioButton rdbInactive;
    private javax.swing.JTable tblSoferiTiruri;
    // End of variables declaration//GEN-END:variables
}
