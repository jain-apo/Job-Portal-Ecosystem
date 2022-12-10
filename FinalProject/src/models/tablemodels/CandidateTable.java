package models.tablemodels;

public class CandidateTable extends BaseTableModel {
    public CandidateTable() {
        super(new String[]{"Id", "Full Name", "Date of Birth", "Role", "[edit]", "[delete]"});
    }
}
