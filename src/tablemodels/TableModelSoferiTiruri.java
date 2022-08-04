/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import models.SoferiTiruri;

/**
 *
 * @author Vlad
 */
public class TableModelSoferiTiruri extends AbstractTableModel{

    private String[] columnNames = new String[] {"ID" , "SOFER" , "TIR", "ACTIV", "IN CURSA"};
    private ArrayList<SoferiTiruri> listaSoferiTiruri;
    
    public TableModelSoferiTiruri() {
        this.listaSoferiTiruri = new ArrayList<>();
    }
    
    public TableModelSoferiTiruri(ArrayList<SoferiTiruri> listaSoferiTiruri) {
        this.listaSoferiTiruri = listaSoferiTiruri;
    }
    
    @Override
    public int getRowCount() {
        return listaSoferiTiruri.size();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SoferiTiruri soferiTiruri = listaSoferiTiruri.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return soferiTiruri.getId();
            case 1:
                return soferiTiruri.getSofer().getNume();
            case 2:
                return soferiTiruri.getTir().getNrInmatriculare();
            case 3:
                return soferiTiruri.isValid() ? "DA" : "NU";
            case 4:
                return soferiTiruri.isInCursa() ? "DA" : "NU";
            default:
                return "Nespecificat";
        }
    }

    public ArrayList<SoferiTiruri> getListaSoferiTiruri() {
        return listaSoferiTiruri;
    }

    public void setListaSoferiTiruri(ArrayList<SoferiTiruri> listaSoferiTiruri) {
        this.listaSoferiTiruri = listaSoferiTiruri;
        fireTableDataChanged();
    }
    
    public SoferiTiruri getSoferTirByIndex(int rowIndex) {
        return listaSoferiTiruri.get(rowIndex);
    }
    
    public void AddSoferTir(SoferiTiruri soferiTiruri) {
        int index = listaSoferiTiruri.indexOf(soferiTiruri);
        
        if(index == -1) {
            listaSoferiTiruri.add(soferiTiruri);
        } else {
            listaSoferiTiruri.set(index, soferiTiruri);
        }
        fireTableDataChanged();
    }
    
}
