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
import models.SoferiTiruri;

/**
 *
 * @author Vlad
 */
public class ItemSoferiTiruriRenderer extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof SoferiTiruri) {
            SoferiTiruri sf = (SoferiTiruri) value;
            
            setText(sf.getSofer().getNumeComplet() + " - " + sf.getTir().getModel().getMarca().getNume() + " " + sf.getTir().getNrInmatriculare());
            
            if(isSelected) {
                setBackground(Color.CYAN);
            }
        }
        return this;
    }
    
}
