/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Stefan
 */
public class TiruriColorCellRenderer extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
            
            try {
                switch (value.toString()) {
                    case "Disponibil":
                        setBackground(Color.GREEN);
                        break;
                    case "In cursa":
                        setBackground(Color.RED);
                        break;
                    case "In service":
                        setBackground(Color.ORANGE);
                        break;
                    case "Tir test":
                        setBackground(Color.BLUE);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
            }
        }
        
        return this;
    }
    
}
