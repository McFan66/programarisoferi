/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import gui.FrmAddUtilizatori;
import gui.FrmAddUtilizatoriRoluri;
import gui.FrmAdministrareUtilizatori;
import gui.FrmAfisareDetaliiUtilizator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Rol;
import models.Utilizator;
import models.UtilizatoriRoluri;
import renderers.ItemRolRenderer;
import renderers.RoluriColorCellRenderer;
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
public class UtilizatorController {

    private FrmAdministrareUtilizatori frmAdministrareUtilizatori;
    private FrmAddUtilizatori frmAddUtilizatori;
    private FrmAddUtilizatoriRoluri frmAddUtilizatoriRoluri;
    private FrmAfisareDetaliiUtilizator frmAfisareDetaliiUtilizator;
    private UtilizatoriService utilizatoriService = new UtilizatoriServiceImpl();
    private UtilizatoriRoluriService utilizatoriRoluriService = new UtilizatoriRoluriServiceImpl();
    private RoluriService roluriService = new RoluriServiceImpl();
    private ArrayList<Utilizator> listaUtilizatori;
    private ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluri = new ArrayList<>();
    private ArrayList<UtilizatoriRoluri> listaUtilizatoriRoluriNoua = new ArrayList<>();
    private Utilizator utilizatorSelectat;
    private UtilizatoriRoluri utilizatoriRoluriSelectat;
    private DefaultComboBoxModel<Rol> modelCmbRoluri = new DefaultComboBoxModel<>();

    private String[][] data;
    private String[][] data1;
    private String[][] data2;
    private DefaultTableModel defaultTableModel;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;
    private String[] columnNames = new String[]{"Nume Complet", "Adresa Email", "Rol"};
    private String[] columnNames1 = new String[]{"Rol", "Data inceput", "Data sfarsit", "Status"};
    private String[] columnNames2 = new String[]{"Rol", "Data inceput", "Data sfarsit", "Status"};
    private JTable tblUtilizatori;
    private JTable tblRoluri;

    ArrayList<UtilizatoriRoluri> listaNoua = new ArrayList<>();

