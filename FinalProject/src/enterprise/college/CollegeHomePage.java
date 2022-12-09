package enterprise.college;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CollegeHomePage extends BaseFrame {
    private JButton interviewsButton;
    private JPanel mainPane;
    private JButton candidateButton;

    public CollegeHomePage() {
        super();
        setContentPane(mainPane);
        candidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
