/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddMarca;
import gui.FrmAdministrareMarci;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import models.AppSingleTone;
import models.Marca;
import renderers.MarciModeleColorCellRenderer;
import services.MarcaService;
import services.MarcaServiceImpl;
import tablemodel.ColumnResizer1;
import tablemodels.TableModelMarci;

/**
 *
 * @author Vlad
 */
public class MarcaController {
    private FrmAddMarca frmAddMarca;
    private FrmAdministrareMarci frmAdministrareMarci;
    private MarcaService marcaService = AppSingleTone.getAppSingleToneInstance().getMarcaService();
    private ArrayList<Marca> listaMarci;
    private tablemodels.TableModelMarci tableModelMarci = new TableModelMarci();
    private Marca marcaSelectata;
    
    public void actionCreate(JFrame parent) {
        frmAddMarca = new FrmAddMarca(parent, true);
        frmAddMarca.setMarcaController(this);
        frmAddMarca.setLocationRelativeTo(parent);
        frmAddMarca.setVisible(true);
    }
    
    public void actionIndex(JFrame parent) {
        frmAdministrareMarci = new FrmAdministrareMarci();
        this.marcaSelectata = null;
        updateAndSetModelToTable();
        frmAdministrareMarci.setMarcaController(this);
        frmAdministrareMarci.setLocationRelativeTo(parent);
        frmAdministrareMarci.setVisible(true);
    }
    
    public void updateAndSetModelToTable() {
        JTable tblMarci = frmAdministrareMarci.getTblMarci();
        tblMarci.setAutoCreateRowSorter(true);
        listaMarci = marcaService.getAll();
        tableModelMarci.setListaMarci(listaMarci);
        tableModelMarci.fireTableDataChanged();
        tblMarci.setModel(tableModelMarci);
        tblMarci.getColumnModel().getColumn(2).setCellRenderer(new MarciModeleColorCellRenderer());
        ColumnResizer1.resizeRowHeightAndColumnsWidth(tblMarci);
    }
    
    public void actionEdit(JFrame parent) {
        JTable tblMarci = frmAdministrareMarci.getTblMarci();
        int index = tblMarci.convertRowIndexToModel(tblMarci.getSelectedRow());
        listaMarci = marcaService.getAll();
        this.marcaSelectata = listaMarci.get(index);
        
        frmAddMarca = new FrmAddMarca(parent, true, marcaSelectata);
        frmAddMarca.setMarcaController(this);
        frmAddMarca.setLocationRelativeTo(parent);
        frmAddMarca.getTxtMarca().setText(marcaSelectata.getNume());
        frmAddMarca.setVisible(true);
        updateAndSetModelToTable();
        
        frmAddMarca.dispose();
        
    }
    
    public void actionToggleActiva(JFrame parent) {
        JTable tblMarci = frmAdministrareMarci.getTblMarci();
        
        int index = tblMarci.convertRowIndexToModel(tblMarci.getSelectedRow());
        
        this.marcaSelectata = listaMarci.get(index);
        
        if(this.marcaSelectata.getActiva() == 1) {
            this.marcaSelectata.setActiva(0);
        } else {
            this.marcaSelectata.setActiva(1);
        }
        
        marcaService.adaugaMarca(marcaSelectata);
        
        updateAndSetModelToTable();
    }
    
    public boolean isFormValid() {
        JTextField txtMarca = frmAddMarca.getTxtMarca();
        
        if(txtMarca.getText().trim().length() < 2) {
            JOptionPane.showMessageDialog(frmAddMarca, "Numele trebuie sa aiba minim 2 caractere");
            return false;
        }
        return true;
    }
    
    public void saveMarca() {
        if(isFormValid()) {
            String nume = frmAddMarca.getTxtMarca().getText();
            
            Marca marca = new Marca();
            
            if(marcaSelectata != null) {
                marca.setId(marcaSelectata.getId());
                marca.setActiva(marcaSelectata.getActiva());
            } else {
                marca.setActiva(1);
            }
            
            marca.setNume(nume);
            
            marcaService.adaugaMarca(marca);
            updateAndSetModelToTable();
            frmAddMarca.dispose();
        }
    }
}
