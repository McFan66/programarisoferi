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
import models.Marca;

/**
 *
 * @author Stefan
 */
public class ItemMarcaRenderer extends DefaultListCellRenderer {

    private Color defaultForegroundColor = getForeground();

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Marca) {
            Marca m = (Marca) value;
            setText(m.getNume());

            if (isSelected) {
                setBackground(new Color(25, 116, 211));
                setForeground(Color.white);
            } else {
                setBackground(Color.white);
                setForeground(defaultForegroundColor);
            }
        }
        return this;
    }

}
