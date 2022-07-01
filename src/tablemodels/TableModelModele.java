/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Model;

/**
 *
 * @author Vlad
 */
public class TableModelModele extends AbstractTableModel{

    private List<Model> listaModele;
    private String[] columnNames = new String[] {"ID" , "NUME" , "MARCA" , "ACTIV"}; 

    public TableModelModele() {
        this.listaModele = new ArrayList<>();
    }
    
    public TableModelModele(List<Model> listaModele) {
        this.listaModele = listaModele;
    }
    
    @Override
    public int getRowCount() {
        return listaModele.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Model model = listaModele.get(rowIndex);
      
      switch(columnIndex) {
          case 0:
              return model.getId();
          case 1:
              return model.getNume();
          case 2:
              return model.getMarca().getNume();
          case 3:
              return model.isActiv() ? "DA" : "NU";
          default:
              return "Nespecificat";
      }
    }

    public List<Model> getListaModele() {
        return listaModele;
    }

    public void setListaModele(List<Model> listaModele) {
        this.listaModele = listaModele;
        fireTableDataChanged();
    }
    
    public Model getModelByIndex(int rowIndex) {
        return listaModele.get(rowIndex);
    }
    
    public void addModel(Model model) {
        
        int index = listaModele.indexOf(model);
        
        if(index == -1) {
            listaModele.add(model);
        } else {
            listaModele.set(index, model);
        }
        fireTableDataChanged();
    }
    
}
