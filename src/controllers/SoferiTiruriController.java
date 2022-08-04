/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddSoferiTiruri;
import gui.FrmAdministrareSoferiTiruri;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.Marca;
import models.Model;
import models.Sofer;
import models.SoferiTiruri;
import models.Tir;
import renderers.ItemSoferRenderer;
import renderers.ItemTirRenderer;
import services.SoferService;
import services.SoferServiceImpl;
import services.SoferiTiruriService;
import services.SoferiTiruriServiceImpl;
import services.TiruriService;
import services.TiruriServiceImpl;
import tablemodel.ColumnResizer1;
import tablemodels.TableModelSoferiTiruri;

/**
 *
 * @author Vlad
 */
public class SoferiTiruriController {
    
    private FrmAddSoferiTiruri frmAddSoferiTiruri;
    private FrmAdministrareSoferiTiruri frmAdministrareSoferiTiruri;
    private SoferiTiruriService soferiTiruriService = new SoferiTiruriServiceImpl();
    private TiruriService tiruriService = new TiruriServiceImpl();
    private SoferService soferService = new SoferServiceImpl();
    private SoferiTiruri soferiTiruriSelectat = null;
    private ArrayList<SoferiTiruri> listaSoferiTiruri;
    private tablemodels.TableModelSoferiTiruri tableModelSoferiTiruri = new TableModelSoferiTiruri();
    private JComboBox dropDownSoferi;
    private JComboBox dropDownTiruri;
    private Sofer selectatiSoferul;
    private Tir selectatiTirul;
    private ItemSoferRenderer soferRenderer = new ItemSoferRenderer();
    private ItemTirRenderer tirRenderer = new ItemTirRenderer();
    
    public void actionIndex(JFrame parent) {
        frmAdministrareSoferiTiruri = new FrmAdministrareSoferiTiruri();
        frmAdministrareSoferiTiruri.setLocationRelativeTo(parent);
        this.soferiTiruriSelectat = null;
        frmAdministrareSoferiTiruri.setSoferiTiruriController(this);
        updateAndSetModelToTable();
        frmAdministrareSoferiTiruri.setVisible(true);
    }
    
    public void actionCreate(JFrame parent) {
        this.soferiTiruriSelectat = null;
        frmAddSoferiTiruri = new FrmAddSoferiTiruri(parent, true);
        dropDownSoferi = frmAddSoferiTiruri.getDropDownSoferi();
        dropDownTiruri = frmAddSoferiTiruri.getDropDownTiruri();
        frmAddSoferiTiruri.setSoferiTiruriController(this);
        frmAddSoferiTiruri.setLocationRelativeTo(parent);
        updateAndSetModelToDropDowns();
        frmAddSoferiTiruri.setVisible(true);
    }
    
    public void actionUpdate(JFrame parent) {
        JTable tblSoferiTiruri = frmAdministrareSoferiTiruri.getTblSoferiTiruri();
        
        int index = tblSoferiTiruri.convertRowIndexToModel(tblSoferiTiruri.getSelectedRow());
        listaSoferiTiruri = soferiTiruriService.getAll();
        
        this.soferiTiruriSelectat = listaSoferiTiruri.get(index);
        
        frmAddSoferiTiruri = new FrmAddSoferiTiruri(parent, true, soferiTiruriSelectat);
        frmAddSoferiTiruri.setSoferiTiruriController(this);
        frmAddSoferiTiruri.setLocationRelativeTo(parent);
        dropDownSoferi = frmAddSoferiTiruri.getDropDownSoferi();
        dropDownTiruri = frmAddSoferiTiruri.getDropDownTiruri();
        updateAndSetModelToDropDowns();
        frmAddSoferiTiruri.getDropDownSoferi().setSelectedItem(soferiTiruriSelectat.getSofer());
        frmAddSoferiTiruri.getDropDownTiruri().setSelectedItem(soferiTiruriSelectat.getTir());
        frmAddSoferiTiruri.setVisible(true);
        updateAndSetModelToTable();
    }
    
    public void actionToggleActiv() {
        JTable tblSoferTiruri = frmAdministrareSoferiTiruri.getTblSoferiTiruri();
        
        int index = tblSoferTiruri.convertRowIndexToModel(tblSoferTiruri.getSelectedRow());
        SoferiTiruri sf = listaSoferiTiruri.get(index);
        
        if(frmAdministrareSoferiTiruri.getBtnActiveazaInactiveaza().getText() == "Activare") {
            sf.setValid(true);
        } 
        if(frmAdministrareSoferiTiruri.getBtnActiveazaInactiveaza().getText() == "Inactivare") {
            sf.setValid(false);
        }
        
        soferiTiruriService.adaugaSoferTir(sf);
        
        updateAndSetModelToTable();
    }
    
