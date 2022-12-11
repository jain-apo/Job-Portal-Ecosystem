package enterprise.company;

import domain.Application;
import utils.Dialog;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class CandidateProfile extends BaseFrame {
    private JPanel p;
    private JLabel candidateName;
    private JLabel dateOfBirth;
    private JLabel email;
    private JLabel phoneNo;
    private JPanel mainPane;
    private int candidateId;

    public CandidateProfile(int candidateId) {
        super();
        this.candidateId = candidateId;

        try {
            getProfile();
        } catch (SQLException e) {
            Dialog.error("Error loading candidate profile");
        }

        setContentPane(p);
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
