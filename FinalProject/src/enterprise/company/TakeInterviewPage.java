package enterprise.company;

import domain.Application;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TakeInterviewPage extends BaseFrame {

    private final int candidateId;
    private JButton acceptButton;
    private JButton rejectButton;
    private JButton profileButton;
    private JPanel p;
    private JPanel mainPane;
    private JButton viewResumeButton;
    private JLabel heading;

    public TakeInterviewPage(int candidateId) {
        super();
        this.candidateId = candidateId;

        setupPageStuff();

        setContentPane(p);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO get candidate id from interview page
                new CandidateProfile(candidateId).setVisible(true);
            }
        });
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }
        });
    }

    private void setupPageStuff() {
        try {
            heading.setText(Application.Database.JobCandidates.getCandidateById(candidateId).getPerson().getFullName());
        } catch (SQLException e) {
            //
        }
    }
}
