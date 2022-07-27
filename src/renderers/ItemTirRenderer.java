/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import models.Tir;

/**
 *
 * @author Vlad
 */
public class ItemTirRenderer extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Tir) {
            Tir t = (Tir) value;
            
                setText(String.format("%s %s %s", t.getModel().getMarca().getNume() , t.getModel().getNume() , t.getNrInmatriculare()));
        }
        return this;
    }
    
}
