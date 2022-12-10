package helpers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TableHelpers {
    public static void centerColumn(JTable table, int columnIndex) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
    }
}
