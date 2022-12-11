package models.tablemodels;

import models.JobPosting;

public class JobPostingsTableModel extends BaseTableModel<JobPosting> {
    public JobPostingsTableModel() {
        super(new String[]{"Id", "Title", "Description", "Category", "[Apply]"});
    }

    @Override
    public Object[] rowMapping(JobPosting item) {
        return new Object[]{
                item.getId(),
                item.getTitle(),
                item.getJobDescription(),
                item.getCategory(),
                "✖",
                "✖"};
    }
}

