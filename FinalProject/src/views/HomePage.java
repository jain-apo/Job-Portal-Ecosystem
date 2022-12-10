package views;

import domain.Application;
import enterprise.company.CompanyHomePage;
import enterprise.job.JobHomePage;
import enterprise.training.TrainingHomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends BaseFrame {
    private JPanel mainPanel;
    private JButton companyPortalButton;
    private JButton jobPortalButton;
    private JButton collegePortalButton;
    private JButton trainingPortalButton;
    private JButton logoutButton;
    private JLabel heading;
    private JButton adminPersonsDirectoryButton;

    HomePage() {
        super();

        heading.setText("Welcome, " + Application.getCurrentlyLoggedInPerson().getFirstName());

        setContentPane(mainPanel);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.setCurrentlyLoggedInPerson(null);
                switchToWindow(new LoginPage());
            }
        });
        companyPortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CompanyHomePage().setVisible(true);
            }
        });
        jobPortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JobHomePage().setVisible(true);
            }
        });
        adminPersonsDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PersonsDirectoryPage().setVisible(true);
            }
        });
        trainingPortalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrainingHomePage().setVisible(true);
            }
        });
    }
}
