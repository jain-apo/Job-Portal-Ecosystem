package models.tablemodels;

import javax.swing.table.DefaultTableModel;

public class BaseTableModel extends DefaultTableModel {
    public BaseTableModel(String[] columnNames) {
        super(new Object[][]{}, columnNames);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}

