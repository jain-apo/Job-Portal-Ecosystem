package models.tablemodels;

import javax.swing.table.DefaultTableModel;

public class PersonsTableModel extends DefaultTableModel {
    public PersonsTableModel() {
        super(new Object[][]{}, new String[]{"Id", "Full Name", "Date of Birth", "[edit]", "[delete]"});
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
}
