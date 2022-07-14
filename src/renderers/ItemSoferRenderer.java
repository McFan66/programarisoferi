/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import models.Sofer;

/**
 *
 * @author Vlad
 */
public class ItemSoferRenderer extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Sofer) {
            Sofer s = (Sofer) value;
            
                setText(String.format("%s %s", s.getNume() , s.getPrenume()));
        }
        return this;
    }
    
}
