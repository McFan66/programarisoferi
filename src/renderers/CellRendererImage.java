/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import gui.ImageAvatar;
import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Vlad
 */
public class CellRendererImage implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        if (column == 1) {
            TableColumn tb = table.getColumnModel().getColumn(column);
            ImageAvatar imageAvatar = new ImageAvatar();
            ImageIcon imageicon = new ImageIcon(value.toString());
            Image img = imageicon.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
            imageAvatar.setIcon(new ImageIcon(img));
            imageAvatar.setBorderSize(3);
            return imageAvatar;
        }
        return (Component) value;
    }
}
