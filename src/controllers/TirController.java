/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddTir;
import static gui.FrmAddTir.copyFile;
import gui.FrmAdministrareTiruri;
import gui.FrmAfisareDetaliiTir;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.AppSingleTone;
import models.Marca;
import models.Model;
import models.Poza;
import models.Sofer;
import models.SoferiTiruri;
import models.Stare;
import models.Tir;
import org.hibernate.Session;
import renderers.ItemMarcaRenderer;
import renderers.ItemModelRenderer;
import renderers.ItemSoferRenderer;
import renderers.TiruriColorCellRenderer;
import services.MarcaService;
import services.ModelService;
import services.PozaService;
import services.SoferiTiruriService;
import services.StareService;
import services.TiruriService;
import tablemodel.ColumnResizer1;
import utils.HibernateUtil;
import utils.ProjectUtils;

/**
 *
 * @author Stefan
 */
public class TirController {

    private FrmAddTir frmAddTir;
    private FrmAdministrareTiruri frmAdministrareTiruri;
    private FrmAfisareDetaliiTir frmAfisareDetaliiTir;
    private ArrayList<File> listaFisiere = new ArrayList<>();
    private ArrayList<ImageIcon> listaPoze = new ArrayList<>();
    private int curImageIndex;
    private int indexPozaCurenta;
    private ArrayList<File> listaPozeDeAfisat = new ArrayList<>();
    private TiruriService tiruriService = AppSingleTone.getAppSingleToneInstance().getTiruriService();
    private MarcaService marcaService = AppSingleTone.getAppSingleToneInstance().getMarcaService();
    private ModelService modelService = AppSingleTone.getAppSingleToneInstance().getModelService();
    private PozaService pozaService = AppSingleTone.getAppSingleToneInstance().getPozaService();
    private SoferiTiruriService soferiTiruriService = AppSingleTone.getAppSingleToneInstance().getSoferiTiruriService();
    private StareService stareService = AppSingleTone.getAppSingleToneInstance().getStareService();
    private DefaultComboBoxModel<Marca> modelCmbMarci = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Model> modelCmbModele = new DefaultComboBoxModel<>();
    private DefaultListModel<Sofer> modelListaSoferi = new DefaultListModel<>();
    private Tir tirSelectat;
    private JComboBox cmbMarca;
    private JComboBox cmbModel;
    private ArrayList<Tir> listaTiruri;
    private File pozaCreeata;
    private ArrayList<Poza> pozeTir;

    private JTable tblTiruri;
    private Object[][] data;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames = new String[]{"Marca", "Model", "Nr Inmatriculare", "Poza", "Status"};

    public void actionCreate(JDialog parent) {
        this.tirSelectat = null;
        frmAddTir = new FrmAddTir(parent, true);
        frmAddTir.setTitle("Adauga tir");
        cmbMarca = frmAddTir.getCmbMarca();
        cmbModel = frmAddTir.getCmbModel();
        cmbMarca.setRenderer(new ItemMarcaRenderer());
        cmbModel.setRenderer(new ItemModelRenderer());
        ArrayList<Marca> listaMarci = marcaService.getAll();
        modelCmbMarci.removeAllElements();
        Marca marcaTest = new Marca();
        marcaTest.setNume("--Selectati marca--");
        modelCmbMarci.addElement(marcaTest);
        for (Marca m : listaMarci) {
            modelCmbMarci.addElement(m);
        }
        listaFisiere.clear();
        listaPoze.clear();
        cmbMarca.setModel(modelCmbMarci);
        cmbModel.setModel(modelCmbModele);
        frmAddTir.setTirController(this);
        frmAddTir.setLocationRelativeTo(parent);
        frmAddTir.setVisible(true);
    }