    public boolean isFormValid() {
        dropDownSoferi = frmAddSoferiTiruri.getDropDownSoferi();
        dropDownTiruri = frmAddSoferiTiruri.getDropDownTiruri();
        
        if(dropDownSoferi.getSelectedItem() == selectatiSoferul || dropDownTiruri.getSelectedItem() == selectatiTirul) {
            JOptionPane.showMessageDialog(frmAddSoferiTiruri, "Nu ati selectat un sofer/tir");
            return false;
        }
        return true;
    }
    
    public void updateAndSetModelToTable() {
        JTable tblSoferiTiruri = frmAdministrareSoferiTiruri.getTblSoferiTiruri();
        tblSoferiTiruri.setAutoCreateRowSorter(true);
        
        if(frmAdministrareSoferiTiruri.getRdbActive().isSelected()) {
            listaSoferiTiruri = soferiTiruriService.getSoferiTiruriByValid(true);
            frmAdministrareSoferiTiruri.getBtnActiveazaInactiveaza().setText("Inactivare");
        }
        if(frmAdministrareSoferiTiruri.getRdbInactive().isSelected()) {
            listaSoferiTiruri = soferiTiruriService.getSoferiTiruriByValid(false);
            frmAdministrareSoferiTiruri.getBtnActiveazaInactiveaza().setText("Activare");
        }
        
        tableModelSoferiTiruri.setListaSoferiTiruri(listaSoferiTiruri);
        tableModelSoferiTiruri.fireTableDataChanged();
        tblSoferiTiruri.setModel(tableModelSoferiTiruri);
        ColumnResizer1.resizeRowHeightAndColumnsWidth(tblSoferiTiruri);
    }
    
    public void updateAndSetModelToDropDowns() {
        DefaultComboBoxModel<Sofer> cmbModelSoferi = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Tir> cmbModelTiruri = new DefaultComboBoxModel<>();
        ArrayList<Tir> listaTiruri = tiruriService.getAll();
        ArrayList<Sofer> listaSoferi = soferService.getAll();
        
        dropDownSoferi.setRenderer(soferRenderer);
        dropDownTiruri.setRenderer(tirRenderer);
        
        if(soferiTiruriSelectat == null) {
            this.selectatiSoferul = new Sofer(-1, "--Selectati Soferul--", "", "");
            this.selectatiTirul = new Tir(new Model(-1, "", new Marca(-1, "", 0)), "--Selectati Tirul--", -1, null);
            
            cmbModelSoferi.addElement(selectatiSoferul);
            cmbModelTiruri.addElement(selectatiTirul);
        }
        
        
        for(Tir tir : listaTiruri) {
//            if(tir.getStare().getNume() != "In cursa" )
            cmbModelTiruri.addElement(tir);
        }
        
        for(Sofer sofer : listaSoferi) {
//            if(sofer.getSoferiTiruri().iterator().next().getInregistrari().isEmpty()) {
                cmbModelSoferi.addElement(sofer);
//            }
        }
        
        dropDownSoferi.setModel(cmbModelSoferi);
        dropDownTiruri.setModel(cmbModelTiruri);
    }
    
    public void saveSoferTir() {
        Sofer sofer = (Sofer) dropDownSoferi.getSelectedItem();
        Tir tir = (Tir) dropDownTiruri.getSelectedItem();
        
        SoferiTiruri soferTir = new SoferiTiruri();
        
        if(soferiTiruriSelectat != null) {
            soferTir.setId(soferiTiruriSelectat.getId());
        }
        
        soferTir.setIdSofer(sofer.getId());
        soferTir.setIdTir(tir.getId());
        soferTir.setSofer(sofer);
        soferTir.setTir(tir);
        soferTir.setValid(true);
        
        listaSoferiTiruri = soferiTiruriService.getAll();
        
//        for(SoferiTiruri sf : listaSoferiTiruri) {
//            if(sf.equals(soferTir)) {
//                JOptionPane.showMessageDialog(frmAddSoferiTiruri, String.format("Soferul %s este deja asociat tirului %s", sofer.getNume() , tir.getNrInmatriculare()));
//                return;
//            }
//        }
        
        soferiTiruriService.adaugaSoferTir(soferTir);
        updateAndSetModelToTable();
        frmAddSoferiTiruri.dispose();
    }
  }
