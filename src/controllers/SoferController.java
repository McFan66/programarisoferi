/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import administrare.CnpValidator;
import gui.FrmAddSofer;
import gui.FrmAdministrareSoferi;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import models.Sofer;
import renderers.CellRendererImage;
import services.SoferService;
import services.SoferServiceImpl;
import tablemodels.TableModelSoferi;
import utils.ProjectUtils;

/**
 *
 * @author Stefan
 */
public class SoferController {

    private FrmAddSofer frmAddSofer;
    private FrmAdministrareSoferi frmAdministrareSoferi;
    private File pozaSelectata;
    private SoferService soferService = new SoferServiceImpl();
    private ArrayList<Sofer> listaSoferi;
    private Sofer soferSelectat;

    private Object[][] data;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames = new String[]{"Nume Complet", "Poza"};

    public void actionCreate(JDialog parent) {
        frmAddSofer = new FrmAddSofer(parent, true);
        frmAddSofer.setSoferController(this);
        frmAddSofer.setLocationRelativeTo(parent);
        frmAddSofer.setVisible(true);
    }

    public void actionIndex(JFrame parent) {
        frmAdministrareSoferi = new FrmAdministrareSoferi(parent, true);
        this.soferSelectat = null;
        updateAndSetModelToTable();
        frmAdministrareSoferi.setSoferController(this);
        frmAdministrareSoferi.setLocationRelativeTo(parent);
        frmAdministrareSoferi.setVisible(true);
    }

    private void updateAndSetModelToTable() {
        JTable tblSoferi = frmAdministrareSoferi.getTblSoferi();
        tblSoferi.setRowHeight(70);
        listaSoferi = soferService.getAll();
        data = new Object[listaSoferi.size()][2];
        int x = 0;
        for (Sofer s : listaSoferi) {
            data[x][0] = s.getNumeComplet();

            try {
                data[x][1] = new ImageIcon(s.getPoza().getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(SoferController.class.getName()).log(Level.SEVERE, null, ex);
            }

            x++;
        }

        defaultTableModel = new DefaultTableModel(data, columnNames);
        tblSoferi.setModel(defaultTableModel);
        tblSoferi.getColumnModel().getColumn(1).setCellRenderer(new CellRendererImage());
        ProjectUtils.tableColumnAdjusterByHeader(tblSoferi);
    }

    public void actionEdit(JDialog parent) {
        JTable tblSoferi = frmAdministrareSoferi.getTblSoferi();
        int index = tblSoferi.convertRowIndexToModel(tblSoferi.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareSoferi, "Va rugam selectati un sofer.");
            return;
        }
        listaSoferi = soferService.getAll();
        Sofer s = listaSoferi.get(index);
        frmAddSofer = new FrmAddSofer(parent, true, s);
        this.soferSelectat = s;
        frmAddSofer.getTxtNume().setText(s.getNume());
        frmAddSofer.getTxtPrenume().setText(s.getPrenume());
        frmAddSofer.getTxtCNP().setText(s.getCnp());
        //       File pozaSofer = s.getPoza();
        //     frmAddSofer.getLblPoza().setIcon(new ImageIcon(new ImageIcon(pozaSofer.getAbsolutePath()).getImage().getScaledInstance(frmAddSofer.getLblPoza().getWidth(), frmAddSofer.getLblPoza().getHeight(), Image.SCALE_SMOOTH)));
        frmAddSofer.setSoferController(this);
        frmAddSofer.setLocationRelativeTo(parent);
        frmAddSofer.setVisible(true);
        updateAndSetModelToTable();
        frmAddSofer.dispose();
    }

    public void actionDelete(JDialog parent) {
        JTable tblSoferi = frmAdministrareSoferi.getTblSoferi();
        int index = tblSoferi.convertRowIndexToModel(tblSoferi.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareSoferi, "Va rugam selectati un sofer.");
            return;
        }
        listaSoferi = soferService.getAll();
        Sofer s = listaSoferi.get(index);
        int raspuns = JOptionPane.showConfirmDialog(frmAdministrareSoferi, String.format("Sunteti sigur ca doriti sa stergeti soferul %s?", s.getNumeComplet()), "Stergere sofer", JOptionPane.YES_NO_OPTION);
        if (raspuns == JOptionPane.YES_OPTION) {
            soferService.stergeSofer(s);
            updateAndSetModelToTable();
        }
    }

    public void saveSofer() {
        if (isFormValid()) {
            String cnp = frmAddSofer.getTxtCNP().getText();
            String nume = frmAddSofer.getTxtNume().getText();
            String prenume = frmAddSofer.getTxtPrenume().getText();

            File dirCur = new File(".");
            File folder = new File(dirCur, "poze");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File pozeSofer = new File(folder, "soferi");
            if (!pozeSofer.exists()) {
                pozeSofer.mkdir();
            }
            UUID uuidPoza = UUID.randomUUID();

            String extension = pozaSelectata.getName().substring(pozaSelectata.getName().lastIndexOf("."));

            File poza = new File(pozeSofer, String.format("%s%s", uuidPoza.toString(), extension));
            try {
                copyFile(pozaSelectata, poza);
            } catch (IOException ex) {
                Logger.getLogger(FrmAddSofer.class.getName()).log(Level.SEVERE, null, ex);
            }
            Sofer sofer = new Sofer();
            sofer.setCnp(cnp.trim());
            sofer.setNume(nume.trim());
            sofer.setPrenume(prenume.trim());
            sofer.setPoza(poza);
            soferService.salveazaSofer(sofer);
            JOptionPane.showMessageDialog(frmAddSofer, "Soferul a fost salvat cu succes!");
            updateAndSetModelToTable();
            frmAddSofer.dispose();
        }
    }

    public void selecteazaImaginea() {
        JFileChooser fileChooserPoza = frmAddSofer.getFileChooserPoza();
        JLabel lblPoza = frmAddSofer.getLblPoza();
        int returnVal = fileChooserPoza.showOpenDialog(frmAddSofer);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooserPoza.getSelectedFile();
            this.pozaSelectata = file;
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoza.getWidth(), lblPoza.getHeight(), Image.SCALE_SMOOTH));
            lblPoza.setIcon(imageIcon);
        } else {
            System.out.println("File access cancelled by user");
        }
    }

    private boolean isFormValid() {
        JTextField txtCNP = frmAddSofer.getTxtCNP();
        JTextField txtNume = frmAddSofer.getTxtNume();
        JTextField txtPrenume = frmAddSofer.getTxtPrenume();

        CnpValidator c = new CnpValidator();
        if (pozaSelectata == null) {
            JOptionPane.showMessageDialog(frmAddSofer, "Va rugam sa adaugati o poza.");
            return false;
        }
        if (!c.validate(txtCNP.getText())) {
            JOptionPane.showMessageDialog(frmAddSofer, "CNP incorect.");
            return false;
        }
        if (txtNume.getText().trim().length() < 2 || txtPrenume.getText().trim().length() < 2) {
            JOptionPane.showMessageDialog(frmAddSofer, "Numele si prenumele trebuie sa aiba minim 2 caractere.");
            return false;
        }
        return true;
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

}
