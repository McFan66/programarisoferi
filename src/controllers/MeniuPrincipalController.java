/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.toedter.calendar.JDateChooser;
import gui.FrmAddInregistrare;
import gui.FrmMeniuPrincipal;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.Inregistrare;
import models.Sofer;
import models.SoferiTiruri;
import models.Tir;
import observers.Fereastra;
import observers.VObserver;
import renderers.ItemSoferRenderer;
import renderers.ItemSoferiTiruriRenderer;
import services.InregistrareServiceImpl;
import services.SoferServiceImpl;
import services.SoferiTiruriServiceImpl;
import services.StareServiceImpl;
import services.TiruriServiceImpl;
import tablemodel.ColumnResizer1;
import tablemodels.TableModelInregistrari;

/**
 *
 * @author Vlad
 */
public class MeniuPrincipalController implements VObserver {

    private FrmAddInregistrare frmAddInregistrare;
    private FrmMeniuPrincipal frmMeniuPrincipal;
    private services.InregistrariService inregistrariService = new InregistrareServiceImpl();
    private services.TiruriService tiruriService = new TiruriServiceImpl();
    private services.StareService stareService = new StareServiceImpl();
    private services.SoferiTiruriService soferiTiruriService = new SoferiTiruriServiceImpl();
    private services.SoferService soferService = new SoferServiceImpl();

    private DefaultListModel<Tir> modelListe;

    private Inregistrare inregistrareSelectata = null;
    private ArrayList<Inregistrare> listaInregistrari;
    private tablemodels.TableModelInregistrari tableModelInregistrari = new TableModelInregistrari();
    private JDateChooser dtcPlecare;
    private JDateChooser dtcSosire;

    public void actionIndex() {
        tiruriService.addObserver(this);
        frmMeniuPrincipal = new FrmMeniuPrincipal();
        frmMeniuPrincipal.setMeniuPrincipalController(this);
        frmMeniuPrincipal.setLocationRelativeTo(null);
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Stare test")), frmMeniuPrincipal.getLstParcate());
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Disponibil")), frmMeniuPrincipal.getLstLibere());
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Cursa")), frmMeniuPrincipal.getLstInCursa());
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Service")), frmMeniuPrincipal.getLstInService());
        setModelToListaSoferiLiberi();
        updateAndSetModelToTable();
        frmMeniuPrincipal.setVisible(true);
    }

    public void endInregistrare() {
        JTable tblInregistrari = frmMeniuPrincipal.getTblInregistrari();

        int index = tblInregistrari.convertRowIndexToModel(tblInregistrari.getSelectedRow());

        if (index == -1) {
            JOptionPane.showMessageDialog(frmMeniuPrincipal, "Nu ati selectat o inregistrare");
            return;
        }

        inregistrareSelectata = listaInregistrari.get(index);
        Calendar c1 = Calendar.getInstance();
        inregistrareSelectata.setDataSosire(c1.getTime());

        inregistrariService.adaugaInregistrare(inregistrareSelectata);

        updateAndSetModelToTable();

    }

    public void actionEdit(JFrame parent) {
        frmAddInregistrare = new FrmAddInregistrare(parent, true, inregistrareSelectata);

        JTable tblInregistrari = frmMeniuPrincipal.getTblInregistrari();
        int index;

        try {
            index = tblInregistrari.convertRowIndexToModel(tblInregistrari.getSelectedRow());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frmMeniuPrincipal, "Va rugam sa selectati o inregistrare");
            return;
        }

        this.inregistrareSelectata = listaInregistrari.get(index);

