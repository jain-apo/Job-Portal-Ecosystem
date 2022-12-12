package enterprise.job;

import domain.Application;
import domain.Roles;
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

        isStudent = person.hasRole(Roles.COLLEGE_STUDENT);
        isHr = person.hasRole(Roles.COMPANY_HR);

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