    public void actionEdit(JDialog parent) {
        int index = tblTiruri.convertRowIndexToModel(tblTiruri.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareTiruri, "Va rugam sa selectati un tir.");
            return;
        }
        if (frmAdministrareTiruri.getRdbToate().isSelected()) {
            listaTiruri = tiruriService.getAll();
        } else if (frmAdministrareTiruri.getRdbActive().isSelected()) {
            listaTiruri = tiruriService.getTirByValid(true);
        } else if (frmAdministrareTiruri.getRdbInactive().isSelected()) {
            listaTiruri = tiruriService.getTirByValid(false);
        }
        Tir t = listaTiruri.get(index);
        this.tirSelectat = t;
        frmAddTir = new FrmAddTir(parent, true, tirSelectat);
        frmAddTir.setTitle("Editeaza tir");
        frmAddTir.setTirController(this);
        cmbMarca = frmAddTir.getCmbMarca();
        cmbMarca.setRenderer(new ItemMarcaRenderer());
        cmbModel = frmAddTir.getCmbModel();
        cmbModel.setRenderer(new ItemModelRenderer());
        ArrayList<Marca> listaMarci = marcaService.getAll();
        modelCmbMarci.removeAllElements();
        Marca marcaTest = new Marca();
        marcaTest.setNume("--Selectati marca--");
        modelCmbMarci.addElement(marcaTest);
        for (Marca m : listaMarci) {
            modelCmbMarci.addElement(m);
        }
        listaFisiere.clear();
        listaPoze.clear();
        cmbMarca.setModel(modelCmbMarci);
        cmbModel.setModel(modelCmbModele);
        frmAddTir.getCmbMarca().setSelectedItem(tirSelectat.getModel().getMarca());
        frmAddTir.getCmbModel().setSelectedItem(tirSelectat.getModel());
        frmAddTir.getTxtNrInmatriculare().setText(tirSelectat.getNrInmatriculare());

        curImageIndex = 0;
        pozeTir = pozaService.getPozaByTipAndObiect(1, tirSelectat.getId());
        if (pozeTir.size() == 0) {
            frmAddTir.getLblCnt().setText("0/" + pozeTir.size());
        } else {
            frmAddTir.getLblCnt().setText("1/" + pozeTir.size());
        }

        JLabel lblPoze = frmAddTir.getLblPoze();
        lblPoze.setMinimumSize(new Dimension(lblPoze.getWidth(), lblPoze.getHeight()));
        lblPoze.setPreferredSize(new Dimension(lblPoze.getWidth(), lblPoze.getHeight()));
        lblPoze.setMaximumSize(new Dimension(lblPoze.getWidth(), lblPoze.getHeight()));
        File currentDirectory = new File(".");
        int c = 0;
        for (Poza p : pozeTir) {

            File file = new File(currentDirectory + "/poze/tiruri/" + tirSelectat.getNrInmatriculare() + "/" + pozeTir.get(c).getImagePath());

            listaFisiere.add(file);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoze.getWidth(), lblPoze.getHeight(), Image.SCALE_SMOOTH));

            listaPoze.add(imageIcon);
            c++;
        }
        File file = new File(currentDirectory + "/poze/tiruri/" + tirSelectat.getNrInmatriculare() + "/" + pozeTir.get(0).getImagePath());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoze.getWidth(), lblPoze.getHeight(), Image.SCALE_SMOOTH));
        lblPoze.setIcon(imageIcon);
        lblPoze.setText(null);

        frmAddTir.setLocationRelativeTo(parent);
        frmAddTir.setVisible(true);
    }

    public void anterioaraImagine() {
        JLabel lblPoze = frmAddTir.getLblPoze();

        if (curImageIndex > 0 && curImageIndex < listaPoze.size()) {
            curImageIndex--;
            lblPoze.setIcon(listaPoze.get(curImageIndex));
            modificaCntPoze();
        }
    }

    public void urmatoareaImagine() {
        JLabel lblPoze = frmAddTir.getLblPoze();

        if (curImageIndex >= 0 && curImageIndex < listaPoze.size() - 1) {
            curImageIndex++;
            lblPoze.setIcon(listaPoze.get(curImageIndex));
            modificaCntPoze();
        }
    }

    public void actionDelete() {
        int index = tblTiruri.convertRowIndexToModel(tblTiruri.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareTiruri, "Va rugam sa selectati un tir.");
            return;
        }
        if (frmAdministrareTiruri.getRdbToate().isSelected()) {
            listaTiruri = tiruriService.getAll();
        } else if (frmAdministrareTiruri.getRdbActive().isSelected()) {
            listaTiruri = tiruriService.getTirByValid(true);
        } else if (frmAdministrareTiruri.getRdbInactive().isSelected()) {
            listaTiruri = tiruriService.getTirByValid(false);
        }
        Tir t = listaTiruri.get(index);
        int raspuns = JOptionPane.showConfirmDialog(frmAdministrareTiruri, "Sunteti sigur ca vreti sa stergeti tirul selectat?", "Stergere tir", JOptionPane.YES_NO_OPTION);
