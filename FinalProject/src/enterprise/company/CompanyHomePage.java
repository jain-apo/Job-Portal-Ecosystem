package enterprise.company;

import domain.Application;
import domain.Roles;
import views.BaseFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static helpers.UiHelpers.buttonRole;

public class CompanyHomePage extends BaseFrame {
    private JPanel p;
    private JButton candidatePageButton;
    private JButton interviewPageButton;
    private JPanel mainPane;
    private JLabel heading;
    private JButton jobApplicationsButton;


    public CompanyHomePage() {
        super();
        setupRoles();
        setupActions();
        setContentPane(p);
    }

    private void setupRoles() {
        try {
            buttonRole(candidatePageButton, new String[]{
                    Roles.ADMIN,
                    Roles.COMPANY_HR,
                    Roles.COMPANY_SYSADMIN,
            }, Application.getCurrentlyLoggedInPerson());

            buttonRole(interviewPageButton, new String[]{
                    Roles.ADMIN,
                    Roles.COMPANY_HR,
                    Roles.COMPANY_SYSADMIN,
                    Roles.COMPANY_EMPLOYEE,
            }, Application.getCurrentlyLoggedInPerson());

            buttonRole(jobApplicationsButton, new String[]{
                    Roles.ADMIN,
                    Roles.COMPANY_HR,
                    Roles.COMPANY_SYSADMIN,
            }, Application.getCurrentlyLoggedInPerson());

        } catch (SQLException e) {
            //
        }
    }

    private void setupActions() {
        candidatePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CandidatesPage().setVisible(true);
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
