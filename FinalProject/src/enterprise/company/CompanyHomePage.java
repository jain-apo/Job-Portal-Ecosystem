package enterprise.company;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyHomePage extends BaseFrame {
    private JPanel p;
    private JButton candidatePageButton;
    private JButton interviewPageButton;
    private JPanel mainPane;
    private JLabel heading;
    private JButton jobApplicationsButton;


    public CompanyHomePage() {
        super();
        setupActions();
        setContentPane(p);
    }

    private void setupActions() {
        candidatePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CandidatePage().setVisible(true);
            }
        });
        interviewPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InterviewPage().setVisible(true);
            }
        });
        jobApplicationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ApplicantsPage().setVisible(true);
            }
        });
    }
}
