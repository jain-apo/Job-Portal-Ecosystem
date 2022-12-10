package models.tablemodels;

import enterprise.company.CandidateProfile;

public class CandidateTable extends BaseTableModel<CandidateProfile> {
    public CandidateTable() {
        super(new String[]{"Id", "Full Name", "Date of Birth", "Role", "[edit]", "[delete]"});
    }

    @Override
    public Object[] rowMapping(CandidateProfile item) {
        return new Object[0];
    }
}
