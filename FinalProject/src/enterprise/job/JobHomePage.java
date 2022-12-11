package enterprise.job;

import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobHomePage extends BaseFrame {
    private JPanel mainPane;
    private JButton myApplicationsButton;
    private JButton companyJobPostingsButton;

    public JobHomePage() {
        super();
        setContentPane(mainPane);
        companyJobPostingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobPostingsPage().setVisible(true);
            }
        });
        myApplicationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobPostingsPage().setVisible(true);
            }
        });
    }
}
