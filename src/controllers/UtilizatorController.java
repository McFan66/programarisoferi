/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddUtilizatori;
import gui.FrmAdministrareUtilizatori;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Utilizator;
import services.UtilizatoriService;
import services.UtilizatoriServiceImpl;

/**
 *
 * @author Stefan
 */
public class UtilizatorController {

    private FrmAdministrareUtilizatori frmAdministrareUtilizatori;
    private FrmAddUtilizatori frmAddUtilizatori;
    private UtilizatoriService utilizatoriService = new UtilizatoriServiceImpl();
    private ArrayList<Utilizator> listaUtilizatori;
    private Utilizator utilizatorSelectat;

    private String[][] data;
    private DefaultTableModel defaultTableModel;
    private String[] columnNames = new String[]{"Nume Complet", "Adresa Email", "Rol"};
    private JTable tblUtilizatori;

    public void actionIndex(JFrame parent) {
        frmAdministrareUtilizatori = new FrmAdministrareUtilizatori(parent, true);
        frmAdministrareUtilizatori.getRdbToate().setSelected(true);
        updateAndSetModelToTable();
        frmAdministrareUtilizatori.setUtilizatoriController(this);
        frmAdministrareUtilizatori.setLocationRelativeTo(parent);
        frmAdministrareUtilizatori.setVisible(true);
    }

    public void actionCreate(JDialog parent) {
        frmAddUtilizatori = new FrmAddUtilizatori(parent, true);
        frmAddUtilizatori.setUtilizatorController(this);
        this.utilizatorSelectat = null;
        frmAddUtilizatori.setLocationRelativeTo(parent);
        frmAddUtilizatori.setVisible(true);
    }

    public void actionEdit(JDialog parent) {
        int index = tblUtilizatori.convertRowIndexToModel(tblUtilizatori.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareUtilizatori, "Va rugam selectati un utilizator.");
            return;
        }
        if (frmAdministrareUtilizatori.getRdbToate().isSelected()){
            listaUtilizatori = utilizatoriService.getAll();
        } else if (frmAdministrareUtilizatori.getRdbActiv().isSelected()){
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(true);
        } else if (frmAdministrareUtilizatori.getRdbInactiv().isSelected()){
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(false);
        }
        Utilizator u = listaUtilizatori.get(index);
        frmAddUtilizatori = new FrmAddUtilizatori(parent, true);
        this.utilizatorSelectat = u;
        frmAddUtilizatori.getTxtNume().setText(u.getNume());
        frmAddUtilizatori.getTxtPrenume().setText(u.getPrenume());
        frmAddUtilizatori.getTxtEmail().setText(u.getEmail());
        frmAddUtilizatori.setUtilizatorController(this);
        frmAddUtilizatori.setLocationRelativeTo(parent);
        frmAddUtilizatori.setVisible(true);
    }

    public void actionDelete() {
        int index = tblUtilizatori.convertRowIndexToModel(tblUtilizatori.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareUtilizatori, "Va rugam selectati un utilizator.");
            return;
        }
        if (frmAdministrareUtilizatori.getRdbToate().isSelected()){
            listaUtilizatori = utilizatoriService.getAll();
        } else if (frmAdministrareUtilizatori.getRdbActiv().isSelected()){
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(true);
        } else if (frmAdministrareUtilizatori.getRdbInactiv().isSelected()){
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(false);
        }
        Utilizator u = listaUtilizatori.get(index);
        int raspuns = JOptionPane.showConfirmDialog(frmAdministrareUtilizatori, String.format("Sunteti sigur ca doriti sa inactivati utilizatorul %s?", u), "Inactivare utilizator", JOptionPane.YES_NO_OPTION);
        if (raspuns == JOptionPane.YES_OPTION) {
            u.setValid(!u.isValid());
            utilizatoriService.adaugaUtilizator(u);
            updateAndSetModelToTable();
        }
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

        data = new String[listaUtilizatori.size()][3];
        int x = 0;
        for (Utilizator u : listaUtilizatori) {
            data[x][0] = u.getNumeComplet();
            data[x][1] = u.getEmail();
            data[x][2] = utilizatoriService.getRolulUtilizatorului(u).getNume();
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

    public void saveUtilizator() {
        if (isFormValid()) {
            if (utilizatorSelectat == null) {
                utilizatorSelectat = new Utilizator();
                utilizatorSelectat.setValid(true);
            }
            utilizatorSelectat.setNume(frmAddUtilizatori.getTxtNume().getText());
            utilizatorSelectat.setPrenume(frmAddUtilizatori.getTxtPrenume().getText());
            utilizatorSelectat.setEmail(frmAddUtilizatori.getTxtEmail().getText());
            utilizatorSelectat.setParola(new String(frmAddUtilizatori.getTxtParola().getPassword()));
            utilizatorSelectat.setValid(utilizatorSelectat.isValid());

            utilizatoriService.adaugaUtilizator(utilizatorSelectat);
            updateAndSetModelToTable();
            frmAddUtilizatori.dispose();
        }
    }

    private boolean isFormValid() {
        if (frmAddUtilizatori.getTxtNume().getText().length() < 2 || frmAddUtilizatori.getTxtPrenume().getText().length() < 2) {
            JOptionPane.showMessageDialog(frmAddUtilizatori, "Numele si prenumele trebuie sa aiba minim 2 caractere.");
            return false;
        }
        if (!validateEmailAddress(frmAddUtilizatori.getTxtEmail().getText())) {
            JOptionPane.showMessageDialog(frmAddUtilizatori, "Adresa de email trebuie sa fie una valida.");
            return false;
        }
        if (new String(frmAddUtilizatori.getTxtParola().getPassword()).length() < 3) {
            JOptionPane.showMessageDialog(frmAddUtilizatori, "Parola trebie sa aiba minim 3 caractere.");
            return false;
        }
        return true;
    }

    public boolean validateEmailAddress(String emailAddress) {
        Pattern regexPattern;
        Matcher regMatcher;
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);
        return regMatcher.matches();
    }

}
