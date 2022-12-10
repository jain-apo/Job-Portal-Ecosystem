package models.tablemodels;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class BaseTableModel<T> extends DefaultTableModel {
    public BaseTableModel(String[] columnNames) {
        super(new Object[][]{}, columnNames);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public abstract Object[] rowMapping(T item);

    public void loadData(List<T> items) {
        for (T item : items) {
            addRow(rowMapping(item));
        }
    }
}

