package enterprise.job;

import domain.Application;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobHomePage extends BaseFrame {
    private JPanel p;
    private JButton myApplicationsButton;
    private JButton companyJobPostingsButton;
    private JPanel mainPane;
    private JLabel heading;

    private boolean isStudent;
    private boolean isHr;

    public JobHomePage() {
        super();
        setContentPane(p);
        setupRoles();
        companyJobPostingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobPostingsPage().setVisible(true);
            }
        });
    }

    private void setupRoles() {
        var person = Application.getCurrentlyLoggedInPerson();

        isStudent = person.getRoles().stream().anyMatch(role -> role.getName().equals("COLLEGE_STUDENT"));
        isHr = person.getRoles().stream().anyMatch(role -> role.getName().equals("COMPANY_HR"));
        System.out.println("isStudent" + isStudent);
        System.out.println("isHr" + isHr);

        if (!isHr) {
//            addPersonPane.setVisible(false);
//            addPersonButton.setVisible(false);
//            myApplicationsButton.setVisible(true);

        } else {
//            jobPosterPanel.setVisible(true);
//            addPersonButton.setVisible(true);
//            myApplicationsButton.setVisible(false);
        }
    }
}
