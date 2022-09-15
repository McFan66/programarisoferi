/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import models.Sofer;

/**
 *
 * @author Vlad
 */
public class ItemSoferRenderer extends DefaultListCellRenderer{
private Color defaultForegroundColor=getForeground();
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Sofer) {
            Sofer s = (Sofer) value;
            
                setText(String.format("%s %s", s.getNume() , s.getPrenume()));
                
                if(isSelected) {
                    setBackground(new Color(25,116,211));
                    setForeground(Color.white);
                }
                else{
                    setBackground(Color.white);
                    setForeground(defaultForegroundColor);
                }
        }
        return this;
    }
    
}
