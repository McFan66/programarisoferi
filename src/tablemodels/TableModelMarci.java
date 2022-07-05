/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Marca;

/**
 *
 * @author Vlad
 */
public class TableModelMarci extends AbstractTableModel{

    private List<Marca> listaMarci;
    private String[] columnNames = new String[]{"ID" , "NUME" , "ACTIVA"};

    public TableModelMarci() {
        this.listaMarci = new ArrayList<>();
    }
    
    public TableModelMarci(List<Marca> listaMarci) {
        this.listaMarci = listaMarci;
    }
       
    @Override
    public int getRowCount() {
        return listaMarci.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column]; 
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(listaMarci.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }
        
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Marca marca = listaMarci.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return marca.getId();
            case 1:
                return marca.getNume();
            case 2:
                if(marca.getActiva() == 1) {
                    return "DA";
                }
                if(marca.getActiva() == 0) {
                    return "NU";
                }
            default:
                return "nespecificat";
        }
    }

    public List<Marca> getListaMarci() {
        return listaMarci;
    }

    public void setListaMarci(List<Marca> listaMarci) {
        this.listaMarci = listaMarci;
        fireTableDataChanged();
    }
    
    public Marca getMarcaByIndex(int rowIndex) {
        return listaMarci.get(rowIndex);
    }
    
    public void addMarca(Marca marca) {
        int index = listaMarci.indexOf(marca);
        
        if(index == -1) {
            listaMarci.add(marca);
        } else {
            listaMarci.set(index, marca);
        }
        fireTableDataChanged();
    }
    
}
