package enterprise.company;

import domain.Application;
import domain.Roles;
import models.JobCandidate;
import models.PersonNotification;
import models.PersonRole;
import utils.Dialog;
import utils.FileUtil;
import utils.ICallback;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TakeInterviewPage extends BaseFrame {

    private JButton acceptButton;
    private JButton rejectButton;
    private JButton profileButton;
    private JPanel p;
    private JPanel mainPane;
    private JButton viewResumeButton;
    private JLabel heading;
    private JobCandidate candidate;
    private ICallback callback;

    public TakeInterviewPage(JobCandidate candidate, ICallback callback) {
        super();
        this.candidate = candidate;
        this.callback = callback;

        setupPageStuff();

        setContentPane(p);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CandidateProfile(candidate).setVisible(true);
            }
        });
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptCandidate();
            }
        });
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rejectCandidate();
            }
        });
        viewResumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openResume();
            }
        });
    }

    private void openResume() {
        try {
            FileUtil.openFileInExplorer(candidate.getJobApplication().getResumeFile());
        } catch (SQLException e) {
            //
        }
    }

    private void rejectCandidate() {

        var result = Dialog.confirm("Are you sure you want to reject this candidate?", "Accept Candidate");

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            candidate.setIsRejected(true);

            Application.Database.JobCandidates.update(candidate);

            new PersonNotification(candidate.getPersonId(), "Interview Result",
                    "Your application has been rejected.").create();

            callback.callback();

            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void acceptCandidate() {
        var result = Dialog.confirm("Are you sure you want to accept this candidate?", "Accept Candidate");

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            candidate.setResult("Accepted");
            candidate.setIsAccepted(true);

            Application.Database.JobCandidates.update(candidate);

            var employeeRole = Application.Database.Roles.getAll()
                    .stream().filter(r -> r.getName().equals(Roles.COMPANY_EMPLOYEE))
                    .findFirst().orElse(null);

            if (employeeRole != null) {
                new PersonRole(candidate.getPersonId(), employeeRole.getId()).create();
            }

            new PersonNotification(candidate.getPersonId(), "Congratulations", "Congratulations on being accepted" +
                    " for " +
                    candidate.getJobApplication().getJobPosting().getTitle() + " role!").create();

            Dialog.info("Candidate accepted");

            callback.callback();

            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setupPageStuff() {
        try {
            heading.setText("Interview for " + candidate.getPerson().getFullName());
        } catch (SQLException e) {
            //
        }
    }
}
