package models.tablemodels;

import models.JobPosting;

public class MyApplicationModel extends BaseTableModel<JobPosting> {
    public MyApplicationModel() {
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

