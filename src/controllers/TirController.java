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
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import models.Marca;
import models.Model;
import models.Poza;
import models.Sofer;
import models.SoferiTiruri;
import models.Tir;
import org.hibernate.Session;
import org.hibernate.metamodel.source.annotations.JPADotNames;
import renderers.ItemMarcaRenderer;
import renderers.ItemModelRenderer;
import renderers.TiruriColorCellRenderer;
import services.MarcaService;
import services.MarcaServiceImpl;
import services.ModelService;
import services.ModelServiceImpl;
import services.PozaService;
import services.PozaServiceImpl;
import services.SoferiTiruriService;
import services.SoferiTiruriServiceImpl;
import services.TiruriService;
import services.TiruriServiceImpl;
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
    private ArrayList<File> listaPozeDeAfisat = new ArrayList<>();
    private TiruriService tiruriService = new TiruriServiceImpl();
    private MarcaService marcaService = new MarcaServiceImpl();
    private ModelService modelService = new ModelServiceImpl();
    private PozaService pozaService = new PozaServiceImpl();
    private SoferiTiruriService soferiTiruriService = new SoferiTiruriServiceImpl();
    private DefaultComboBoxModel<Marca> modelCmbMarci = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Model> modelCmbModele = new DefaultComboBoxModel<>();
    private Tir tirSelectat;
    private JComboBox cmbMarca;
    private JComboBox cmbModel;
    private ArrayList<Tir> listaTiruri;
    private File pozaCreeata;

    private JTable tblTiruri;
    private Object[][] data;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames = new String[]{"Marca", "Model", "Nr Inmatriculare", "Poza", "Status"};

    public void actionCreate(JDialog parent) {
        frmAddTir = new FrmAddTir(parent, true);
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
        cmbMarca.setModel(modelCmbMarci);
        cmbModel.setModel(modelCmbModele);
        frmAddTir.setTirController(this);
        frmAddTir.setLocationRelativeTo(parent);
        frmAddTir.setVisible(true);
    }

    public void actionIndex(java.awt.Frame parent) {
        frmAdministrareTiruri = new FrmAdministrareTiruri(parent, true);
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

    private void updateAndSetModelToTable() {
        tblTiruri = frmAdministrareTiruri.getTblTiruri();
        listaTiruri = tiruriService.getAll();
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
            switch (t.getIdStare()) {
                case 1:
                    data[x][4] = "Tir test";
                    break;
                case 2:
                    data[x][4] = "Disponibil";
                    break;
                case 3:
                    data[x][4] = "In cursa";
                    break;
                case 4:
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
        frmAfisareDetaliiTir.getLblMarca().setText(tirSelectat.getModel().getMarca().getNume());
        frmAfisareDetaliiTir.getLblModel().setText(tirSelectat.getModel().getNume());
        Pattern p = Pattern.compile("[A-Z]+|\\d+");
        Matcher m = p.matcher(tirSelectat.getNrInmatriculare());
        ArrayList<String> allMatches = new ArrayList<>();
        while (m.find()) {
            allMatches.add(m.group());
        }
        switch (tirSelectat.getIdStare()) {
            case 1:
                frmAfisareDetaliiTir.getLblStatus().setText("Tir test");
                frmAfisareDetaliiTir.getLblStatus().setForeground(Color.BLUE);
                break;
            case 2:
                frmAfisareDetaliiTir.getLblStatus().setText("Disponibil");
                Color darkGreen = new Color(0, 153, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkGreen);
                break;
            case 3:
                frmAfisareDetaliiTir.getLblStatus().setText("In cursa");
                frmAfisareDetaliiTir.getLblStatus().setForeground(Color.RED);
                frmAfisareDetaliiTir.getBtnModService().setEnabled(false);
                break;
            case 4:
                frmAfisareDetaliiTir.getLblStatus().setText("In service");
                Color darkOrange = new Color(255, 143, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkOrange);
                break;
        }
        frmAfisareDetaliiTir.getLblNrInmatriculare().setText(String.format("%s %s %s", allMatches.get(0), allMatches.get(1), allMatches.get(2)));
        ArrayList<SoferiTiruri> listaSoferiTiruri = soferiTiruriService.getSoferiTiruriByTir(tirSelectat);
        ArrayList<Sofer> listaSoferi = new ArrayList<>();
        for (SoferiTiruri st:listaSoferiTiruri){
            listaSoferi.add(st.getSofer());
        }
        DefaultListModel defaultListModel = new DefaultListModel();
        for (Sofer s:listaSoferi){
            defaultListModel.addElement(s.getNumeComplet());
        }
        frmAfisareDetaliiTir.getLstSoferi().setModel(defaultListModel);
        frmAfisareDetaliiTir.setTirController(this);
        frmAfisareDetaliiTir.setLocationRelativeTo(frmAdministrareTiruri);
        frmAfisareDetaliiTir.setVisible(true);
    }

    public void modService() {
        if (tirSelectat.getIdStare() == 2) {
            int raspuns = JOptionPane.showConfirmDialog(frmAfisareDetaliiTir, "Sunteti sigur ca doriti sa plasati acest tir in service?", "Plasare tir in service", JOptionPane.YES_NO_OPTION);
            if (raspuns == JOptionPane.YES_OPTION) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                org.hibernate.Transaction tx = session.beginTransaction();
                tirSelectat.setIdStare(4);
                tiruriService.adaugaTir(tirSelectat);
                tx.commit();
                frmAfisareDetaliiTir.getLblStatus().setText("In service");
                Color darkOrange = new Color(255, 143, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkOrange);
            }
        } else if (tirSelectat.getIdStare() == 4) {
            int raspuns = JOptionPane.showConfirmDialog(frmAfisareDetaliiTir, "Sunteti sigur ca doriti sa eliminati acest tir din service?", "Eliminare tir din service", JOptionPane.YES_NO_OPTION);
            if (raspuns == JOptionPane.YES_OPTION) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                org.hibernate.Transaction tx = session.beginTransaction();
                tirSelectat.setIdStare(2);
                tiruriService.adaugaTir(tirSelectat);
                tx.commit();
                frmAfisareDetaliiTir.getLblStatus().setText("Disponibil");
                Color darkGreen = new Color(0, 153, 0);
                frmAfisareDetaliiTir.getLblStatus().setForeground(darkGreen);
            }
        }
    }

    public void saveTir() {
        if (isFormValid()) {
            JTextField txtNrInmatriculare = frmAddTir.getTxtNrInmatriculare();

            File tiruri = makeFolderPoze();

            File pozeTir = new File(tiruri, txtNrInmatriculare.getText());
            if (!pozeTir.exists()) {
                pozeTir.mkdir();
            }
            ArrayList<File> listaFisiereNoua = new ArrayList<>();
            for (File pozaDeSalvat : listaFisiere) {
                makeFile(pozaDeSalvat, pozeTir);
                listaFisiereNoua.add(pozaCreeata);
            }
            Tir tir = new Tir();
            tir.setIdStare(2);
            Model m = (Model) cmbModel.getSelectedItem();
            tir.setIdModel(m.getId());
            tir.setNrInmatriculare(txtNrInmatriculare.getText());
            tiruriService.adaugaTir(tir);
            for (File pozaDeSalvat : listaFisiereNoua) {
                Poza p = new Poza();
                p.setTipObiect(1);
                p.setIdObiect(tir.getId());
                p.setImagePath(pozaDeSalvat.getName());
                pozaService.adaugaPoza(p);
            }
            JOptionPane.showMessageDialog(frmAddTir, "Tirul a fost salvat cu succes.");
            frmAddTir.dispose();
            updateAndSetModelToTable();
        }
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
        System.out.println("aici:"+curImageIndex + " "+listaPoze.size());
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
}
