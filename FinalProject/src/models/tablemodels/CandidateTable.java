package models.tablemodels;

import models.JobCandidate;

public class CandidateTable extends BaseTableModel<JobCandidate> {
    public CandidateTable() {
        super(new String[]{"Id", "Full Name", "Date of Birth", "[profile]"});
    }

    @Override
    public Object[] rowMapping(JobCandidate item) {
        return new Object[]{
                item.getId(),
//                item.getFullName(),
//                DateHelper.formatDate(item.getDateOfBirth(), "MMM-dd yyyy"),
//                "✖",
//                "✖"
        };
    }
}
