/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAdministrareUtilizatori;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Utilizator;
import services.UtilizatoriService;
import services.UtilizatoriServiceImpl;

/**
 *
 * @author Stefan
 */
public class UtilizatoriController {
    
    private FrmAdministrareUtilizatori frmAdministrareUtilizatori;
    private UtilizatoriService utilizatoriService = new UtilizatoriServiceImpl();
    private ArrayList<Utilizator> listaUtilizatori;
    private Utilizator utilizatorSelectat;
    
    private String[][] data;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames = new String[]{"Nume Complet", "Adresa Email"};
    private JTable tblUtilizatori;
    
    public void actionIndex(JFrame parent) {
        frmAdministrareUtilizatori = new FrmAdministrareUtilizatori(parent, true);
        frmAdministrareUtilizatori.getRdbToate().setSelected(true);
        updateAndSetModelToTable();
        frmAdministrareUtilizatori.setUtilizatoriController(this);
        frmAdministrareUtilizatori.setLocationRelativeTo(parent);
        frmAdministrareUtilizatori.setVisible(true);
    }
    
    public void updateAndSetModelToTable() {
        tblUtilizatori = frmAdministrareUtilizatori.getTblUtilizatori();
        
        if (frmAdministrareUtilizatori.getRdbToate().isSelected()) {
            listaUtilizatori = utilizatoriService.getAll();
            frmAdministrareUtilizatori.getBtnSterge().setText("Dezactiveaza");
        } else if (frmAdministrareUtilizatori.getRdbActiv().isSelected()) {
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(true);
            frmAdministrareUtilizatori.getBtnSterge().setText("Dezactiveaza");
        } else if (frmAdministrareUtilizatori.getRdbInactiv().isSelected()) {
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(false);
            frmAdministrareUtilizatori.getBtnSterge().setText("Activeaza");
        }
        
        data = new String[listaUtilizatori.size()][2];
        int x = 0;
        for (Utilizator u : listaUtilizatori) {
            data[x][0] = u.getNumeComplet();
            data[x][1] = u.getEmail();
        }
        defaultTableModel = new DefaultTableModel(data, columnNames);
        tblUtilizatori.setModel(defaultTableModel);
    }
    
    public void itemSelected() {
        int index = tblUtilizatori.convertRowIndexToModel(tblUtilizatori.getSelectedRow());
        listaUtilizatori = utilizatoriService.getAll();
        Utilizator u = listaUtilizatori.get(index);
        if (u.isValid()) {
            frmAdministrareUtilizatori.getBtnSterge().setText("Dezactiveaza");
        } else if (!u.isValid()) {
            frmAdministrareUtilizatori.getBtnSterge().setText("Activeaza");
        }
    }
    
}
