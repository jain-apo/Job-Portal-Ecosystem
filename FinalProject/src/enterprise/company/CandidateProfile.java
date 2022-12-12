package enterprise.company;

import domain.Application;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CandidateProfile extends BaseFrame {
    private final int candidateId;
    private JPanel p;
    private JLabel candidateName;
    private JLabel dateOfBirth;
    private JLabel email;
    private JLabel phoneNo;
    private JPanel mainPane;
    private JButton viewResumeButton;
    private JButton viewCertificateButton;

    public CandidateProfile(int candidateId) {
        super();
        this.candidateId = candidateId;

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
                new ViewCertificate().setVisible(true);
            }
        });
    }

    private void setupActions() {
        viewResumeButton.addActionListener(e -> {
            // TODO: implement view resume
        });
    }

    public void getProfile() throws SQLException {
        var details = Application.Database.JobCandidates.getCandidateById(candidateId);

        var person = Application.Database.Persons.getById(details.getPersonId());

        candidateName.setText(person.getFullName());
        dateOfBirth.setText(person.getDateOfBirth().toString());
        email.setText(person.getEmail());
        phoneNo.setText(person.getPhone());
    }
}
