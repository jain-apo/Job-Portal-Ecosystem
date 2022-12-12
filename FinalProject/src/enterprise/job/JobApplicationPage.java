package enterprise.job;

import domain.Application;
import domain.Validator;
import models.JobApplication;
import models.JobPosting;
import utils.Dialog;
import utils.FileUtil;
import utils.ICallback;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobApplicationPage extends BaseFrame {
    private JPanel mainPane;
    private JLabel heading;
    private JTextField yearsOfExperience;
    private JButton browseButton;
    private JButton applyButton;
    private JobPosting jobPosting;
    private ICallback callback;
    private String resumePath;

    JobApplicationPage(JobPosting jobPosting, ICallback callback) {
        super();
        this.jobPosting = jobPosting;
        this.callback = callback;

        setupPageStuff();
        setContentPane(mainPane);
        applyButton.addActionListener(e -> applyForJob());
        browseButton.addActionListener(e -> attachResume());
    }

    private void attachResume() {
        resumePath = FileUtil.pickPdfFile();
    }

    private boolean validateFields() {
        ArrayList<String> validationMessages;

        validationMessages = new ArrayList<String>();

        if (!Validator.checkTextsBlank(new JTextField[]{yearsOfExperience}))
            validationMessages.add("Enter all the mandatory fields");

        int years = 0;

        try {
            years = Integer.parseInt(yearsOfExperience.getText());
        } catch (NumberFormatException e) {
            validationMessages.add("Years of experience must be a number");
        }

        if (years < 0) {
            validationMessages.add("Years of experience must be a positive number");
        }

        if (resumePath == null || resumePath.isEmpty()) {
            validationMessages.add("Please upload a resume");
        }

        if (validationMessages.size() > 0) {
            Dialog.error(String.join("\n\n", validationMessages), "Validation Errors");
            return false;
        }

        return true;
    }

    private void applyForJob() {
        if (!validateFields()) return;

        int years = Integer.parseInt(yearsOfExperience.getText());

        var app = new JobApplication(0, Application.getCurrentlyLoggedInPerson().getId(), jobPosting.getId(), years,
                resumePath);

        try {
            Application.Database.JobApplications.add(app);
            Dialog.show("Job application successful");
            callback.callback();
            dispose();
        } catch (SQLException e) {
            Dialog.error("You can't apply for this job.", "Not Allowed");
        }
    }

    private void setupPageStuff() {
        heading.setText("Application for " + jobPosting.getTitle());
        resumePath = "";
    }
}
