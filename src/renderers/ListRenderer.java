/*
 * To change this template, choose Tools | Templates
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
 * @author Radu Maria
 */
public class ListRenderer extends DefaultListCellRenderer {

    private Color defaultForegroundColor = getForeground();

    /**
     * Metoda returneaza numele categoriei din JComboBox
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return numele Categoriei
     */
    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        //daca obiectul din comboBox este de tipul CategorieClient
        if (value instanceof Marca) {

            Marca m = (Marca) value;
            setText(m.getNume());
//            //setam valoarea afisata in combobox ca fiind numele categoriei
//            if (c.getNivel() != 0) {
//                setText(HelpService.buildLevelString(c.getNivel()) + c.getDenumire());
//            } else {
//                setText(c.getDenumire());
//            }
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
