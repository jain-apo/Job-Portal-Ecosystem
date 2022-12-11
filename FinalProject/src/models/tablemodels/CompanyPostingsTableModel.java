package models.tablemodels;

import models.JobPosting;

public class CompanyPostingsTableModel extends BaseTableModel<JobPosting> {
    public CompanyPostingsTableModel() {
        super(new String[]{"Id", "Title", "Description", "Category"});
    }

    @Override
    public Object[] rowMapping(JobPosting item) {
        return new Object[]{
                item.getId(),
                item.getTitle(),
                item.getJobDescription(),
                item.getCategory(),
        };
    }
}
