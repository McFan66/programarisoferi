/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddModel;
import gui.FrmAdministrareModele;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.Marca;
import models.Model;
import renderers.ItemMarcaRenderer;
import renderers.MarciModeleColorCellRenderer;
import services.MarcaService;
import services.MarcaServiceImpl;
import services.ModelService;
import services.ModelServiceImpl;
import tablemodels.TableModelModele;

/**
 *
 * @author Vlad
 */
public class ModelController {
    private FrmAddModel frmAddModel;
    private FrmAdministrareModele frmAdministrareModele;
    private Model modelSelectat;
    private Marca selectatiMarca = new Marca(-1, "--SelectatiMarca--");
    private ModelService modelService = new ModelServiceImpl();
    private MarcaService marcaService = new MarcaServiceImpl();
    private ArrayList<Model> listaModele;
    private tablemodels.TableModelModele tableModelModele = new TableModelModele();
    private DefaultComboBoxModel<Marca> modelCmbMarci = new DefaultComboBoxModel<>();

    
    public void initComboBoxMarci() {
        JComboBox dropDownMarca = frmAddModel.getDropDownMarca();
        dropDownMarca.setRenderer(new ItemMarcaRenderer());
        ArrayList<Marca> listaMarci = marcaService.getAll();
        modelCmbMarci.removeAllElements();
        modelCmbMarci.addElement(selectatiMarca);
        for (Marca m : listaMarci) {
            modelCmbMarci.addElement(m);
        }
        
        dropDownMarca.setModel(modelCmbMarci);
    }
    
    public void actionCreate(JFrame parent) {        
        frmAddModel = new FrmAddModel(parent, true);
       
        initComboBoxMarci();
        
        frmAddModel.setLocationRelativeTo(parent);
        frmAddModel.setModelController(this);
        frmAddModel.setVisible(true);
    }
    
    public void updateAndSetModelToTable() {
        JTable tblModele = frmAdministrareModele.getTblModele();
        listaModele = modelService.getAll();
        tableModelModele.setListaModele(listaModele);
        tableModelModele.fireTableDataChanged();
        tblModele.setModel(tableModelModele);
        tblModele.setAutoCreateRowSorter(true);
        tblModele.getColumnModel().getColumn(3).setCellRenderer(new MarciModeleColorCellRenderer());
    }   
    
    public void actionIndex(JFrame parent) {
        frmAdministrareModele = new FrmAdministrareModele();
        this.modelSelectat = null;
        updateAndSetModelToTable();
        frmAdministrareModele.setModelController(this);
        frmAdministrareModele.setLocationRelativeTo(parent);
        frmAdministrareModele.setVisible(true);
    }
    
    public void actionEdit(JFrame parent) {
        JTable tblModele = frmAdministrareModele.getTblModele();
        
        int index = tblModele.convertRowIndexToModel(tblModele.getSelectedRow());
        listaModele = modelService.getAll();
        this.modelSelectat = listaModele.get(index);
        
        frmAddModel = new FrmAddModel(parent, true, modelSelectat);
        frmAddModel.getTxtNume().setText(modelSelectat.getNume());
        initComboBoxMarci();
        frmAddModel.getDropDownMarca().setSelectedItem(modelSelectat.getMarca());
        
        frmAddModel.setModelController(this);
        frmAddModel.setLocationRelativeTo(parent);
        frmAddModel.setVisible(true);
        
        updateAndSetModelToTable();
        
        frmAddModel.dispose();
    }
    
    public void actionToggleActiv(JFrame parent) {
        JTable tblModele = frmAdministrareModele.getTblModele();
        
        int index = tblModele.convertRowIndexToModel(tblModele.getSelectedRow());
        
        Model m = listaModele.get(index);
        
        if(m.isActiv()) {
            m.setActiv(false);
        } else {
            m.setActiv(true);
        }
        
        modelService.adaugaModel(m);
        updateAndSetModelToTable();
        
    }
    
    public void saveModel() {
        if(isFormValid()) {
            JComboBox dropDownMarca = frmAddModel.getDropDownMarca();
            String nume = frmAddModel.getTxtNume().getText();
            
            Model model = new Model();
            Marca marca = (Marca) dropDownMarca.getSelectedItem();
            
            if(modelSelectat != null) {
                model.setId(modelSelectat.getId());
                model.setActiv(modelSelectat.isActiv());
            } else {
                model.setActiv(true);
            }
                        
            model.setNume(nume);
            model.setMarca(marca);
            model.setIdMarca(marca.getId());
            
            modelService.adaugaModel(model);
            updateAndSetModelToTable();
            frmAddModel.dispose();
        }
    }
    
    public boolean isFormValid() {
        JTextField txtNume = frmAddModel.getTxtNume();
        JComboBox dropDownMarca = frmAddModel.getDropDownMarca();
        
        if(txtNume.getText().trim().length() < 2) {
            JOptionPane.showMessageDialog(frmAddModel, "Numele modelului trebuie sa contina cel putin 2 caractere");
            return false;
        }
        if(dropDownMarca.getSelectedItem().equals(selectatiMarca)) {
            JOptionPane.showMessageDialog(frmAddModel, "Nu ati selectat o marca");
            return false;
        }
        return true;
    }
}
