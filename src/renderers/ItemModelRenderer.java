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
import models.Model;

/**
 *
 * @author Stefan
 */
public class ItemModelRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Model){
           Model m=(Model)value;
           setText(m.getNume());
           
           if(isSelected) {
               setBackground(Color.cyan);
           }
        }
        return this;
    }
    
}
