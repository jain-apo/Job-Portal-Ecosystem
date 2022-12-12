package enterprise.job;

import domain.Application;
import domain.Roles;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static helpers.UiHelpers.buttonRole;

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
        setupActions();
    }

    private void setupActions() {
        companyJobPostingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobPostingsPage().setVisible(true);
            }
        });
        myApplicationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyApplicationPage().setVisible(true);
            }
        });
    }

    private void setupRoles() {
        var person = Application.getCurrentlyLoggedInPerson();

        var myApplicationsAccess = new String[]{
                Roles.ADMIN,
                Roles.JOB_PORTAL_ADMIN,
                Roles.COLLEGE_STUDENT,
        };

        try {
            buttonRole(myApplicationsButton, myApplicationsAccess, person);
        } catch (SQLException e) {

        }
    }
}
