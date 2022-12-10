package enterprise.company;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyHomePage extends BaseFrame {
    private JPanel mainPane;
    private JButton candidatePageButton;
    private JButton interviewPageButton;


    public CompanyHomePage() {
        super();
        setContentPane(mainPane);


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
    }
}
