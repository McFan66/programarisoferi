/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Tir;

public class TabelModelTiruri extends AbstractTableModel {

    private List<Tir> listaTiruri;
    private String[] columnNames = new String[]{"Id" , "Marca" , "Model" , "Nr Inmatriculare"}; //, "Poze"};

    public TabelModelTiruri() {
        listaTiruri = new ArrayList<>();
    }

    public TabelModelTiruri(List<Tir> listaTiruri) {
        this.listaTiruri = listaTiruri;
    }
    
    @Override
    public int getRowCount() {
        return listaTiruri.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tir tir = listaTiruri.get(rowIndex);
        
        switch(columnIndex) {
            case 0:
                return tir.getId();
            case 1:
                return tir.getModel().getMarca();
            case 2:
                return tir.getModel();
            case 3:
                return tir.getNrInmatriculare();
//            case 4:
//               return tir.getPoze().get(0);
            default:
                return "Nespecificat";
        }
    }
    
    public Tir getTirAtIndex(int rowIndex) {
        return listaTiruri.get(rowIndex);
    }

    public List<Tir> getListaTiruri() {
        return listaTiruri;
    }

    public void setListaTiruri(List<Tir> listaTiruri) {
        this.listaTiruri = listaTiruri;
        fireTableDataChanged();
    }

    public void addTir(Tir tir) {
        int index = listaTiruri.indexOf(tir);
        
        if(index == -1) {
            listaTiruri.add(tir);
        } else {
            listaTiruri.set(index, tir);
        }
        fireTableDataChanged();
    }
}
