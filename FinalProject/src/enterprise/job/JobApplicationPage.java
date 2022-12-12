package enterprise.job;

import domain.Application;
import models.JobApplication;
import models.JobPosting;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class JobApplicationPage extends BaseFrame {
    private JPanel mainPane;
    private JLabel heading;
    private JTextField yearsOfExperience;
    private JButton browseButton;
    private JButton applyButton;
    private JobPosting jobPosting;

    JobApplicationPage(JobPosting jobPosting) {
        super();
        this.jobPosting = jobPosting;

        setupPageStuff();
        setContentPane(mainPane);
        applyButton.addActionListener(e -> applyForJob());
    }

    private boolean validateFields() {
        // todo validation
        return true;
    }

    private void applyForJob() {
        if (!validateFields()) return;

        int years = Integer.parseInt(yearsOfExperience.getText());
        String resumeFile = ""; // todo resume logic

        var app = new JobApplication(0, Application.getCurrentlyLoggedInPerson().getId(), jobPosting.getId(), years, resumeFile);

        try {
            Application.Database.JobApplications.add(app);
            Dialog.show("Job application successful");
            dispose();
        } catch (SQLException e) {
            Dialog.error("You can't apply for this job.", "Not Allowed");
        }
    }

    private void setupPageStuff() {
        heading.setText("Application for " + jobPosting.getTitle());
    }
}
