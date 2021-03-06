/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.ModelController;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import models.Marca;
import models.Model;
import services.MarcaService;
import services.MarcaServiceImpl;

/**
 *
 * @author Vlad
 */
public class FrmAddModel extends javax.swing.JDialog {

    /**
     * Creates new form FrmAddModel
     */
    
    private DefaultComboBoxModel modelMarci;
    private Marca selectatiMarca;
    private MarcaService marcaService = new MarcaServiceImpl();
    private OnModelSaved onModelSaved;
    private Model modelSelectat;
    
    private ModelController modelController = new ModelController();
    
    public FrmAddModel(java.awt.Frame parent, boolean modal, Model... modelSelectat) {
        super(parent, modal);
        initComponents();
        
//        this.modelMarci = new DefaultComboBoxModel();
//        this.selectatiMarca = new Marca(-1, "--Selectati Marca--", 1);
//        ArrayList<Marca> marci = marcaService.getAll();
//        
//        marci.add(0, selectatiMarca);
//        for(Marca m : marci) {
//            modelMarci.addElement(m);
//        }
//        
//        dropDownMarca.setModel(modelMarci);
//        
//        if(modelSelectat.length > 0) {
//            this.modelSelectat = modelSelectat[0];
//            txtNume.setText(this.modelSelectat.getNume());
//            dropDownMarca.setSelectedItem(this.modelSelectat.getMarca());
//        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNume = new javax.swing.JLabel();
        txtNume = new javax.swing.JTextField();
        dropDownMarca = new javax.swing.JComboBox<>();
        lblMarca = new javax.swing.JLabel();
        btnAdauga = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adauga Model");

        lblNume.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNume.setText("Nume:");

        dropDownMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dropDownMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dropDownMarcaItemStateChanged(evt);
            }
        });
        dropDownMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropDownMarcaActionPerformed(evt);
            }
        });

        lblMarca.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblMarca.setText("Marca:");

        btnAdauga.setText("Adauga");
        btnAdauga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdaugaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNume)
                    .addComponent(lblMarca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNume)
                    .addComponent(dropDownMarca, 0, 292, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAdauga))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNume))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dropDownMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdauga)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dropDownMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropDownMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dropDownMarcaActionPerformed

    private void dropDownMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dropDownMarcaItemStateChanged
//        if(evt.getStateChange() == ItemEvent.SELECTED) {
//            modelMarci.removeElementAt(0);
//        }
    }//GEN-LAST:event_dropDownMarcaItemStateChanged

    private void btnAdaugaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdaugaActionPerformed
        // TODO add your handling code here:
        modelController.saveModel();
//        Model m = new Model();
//        
//        if(modelSelectat != null) {
//            m.setId(modelSelectat.getId());
//        }
//        
//        m.setNume(txtNume.getText().trim());
//        m.setActiv(true);
//        m.setMarca((Marca) dropDownMarca.getSelectedItem());
//        
//        if(onModelSaved != null) {
//            onModelSaved.saveModel(m);
//        }
        
        this.dispose();
    }//GEN-LAST:event_btnAdaugaActionPerformed

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
    }

    public JTextField getTxtNume() {
        return txtNume;
    }

    public JComboBox<String> getDropDownMarca() {
        return dropDownMarca;
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
            java.util.logging.Logger.getLogger(FrmAddModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAddModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAddModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAddModel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAddModel dialog = new FrmAddModel(new javax.swing.JFrame(), true);
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

    public void setOnModelSaved(OnModelSaved onModelSaved) {
        this.onModelSaved = onModelSaved;
    }
  
    public interface OnModelSaved {
        void saveModel(Model model);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdauga;
    private javax.swing.JComboBox<String> dropDownMarca;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNume;
    private javax.swing.JTextField txtNume;
    // End of variables declaration//GEN-END:variables

}


