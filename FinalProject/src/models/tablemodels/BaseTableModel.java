package models.tablemodels;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTableModel<T> extends DefaultTableModel {
    private final List<T> data;

    public BaseTableModel(String[] columnNames) {
        super(new Object[][]{}, columnNames);

        data = new ArrayList<T>();
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    public abstract Object[] rowMapping(T item);

    public BaseTableModel loadData(List<T> items) {
        for (T item : items) {
            addRow(rowMapping(item));
            data.add(item);
        }
        return this;
    }

    public T getDataAt(int row) {
        return data.get(row);
    }
}

