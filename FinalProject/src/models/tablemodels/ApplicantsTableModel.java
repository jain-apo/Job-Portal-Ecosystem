package models.tablemodels;

import models.JobApplication;

public class ApplicantsTableModel extends BaseTableModel<JobApplication> {
    public ApplicantsTableModel() {
        super(new String[]{"Name", "Job Posting", "Years of Experience", "[Resume]", "[Move as Candidate]"});
    }

    @Override
    public Object[] rowMapping(JobApplication item) {
        try {
            return new Object[]{
                    item.getPerson().getFullName(),
                    item.getJobPosting().getTitle(),
                    item.getYearsOfExperience(),
                    "✖", "✖"
            };
        } catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
        return new Object[]{};
    }
}
