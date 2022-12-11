package enterprise.company;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeInterviewPage extends BaseFrame {

    private final int candidateId;
    private JButton acceptButton;
    private JButton rejectButton;
    private JButton profileButton;
    private JPanel p;

    public TakeInterviewPage(int candidateId) {
        super();
        this.candidateId = candidateId;
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
}
