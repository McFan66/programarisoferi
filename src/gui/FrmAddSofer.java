/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.SoferController;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import models.Sofer;

public class FrmAddSofer extends javax.swing.JDialog {

    /**
     * Creates new form FrmDateSofer
     */
    private File pozaSelectata = null;
    private OnSoferSaved onSoferSaved;
    private Sofer soferSelectat;
    private SoferController soferController = new SoferController();

    public FrmAddSofer(JDialog parent, boolean modal, Sofer... soferSelectat) {
        super(parent, modal);
        initComponents();
//        if (soferSelectat.length > 0) {
//            this.soferSelectat = soferSelectat[0];
//            txtNume.setText(this.soferSelectat.getNume());
//            txtPrenume.setText(this.soferSelectat.getPrenume());
//            txtCNP.setText(this.soferSelectat.getCnp());
//            this.pozaSelectata = this.soferSelectat.getPoza();
//            lblPoza.setIcon(new ImageIcon(new ImageIcon(this.soferSelectat.getPoza().getAbsolutePath()).getImage().getScaledInstance(lblPoza.getWidth(), lblPoza.getHeight(), Image.SCALE_SMOOTH)));
//
//        }
    }

    public CustomTextField getTxtNume() {
        return txtNume;
    }

    public CustomTextField getTxtPrenume() {
        return txtPrenume;
    }

    public JTextField getTxtCNP() {
        return txtCNP;
    }

    public void setSoferController(SoferController soferController) {
        this.soferController = soferController;
    }

    public JFileChooser getFileChooserPoza() {
        return fileChooserPoza;
    }

    public JLabel getLblPoza() {
        return lblPoza;
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooserPoza = new javax.swing.JFileChooser();
        panelPrincipal = new javax.swing.JPanel();
        lblNume = new javax.swing.JLabel();
        lblPrenume = new javax.swing.JLabel();
        lblCNP = new javax.swing.JLabel();
        btnSalveaza = new javax.swing.JButton();
        lblPoza = new javax.swing.JLabel();
        txtNume = new gui.CustomTextField();
        txtPrenume = new gui.CustomTextField();
        txtCNP = new javax.swing.JTextField();

        fileChooserPoza.setAcceptAllFileFilterUsed(false);
        fileChooserPoza.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Date sofer");

        lblNume.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNume.setText("Nume:");

        lblPrenume.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPrenume.setText("Prenume:");

        lblCNP.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCNP.setText("CNP:");

        btnSalveaza.setText("Salveaza");
        btnSalveaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalveazaActionPerformed(evt);
            }
        });

        lblPoza.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPoza.setText("<html>Click aici<br>pentru poza</html>");
        lblPoza.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        lblPoza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPozaMouseClicked(evt);
            }
        });

        txtCNP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCNPKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrenume)
                    .addComponent(lblCNP, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNume, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrenume, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(txtCNP))
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalveaza, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPoza, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNume))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrenume)
                            .addComponent(txtPrenume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCNP)
                            .addComponent(txtCNP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblPoza, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(btnSalveaza)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtNume.getAccessibleContext().setAccessibleName("");
        txtNume.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblPozaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPozaMouseClicked
        // TODO add your handling code here:
    soferController.selecteazaImaginea();
//        int returnVal = fileChooserPoza.showOpenDialog(this);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooserPoza.getSelectedFile();
//            this.pozaSelectata = file;
//            ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoza.getWidth(), lblPoza.getHeight(), Image.SCALE_SMOOTH));
//
//            lblPoza.setIcon(imageIcon);
//
//        } else {
//            System.out.println("File access cancelled by user");
//        }
    }//GEN-LAST:event_lblPozaMouseClicked

    private void btnSalveazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalveazaActionPerformed
        // TODO add your handling code here:
        soferController.saveSofer();
//        try {
//            CnpValidator c = new CnpValidator();
//            if(pozaSelectata == null) {
//                throw new Error("Va rugam sa adaugati o poza");
//            }
//            if(!c.validate(txtCNP.getText())) {
//                throw new Error("CNP incorect");
//            }
//            if(txtNume.getText().trim().length() < 2 || txtPrenume.getText().trim().length() < 2) {
//                throw new Error("Numele si prenumele trebuie sa aiba minim 2 caractere");
//            }
//            File dirCur = new File(".");
//            File folder = new File(dirCur, "poze");
//            if (!folder.exists()) {
//                folder.mkdir();
//            }
//
//            File pozeSofer = new File(folder, "soferi");
//            if (!pozeSofer.exists()) {
//                pozeSofer.mkdir();
//            }
//
//            UUID uuidPoza = UUID.randomUUID();
//
//            String extension = pozaSelectata.getName().substring(pozaSelectata.getName().lastIndexOf("."));
//
//            File poza = new File(pozeSofer, String.format("%s%s", uuidPoza.toString(), extension));
//
//            try {
//                copyFile(pozaSelectata, poza);
//            } catch (IOException ex) {
//                Logger.getLogger(FrmDateSofer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            Sofer sofer = new Sofer();
//            sofer.setId((int) (Math.random() * 10000));
//            if(soferSelectat!=null){
//                sofer.setId(soferSelectat.getId());
//            }
//            sofer.setNume(txtNume.getText().trim());
//            sofer.setPrenume(txtPrenume.getText().trim());
//            sofer.setCnp(txtCNP.getText().trim());
//            sofer.setPoza(poza);
//            if (onSoferSaved != null) {
//                onSoferSaved.saveSofer(sofer);
//            }
//            this.dispose();
//        } catch (Error err) {
//            JOptionPane.showMessageDialog(this, err.getMessage());
//        }
    }//GEN-LAST:event_btnSalveazaActionPerformed

    private void txtCNPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCNPKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCNPKeyTyped

//    public static void copyFile(File sourceFile, File destFile) throws IOException {
//        if (!destFile.exists()) {
//            destFile.createNewFile();
//        }
//
//        FileChannel source = null;
//        FileChannel destination = null;
//
//        try {
//            source = new FileInputStream(sourceFile).getChannel();
//            destination = new FileOutputStream(destFile).getChannel();
//            destination.transferFrom(source, 0, source.size());
//        } finally {
//            if (source != null) {
//                source.close();
//            }
//            if (destination != null) {
//                destination.close();
//            }
//        }
//    }

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
            java.util.logging.Logger.getLogger(FrmAddSofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAddSofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAddSofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAddSofer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAddSofer dialog = new FrmAddSofer(new JDialog(), true);
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

    public void setOnSoferSaved(OnSoferSaved onSoferSaved) {
        this.onSoferSaved = onSoferSaved;
    }

    public interface OnSoferSaved {
        void saveSofer(Sofer sofer);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalveaza;
    private javax.swing.JFileChooser fileChooserPoza;
    private javax.swing.JLabel lblCNP;
    private javax.swing.JLabel lblNume;
    private javax.swing.JLabel lblPoza;
    private javax.swing.JLabel lblPrenume;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtCNP;
    private gui.CustomTextField txtNume;
    private gui.CustomTextField txtPrenume;
    // End of variables declaration//GEN-END:variables
}
