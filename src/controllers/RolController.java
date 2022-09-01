/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddRoluri;
import gui.FrmAddUtilizatori;
import gui.FrmAdministrareRoluri;
import gui.FrmAdministrareUtilizatori;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Rol;
import models.Utilizator;
import services.RoluriService;
import services.RoluriServiceImpl;
import services.UtilizatoriRoluriService;
import services.UtilizatoriRoluriServiceImpl;
import services.UtilizatoriService;
import services.UtilizatoriServiceImpl;

/**
 *
 * @author Stefan
 */
public class RolController {

    private FrmAdministrareRoluri frmAdministrareRoluri;
    private FrmAddRoluri frmAddRoluri;
    private RoluriService roluriService = new RoluriServiceImpl();
    private UtilizatoriRoluriService utilizatoriRoluriService = new UtilizatoriRoluriServiceImpl();
    private ArrayList<Rol> listaRoluri;
    private Rol rolSelectat;

    private String[][] data;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames = new String[]{"Nume", "Numar utilizatori"};
    private JTable tblRoluri;

    public void actionIndex(JFrame parent) {
        frmAdministrareRoluri = new FrmAdministrareRoluri(parent, true);
        frmAdministrareRoluri.getRdbToate().setSelected(true);
        updateAndSetModelToTable();
        frmAdministrareRoluri.setRolController(this);
        frmAdministrareRoluri.setLocationRelativeTo(parent);
        frmAdministrareRoluri.setVisible(true);
    }

    public void actionCreate(JDialog parent) {
        frmAddRoluri = new FrmAddRoluri(parent, true);
        frmAddRoluri.setRolController(this);
        this.rolSelectat = null;
        frmAddRoluri.setLocationRelativeTo(parent);
        frmAddRoluri.setVisible(true);
    }

    public void actionEdit(JDialog parent) {
        int index = tblRoluri.convertRowIndexToModel(tblRoluri.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareRoluri, "Va rugam selectati un rol.");
            return;
        }
        if (frmAdministrareRoluri.getRdbToate().isSelected()){
            listaRoluri = roluriService.getAll();
        } else if (frmAdministrareRoluri.getRdbActiv().isSelected()){
            listaRoluri = roluriService.getRoluriByValid(true);
        } else if (frmAdministrareRoluri.getRdbInactiv().isSelected()){
            listaRoluri = roluriService.getRoluriByValid(false);
        }
        Rol r = listaRoluri.get(index);
        frmAddRoluri = new FrmAddRoluri(parent, true);
        this.rolSelectat = r;
        frmAddRoluri.getTxtNume().setText(r.getNume());
        frmAddRoluri.setRolController(this);
        frmAddRoluri.setLocationRelativeTo(parent);
        frmAddRoluri.setVisible(true);
    }

    public void actionDelete() {
        int index = tblRoluri.convertRowIndexToModel(tblRoluri.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareRoluri, "Va rugam selectati un rol.");
            return;
        }
        if (frmAdministrareRoluri.getRdbToate().isSelected()){
            listaRoluri = roluriService.getAll();
        } else if (frmAdministrareRoluri.getRdbActiv().isSelected()){
            listaRoluri = roluriService.getRoluriByValid(true);
        } else if (frmAdministrareRoluri.getRdbInactiv().isSelected()){
            listaRoluri = roluriService.getRoluriByValid(false);
        }
        Rol r = listaRoluri.get(index);
        int raspuns = JOptionPane.showConfirmDialog(frmAdministrareRoluri, String.format("Sunteti sigur ca doriti sa inactivati rolul %s?", r), "Inactivare rol", JOptionPane.YES_NO_OPTION);
        if (raspuns == JOptionPane.YES_OPTION) {
            r.setValid(!r.isValid());
            roluriService.adaugaRol(r);
            updateAndSetModelToTable();
        }
    }

    public void updateAndSetModelToTable() {
        tblRoluri = frmAdministrareRoluri.getTblUtilizatori();

        if (frmAdministrareRoluri.getRdbToate().isSelected()) {
            listaRoluri = roluriService.getAll();
            frmAdministrareRoluri.getBtnSterge().setText("Dezactiveaza");
        } else if (frmAdministrareRoluri.getRdbActiv().isSelected()) {
            listaRoluri = roluriService.getRoluriByValid(true);
            frmAdministrareRoluri.getBtnSterge().setText("Dezactiveaza");
        } else if (frmAdministrareRoluri.getRdbInactiv().isSelected()) {
            listaRoluri = roluriService.getRoluriByValid(false);
            frmAdministrareRoluri.getBtnSterge().setText("Activeaza");
        }

        data = new String[listaRoluri.size()][2];
        int x = 0;
        for (Rol r : listaRoluri) {
            data[x][0] = r.getNume();
            data[x][1] = String.valueOf(utilizatoriRoluriService.getNrUtilizatoriCuRol(r));
            x++;
        }
        defaultTableModel = new DefaultTableModel(data, columnNames);
        tblRoluri.setModel(defaultTableModel);
    }

    public void itemSelected() {
        int index = tblRoluri.convertRowIndexToModel(tblRoluri.getSelectedRow());
        listaRoluri = roluriService.getAll();
        Rol r = listaRoluri.get(index);
        if (r.isValid()) {
            frmAdministrareRoluri.getBtnSterge().setText("Dezactiveaza");
        } else if (!r.isValid()) {
            frmAdministrareRoluri.getBtnSterge().setText("Activeaza");
        }
    }

    public void saveRol() {
        if (isFormValid()) {
            if (rolSelectat == null) {
                rolSelectat = new Rol();
                rolSelectat.setValid(true);
            }
            rolSelectat.setNume(frmAddRoluri.getTxtNume().getText());
            rolSelectat.setValid(!rolSelectat.isValid());

            roluriService.adaugaRol(rolSelectat);
            updateAndSetModelToTable();
            frmAddRoluri.dispose();
        }
    }

    private boolean isFormValid() {
        if (frmAddRoluri.getTxtNume().getText().length() < 3) {
            JOptionPane.showMessageDialog(frmAdministrareRoluri, "Numele rolului trebuie sa aiba minim 2 caractere.");
            return false;
        }
        return true;
    }

}
