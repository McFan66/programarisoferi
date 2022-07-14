/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.SoferController;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import tablemodels.TableModelSoferi;

/**
 *
 * @author Stefan
 */
public class FrmAdministrareSoferi extends javax.swing.JDialog {

    /**
     * Creates new form FrmAdministrareSoferi
     */
    private SoferController soferController;
    
    public FrmAdministrareSoferi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setSoferController(SoferController soferController) {
        this.soferController = soferController;
    }

    public JTable getTblSoferi() {
        return tblSoferi;
    }

    public TableModelSoferi getTableModelSoferi1() {
        return tableModelSoferi1;
    }

    public JRadioButton getRdbActiv() {
        return rdbActiv;
    }

    public JRadioButton getRdbInactiv() {
        return rdbInactiv;
    }

    public JRadioButton getRdbToate() {
        return rdbToate;
    }

    public JButton getBtnSterge() {
        return btnSterge;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableModelSoferi1 = new tablemodels.TableModelSoferi();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSoferi = new javax.swing.JTable();
        btnAdauga = new javax.swing.JButton();
        btnEditeaza = new javax.swing.JButton();
        btnSterge = new javax.swing.JButton();
        rdbToate = new javax.swing.JRadioButton();
        rdbActiv = new javax.swing.JRadioButton();
        rdbInactiv = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblSoferi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume si prenume", "Poza"
            }
        ));
        tblSoferi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSoferiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSoferi);
        if (tblSoferi.getColumnModel().getColumnCount() > 0) {
            tblSoferi.getColumnModel().getColumn(1).setResizable(false);
            tblSoferi.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        btnAdauga.setText("Adauga");
        btnAdauga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdaugaActionPerformed(evt);
            }
        });

        btnEditeaza.setText("Editeaza");
        btnEditeaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditeazaActionPerformed(evt);
            }
        });

        btnSterge.setText("Sterge");
        btnSterge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStergeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbToate);
        rdbToate.setText("Toate");
        rdbToate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbToateActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbActiv);
        rdbActiv.setText("Activ");
        rdbActiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbActivActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbInactiv);
        rdbInactiv.setText("Inactiv");
        rdbInactiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbInactivActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdbToate)
                        .addGap(18, 18, 18)
                        .addComponent(rdbActiv)
                        .addGap(18, 18, 18)
                        .addComponent(rdbInactiv)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdauga, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnEditeaza, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnSterge, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdauga)
                    .addComponent(btnEditeaza)
                    .addComponent(btnSterge))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbToate)
                    .addComponent(rdbActiv)
                    .addComponent(rdbInactiv))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdaugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdaugaActionPerformed
        soferController.actionCreate(this);
    }//GEN-LAST:event_btnAdaugaActionPerformed

    private void btnEditeazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditeazaActionPerformed
        soferController.actionEdit(this);
    }//GEN-LAST:event_btnEditeazaActionPerformed

    private void btnStergeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStergeActionPerformed
        soferController.actionDelete(this);
    }//GEN-LAST:event_btnStergeActionPerformed

    private void rdbToateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbToateActionPerformed
        soferController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbToateActionPerformed

    private void rdbActivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbActivActionPerformed
        soferController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbActivActionPerformed

    private void rdbInactivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbInactivActionPerformed
        soferController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbInactivActionPerformed

    private void tblSoferiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSoferiMouseClicked
        soferController.itemSelected();
    }//GEN-LAST:event_tblSoferiMouseClicked

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
            java.util.logging.Logger.getLogger(FrmAdministrareSoferi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareSoferi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareSoferi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareSoferi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAdministrareSoferi dialog = new FrmAdministrareSoferi(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdauga;
    private javax.swing.JButton btnEditeaza;
    private javax.swing.JButton btnSterge;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbActiv;
    private javax.swing.JRadioButton rdbInactiv;
    private javax.swing.JRadioButton rdbToate;
    private tablemodels.TableModelSoferi tableModelSoferi1;
    private javax.swing.JTable tblSoferi;
    // End of variables declaration//GEN-END:variables
}
