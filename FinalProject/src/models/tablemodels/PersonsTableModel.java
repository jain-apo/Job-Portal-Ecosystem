package models.tablemodels;

public class PersonsTableModel extends BaseTableModel {
    public PersonsTableModel() {
        super(new String[]{"Id", "Full Name", "Date of Birth", "[edit]", "[delete]"});
    }
}
