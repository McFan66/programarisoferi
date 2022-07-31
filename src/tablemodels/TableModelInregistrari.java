/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import models.Inregistrare;

/**
 *
 * @author Vlad
 */
public class TableModelInregistrari extends AbstractTableModel{
    
    private String[] columnNames = new String[] {"Data plecare" , "Data sosire" , "Sofer" , "Tir"};
    private ArrayList<Inregistrare> listaInregistrari;
    
    public TableModelInregistrari() {
        this.listaInregistrari = new ArrayList();
    }
    
    public TableModelInregistrari(ArrayList<Inregistrare> listaInregistrari) {
        this.listaInregistrari = listaInregistrari;
    }
    
    @Override
    public int getRowCount() {
        return listaInregistrari.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setListaInregistrari(ArrayList<Inregistrare> listaInregistrari) {
        this.listaInregistrari = listaInregistrari;
        fireTableDataChanged();
    }

    public ArrayList<Inregistrare> getListaInregistrari() {
        return listaInregistrari;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inregistrare i = listaInregistrari.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        switch(columnIndex) {
            case 0:
                return sdf.format(i.getDataPlecare());
            case 1:
                return sdf.format(i.getDataSosire());
            case 2:
                return i.getSoferTir().getSofer().getNume();
            case 3:
                return i.getSoferTir().getTir().getNrInmatriculare();
            default:
                return "Nespecificat";
        }
    }
    
    public Inregistrare getInregistrareByIndex(int rowIndex) {
        return listaInregistrari.get(rowIndex);
    }
    
    public void addInregistrare(Inregistrare i) {
        int index = listaInregistrari.indexOf(i);
        if(index == -1) {
            listaInregistrari.add(i);
        } else {
            listaInregistrari.set(index, i);
        }
        fireTableDataChanged();
    }
    
}