        frmAddInregistrare.getCmbSoferTir().setSelectedItem(inregistrareSelectata.getSoferTir());
        frmAddInregistrare.getDtcPlecare().setDate(inregistrareSelectata.getDataPlecare());
        frmAddInregistrare.getDtcSosire().setDate(inregistrareSelectata.getDataSosire());
        frmAddInregistrare.setLocationRelativeTo(parent);
        frmAddInregistrare.setInregistrariController(this);
        setModelToCmb();
        frmAddInregistrare.setVisible(true);
        updateAndSetModelToTable();
    }

    public void actionCreate(JFrame parent) {
        inregistrareSelectata = null;
        frmAddInregistrare = new FrmAddInregistrare(parent, true);
        frmAddInregistrare.setInregistrariController(this);
        frmAddInregistrare.setLocationRelativeTo(parent);
        Calendar c1 = Calendar.getInstance();
        frmAddInregistrare.getDtcPlecare().getDateEditor().setDate(c1.getTime());
//        dateChooserStateChanged();
        setModelToCmb();
        frmAddInregistrare.setVisible(true);
    }

    public void setModelToCmb() {
        DefaultComboBoxModel<SoferiTiruri> modelCmbSoferiTiruri = new DefaultComboBoxModel<>();

        ArrayList<SoferiTiruri> listaSoferiTiruri = soferiTiruriService.getSoferiTiruriByValid(true);

        for (SoferiTiruri sf : listaSoferiTiruri) {
            modelCmbSoferiTiruri.addElement(sf);
        }

        frmAddInregistrare.getCmbSoferTir().setRenderer(new ItemSoferiTiruriRenderer());
        frmAddInregistrare.getCmbSoferTir().setModel(modelCmbSoferiTiruri);
    }

    public boolean isFormValid() {
        if (frmAddInregistrare.getDtcPlecare().getDate() == null) {
            JOptionPane.showMessageDialog(frmAddInregistrare, "Va rugam sa selectati o data de plecare");
            return false;
        }
        if (frmAddInregistrare.getDtcSosire().getDate() == null) {
            JOptionPane.showMessageDialog(frmAddInregistrare, "Va rugam sa selectati o data de sosire");
            return false;
        }
        if (frmAddInregistrare.getCmbSoferTir().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(frmAddInregistrare, "Va rugam sa selectati soferul + tirul care vor pleca in cursa");
            return false;
        }
        return true;
    }

    public void dateChooserStateChanged() {
        frmAddInregistrare.getDtcSosire().getDateEditor().setDate(frmAddInregistrare.getDtcPlecare().getDate());
    }

    public void saveInregistrare() {
        if (isFormValid()) {
            Inregistrare i = new Inregistrare();

            this.dtcPlecare = frmAddInregistrare.getDtcPlecare();
            this.dtcSosire = frmAddInregistrare.getDtcSosire();

            SoferiTiruri sf = (SoferiTiruri) frmAddInregistrare.getCmbSoferTir().getSelectedItem();
            sf.setInCursa(true);
//            sf.getTir().setStare(stareService.getStareByNume("In cursa"));
//            tiruriService.adaugaTir(sf.getTir());
            soferiTiruriService.adaugaSoferTir(sf);

            if (inregistrareSelectata != null) {
                i.setId(inregistrareSelectata.getId());
            }

            i.setDataPlecare(this.dtcPlecare.getDate());
            i.setDataSosire(this.dtcSosire.getDate());
            i.setSoferTir(sf);
            i.setIdSoferiTiruri(sf.getId());

            inregistrariService.adaugaInregistrare(i);
            updateAndSetModelToTable();

            JOptionPane.showMessageDialog(frmAddInregistrare, "Inregistrare adaugata cu success");
            frmAddInregistrare.dispose();
        }
    }

    public void updateAndSetModelToTable() {
        JTable tblInregistrari = frmMeniuPrincipal.getTblInregistrari();
        tblInregistrari.setAutoCreateRowSorter(true);

        if (frmMeniuPrincipal.getRdbToate().isSelected()) {
            listaInregistrari = inregistrariService.getAll();
        }
        if (frmMeniuPrincipal.getRdbFinalizate().isSelected()) {
            listaInregistrari = inregistrariService.getInregistrariFinalizate();
        }
        if (frmMeniuPrincipal.getRdbInDesfasurare().isSelected()) {
            listaInregistrari = inregistrariService.getInregistrariInDesfasurare();
        }

        tableModelInregistrari.setListaInregistrari(listaInregistrari);
        tableModelInregistrari.fireTableDataChanged();
        tblInregistrari.setModel(tableModelInregistrari);
        ColumnResizer1.resizeRowHeightAndColumnsWidth(tblInregistrari);
    }

    private void setModelToListaSoferiLiberi() {
        DefaultListModel<Sofer> modelListaSoferiLiberi = new DefaultListModel<>();

        for (Sofer s : soferService.getAll()) {
            if (s.getSoferiTiruri().isEmpty()) {
                modelListaSoferiLiberi.addElement(s);
            }
        }

        frmMeniuPrincipal.getLstSoferiLiberi().setModel(modelListaSoferiLiberi);
        frmMeniuPrincipal.getLstSoferiLiberi().setCellRenderer(new ItemSoferRenderer());
    }

    private void setModel(ArrayList<Tir> listaTiruri, JList listaInterfata) {
        modelListe = new DefaultListModel<>();
        for (Tir t : listaTiruri) {
            modelListe.addElement(t);
        }
        listaInterfata.setModel(modelListe);
        listaInterfata.setCellRenderer(new renderers.ItemTirRenderer());
    }

    @Override
    public void update(Object subject) {
        if (subject != null) {
            Tir tir=(Tir)subject;
            System.out.println(tir.toString());
        }
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Stare test")), frmMeniuPrincipal.getLstParcate());
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Disponibil")), frmMeniuPrincipal.getLstLibere());
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Cursa")), frmMeniuPrincipal.getLstInCursa());
        setModel(tiruriService.getTirByStare(stareService.getStareByNume("Service")), frmMeniuPrincipal.getLstInService());
    }

}
