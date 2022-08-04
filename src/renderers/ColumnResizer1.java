/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Admin Retea
 */
public class ColumnResizer1 {

    /**
     * Metoda care face refresh datelor dintr-un obiect JTable
     * @param tabel
     */
    public static void refreshTableData(JTable tabel) {
        tabel.validate();
        final JTable fTable = tabel;
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                adjustColumnPreferredWidths(fTable);
                fTable.revalidate();
            }
        });
        tabel.removeRowSelectionInterval(0, tabel.getRowCount() - 1);
        tabel.transferFocus();
    }

    /**
     * Metoda care ajusteaza dimensiunea coloanelor in functie de continut in JTable
     * @param table
     */
    public static void adjustColumnPreferredWidths(JTable table) {
        // strategy - get max width for cells in column and
        // make that the preferred width
        TableColumnModel columnModel = table.getColumnModel();
        for (int col = 0; col < table.getColumnCount(); col++) {

            int maxwidth = 0;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer rend =
                        table.getCellRenderer(row, col);
                Object value = table.getValueAt(row, col);
                Component comp =
                        rend.getTableCellRendererComponent(table,
                        value,
                        false,
                        false,
                        row,
                        col);
                maxwidth = Math.max(comp.getPreferredSize().width, maxwidth);
            } // for row
            TableColumn column = columnModel.getColumn(col);
            TableCellRenderer headerRenderer = column.getHeaderRenderer();
            if (headerRenderer == null) {
                headerRenderer = table.getTableHeader().getDefaultRenderer();
            }
            Object headerValue = column.getHeaderValue();
            Component headerComp =
                    headerRenderer.getTableCellRendererComponent(table,
                    headerValue,
                    false,
                    false,
                    0,
                    col);
            maxwidth = Math.max(maxwidth,
                    headerComp.getPreferredSize().width);
            column.setPreferredWidth(maxwidth + 20);

        } // for col
    }

    public static void updateRowHeights(JTable table) {


        try {
            for (int row = 0; row < table.getRowCount(); row++) {
                int rowHeight = table.getRowHeight();

                for (int column = 0; column < table.getColumnCount(); column++) {
                    Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }

                table.setRowHeight(row, rowHeight);
            }
        } catch (ClassCastException e) {
        }
    }

    public static void resizeRowHeightAndColumnsWidth(JTable table) {
        adjustColumnPreferredWidths(table);
        updateRowHeights(table);
    }
}