/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderers;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author gigi_
 */
public class CellRendererImage implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        table.setRowHeight(70);
        if (column == 4) {
            TableColumn tb = table.getColumnModel().getColumn(column);
            tb.setMaxWidth(100);
            tb.setMinWidth(100);
            JLabel imageLabel = new JLabel();
            ImageIcon imageicon = new ImageIcon(value.toString());
            Image img = imageicon.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            return imageLabel;
        }
        return (Component) value;
    }
}