//        if (raspuns == JOptionPane.YES_OPTION) {
//            ArrayList<Poza> pozeDeSters = pozaService.getPozaByTipAndObiect(1, t.getId());
//            if (pozeDeSters.isEmpty()) {
//                tiruriService.stergeTir(t);
//                updateAndSetModelToTable();
//                return;
//            }
//            tiruriService.stergeTir(t);
//            for (Poza pozaDeSters : pozeDeSters) {
//                pozaService.stergePoza(pozaDeSters);
//            }
//        }
        if (raspuns == JOptionPane.YES_OPTION) {
            if (t.isValid()) {
                t.setValid(false);
                tiruriService.adaugaTir(t);
                updateAndSetModelToTable();
            } else if (!t.isValid()) {
                t.setValid(true);
                tiruriService.adaugaTir(t);
                updateAndSetModelToTable();
            }
        }
    }

    public void savePoza(Tir tir) {
        File tiruri = makeFolderPoze();
        File pozeTir = new File(tiruri, frmAddTir.getTxtNrInmatriculare().getText());
        if (!pozeTir.exists()) {
            pozeTir.mkdir();
        }

        ArrayList<File> listaFisiereNoua = new ArrayList<>();
        System.out.println(listaFisiere.size());
        for (File pozaDeSalvat : listaFisiere) {
            makeFile(pozaDeSalvat, pozeTir);
            listaFisiereNoua.add(pozaCreeata);
        }
        for (File pozaDeSalvat : listaFisiereNoua) {
            Poza p = new Poza();
            p.setTipObiect(1);
            p.setIdObiect(tir.getId());
            p.setImagePath(pozaDeSalvat.getName());
            pozaService.adaugaPoza(p);
        }
    }

    public void saveTir() {
        if (isFormValid()) {
            if (tirSelectat == null) {
                tirSelectat = new Tir();
                Model m = (Model) cmbModel.getSelectedItem();
                tirSelectat.setModel(m);
                tirSelectat.setIdModel(m.getId());
                tirSelectat.setNrInmatriculare(frmAddTir.getTxtNrInmatriculare().getText());
                tirSelectat.setIdStare(stareService.getStareByNume("Disponibil").getId());
                tirSelectat.setStare(stareService.getStareByNume("Disponibil"));
                tirSelectat.setValid(true);
                tiruriService.adaugaTir(tirSelectat);
                savePoza(tirSelectat);
            } else {
                Model m = (Model) cmbModel.getSelectedItem();
                tirSelectat.setIdModel(m.getId());
                tirSelectat.setModel(m);
                tirSelectat.setNrInmatriculare(frmAddTir.getTxtNrInmatriculare().getText());
                tirSelectat.setIdStare(tirSelectat.getIdStare());
                tirSelectat.setStare(stareService.getStareByNume("Disponibil"));
                ArrayList<Poza> pozeDeSters = pozaService.getPozaByTipAndObiect(1, tirSelectat.getId());
                for (Poza pozaDeSters : pozeDeSters) {
                    pozaService.stergePoza(pozaDeSters);
                }
                tiruriService.adaugaTir(tirSelectat);
                savePoza(tirSelectat);

            }

            JOptionPane.showMessageDialog(frmAddTir, "Tirul a fost salvat cu succes.");
            frmAddTir.dispose();
            updateAndSetModelToTable();
        }
    }

    public void actionIndex(java.awt.Frame parent) {
        frmAdministrareTiruri = new FrmAdministrareTiruri(parent, true);
        frmAdministrareTiruri.setTitle("Administrare tiruri");
        frmAdministrareTiruri.getRdbToate().setSelected(true);
        updateAndSetModelToTable();
        tblTiruri.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    afisareDetaliiTir();
                }
            }
        });
        frmAdministrareTiruri.setTirController(this);
        frmAdministrareTiruri.setLocationRelativeTo(parent);
        frmAdministrareTiruri.setVisible(true);
    }

    public void itemSelected() {
        int index = tblTiruri.convertRowIndexToModel(tblTiruri.getSelectedRow());
        listaTiruri = tiruriService.getAll();
        Tir t = listaTiruri.get(index);
        if (t.isValid()) {
            frmAdministrareTiruri.getBtnSterge().setText("Dezactiveaza");
        } else if (!t.isValid()) {
            frmAdministrareTiruri.getBtnSterge().setText("Activeaza");
        }
    }

    public void updateAndSetModelToTable() {
        tblTiruri = frmAdministrareTiruri.getTblTiruri();
        if (frmAdministrareTiruri.getRdbToate().isSelected()) {
            listaTiruri = tiruriService.getAll();
            frmAdministrareTiruri.getBtnSterge().setText("Dezactiveaza");
        } else if (frmAdministrareTiruri.getRdbActive().isSelected()) {
            listaTiruri = tiruriService.getTirByValid(true);
            frmAdministrareTiruri.getBtnSterge().setText("Dezactiveaza");
        } else if (frmAdministrareTiruri.getRdbInactive().isSelected()) {
            listaTiruri = tiruriService.getTirByValid(false);
            frmAdministrareTiruri.getBtnSterge().setText("Activeaza");
        }
        data = new Object[listaTiruri.size()][5];
        int x = 0;
        for (Tir t : listaTiruri) {
            data[x][0] = t.getModel().getMarca().getNume();
            data[x][1] = t.getModel().getNume();
            Pattern p = Pattern.compile("[A-Z]+|\\d+");
            Matcher m = p.matcher(t.getNrInmatriculare());
            ArrayList<String> allMatches = new ArrayList<>();
            while (m.find()) {
                allMatches.add(m.group());
            }
            data[x][2] = String.format("%s %s %s", allMatches.get(0), allMatches.get(1), allMatches.get(2));
            switch (t.getStare().getNume()) {
                case "Stare test":
                    data[x][4] = "Tir test";
                    break;
                case "Disponibil":
                    data[x][4] = "Disponibil";
                    break;
                case "Cursa":
                    data[x][4] = "In cursa";
                    break;
                case "Service":
                    data[x][4] = "In service";
                    break;
            }
            x++;
        }
        defaultTableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblTiruri.setModel(defaultTableModel);
        tblTiruri.getColumnModel().getColumn(4).setCellRenderer(new TiruriColorCellRenderer());
        ColumnResizer1.resizeRowHeightAndColumnsWidth(tblTiruri);
        ProjectUtils.tableColumnAdjusterByHeader(tblTiruri);
    }

    public void marcaHasChanged(java.awt.event.ItemEvent event) {
        System.out.println("intru");
        if (event.getStateChange() == ItemEvent.SELECTED) {
            modelCmbModele.removeAllElements();
            if (cmbMarca.getSelectedIndex() > 0) {
                Model modelTest = new Model();
                modelTest.setNume("--Selectati modelul--");
                System.out.println("din nou");
                modelCmbModele.addElement(modelTest);
                ArrayList<Model> listaModele = modelService.getModeleByMarca((Marca) cmbMarca.getSelectedItem());
                for (Model m : listaModele) {
                    modelCmbModele.addElement(m);
                }
                return;
            }
            cmbModel.removeAllItems();
        }
    }

    private void afisareDetaliiTir() {
        tirSelectat = listaTiruri.get(tblTiruri.getSelectedRow());
        frmAfisareDetaliiTir = new FrmAfisareDetaliiTir(frmAdministrareTiruri, true);
        frmAfisareDetaliiTir.setTitle("Detalii tir");
        frmAfisareDetaliiTir.getLblMarca().setText(tirSelectat.getModel().getMarca().getNume());
        frmAfisareDetaliiTir.getLblModel().setText(tirSelectat.getModel().getNume());
        Pattern p = Pattern.compile("[A-Z]+|\\d+");
        Matcher m = p.matcher(tirSelectat.getNrInmatriculare());
        ArrayList<String> allMatches = new ArrayList<>();
        while (m.find()) {
            allMatches.add(m.group());
        }
        switch (tirSelectat.getStare().getNume()) {
            case "Stare test":
                frmAfisareDetaliiTir.getLblStatus().setText("Tir test");
                frmAfisareDetaliiTir.getLblStatus().setForeground(Color.BLUE);
                break;
            case "Disponibil":
                frmAfisareDetaliiTir.getLblStatus().setText("Disponibil");
                Color darkGreen = new Color(0, 153, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkGreen);
                break;
            case "Cursa":
                frmAfisareDetaliiTir.getLblStatus().setText("In cursa");
                frmAfisareDetaliiTir.getLblStatus().setForeground(Color.RED);
                frmAfisareDetaliiTir.getBtnModService().setEnabled(false);
                break;
            case "Service":
                frmAfisareDetaliiTir.getLblStatus().setText("In service");
                Color darkOrange = new Color(255, 143, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkOrange);
                break;
        }
        frmAfisareDetaliiTir.getLblNrInmatriculare().setText(String.format("%s %s %s", allMatches.get(0), allMatches.get(1), allMatches.get(2)));
        ArrayList<SoferiTiruri> listaSoferiTiruri = soferiTiruriService.getSoferiTiruriByTir(tirSelectat);
        Set<Sofer> uniqueSofer = new HashSet<>(0);
        for (SoferiTiruri st : listaSoferiTiruri) {
            uniqueSofer.add(st.getSofer());
        }
        for (Sofer s : uniqueSofer) {
            modelListaSoferi.addElement(s);
        }
        frmAfisareDetaliiTir.getLstSoferi().setCellRenderer(new ItemSoferRenderer());
        frmAfisareDetaliiTir.getLstSoferi().setModel(modelListaSoferi);
        indexPozaCurenta = 0;
        pozeTir = pozaService.getPozaByTipAndObiect(1, tirSelectat.getId());
        if (pozeTir.size() == 0) {
            frmAfisareDetaliiTir.getLblCnt().setText("0 / " + pozeTir.size());
        } else {
            frmAfisareDetaliiTir.getLblCnt().setText("1 / " + pozeTir.size());
        }

        JLabel lblPoze = frmAfisareDetaliiTir.getLblPoze();
        lblPoze.setMinimumSize(new Dimension(lblPoze.getWidth(), lblPoze.getHeight()));
        lblPoze.setPreferredSize(new Dimension(lblPoze.getWidth(), lblPoze.getHeight()));
        lblPoze.setMaximumSize(new Dimension(lblPoze.getWidth(), lblPoze.getHeight()));
        File currentDirectory = new File(".");
        File file = new File(currentDirectory + "/poze/tiruri/" + tirSelectat.getNrInmatriculare() + "/" + pozeTir.get(0).getImagePath());
        lblPoze.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoze.getWidth(), lblPoze.getHeight(), Image.SCALE_SMOOTH)));

        frmAfisareDetaliiTir.setTirController(this);
        frmAfisareDetaliiTir.setLocationRelativeTo(frmAdministrareTiruri);
        frmAfisareDetaliiTir.setVisible(true);
    }

    public void nextImageDetalii() {
        JLabel lblPoze = frmAfisareDetaliiTir.getLblPoze();
        if (indexPozaCurenta >= 0 && indexPozaCurenta < pozeTir.size() - 1) {
            indexPozaCurenta++;
            File currentDirectory = new File(".");
            File file = new File(currentDirectory + "/poze/tiruri/" + tirSelectat.getNrInmatriculare() + "/" + pozeTir.get(indexPozaCurenta).getImagePath());
            lblPoze.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoze.getWidth(), lblPoze.getHeight(), Image.SCALE_SMOOTH)));
            frmAfisareDetaliiTir.getLblCnt().setText(indexPozaCurenta + 1 + " / " + pozeTir.size());
        }
    }

    public void previousImageDetalii() {
        JLabel lblPoze = frmAfisareDetaliiTir.getLblPoze();
        if (indexPozaCurenta > 0 && indexPozaCurenta < pozeTir.size()) {
            indexPozaCurenta--;
            File currentDirectory = new File(".");
            File file = new File(currentDirectory + "/poze/tiruri/" + tirSelectat.getNrInmatriculare() + "/" + pozeTir.get(indexPozaCurenta).getImagePath());
            lblPoze.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoze.getWidth(), lblPoze.getHeight(), Image.SCALE_SMOOTH)));
            frmAfisareDetaliiTir.getLblCnt().setText(indexPozaCurenta + 1 + " / " + pozeTir.size());
        }
    }

    public void modService() {
        System.out.println(tirSelectat.getStare().getNume());
        if (tirSelectat.getStare().equals(stareService.getStareByNume("Disponibil"))) {
            int raspuns = JOptionPane.showConfirmDialog(frmAfisareDetaliiTir, "Sunteti sigur ca doriti sa plasati acest tir in service?", "Plasare tir in service", JOptionPane.YES_NO_OPTION);
            if (raspuns == JOptionPane.YES_OPTION) {
                tirSelectat.setIdStare(stareService.getStareByNume("Service").getId());
                Stare st = new Stare();
                st.setId(4);
                tirSelectat.setStare(st);
                tiruriService.adaugaTir(tirSelectat);
                frmAfisareDetaliiTir.getLblStatus().setText("In service");
                Color darkOrange = new Color(255, 143, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkOrange);
            }
        } else if (tirSelectat.getStare().equals(stareService.getStareByNume("Service"))) {
            int raspuns = JOptionPane.showConfirmDialog(frmAfisareDetaliiTir, "Sunteti sigur ca doriti sa eliminati acest tir din service?", "Eliminare tir din service", JOptionPane.YES_NO_OPTION);
            if (raspuns == JOptionPane.YES_OPTION) {
                tirSelectat.setIdStare(stareService.getStareByNume("Disponibil").getId());
                Stare st = new Stare();
                st.setId(2);
                tirSelectat.setStare(st);
                tiruriService.adaugaTir(tirSelectat);
                frmAfisareDetaliiTir.getLblStatus().setText("Disponibil");
                Color darkGreen = new Color(0, 153, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkGreen);
            }
        }
        updateAndSetModelToTable();
    }

    public void stergeImagine() {
        JLabel lblPoze = frmAddTir.getLblPoze();

        if (listaPoze.isEmpty()) {
            throw new Error("Nu aveti poze adaugate");
        }
        if (listaPoze.size() == 1) {
            listaPoze.remove(0);
            lblPoze.setIcon(null);
            listaFisiere.remove(0);
            curImageIndex--;
            modificaCntPoze();
        }
        if (listaPoze.size() > 1) {
            listaPoze.remove(curImageIndex);
            listaFisiere.remove(curImageIndex);
            curImageIndex--;
            lblPoze.setIcon(listaPoze.get(curImageIndex));
            modificaCntPoze();
        }
    }

    public void selecteazaImagini() {
        JFileChooser fileChooserPozeTir = frmAddTir.getFileChooserPozeMasina();
        JLabel lblPoze = frmAddTir.getLblPoze();

        int returnVal = fileChooserPozeTir.showOpenDialog(frmAddTir);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooserPozeTir.getSelectedFile();
            listaFisiere.add(file);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(lblPoze.getWidth(), lblPoze.getHeight(), Image.SCALE_SMOOTH));
            listaPoze.add(imageIcon);
            lblPoze.setIcon(imageIcon);
            lblPoze.setText(null);
            curImageIndex = listaPoze.indexOf(imageIcon);
            System.out.println("aici avem:" + curImageIndex);
            modificaCntPoze();
        } else {
            System.out.println("File access cancelled by user");
        }
    }

    private boolean isFormValid() {
        JTextField txtNrInmatriculare = frmAddTir.getTxtNrInmatriculare();

        if (cmbMarca.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(frmAddTir, "Va rog sa selectati o marca.");
            return false;
        }
        if (cmbModel.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(frmAddTir, "Va rog sa selectati un model.");
            return false;
        }
        if (txtNrInmatriculare.getText() == null) {
            JOptionPane.showConfirmDialog(frmAddTir, "Va rog sa introduceti numarul de inmatriculare.");
            return false;
        }
        if (listaPoze.isEmpty()) {
            JOptionPane.showConfirmDialog(frmAddTir, "Va rog sa adaugati macar o poza.");
            return false;
        }
        return true;
    }

    private void modificaCntPoze() {
        JLabel lblCnt = frmAddTir.getLblCnt();
        //  System.out.println("aici:" + curImageIndex + " " + listaPoze.size());
        lblCnt.setText(String.format("%s/%s", curImageIndex + 1, listaPoze.size()));
    }

    private File makeFolderPoze() {
        File dirCur = new File(".");
        File folder = new File(dirCur, "poze");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File tiruri = new File(folder, "tiruri");
        if (!tiruri.exists()) {
            tiruri.mkdir();
        }

        return tiruri;
    }

    private void makeFile(File file, File dest) {
        UUID uuidPoza = UUID.randomUUID();
        String extension = file.getName().substring(file.getName().lastIndexOf("."));

        File poza = new File(dest, String.format("%s%s", uuidPoza.toString(), extension));
        listaPozeDeAfisat.add(poza);
        pozaCreeata = poza;
        try {
            copyFile(file, poza);
        } catch (IOException ex) {
            Logger.getLogger(FrmAddTir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTiruriService(TiruriService tiruriService) {
        this.tiruriService = tiruriService;
    }

    public void setPozaService(PozaService pozaService) {
        this.pozaService = pozaService;
    }

    public void setSoferiTiruriService(SoferiTiruriService soferiTiruriService) {
        this.soferiTiruriService = soferiTiruriService;
    }

}
