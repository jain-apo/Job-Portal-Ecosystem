package enterprise.company;

import domain.Application;
import views.BaseFrame;

import javax.swing.*;
import java.sql.SQLException;

public class CandidateProfile extends BaseFrame {
    private JPanel mainPanel;
    private int candidateId;

    public CandidateProfile(int candidateId) {
        super();
        this.candidateId = candidateId;
        setContentPane(mainPanel);
    }

    public void getProfile() throws SQLException {
        var details = Application.Database.JobCandidates.getCandidateById(candidateId);
    }
}
