package models.tablemodels;

import domain.Application;
import models.JobApplication;
import models.JobPosting;
import utils.Dialog;

import java.sql.SQLException;
import java.util.HashMap;

public class MyApplicationsTableModel extends BaseTableModel<JobApplication> {

    private final HashMap<Integer, JobPosting> personsCache = new HashMap<>();

    public MyApplicationsTableModel() {
        super(new String[]{"Id", "Title", "Description", "Category", "Result"});

        try {
            Application.Database.JobPostings.getAll().forEach(jobPosting -> {
                personsCache.put(jobPosting.getId(), jobPosting);
            });
        } catch (SQLException e) {
            Dialog.error("Error loading job applications for candidate table");
        }
    }

    @Override
    public Object[] rowMapping(JobApplication item) {

        JobPosting jobPosting = personsCache.get(item.getJobPostingId());

        return new Object[]{
                item.getId(),
                jobPosting.getTitle(),
                jobPosting.getJobDescription(),
                jobPosting.getCategory(),
                "",
                "✖",
                "✖"};
    }
}

