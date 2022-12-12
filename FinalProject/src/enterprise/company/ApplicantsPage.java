package enterprise.company;

import domain.Application;
import helpers.TableHelpers;
import models.JobApplication;
import models.JobCandidate;
import models.PersonNotification;
import models.tablemodels.ApplicantsTableModel;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ApplicantsPage extends BaseFrame {
    private final int RESUME_COLUMN = 3;
    private final int MOVE_COLUMN = 4;
    private JPanel p;
    private JPanel mainPane;
    private JTable candidate;
    private JLabel heading;
    private JScrollPane sp;

    public ApplicantsPage() {
        super();
        displayPeople();
        setupAction();
        setContentPane(mainPane);
    }

    @Override
    public void setContentPane(Container contentPane) {
        sp.setMinimumSize(new Dimension(800, 600));
        super.setContentPane(contentPane);
    }

    public void setupAction() {
        candidate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                JTable target = (JTable) me.getSource();
                int row = target.getSelectedRow(); // selected row
                int column = target.getSelectedColumn(); // selected column
                JobApplication application = ((ApplicantsTableModel) candidate.getModel()).getDataAt(row);

                if (me.getClickCount() == 2) {
                    System.out.println("double click");

                    if (column == RESUME_COLUMN) {
                        // TODO show resume
                    } else if (column == MOVE_COLUMN) {
                        // TODO move to next stage

                        try {
                            var result = Dialog.confirm("Are you sure you want to move " + application.getPerson().getFullName() + " as a Job Candidate?", "Move to next stage?");

                            if (result == JOptionPane.YES_OPTION) {
                                var newJobCandidate = new JobCandidate(0, application.getPersonId(), application.getJobPostingId(), 0, "Application Accepted");
                                Application.Database.JobCandidates.add(newJobCandidate);

                                new PersonNotification(0, application.getPersonId(), "Application Accepted", "Your application for " + application.getJobPosting().getTitle() + " has been accepted.", null).create();

                                Dialog.info("Application moved to next stage");
                                displayPeople();
                            }
                        } catch (SQLException e) {
                            Dialog.error("Try again later.");
                        }
                    }

                }
            }
        });
    }

    private void displayPeople() {
        try {
            candidate.setModel(new ApplicantsTableModel().loadData(Application.Database.JobApplications.getAllNonCandidates()));
        } catch (SQLException e) {
            Dialog.error("Error loading candidates");
        }

        TableHelpers.centerColumn(candidate, 2);
        TableHelpers.centerColumn(candidate, 3);
        TableHelpers.centerColumn(candidate, 4);
    }
}
