package enterprise.company;

import models.JobCandidate;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static utils.FileUtil.openFileInExplorer;

public class CandidateProfile extends BaseFrame {
    private JPanel p;
    private JLabel candidateName;
    private JLabel dateOfBirth;
    private JLabel email;
    private JLabel phoneNo;
    private JPanel mainPane;
    private JButton viewResumeButton;
    private JButton viewCertificateButton;
    private JobCandidate candidate;

    public CandidateProfile(JobCandidate candidate) {
        super();
        this.candidate = candidate;

        try {
            getProfile();
        } catch (SQLException e) {
            Dialog.error("Error loading candidate profile");
        }

        setContentPane(p);
        setupActions();
        viewCertificateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ViewCertificate(candidate.getPerson()).setVisible(true);
                } catch (SQLException ex) {
                    //
                }
            }
        });
        viewResumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var file = candidate.getJobApplication().getResumeFile();

                    System.out.println(file);

                    openFileInExplorer(file);
                } catch (SQLException ex) {
                    //
                }
            }
        });
    }

    private void setupActions() {
        viewResumeButton.addActionListener(e -> {
            // TODO: implement view resume
        });
    }

    public void getProfile() throws SQLException {
        var person = candidate.getPerson();

        candidateName.setText(person.getFullName());
        dateOfBirth.setText(person.getDateOfBirth().toString());
        email.setText(person.getEmail());
        phoneNo.setText(person.getPhone());
    }
}
