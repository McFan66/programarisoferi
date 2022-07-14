/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.TirController;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Stefan
 */
public class FrmAdministrareTiruri extends javax.swing.JDialog {

    private TirController tirController;

    /**
     * Creates new form FrmAdministrareTiruri
     */
    public FrmAdministrareTiruri(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                MouseEvent eventMouse = (MouseEvent) event;
            //    System.out.println(event.getID() + " " + eventMouse.getID() + " " + MouseEvent.BUTTON3);

                if (eventMouse.getButton() == MouseEvent.BUTTON3) {
                    //MouseEvent eventMouse=(MouseEvent) event;
                    // int row=tblTiruri.rowAtPoint(eventMouse.getLocationOnScreen());
                    // if(row==-1)
                    tblTiruri.clearSelection();
                    ListSelectionModel selectionModel = tblTiruri.getSelectionModel();
                    selectionModel.setAnchorSelectionIndex(-1);
                    selectionModel.setLeadSelectionIndex(-1);

                    TableColumnModel columnModel = tblTiruri.getColumnModel();
                    columnModel.getSelectionModel().setAnchorSelectionIndex(-1);
                    columnModel.getSelectionModel().setLeadSelectionIndex(-1);
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);
    }

    public void setTirController(TirController tirController) {
        this.tirController = tirController;
    }

    public JTable getTblTiruri() {
        return tblTiruri;
    }

    public JRadioButton getRdbToate() {
        return rdbToate;
    }

    public JRadioButton getRdbActive() {
        return rdbActive;
    }

    public JRadioButton getRdbInactive() {
        return rdbInactive;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTiruri = new javax.swing.JTable();
        btnAdauga = new javax.swing.JButton();
        btnEditeaza = new javax.swing.JButton();
        btnSterge = new javax.swing.JButton();
        rdbToate = new javax.swing.JRadioButton();
        rdbActive = new javax.swing.JRadioButton();
        rdbInactive = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblTiruri.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTiruri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTiruriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTiruri);

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

        buttonGroup1.add(rdbActive);
        rdbActive.setText("Active");
        rdbActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbActiveActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbInactive);
        rdbInactive.setText("Inactive");
        rdbInactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbInactiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdauga, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnEditeaza, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSterge, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdbToate)
                        .addGap(18, 18, 18)
                        .addComponent(rdbActive)
                        .addGap(18, 18, 18)
                        .addComponent(rdbInactive)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdauga)
                    .addComponent(btnEditeaza)
                    .addComponent(btnSterge))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbToate)
                    .addComponent(rdbActive)
                    .addComponent(rdbInactive))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditeazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditeazaActionPerformed
        tirController.actionEdit(this);
    }//GEN-LAST:event_btnEditeazaActionPerformed

    private void btnAdaugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdaugaActionPerformed
        tirController.actionCreate(this);
    }//GEN-LAST:event_btnAdaugaActionPerformed

    private void btnStergeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStergeActionPerformed
       tirController.actionDelete();
    }//GEN-LAST:event_btnStergeActionPerformed

    private void rdbToateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbToateActionPerformed
        tirController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbToateActionPerformed

    private void rdbActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbActiveActionPerformed
        tirController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbActiveActionPerformed

    private void rdbInactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbInactiveActionPerformed
        tirController.updateAndSetModelToTable();
    }//GEN-LAST:event_rdbInactiveActionPerformed

    private void tblTiruriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTiruriMouseClicked
        tirController.itemSelected();
    }//GEN-LAST:event_tblTiruriMouseClicked

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
            java.util.logging.Logger.getLogger(FrmAdministrareTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrareTiruri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAdministrareTiruri dialog = new FrmAdministrareTiruri(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton rdbActive;
    private javax.swing.JRadioButton rdbInactive;
    private javax.swing.JRadioButton rdbToate;
    private javax.swing.JTable tblTiruri;
    // End of variables declaration//GEN-END:variables
}