    public void actionIndex(JFrame parent) {
        frmAdministrareUtilizatori = new FrmAdministrareUtilizatori(parent, true);
        frmAdministrareUtilizatori.getRdbToate().setSelected(true);
        updateAndSetModelToTable();
        frmAdministrareUtilizatori.setTitle("Administrare utilizatori");
        tblUtilizatori.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    afisareDetaliiUtilizator();
                }
            }
        });
        frmAdministrareUtilizatori.setUtilizatoriController(this);
        frmAdministrareUtilizatori.setLocationRelativeTo(parent);
        frmAdministrareUtilizatori.setVisible(true);
    }

    public void actionCreate(JDialog parent) {
        frmAddUtilizatori = new FrmAddUtilizatori(parent, true);
        frmAddUtilizatori.setUtilizatorController(this);
        this.utilizatorSelectat = null;
        this.listaUtilizatoriRoluriNoua.clear();
        frmAddUtilizatori.setTitle("Adaugare utilizator");
        updateAndSetModelToTableForRoluri();
        frmAddUtilizatori.setLocationRelativeTo(parent);
        frmAddUtilizatori.setVisible(true);
    }

    public void actionAdaugaRol(JDialog parent) {
        frmAddUtilizatoriRoluri = new FrmAddUtilizatoriRoluri(parent, true);
        frmAddUtilizatoriRoluri.setUtilizatorController(this);
        frmAddUtilizatoriRoluri.setTitle("Atribuire rol");
        this.utilizatoriRoluriSelectat = null;
        ArrayList<Rol> listaRoluri = roluriService.getAll();
        frmAddUtilizatoriRoluri.getCmbRol().setRenderer(new ItemRolRenderer());
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();
        frmAddUtilizatoriRoluri.getChooserDataInceput().setMinSelectableDate(azi);
        frmAddUtilizatoriRoluri.getChooserDataSfarsit().setMinSelectableDate(azi);
        modelCmbRoluri.removeAllElements();
        Rol r = new Rol();
        r.setNume("--Selectati rolul--");
        modelCmbRoluri.addElement(r);
        for (Rol rr : listaRoluri) {
            modelCmbRoluri.addElement(rr);
        }
        frmAddUtilizatoriRoluri.getCmbRol().setModel(modelCmbRoluri);
        frmAddUtilizatoriRoluri.setLocationRelativeTo(parent);
        frmAddUtilizatoriRoluri.setVisible(true);
    }

    public void actionEditeazaRol(JDialog parent) {
        int index = tblRoluri.convertRowIndexToModel(tblRoluri.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAddUtilizatori, "Va rugam sa selectati un rol.");
            return;
        }

        UtilizatoriRoluri ur = listaNoua.get(index);
        frmAddUtilizatoriRoluri = new FrmAddUtilizatoriRoluri(frmAddUtilizatori, true);
        frmAddUtilizatoriRoluri.setUtilizatorController(this);
        frmAddUtilizatoriRoluri.setTitle("Editare rol");
        ArrayList<Rol> listaRoluri = roluriService.getAll();
        frmAddUtilizatoriRoluri.getCmbRol().setRenderer(new ItemRolRenderer());
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();
        frmAddUtilizatoriRoluri.getChooserDataInceput().setMinSelectableDate(azi);
        frmAddUtilizatoriRoluri.getChooserDataSfarsit().setMinSelectableDate(azi);
        modelCmbRoluri.removeAllElements();
        Rol r = new Rol();
        r.setNume("--Selectati rolul--");
        modelCmbRoluri.addElement(r);
        for (Rol rr : listaRoluri) {
            modelCmbRoluri.addElement(rr);
        }
        frmAddUtilizatoriRoluri.getCmbRol().setModel(modelCmbRoluri);
        this.utilizatoriRoluriSelectat = ur;
        frmAddUtilizatoriRoluri.getCmbRol().setSelectedItem(ur.getRol());
        frmAddUtilizatoriRoluri.getChooserDataInceput().setDate(ur.getDataInceput());
        if (ur.getDataSfarsit() != null) {
            frmAddUtilizatoriRoluri.getChooserDataSfarsit().setDate(ur.getDataSfarsit());
        }
        frmAddUtilizatoriRoluri.setLocationRelativeTo(frmAddUtilizatori);
        frmAddUtilizatoriRoluri.setVisible(true);
    }

    public void actionDeleteRol() {
        int index = tblRoluri.convertRowIndexToModel(tblRoluri.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAddUtilizatori, "Va rugam sa selectati un rol.");
            return;
        }
        UtilizatoriRoluri ur = listaNoua.get(index);
        if (frmAddUtilizatori.getBtnFinalizeaza().getText() == "Sterge") {
            int raspuns = JOptionPane.showConfirmDialog(frmAddUtilizatori, "Sunteti sigur ca vreti sa stergeti acest rol?", "Stergere rol", JOptionPane.YES_NO_OPTION);
            if (raspuns == JOptionPane.YES_OPTION) {
                listaUtilizatoriRoluriNoua.remove(ur);
                updateAndSetModelToTableForRoluri();
                return;
            } else {
                return;
            }
        }
        if (frmAddUtilizatori.getBtnFinalizeaza().getText() == "Finalizeaza") {
            Calendar c = Calendar.getInstance();
            Date azi = c.getTime();
            if (azi.before(ur.getDataInceput())) {
                JOptionPane.showMessageDialog(frmAddUtilizatori, "Acest rol nu este activ!");
                return;
            } else {
                if (utilizatoriRoluriSelectat == null) {
                    System.out.println("e null");
                } else {
                    utilizatoriRoluriSelectat.setDataSfarsit(azi);
                    utilizatoriRoluriService.adaugaUtilizatoriRoluri(utilizatoriRoluriSelectat);
                    updateAndSetModelToTableForRoluri();
                }
            }
        }
    }

    public void adaugaRol() {
        UtilizatoriRoluri ur = new UtilizatoriRoluri();
        if (utilizatoriRoluriSelectat != null) {
            ur = utilizatoriRoluriSelectat;
        }
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();

        if (frmAddUtilizatoriRoluri.getChooserDataInceput().getDate() != null) {
            ur.setDataInceput(frmAddUtilizatoriRoluri.getChooserDataInceput().getDate());
        } else {
            ur.setDataInceput(azi);
        }
        if (frmAddUtilizatoriRoluri.getChooserDataSfarsit().getDate() != null) {
            ur.setDataSfarsit(frmAddUtilizatoriRoluri.getChooserDataSfarsit().getDate());
        }
        Rol rolSelectat = (Rol) frmAddUtilizatoriRoluri.getCmbRol().getSelectedItem();
        ur.setIdRol(rolSelectat.getId());
        ur.setRol(rolSelectat);
        if (utilizatoriRoluriSelectat != null) {
            ur.setEditat(true);
            updateAndSetModelToTableForRoluri();
            frmAddUtilizatoriRoluri.dispose();
        } else {
            listaUtilizatoriRoluriNoua.add(0, ur);
            updateAndSetModelToTableForRoluri();
            frmAddUtilizatoriRoluri.dispose();
        }
    }

    public void actionEdit(JDialog parent) {
        int index = tblUtilizatori.convertRowIndexToModel(tblUtilizatori.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareUtilizatori, "Va rugam selectati un utilizator.");
            return;
        }
        this.listaUtilizatoriRoluriNoua.clear();
        if (frmAdministrareUtilizatori.getRdbToate().isSelected()) {
            listaUtilizatori = utilizatoriService.getAll();
        } else if (frmAdministrareUtilizatori.getRdbActiv().isSelected()) {
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(true);
        } else if (frmAdministrareUtilizatori.getRdbInactiv().isSelected()) {
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(false);
        }
        Utilizator u = listaUtilizatori.get(index);
        frmAddUtilizatori = new FrmAddUtilizatori(parent, true);
        frmAddUtilizatori.setTitle("Editare utilizator");
        this.utilizatorSelectat = u;
        frmAddUtilizatori.getTxtNume().setText(u.getNume());
        frmAddUtilizatori.getTxtPrenume().setText(u.getPrenume());
        frmAddUtilizatori.getTxtEmail().setText(u.getEmail());
        frmAddUtilizatori.setUtilizatorController(this);
        updateAndSetModelToTableForRoluri();
        frmAddUtilizatori.setLocationRelativeTo(parent);
        frmAddUtilizatori.setVisible(true);
    }

    public void actionDelete() {
        int index = tblUtilizatori.convertRowIndexToModel(tblUtilizatori.getSelectedRow());
        if (index == -1) {
            JOptionPane.showMessageDialog(frmAdministrareUtilizatori, "Va rugam selectati un utilizator.");
            return;
        }
        if (frmAdministrareUtilizatori.getRdbToate().isSelected()) {
            listaUtilizatori = utilizatoriService.getAll();
        } else if (frmAdministrareUtilizatori.getRdbActiv().isSelected()) {
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(true);
        } else if (frmAdministrareUtilizatori.getRdbInactiv().isSelected()) {
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

    private void updateAndSetModelToTableForRoluri() {
        tblRoluri = frmAddUtilizatori.getTblRoluri();
        listaNoua.clear();
        if (utilizatorSelectat != null) {
            listaNoua.addAll(listaUtilizatoriRoluriNoua);
            listaNoua.addAll(utilizatoriRoluriService.getUtilizatoriRoluriActiveAndUpcomingByUtilizator(utilizatorSelectat));
        } else {
            listaNoua.addAll(listaUtilizatoriRoluriNoua);
        }
        data1 = new String[listaNoua.size()][4];
        int x = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();

        for (UtilizatoriRoluri ur : listaNoua) {
            data1[x][0] = ur.getRol().getNume();
            data1[x][1] = formatter.format(ur.getDataInceput());
            if (ur.getDataSfarsit() != null) {
                data1[x][2] = formatter.format(ur.getDataSfarsit());
            } else {
                data1[x][2] = "Nedefinit";
            }
            if (ur.isEditat()) {
                data1[x][3] = "Editat";
            } else if (x < listaUtilizatoriRoluriNoua.size()) {
                data1[x][3] = "Nesalvat";
            } else if ((ur.getDataSfarsit() == null || ur.getDataSfarsit().compareTo(azi) >= 0) && ur.getDataInceput().compareTo(azi) <= 0) {
                data1[x][3] = "Activ";
            } else {
                data1[x][3] = "Inactiv";
            }
            x++;
        }
        defaultTableModel1 = new DefaultTableModel(data1, columnNames1);
        tblRoluri.setModel(defaultTableModel1);
        tblRoluri.getColumnModel().getColumn(3).setCellRenderer(new RoluriColorCellRenderer());
    }

    public void rolSelected() {
        int index = tblRoluri.convertRowIndexToModel(tblRoluri.getSelectedRow());
        UtilizatoriRoluri ur = listaNoua.get(index);

        if (tblRoluri.getModel().getValueAt(index, 3) == "Nesalvat") {
            frmAddUtilizatori.getBtnFinalizeaza().setText("Sterge");
            frmAddUtilizatori.getBtnFinalizeaza().setEnabled(true);
        } else if (tblRoluri.getModel().getValueAt(index, 3) == "Inactiv") {
            frmAddUtilizatori.getBtnFinalizeaza().setText("Finalizeaza");
            frmAddUtilizatori.getBtnFinalizeaza().setEnabled(false);
        } else if (tblRoluri.getModel().getValueAt(index, 3) == "Activ") {
            frmAddUtilizatori.getBtnFinalizeaza().setText("Finalizeaza");
            frmAddUtilizatori.getBtnFinalizeaza().setEnabled(true);
        }
    }

    private void afisareDetaliiUtilizator() {
        utilizatorSelectat = listaUtilizatori.get(tblUtilizatori.getSelectedRow());
        frmAfisareDetaliiUtilizator = new FrmAfisareDetaliiUtilizator(frmAdministrareUtilizatori, true);
        frmAfisareDetaliiUtilizator.setTitle("Detalii utilizator");
        frmAfisareDetaliiUtilizator.getLblNume().setText(utilizatorSelectat.getNume());
        frmAfisareDetaliiUtilizator.getLblPrenume().setText(utilizatorSelectat.getPrenume());
        frmAfisareDetaliiUtilizator.getLblEmail().setText(utilizatorSelectat.getEmail());

        listaUtilizatoriRoluri = utilizatoriRoluriService.getUtilizatoriRoluriByUtilizator(utilizatorSelectat);
        data2 = new String[listaUtilizatoriRoluri.size()][4];
        int x = listaUtilizatoriRoluri.size() - 1;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        Date azi = c.getTime();
        for (UtilizatoriRoluri ur : listaUtilizatoriRoluri) {
            data2[x][0] = ur.getRol().getNume();
            data2[x][1] = formatter.format(ur.getDataInceput());
            if (ur.getDataSfarsit() != null) {
                data2[x][2] = formatter.format(ur.getDataSfarsit());
            } else {
                data2[x][2] = "Nedefinit";
            }
            if ((ur.getDataSfarsit() == null || ur.getDataSfarsit().compareTo(azi) >= 0) && ur.getDataInceput().compareTo(azi) <= 0) {
                data2[x][3] = "Activ";
            } else {
                data2[x][3] = "Inactiv";
            }
            x--;
        }
        defaultTableModel2 = new DefaultTableModel(data2, columnNames2);
        frmAfisareDetaliiUtilizator.getTblRoluri().setModel(defaultTableModel2);
        frmAfisareDetaliiUtilizator.getTblRoluri().getColumnModel().getColumn(3).setCellRenderer(new RoluriColorCellRenderer());
        frmAfisareDetaliiUtilizator.setLocationRelativeTo(frmAdministrareUtilizatori);
        frmAfisareDetaliiUtilizator.setVisible(true);
    }

    public void updateAndSetModelToTable() {
        tblUtilizatori = frmAdministrareUtilizatori.getTblUtilizatori();
        if (frmAdministrareUtilizatori.getRdbToate().isSelected()) {
            listaUtilizatori = utilizatoriService.getAll();
            frmAdministrareUtilizatori.getBtnSterge().setText("Dezactiveaza");
            frmAdministrareUtilizatori.getBtnEditeaza().setEnabled(true);
        } else if (frmAdministrareUtilizatori.getRdbActiv().isSelected()) {
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(true);
            frmAdministrareUtilizatori.getBtnSterge().setText("Dezactiveaza");
            frmAdministrareUtilizatori.getBtnEditeaza().setEnabled(true);
        } else if (frmAdministrareUtilizatori.getRdbInactiv().isSelected()) {
            listaUtilizatori = utilizatoriService.getUtilizatoriByValid(false);
            frmAdministrareUtilizatori.getBtnSterge().setText("Activeaza");
            frmAdministrareUtilizatori.getBtnEditeaza().setEnabled(false);
        }

        data = new String[listaUtilizatori.size()][3];
        int x = 0;
        for (Utilizator u : listaUtilizatori) {
            data[x][0] = u.getNumeComplet();
            data[x][1] = u.getEmail();
            data[x][2] = utilizatoriService.getListaRoluriActive(u).toString();
            x++;
        }
        defaultTableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblUtilizatori.setModel(defaultTableModel);
    }

    public void itemSelected() {
        int index = tblUtilizatori.convertRowIndexToModel(tblUtilizatori.getSelectedRow());
        listaUtilizatori = utilizatoriService.getAll();
        Utilizator u = listaUtilizatori.get(index);
        if (u.isValid()) {
            frmAdministrareUtilizatori.getBtnSterge().setText("Dezactiveaza");
            frmAdministrareUtilizatori.getBtnEditeaza().setEnabled(true);
        } else if (!u.isValid()) {
            frmAdministrareUtilizatori.getBtnSterge().setText("Activeaza");
            frmAdministrareUtilizatori.getBtnEditeaza().setEnabled(false);
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
            for (UtilizatoriRoluri ur : listaNoua) {
                ur.setEditat(false);
                ur.setIdUtilizator(utilizatorSelectat.getId());
                utilizatoriRoluriService.adaugaUtilizatoriRoluri(ur);
            }
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
        if (utilizatorSelectat == null) {
            if (new String(frmAddUtilizatori.getTxtParola().getPassword()).length() < 3) {
                JOptionPane.showMessageDialog(frmAddUtilizatori, "Parola trebie sa aiba minim 3 caractere.");
                return false;
            }
            String parola = new String(frmAddUtilizatori.getTxtParola().getPassword());
            String confirmaParola = new String(frmAddUtilizatori.getTxtConfirmaParola().getPassword());
            if (!parola.equals(confirmaParola)) {
                JOptionPane.showMessageDialog(frmAddUtilizatori, "Parolele nu se potrivesc.");
                return false;
            }
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
