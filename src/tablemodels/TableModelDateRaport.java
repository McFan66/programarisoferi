/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodels;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import models.DateRaport;

/**
 *
 * @author Vlad Apostol
 */
public class TableModelDateRaport extends AbstractTableModel{

    private ArrayList<DateRaport> listaDateRaport;
    private String[] columnNames = {"ID" , "NUME RAPORT" , "UTILIZATOR" , "DATA SUBMIT" , "DATA GENERARE" , "STARE" , "LOCATIE"};
    
    public TableModelDateRaport() {
        this.listaDateRaport = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return listaDateRaport.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    public void setListaDateRaport(ArrayList<DateRaport> listaDateRaport) {
        this.listaDateRaport = listaDateRaport;
        fireTableDataChanged();
    }

    public ArrayList<DateRaport> getListaDateRaport() {
        return listaDateRaport;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateRaport dateRaport = listaDateRaport.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dateRaport.getId();
            case 1:
                return dateRaport.getNumeRaport();
            case 2:
                return dateRaport.getUtilizator().getEmail();
            case 3:
                return dateRaport.getDataSubmit();
            case 4:
                return dateRaport.getDataGenerare();
            case 5:
                return dateRaport.getStare();
            case 6:
                return dateRaport.getReportPath();
            default:
                return "Nespecificat";
        }
        
    }
    
}
