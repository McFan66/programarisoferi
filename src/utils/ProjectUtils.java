/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Stefan
 */
public class ProjectUtils {

    public static void autoreSizeTable(JTable table, int column) {
        //   for (int column = 0; column < table.getColumnCount(); column++) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        int preferredWidth = tableColumn.getMinWidth();
        int maxWidth = tableColumn.getMaxWidth();

        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
            Component c = table.prepareRenderer(cellRenderer, row, column);
            int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
            preferredWidth = Math.max(preferredWidth, width);

            //  We've exceeded the maximum width, no need to check other rows
            if (preferredWidth >= maxWidth) {
                preferredWidth = maxWidth;
                break;
            }
        }
        tableColumn.setPreferredWidth(preferredWidth);
        // }
    }

    public static void resizeColumnWidth(JTable table) {

        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public static void tableColumnAdjuster(JTable table) {
        for (int column = 0; column < table.getColumnCount(); column++) {
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Component c = table.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setPreferredWidth(preferredWidth);
        }
    }

    public static void tableColumnAdjusterByHeader(JTable jTable1) {
        for (int column = 0; column < jTable1.getColumnCount(); column++) {
            TableColumn tableColumn = jTable1.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = 0;
            TableCellRenderer rend = jTable1.getTableHeader().getDefaultRenderer();
            TableCellRenderer rendCol = tableColumn.getHeaderRenderer();
            if (rendCol == null) {
                rendCol = rend;
            }
            Component header = rendCol.getTableCellRendererComponent(jTable1, tableColumn.getHeaderValue(), false, false, 0, column);
            maxWidth = header.getPreferredSize().width;
         //   System.out.println("maxWidth :" + maxWidth);

            for (int row = 0; row < jTable1.getRowCount(); row++) {
                TableCellRenderer cellRenderer = jTable1.getCellRenderer(row, column);
                Component c = jTable1.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + jTable1.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);
               // System.out.println("preferredWidth :" + preferredWidth);
            //    System.out.println("Width :" + width);

                //  We've exceeded the maximum width, no need to check other rows
                if (preferredWidth <= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }
    }
}
